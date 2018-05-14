package cn.yat.entity;

import java.util.Date;

public class Record {
    private Integer id;

    private Integer userId;

    private String operation;

    private String operateData;

    private Date operateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperateData() {
        return operateData;
    }

    public void setOperateData(String operateData) {
        this.operateData = operateData;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}