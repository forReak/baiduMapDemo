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

    /**
     * 骑手点击接单后的后台操作
     * @param session 缓存
     * @param model model
     *              包含了页面跳转信息和向前台发送的参数。
     * @return 当return 一个string的时候，就代表返回一个页面的名称。springMVC的
     * 底层实现中，回自动将入参参数中的model放到页面中。
     */
    @RequestMapping("/getOrder")
    public String getOrder(HttpSession session, Model model){
        //获取缓存中当前登陆人的id
        Integer userId = (Integer)session.getAttribute("userId");
        //根据当前登陆人的id，去数据库查询当前骑手是否有未完成单
        OrderEntity orderEntity = deliveryService.getOrderByRider(userId);
        //查询结束。获取到订单信息：orderEntity
        //如果订单信息是空的，则向前台返回接单页面
        if(orderEntity==null){
            return "getOrder";
        }else{
            //否则，向前台返回订单信息，及跳转到导航页面。
            //返回起始点位置信息
            //根据key-value去向model中放入前台所需要的参数。
            model.addAttribute("orderInfo",orderEntity);
            /*model.addAttribute("name",orderEntity);
            model.addAttribute("age",orderEntity);*/
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
