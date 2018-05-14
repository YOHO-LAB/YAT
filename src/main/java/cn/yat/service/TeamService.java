package cn.yat.service;

import cn.yat.entity.Team;
import cn.yat.entity.TeamExample;
import cn.yat.mapper.TeamMapper;
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
public class TeamService {
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private UserService us;
    @Autowired
    private RecordUtil ru;

    public void addTeam(JSONObject res , String userId , String teamName , String teamNote) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        Team oTeam = new Team();
        oTeam.setName(teamName);
        oTeam.setNote(teamNote);
        Date now = new Date();
        oTeam.setAddTime(now);
        oTeam.setAddUserId(userIdInt);
        oTeam.setUpdateTime(now);
        oTeam.setUpdateUserId(userIdInt);
        int ist = teamMapper.insertSelective(oTeam);
        if(ist == 1){
            TeamExample example = new TeamExample();
            TeamExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(teamName);
            List<Team> teamList = teamMapper.selectByExample(example);
            ru.addRecord(userIdInt,"addTeam",teamList.get(0).getId()+"");
            res.put("success", true);
            res.put("data", "insert success");
        }else{
            res.put("success", false);
            res.put("data", "insert failed");
        }
    }
    public void getTeam(JSONObject res , String search , String page , String count) throws Exception{
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
        TeamExample example = new TeamExample();
        TeamExample.Criteria criteria = example.createCriteria();
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
        int totalCount = teamMapper.countByExample(example);
        int totalPage = (int)Math.ceil(totalCount/Double.parseDouble(count));
        totalPage = totalPage==0?1:totalPage;
        example.setOrderByClause("id limit "+(pageInt-1)*countInt+","+countInt);
        List<Team> teamList = teamMapper.selectByExample(example);
        JSONArray arr = new JSONArray();
        if(teamList.size() > 0){
            for(Team team : teamList){
                JSONObject obj = (JSONObject)JSONObject.toJSON(team);
                obj.put("addUserNameCn",us.getUserNameCnById(team.getAddUserId()));
                obj.put("updateUserNameCn",us.getUserNameCnById(team.getUpdateUserId()));
                arr.add(obj);
            }
        }
        res.put("success", true);
        res.put("data", arr);
        res.put("totalCount", totalCount);
        res.put("totalPage", totalPage);
    }

    public void modifyTeam(JSONObject res , String userId , String teamName , String teamNote , String teamId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int teamIdInt = Integer.parseInt(teamId);
        TeamExample example = new TeamExample();
        TeamExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(teamIdInt);
        List<Team> teamList = teamMapper.selectByExample(example);
        if(teamList.size() != 1){
            throw new Exception("teamId:"+teamId+"不存在！");
        }
        Team oTeam= teamList.get(0);
        oTeam.setName(teamName);
        oTeam.setNote(teamNote);
        oTeam.setUpdateUserId(userIdInt);
        oTeam.setUpdateTime(new Date());
        int ist = teamMapper.updateByPrimaryKeySelective(oTeam);
        if(ist == 1){
            ru.addRecord(userIdInt,"modifyTeam",teamId);
            res.put("success", true);
            res.put("data", oTeam);
        }else{
            res.put("success", false);
            res.put("data", "insert failed");
        }
    }
    public void deleteTeam(JSONObject res , String userId, String teamId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int teamIdInt = Integer.parseInt(teamId);
        int del = teamMapper.deleteByPrimaryKey(teamIdInt);
        if(del == 1){
            ru.addRecord(userIdInt,"deleteTeam",teamId);
            res.put("success", true);
            res.put("data", "delete success");
        }else{
            res.put("success", false);
            res.put("data", "delete failed");
        }
    }
    public void getAllTeam(JSONObject res) throws Exception{
        TeamExample example = new TeamExample();
        TeamExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        List<Team> teamList = teamMapper.selectByExample(example);
        res.put("success", true);
        res.put("data", teamList);
    }
    public List<Team> getAllTeam() throws Exception{
        TeamExample example = new TeamExample();
        TeamExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        List<Team> teamList = teamMapper.selectByExample(example);
        return teamList;
    }
    public String getNameById(int id) throws Exception{
        String res = "";
        if(id>0){
            Team oTeam =teamMapper.selectByPrimaryKey(id);
            res = oTeam.getName();
        }
        return res;
    }
}
