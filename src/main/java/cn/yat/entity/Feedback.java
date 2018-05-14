package cn.yat.entity;

import java.util.Date;

public class Feedback {
    private Integer id;

    private Integer userId;

    private Integer feedbackType;

    private String feedbackData;

    private Date addTime;

    private Boolean isResolved;

    private Date resolveTime;

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

    public Integer getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(Integer feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackData() {
        return feedbackData;
    }

    public void setFeedbackData(String feedbackData) {
        this.feedbackData = feedbackData;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Boolean getIsResolved() {
        return isResolved;
    }

    public void setIsResolved(Boolean isResolved) {
        this.isResolved = isResolved;
    }

    public Date getResolveTime() {
        return resolveTime;
    }

    public void setResolveTime(Date resolveTime) {
        this.resolveTime = resolveTime;
    }
}