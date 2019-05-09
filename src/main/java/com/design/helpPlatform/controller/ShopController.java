package com.design.helpPlatform.controller;

import com.design.helpPlatform.entity.ResponseMap;
import com.design.helpPlatform.entity.ShopEntity;
import com.design.helpPlatform.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ShopController {

    @Autowired
    ShopService shopService;

    @RequestMapping("/getShopInfo")
    @ResponseBody
    public ResponseMap getShopInfo(String shopInfo){
        //查询商铺信息
        System.out.println(shopInfo);
        ShopEntity shopByUrl = null;
        //根据url查找商户信息
        try {
            shopByUrl = shopService.getShopByUrl(shopInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMap(false,"false",e.getMessage());
        }

        return new ResponseMap(true,shopByUrl,"success");
    }
}
