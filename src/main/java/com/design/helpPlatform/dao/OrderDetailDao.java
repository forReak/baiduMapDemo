package com.design.helpPlatform.dao;

import com.design.helpPlatform.entity.OrderDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderDetailDao {

    int insert(OrderDetailEntity orderDetailEntity);

    int insertBeach(List<OrderDetailEntity> orderDetailEntities);

    List<OrderDetailEntity> orderEntity(String orderNo);

    List<OrderDetailEntity> getOrderDetailByOrderNo(String orderNo);
}
