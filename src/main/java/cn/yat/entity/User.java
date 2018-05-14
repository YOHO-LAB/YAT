package cn.yat.entity;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String usernameCn;

    private String email;

    private Integer department;

    private Integer groupName;

    private Date addTime;

    private Date loginTime;

    private String roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsernameCn() {
        return usernameCn;
    }

    public void setUsernameCn(String usernameCn) {
        this.usernameCn = usernameCn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getGroupName() {
        return groupName;
    }

    public void setGroupName(Integer groupName) {
        this.groupName = groupName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}