package com.design.helpPlatform.entity;

import java.util.List;

public class ShopEntity {
    private Integer id;
    private String shopUrl;
    private String shopName;
    private String shopAddr;
    private String shopTags;
    private String shopMainItem;
    private String online;

    private List<ShopTypeEntity> typeList;

    private List<GoodsEntity> goodsEntityList;

    public Integer getId() {
        return id;
    }

    public List<GoodsEntity> getGoodsEntityList() {
        return goodsEntityList;
    }

    public void setGoodsEntityList(List<GoodsEntity> goodsEntityList) {
        this.goodsEntityList = goodsEntityList;
    }

    public List<ShopTypeEntity> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<ShopTypeEntity> typeList) {
        this.typeList = typeList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    public String getShopTags() {
        return shopTags;
    }

    public void setShopTags(String shopTags) {
        this.shopTags = shopTags;
    }

    public String getShopMainItem() {
        return shopMainItem;
    }

    public void setShopMainItem(String shopMainItem) {
        this.shopMainItem = shopMainItem;
    }
}
