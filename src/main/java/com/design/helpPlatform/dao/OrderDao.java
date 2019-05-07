package com.design.helpPlatform.dao;

import com.design.helpPlatform.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface OrderDao {

    int insertOrderDao(OrderEntity orderEntity);

    List<OrderEntity> getOrderEntityByUserId(Integer userId);

    List<OrderEntity> getAllWaitOrderEntity();

    OrderEntity getOrderEntityByRider(Integer userId);

    OrderEntity getOrderEntityByOrderNo(String orderNo);

    int updateOrderToSending(Map<String,Object> map);

    int updateOrderFinish(Map map);
}
