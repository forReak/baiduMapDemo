package com.design.helpPlatform.dao;

import com.design.helpPlatform.entity.ShopTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ShopTypeDao {

    List<ShopTypeEntity> getShopTypeByShopId(Integer shopId);
}
