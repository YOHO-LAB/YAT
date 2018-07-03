package cn.yat.service;

import cn.yat.entity.Feedback;
import cn.yat.entity.User;
import cn.yat.entity.UserExample;
import cn.yat.ldap.LdapAuthService;
import cn.yat.ldap.LdapUser;
import cn.yat.mapper.FeedbackMapper;
import cn.yat.mapper.UserMapper;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private LdapAuthService ldap;

    public JSONObject get(HttpServletRequest request) {
        JSONObject res = new JSONObject();
        res.put("success", false);
        res.put("data", "N/A");
        try{
            String method = request.getParameter("method");
            if(method.equals("getAllProject")){
//                getAllProject(res);
            }
        }catch(Exception e){
            e.printStackTrace();
            res.put("success", false);
            res.put("data", e.getMessage());
        }
        return res;
    }

    public JSONObject post(HttpServletRequest request) {
        JSONObject res = new JSONObject();
        res.put("success", false);
        res.put("data", "N/A");
        try{
            String method = request.getParameter("method");
            if(method.equals("login")){
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                login(res, username , password);
            }
            if(method.equals("feedback")){
                String userId = request.getParameter("userId");
                String feedbackType = request.getParameter("feedbackType");
                String feedbackData = request.getParameter("feedbackData");
                feedback(res, userId , feedbackType,feedbackData);
            }
        }catch(Exception e){
            e.printStackTrace();
            res.put("success", false);
            res.put("data", e.getMessage());
        }
        return res;
    }

    public void login(JSONObject res , String username , String password) throws Exception{
        boolean isUserValid = ldap.login(username,password);
        if(!isUserValid){
            throw new Exception("用户名不存在 / 密码错误 , 请检查 !");
        }
        LdapUser oLdapUser = ldap.getUser(username);
        String userNameCnLdap = oLdapUser.getName();
        String userNameLdap = oLdapUser.getsAMAccountName();
        String emailLdap = oLdapUser.getMail();
        UserExample example = new UserExample();
        UserExample.Criteria criteria1 = example.createCriteria();
        criteria1.andEmailEqualTo(emailLdap);
        List<User> user = userMapper.selectByExample(example);
        if(user.size() > 1){
            throw new Exception("存在多个同名用户【"+emailLdap+"】,请联系管理员修改 !");
        }
        if(user.size() == 0){
            User oUser = new User();
            Date now = new Date();
            oUser.setAddTime(now);
            oUser.setLoginTime(now);
            oUser.setEmail(emailLdap);
            oUser.setPassword("");
            oUser.setRoleId("0");
            oUser.setUsername(userNameLdap);
            oUser.setUsernameCn(userNameCnLdap);
            int flag = userMapper.insertSelective(oUser);
            if(flag != 1){
                throw new Exception("新增用户失败 !");
            }else{
                res.put("success", true);
                res.put("data", oUser);
            }
        }
        UserExample example2 = new UserExample();
        UserExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andEmailEqualTo(emailLdap);
        List<User> user2 = userMapper.selectByExample(example2);
        if(user2.size() != 1){
            if(user2.size() < 1){
                throw new Exception("用户不存在!");
            }
            if(user2.size() > 1){
                throw new Exception("存在多个同名用户 ["+emailLdap+"],请联系管理员!");
            }
        }
        res.put("success", true);
        res.put("data", user2.get(0));
    }

    public void feedback(JSONObject res , String userId , String feedbackType , String feedbackData) throws Exception{
        int userIdInt = Integer.parseInt(userId);
        int feedbackTypeInt = Integer.parseInt(feedbackType);
        Feedback oFeedback = new Feedback();
        oFeedback.setUserId(userIdInt);
        oFeedback.setFeedbackType(feedbackTypeInt);
        oFeedback.setFeedbackData(feedbackData);
        oFeedback.setAddTime(new Date());
        int ist = feedbackMapper.insertSelective(oFeedback);
        if(ist == 1){
            res.put("success", true);
            res.put("data", "insert success");
        }else{
            res.put("success", false);
            res.put("data", "insert failed");
        }
    }

    public List<Integer> getUserIdByNameCn(String name) throws Exception{
        List<Integer> res = Lists.newArrayList();
        if(name.equals("YAT")){
            res.add(0);
            return res;
        }
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameCnEqualTo(name);
        List<User> list =userMapper.selectByExample(example);
        if(list.size()>0){
            for(User u : list){
                res.add(u.getId());
            }
        }
        return res;
    }

    public JSONObject getUserNameCnByIdList(List<Integer> idList) throws Exception{
        JSONObject res = new JSONObject();
        if(idList.size()>0){
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(idList);
            List<User> list =userMapper.selectByExample(example);
            for(User user : list){
                res.put(""+user.getId(),user.getUsernameCn());
            }
        }
        return res;
    }
    public String getUserNameCnById(int id) throws Exception{
        String res = "";
        if(id>0){
            User oUser = userMapper.selectByPrimaryKey(id);
            res = oUser.getUsernameCn();
        }
        if(id == 0){
            res = "YAT";
        }
        return res;
    }
    public User getUserById(int id) throws Exception{
        return userMapper.selectByPrimaryKey(id);
    }
}
