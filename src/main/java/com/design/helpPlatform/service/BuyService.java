package com.design.helpPlatform.service;

import com.design.helpPlatform.entity.BuyInfoEntity;

import javax.servlet.http.HttpSession;

public interface BuyService {


    boolean addOrderNow(BuyInfoEntity buyInfoEntity, HttpSession session);
}
