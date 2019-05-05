package com.design.helpPlatform.controller;

import com.design.helpPlatform.entity.OrderInfo;
import com.design.helpPlatform.entity.ResponseMap;
import com.design.helpPlatform.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 同城送取
 */
@Controller
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @RequestMapping("/main")
    public String deliveryMainView(){
        return "delivery";
    }

    @RequestMapping("/orderNow")
    @ResponseBody
    public ResponseMap orderNow(OrderInfo orderInfo, HttpSession session){
        int i = deliveryService.saveOrder(orderInfo, session);
        if(i>0){
            return new ResponseMap(true,"success","success");
        }else{
            return new ResponseMap(false,"false","success");

        }

    }
}
