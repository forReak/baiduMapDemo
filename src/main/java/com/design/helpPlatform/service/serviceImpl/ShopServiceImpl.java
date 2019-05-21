package com.design.helpPlatform.service.serviceImpl;

import com.design.helpPlatform.dao.GoodsDao;
import com.design.helpPlatform.dao.ShopDao;
import com.design.helpPlatform.dao.ShopTypeDao;
import com.design.helpPlatform.entity.GoodsEntity;
import com.design.helpPlatform.entity.ShopEntity;
import com.design.helpPlatform.entity.ShopTypeEntity;
import com.design.helpPlatform.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDao shopDao;
    @Autowired
    ShopTypeDao shopTypeDao;
    @Autowired
    GoodsDao goodsDao;

    @Override
    public ShopEntity getShopByUrl(String url) {
        //首先通过dao获取数据库中的数据，放入shopEntity
        ShopEntity shopEntity = shopDao.getShopInfoByUrl(url);
        if(shopEntity!=null){
            //根据商家id获取货物类型及货物
            List<ShopTypeEntity> shopTypeEntity = shopTypeDao.getShopTypeByShopId(shopEntity.getId());
            List<GoodsEntity> goodsEntityList = goodsDao.getGoodsInfoByShopId(shopEntity.getId());

            shopEntity.setGoodsEntityList(goodsEntityList);
            shopEntity.setTypeList(shopTypeEntity);
            return shopEntity;
        }else{
            throw new RuntimeException("无法根据url获取商家数据！");
        }
    }

    @Override
    public List<GoodsEntity> getShopGoodsByType(String typeId) {
        List<GoodsEntity> result = goodsDao.getGoodsInfoByType(Integer.valueOf(typeId));
        return result;
    }

    @Override
    public List<GoodsEntity> getGoodsByGoodsIds(Set<String> goodsIdSet) {
        List<GoodsEntity> result = goodsDao.getGoodsByGoodsIds(goodsIdSet);
        return result;
    }
}
