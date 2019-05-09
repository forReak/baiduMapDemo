package com.design.helpPlatform.dao;

import com.design.helpPlatform.entity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface GoodsDao {

    List<GoodsEntity> getGoodsInfoByShopId(Integer shopId);
}
