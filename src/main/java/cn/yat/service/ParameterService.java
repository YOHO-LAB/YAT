package cn.yat.service;

import cn.yat.entity.*;
import cn.yat.mapper.ParameterMapper;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class ParameterService {
    @Autowired
    private ParameterMapper parameterMapper;
    @Autowired
    private EnvironmentService es;
    @Autowired
    private TestcaseService ts;
    @Autowired
    private ServiceService ss;
    @Autowired
    private UserService us;
    @Autowired
    private DbService ds;
    @Autowired
    private RecordUtil ru;
    @Autowired
    private ParamUtil pu;
    @Autowired
    private CheckPointUtil cpu;

    public void addParam(JSONObject res , String userId , String parameter) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        JSONObject oParameterJson = JSONObject.parseObject(parameter);
        int envId = oParameterJson.getIntValue("envId");
        String name = oParameterJson.getString("name");
        String note = oParameterJson.getString("note");
        int paramType = oParameterJson.getIntValue("paramType");
        String kvVal = oParameterJson.getString("kvVal");
        int dbId = oParameterJson.getIntValue("dbId");
        String dbSql = oParameterJson.getString("dbSql");
        String dbColumn = oParameterJson.getString("dbColumn");
        int dbGetValType = oParameterJson.getIntValue("dbGetValType");
        int tcId = oParameterJson.getIntValue("tcId");
        int tcGetValType = oParameterJson.getIntValue("tcGetValType");
        String tcJsonPath = oParameterJson.getString("tcJsonPath");
        String tcHeader = oParameterJson.getString("tcHeader");
        String tcCookie = oParameterJson.getString("tcCookie");
        int tcJsonMatchNum = oParameterJson.getIntValue("tcJsonMatchNum");
        String tcLeft = oParameterJson.getString("tcLeft");
        String tcRight = oParameterJson.getString("tcRight");
        int tcLrMatchNum = oParameterJson.getIntValue("tcLrMatchNum");
        int isUseDefault = oParameterJson.getIntValue("isUseDefault");
        String defaultValue = oParameterJson.getString("defaultValue");
        Parameter oParameter = new Parameter();
        oParameter.setEnvId(envId);
        oParameter.setName(name);
        oParameter.setNote(note);
        oParameter.setParamType(paramType);
        oParameter.setKvVal(kvVal);
        oParameter.setDbId(dbId);
        oParameter.setDbSql(dbSql);
        oParameter.setDbColumn(dbColumn);
        oParameter.setDbGetValType(dbGetValType);
        oParameter.setTcId(tcId);
        oParameter.setTcGetValType(tcGetValType);
        oParameter.setTcJsonPath(tcJsonPath);
        oParameter.setTcHeader(tcHeader);
        oParameter.setTcCookie(tcCookie);
        oParameter.setTcJsonMatchNum(tcJsonMatchNum);
        oParameter.setTcLeft(tcLeft);
        oParameter.setTcRight(tcRight);
        oParameter.setTcLrMatchNum(tcLrMatchNum);
        oParameter.setIsUseDefault(isUseDefault);
        oParameter.setDefaultValue(defaultValue);
        Date now = new Date();
        oParameter.setAddTime(now);
        oParameter.setAddUserId(userIdInt);
        oParameter.setUpdateTime(now);
        oParameter.setUpdateUserId(userIdInt);
        int ist = parameterMapper.insertSelective(oParameter);
        if(ist == 1){
            ParameterExample example = new ParameterExample();
            ParameterExample.Criteria criteria = example.createCriteria();
            criteria.andEnvIdEqualTo(envId);
            criteria.andNameEqualTo(name);
            List<Parameter> paramList = parameterMapper.selectByExample(example);
            ru.addRecord(userIdInt,"addParam",paramList.get(0).getId()+"");
            res.put("success", true);
            res.put("data", "insert success");
        }else{
            res.put("success", false);
            res.put("data", "insert failed");
        }
    }
    public void modifyParam(JSONObject res , String userId , String parameter) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        JSONObject oParameterJson = JSONObject.parseObject(parameter);
        int id = oParameterJson.getIntValue("id");
        Parameter oParameter = parameterMapper.selectByPrimaryKey(id);
        if(oParameter == null){
            throw new Exception("参数不存在，id="+id);
        }
        int envId = oParameterJson.getIntValue("envId");
        String name = oParameterJson.getString("name");
        String note = oParameterJson.getString("note");
        int paramType = oParameterJson.getIntValue("paramType");
        String kvVal = oParameterJson.getString("kvVal");
        int dbId = oParameterJson.getIntValue("dbId");
        String dbSql = oParameterJson.getString("dbSql");
        String dbColumn = oParameterJson.getString("dbColumn");
        int dbGetValType = oParameterJson.getIntValue("dbGetValType");
        int tcId = oParameterJson.getIntValue("tcId");
        int tcGetValType = oParameterJson.getIntValue("tcGetValType");
        String tcJsonPath = oParameterJson.getString("tcJsonPath");
        String tcHeader = oParameterJson.getString("tcHeader");
        String tcCookie = oParameterJson.getString("tcCookie");
        int tcJsonMatchNum = oParameterJson.getIntValue("tcJsonMatchNum");
        String tcLeft = oParameterJson.getString("tcLeft");
        String tcRight = oParameterJson.getString("tcRight");
        int tcLrMatchNum = oParameterJson.getIntValue("tcLrMatchNum");
        int isUseDefault = oParameterJson.getIntValue("isUseDefault");
        String defaultValue = oParameterJson.getString("defaultValue");
        oParameter.setEnvId(envId);
        oParameter.setName(name);
        oParameter.setNote(note);
        oParameter.setParamType(paramType);
        oParameter.setKvVal(kvVal);
        oParameter.setDbId(dbId);
        oParameter.setDbSql(dbSql);
        oParameter.setDbColumn(dbColumn);
        oParameter.setDbGetValType(dbGetValType);
        oParameter.setTcId(tcId);
        oParameter.setTcGetValType(tcGetValType);
        oParameter.setTcJsonPath(tcJsonPath);
        oParameter.setTcHeader(tcHeader);
        oParameter.setTcCookie(tcCookie);
        oParameter.setTcJsonMatchNum(tcJsonMatchNum);
        oParameter.setTcLeft(tcLeft);
        oParameter.setTcRight(tcRight);
        oParameter.setTcLrMatchNum(tcLrMatchNum);
        oParameter.setIsUseDefault(isUseDefault);
        oParameter.setDefaultValue(defaultValue);
        Date now = new Date();
        oParameter.setUpdateTime(now);
        oParameter.setUpdateUserId(userIdInt);
        int ist = parameterMapper.updateByPrimaryKeySelective(oParameter);
        if(ist == 1){
            ru.addRecord(userIdInt,"modifyParam",id+"");
            res.put("success", true);
            res.put("data", "modify success");
        }else{
            res.put("success", false);
            res.put("data", "modify failed");
        }
    }

    public void getParam(JSONObject res , String search , String page , String count) throws Exception{
        int pageInt = Integer.parseInt(page);
        int countInt = Integer.parseInt(count);
        JSONObject oSearch = JSONObject.parseObject(search);

        String s_env_id = oSearch.getString("s_env_id").trim();
        String s_param_name = oSearch.getString("s_param_name").trim();
        String s_param_note = oSearch.getString("s_param_note").trim();

        String isDiff = oSearch.getString("isDiff");
        String s_env_id_diff_destination = oSearch.getString("s_env_id_diff_destination");
        String diffMiss = oSearch.getString("diffMiss");
        boolean isDiffBool = Boolean.parseBoolean(isDiff);

        List<Parameter> paramList = null;
        int totalCount = 0;
        int totalPage = 1;
        int missCount = 0;
        int moreCount = 0;
        if(isDiffBool){
            int envIdDiffSource = Integer.parseInt(s_env_id);
            int envIdDiffDestination = Integer.parseInt(s_env_id_diff_destination);
            if(envIdDiffSource!=0&&envIdDiffDestination!=0){
                boolean diffMissBool = Boolean.parseBoolean(diffMiss);
                Object[] oMiss,oMore;
                if(diffMissBool){
                    oMiss = diffParam(envIdDiffDestination,envIdDiffSource,pageInt,countInt,false);
                    oMore = diffParam(envIdDiffSource,envIdDiffDestination,pageInt,countInt,true);
                    missCount = (int)oMiss[0];
                    moreCount = (int)oMore[0];
                    totalCount = missCount;
                    totalPage = (int)oMiss[1];
                    paramList = (List)oMiss[2];
                }else {
                    oMiss = diffParam(envIdDiffDestination,envIdDiffSource,pageInt,countInt,true);
                    oMore = diffParam(envIdDiffSource,envIdDiffDestination,pageInt,countInt,false);
                    missCount = (int)oMiss[0];
                    moreCount = (int)oMore[0];
                    totalCount = moreCount;
                    totalPage = (int)oMore[1];
                    paramList = (List)oMore[2];
                }
            }
        }else{
            ParameterExample example = new ParameterExample();
            ParameterExample.Criteria criteria = example.createCriteria();
            criteria.andIdIsNotNull();
            if(!s_env_id.equals("")){
                int s_env_id_int = Integer.parseInt(s_env_id);
                if(s_env_id_int > 0){
                    criteria.andEnvIdEqualTo(s_env_id_int);
                }
            }
            if(!s_param_name.equals("")){
                criteria.andNameLike("%"+s_param_name+"%");
            }
            if(!s_param_note.equals("")){
                criteria.andNoteLike("%"+s_param_note+"%");
            }

            totalCount = parameterMapper.countByExample(example);
            totalPage = (int)Math.ceil(totalCount/Double.parseDouble(count));
            totalPage = totalPage==0?1:totalPage;
            example.setOrderByClause("id limit "+(pageInt-1)*countInt+","+countInt);
            paramList = parameterMapper.selectByExample(example);
        }

        JSONArray arr = new JSONArray();
        if(paramList != null){
            for(Parameter p : paramList){
                JSONObject o = (JSONObject)JSONObject.toJSON(p);
                String paramVal = "";
                if(p.getParamType()==1){
                    paramVal = p.getKvVal();
                }
                o.put("paramVal",paramVal);
                arr.add(o);
            }
        }
        res.put("success", true);
        res.put("data", arr);
        res.put("totalCount", totalCount);
        res.put("totalPage", totalPage);
        res.put("missCount",missCount);
        res.put("moreCount",moreCount);
    }
    private Object[] diffParam(int envId1,int envId2,int pageInt ,int countInt,boolean onlyCount) throws Exception{
        Object[] o = new Object[3];
        ParameterExample example = new ParameterExample();
        ParameterExample.Criteria criteria = example.createCriteria();
        criteria.andEnvIdEqualTo(envId1);
        List<Parameter> dList = parameterMapper.selectByExample(example);
        List<String> nameList = Lists.newArrayList();
        for(Parameter p : dList){
            nameList.add(p.getName());
        }
        ParameterExample example2 = new ParameterExample();
        ParameterExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andEnvIdEqualTo(envId2);
        if(nameList.size()>0){
            criteria2.andNameNotIn(nameList);
        }
        int totalCount = parameterMapper.countByExample(example2);
        o[0] = totalCount;
        if(!onlyCount){
            int totalPage = (int)Math.ceil(totalCount*1.00/countInt);
            totalPage = totalPage==0?1:totalPage;
            o[1] = totalPage;
            if(pageInt >0 && countInt > 0){
                example2.setOrderByClause("id limit "+(pageInt-1)*countInt+","+countInt);
            }
            List<Parameter> list = parameterMapper.selectByExample(example2);
            o[2] = list;
        }
        return o;
    }
    public void copyParam(JSONObject res,String isDelete,String paramId,String sourceEnvId,String destinationEnvId) throws Exception{
        int destinationEnvIdInt = Integer.parseInt(destinationEnvId);
        boolean isDeleteBool = Boolean.parseBoolean(isDelete);
        String desEnvName = es.getNameById(destinationEnvIdInt);
        if(paramId == null){
            int sourceEnvIdInt = Integer.parseInt(sourceEnvId);
            Object[] o;
            if(isDeleteBool){
                o = diffParam(sourceEnvIdInt,destinationEnvIdInt,-1,-1,false);
            }else{
                o = diffParam(destinationEnvIdInt,sourceEnvIdInt,-1,-1,false);
            }
            List<Parameter> list = (List)o[2];
            String failParamName = "";
            int passNum = 0;
            int failNum = 0;
            for(Parameter p : list){
                int ist;
                if(isDeleteBool){
                    ist = parameterMapper.deleteByPrimaryKey(p.getId());
                }else{
                    p.setId(0);
                    p.setEnvId(destinationEnvIdInt);
                    ist = parameterMapper.insertSelective(p);
                }
                if(ist != 1){
                    failParamName += p.getName() + ",";
                    failNum ++;
                }else{
                    passNum ++;
                }
            }
            String msg ;
            if(isDeleteBool){
                msg = "删除";
            }else{
                msg = "复制";
            }
            if(failNum == 0){
                res.put("success", true);
                res.put("data", msg+"参数成功！共"+msg+"："+passNum+"个参数， ["+desEnvName+"]");
            }else{
                res.put("success", false);
                res.put("data", msg+"参数失败！共"+msg+"："+failNum+"个，失败参数："+failParamName+" ["+desEnvName+"]");
            }
        }else{
            int paramIdInt = Integer.parseInt(paramId);
            Parameter parameter = parameterMapper.selectByPrimaryKey(paramIdInt);
            parameter.setId(0);
            parameter.setEnvId(destinationEnvIdInt);
            int ist = parameterMapper.insertSelective(parameter);
            if(ist == 1){
                res.put("success", true);
                res.put("data", "参数复制成功！");
            }else{
                res.put("success", false);
                res.put("data", "参数复制失败！");
            }
        }
    }
    public void deleteParam(JSONObject res , String userId, String paramId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int paramIdInt = Integer.parseInt(paramId);
        int del = parameterMapper.deleteByPrimaryKey(paramIdInt);
        if(del == 1){
            ru.addRecord(userIdInt,"deleteParam",paramId);
            res.put("success", true);
            res.put("data", "delete success");
        }else{
            res.put("success", false);
            res.put("data", "delete failed");
        }
    }
    public void getAllParam(JSONObject res) throws Exception{
        ParameterExample example = new ParameterExample();
        ParameterExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        List<Parameter> paramList = parameterMapper.selectByExample(example);
        res.put("success", true);
        res.put("data", paramList);
    }
    public List<Parameter> getAllParam() throws Exception{
        ParameterExample example = new ParameterExample();
        ParameterExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        List<Parameter> paramList = parameterMapper.selectByExample(example);
        return paramList;
    }
    public void getAllHostParamByEnvId(JSONObject res,String envId) throws Exception{
        int envIdInt = Integer.parseInt(envId);
        ParameterExample example = new ParameterExample();
        ParameterExample.Criteria criteria = example.createCriteria();
        criteria.andEnvIdEqualTo(envIdInt);
        criteria.andNameLike("HOST%");
        criteria.andParamTypeEqualTo(1);
        List<Parameter> paramList = parameterMapper.selectByExample(example);
        JSONArray arr = new JSONArray();
        for(Parameter p : paramList){
            JSONObject obj = new JSONObject();
            obj.put("k",p.getName());
            obj.put("v",p.getKvVal());
            arr.add(obj);
        }
        res.put("success", true);
        res.put("data", arr);
    }
    public List<Parameter> getParamByEnvId(int envId) throws Exception{
        ParameterExample example = new ParameterExample();
        ParameterExample.Criteria criteria = example.createCriteria();
        criteria.andEnvIdEqualTo(envId);
        List<Parameter> paramList = parameterMapper.selectByExample(example);
        return paramList;
    }
    public Parameter getById(int id) throws Exception{
        return parameterMapper.selectByPrimaryKey(id);
    }
    public int addParameter(Parameter parameter) throws Exception{
        int ist = parameterMapper.insert(parameter);
        if(ist > 0){
            ParameterExample example = new ParameterExample();
            ParameterExample.Criteria criteria = example.createCriteria();
            criteria.andEnvIdEqualTo(parameter.getEnvId());
            criteria.andNameEqualTo(parameter.getName());
            List<Parameter> l = parameterMapper.selectByExample(example);
            if(l.size() > 0){
                return l.get(0).getId();
            }
        }
        return 0;
    }
    public void updateParameter(Parameter parameter) throws Exception{
        parameterMapper.updateByPrimaryKey(parameter);
    }

    private String getValOfGlobalParam(String uuid,Map<String,Parameter> globalParamMap,Parameter oParameter) throws Exception{
        if(oParameter.getParamType() == 1){//KV
            return oParameter.getKvVal();
        }
        if(oParameter.getParamType() == 2){//SQL
            String res;
            int isUseDefault = oParameter.getIsUseDefault();
            int dbId = oParameter.getDbId();
            String sql = oParameter.getDbSql();
            try{
                String column = oParameter.getDbColumn();
                int getValType = oParameter.getDbGetValType();
                Db db = ds.getDbById(dbId);
                String ip = db.getIp();
                int port = db.getPort();
                String dbName = db.getDbName();
                String userName = db.getUserName();
                String password = db.getPassWord();
                res = JdbcUtil.query(ip,port,dbName,userName,password,sql,column,getValType);
                if(isUseDefault == 1){
                    oParameter.setDefaultValue(res);
                    parameterMapper.updateByPrimaryKey(oParameter);
                }
            }catch (Exception e){
                if(isUseDefault == 1 || isUseDefault == 2){
                    res = oParameter.getDefaultValue();
                    LogUtil.addLog(uuid,"参数替换-默认值","参数名："+oParameter.getName()+"，sql(dbId="+dbId+")执行失败，返回默认值："+res+"，sql为"+sql+"，失败原因："+e.getMessage(),"darkorange","","");
                }else{
                    LogUtil.addLog(uuid,"参数替换","参数名："+oParameter.getName()+"，sql(dbId="+dbId+")执行失败","red","","");
                    throw e;
                }
            }
            oParameter.setParamType(1);
            oParameter.setKvVal(res);
            return res;
        }
        if(oParameter.getParamType() == 3){//TC
            int tcId = oParameter.getTcId();
            String tcHeader = oParameter.getTcHeader();
            String tcCookie = oParameter.getTcCookie();
            String tcJsonPath = oParameter.getTcJsonPath();
            String res;
            int isUseDefault = oParameter.getIsUseDefault();
            try {
                res = exeParamTc(globalParamMap,oParameter.getName(),tcId,tcHeader,tcCookie,tcJsonPath);
                if(isUseDefault == 1){
                    oParameter.setDefaultValue(res);
                    parameterMapper.updateByPrimaryKey(oParameter);
                }
            }catch (Exception e){
                if(isUseDefault == 1 || isUseDefault == 2){
                    res = oParameter.getDefaultValue();
                    LogUtil.addLog(uuid,"参数替换-默认值","参数名："+oParameter.getName()+"，用例(id="+tcId+")执行失败，返回默认值："+res+"，用例失败原因："+e.getMessage(),"darkorange","","");
                }else{
                    LogUtil.addLog(uuid,"参数替换","参数名："+oParameter.getName()+"，用例(id="+tcId+")执行失败","red","","");
                    throw e;
                }
            }
            oParameter.setParamType(1);
            oParameter.setKvVal(res);
            return res;
        }
        throw new Exception("参数类型["+oParameter.getParamType()+"]不存在！");
    }
    public String getValByParamName(String uuid , String paramName , Map<String,Parameter> globalParamMap,Map<String,String> dsParamMap,Map<String,String> localParamMap) throws Exception{
        if(localParamMap.containsKey(paramName)){
            return localParamMap.get(paramName);
        }
        if(dsParamMap.containsKey(paramName)){
            String val = dsParamMap.get(paramName);
            if(val.contains("${")){
                val = pu.replaceParam(uuid,val,globalParamMap,dsParamMap,localParamMap);
                dsParamMap.put(paramName,val);
            }
            return val;
        }
        if(globalParamMap.containsKey(paramName)){
            String res = getValOfGlobalParam(uuid,globalParamMap,globalParamMap.get(paramName));
            return res;
        }
        throw new Exception("参数 "+paramName+" ,取值失败！");
    }
    public void debugParameter(JSONObject res , String userId, String parameter) throws Exception{
        JSONObject oParameterJson = JSONObject.parseObject(parameter);
        int paramType = oParameterJson.getIntValue("paramType");
        if(paramType == 2){
            int dbId = oParameterJson.getIntValue("dbId");
            String dbSql = oParameterJson.getString("dbSql").trim();
            String dbColumn = oParameterJson.getString("dbColumn").trim();
            int dbGetValType = oParameterJson.getIntValue("dbGetValType");
            if(dbSql.equals("")){
                throw new Exception("sql为空");
            }
            if(dbColumn.equals("")){
                throw new Exception("未选择列名");
            }
            if(!dbSql.toLowerCase().startsWith("select")){
                throw new Exception("sql语句不是查询语句");
            }
            res.put("success", false);
            res.put("data", "请检查SQL语句、列名!");
            Db oDb = ds.getDbById(dbId);
            String data = JdbcUtil.query(oDb.getIp(),oDb.getPort(),oDb.getDbName(),oDb.getUserName(),oDb.getPassWord(),dbSql,dbColumn,dbGetValType);
            res.put("success", true);
            res.put("data", data);
        }
        if(paramType == 3){
            int tcId = oParameterJson.getIntValue("tcId");
            String paramName = oParameterJson.getString("name").trim();
            String tcJsonPath = oParameterJson.getString("tcJsonPath").trim();
            String tcHeader = oParameterJson.getString("tcHeader").trim();
            String tcCookie = oParameterJson.getString("tcCookie").trim();
            String val = exeParamTc(null,paramName,tcId,tcHeader,tcCookie,tcJsonPath);
            res.put("success", true);
            res.put("data", val);
        }
    }
    private String exeParamTc(Map<String,Parameter> globalParamMap,String paramName , int tcId,String tcHeader,String tcCookie,String tcJsonPath) throws Exception{
        Map<String,String> dsParamMap ;
        Testcase oTestcase = ts.getById(tcId);
        if(globalParamMap == null){
            globalParamMap = pu.getGlobalParamMap(oTestcase.getTestEnvId(),paramName);
        }
        List<Map<String,String>> pList = ts.getDataSourceParamList(tcId);
        if(pList.size() > 0){
            dsParamMap = pList.get(pList.size()-1);
        }else{
            dsParamMap = new HashMap<>();
        }
        RunHttpResultEntity response = ts.runCase(oTestcase,null,globalParamMap,dsParamMap, Maps.newHashMap());
        if(!response.isPass()){
            throw new Exception("获取全局变量参数失败，参数名："+paramName+"，参数类型：执行测试用例，失败原因："+response.getException());
        }
        if(!tcHeader.equals("")){
            Header[] headerArr = response.getHeader();
            for(Header h : headerArr){
                if(h.getName().equals(tcHeader)){
                    return h.getValue();
                }
            }
            throw new Exception("Header中未找到对应名称："+tcHeader);
        }
        if(!tcCookie.equals("")){
            List<Cookie> cookieList = response.getCookie();
            for(Cookie c : cookieList){
                if(c.getName().equals(tcCookie)){
                    return c.getValue();
                }
            }
            throw new Exception("Cookie中未找到对应名称："+tcCookie);
        }
        if(!tcJsonPath.equals("")){
            String resp = response.getResponse();
            if(resp == null){
                throw new Exception("用例id="+tcId+",返回结果为null!");
            }
            JSONObject respObj;
            try{
                respObj = JSONObject.parseObject(resp);
            }catch (Exception e){
                throw new Exception("用例id="+tcId+",返回结果不是JSON格式,无法通过JsonPath取值!");
            }
            Object jsonpathRes = JsonPath.using(cpu.JSONPATH_CONFIG).parse(resp).read(tcJsonPath);
            // list结果随机取一个值
            if(jsonpathRes instanceof net.minidev.json.JSONArray){
                net.minidev.json.JSONArray jsonpathResArr = (net.minidev.json.JSONArray)jsonpathRes;
                int jsonpathResArrSize = jsonpathResArr.size();
                if(jsonpathResArrSize == 0){
                    return "";
                }else{
                    int rd = ThreadLocalRandom.current().nextInt(jsonpathResArrSize);
                    jsonpathRes = jsonpathResArr.get(rd);
                }
            }

            String left = cpu.convertJsonpathToString(jsonpathRes);
            if(left == null){
                throw new Exception("用例id="+tcId+",返回结果中未找到匹配JsonPath="+tcJsonPath+"的值!");
            }else{
                return left;
            }
        }
        throw new Exception("Header Cookie JsonPath 均为空！");
    }
}
