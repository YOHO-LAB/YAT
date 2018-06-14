package cn.yat.service;

import cn.yat.entity.Db;
import cn.yat.entity.DbExample;
import cn.yat.mapper.DbMapper;
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
public class DbService {
    @Autowired
    private DbMapper dbMapper;
    @Autowired
    private UserService us;
    @Autowired
    private RecordUtil ru;

    public void addDb(JSONObject res , String userId , String teamName , String teamNote, String db_ip,String db_port,String db_dbName,String db_user,String db_password,String envId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int db_port_int = Integer.parseInt(db_port);
        int envIdInt = Integer.parseInt(envId);
        Db oTeam = new Db();
        oTeam.setName(teamName);
        oTeam.setNote(teamNote);
        oTeam.setEnvId(envIdInt);
        oTeam.setIp(db_ip);
        oTeam.setPort(db_port_int);
        oTeam.setDbName(db_dbName);
        oTeam.setUserName(db_user);
        oTeam.setPassWord(db_password);
        Date now = new Date();
        oTeam.setAddTime(now);
        oTeam.setAddUserId(userIdInt);
        oTeam.setUpdateTime(now);
        oTeam.setUpdateUserId(userIdInt);
        int ist = dbMapper.insertSelective(oTeam);
        if(ist == 1){
            DbExample example = new DbExample();
            DbExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(teamName);
            criteria.andEnvIdEqualTo(envIdInt);
            List<Db> teamList = dbMapper.selectByExample(example);
            ru.addRecord(userIdInt,"addDb",teamList.get(0).getId()+"");
            res.put("success", true);
            res.put("data", "insert success");
        }else{
            res.put("success", false);
            res.put("data", "insert failed");
        }
    }
    public void getDb(JSONObject res , String search , String page , String count) throws Exception{
        int pageInt = Integer.parseInt(page);
        int countInt = Integer.parseInt(count);
        JSONObject oSearch = JSONObject.parseObject(search);
        //{"s_team_name":"","s_team_note":"","s_create_user":"","s_update_user":"","s_create_time":"","s_update_time":""}
        String s_team_name = oSearch.getString("s_team_name").trim();
        String s_team_note = oSearch.getString("s_team_note").trim();
        String s_db_ip = oSearch.getString("s_db_ip").trim();
        String s_db_port = oSearch.getString("s_db_port").trim();
        String s_db_name = oSearch.getString("s_db_name").trim();
        String s_create_user = oSearch.getString("s_create_user").trim();
        String s_update_user = oSearch.getString("s_update_user").trim();
        String s_create_time = oSearch.getString("s_create_time").trim();
        String s_update_time = oSearch.getString("s_update_time").trim();
        int envId = oSearch.getIntValue("envId");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DbExample example = new DbExample();
        DbExample.Criteria criteria = example.createCriteria();
        criteria.andEnvIdEqualTo(envId);
        if(!s_team_name.equals("")){
            criteria.andNameLike("%"+s_team_name+"%");
        }
        if(!s_team_note.equals("")){
            criteria.andNoteLike("%"+s_team_note+"%");
        }
        if(!s_db_ip.equals("")){
            criteria.andIpLike("%"+s_db_ip+"%");
        }
        if(!s_db_port.equals("")){
            criteria.andPortEqualTo(Integer.parseInt(s_db_port));
        }
        if(!s_db_name.equals("")){
            criteria.andDbNameLike("%"+s_db_name+"%");
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
        int totalCount = dbMapper.countByExample(example);
        int totalPage = (int)Math.ceil(totalCount/Double.parseDouble(count));
        totalPage = totalPage==0?1:totalPage;
        example.setOrderByClause("id limit "+(pageInt-1)*countInt+","+countInt);
        List<Db> teamList = dbMapper.selectByExample(example);
        JSONArray arr = new JSONArray();
        if(teamList.size() > 0){
            for(Db team : teamList){
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

    public void modifyDb(JSONObject res , String userId , String teamName , String teamNote , String teamId, String db_ip,String db_port,String db_dbName,String db_user,String db_password,String envId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int teamIdInt = Integer.parseInt(teamId);
        int db_port_int = Integer.parseInt(db_port);
        int envIdInt = Integer.parseInt(envId);
        DbExample example = new DbExample();
        DbExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(teamIdInt);
        List<Db> teamList = dbMapper.selectByExample(example);
        if(teamList.size() != 1){
            throw new Exception("teamId:"+teamId+"不存在！");
        }
        Db oTeam= teamList.get(0);
        oTeam.setName(teamName);
        oTeam.setNote(teamNote);
        oTeam.setEnvId(envIdInt);
        oTeam.setIp(db_ip);
        oTeam.setPort(db_port_int);
        oTeam.setDbName(db_dbName);
        oTeam.setUserName(db_user);
        oTeam.setPassWord(db_password);
        oTeam.setUpdateUserId(userIdInt);
        oTeam.setUpdateTime(new Date());
        int ist = dbMapper.updateByPrimaryKeySelective(oTeam);
        if(ist == 1){
            ru.addRecord(userIdInt,"modifyDb",teamId);
            res.put("success", true);
            res.put("data", oTeam);
        }else{
            res.put("success", false);
            res.put("data", "insert failed");
        }
    }
    public void deleteDb(JSONObject res , String userId, String teamId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int teamIdInt = Integer.parseInt(teamId);
        int del = dbMapper.deleteByPrimaryKey(teamIdInt);
        if(del == 1){
            ru.addRecord(userIdInt,"deleteDb",teamId);
            res.put("success", true);
            res.put("data", "delete success");
        }else{
            res.put("success", false);
            res.put("data", "delete failed");
        }
    }
    public void getAllDb(JSONObject res) throws Exception{
        DbExample example = new DbExample();
        DbExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        List<Db> teamList = dbMapper.selectByExample(example);
        res.put("success", true);
        res.put("data", teamList);
    }
    public void getAllDbByEnvId(JSONObject res , String envId) throws Exception{
        int envIdInt = Integer.parseInt(envId);
        DbExample example = new DbExample();
        DbExample.Criteria criteria = example.createCriteria();
        criteria.andEnvIdEqualTo(envIdInt);
        List<Db> teamList = dbMapper.selectByExample(example);
        res.put("success", true);
        res.put("data", teamList);
    }
    public List<Db> getDbByEnvId(int envId) throws Exception{
        DbExample example = new DbExample();
        DbExample.Criteria criteria = example.createCriteria();
        criteria.andEnvIdEqualTo(envId);
        return dbMapper.selectByExample(example);
    }
    public int addDb(Db db) throws Exception{
        int ist = dbMapper.insert(db);
        if(ist > 0){
            DbExample example = new DbExample();
            DbExample.Criteria criteria = example.createCriteria();
            criteria.andEnvIdEqualTo(db.getEnvId());
            criteria.andNameEqualTo(db.getName());
            List<Db> l = dbMapper.selectByExample(example);
            if(l.size() > 0){
                return l.get(0).getId();
            }
        }
        return 0;
    }
    public Db getDbById(int dbId) throws Exception{
        return dbMapper.selectByPrimaryKey(dbId);
    }
}
