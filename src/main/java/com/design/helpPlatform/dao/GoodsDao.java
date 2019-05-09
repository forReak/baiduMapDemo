package com.design.helpPlatform.dao;

import com.design.helpPlatform.entity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@Mapper
public interface GoodsDao {

    List<GoodsEntity> getGoodsInfoByShopId(Integer shopId);

    List<GoodsEntity> getGoodsInfoByType(Integer type);

    List<GoodsEntity> getGoodsByGoodsIds(Set<String> goodsIdSet);
}
