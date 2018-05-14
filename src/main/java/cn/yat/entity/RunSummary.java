package cn.yat.entity;

import java.util.Date;

public class RunSummary {
    private Integer id;

    private String name;

    private Integer userId;

    private Boolean isCi;

    private Date startTime;

    private Date endTime;

    private String log;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getIsCi() {
        return isCi;
    }

    public void setIsCi(Boolean isCi) {
        this.isCi = isCi;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}