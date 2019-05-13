package com.design.helpPlatform.controller;

import com.design.helpPlatform.entity.OrderDetailEntity;
import com.design.helpPlatform.entity.OrderEntity;
import com.design.helpPlatform.entity.ResponseMap;
import com.design.helpPlatform.entity.User;
import com.design.helpPlatform.service.DeliveryService;
import com.design.helpPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/myOrder")
public class OrderController {
    @Autowired
    DeliveryService deliveryService;
    @Autowired
    UserService userService;

    @RequestMapping("/list")
    public String orderList(){
        return "myOrder";
    }

    @RequestMapping("/orderList")
    @ResponseBody
    public ResponseMap getAllMyOrder(HttpSession session){
        Integer userId = (Integer)session.getAttribute("userId");
        List<OrderEntity> orderEntityList = deliveryService.getOrderEntity(userId);
        return new ResponseMap(true,orderEntityList,"success");
    }

    @RequestMapping("/allOrderList")
    @ResponseBody
    public ResponseMap getAllOrder(){
        List<OrderEntity> orderEntityList = deliveryService.getWaitOrderEntity();
        return new ResponseMap(true,orderEntityList,"success");
    }

    @RequestMapping("/getOrder")
    public String getOrder(HttpSession session, Model model){
        //查询当前骑手是否有未完成单
        Integer userId = (Integer)session.getAttribute("userId");
        OrderEntity orderEntity = deliveryService.getOrderByRider(userId);
        if(orderEntity==null){
            return "getOrder";
        }else{
            //返回起始点位置信息
            model.addAttribute("orderInfo",orderEntity);
            return "nav";
        }
    }

    @RequestMapping("/sendArrived")
    public String arrived(String orderNo){
        //更新订单状态
        int i = deliveryService.updateOrderFinish(orderNo);
        if(i>0){
            return "getOrder";
        }else{
            return "error";
        }
    }


    @RequestMapping("/showOrderDetail")
    @ResponseBody
    public ResponseMap showOrderDetail(String orderNo){
        try {
            OrderEntity orderEntity = deliveryService.getOrderByOrderNo(orderNo);
            List<OrderDetailEntity> orderDetailEntity = deliveryService.getOrderDetailbyOrderNo(orderNo);
            User user = userService.getUserById(orderEntity.getOrderRiderId());
            orderEntity.setOrderDetailList(orderDetailEntity);
            orderEntity.setUser(user);
            return new ResponseMap(true,orderEntity,"查询成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMap(false,"false","false");

        }
    }

}
