package com.design.helpPlatform.service;

import com.design.helpPlatform.entity.BuyInfoEntity;

import javax.servlet.http.HttpSession;

public interface BuyService {


    boolean orderNow(BuyInfoEntity buyInfoEntity, HttpSession session);
}
