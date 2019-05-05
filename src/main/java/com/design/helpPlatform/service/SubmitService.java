package com.design.helpPlatform.service;

public interface SubmitService {

    /**
     * 用户注册
     * @param userName
     * @param password
     * @param phone
     * @param nickName
     * @param rider
     * @return
     */
    boolean submit(String userName, String password, String phone, String nickName, String rider);
}
