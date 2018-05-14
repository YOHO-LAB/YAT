package cn.yat.service;


import cn.yat.entity.ServiceExample;
import cn.yat.entity.Testcase;
import cn.yat.mapper.ServiceMapper;
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
public class ServiceService {
    @Autowired
    private ServiceMapper serviceMapper;
    @Autowired
    private TestcaseService ts;
    @Autowired
    private UserService us;
    @Autowired
    private RecordUtil ru;

    public void addService(JSONObject res , String userId , String serviceName , String serviceNote,String prjId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int prjIdInt = Integer.parseInt(prjId);
        cn.yat.entity.Service oService = new cn.yat.entity.Service();
        oService.setName(serviceName);
        oService.setNote(serviceNote);
        oService.setProjectId(prjIdInt);
        Date now = new Date();
        oService.setAddTime(now);
        oService.setAddUserId(userIdInt);
        oService.setUpdateTime(now);
        oService.setUpdateUserId(userIdInt);
        int ist = serviceMapper.insertSelective(oService);
        if(ist == 1){
            ServiceExample example = new ServiceExample();
            ServiceExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(serviceName);
            criteria.andProjectIdEqualTo(prjIdInt);
            List<cn.yat.entity.Service> teamList = serviceMapper.selectByExample(example);
            ru.addRecord(userIdInt,"addService",teamList.get(0).getId()+"");
            res.put("success", true);
            res.put("data", "insert success");
        }else{
            res.put("success", false);
            res.put("data", "insert failed");
        }
    }
    public void getService(JSONObject res , String search , String page , String count) throws Exception{
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
        int prjId = oSearch.getIntValue("prjId");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        ServiceExample example = new ServiceExample();
        ServiceExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(prjId);
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
        int totalCount = serviceMapper.countByExample(example);
        int totalPage = (int)Math.ceil(totalCount/Double.parseDouble(count));
        totalPage = totalPage==0?1:totalPage;
        example.setOrderByClause("id limit "+(pageInt-1)*countInt+","+countInt);
        List<cn.yat.entity.Service> serviceList = serviceMapper.selectByExample(example);
        JSONArray arr = new JSONArray();
        if(serviceList.size() > 0){
            for(cn.yat.entity.Service serv : serviceList){
                JSONObject obj = (JSONObject)JSONObject.toJSON(serv);
                obj.put("addUserNameCn",us.getUserNameCnById(serv.getAddUserId()));
                obj.put("updateUserNameCn",us.getUserNameCnById(serv.getUpdateUserId()));
                arr.add(obj);
            }
        }
        res.put("success", true);
        res.put("data", arr);
        res.put("totalCount", totalCount);
        res.put("totalPage", totalPage);
    }

    public void modifyService(JSONObject res , String userId , String teamName , String teamNote , String serviceId,String prjId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int serviceIdInt = Integer.parseInt(serviceId);
        int prjIdInt = Integer.parseInt(prjId);
        cn.yat.entity.Service oService= serviceMapper.selectByPrimaryKey(serviceIdInt);
        if(oService == null){
            throw new Exception("serviceId:"+serviceId+"不存在！");
        }
        oService.setName(teamName);
        oService.setNote(teamNote);
        oService.setProjectId(prjIdInt);
        oService.setUpdateUserId(userIdInt);
        oService.setUpdateTime(new Date());
        int ist = serviceMapper.updateByPrimaryKeySelective(oService);
        if(ist == 1){
            ru.addRecord(userIdInt,"modifyService",serviceId);
            res.put("success", true);
            res.put("data", oService);
        }else{
            res.put("success", false);
            res.put("data", "insert failed");
        }
    }
    public void deleteService(JSONObject res , String userId, String teamId) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int teamIdInt = Integer.parseInt(teamId);
        int del = serviceMapper.deleteByPrimaryKey(teamIdInt);
        if(del == 1){
            ru.addRecord(userIdInt,"deleteService",teamId);
            res.put("success", true);
            res.put("data", "delete success");
        }else{
            res.put("success", false);
            res.put("data", "delete failed");
        }
    }
    public void getAllService(JSONObject res) throws Exception{
        ServiceExample example = new ServiceExample();
        ServiceExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        List<cn.yat.entity.Service> teamList = serviceMapper.selectByExample(example);
        res.put("success", true);
        res.put("data", teamList);
    }
    public void getAllServiceByPrjId(JSONObject res , String projectId) throws Exception{
        int projectIdInt = Integer.parseInt(projectId);
        List<cn.yat.entity.Service> teamList = getAllServiceByPrjId(projectIdInt);
        res.put("success", true);
        res.put("data", teamList);
    }
    public List<cn.yat.entity.Service> getAllServiceByPrjId(int projectId) throws Exception{
        ServiceExample example = new ServiceExample();
        ServiceExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        return serviceMapper.selectByExample(example);
    }
    public List<cn.yat.entity.Service> getAllService() throws Exception{
        ServiceExample example = new ServiceExample();
        ServiceExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return serviceMapper.selectByExample(example);
    }
    public String getNameById(int id) throws Exception{
        String res = "";
        if(id>0){
            cn.yat.entity.Service oService =serviceMapper.selectByPrimaryKey(id);
            res = oService.getName();
        }
        return res;
    }
    public String getNameByCaseId(int caseId) throws Exception{
        String res = "";
        if(caseId > 0){
            Testcase tc = ts.getById(caseId);
            cn.yat.entity.Service oService =serviceMapper.selectByPrimaryKey(tc.getServiceId());
            res = oService.getName();
        }
        return res;
    }
}
