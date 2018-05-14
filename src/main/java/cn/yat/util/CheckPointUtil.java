package cn.yat.util;

import cn.yat.entity.Parameter;
import cn.yat.service.ParameterService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Map;

@Component
public class CheckPointUtil {
    @Autowired
    private ParameterService ps;
    @Autowired
    private ParamUtil pu;
    private final String doNotCheckFlag = "不校验";
    public final Configuration JSONPATH_CONFIG = Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
    public void doCheckPoint(String uuid, int httpCode, String httpResponse, String httpCodeCheck , String containCheck, String notContainCheck, String jsonCheck, String dbParamCheck
    , Map<String,Parameter> globalParamMap,Map<String,String> dsParamMap,Map<String,String> localParamMap) throws Exception{
        if(httpCodeCheck!=null){
            httpCodeCheck = httpCodeCheck.trim();
            if(!httpCodeCheck.equals("")){
                if(httpCodeCheck.startsWith("${") && httpCodeCheck.endsWith("}")){
                    httpCodeCheck = pu.replaceParam(uuid,httpCodeCheck,globalParamMap,dsParamMap,localParamMap);
                }
                if(!httpCodeCheck.equals(doNotCheckFlag)){
                    int httpCodeCheckInt;
                    try {
                        httpCodeCheckInt = Integer.parseInt(httpCodeCheck);
                    }catch (Exception e){
                        throw new Exception("HttpCode检查点 失败，原因：httpCode["+httpCodeCheck+"]不是正整数");
                    }
                    if(httpCodeCheckInt == httpCode){
                        LogUtil.addLog(uuid,"检查点","[检查HTTP_CODE是否等于"+httpCodeCheck+"]------Pass","lightseagreen","","");
                    }else{
                        LogUtil.addLog(uuid,"检查点","[检查HTTP_CODE是否等于"+httpCodeCheck+"]------Fail","red","","");
                        throw new Exception("HttpCode检查点 失败，原因：httpCode="+httpCode+",不等于"+httpCodeCheck);
                    }
                }
            }
        }
        if(containCheck!=null){
            containCheck = containCheck.trim();
            if(!containCheck.equals("")){
                containCheck = pu.replaceParam(uuid,containCheck,globalParamMap,dsParamMap,localParamMap);
                if(!containCheck.equals(doNotCheckFlag)){
                    if(httpResponse.contains(containCheck)){
                        LogUtil.addLog(uuid,"检查点","[检查是否包含"+containCheck+"]------Pass","lightseagreen","","");
                    }else{
                        LogUtil.addLog(uuid,"检查点","[检查是否包含"+containCheck+"]------Fail","red","","");
                        throw new Exception("包含检查点 失败，原因：http response 不包含 "+containCheck);
                    }
                }
            }
        }
        if(notContainCheck!=null){
            notContainCheck = notContainCheck.trim();
            if(!notContainCheck.equals("")){
                notContainCheck = pu.replaceParam(uuid,notContainCheck,globalParamMap,dsParamMap,localParamMap);
                if(!notContainCheck.equals(doNotCheckFlag)){
                    if(!httpResponse.contains(notContainCheck)){
                        LogUtil.addLog(uuid,"检查点","[检查是否不包含"+notContainCheck+"]------Pass","lightseagreen","","");
                    }else{
                        LogUtil.addLog(uuid,"检查点","[检查是否不包含"+notContainCheck+"]------Fail","red","","");
                        throw new Exception("不包含检查点 失败，原因：http response 包含 "+notContainCheck);
                    }
                }
            }
        }
        if(jsonCheck!=null && !jsonCheck.trim().equals("")){
            JSONArray arr = JSONArray.parseArray(jsonCheck);
            for(int i=0;i<arr.size();i++){
                JSONObject obj = arr.getJSONObject(i);
                boolean hasIf = obj.containsKey("if");
                if(hasIf){
                    String jsonPath = obj.getString("k");
                    String compareType = obj.getString("t");
                    String compareValue = obj.getString("v");
                    compareValue = pu.replaceParam(uuid,compareValue,globalParamMap,dsParamMap,localParamMap);
                    if(!compareValue.equals(doNotCheckFlag)){
                        String left = getValByJsonPath(httpResponse,jsonPath);
                        boolean res = doCompare(left,compareType,compareValue);
                        if(res){
                            LogUtil.addLog(uuid,"检查点[If控制器]","[If控制器-Y]["+jsonPath+" "+compareType+" "+compareValue+"]------Y，进入If控制体","lightseagreen","","");
                            JSONArray ifArr = obj.getJSONArray("if");
                            for(int j=0;j<ifArr.size();j++){
                                doJsonPathCompare(uuid,ifArr.getJSONObject(j),httpResponse,globalParamMap,dsParamMap,localParamMap);
                            }
                        }else{
                            LogUtil.addLog(uuid,"检查点[If控制器]","[If控制器-N]["+jsonPath+"="+left+"]["+jsonPath+" "+compareType+" "+compareValue+"]------N，跳过If控制体","","","");
                        }
                    }else{
                        LogUtil.addLog(uuid,"检查点[不校验]","[不校验]["+jsonPath+" "+compareType+" "+compareValue+"]，跳过","","","");
                    }
                }else{
                    doJsonPathCompare(uuid,obj,httpResponse,globalParamMap,dsParamMap,localParamMap);
                }
            }
        }
        if(dbParamCheck!=null && !dbParamCheck.trim().equals("")){
            JSONArray arr = JSONArray.parseArray(dbParamCheck);
            for(int i=0;i<arr.size();i++){
                JSONObject obj = arr.getJSONObject(i);
                String paramName = obj.getString("k");
                String compareType = obj.getString("t");
                String compareValue = obj.getString("v");
                compareValue = pu.replaceParam(uuid,compareValue,globalParamMap,dsParamMap,localParamMap);
                if(!compareValue.equals(doNotCheckFlag)){
                    String left = ps.getValByParamName(uuid,paramName,globalParamMap,dsParamMap,localParamMap);
                    boolean res = doCompare(left,compareType,compareValue);
                    if(res){
                        LogUtil.addLog(uuid,"检查点","[参数检查点]["+paramName+" "+compareType+" "+compareValue+"]------Pass","lightseagreen","","");
                    }else{
                        LogUtil.addLog(uuid,"检查点","[参数检查点]["+paramName+"="+left+"]["+paramName+" "+compareType+" "+compareValue+"]------Fail","red","","");
                        throw new Exception("[参数检查点]["+paramName+" "+compareType+" "+compareValue+"]，检查失败");
                    }
                }
            }
        }
    }

    private void doJsonPathCompare(String uuid,JSONObject checkObj,String httpResponse,Map<String,Parameter> globalParamMap,Map<String,String> dsParamMap,Map<String,String> localParamMap) throws Exception{
        String jsonPath = checkObj.getString("k");
        String compareType = checkObj.getString("t");
        String compareValue = checkObj.getString("v");
        compareValue = pu.replaceParam(uuid,compareValue,globalParamMap,dsParamMap,localParamMap);
        if(!compareValue.equals(doNotCheckFlag)){
            String left = getValByJsonPath(httpResponse,jsonPath);
            boolean res = doCompare(left,compareType,compareValue);
            if(res){
                LogUtil.addLog(uuid,"检查点","[jsonPath检查点]["+jsonPath+" "+compareType+" "+compareValue+"]------Pass","lightseagreen","","");
            }else{
                LogUtil.addLog(uuid,"检查点","[jsonPath检查点]["+jsonPath+"="+left+"]["+jsonPath+" "+compareType+" "+compareValue+"]------Fail","red","","");
                throw new Exception("[jsonPath检查点]["+jsonPath+" "+compareType+" "+compareValue+"]，检查失败");
            }
        }
    }
    private boolean doCompare(String left , String compareType, String right) throws Exception {
        try{
            boolean res = false;
            if(left != null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                switch (compareType){
                    case "==":
                        res = Double.parseDouble(left)==Double.parseDouble(right)?true:false;
                        break;
                    case "!=":
                        res = Double.parseDouble(left)!=Double.parseDouble(right)?true:false;
                        break;
                    case ">":
                        res = Double.parseDouble(left)>Double.parseDouble(right)?true:false;
                        break;
                    case ">=":
                        res = Double.parseDouble(left)>=Double.parseDouble(right)?true:false;
                        break;
                    case "<":
                        res = Double.parseDouble(left)<Double.parseDouble(right)?true:false;
                        break;
                    case "<=":
                        res = Double.parseDouble(left)<=Double.parseDouble(right)?true:false;
                        break;
                    case "equals":
                        res = left.equals(right)?true:false;
                        break;
                    case "!equals":
                        res = left.equals(right)?false:true;
                        break;
                    case "contains":
                        res = left.contains(right)?true:false;
                        break;
                    case "!contains":
                        res = left.contains(right)?false:true;
                        break;
                    case "length==":
                        res = left.length()==right.length()?true:false;
                        break;
                    case "length!=":
                        res = left.length()!=right.length()?true:false;
                        break;
                    case "length>":
                        res = left.length()>right.length()?true:false;
                        break;
                    case "length>=":
                        res = left.length()>=right.length()?true:false;
                        break;
                    case "length<":
                        res = left.length()<right.length()?true:false;
                        break;
                    case "length<=":
                        res = left.length()<=right.length()?true:false;
                        break;
                    case "size==":
                        res = JSONArray.parseArray(left).size()==Integer.parseInt(right)?true:false;
                        break;
                    case "size!=":
                        res = JSONArray.parseArray(left).size()!=Integer.parseInt(right)?true:false;
                        break;
                    case "size>":
                        res = JSONArray.parseArray(left).size()>Integer.parseInt(right)?true:false;
                        break;
                    case "size>=":
                        res = JSONArray.parseArray(left).size()>=Integer.parseInt(right)?true:false;
                        break;
                    case "size<":
                        res = JSONArray.parseArray(left).size()<Integer.parseInt(right)?true:false;
                        break;
                    case "size<=":
                        res = JSONArray.parseArray(left).size()<=Integer.parseInt(right)?true:false;
                        break;
                    case "allEquals":
                        JSONArray arr = JSONArray.parseArray(left);
                        if(arr.size()==0){
                            if(right.equals("")){
                                res = true;
                            }
                        }else{
                            int count = 0;
                            for(int i=0;i<arr.size();i++){
                                if(arr.getString(i).equals(right)){
                                    count ++;
                                }
                            }
                            if(count == arr.size()){
                                res = true;
                            }
                        }
                        break;
                    case "allNotEquals":
                        arr = JSONArray.parseArray(left);
                        if(arr.size()==0){
                            if(!right.equals("")){
                                res = true;
                            }
                        }else{
                            int count = 0;
                            for(int i=0;i<arr.size();i++){
                                if(!arr.getString(i).equals(right)){
                                    count ++;
                                }
                            }
                            if(count == arr.size()){
                                res = true;
                            }
                        }
                        break;
                    case "oneEquals":
                        arr = JSONArray.parseArray(left);
                        if(arr.size()==0){
                            if(right.equals("")){
                                res = true;
                            }
                        }else{
                            for(int i=0;i<arr.size();i++){
                                if(arr.getString(i).equals(right)){
                                    res = true;
                                }
                            }
                        }
                        break;
                    case "oneNotEquals":
                        arr = JSONArray.parseArray(left);
                        if(arr.size()==0){
                            if(!right.equals("")){
                                res = true;
                            }
                        }else{
                            for(int i=0;i<arr.size();i++){
                                if(!arr.getString(i).equals(right)){
                                    res = true;
                                }
                            }
                        }
                        break;
                    case "allContains":
                        arr = JSONArray.parseArray(left);
                        if(arr.size()==0){
                            if(right.equals("")){
                                res = true;
                            }
                        }else{
                            int count = 0;
                            for(int i=0;i<arr.size();i++){
                                if(arr.getString(i).contains(right)){
                                    count ++;
                                }
                            }
                            if(count == arr.size()){
                                res = true;
                            }
                        }
                        break;
                    case "allNotContains":
                        arr = JSONArray.parseArray(left);
                        if(arr.size()==0){
                            if(!right.equals("")){
                                res = true;
                            }
                        }else{
                            int count = 0;
                            for(int i=0;i<arr.size();i++){
                                if(!arr.getString(i).contains(right)){
                                    count ++;
                                }
                            }
                            if(count == arr.size()){
                                res = true;
                            }
                        }
                        break;
                    case "oneContains":
                        arr = JSONArray.parseArray(left);
                        if(arr.size()==0){
                            if(right.equals("")){
                                res = true;
                            }
                        }else{
                            for(int i=0;i<arr.size();i++){
                                if(arr.getString(i).contains(right)){
                                    res = true;
                                }
                            }
                        }
                        break;
                    case "oneNotContains":
                        arr = JSONArray.parseArray(left);
                        if(arr.size()==0){
                            if(!right.equals("")){
                                res = true;
                            }
                        }else{
                            for(int i=0;i<arr.size();i++){
                                if(!arr.getString(i).contains(right)){
                                    res = true;
                                }
                            }
                        }
                        break;
                    case "before":
                        res = sdf.parse(left).before(sdf.parse(right))?true:false;
                        break;
                    case "timeEqual":
                        res = sdf.parse(left).compareTo(sdf.parse(right))==0?true:false;
                        break;
                    case "after":
                        res = sdf.parse(left).after(sdf.parse(right))?true:false;
                        break;
                }
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("检查点比较失败，左值："+left+"，比较类型："+compareType+"，右值："+right+"");
        }
    }

    public String convertJsonpathToString(Object jsonpathRes) throws Exception{
        if(jsonpathRes == null){
            return null;
        }
        if(jsonpathRes instanceof String){
            return (String)jsonpathRes;
        }
        if(jsonpathRes instanceof Integer){
            return String.valueOf((int)jsonpathRes);
        }
        if(jsonpathRes instanceof Double){
            return String.valueOf((double)jsonpathRes);
        }
        if(jsonpathRes instanceof Boolean){
            return String.valueOf((boolean)jsonpathRes);
        }
        return jsonpathRes.toString();
    }

    public String getValByJsonPath(String httpResponse,String jsonPath) throws Exception{
        Object jsonpathRes = JsonPath.using(JSONPATH_CONFIG).parse(httpResponse).read(jsonPath);
        return convertJsonpathToString(jsonpathRes);
    }
}
