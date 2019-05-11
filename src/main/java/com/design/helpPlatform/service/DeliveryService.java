package com.design.helpPlatform.service;

import com.design.helpPlatform.entity.OrderEntity;
import com.design.helpPlatform.entity.OrderInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface DeliveryService {

    int orderNow(OrderInfo orderInfo, HttpSession session);

    List<OrderEntity> getOrderEntity(Integer userId);

    List<OrderEntity> getWaitOrderEntity();

    OrderEntity getOrderByRider(Integer userId);

    OrderEntity getOrderByOrderNo(String orderNo);

    int updateOrderToSending(String orderNo, Integer userId);

    int updateOrderFinish(String orderNo);
}
