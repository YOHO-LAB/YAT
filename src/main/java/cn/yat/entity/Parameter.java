package cn.yat.entity;

import java.util.Date;

public class Parameter {
    private Integer id;

    private Integer envId;

    private String name;

    private String note;

    private Integer paramType;

    private Integer addUserId;

    private Date addTime;

    private Integer updateUserId;

    private Date updateTime;

    private String kvVal;

    private Integer dbId;

    private String dbSql;

    private String dbColumn;

    private Integer dbGetValType;

    private Integer tcId;

    private Integer tcGetValType;

    private String tcJsonPath;

    private Integer tcJsonMatchNum;

    private String tcLeft;

    private String tcRight;

    private Integer tcLrMatchNum;

    private String tcCookie;

    private String tcHeader;

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

    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
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

    public String getKvVal() {
        return kvVal;
    }

    public void setKvVal(String kvVal) {
        this.kvVal = kvVal;
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

    public String getDbColumn() {
        return dbColumn;
    }

    public void setDbColumn(String dbColumn) {
        this.dbColumn = dbColumn;
    }

    public Integer getDbGetValType() {
        return dbGetValType;
    }

    public void setDbGetValType(Integer dbGetValType) {
        this.dbGetValType = dbGetValType;
    }

    public Integer getTcId() {
        return tcId;
    }

    public void setTcId(Integer tcId) {
        this.tcId = tcId;
    }

    public Integer getTcGetValType() {
        return tcGetValType;
    }

    public void setTcGetValType(Integer tcGetValType) {
        this.tcGetValType = tcGetValType;
    }

    public String getTcJsonPath() {
        return tcJsonPath;
    }

    public void setTcJsonPath(String tcJsonPath) {
        this.tcJsonPath = tcJsonPath;
    }

    public Integer getTcJsonMatchNum() {
        return tcJsonMatchNum;
    }

    public void setTcJsonMatchNum(Integer tcJsonMatchNum) {
        this.tcJsonMatchNum = tcJsonMatchNum;
    }

    public String getTcLeft() {
        return tcLeft;
    }

    public void setTcLeft(String tcLeft) {
        this.tcLeft = tcLeft;
    }

    public String getTcRight() {
        return tcRight;
    }

    public void setTcRight(String tcRight) {
        this.tcRight = tcRight;
    }

    public Integer getTcLrMatchNum() {
        return tcLrMatchNum;
    }

    public void setTcLrMatchNum(Integer tcLrMatchNum) {
        this.tcLrMatchNum = tcLrMatchNum;
    }

    public String getTcCookie() {
        return tcCookie;
    }

    public void setTcCookie(String tcCookie) {
        this.tcCookie = tcCookie;
    }

    public String getTcHeader() {
        return tcHeader;
    }

    public void setTcHeader(String tcHeader) {
        this.tcHeader = tcHeader;
    }
}