package com.design.helpPlatform.dao;

import com.design.helpPlatform.entity.OrderDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderDetailDao {

    int insert(OrderDetailEntity orderDetailEntity);

}
