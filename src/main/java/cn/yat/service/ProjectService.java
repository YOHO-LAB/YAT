package cn.yat.service;

import cn.yat.entity.Environment;
import cn.yat.entity.Project;
import cn.yat.entity.ProjectExample;
import cn.yat.entity.Testcase;
import cn.yat.mapper.ProjectMapper;
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
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserService us;
    @Autowired
    private TestcaseService ts;
    @Autowired
    private EnvironmentService es;
    @Autowired
    private RecordUtil ru;

    public void addProject(JSONObject res , String userId , String teamName , String teamNote) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        Project oProject = new Project();
        oProject.setName(teamName);
        oProject.setNote(teamNote);
        Date now = new Date();
        oProject.setAddTime(now);
        oProject.setAddUserId(userIdInt);
        oProject.setUpdateTime(now);
        oProject.setUpdateUserId(userIdInt);
        int ist = projectMapper.insertSelective(oProject);
        if(ist == 1){
            ProjectExample example = new ProjectExample();
            ProjectExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(teamName);
            List<Project> projectList = projectMapper.selectByExample(example);
            int prjId = projectList.get(0).getId();
            es.addEnvironment(new JSONObject(),userId,"空，请修改！","","http://xxx.xxx.xxx",prjId+"");
            ru.addRecord(userIdInt,"addTeam",prjId+"");
            res.put("success", true);
            res.put("data", "insert success");
        }else{
            res.put("success", false);
            res.put("data", "insert failed");
        }
    }
    public void getProject(JSONObject res , String search , String page , String count) throws Exception{
        int pageInt = Integer.parseInt(page);
        int countInt = Integer.parseInt(count);
        JSONObject oSearch = JSONObject.parseObject(search);
        //{"s_team_name":"","s_team_note":"","s_create_user":"","s_update_user":"","s_create_time":"","s_update_time":""}
        String s_team_name = oSearch.getString("s_team_name").trim();
        String s_team_note = oSearch.getString("s_team_note").trim();
        String s_create_user = oSearch.getString("s_create_user").trim();
        String s_update_user = oSearch.getString("s_update_user").trim();
        String s_create_time = oSearch.getString("s_create_time").trim();
        String s_update_time = oSearch.getString("s_update_time").trim();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if(!s_team_name.equals("")){
            criteria.andNameLike("%"+s_team_name+"%");
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
        int totalCount = projectMapper.countByExample(example);
        int totalPage = (int)Math.ceil(totalCount/Double.parseDouble(count));
        totalPage = totalPage==0?1:totalPage;
        example.setOrderByClause("id limit "+(pageInt-1)*countInt+","+countInt);
        List<Project> projectList = projectMapper.selectByExample(example);
        JSONArray arr = new JSONArray();
        if(projectList.size() > 0){
            for(Project project : projectList){
                JSONObject obj = (JSONObject)JSONObject.toJSON(project);
                obj.put("addUserNameCn",us.getUserNameCnById(project.getAddUserId()));
                obj.put("updateUserNameCn",us.getUserNameCnById(project.getUpdateUserId()));
                arr.add(obj);
            }
        }
        res.put("success", true);
        res.put("data", arr);
        res.put("totalCount", totalCount);
        res.put("totalPage", totalPage);
    }

    public void modifyProject(JSONObject res , String userId , String teamName , String teamNote , String teamId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int teamIdInt = Integer.parseInt(teamId);
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(teamIdInt);
        List<Project> projectList = projectMapper.selectByExample(example);
        if(projectList.size() != 1){
            throw new Exception("projectId:"+teamId+"不存在！");
        }
        Project oProject= projectList.get(0);
        oProject.setName(teamName);
        oProject.setNote(teamNote);
        oProject.setUpdateUserId(userIdInt);
        oProject.setUpdateTime(new Date());
        int ist = projectMapper.updateByPrimaryKeySelective(oProject);
        if(ist == 1){
            ru.addRecord(userIdInt,"modifyProject",teamId);
            res.put("success", true);
            res.put("data", oProject);
        }else{
            res.put("success", false);
            res.put("data", "modify failed");
        }
    }
    public void deleteProject(JSONObject res , String userId, String teamId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int teamIdInt = Integer.parseInt(teamId);
        int del = projectMapper.deleteByPrimaryKey(teamIdInt);
        if(del == 1){
            ru.addRecord(userIdInt,"deleteProject",teamId);
            res.put("success", true);
            res.put("data", "delete success");
        }else{
            res.put("success", false);
            res.put("data", "delete failed");
        }
    }
    public void getAllProject(JSONObject res) throws Exception{
        List<Project> projectList = getAllProject();
        res.put("success", true);
        res.put("data", projectList);
    }
    public String getNameByCaseId(int caseId) throws Exception{
        Testcase testcase = ts.getById(caseId);
        Environment environment = es.getById(testcase.getTestEnvId());
        Project project = projectMapper.selectByPrimaryKey(environment.getProjectId());
        return project.getName();
    }
    public List<Project> getAllProject() throws Exception{
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        List<Project> projectList = projectMapper.selectByExample(example);
        return projectList;
    }
    public Project getById(int id) throws Exception{
        return projectMapper.selectByPrimaryKey(id);
    }
    public String getNameById(int id) throws Exception{
        String res = "";
        if(id>0){
            Project oProject =projectMapper.selectByPrimaryKey(id);
            res = oProject.getName();
        }
        return res;
    }
}
