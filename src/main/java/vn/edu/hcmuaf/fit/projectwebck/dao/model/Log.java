package vn.edu.hcmuaf.fit.projectwebck.dao.model;

import java.time.LocalDateTime;

public class Log {
    private Integer logId;
    private String label;
    private Integer userId;
    private String time;
    private String location;
    private String beforeData;
    private String afterData;

    public Log() {
    }

    public Log(String label, Integer userId, String time, String location, String beforeData, String afterData, Integer logId) {
        this.label = label;
        this.userId = userId;
        this.time = time;
        this.location = location;
        this.beforeData = beforeData;
        this.afterData = afterData;
        this.logId = logId;
    }

    public Log(String label, Integer userId, String time, String location, String beforeData, String afterData) {
        this.label = label;
        this.userId = userId;
        this.time = time;
        this.location = location;
        this.beforeData = beforeData;
        this.afterData = afterData;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBeforeData() {
        return beforeData;
    }

    public void setBeforeData(String beforeData) {
        this.beforeData = beforeData;
    }

    public String getAfterData() {
        return afterData;
    }

    public void setAfterData(String afterData) {
        this.afterData = afterData;
    }
}
