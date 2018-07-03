package cn.yat.service;

import cn.yat.entity.*;
import cn.yat.mapper.BshJavaCodeMapper;
import cn.yat.mapper.OperationMapper;
import cn.yat.myentity.RunHttpResultEntity;
import cn.yat.util.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jayway.jsonpath.JsonPath;
import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class OperationService{
    @Autowired
    private OperationMapper operationMapper;
    @Autowired
    private BshJavaCodeMapper bshJavaCodeMapper;
    @Autowired
    private DbService ds;
    @Autowired
    private TestcaseService ts;
    @Autowired
    private ParameterService ps;
    @Autowired
    private CheckPointUtil cpu;
    @Autowired
    private ParamUtil pu;
    @Value("${runCaseLogDir}")
    private String runCaseLogDir;
    @Value("${reRunCount}")
    private int reRunCaseCount;

    public void addOps(JSONObject res , String userId , String operation)  throws Exception {
        int userIdInt = Integer.parseInt(userId);
        JSONObject oOperationJson = JSONObject.parseObject(operation);
        String name = oOperationJson.getString("name");
        String note = oOperationJson.getString("note");
        int envId = oOperationJson.getIntValue("envId");
        int opsType = oOperationJson.getIntValue("opsType");
        int dbId = oOperationJson.getIntValue("dbId");
        String dbSql = oOperationJson.getString("dbSql");
        boolean httpIsPost = oOperationJson.getBooleanValue("httpIsPost");
        String httpUrl = oOperationJson.getString("httpUrl");
        String httpParam = oOperationJson.getString("httpParam");
        int tcId = oOperationJson.getIntValue("tcId");
        String tcValList = oOperationJson.getString("tcValList");
        String javaCode = oOperationJson.getString("javaCode");
        int waitTime = oOperationJson.getIntValue("waitTime");
        Operation oOperation = new Operation();
        oOperation.setName(name);
        oOperation.setNote(note);
        oOperation.setEnvId(envId);
        oOperation.setOpsType(opsType);
        oOperation.setDbId(dbId);
        oOperation.setHttpIsPost(httpIsPost);
        oOperation.setDbSql(dbSql);
        oOperation.setHttpUrl(httpUrl);
        oOperation.setHttpParam(httpParam);
        oOperation.setTcId(tcId);
        oOperation.setTcValList(tcValList);
//        oOperation.setJavaCode(javaCode);
        oOperation.setWaitTime(waitTime);
        Date now = new Date();
        oOperation.setAddTime(now);
        oOperation.setAddUserId(userIdInt);
        oOperation.setUpdateTime(now);
        oOperation.setUpdateUserId(userIdInt);
        int ist = operationMapper.insertSelective(oOperation);
        if(ist == 1){
            OperationExample example = new OperationExample();
            OperationExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(name);
            criteria.andEnvIdEqualTo(envId);
            List<Operation> paramList = operationMapper.selectByExample(example);
            int opsId = paramList.get(0).getId();
            if(opsType == 4){
                String bshArgs = oOperationJson.getString("bshArgs");
                BshJavaCode bshJavaCode = new BshJavaCode();
                bshJavaCode.setOpsId(opsId);
                bshJavaCode.setCode(javaCode);
                bshJavaCode.setBshArgs(bshArgs);
                int ist2 = bshJavaCodeMapper.insertSelective(bshJavaCode);
                if(ist2 != 1){
                    throw new Exception("Add BshJavaCode Failed!");
                }
            }
            res.put("success", true);
            res.put("data", "insert success");
        }else{
            res.put("success", false);
            res.put("data", "insert failed");
        }
    }
    public void modifyOps(JSONObject res , String userId , String operation) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        JSONObject oOperationJson = JSONObject.parseObject(operation);
        int id = oOperationJson.getIntValue("id");
        Operation oOperation = operationMapper.selectByPrimaryKey(id);
        if(oOperation == null){
            throw new Exception("操作不存在，id="+id);
        }
        String name = oOperationJson.getString("name");
        String note = oOperationJson.getString("note");
        int envId = oOperationJson.getIntValue("envId");
        int opsType = oOperationJson.getIntValue("opsType");
        int dbId = oOperationJson.getIntValue("dbId");
        String dbSql = oOperationJson.getString("dbSql");
        boolean httpIsPost = oOperationJson.getBooleanValue("httpIsPost");
        String httpUrl = oOperationJson.getString("httpUrl");
        String httpParam = oOperationJson.getString("httpParam");
        int tcId = oOperationJson.getIntValue("tcId");
        String tcValList = oOperationJson.getString("tcValList");
        String javaCode = oOperationJson.getString("javaCode");
        int waitTime = oOperationJson.getIntValue("waitTime");
        oOperation.setName(name);
        oOperation.setNote(note);
        oOperation.setEnvId(envId);
        oOperation.setOpsType(opsType);
        oOperation.setDbId(dbId);
        oOperation.setHttpIsPost(httpIsPost);
        oOperation.setDbSql(dbSql);
        oOperation.setHttpUrl(httpUrl);
        oOperation.setHttpParam(httpParam);
        oOperation.setTcId(tcId);
        oOperation.setTcValList(tcValList);
//        oOperation.setJavaCode(javaCode);
        oOperation.setWaitTime(waitTime);
        Date now = new Date();
        oOperation.setUpdateTime(now);
        oOperation.setUpdateUserId(userIdInt);
        int ist = operationMapper.updateByPrimaryKeySelective(oOperation);
        if(ist == 1){
            if(opsType == 4){
                BshJavaCode bshJavaCode = bshJavaCodeMapper.selectByPrimaryKey(id);
                if(bshJavaCode == null){
                    throw new Exception("bshJavaCode = null! operationId = " + id);
                }
                String bshArgs = oOperationJson.getString("bshArgs");
                bshJavaCode.setCode(javaCode);
                bshJavaCode.setBshArgs(bshArgs);
                int ist2 = bshJavaCodeMapper.updateByPrimaryKeySelective(bshJavaCode);
                if(ist2 != 1){
                    throw new Exception("Modify BshJavaCode Failed!");
                }
            }
            res.put("success", true);
            res.put("data", "modify success");
        }else{
            res.put("success", false);
            res.put("data", "modify failed");
        }
    }

    public void getOps(JSONObject res , String search , String page , String count) throws Exception{
        int pageInt = Integer.parseInt(page);
        int countInt = Integer.parseInt(count);
        JSONObject oSearch = JSONObject.parseObject(search);

        String s_ops_id = oSearch.getString("s_ops_id").trim();
        String s_ops_name = oSearch.getString("s_ops_name").trim();
        String s_ops_note = oSearch.getString("s_ops_note").trim();
        int envId = oSearch.getIntValue("envId");


        OperationExample example = new OperationExample();
        OperationExample.Criteria criteria = example.createCriteria();
        criteria.andEnvIdEqualTo(envId);
        if(!s_ops_id.equals("")){
            try{
                int s_ops_id_int = Integer.parseInt(s_ops_id);
                criteria.andIdEqualTo(s_ops_id_int);
            }catch (Exception e){
                throw new Exception("您搜索的操作ID不是正整数！");
            }

        }
        if(!s_ops_name.equals("")){
            criteria.andNameLike("%"+s_ops_name+"%");
        }
        if(!s_ops_note.equals("")){
            criteria.andNoteLike("%"+s_ops_note+"%");
        }
        int totalCount = operationMapper.countByExample(example);
        int totalPage = (int)Math.ceil(totalCount/Double.parseDouble(count));
        totalPage = totalPage==0?1:totalPage;
        example.setOrderByClause("id limit "+(pageInt-1)*countInt+","+countInt);
        List<Operation> opsList = operationMapper.selectByExample(example);

        res.put("success", true);
        res.put("data", opsList);
        res.put("totalCount", totalCount);
        res.put("totalPage", totalPage);
    }

    public void deleteOps(JSONObject res , String userId, String opsId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int opsIdInt = Integer.parseInt(opsId);
        int del = operationMapper.deleteByPrimaryKey(opsIdInt);
        if(del == 1){
            res.put("success", true);
            res.put("data", "delete success");
        }else{
            res.put("success", false);
            res.put("data", "delete failed");
        }
    }
    public void getAllOps(JSONObject res) throws Exception{
        OperationExample example = new OperationExample();
        OperationExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        List<Operation> opsList = operationMapper.selectByExample(example);
        res.put("success", true);
        res.put("data", opsList);
    }
    public void getAllOpsByEnvId(JSONObject res ,String envId) throws Exception{
        int envIdInt = Integer.parseInt(envId);
        OperationExample example = new OperationExample();
        OperationExample.Criteria criteria = example.createCriteria();
        criteria.andEnvIdEqualTo(envIdInt);
        List<Operation> opsList = operationMapper.selectByExample(example);
        res.put("success", true);
        res.put("data", opsList);
    }
    public List<Operation> getOpsByEnvId(int envId) throws Exception{
        OperationExample example = new OperationExample();
        OperationExample.Criteria criteria = example.createCriteria();
        criteria.andEnvIdEqualTo(envId);
        return operationMapper.selectByExample(example);
    }
    public Operation getById(int id) throws Exception{
        return operationMapper.selectByPrimaryKey(id);
    }
    public int addOperation(Operation operation) throws Exception{
        int ist = operationMapper.insert(operation);
        if(ist > 0){
            OperationExample example = new OperationExample();
            OperationExample.Criteria criteria = example.createCriteria();
            criteria.andEnvIdEqualTo(operation.getEnvId());
            criteria.andNameEqualTo(operation.getName());
            List<Operation> l = operationMapper.selectByExample(example);
            if(l.size() > 0){
                return l.get(0).getId();
            }
        }
        return 0;
    }
    public void updateOperation(Operation operation) throws Exception{
        operationMapper.updateByPrimaryKey(operation);
    }
    public void addBshJavaCode(int oldId,int newId) throws Exception{
        BshJavaCode bshJavaCode = bshJavaCodeMapper.selectByPrimaryKey(oldId);
        if(bshJavaCode != null){
            bshJavaCode.setOpsId(newId);
            bshJavaCodeMapper.insert(bshJavaCode);
        }
    }
    public void getJavaCodeByOpsId(JSONObject res ,String opsId) throws Exception{
        int opsIdInt = Integer.parseInt(opsId);
        BshJavaCode bshJavaCode = bshJavaCodeMapper.selectByPrimaryKey(opsIdInt);
        res.put("success", true);
        res.put("data", bshJavaCode);
    }
    public List<Operation> getByIdsInOrder(String ids) throws Exception{
        List<Operation> list = Lists.newArrayList();
        if(ids!=null){
            for(String id : ids.split(",")){
                if(!id.trim().equals("")){
                    int idInt = Integer.parseInt(id);
                    Operation operation = operationMapper.selectByPrimaryKey(idInt);
                    if(operation != null){
                        list.add(operation);
                    }
                }
            }
        }
        return list;
    }
    public void debugOps(JSONObject res , String userId, String operation) throws Exception{
        JSONObject oOperationJson = JSONObject.parseObject(operation);
        int opsId = oOperationJson.getIntValue("id");
        Operation oOperation = operationMapper.selectByPrimaryKey(opsId);
        Map<String,Parameter> globalParamMap = pu.getGlobalParamMap(oOperation.getEnvId(),null);
        Map<String,String> dsParamMap = Maps.newHashMap();
        Map<String,String> localParamMap = Maps.newHashMap();
        runOps(res , "debug",null,oOperation,globalParamMap,dsParamMap,localParamMap,Maps.newHashMap());
    }
    public void runOps(JSONObject res,String opsFlag,String uuid,Operation operation,Map<String,Parameter> globalParamMap,Map<String,String> dsParamMap,Map<String,String> localParamMap,Map<String,String> httpResponseParamMap) throws Exception{
        String title = "";
        if(opsFlag.equals("pre")){
            title = "前置操作";
        }
        if(opsFlag.equals("after")){
            title = "测试后操作";
        }
        if(opsFlag.equals("post")){
            title = "后置操作";
        }
        // 是否跳过
        if(!opsFlag.equals("debug")){
            String skipParam = "ds.skip."+opsFlag+"."+operation.getName();
            String skipVal = null;
            try{
                skipVal = ps.getValByParamName(uuid,skipParam,globalParamMap,dsParamMap,localParamMap);
            }catch (Exception e){
                LogUtil.addLog(uuid,"获取参数","跳过标记,参数"+skipParam+"不存在！继续执行操作。","","","");
            }
            if(skipVal != null && (skipVal.trim().equals("true") || skipVal.trim().equals("1"))){
                LogUtil.addLog(uuid,title,"跳过标记,参数"+skipParam+"="+skipVal+",跳过操作<"+operation.getName()+">","brown","","");
                return;
            }
        }
        LogUtil.addLog(uuid,title,"开始"+operation.getName(),"","","");
        int opsType = operation.getOpsType();
        if(opsType == 1){
            int dbId = operation.getDbId();
            String dbSql = operation.getDbSql();
            String dbSqlNew = pu.replaceParam(uuid,dbSql,globalParamMap,dsParamMap,localParamMap);
            LogUtil.addLog(uuid,title,"执行SQL："+dbSqlNew,"blue","","");
            if(dbSqlNew.equals("")){
                throw new Exception("sql为空");
            }
            res.put("success", false);
            res.put("data", "请检查SQL语句!");
            if(dbSqlNew.toLowerCase().startsWith("select")){
                Db oDb = ds.getDbById(dbId);
                JSONObject obj = JdbcUtil.query(oDb.getIp(),oDb.getPort(),oDb.getDbName(),oDb.getUserName(),oDb.getPassWord(),dbSqlNew);
                JSONObject objData = obj.getJSONObject("data");
                for(String key : objData.keySet()){
                    String p = opsFlag+"."+key;
                    String v = objData.getString(key);
                    LogUtil.addLog(uuid,title,"得到参数："+p+" = "+v,"blue","","");
                    localParamMap.put(p,v);
                }
                res.put("success", true);
                res.put("data", obj);
                res.put("type", "sql-query");
                res.put("sql", dbSqlNew);
            }
            if(dbSqlNew.toLowerCase().startsWith("update") || dbSqlNew.toLowerCase().startsWith("insert")){
                Db oDb = ds.getDbById(dbId);
                int upd = JdbcUtil.update(oDb.getIp(),oDb.getPort(),oDb.getDbName(),oDb.getUserName(),oDb.getPassWord(),dbSqlNew);
                res.put("success", true);
                res.put("data", upd);
                res.put("type", "sql-update");
                res.put("sql", dbSqlNew);
            }
        }
        if(opsType == 3){
            int tcId = operation.getTcId();
            List<String> dataList = Lists.newArrayList();
            JSONArray tcValList = JSONArray.parseArray(operation.getTcValList());
            LogUtil.addLog(uuid,title,"执行用例 开始：caseId="+tcId,"white","darkorange","");
            Map<String,String> dsParamMapNew ;
            List<Map<String,String>> pList = ts.getDataSourceParamList(tcId);
            if(pList.size() > 0){
                dsParamMapNew = pList.get(pList.size()-1);
            }else{
                dsParamMapNew = new HashMap<>();
            }
            Testcase oTestcase = ts.getById(tcId);
//            Map<String,Parameter> globalParamMapNew = pu.getGlobalParamMap(oTestcase.getTestEnvId(),null);
            RunHttpResultEntity response = ts.runCase(oTestcase,uuid,globalParamMap,dsParamMapNew,httpResponseParamMap);
            if(!response.isPass()){
                LogUtil.addLog(uuid,title,"执行用例 失败：caseId="+tcId+"，原因："+response.getException(),"white","red","");
                throw new Exception(response.getException());
            }
            LogUtil.addLog(uuid,title,"执行用例 完成：caseId="+tcId,"white","darkorange","");
            for(int i=0;i<tcValList.size();i++){
                JSONObject o = tcValList.getJSONObject(i);
                String headerName = o.getString("h").trim();
                String cookieName = o.getString("c").trim();
                String jsonPath = o.getString("j").trim();
                String paramName = o.getString("p").trim();
                if(paramName.equals("")){
                    continue;
                }
                if(!headerName.equals("")){
                    Header[] headerArr = response.getHeader();
                    boolean getTheVal = false;
                    for(Header h : headerArr){
                        if(h.getName().equals(headerName)){
                            String p = opsFlag+"."+paramName;
                            String v = h.getValue();
                            LogUtil.addLog(uuid,title,"得到参数："+p+" = "+v,"blue","","");
                            if(!opsFlag.equals("debug")){
                                localParamMap.put(p,v);
                            }
                            dataList.add(paramName+" = "+v);
                            getTheVal = true;
                            break;
                        }
                    }
                    if(!getTheVal){
                        LogUtil.addLog(uuid,title,"用例id="+tcId+",响应Header中未找到名为"+headerName+"的header!","","lightgray","");
                    }
                    continue;
                }
                if(!cookieName.equals("")){
                    List<Cookie> cookieList = response.getCookie();
                    boolean getTheVal = false;
                    for(Cookie c : cookieList){
                        if(c.getName().equals(cookieName)){
                            String p = opsFlag+"."+paramName;
                            String v = c.getValue();
                            LogUtil.addLog(uuid,title,"得到参数："+p+" = "+v,"blue","","");
                            if(!opsFlag.equals("debug")){
                                localParamMap.put(p,v);
                            }
                            dataList.add(paramName+" = "+v);
                            getTheVal = true;
                            break;
                        }
                    }
                    if(!getTheVal){
                        LogUtil.addLog(uuid,title,"用例id="+tcId+",响应Cookie中未找到名为"+cookieName+"的cookie!","","lightgray","");
                    }
                    continue;
                }
                if(!jsonPath.equals("")){
                    String resp = response.getResponse();
                    if(resp == null){
                        LogUtil.addLog(uuid,title,"用例id="+tcId+",返回结果为null!","","lightgray","");
                        continue;
                    }
                    JSONObject respObj;
                    try{
                        respObj = JSONObject.parseObject(resp);
                    }catch (Exception e){
                        LogUtil.addLog(uuid,title,"用例id="+tcId+",返回结果不是JSON格式,无法通过JsonPath取值!","","lightgray","");
                        continue;
                    }
                    Object jsonpathRes = JsonPath.using(cpu.JSONPATH_CONFIG).parse(resp).read(jsonPath);
                    String left;
                    // list结果随机取一个值
                    if(jsonpathRes instanceof net.minidev.json.JSONArray){
                        net.minidev.json.JSONArray jsonpathResArr = (net.minidev.json.JSONArray)jsonpathRes;
                        int jsonpathResArrSize = jsonpathResArr.size();
                        if(jsonpathResArrSize == 0){
                            left = "";
                        }else{
                            int rd = ThreadLocalRandom.current().nextInt(jsonpathResArrSize);
                            left = cpu.convertJsonpathToString(jsonpathResArr.get(rd));
                        }
                    }else{
                        left = cpu.convertJsonpathToString(jsonpathRes);
                    }

                    if(left == null){
                        LogUtil.addLog(uuid,title,"用例id="+tcId+",返回结果中未找到匹配JsonPath="+jsonPath+"的值!","","lightgray","");
                        continue;
                    }else{
                        String p = opsFlag+"."+paramName;
                        String v = left;
                        LogUtil.addLog(uuid,title,"得到参数："+p+" = "+v,"blue","","");
                        if(!opsFlag.equals("debug")){
                            localParamMap.put(p,v);
                        }
                        dataList.add(paramName+" = "+v);
                    }
                    continue;
                }
            }
            res.put("success", true);
            res.put("data", dataList);
            res.put("response", response);
            res.put("type", "tc");
        }
        if(opsType == 4){
            int opsId = operation.getId();
            BshJavaCode bshJavaCode = bshJavaCodeMapper.selectByPrimaryKey(opsId);
            String bshArgs = bshJavaCode.getBshArgs().trim();
            String javaCode = bshJavaCode.getCode();
            String[] bshArgsArr = null;
            if(!bshArgs.equals("")){
                bshArgsArr = bshArgs.split(" ");
                for(int i=0;i<bshArgsArr.length;i++){
                    if(bshArgsArr[i].contains("${")){
                        bshArgsArr[i] = pu.replaceParam(uuid,bshArgsArr[i],globalParamMap,dsParamMap,localParamMap);
                    }
                }
            }
            Object ret = BeanShellUtil.execute(localParamMap,httpResponseParamMap,bshArgsArr,javaCode);
            LogUtil.addLog(uuid,title,"执行JAVA代码：完成。返回结果："+ret,"blue","","");
            res.put("success", true);
            res.put("data", "执行JAVA代码：完成。返回结果："+ret);
            res.put("type", "java");
        }
        if(opsType == 5){
            int waitTime = operation.getWaitTime();
            LogUtil.addLog(uuid,title,"执行等待时间："+waitTime+"秒","blue","","");
            Thread.sleep(waitTime*1000);
        }
        LogUtil.addLog(uuid,title,"完成"+operation.getName(),"","","");
    }

}
