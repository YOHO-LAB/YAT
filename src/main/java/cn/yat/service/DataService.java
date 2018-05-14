package cn.yat.service;

import cn.yat.entity.*;
import cn.yat.util.RecordUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rong.gao on 2018/3/6.
 */
@Service
@Transactional
public class DataService {
    @Autowired
    private TeamService ts;
    @Autowired
    private ServiceService ss;
    @Autowired
    private EnvironmentService es;
    @Autowired
    private ProjectService prjs;
    @Autowired
    private ParameterService ps;
    @Autowired
    private OperationService os;
    @Autowired
    private DbService ds;
    @Autowired
    private UserService us;
    @Autowired
    private RecordUtil ru;

    public JSONObject post(HttpServletRequest request) {
        JSONObject res = new JSONObject();
        res.put("success", false);
        res.put("data", "N/A");
        try{
            String method = request.getParameter("method");
            if(method.equals("addTeam") || method.equals("addService") || method.equals("addEnvironment") || method.equals("addDb")||method.equals("addProject")){
                String userId = request.getParameter("userId");
                String teamName = request.getParameter("teamName");
                String teamNote = request.getParameter("teamNote");
                if(method.equals("addTeam")){
                    ts.addTeam(res, userId , teamName,teamNote);
                }
                if(method.equals("addService")){
                    String prjId = request.getParameter("prjId");
                    ss.addService(res, userId , teamName,teamNote,prjId);
                }
                if(method.equals("addEnvironment")){
                    String hostUrl = request.getParameter("hostUrl");
                    String prjId = request.getParameter("prjId");
                    es.addEnvironment(res, userId , teamName,teamNote,hostUrl,prjId);
                }
                if(method.equals("addDb")){
                    String db_ip = request.getParameter("db_ip");
                    String db_port = request.getParameter("db_port");
                    String db_dbName = request.getParameter("db_dbName");
                    String db_user = request.getParameter("db_user");
                    String db_password = request.getParameter("db_password");
                    String envId = request.getParameter("envId");
                    ds.addDb(res, userId , teamName,teamNote,db_ip,db_port,db_dbName,db_user,db_password,envId);
                }
                if(method.equals("addProject")){
                    prjs.addProject(res, userId , teamName,teamNote);
                }
            }
            if(method.equals("getTeam") || method.equals("getService") || method.equals("getEnvironment") || method.equals("getDb")|| method.equals("getProject")){
                String search = request.getParameter("search");
                String page = request.getParameter("page");
                String count = request.getParameter("count");
                if(method.equals("getTeam")){
                    ts.getTeam(res , search,  page ,  count);
                }
                if(method.equals("getService")){
                    ss.getService(res , search,  page ,  count);
                }
                if(method.equals("getEnvironment")){
                    es.getEnvironment(res , search,  page ,  count);
                }
                if(method.equals("getDb")){
                    ds.getDb(res , search,  page ,  count);
                }
                if(method.equals("getProject")){
                    prjs.getProject(res , search,  page ,  count);
                }

            }
            if(method.equals("modifyTeam") || method.equals("modifyService") || method.equals("modifyEnvironment")|| method.equals("modifyDb")|| method.equals("modifyProject")){
                String userId = request.getParameter("userId");
                String teamName = request.getParameter("teamName");
                String teamNote = request.getParameter("teamNote");
                String teamId = request.getParameter("teamId");
                if(method.equals("modifyTeam")){
                    ts.modifyTeam(res, userId , teamName,teamNote,teamId);
                }
                if(method.equals("modifyService")){
                    String prjId = request.getParameter("prjId");
                    ss.modifyService(res, userId , teamName,teamNote,teamId,prjId);
                }
                if(method.equals("modifyEnvironment")){
                    String prjId = request.getParameter("prjId");
                    String hostUrl = request.getParameter("hostUrl");
                    es.modifyEnvironment(res, userId , teamName,teamNote,teamId,hostUrl,prjId);
                }
                if(method.equals("modifyDb")){
                    String db_ip = request.getParameter("db_ip");
                    String db_port = request.getParameter("db_port");
                    String db_dbName = request.getParameter("db_dbName");
                    String db_user = request.getParameter("db_user");
                    String db_password = request.getParameter("db_password");
                    String envId = request.getParameter("envId");
                    ds.modifyDb(res, userId , teamName,teamNote,teamId,db_ip,db_port,db_dbName,db_user,db_password,envId);
                }
                if(method.equals("modifyProject")){
                    prjs.modifyProject(res, userId , teamName,teamNote,teamId);
                }
            }
            if(method.equals("deleteTeam") || method.equals("deleteService") || method.equals("deleteEnvironment") || method.equals("deleteDb")|| method.equals("deleteProject")){
                String userId = request.getParameter("userId");
                String teamId = request.getParameter("teamId");
                if(method.equals("deleteTeam")){
                    ts.deleteTeam(res,userId,teamId);
                }
                if(method.equals("deleteService")){
                    ss.deleteService(res,userId,teamId);
                }
                if(method.equals("deleteEnvironment")){
                    es.deleteEnvironment(res,userId,teamId);
                }
                if(method.equals("deleteDb")){
                    ds.deleteDb(res,userId,teamId);
                }
                if(method.equals("deleteProject")){
                    prjs.deleteProject(res,userId,teamId);
                }
            }
            if(method.equals("getAllTeam") || method.equals("getAllService") || method.equals("getAllEnvironment")|| method.equals("getAllDb")|| method.equals("getAllProject")){
                if(method.equals("getAllTeam")){
                    ts.getAllTeam(res);
                }
                if(method.equals("getAllService")){
                    ss.getAllService(res);
                }
                if(method.equals("getAllEnvironment")){
                    es.getAllEnvironment(res);
                }
                if(method.equals("getAllDb")){
                    ds.getAllDb(res);
                }
                if(method.equals("getAllProject")){
                    prjs.getAllProject(res);
                }
            }
            //env
            if(method.equals("getEnvironmentByProjectId")){
                String projectId = request.getParameter("projectId");
                es.getEnvironmentByProjectId(res,projectId);
            }
            if(method.equals("getDestinationEnvironment")){
                String prjId = request.getParameter("prjId");
                String envId = request.getParameter("envId");
                es.getDestinationEnvironment(res,prjId,envId);
            }
            if(method.equals("getEnvironmentByEnvId")){
                String envId = request.getParameter("envId");
                es.getEnvironmentByEnvId(res,envId);
            }
            if(method.equals("getAllServiceByPrjId")){
                String projectId = request.getParameter("projectId");
                ss.getAllServiceByPrjId(res,projectId);
            }
            //db
            if(method.equals("getAllDbByEnvId")){
                String envId = request.getParameter("envId");
                ds.getAllDbByEnvId(res,envId);
            }
            //parameter
            if(method.equals("addParam")){
                String userId = request.getParameter("userId");
                String parameter = request.getParameter("parameter");
                ps.addParam(res,userId,parameter);
            }
            if(method.equals("getParam")){
                String search = request.getParameter("search");
                String page = request.getParameter("page");
                String count = request.getParameter("count");
                ps.getParam(res,search,page,count);
            }
            if(method.equals("copyParam")){
                String isDelete = request.getParameter("isDelete");
                String paramId = request.getParameter("paramId");
//                String sourceSrvId = request.getParameter("sourceSrvId");
                String sourceEnvId = request.getParameter("sourceEnvId");
//                String destinationSrvId = request.getParameter("destinationSrvId");
                String destinationEnvId = request.getParameter("destinationEnvId");
                ps.copyParam(res,isDelete,paramId,sourceEnvId,destinationEnvId);
            }
            if(method.equals("modifyParam")){
                String userId = request.getParameter("userId");
                String parameter = request.getParameter("parameter");
                ps.modifyParam(res, userId , parameter);
            }
            if(method.equals("deleteParam")){
                String userId = request.getParameter("userId");
                String paramId = request.getParameter("paramId");
                ps.deleteParam(res,userId,paramId);
            }
            if(method.equals("getAllParam")){
                ps.getAllParam(res);
            }
            if(method.equals("debugParameter")){
                String userId = request.getParameter("userId");
                String parameter = request.getParameter("parameter");
                ps.debugParameter(res,userId,parameter);
            }
            //operation
            if(method.equals("addOps")){
                String userId = request.getParameter("userId");
                String operation = request.getParameter("operation");
                os.addOps(res,userId,operation);
            }
            if(method.equals("getOps")){
                String search = request.getParameter("search");
                String page = request.getParameter("page");
                String count = request.getParameter("count");
                os.getOps(res,search,page,count);
            }
            if(method.equals("modifyOps")){
                String userId = request.getParameter("userId");
                String operation = request.getParameter("operation");
                os.modifyOps(res, userId , operation);
            }
            if(method.equals("deleteOps")){
                String userId = request.getParameter("userId");
                String opsId = request.getParameter("opsId");
                os.deleteOps(res,userId,opsId);
            }
            if(method.equals("getAllOps")){
                os.getAllOps(res);
            }
            if(method.equals("getAllOpsByEnvId")){
                String envId = request.getParameter("envId");
                os.getAllOpsByEnvId(res,envId);
            }
            if(method.equals("debugOps")){
                String userId = request.getParameter("userId");
                String operation = request.getParameter("operation");
                os.debugOps(res,userId,operation);
            }
            if(method.equals("getJavaCodeByOpsId")){
                String opsId = request.getParameter("opsId");
                os.getJavaCodeByOpsId(res,opsId);
            }
        }catch(Exception e){
            e.printStackTrace();
            res.put("success", false);
            res.put("data", e.getMessage());
        }
        return res;
    }
}
