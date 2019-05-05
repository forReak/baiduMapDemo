package com.design.helpPlatform.dao;

import com.design.helpPlatform.entity.OrderDetailEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao {

    int insert(OrderDetailEntity orderDetailEntity);

}
