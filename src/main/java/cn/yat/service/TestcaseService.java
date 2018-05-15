package cn.yat.service;

import cn.yat.entity.*;
import cn.yat.mapper.*;
import cn.yat.myentity.LogDataSourceEntity;
import cn.yat.myentity.LogEntity;
import cn.yat.myentity.RunHttpResultEntity;
import cn.yat.util.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by rong.gao on 2018/3/6.
 */
@Service
@Transactional
public class TestcaseService {
    @Autowired
    private ProjectService prjs;
    @Autowired
    private ParameterService ps;
    @Autowired
    private TeamService ts;
    @Autowired
    private ServiceService ss;
    @Autowired
    private EnvironmentService es;
    @Autowired
    private TestcaseMapper testcaseMapper;
    @Autowired
    private RunSummaryMapper runSummaryMapper;
    @Autowired
    private DataSourceLoopMapper dataSourceLoopMapper;
    @Autowired
    private UserService us;
    @Autowired
    private OperationService os;
    @Autowired
    private RecordUtil ru;
    @Autowired
    private ParamUtil pu;
    @Autowired
    private CheckPointUtil cpu;
    @Autowired
    private AsyncExecutorUtil aeu;
    @Autowired
    private JavaMailUtil jmu;
    @Value("${debugCaseLogDir}")
    private String debugCaseLogDir;
    @Value("${runCaseLogDir}")
    private String runCaseLogDir;
    @Value("${domain}")
    private String domain;
    @Value("${port}")
    private String port;
    @Value("${socket.timeout}")
    private int socketTimeout;
    @Value("${connect.timeout}")
    private int connectTimeout;
    @Value("${reRunCount}")
    private int reRunCaseCount;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public JSONObject post(HttpServletRequest request) {
        JSONObject res = new JSONObject();
        res.put("success", false);
        res.put("data", "N/A");
        try{
            String method = request.getParameter("method");
            if(method.equals("addTestcase")){
                String userId = request.getParameter("userId");
                String tc = request.getParameter("tc");
                String ds = request.getParameter("ds");
                addTestcase(res,userId,tc,ds);
            }
            if(method.equals("modifyTestcase")){
                String userId = request.getParameter("userId");
                String tc = request.getParameter("tc");
                String ds = request.getParameter("ds");
                modifyTestcase(res,userId,tc,ds);
            }
            if(method.equals("deleteTestcase")){
                String userId = request.getParameter("userId");
                String id = request.getParameter("id");
                deleteTestcase(res,userId,id);
            }
            if(method.equals("getTestcaseById")){
                String id = request.getParameter("id");
                getTestcaseById(res,id);
            }
            if(method.equals("getAllTestCase")){
                getAllTestCase(res);
            }
            if(method.equals("getAllCaseByEnvId")){
                String envId = request.getParameter("envId");
                getAllCaseByEnvId(res,envId);
            }
            if(method.equals("debugTestcase")){
                String userId = request.getParameter("userId");
                String caseId = request.getParameter("caseId");
                String dsIdx = request.getParameter("dsIdx");
                debugTestcase(res,userId,caseId,dsIdx);
            }
            if(method.equals("testRunHttp")){
                String userId = request.getParameter("userId");
                String isPost = request.getParameter("isPost");
                String envId = request.getParameter("envId");
                String url = request.getParameter("url");
                String parameters = request.getParameter("parameters");
                String preOpsIds = request.getParameter("preOpsIds");
                String ueHeaderList = request.getParameter("ueHeaderList");
                String ueCookieList = request.getParameter("ueCookieList");
                String ueGetHttpResList = request.getParameter("ueGetHttpResList");
                String ds = request.getParameter("ds");
                String dsIdx = request.getParameter("dsIdx");
                testRunHttp(res,userId,isPost,envId,url,parameters,preOpsIds,ueHeaderList,ueCookieList,ueGetHttpResList,ds,dsIdx);
            }
            if(method.equals("runDailyCiManually")){
                String userId = request.getParameter("userId");
                String subject = request.getParameter("subject");
                String testcaseIds = request.getParameter("testcaseIds");
                runDailyCiManually(res,userId,subject,testcaseIds);
            }
            if(method.equals("getCase")){
                String search = request.getParameter("search");
                String page = request.getParameter("page");
                String count = request.getParameter("count");
                getCase(res,search,page,count);
            }
            if(method.equals("getDebugLog")){
                String runId = request.getParameter("runId");
                getDebugLog(res,runId);
            }
            if(method.equals("getReportById")){
                String id = request.getParameter("id");
                getReportById(res,id);
            }
            if(method.equals("getCiReport")){
                String timeRange = request.getParameter("timeRange");
                String page = request.getParameter("page");
                String count = request.getParameter("count");
                getCiReport(res,timeRange,  page ,  count);
            }
            if(method.equals("getDebugReport")){
                String timeRange = request.getParameter("timeRange");
                String page = request.getParameter("page");
                String count = request.getParameter("count");
                getDebugReport(res,timeRange,  page ,  count);
            }
            if(method.equals("getDetailReportLog")){
                String uuid = request.getParameter("uuid");
                String isCi = request.getParameter("isCi");
                getDetailReportLog(res,uuid,isCi);
            }
            if(method.equals("getSummaryOfCase")){
                getSummaryOfCase(res);
            }
            if(method.equals("getSummaryOfCi")){
                getSummaryOfCi(res);
            }
            if(method.equals("getSummaryOfLastFiveCi")){
                getSummaryOfLastFiveCi(res);
            }
            if(method.equals("getSummaryOfTeamCase")){
                getSummaryOfTeamCase(res);
            }

        }catch(Exception e){
            e.printStackTrace();
            res.put("success", false);
            res.put("data", e.getMessage());
        }
        return res;
    }

    private void addTestcase(JSONObject res  , String userId , String tc, String ds) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        JSONObject oTestcaseJson = JSONObject.parseObject(tc);
        int teamId = oTestcaseJson.getIntValue("teamId");
        int status = oTestcaseJson.getIntValue("status");
        int serviceId = oTestcaseJson.getIntValue("serviceId");
        int testEnvId = oTestcaseJson.getIntValue("testEnvId");
        String method = oTestcaseJson.getString("method");
        boolean isPost = oTestcaseJson.getBooleanValue("isPost");
        String url = oTestcaseJson.getString("url");
        String parameters = oTestcaseJson.getString("parameters");
        String headerList = oTestcaseJson.getString("headerList");
        String cookieList = oTestcaseJson.getString("cookieList");
        String getHttpResList = oTestcaseJson.getString("getHttpResList");
        String note = oTestcaseJson.getString("note");
        String preOpsIds = oTestcaseJson.getString("preOpsIds");
        String afterTestOpsIds = oTestcaseJson.getString("afterTestOpsIds");
        String postOpsIds = oTestcaseJson.getString("postOpsIds");
        String httpCodeCheck = oTestcaseJson.getString("httpCodeCheck");
        String containCheck = oTestcaseJson.getString("containCheck");
        String notContainCheck = oTestcaseJson.getString("notContainCheck");
        String jsonCheck = oTestcaseJson.getString("jsonCheck");
        String dbCheck = oTestcaseJson.getString("dbCheck");
        Testcase oTestcase = new Testcase();
        oTestcase.setTeamId(teamId);
        oTestcase.setStatus(status);
        oTestcase.setServiceId(serviceId);
        oTestcase.setTestEnvId(testEnvId);
        oTestcase.setMethod(method);
        oTestcase.setIsPost(isPost);
        oTestcase.setUrl(url);
        oTestcase.setParameters(parameters);
        oTestcase.setHeaderList(headerList);
        oTestcase.setCookieList(cookieList);
        oTestcase.setGetHttpResList(getHttpResList);
        oTestcase.setNote(note);
        oTestcase.setPreOpsIds(preOpsIds);
        oTestcase.setAfterTestOpsIds(afterTestOpsIds);
        oTestcase.setPostOpsIds(postOpsIds);
        oTestcase.setHttpCodeCheck(httpCodeCheck);
        oTestcase.setContainCheck(containCheck);
        oTestcase.setNotContainCheck(notContainCheck);
        oTestcase.setJsonCheck(jsonCheck);
        oTestcase.setDbCheck(dbCheck);
        Date now = new Date();
        oTestcase.setAddTime(now);
        oTestcase.setAddUserId(userIdInt);
        oTestcase.setUpdateTime(now);
        oTestcase.setUpdateUserId(userIdInt);
        int ist = testcaseMapper.insertSelective(oTestcase);
        if(ist == 1){
            TestcaseExample example = new TestcaseExample();
            TestcaseExample.Criteria criteria = example.createCriteria();
            criteria.andTeamIdEqualTo(teamId);
            criteria.andNoteEqualTo(note);
            List<Testcase> paramList = testcaseMapper.selectByExample(example);
            int caseId = paramList.get(0).getId();
            //ds
            JSONObject oDs = JSONObject.parseObject(ds);
            JSONArray th = oDs.getJSONArray("th");
            JSONArray tr = oDs.getJSONArray("tr");
            DataSourceLoop oDataSourceLoop = new DataSourceLoop();
            oDataSourceLoop.setCaseId(caseId);
            oDataSourceLoop.setThList(th.toJSONString());
            oDataSourceLoop.setTrList(tr.toJSONString());
            int ist2 = dataSourceLoopMapper.insertSelective(oDataSourceLoop);
            if(ist2 == 1){
                res.put("success", true);
                res.put("data", caseId);
            }else{
                res.put("success", false);
                res.put("data", "insert DataSourceLoop failed");
            }
            ru.addRecord(userIdInt,"addTestcase",caseId+"");
        }else{
            res.put("success", false);
            res.put("data", "insert Testcase failed");
        }
    }
    public void modifyTestcase(JSONObject res , String userId , String tc ,String ds) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        JSONObject oTestcaseJson = JSONObject.parseObject(tc);
        int id = oTestcaseJson.getIntValue("id");
        Testcase oTestcase = testcaseMapper.selectByPrimaryKey(id);
        if(oTestcase == null){
            throw new Exception("测试用例不存在，id="+id);
        }
        int teamId = oTestcaseJson.getIntValue("teamId");
        int status = oTestcaseJson.getIntValue("status");
        int serviceId = oTestcaseJson.getIntValue("serviceId");
        int testEnvId = oTestcaseJson.getIntValue("testEnvId");
        String method = oTestcaseJson.getString("method");
        boolean isPost = oTestcaseJson.getBooleanValue("isPost");
        String url = oTestcaseJson.getString("url");
        String parameters = oTestcaseJson.getString("parameters");
        String headerList = oTestcaseJson.getString("headerList");
        String cookieList = oTestcaseJson.getString("cookieList");
        String getHttpResList = oTestcaseJson.getString("getHttpResList");
        String note = oTestcaseJson.getString("note");
        String preOpsIds = oTestcaseJson.getString("preOpsIds");
        String afterTestOpsIds = oTestcaseJson.getString("afterTestOpsIds");
        String postOpsIds = oTestcaseJson.getString("postOpsIds");
        String httpCodeCheck = oTestcaseJson.getString("httpCodeCheck");
        String containCheck = oTestcaseJson.getString("containCheck");
        String notContainCheck = oTestcaseJson.getString("notContainCheck");
        String jsonCheck = oTestcaseJson.getString("jsonCheck");
        String dbCheck = oTestcaseJson.getString("dbCheck");
        oTestcase.setTeamId(teamId);
        oTestcase.setStatus(status);
        oTestcase.setServiceId(serviceId);
        oTestcase.setTestEnvId(testEnvId);
        oTestcase.setMethod(method);
        oTestcase.setIsPost(isPost);
        oTestcase.setUrl(url);
        oTestcase.setParameters(parameters);
        oTestcase.setHeaderList(headerList);
        oTestcase.setCookieList(cookieList);
        oTestcase.setGetHttpResList(getHttpResList);
        oTestcase.setNote(note);
        oTestcase.setPreOpsIds(preOpsIds);
        oTestcase.setAfterTestOpsIds(afterTestOpsIds);
        oTestcase.setPostOpsIds(postOpsIds);
        oTestcase.setHttpCodeCheck(httpCodeCheck);
        oTestcase.setContainCheck(containCheck);
        oTestcase.setNotContainCheck(notContainCheck);
        oTestcase.setJsonCheck(jsonCheck);
        oTestcase.setDbCheck(dbCheck);
        Date now = new Date();
        oTestcase.setUpdateTime(now);
        oTestcase.setUpdateUserId(userIdInt);
        int ist = testcaseMapper.updateByPrimaryKeySelective(oTestcase);
        if(ist == 1){
            //ds
            JSONObject oDs = JSONObject.parseObject(ds);
            JSONArray th = oDs.getJSONArray("th");
            JSONArray tr = oDs.getJSONArray("tr");
            int ist2 ;
            DataSourceLoop oDataSourceLoop = dataSourceLoopMapper.selectByPrimaryKey(id);
            if(oDataSourceLoop == null){
                oDataSourceLoop = new DataSourceLoop();
                oDataSourceLoop.setCaseId(id);
                oDataSourceLoop.setThList(th.toJSONString());
                oDataSourceLoop.setTrList(tr.toJSONString());
                ist2 = dataSourceLoopMapper.insertSelective(oDataSourceLoop);
            }else{
                oDataSourceLoop.setThList(th.toJSONString());
                oDataSourceLoop.setTrList(tr.toJSONString());
                ist2 = dataSourceLoopMapper.updateByPrimaryKey(oDataSourceLoop);
            }
            if(ist2 == 1){
                res.put("success", true);
                res.put("data", "modify success");
            }else{
                res.put("success", false);
                res.put("data", "modify DataSourceLoop failed");
            }
            ru.addRecord(userIdInt,"modifyTestcase",id+"");
        }else{
            res.put("success", false);
            res.put("data", "modify Testcase failed");
        }
    }
    public void deleteTestcase(JSONObject res , String userId, String id) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int idInt = Integer.parseInt(id);
        Testcase oTestcase = testcaseMapper.selectByPrimaryKey(idInt);
        oTestcase.setStatus(0);
        int upd = testcaseMapper.updateByPrimaryKeySelective(oTestcase);
        if(upd == 1){
            ru.addRecord(userIdInt,"deleteTestcase",id);
            res.put("success", true);
            res.put("data", "delete success");
        }else{
            res.put("success", false);
            res.put("data", "delete failed");
        }
    }

    private void getTestcaseById(JSONObject res , String id) throws Exception{
        int idInt = Integer.parseInt(id);
        Testcase oTestcase = testcaseMapper.selectByPrimaryKey(idInt);
        Environment environment = es.getById(oTestcase.getTestEnvId());
        Project project = prjs.getById(environment.getProjectId());
        if(oTestcase != null){
            String preOpsIds = oTestcase.getPreOpsIds();
            String afterOpsIds = oTestcase.getAfterTestOpsIds();
            String postOpsIds = oTestcase.getPostOpsIds();
            List<Operation> preOpsList = os.getByIdsInOrder(preOpsIds);
            List<Operation> afterOpsList = os.getByIdsInOrder(afterOpsIds);
            List<Operation> postOpsList = os.getByIdsInOrder(postOpsIds);
            JSONArray preArr = ListUtil.getIdNameArray(preOpsList);
            JSONArray afterArr = ListUtil.getIdNameArray(afterOpsList);
            JSONArray postArr = ListUtil.getIdNameArray(postOpsList);
            res.put("success", true);
            res.put("data", oTestcase);
            res.put("prjName", project.getName());
            res.put("envName", environment.getName());
            DataSourceLoop dataSourceLoop = dataSourceLoopMapper.selectByPrimaryKey(idInt);
            if(dataSourceLoop != null){
                res.put("thList", JSONArray.parseArray(dataSourceLoop.getThList()));
                res.put("trList", JSONArray.parseArray(dataSourceLoop.getTrList()));
            }
            res.put("preOpsList", preArr);
            res.put("afterOpsList", afterArr);
            res.put("postOpsList", postArr);
        }else {
            res.put("success", false);
            res.put("data", "用例不存在，id="+id);
        }
    }

    public void getCase(JSONObject res , String search , String page , String count) throws Exception{
        int pageInt = Integer.parseInt(page);
        int countInt = Integer.parseInt(count);
        JSONObject oSearch = JSONObject.parseObject(search);
        //
        int s_teamId = oSearch.getIntValue("s_teamId");
        int s_serviceId = oSearch.getIntValue("s_serviceId");
//        int s_envId = oSearch.getIntValue("s_envId");
        int s_status = oSearch.getIntValue("s_status");
        String s_url = oSearch.getString("s_url").trim();
        String s_method = oSearch.getString("s_method").trim();
        String s_note = oSearch.getString("s_note").trim();
        String s_create_user = oSearch.getString("s_create_user").trim();
        String s_update_user = oSearch.getString("s_update_user").trim();
        String s_create_time = oSearch.getString("s_create_time").trim();
        String s_update_time = oSearch.getString("s_update_time").trim();

        int globalEnvironmentId = oSearch.getIntValue("globalEnvironmentId");

        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        TestcaseExample example = new TestcaseExample();
        TestcaseExample.Criteria criteria = example.createCriteria();
        criteria.andTestEnvIdEqualTo(globalEnvironmentId);
        if(!s_url.equals("")){
            criteria.andUrlLike("%"+s_url+"%");
        }
        if(!s_method.equals("")){
            criteria.andMethodLike("%"+s_method+"%");
        }
        if(!s_note.equals("")){
            criteria.andNoteLike("%"+s_note+"%");
        }
        if(s_teamId>0){
            criteria.andTeamIdEqualTo(s_teamId);
        }
        if(s_serviceId>0){
            criteria.andServiceIdEqualTo(s_serviceId);
        }
//        if(s_envId>0){
//            criteria.andTestEnvIdEqualTo(s_envId);
//        }
        if(s_status>=0){
            criteria.andStatusEqualTo(s_status);
        }else{
            //不显示删除项
            if(s_status == -1){
                criteria.andStatusGreaterThan(0);
            }
        }
        if(!s_create_user.equals("")){
            List<Integer> list = us.getUserIdByNameCn(s_create_user);
            if(list.size() > 0){
                criteria.andAddUserIdIn(list);
            }else{
                res.put("success", true);
                res.put("data", Lists.newArrayList());
                res.put("totalCount", 0);
                res.put("totalPage", 1);
                return ;
            }
        }
        if(!s_update_user.equals("")){
            List<Integer> list = us.getUserIdByNameCn(s_update_user);
            if(list.size() > 0){
                criteria.andUpdateUserIdIn(list);
            }else{
                res.put("success", true);
                res.put("data", Lists.newArrayList());
                res.put("totalCount", 0);
                res.put("totalPage", 1);
                return ;
            }
        }
        if(!s_create_time.equals("")){
            try{
                String [] arr = s_create_time.split("-");
                Date from = sf.parse(arr[0]+" 00:00:00");
                Date to = sf.parse(arr[1]+" 23:59:59");
                criteria.andAddTimeBetween(from,to);
            }catch (Exception e){
                throw new Exception("创建时间("+s_create_time+")格式不正确，请检查！");
            }
        }
        if(!s_update_time.equals("")){
            try{
                String [] arr = s_update_time.split("-");
                Date from = sf.parse(arr[0]+" 00:00:00");
                Date to = sf.parse(arr[1]+" 23:59:59");
                criteria.andUpdateTimeBetween(from,to);
            }catch (Exception e){
                throw new Exception("修改时间("+s_update_time+")格式不正确，请检查！");
            }
        }
        int totalCount = testcaseMapper.countByExample(example);
        int totalPage = (int)Math.ceil(totalCount/Double.parseDouble(count));
        totalPage = totalPage==0?1:totalPage;
        example.setOrderByClause("id limit "+(pageInt-1)*countInt+","+countInt);
        List<Testcase> caseList = testcaseMapper.selectByExample(example);
        JSONArray arr = new JSONArray();
        if(caseList.size() > 0){
            for(Testcase testcase : caseList){
                JSONObject obj = (JSONObject)JSONObject.toJSON(testcase);
                obj.put("teamName",ts.getNameById(testcase.getTeamId()));
                obj.put("serviceName",ss.getNameById(testcase.getServiceId()));
                obj.put("testEnvName",es.getNameById(testcase.getTestEnvId()));
                obj.put("addUserNameCn",us.getUserNameCnById(testcase.getAddUserId()));
                obj.put("updateUserNameCn",us.getUserNameCnById(testcase.getUpdateUserId()));
                arr.add(obj);
            }
        }
        res.put("success", true);
        res.put("data", arr);
        res.put("totalCount", totalCount);
        res.put("totalPage", totalPage);
    }
    public String getNameById(int id) throws Exception{
        String res = "";
        if(id>0){
            Testcase oTestcase =testcaseMapper.selectByPrimaryKey(id);
            res = oTestcase.getNote();
        }
        return res;
    }
    public Testcase getById(int id) throws Exception{
        Testcase oTestcase = null;
        if(id>0){
            oTestcase =testcaseMapper.selectByPrimaryKey(id);
        }
        return oTestcase;
    }
    public void getAllTestCase(JSONObject res) throws Exception{
        TestcaseExample example = new TestcaseExample();
        TestcaseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        List<Testcase> list = testcaseMapper.selectByExample(example);
        res.put("success", true);
        res.put("data", list);
    }
    public void getAllCaseByEnvId(JSONObject res,String envId) throws Exception{
        int envIdInt = Integer.parseInt(envId);
        TestcaseExample example = new TestcaseExample();
        TestcaseExample.Criteria criteria = example.createCriteria();
        criteria.andTestEnvIdEqualTo(envIdInt);
        List<Testcase> list = testcaseMapper.selectByExample(example);
        res.put("success", true);
        res.put("data", list);
    }
    public void getDebugLog(JSONObject res,String runId) throws Exception{
        int runIdInt = Integer.parseInt(runId);
        JSONArray arr = getLogByRunId(runIdInt);
        res.put("success", true);
        res.put("data", arr);
    }
    public JSONArray getLogByRunId(int runId) throws Exception{
        JSONArray arr = new JSONArray();
        LogEntity le = getLogEntity(runId);
        Map<Integer , List<LogDataSourceEntity>> me = le.getMap();
        for(int cid : me.keySet()){
            List<LogDataSourceEntity> ldseList = me.get(cid);
            String caseName = getNameById(cid);
            int i=1;
            for(LogDataSourceEntity ldse : ldseList){
                JSONObject obj = new JSONObject();
                obj.put("caseName",caseName+"("+(i++)+")");
                obj.put("status","running");
                if(ldse.getS() == 1){
                    obj.put("status","skip");
                }
                if(ldse.getF() == 1){
                    obj.put("status","fail");
                }
                if(ldse.getP() == 1){
                    obj.put("status","pass");
                }
                String logName = ldse.getL();
                if(logName != null){
                    logName = logName.trim();
                    if(!logName.equals("")){
                        JSONArray arrTmp = LogUtil.getLog(debugCaseLogDir,logName);
//                        arr.addAll(arrTmp);
                        obj.put("logList",arrTmp);
                    }
                }
                arr.add(obj);
            }
        }
        return arr;
    }
    public LogEntity getLogEntity(int runId) throws Exception{
        LogEntity le = LogUtil.getLogEntity(runId);
        if(le == null){
            RunSummary oRunSummary = runSummaryMapper.selectByPrimaryKey(runId);
            String log = oRunSummary.getLog();
            JSONObject leObj = JSONObject.parseObject(log);
            le = JSONObject.toJavaObject(leObj,LogEntity.class);
        }
        return le;
    }
    public void getSummaryOfCase(JSONObject res) throws Exception{
        int[] list = new int[30];
        Date[] dateList = new Date[30];
        Date now = new Date();
        for(int i=0;i<30;i++){
            Calendar ca = Calendar.getInstance();
            ca.setTime(now);
            ca.add(Calendar.DATE, i-29);
            Date newDate = ca.getTime();
            dateList[i] = newDate;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(now);
        ca.set(Calendar.DATE,1);
        ca.set(Calendar.HOUR_OF_DAY,0);
        ca.set(Calendar.MINUTE,0);
        ca.set(Calendar.SECOND,0);
        Date firstDayOfThisMonth = ca.getTime();
        TestcaseExample example = new TestcaseExample();
        TestcaseExample.Criteria criteria = example.createCriteria();
        criteria.andStatusGreaterThan(0);
        List<Testcase> caseList = testcaseMapper.selectByExample(example);
        int total = caseList.size();
        int increase = 0;
        int ci = 0;
        for(Testcase oTestcase : caseList){
            for(int i=0;i<30;i++){
                if(oTestcase.getAddTime().before(dateList[i])){
                    list[i]++;
                }
            }
            if(oTestcase.getAddTime().after(firstDayOfThisMonth)){
                increase ++;
            }
            if(oTestcase.getStatus()==2){
                ci ++;
            }
        }
        BigDecimal bd = new BigDecimal(ci*100.0/total);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String ciPercent = bd.toString();
        res.put("success", true);
        res.put("data", list);
        res.put("total", total);
        res.put("increase", increase);
        res.put("ciPercent", ciPercent);
        res.put("ci", ci);
    }
    public void getSummaryOfCi(JSONObject res) throws Exception{
        RunSummaryExample example = new RunSummaryExample();
        RunSummaryExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(0);
        criteria.andEndTimeIsNotNull();
        example.setOrderByClause("start_time asc limit 30");
        List<RunSummary> list = runSummaryMapper.selectByExample(example);
        JSONArray arr =new JSONArray();
        Date now = new Date();
        for(int i=0;i<30;i++){
            Calendar ca = Calendar.getInstance();
            ca.setTime(now);
            ca.add(Calendar.DATE, i-29);
            int year = ca.get(Calendar.YEAR);
            int month = ca.get(Calendar.MONTH);
            int day = ca.get(Calendar.DATE);
            JSONObject obj = new JSONObject();
            obj.put("year",year);
            obj.put("month",month);
            obj.put("day",day);
            double val = 0.00;
            for(RunSummary oRunSummary : list){
                Date date = oRunSummary.getStartTime();
                Calendar ca2 = Calendar.getInstance();
                ca2.setTime(date);
                int year2 = ca2.get(Calendar.YEAR);
                int month2 = ca2.get(Calendar.MONTH);
                int day2 = ca2.get(Calendar.DATE);
                if(year2==year && month2==month && day2==day){
                    JSONObject o = getPassRateBySummaryLog(oRunSummary);
                    val = o.getDouble("passRate");
                    break;
                }
            }
            obj.put("val",val);
            arr.add(obj);
        }
        res.put("success", true);
        res.put("data", arr);
    }
    private JSONObject getPassRateBySummaryLog(RunSummary oRunSummary) throws Exception{
        JSONObject obj = new JSONObject();
        String log = oRunSummary.getLog();
        JSONObject logObj = JSONObject.parseObject(log);
        LogEntity le = JSONObject.toJavaObject(logObj,LogEntity.class);
        Map<Integer , List<LogDataSourceEntity>> me = le.getMap();
        int pass=0,fail=0,skip=0,total=0;
        double passRate = 0.00;
        for(int cId : me.keySet()){
            List<LogDataSourceEntity> dseList = me.get(cId);
            for(LogDataSourceEntity dse : dseList){
                if(dse.getF()==1){
                    fail ++;
                }
                if(dse.getS()==1){
                    skip ++;
                }
                if(dse.getP()==1){
                    pass ++;
                }
                total ++;
            }
        }
        obj.put("pass",pass);
        obj.put("fail",pass);
        obj.put("skip",pass);
        obj.put("total",pass);
        if(total > 0){
            BigDecimal bd = new BigDecimal(pass*100.0/total);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            passRate = Double.parseDouble(bd.toString());
        }
        obj.put("passRate",passRate);
        return obj;
    }
    public void getSummaryOfLastFiveCi(JSONObject res) throws Exception{
        RunSummaryExample example = new RunSummaryExample();
        RunSummaryExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(0);
        example.setOrderByClause("start_time desc limit 5");
        List<RunSummary> list = runSummaryMapper.selectByExample(example);
        res.put("success", true);
        res.put("data", list);
    }

    private String getOperationDuration(Date date , Date now) throws Exception{
        long s = now.getTime() - date.getTime();
        if(s<1000){
            return s+"毫秒";
        }else{
            if(s< 60000){
                BigDecimal bd = new BigDecimal(s/1000.0);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                return bd.toString()+"秒";
            }else{
                if(s < 3600000){
                    BigDecimal bd = new BigDecimal(s/60000.0);
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    return bd.toString()+"分钟";
                }else{
                    if(s < 86400000){
                        BigDecimal bd = new BigDecimal(s/3600000.0);
                        bd = bd.setScale(2, RoundingMode.HALF_UP);
                        return bd.toString()+"小时";
                    }else{
                        BigDecimal bd = new BigDecimal(s/86400000.0);
                        bd = bd.setScale(2, RoundingMode.HALF_UP);
                        return bd.toString()+"天";
                    }
                }
            }
        }
    }
    public void getSummaryOfTeamCase(JSONObject res) throws Exception{
        List<Team> teamList = ts.getAllTeam();
        List<Environment> envList = es.getAllEnvironment();
        List<String> thList = Lists.newArrayList();
        for(Environment oEnvironment : envList){
            thList.add(oEnvironment.getName());
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.set(Calendar.DATE, 1);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        Date thisMonth = ca.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        JSONArray trList = new JSONArray();
        for(Team oTeam : teamList){
            JSONObject obj = (JSONObject) JSONObject.toJSON(oTeam);
            List<Integer> list = Lists.newArrayList();
            int total = 0;
            for(Environment oEnvironment : envList){
                TestcaseExample example = new TestcaseExample();
                TestcaseExample.Criteria criteria = example.createCriteria();
                criteria.andTeamIdEqualTo(oTeam.getId());
                criteria.andTestEnvIdEqualTo(oEnvironment.getId());
                criteria.andStatusGreaterThan(0);
                int count = testcaseMapper.countByExample(example);
                list.add(count);
                total += count;
            }
            TestcaseExample example2 = new TestcaseExample();
            TestcaseExample.Criteria criteria2 = example2.createCriteria();
            criteria2.andStatusEqualTo(2);
            criteria2.andTeamIdEqualTo(oTeam.getId());
            List<Testcase> testcaseList = testcaseMapper.selectByExample(example2);
            List<Integer> caseIdList = Lists.newArrayList();
            for(Testcase o : testcaseList){
                caseIdList.add(o.getId());
            }
            RunSummaryExample example3 = new RunSummaryExample();
            RunSummaryExample.Criteria criteria3 = example3.createCriteria();
            criteria3.andStartTimeGreaterThanOrEqualTo(thisMonth);
            List<RunSummary> rsList = runSummaryMapper.selectByExample(example3);
            List<Integer> caseIdList2 = Lists.newArrayList();
            String caseIdsStr = "";
            int failNum = 0;
            int passNum = 0;
            int totalNum = 0;
            String passRateNum = "0.00";
            for(RunSummary o : rsList){
                String log = o.getLog();
                if(log !=null){
                    JSONObject logObj = JSONObject.parseObject(log);
                    LogEntity le = JSONObject.toJavaObject(logObj,LogEntity.class);
                    Map<Integer , List<LogDataSourceEntity>> me = le.getMap();
                    for(int cId : me.keySet()){
                        if(caseIdList.contains(cId)){
                            List<LogDataSourceEntity> dseList = me.get(cId);
                            for(LogDataSourceEntity dse : dseList){
                                if(dse.getF()==1){
                                    failNum ++;
                                }
                                if(dse.getP()==1){
                                    passNum ++;
                                }
                                totalNum ++;
                            }
                        }
                    }
                }
            }
            if(totalNum > 0){
                BigDecimal bd = new BigDecimal(passNum*100.0/totalNum);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                passRateNum = bd.toString();
            }
            obj.put("tdList",list);
            obj.put("total",total);
            obj.put("nowMonth",sdf.format(thisMonth));
            obj.put("failNum",failNum);
            obj.put("totalNum",totalNum);
            obj.put("passRateNum",passRateNum);
            trList.add(obj);
        }
        res.put("success", true);
        res.put("data", "");
        res.put("thList", thList);
        res.put("trList", trList);
    }
    public void getCiReport(JSONObject res , String timeRange , String page , String count) throws Exception{
        JSONObject object = getReportList("ci",timeRange,page,count);
        res.put("success", true);
        res.put("data", object.getJSONArray("data"));
        res.put("totalCount", object.getIntValue("totalCount"));
        res.put("totalPage", object.getIntValue("totalPage"));
    }
    public void getDebugReport(JSONObject res , String timeRange , String page , String count) throws Exception{
        JSONObject object = getReportList("debug",timeRange,page,count);
        res.put("success", true);
        res.put("data", object.getJSONArray("data"));
        res.put("totalCount", object.getIntValue("totalCount"));
        res.put("totalPage", object.getIntValue("totalPage"));
    }
    private JSONObject getReportList(String reportType , String timeRange , String page , String count) throws Exception{
        int pageInt = Integer.parseInt(page);
        int countInt = Integer.parseInt(count);
        Date[] date = getDateByTimeRange(timeRange);
        RunSummaryExample example = new RunSummaryExample();
        RunSummaryExample.Criteria criteria = example.createCriteria();
        if(reportType.equals("ci")){
            criteria.andUserIdEqualTo(0);
            criteria.andIsCiEqualTo(true);
        }
        criteria.andStartTimeBetween(date[0], date[1]);
        int totalCount = runSummaryMapper.countByExample(example);
        int totalPage = (int)Math.ceil(totalCount/Double.parseDouble(count));
        totalPage = totalPage==0?1:totalPage;
        example.setOrderByClause("start_time desc limit "+(pageInt-1)*countInt+","+countInt);
        List<RunSummary> teamList = runSummaryMapper.selectByExample(example);
        JSONArray arr = new JSONArray();
        if(teamList.size() > 0){
            for(RunSummary team : teamList){
                JSONObject obj = (JSONObject)JSONObject.toJSON(team);
                obj.put("userName",us.getUserNameCnById(team.getUserId()));
                arr.add(obj);
            }
        }
        JSONObject object = new JSONObject();
        object.put("data", arr);
        object.put("totalCount", totalCount);
        object.put("totalPage", totalPage);
        return object;
    }
    private Date[] getDateByTimeRange(String timeRange) throws Exception{
        Date[] date = new Date[2];
        date[1] = new Date();
        Calendar calendar = Calendar.getInstance();
        if(timeRange.equals("day")){
            calendar.add(Calendar.DATE, -1);
        }
        if(timeRange.equals("week")){
            calendar.add(Calendar.DATE, -7);
        }
        if(timeRange.equals("month")){
            calendar.add(Calendar.MONTH, -1);
        }
        if(timeRange.equals("year")){
            calendar.add(Calendar.YEAR, -1);
        }
        date[0] = calendar.getTime();
        return date;
    }

    public void getReportById(JSONObject res,String id) throws Exception{
        int idInt = Integer.parseInt(id);
        RunSummary oRunSummary = runSummaryMapper.selectByPrimaryKey(idInt);
        if(oRunSummary!=null){
            String userName = us.getUserNameCnById(oRunSummary.getUserId());
            String log = oRunSummary.getLog();
            JSONObject logObj = JSONObject.parseObject(log);
            LogEntity le = JSONObject.toJavaObject(logObj,LogEntity.class);
            Map<Integer , List<LogDataSourceEntity>> me = le.getMap();
            int total = 0;
            int pass = 0;
            int skip = 0;
            int fail = 0;
//            JSONArray arr = new JSONArray();
            List<JSONObject> failList = Lists.newArrayList();
            List<JSONObject> passList = Lists.newArrayList();
            for(int cId : me.keySet()){
                String caseName = getNameById(cId);
                List<LogDataSourceEntity> dseList = me.get(cId);
                for(int i=0;i<dseList.size();i++){
                    LogDataSourceEntity dse = dseList.get(i);
                    JSONObject obj = (JSONObject)JSONObject.toJSON(dse);
                    obj.put("caseId",cId);
                    obj.put("caseName",caseName + "("+(i+1)+")");
                    if(dse.getP()==1){
                        pass ++;
                    }
                    if(dse.getS()==1){
                        skip ++;
                    }
                    if(dse.getF()==1){
                        fail ++;
                    }
                    total ++;
//                    arr.add(obj);
                    if(dse.getP()==1){
                        passList.add(obj);
                    }else{
                        failList.add(obj);
                    }
                }
            }
            Collections.sort(failList, new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject o1, JSONObject o2) {
                    if(o1.getLongValue("h") > o2.getLongValue("h")) return -1;
                    if(o1.getLongValue("h") == o2.getLongValue("h")) return 0;
                    if(o1.getLongValue("h") < o2.getLongValue("h")) return 1;
                    return 0;
                }
            });
            Collections.sort(passList, new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject o1, JSONObject o2) {
                    if(o1.getLongValue("h") > o2.getLongValue("h")) return -1;
                    if(o1.getLongValue("h") == o2.getLongValue("h")) return 0;
                    if(o1.getLongValue("h") < o2.getLongValue("h")) return 1;
                    return 0;
                }
            });
            List<JSONObject> sortList = Lists.newArrayList();
            sortList.addAll(failList);
            sortList.addAll(passList);
//            for(int i=0;i<arr.size();i++){
//                sortList.add(arr.getJSONObject(i));
//            }
//            Collections.sort(sortList, new Comparator<JSONObject>() {
//                @Override
//                public int compare(JSONObject o1, JSONObject o2) {
//                    if(o1.getIntValue("f") == 1){
//                        return -1;
//                    }
//                    if(o2.getIntValue("f") == 1){
//                        return 1;
//                    }
//                    if(o1.getLongValue("h") > o2.getLongValue("h")) return -1;
//                    if(o1.getLongValue("h") == o2.getLongValue("h")) return 0;
//                    if(o1.getLongValue("h") < o2.getLongValue("h")) return 1;
//                    return 0;
//                }
//            });

            res.put("data", oRunSummary);
            res.put("log", sortList);
            res.put("userName", userName);
            res.put("total", total);
            res.put("pass", pass);
            res.put("skip", skip);
            res.put("fail", fail);
            String passRate = "0.00";
            if(total > 0){
                BigDecimal bd = new BigDecimal(pass*100.0/total);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                passRate = bd.toString();
            }
            res.put("passRate", passRate);
            res.put("success", true);
        }else{
            res.put("success", false);
            res.put("data", "报告不存在，id="+id);
        }
    }
    public void getDetailReportLog(JSONObject res,String uuid, String isCi) throws Exception{
        Boolean isCiBool = Boolean.parseBoolean(isCi);
        String logParentPath = debugCaseLogDir;
        if(isCiBool){
            logParentPath = runCaseLogDir;
        }
        JSONArray logArr = LogUtil.getLog(logParentPath,uuid);
        res.put("success", true);
        res.put("data", logArr);
    }

    public void runDailyCiManually(JSONObject res , String userId , String subject , String testcaseIds) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        String debugEmailTmp ;
        User user  = us.getUserById(userIdInt);
        debugEmailTmp = user.getEmail();
        String userNameCn = user.getUsernameCn();
        String debugEmail = debugEmailTmp;
        List<Testcase> testcaseListTmp = null;
        if(!testcaseIds.equals("")){
            List<Integer> cidList = Lists.newArrayList();
            for(String s : testcaseIds.split(",")){
                int cid = Integer.parseInt(s);
                if(cid > 0){
                    cidList.add(cid);
                }
            }
            TestcaseExample example = new TestcaseExample();
            TestcaseExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(cidList);
            testcaseListTmp = testcaseMapper.selectByExample(example);
        }
        List<Testcase> testcaseList = testcaseListTmp;
        aeu.doExecute(new Runnable() {
            @Override
            public void run() {
                try {
                    runDailyCi(subject+"By"+userNameCn,debugEmail,testcaseList,userIdInt,0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ru.addRecord(userIdInt,"runDailyCiManually","");
        res.put("success", true);
        res.put("data", "已触发CI，运行结果名称："+subject+"By"+userNameCn);
    }
    public void runDailyCi(String subject,String debugEmail,List<Testcase> testcaseList,int userId,int sleep) throws Exception{
        if(testcaseList == null){
            TestcaseExample example = new TestcaseExample();
            TestcaseExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(2);
            testcaseList = testcaseMapper.selectByExample(example);
        }
        runTestcase4CI(testcaseList,subject,debugEmail,userId,sleep);
    }

    private void runTestcase4CI(List<Testcase> testcaseList,String subject,String debugEmail,int userId,int sleep) throws Exception{
        if(testcaseList.size() > 0){
            long now = System.currentTimeMillis();
            String nameTmp = subject + now;
            Date startTime = new Date();
            RunSummary oRunSummary = new RunSummary();
            oRunSummary.setName(nameTmp);
            oRunSummary.setIsCi(true);
            oRunSummary.setStartTime(startTime);
            oRunSummary.setUserId(userId);
            int ist = runSummaryMapper.insertSelective(oRunSummary);
            if(ist == 1){
                RunSummaryExample example1 = new RunSummaryExample();
                RunSummaryExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andNameEqualTo(nameTmp);
                List<RunSummary> runSummaryList = runSummaryMapper.selectByExample(example1);
                if(runSummaryList.size() == 1){
                    RunSummary oRunSummaryIst = runSummaryList.get(0);
                    int runId = oRunSummaryIst.getId();
                    LogEntity logEntity = LogUtil.addLogEntity(runId);
                    Map<String,Map<String,Parameter>> globalParamMapAll = pu.getAllGlobalParamMap();
                    for(Testcase oTestcase : testcaseList){
                        Map<String,Parameter> globalParamMap = globalParamMapAll.get(""+oTestcase.getTestEnvId());
                        List<Map<String,String>> pList = getDataSourceParamList(oTestcase.getId());
                        if(pList.size() > 0){
                            for(int i=1;i<=pList.size();i++){
                                runSingleCase("DataSourceLoop数据驱动","执行第"+i+"条" ,oTestcase,globalParamMap,pList.get(i-1),logEntity,runCaseLogDir,false);
                                if(sleep > 0){
                                    Thread.sleep(sleep);
                                }
                            }
                        }else{
                            runSingleCase("执行用例-非数据驱动","" ,oTestcase,globalParamMap,Maps.newHashMap(),logEntity,runCaseLogDir,false);
                            if(sleep > 0){
                                Thread.sleep(sleep);
                            }
                        }
                    }
                    reWriteRunSummary(runId,subject,logEntity);
                    // 发邮件
                    String msg = buildEmailMsg(runId,logEntity);
                    jmu.sendCiMail(msg,subject,debugEmail);
                }else{
                    throw new Exception("insert fail 2");
                }
            }else{
                throw new Exception("insert fail 1");
            }
        }
    }
    private String buildEmailMsg(int runId,LogEntity logEntity) throws Exception{
        String msg ;
        List<Project> projectList = prjs.getAllProject();
        Map<String ,Map<String,int[]>> map = Maps.newHashMap();
        for(Project p : projectList){
            List<cn.yat.entity.Service> serviceList = ss.getAllServiceByPrjId(p.getId());
            List<Environment> envList = es.getAllEnvironmentByPrjId(p.getId());
            for(cn.yat.entity.Service s : serviceList){
                map.put(p.getName()+"-"+s.getName(),Maps.newHashMap());
                for(Environment e : envList){
                    map.get(p.getName()+"-"+s.getName()).put(p.getName()+"-"+e.getName(),new int[4]);
                }
            }
        }
        int sum=0,sum_pass=0,sum_skip=0,sum_fail=0;
        String sum_pass_rate;
        Map<Integer , List<LogDataSourceEntity>> me = logEntity.getMap();
        for(int caseId : me.keySet()){
            String serviceName = ss.getNameByCaseId(caseId);
            String envName = es.getNameByCaseId(caseId);
            String projectName = prjs.getNameByCaseId(caseId);
            int [] intArr = map.get(projectName+"-"+serviceName).get(projectName+"-"+envName);
            int tmp=intArr[3],tmp_pass=intArr[0],tmp_skip=intArr[1],tmp_fail=intArr[2];
            List<LogDataSourceEntity> logDsList = me.get(caseId);
            for(LogDataSourceEntity logDs : logDsList){
                int fail = logDs.getF();
                int pass = logDs.getP();
                int skip = logDs.getS();
                if(skip==1){
                    sum_skip ++;
                    tmp_skip ++;
                }
                if(fail==1){
                    sum_fail ++;
                    tmp_fail ++;
                }
                if(pass==1){
                    sum_pass ++;
                    tmp_pass ++;
                }
                sum ++;
                tmp ++;
            }
            intArr[0] = tmp_pass;
            intArr[1] = tmp_skip;
            intArr[2] = tmp_fail;
            intArr[3] = tmp;
        }
        sum_pass_rate = calcPassRate(sum_pass,sum);
        msg = "<table border=\"2\" cellspacing=\"0\" style=\"border:1px solid #e7e7e7\">\n" +
                "<tr style=\"background-color:#F5F5F6;border:1px solid #e7e7e7\" align=\"center\">\n" +
                "<th style=\"border:1px solid #e7e7e7\" colspan=\"5\">总计</th>\n" +
                "</tr>\n" +
                "<tr style=\"background-color:#F5F5F6;border:1px solid #e7e7e7\" align=\"center\">\n" +
                "<th style=\"border:1px solid #e7e7e7\">成功率</th>\n" +
                "<th style=\"border:1px solid #e7e7e7\">成功</th>\n" +
                "<th style=\"border:1px solid #e7e7e7\">跳过</th>\n" +
                "<th style=\"border:1px solid #e7e7e7\">失败</th>\n" +
                "<th style=\"border:1px solid #e7e7e7\">用例总数</th>\n" +
                "</tr>\n" +
                "<tr align=\"center\">\n" +
                "<td style=\"background-color:lavender;border:1px solid #e7e7e7\">"+sum_pass_rate+"%</td>\n" +
                "<td style=\"background-color:lightgreen;border:1px solid #e7e7e7\">"+sum_pass+"</td>\n" +
                "<td style=\"background-color:lightgoldenrodyellow;border:1px solid #e7e7e7\">"+sum_skip+"</td>\n" +
                "<td style=\"background-color:lightcoral;border:1px solid #e7e7e7\">"+sum_fail+"</td>\n" +
                "<td style=\"background-color:aqua;border:1px solid #e7e7e7\">"+sum+"</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<br>\n";
        for(Project p : projectList){
            List<cn.yat.entity.Service> serviceList2 = ss.getAllServiceByPrjId(p.getId());
            List<Environment> envList2 = es.getAllEnvironmentByPrjId(p.getId());
            msg +=  "<table border=\"2\" cellspacing=\"0\" style=\"border:1px solid #e7e7e7\">\n" +
                    "<tr style=\"background-color:#F5F5F6;border:1px solid #e7e7e7\" align=\"center\">\n" +
                    "<th style=\"color:darksalmon;border:1px solid #e7e7e7\" rowspan=\"2\">"+p.getName()+"</th>\n" ;
            for(Environment e : envList2){
                msg += "<th style=\"border:1px solid #e7e7e7\" colspan=\"5\">"+e.getName()+"</th>\n";
            }
            msg +=  "</tr>\n" +
                    "<tr style=\"background-color:#F5F5F6;border:1px solid #e7e7e7\" align=\"center\">\n" ;
            for(Environment e : envList2){
                msg += "<th style=\"border:1px solid #e7e7e7\">成功率</th>\n" +
                        "<th style=\"border:1px solid #e7e7e7\">成功</th>\n" +
                        "<th style=\"border:1px solid #e7e7e7\">跳过</th>\n" +
                        "<th style=\"border:1px solid #e7e7e7\">失败</th>\n" +
                        "<th style=\"border:1px solid #e7e7e7\">用例总数</th>\n";
            }
            msg +=  "</tr>\n";
            for(cn.yat.entity.Service s : serviceList2){
                msg += "<tr align=\"center\">\n" +
                        "<td style=\"background-color:lightcyan;border:1px solid #e7e7e7\">"+s.getName()+"</td>\n";
                for(Environment e : envList2){
                    int[] intArr = map.get(p.getName()+"-"+s.getName()).get(p.getName()+"-"+e.getName());
                    String passRate = calcPassRate(intArr[0],intArr[3]);
                    msg +=  "<td style=\"color:blueviolet;border:1px solid #e7e7e7\">"+passRate+"%</td>\n" +
                            "<td style=\"color:green;border:1px solid #e7e7e7\">"+intArr[0]+"</td>\n" +
                            "<td style=\"color:yellowgreen;border:1px solid #e7e7e7\">"+intArr[1]+"</td>\n" +
                            "<td style=\"color:red;border:1px solid #e7e7e7\">"+intArr[2]+"</td>\n" +
                            "<td style=\"color:blue;border:1px solid #e7e7e7\">"+intArr[3]+"</td>\n";
                }
                msg +=  "</tr>\n";
            }
            msg +=  "</table><br/>\n";
        }
        msg += "<p>详细测试报告:<a href=\"http://"+domain+":"+port+"/yat/pages/report.html?reportId="+runId+"\" target=\"_blank\">报告链接</a></p>\n";
        //清除map
        map.clear();
        return msg;
    }
    private String calcPassRate(int pass , int total) throws Exception{
        String passRate;
        if(total != 0){
            BigDecimal bd = new BigDecimal(pass*100.0/total);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            passRate = bd.toString();
        }else{
            passRate = "0.00";
        }
        return passRate;
    }
    public void testRunHttp(JSONObject res,String userId,String isPost,String envId,String url,String parameters,String preOpsIds,String ueHeaderList,
                            String ueCookieList,String ueGetHttpResList,String ds,String dsIdx) throws Exception{
        int envIdInt = Integer.parseInt(envId);
        boolean isPostBool = Boolean.parseBoolean(isPost);
        Testcase oTestcase = new Testcase();
        oTestcase.setTestEnvId(envIdInt);
        oTestcase.setPreOpsIds(preOpsIds);
        oTestcase.setIsPost(isPostBool);
        oTestcase.setUrl(url);
        oTestcase.setParameters(parameters);
        oTestcase.setCookieList(ueCookieList);
        oTestcase.setHeaderList(ueHeaderList);
        oTestcase.setGetHttpResList(ueGetHttpResList);
        //param
        Map<String,Parameter> globalParamMap = pu.getGlobalParamMap(envIdInt,null);
        Map<String,String> dsParamMap = Maps.newHashMap();
        //ds-param
        if(!dsIdx.equals("")){
            JSONObject dsObj = JSONObject.parseObject(ds);
            JSONArray thArr = dsObj.getJSONArray("th");
            JSONArray trArr = dsObj.getJSONArray("tr");
            int dsTrIdx = Integer.parseInt(dsIdx);
            if(trArr.size() > 0){
                JSONArray tdArr = trArr.getJSONArray(dsTrIdx-1);
                makeDsMap(dsParamMap,tdArr,thArr);
            }
        }
        // run case
        RunHttpResultEntity oRunHttpResultEntity = runCase(oTestcase,null,globalParamMap,dsParamMap,null);
        JSONObject o = (JSONObject)JSONObject.toJSON(oRunHttpResultEntity);
        o.put("fullUrl",oRunHttpResultEntity.getUrl());
        o.put("fullMethod",isPostBool?"POST":"GET");
        o.put("fullPostData",oRunHttpResultEntity.getParameters());
        res.put("success",true);
        res.put("data",o);
    }
    public List<Map<String,String>> getDataSourceParamList(int caseId) throws Exception{
        List<Map<String,String>> pList = Lists.newArrayList();
        DataSourceLoop dataSourceLoop = dataSourceLoopMapper.selectByPrimaryKey(caseId);
        if(dataSourceLoop != null){
            JSONArray thArr = JSONArray.parseArray(dataSourceLoop.getThList());
            JSONArray trArr = JSONArray.parseArray(dataSourceLoop.getTrList());
            if(trArr.size() > 0){
                for(int i=0;i<trArr.size();i++){
                    JSONArray tdArr = trArr.getJSONArray(i);
                    Map<String,String> paramMap2 = new HashMap<>();
                    makeDsMap(paramMap2,tdArr,thArr);
                    pList.add(paramMap2);
                }
            }
        }
        return pList;
    }
    private void makeDsMap(Map<String,String> paramMap,JSONArray tdArr,JSONArray thArr) throws Exception{
        for(int j=0 ; j<thArr.size();j++){
            String k = "ds."+thArr.getString(j);
            String vJson = tdArr.getString(j);
            String v = JSONObject.parseObject(vJson).getString("v");
            paramMap.put(k,v);
        }
    }
    public void debugTestcase(JSONObject res,String userId,String caseId,String dsIdx) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int caseIdInt = Integer.parseInt(caseId);
        int dsIdxInt = Integer.parseInt(dsIdx);
        List<Map<String,String>> pList = getDataSourceParamList(caseIdInt);
        RunSummary oRunSummary = new RunSummary();
        Date now = new Date();
        String name = "DebugCase"+now.getTime();
        oRunSummary.setName(name);
        oRunSummary.setIsCi(false);
        oRunSummary.setUserId(userIdInt);
        oRunSummary.setStartTime(now);
        runSummaryMapper.insertSelective(oRunSummary);
        RunSummaryExample example = new RunSummaryExample();
        RunSummaryExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<RunSummary> runList = runSummaryMapper.selectByExample(example);
        oRunSummary = runList.get(0);
        int runId = oRunSummary.getId();
        Testcase oTestcase = testcaseMapper.selectByPrimaryKey(caseIdInt);
        Map<String,Parameter> globalParamMap = pu.getGlobalParamMap(oTestcase.getTestEnvId(),null);
        LogEntity logEntity = LogUtil.addLogEntity(runId);
        aeu.doExecute(new Runnable() {
            @Override
            public void run() {
                if(pList.size()>0){
                    if(dsIdxInt >= 0){
                        runSingleCase("DataSourceLoop数据驱动-单行调试","执行序号："+(dsIdxInt+1) ,oTestcase,globalParamMap,pList.get(dsIdxInt),logEntity,debugCaseLogDir,true);
                    }else{
                        for(int i=1;i<=pList.size();i++){
                            runSingleCase("DataSourceLoop数据驱动","执行第"+i+"条" ,oTestcase,globalParamMap,pList.get(i-1),logEntity,debugCaseLogDir,true);
                        }
                    }
                }else {
                    runSingleCase("用例调试-非数据驱动","" ,oTestcase,globalParamMap,Maps.newHashMap(),logEntity,debugCaseLogDir,true);
                }
                reWriteRunSummary(runId,null,logEntity);
            }
        });
        res.put("success", true);
        res.put("data", runId);
    }
    private void reWriteRunSummary(int runId,String subject,LogEntity logEntity) {
        RunSummary oRunSummary = runSummaryMapper.selectByPrimaryKey(runId);
        if(subject != null){
            oRunSummary.setName(subject);
        }
        oRunSummary.setLog(JSONObject.toJSONString(logEntity));
        oRunSummary.setEndTime(new Date());
        runSummaryMapper.updateByPrimaryKeySelective(oRunSummary);
        LogUtil.removeLogEntity(runId);
    }

    private void runSingleCase(String title,String content,Testcase oTestcase,Map<String,Parameter> globalParamMap,Map<String,String> dsMap,LogEntity logEntity,String logParentPath,boolean isDebug){
        String uuid = UUID.randomUUID().toString();
        LogUtil.addLog(uuid,title,"开始 "+content,"","deepskyblue","");
        int caseIdInt = oTestcase.getId();
        LogDataSourceEntity oLogDataSourceEntity = new LogDataSourceEntity();
        oLogDataSourceEntity.setL(uuid);
        Map<Integer , List<LogDataSourceEntity>> em = logEntity.getMap();
        List<LogDataSourceEntity> ldsList;
        if(em.containsKey(caseIdInt)){
            ldsList = em.get(caseIdInt);
        }else{
            ldsList = Lists.newArrayList();
            em.put(caseIdInt,ldsList);
        }
        ldsList.add(oLogDataSourceEntity);

        RunHttpResultEntity runRes = runCase(oTestcase,uuid,globalParamMap,dsMap,null);
        //失败重试
        if(!isDebug && !runRes.isPass()){
            for(int i=0;i<reRunCaseCount;i++){
                LogUtil.addLog(uuid,"异常重跑，第"+(i+1)+"次","异常原因："+runRes.getException(),"","lightpink","");
                runRes = runCase(oTestcase,uuid,globalParamMap,dsMap,null);
                if(runRes.isPass()){
                    LogUtil.addLog(uuid,"异常重跑，第"+(i+1)+"次","成功","","lightgreen","");
                    break;
                }else{
                    LogUtil.addLog(uuid,"异常重跑，第"+(i+1)+"次","失败，异常原因："+runRes.getException(),"","lightpink","");
                }
            }
        }
        LogUtil.flushLog(logParentPath,uuid);
        if(runRes.isSkip()){oLogDataSourceEntity.setS(1);}
        if(runRes.isPass()){
            oLogDataSourceEntity.setP(1);
        }else{
            oLogDataSourceEntity.setF(1);
        }
        oLogDataSourceEntity.setT(runRes.getTotalTime());
        oLogDataSourceEntity.setH(runRes.getResponseTime());
    }

    public RunHttpResultEntity runCase(Testcase testcase,String uuid,Map<String,Parameter> globalParamMap,Map<String,String> dsParamMap,Map<String,String> localParamMap) {
        Exception ee = null;
        long startTime = System.currentTimeMillis();
        long httpCostTime ;
        long totalCostTime ;
        if(localParamMap == null){
            localParamMap = Maps.newHashMap();
        }
        Map<String,String> httpResponseParamMap = Maps.newHashMap();
        RunHttpResultEntity httpResult = new RunHttpResultEntity();
        httpResult.setUuid(uuid);
        try {
            LogUtil.addLog(uuid,"开始执行测试用例","","white","green","");
            LogUtil.addLog(uuid,"ID",testcase.getId()+"","blue","","");
            LogUtil.addLog(uuid,"接口",testcase.getMethod(),"blue","","");
            LogUtil.addLog(uuid,"环境",es.getNameById(testcase.getTestEnvId()),"blue","","");
            LogUtil.addLog(uuid,"用例名称",testcase.getNote(),"blue","","");
            //param
            LogUtil.addLog(uuid,"打印参数-全局","开始","","lightyellow","");
            String str = "";
            for(String k : globalParamMap.keySet()){
                str += k + "=" + globalParamMap.get(k).getKvVal() + "；";
            }
            LogUtil.addLog(uuid,"打印参数-全局","完成："+str,"","lightyellow","");
            LogUtil.addLog(uuid,"打印参数-数据池","开始","","lightyellow","");
            str = "";
            for(String k : dsParamMap.keySet()){
                str += k + "=" + dsParamMap.get(k) + "；";
            }
            LogUtil.addLog(uuid,"打印参数-数据池","完成："+str,"","lightyellow","");
            //pre
            LogUtil.addLog(uuid,"前置操作 开始","","","lightyellow","");
            List<Operation> preOpsList = os.getByIdsInOrder(testcase.getPreOpsIds());
            for(Operation operation : preOpsList){
                os.runOps(new JSONObject(),"pre",uuid,operation,globalParamMap,dsParamMap,localParamMap,null);
            }
            LogUtil.addLog(uuid,"前置操作 完成","DONE","","lightyellow","");
            //tc
            LogUtil.addLog(uuid,"执行测试 开始","","","lightyellow","");
            String postStr = testcase.getIsPost()?"POST":"GET";
            Environment environment = es.getById(testcase.getTestEnvId());
            String url = environment.getHostUrl() + testcase.getUrl().trim();
            String parameters = testcase.getParameters().trim();
            if(url.contains("${")){
                url = pu.replaceParam(uuid,url,globalParamMap,dsParamMap,localParamMap);
            }
            if(parameters.contains("${")){
                parameters = pu.replaceParam(uuid,parameters,globalParamMap,dsParamMap,localParamMap);
            }
            int projectId = environment.getProjectId();
            Map<String,String> reqParams = Maps.newHashMap();
            if(testcase.getIsPost()){
                reqParams = pu.getReqParams(parameters);
                parameters = pu.clientSecret(uuid,projectId,reqParams);
            }else{
                int idx = url.indexOf("?");
                if(idx > 0){
                    String url_pre = url.substring(0,idx+1).trim();
                    String url_param = url.substring(idx+1).trim();
                    if(!url_param.equals("")){
                        reqParams = pu.getReqParams(url_param);
                        url_param = pu.clientSecret(uuid,projectId,reqParams);
                    }
                    url = url_pre + url_param;
                }
            }
            LogUtil.addLog(uuid,"获取cookie","开始","black","","");
            String cookieList = testcase.getCookieList();
            JSONArray cookieListArr = JSONArray.parseArray(cookieList);
            List<Cookie> inputCookies = Lists.newArrayList();
            String s = "";
            String hostUrl = environment.getHostUrl();
            String hostName = hostUrl.substring(hostUrl.indexOf("//")+2);
            for(int i=0;i<cookieListArr.size();i++){
                JSONObject o = cookieListArr.getJSONObject(i);
                String k = o.getString("k").trim();
                String v = o.getString("v").trim();
                if(!k.equals("")){
                    if(v.contains("${")){
                        v = pu.replaceParam(uuid,v,globalParamMap,dsParamMap,localParamMap);
                    }
                    BasicClientCookie oBasicClientCookie = new BasicClientCookie(k,v);
                    oBasicClientCookie.setDomain(hostName);
                    oBasicClientCookie.setPath("/");
                    inputCookies.add(oBasicClientCookie);
                    s += k+"="+v+";";
                }
            }
            LogUtil.addLog(uuid,"获取cookie","完成,cookies: "+s,"black","","");
            LogUtil.addLog(uuid,"获取header","开始","black","","");
            String headerList = testcase.getHeaderList();
            JSONArray headerListArr = JSONArray.parseArray(headerList);
            List<Header> inputHeaders = Lists.newArrayList();
            s = "";
            for(int i=0;i<headerListArr.size();i++){
                JSONObject o = headerListArr.getJSONObject(i);
                String k = o.getString("k").trim();
                String v = o.getString("v").trim();
                if(!k.equals("")){
                    if(v.contains("${")){
                        v = pu.replaceParam(uuid,v,globalParamMap,dsParamMap,localParamMap);
                    }
                    inputHeaders.add(new BasicHeader(k,v));
                    s += "["+k+":"+v+"],";
                }
            }

            LogUtil.addLog(uuid,"添加验签","开始","black","","");
            String sortedParamStr = SignatureVerifyUtil.getPramsString(reqParams);
            String xNowVerifyType = SignatureVerifyUtil.VERIFY_TPYE_DOUBLE;
            String headerS = "";
            if(reqParams.containsKey("udid")){
                String verify = SignatureVerifyUtil.getSignature(projectId,environment.getHostUrl(),sortedParamStr,reqParams,socketTimeout,connectTimeout);
                if(projectId == ClientSecretUtil.NOW_PROJECT_ID || projectId == ClientSecretUtil.MARS_PROJECT_ID){
                    if(verify != null){
                        if(projectId == ClientSecretUtil.NOW_PROJECT_ID){
                            inputHeaders.add(new BasicHeader("x-now-verify",verify));
                            inputHeaders.add(new BasicHeader("x-now-verify-type",xNowVerifyType));
                            LogUtil.addLog(uuid,"添加验签","完成,x-now-verify:"+verify+";x-now-verify-type:"+xNowVerifyType,"black","","");
                            headerS += "[x-now-verify:"+verify+"][x-now-verify-type:"+xNowVerifyType+"]";
                        }else{
                            inputHeaders.add(new BasicHeader("x-mars-verify",verify));
                            inputHeaders.add(new BasicHeader("x-mars-verify-type",xNowVerifyType));
                            LogUtil.addLog(uuid,"添加验签","完成,x-mars-verify:"+verify+";x-mars-verify-type:"+xNowVerifyType,"black","","");
                            headerS += "[x-mars-verify:"+verify+"][x-mars-verify-type:"+xNowVerifyType+"]";
                        }
                    }else{
                        LogUtil.addLog(uuid,"添加验签","验签信息为null，可能缺少必要参数，跳过验签","brown","","");
                    }
                }else{
                    inputHeaders.add(new BasicHeader("x-yoho-verify",verify));
                    LogUtil.addLog(uuid,"添加验签","完成,x-yoho-verify:"+verify,"black","","");
                    headerS += "[x-yoho-verify:"+verify+"]";
                }
            }else{
                LogUtil.addLog(uuid,"添加验签","跳过，udid不存在","brown","","");
            }
            LogUtil.addLog(uuid,"获取header","完成,headers: "+s+headerS,"black","","");

            LogUtil.addLog(uuid,"发送HTTP请求",postStr+" | "+url+" | "+parameters,"black","","");
            httpResult.setUrl(url);
            httpResult.setParameters(parameters);
            if(testcase.getIsPost()){
                httpResult = HttpUtil.doPost(url,parameters,inputCookies,inputHeaders,socketTimeout,connectTimeout);
            }else {
                httpResult = HttpUtil.doGet(url,parameters,inputCookies,inputHeaders,socketTimeout,connectTimeout);
            }
            httpCostTime = httpResult.getResponseTime();
            int httpCode = httpResult.getCode();
            String httpResponse = httpResult.getResponse();
            LogUtil.addLog(uuid,"HTTP请求耗时",httpCostTime + "ms","","","");
            LogUtil.addLog(uuid,"完成HTTP请求",httpResponse,"","","");
            LogUtil.addLog(uuid,"提取测试结果","开始","","","");
            String getHttpResList = testcase.getGetHttpResList();
            String getHttpResStr="";
            JSONArray arr = JSONArray.parseArray(getHttpResList);
            for(int i=0;i<arr.size();i++){
                JSONObject obj = arr.getJSONObject(i);
                if(obj != null){
                    String paramName = obj.getString("k").trim();
                    String jsonpath = obj.getString("v").trim();
                    if(!paramName.equals("") && !jsonpath.equals("")){
                        String paramVal = cpu.getValByJsonPath(httpResponse,jsonpath);
                        httpResponseParamMap.put(paramName,paramVal);
                        localParamMap.put(paramName,paramVal);
                        getHttpResStr += paramName + "=" + paramVal + ";";
                    }
                }
            }
            LogUtil.addLog(uuid,"提取测试结果","完成："+getHttpResStr,"","","");
            LogUtil.addLog(uuid,"执行测试 完成","DONE","","lightyellow","");
            //after
            LogUtil.addLog(uuid,"测试后操作 开始","","","lightyellow","");
            List<Operation> afterOpsList = os.getByIdsInOrder(testcase.getAfterTestOpsIds());
            for(Operation operation : afterOpsList){
                os.runOps(new JSONObject(),"after",uuid,operation,globalParamMap,dsParamMap,localParamMap,httpResponseParamMap);
            }
            LogUtil.addLog(uuid,"测试后操作 完成","DONE","","lightyellow","");
            //check point
            LogUtil.addLog(uuid,"检查点 开始","","","lightyellow","");
            cpu.doCheckPoint(uuid,httpCode,httpResponse,testcase.getHttpCodeCheck(),testcase.getContainCheck(),testcase.getNotContainCheck(),testcase.getJsonCheck(),testcase.getDbCheck(),globalParamMap,dsParamMap,localParamMap);
            LogUtil.addLog(uuid,"检查点 完成","DONE","","lightyellow","");
        }catch (Exception e){
            ee = e;
            LogUtil.addLog(uuid,"用例 失败",e.toString(),"","lightpink","");
            e.printStackTrace();
        }
        Exception ee2 = null;
        try{
            //post
            LogUtil.addLog(uuid,"后置操作 开始","","","lightyellow","");
            List<Operation> postOpsList = os.getByIdsInOrder(testcase.getPostOpsIds());
            for(Operation operation : postOpsList){
                os.runOps(new JSONObject(),"post",uuid,operation,globalParamMap,dsParamMap,localParamMap,httpResponseParamMap);
            }
            LogUtil.addLog(uuid,"后置操作 成功","DONE","","lightyellow","");
        }catch (Exception e2){
            ee2 = e2;
            LogUtil.addLog(uuid,"后置操作 失败",e2.toString(),"","lightpink","");
            e2.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        totalCostTime = endTime-startTime;
        httpResult.setTotalTime(totalCostTime);
        LogUtil.addLog(uuid,"全部完成","用例ID："+testcase.getId()+"，耗时："+totalCostTime + "ms","white","mediumpurple","");

        if(ee == null && ee2 == null){
            httpResult.setPass(true);
            LogUtil.addLog(uuid,"用例执行结果","通过","","lightgreen","");
        }else{
            httpResult.setPass(false);
            if(ee == null && ee2 != null){
                ee = ee2;
            }
            httpResult.setException(ee.toString());
            LogUtil.addLog(uuid,"用例执行结果","失败","white","deeppink","");
        }

        return httpResult;
    }
}
