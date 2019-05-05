package com.design.helpPlatform.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class User {

    private Integer id;

    private String userName;

    private String password;

    private String phoneNum;

    private String nickName;

    private String submitAddr;

    private Timestamp birthDay;

    private String sex;

    private Integer rider;

    public Integer getRider() {
        return rider;
    }

    public void setRider(Integer rider) {
        this.rider = rider;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getSubmitAddr() {
        return submitAddr;
    }

    public void setSubmitAddr(String submitAddr) {
        this.submitAddr = submitAddr;
    }

    public Timestamp getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Timestamp birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
