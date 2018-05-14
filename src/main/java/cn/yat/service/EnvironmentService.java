package cn.yat.service;

import cn.yat.entity.Environment;
import cn.yat.entity.EnvironmentExample;
import cn.yat.entity.Testcase;
import cn.yat.mapper.EnvironmentMapper;
import cn.yat.util.RecordUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by rong.gao on 2018/3/6.
 */
@Service
@Transactional
public class EnvironmentService {
    @Autowired
    private EnvironmentMapper environmentMapper;
    @Autowired
    private TestcaseService ts;
    @Autowired
    private ProjectService ps;
    @Autowired
    private UserService us;
    @Autowired
    private RecordUtil ru;

    public void addEnvironment(JSONObject res , String userId , String teamName , String teamNote,String hostUrl,String prjId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int prjIdInt = Integer.parseInt(prjId);
        Environment oEnvironment = new Environment();
        oEnvironment.setName(teamName);
        hostUrl = editHostUrl(hostUrl);
        oEnvironment.setHostUrl(hostUrl);
        oEnvironment.setNote(teamNote);
        oEnvironment.setProjectId(prjIdInt);
        Date now = new Date();
        oEnvironment.setAddTime(now);
        oEnvironment.setAddUserId(userIdInt);
        oEnvironment.setUpdateTime(now);
        oEnvironment.setUpdateUserId(userIdInt);
        int ist = environmentMapper.insertSelective(oEnvironment);
        if(ist == 1){
            EnvironmentExample example = new EnvironmentExample();
            EnvironmentExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(teamName);
            criteria.andProjectIdEqualTo(prjIdInt);
            List<Environment> teamList = environmentMapper.selectByExample(example);
            ru.addRecord(userIdInt,"addEnvironment",teamList.get(0).getId()+"");
            res.put("success", true);
            res.put("data", "insert success");
        }else{
            res.put("success", false);
            res.put("data", "insert failed");
        }
    }
    private String editHostUrl(String hosturl) throws Exception{
        hosturl = hosturl.trim();
        int l = hosturl.length();
        for(int i=l-1;i>=0;i--){
            char c = hosturl.charAt(i);
            if(c=='/' || c=='\\'){
                hosturl = hosturl.substring(0,i);
            }else{
                break;
            }
        }
        return hosturl;
    }
    public void getEnvironment(JSONObject res , String search , String page , String count) throws Exception{
        int pageInt = Integer.parseInt(page);
        int countInt = Integer.parseInt(count);
        JSONObject oSearch = JSONObject.parseObject(search);
        //{"s_team_name":"","s_team_note":"","s_create_user":"","s_update_user":"","s_create_time":"","s_update_time":""}
        String s_team_name = oSearch.getString("s_team_name").trim();
        String s_host_url = oSearch.getString("s_host_url").trim();
        String s_team_note = oSearch.getString("s_team_note").trim();
        String s_create_user = oSearch.getString("s_create_user").trim();
        String s_update_user = oSearch.getString("s_update_user").trim();
        String s_create_time = oSearch.getString("s_create_time").trim();
        String s_update_time = oSearch.getString("s_update_time").trim();
        int prjId = oSearch.getIntValue("prjId");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        EnvironmentExample example = new EnvironmentExample();
        EnvironmentExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(prjId);
        if(!s_team_name.equals("")){
            criteria.andNameLike("%"+s_team_name+"%");
        }
        if(!s_host_url.equals("")){
            criteria.andHostUrlLike("%"+s_host_url+"%");
        }
        if(!s_team_note.equals("")){
            criteria.andNoteLike("%"+s_team_note+"%");
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
        int totalCount = environmentMapper.countByExample(example);
        int totalPage = (int)Math.ceil(totalCount/Double.parseDouble(count));
        totalPage = totalPage==0?1:totalPage;
        example.setOrderByClause("id limit "+(pageInt-1)*countInt+","+countInt);
        List<Environment> envList = environmentMapper.selectByExample(example);
        JSONArray arr = new JSONArray();
        if(envList.size() > 0){
            for(Environment env : envList){
                JSONObject obj = (JSONObject)JSONObject.toJSON(env);
                obj.put("addUserNameCn",us.getUserNameCnById(env.getAddUserId()));
                obj.put("updateUserNameCn",us.getUserNameCnById(env.getUpdateUserId()));
                arr.add(obj);
            }
        }
        res.put("success", true);
        res.put("data", arr);
        res.put("totalCount", totalCount);
        res.put("totalPage", totalPage);
    }

    public void modifyEnvironment(JSONObject res , String userId , String envName , String envNote , String envId,String hostUrl,String prjId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int envIdInt = Integer.parseInt(envId);
        int prjIdInt = Integer.parseInt(prjId);
        Environment oEnvironment= environmentMapper.selectByPrimaryKey(envIdInt);
        if(oEnvironment == null){
            throw new Exception("environmentId:"+envId+"不存在！");
        }
        oEnvironment.setName(envName);
        oEnvironment.setProjectId(prjIdInt);
        hostUrl = editHostUrl(hostUrl);
        oEnvironment.setHostUrl(hostUrl);
        oEnvironment.setNote(envNote);
        oEnvironment.setUpdateUserId(userIdInt);
        oEnvironment.setUpdateTime(new Date());
        int ist = environmentMapper.updateByPrimaryKeySelective(oEnvironment);
        if(ist == 1){
            ru.addRecord(userIdInt,"modifyEnvironment",envId);
            res.put("success", true);
            res.put("data", oEnvironment);
        }else{
            res.put("success", false);
            res.put("data", "insert failed");
        }
    }
    public void deleteEnvironment(JSONObject res , String userId, String teamId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int teamIdInt = Integer.parseInt(teamId);
        int del = environmentMapper.deleteByPrimaryKey(teamIdInt);
        if(del == 1){
            ru.addRecord(userIdInt,"deleteEnvironment",teamId);
            res.put("success", true);
            res.put("data", "delete success");
        }else{
            res.put("success", false);
            res.put("data", "delete failed");
        }
    }
    public void getAllEnvironment(JSONObject res) throws Exception{
        EnvironmentExample example = new EnvironmentExample();
        EnvironmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        List<Environment> envList = environmentMapper.selectByExample(example);
        JSONArray arr = new JSONArray();
        for(Environment env : envList){
            JSONObject object =(JSONObject) JSONObject.toJSON(env);
            object.put("projectName",ps.getNameById(env.getProjectId()));
            arr.add(object);
        }
        res.put("success", true);
        res.put("data", arr);
    }
    public List<Environment> getAllEnvironment() throws Exception{
        EnvironmentExample example = new EnvironmentExample();
        EnvironmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        List<Environment> envList = environmentMapper.selectByExample(example);
        return envList;
    }
    public List<Environment> getAllEnvironmentByPrjId(int prjId) throws Exception{
        EnvironmentExample example = new EnvironmentExample();
        EnvironmentExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(prjId);
        List<Environment> envList = environmentMapper.selectByExample(example);
        return envList;
    }
    public String getNameById(int id) throws Exception{
        String res = "";
        if(id>0){
            Environment oEnvironment =environmentMapper.selectByPrimaryKey(id);
            res = oEnvironment.getName();
        }
        return res;
    }
    public String getNameByCaseId(int caseId) throws Exception{
        String res = "";
        if(caseId>0){
            Testcase tc = ts.getById(caseId);
            Environment oEnvironment =environmentMapper.selectByPrimaryKey(tc.getTestEnvId());
            res = oEnvironment.getName();
        }
        return res;
    }
    public Environment getById(int id) throws Exception{
        Environment oEnvironment = null;
        if(id>0){
            oEnvironment =environmentMapper.selectByPrimaryKey(id);
        }
        return oEnvironment;
    }
    public void getEnvironmentByProjectId(JSONObject res,String projectId) throws Exception{
        int projectIdInt = Integer.parseInt(projectId);
        EnvironmentExample example = new EnvironmentExample();
        EnvironmentExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectIdInt);
        List list = environmentMapper.selectByExample(example);
        res.put("success", true);
        res.put("data", list);
    }
    public void getDestinationEnvironment(JSONObject res,String prjId,String envId) throws Exception{
        int prjIdInt = Integer.parseInt(prjId);
        int envIdInt = Integer.parseInt(envId);
        EnvironmentExample example = new EnvironmentExample();
        EnvironmentExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(prjIdInt);
        criteria.andIdNotEqualTo(envIdInt);
        List list = environmentMapper.selectByExample(example);
        res.put("success", true);
        res.put("data", list);
    }
    public void getEnvironmentByEnvId(JSONObject res,String envId) throws Exception{
        int envIdInt = Integer.parseInt(envId);
        Environment e = environmentMapper.selectByPrimaryKey(envIdInt);
        if(e != null){
            res.put("success", true);
            res.put("data", e);
        }else{
            res.put("success", false);
            res.put("data", "环境不存在，id="+envId);
        }
    }
}
