package com.design.helpPlatform.service;

import com.design.helpPlatform.entity.GoodsEntity;
import com.design.helpPlatform.entity.ShopEntity;

import java.util.List;
import java.util.Set;

public interface ShopService  {

    ShopEntity getShopByUrl(String url);

    List<GoodsEntity> getShopGoodsByType(String typeId);

    List<GoodsEntity> getGoodsByGoodsIds(Set<String> goodsIdSet);

}
