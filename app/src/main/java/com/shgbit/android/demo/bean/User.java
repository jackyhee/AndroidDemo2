package com.shgbit.android.demo.bean;

public class User {
    private String userName;
    private String displayName;
    private String status = "";
    private SessionType sessionType;
    private String telephone;
    private String mqclient;

    public User() {
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public SessionType getSessionType() {
        return this.sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMqclient() {
        return this.mqclient;
    }

    public void setMqclient(String mqclient) {
        this.mqclient = mqclient;
    }
}
