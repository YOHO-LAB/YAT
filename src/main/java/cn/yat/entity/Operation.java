package cn.yat.entity;

import java.util.Date;

public class Operation {
    private Integer id;

    private Integer envId;

    private String name;

    private String note;

    private Integer opsType;

    private Integer addUserId;

    private Date addTime;

    private Integer updateUserId;

    private Date updateTime;

    private Integer dbId;

    private String dbSql;

    private Boolean httpIsPost;

    private String httpUrl;

    private String httpParam;

    private Integer tcId;

    private String tcValList;

    private String javaCode;

    private Integer waitTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnvId() {
        return envId;
    }

    public void setEnvId(Integer envId) {
        this.envId = envId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getOpsType() {
        return opsType;
    }

    public void setOpsType(Integer opsType) {
        this.opsType = opsType;
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDbId() {
        return dbId;
    }

    public void setDbId(Integer dbId) {
        this.dbId = dbId;
    }

    public String getDbSql() {
        return dbSql;
    }

    public void setDbSql(String dbSql) {
        this.dbSql = dbSql;
    }

    public Boolean getHttpIsPost() {
        return httpIsPost;
    }

    public void setHttpIsPost(Boolean httpIsPost) {
        this.httpIsPost = httpIsPost;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getHttpParam() {
        return httpParam;
    }

    public void setHttpParam(String httpParam) {
        this.httpParam = httpParam;
    }

    public Integer getTcId() {
        return tcId;
    }

    public void setTcId(Integer tcId) {
        this.tcId = tcId;
    }

    public String getTcValList() {
        return tcValList;
    }

    public void setTcValList(String tcValList) {
        this.tcValList = tcValList;
    }

    public String getJavaCode() {
        return javaCode;
    }

    public void setJavaCode(String javaCode) {
        this.javaCode = javaCode;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }
}