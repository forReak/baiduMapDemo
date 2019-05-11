package com.design.helpPlatform.dao;

import com.design.helpPlatform.entity.ShopEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShopDao {

    ShopEntity getShopInfoByUrl(String url);

    ShopEntity getShopInfoById(Integer shopId);
}
