package cn.yat.entity;

import java.util.Date;

public class Testcase {
    private Integer id;

    private Integer teamId;

    private Integer status;

    private Integer serviceId;

    private Integer testEnvId;

    private String hostParam;

    private String method;

    private Boolean isPost;

    private String url;

    private String parameters;

    private String note;

    private String preOpsIds;

    private String afterTestOpsIds;

    private String postOpsIds;

    private String httpCodeCheck;

    private String containCheck;

    private String notContainCheck;

    private String jsonCheck;

    private String dbCheck;

    private Date addTime;

    private Integer addUserId;

    private Date updateTime;

    private Integer updateUserId;

    private String cookieList;

    private String headerList;

    private String getHttpResList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getTestEnvId() {
        return testEnvId;
    }

    public void setTestEnvId(Integer testEnvId) {
        this.testEnvId = testEnvId;
    }

    public String getHostParam() {
        return hostParam;
    }

    public void setHostParam(String hostParam) {
        this.hostParam = hostParam;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getIsPost() {
        return isPost;
    }

    public void setIsPost(Boolean isPost) {
        this.isPost = isPost;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPreOpsIds() {
        return preOpsIds;
    }

    public void setPreOpsIds(String preOpsIds) {
        this.preOpsIds = preOpsIds;
    }

    public String getAfterTestOpsIds() {
        return afterTestOpsIds;
    }

    public void setAfterTestOpsIds(String afterTestOpsIds) {
        this.afterTestOpsIds = afterTestOpsIds;
    }

    public String getPostOpsIds() {
        return postOpsIds;
    }

    public void setPostOpsIds(String postOpsIds) {
        this.postOpsIds = postOpsIds;
    }

    public String getHttpCodeCheck() {
        return httpCodeCheck;
    }

    public void setHttpCodeCheck(String httpCodeCheck) {
        this.httpCodeCheck = httpCodeCheck;
    }

    public String getContainCheck() {
        return containCheck;
    }

    public void setContainCheck(String containCheck) {
        this.containCheck = containCheck;
    }

    public String getNotContainCheck() {
        return notContainCheck;
    }

    public void setNotContainCheck(String notContainCheck) {
        this.notContainCheck = notContainCheck;
    }

    public String getJsonCheck() {
        return jsonCheck;
    }

    public void setJsonCheck(String jsonCheck) {
        this.jsonCheck = jsonCheck;
    }

    public String getDbCheck() {
        return dbCheck;
    }

    public void setDbCheck(String dbCheck) {
        this.dbCheck = dbCheck;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getCookieList() {
        return cookieList;
    }

    public void setCookieList(String cookieList) {
        this.cookieList = cookieList;
    }

    public String getHeaderList() {
        return headerList;
    }

    public void setHeaderList(String headerList) {
        this.headerList = headerList;
    }

    public String getGetHttpResList() {
        return getHttpResList;
    }

    public void setGetHttpResList(String getHttpResList) {
        this.getHttpResList = getHttpResList;
    }
}