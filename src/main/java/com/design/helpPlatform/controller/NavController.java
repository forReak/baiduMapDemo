package com.design.helpPlatform.controller;

import com.design.helpPlatform.entity.OrderEntity;
import com.design.helpPlatform.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class NavController {

    @Autowired
    DeliveryService deliveryService;

    @RequestMapping("/gotoNav")
    public String navView(HttpSession session, String orderNo, Model model){
        OrderEntity orderEntity = deliveryService.getOrderByOrderNo(orderNo);
        //更新订单状态
        Integer userId = (Integer)session.getAttribute("userId");
        model.addAttribute("orderInfo",orderEntity);
        deliveryService.updateOrderToSending(orderNo,userId);
        return "nav";
    }
}
