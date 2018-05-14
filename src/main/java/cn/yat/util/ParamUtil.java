package cn.yat.util;

import cn.yat.entity.Parameter;
import cn.yat.service.ParameterService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParamUtil {
    @Autowired
    private ParameterService ps;
    private final Pattern p= Pattern.compile("\\$\\{[a-zA-Z0-9.*~!%@#\\-\\[\\]_\\u4e00-\\u9fa5]+\\}");
    private final String doNotUserParamFlag = "不传参";
    public String replaceParam(String uuid , String input , Map<String,Parameter> globalParamMap, Map<String,String> dsParamMap, Map<String,String> localParamMap) throws Exception{
        Matcher m=p.matcher(input);
        String newIpt = input.trim();
        while(m.find()){
            String matchStr = m.group(0);
            String matchKey = matchStr.substring(2,matchStr.length()-1);
            String matchVal = ps.getValByParamName(uuid,matchKey,globalParamMap,dsParamMap,localParamMap);
            newIpt = newIpt.replace(matchStr,matchVal);
            LogUtil.addLog(uuid,"参数替换",matchStr+" = "+matchVal,"darkorchid","","");
        }
        if(newIpt.contains(doNotUserParamFlag)){
            newIpt = doNotUserParam(newIpt);
        }
        return newIpt;
    }
    private String doNotUserParam(String param) throws Exception{
        String [] arr = param.split("&");
        List<String> newParamList = Lists.newArrayList();
        for(String str : arr){
            if(str.contains(doNotUserParamFlag)){
                int idx = str.indexOf("=");
                if(idx >= 0){
                    String v = str.substring(idx + 1);
                    if(v.equals(doNotUserParamFlag)){
                        continue;
                    }
                }
            }
            newParamList.add(str);
        }
        return String.join("&", newParamList);
    }

    public Map<String,String> getReqParams(String parameter) throws Exception{
        Map<String,String> reqParams = Maps.newHashMap();
        parameter = parameter.trim();
        if(!parameter.equals("")){
            String [] arr = parameter.split("&");
            for(String str : arr){
                int idx = str.indexOf("=");
                if(idx <= 0){
                    throw new Exception("请求参数格式不正确："+parameter);
                }
                String k = str.substring(0,idx).trim();
                String v = str.substring(idx+1).trim();
                v = URLDecoder.decode(v,"UTF-8").trim();
                reqParams.put(k,v);
            }
        }
        return reqParams;
    }

    public String clientSecret(String uuid,int projectId,Map<String,String> reqParams) throws Exception{
        String clientSecret = ClientSecretUtil.getClientSecret(projectId,reqParams);
        reqParams.put("client_secret",clientSecret);
        List<String> array = new LinkedList<>();
        for(String k : reqParams.keySet()){
            String v = reqParams.get(k);
            if(projectId == ClientSecretUtil.MARS_PROJECT_ID){
                v = URLEncoder.encode(v,"UTF-8");
            }
            array.add(k+"="+v);
        }
        LogUtil.addLog(uuid,"生成client_secret",clientSecret,"darkorchid","","");
        return String.join("&", array);
    }

    public Map<String,Parameter> getGlobalParamMap(int envId,String excludeParamName) throws Exception{
        List<Parameter> paramList;
        if(envId==0){
            paramList = ps.getAllParam();
        }else{
            paramList = ps.getParamByEnvId(envId);
        }
        Map<String,Parameter> globalMap = Maps.newHashMap();
        for(Parameter parameter : paramList){
            if(excludeParamName == null){
                globalMap.put(parameter.getName(),parameter);
            }else{
                if(!parameter.getName().equals(excludeParamName)){
                    globalMap.put(parameter.getName(),parameter);
                }
            }
        }
        return globalMap;
    }
    public Map<String,Map<String,Parameter>> getAllGlobalParamMap() throws Exception{
        List<Parameter> paramList = ps.getAllParam();
        Map<String,Map<String,Parameter>> allGlobalMap = Maps.newHashMap();
        for(Parameter parameter : paramList){
            String k = ""+parameter.getEnvId();
            if(allGlobalMap.containsKey(k)){
                Map<String,Parameter> map = allGlobalMap.get(k);
                map.put(parameter.getName(),parameter);
            }else{
                Map<String,Parameter> map = Maps.newHashMap();
                map.put(parameter.getName(),parameter);
                allGlobalMap.put(k,map);
            }
        }
        return allGlobalMap;
    }
}
