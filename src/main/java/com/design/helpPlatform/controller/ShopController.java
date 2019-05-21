package com.design.helpPlatform.controller;

import com.design.helpPlatform.entity.GoodsEntity;
import com.design.helpPlatform.entity.ResponseMap;
import com.design.helpPlatform.entity.ShopEntity;
import com.design.helpPlatform.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.design.helpPlatform.util.Util.listIsEmpty;

/**
 * 如何添加商品信息：
 * 1。进入虎鲸云数据管理平台，点击我们的数据库，新增点
 * 2。将url和商家信息同步保存到本地数据库
 * 3。新增商家的类别信息
 * 4。新增商家的商品信息
 * 5。发布云端数据
 */
@Controller
public class ShopController {

    @Autowired
    ShopService shopService;

    @RequestMapping("/getShopInfo")
    @ResponseBody
    public ResponseMap getShopInfo(String shopInfo, HttpSession session){
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
        session.setAttribute("buyCar","");
        return new ResponseMap(true,shopByUrl,"success");
    }


    @RequestMapping("/getGoodsInfoByType")
    @ResponseBody
    public ResponseMap getGoodsInfoByTypeAction(String typeId){
        System.out.println("------------------商店类型id是："+typeId);
        List<GoodsEntity> goodsEntityList;
        try {
            goodsEntityList = shopService.getShopGoodsByType(typeId);
            if(!listIsEmpty(goodsEntityList)){
                return new ResponseMap(true,goodsEntityList,"success");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMap(false,"false",e.getMessage());

        }
        return new ResponseMap(true,null,"success");

    }
}
