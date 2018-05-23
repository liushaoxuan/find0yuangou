package com.ahxd.lingyuangou.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/2.
 */

public class FoodShopBean implements Serializable {

    /**
     * shopAddress : 测试地址
     * shopName : 0元购自营总部
     * shopId : 1
     * catId : 366
     * shopImg : http://m.findlyg.com/upload/shops/2017-11/5a127ac32fa97.png
     * distance : 1.2
     * scoreRate : 0.10
     * saleCount : 0
     */

    private String shopAddress;
    private String shopName;
    private String shopId;
    private String catId;
    private String shopImg;
    private String distance;
    private String scoreRate;
    private String saleCount;

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getScoreRate() {
        return scoreRate;
    }

    public void setScoreRate(String scoreRate) {
        this.scoreRate = scoreRate;
    }

    public String getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(String saleCount) {
        this.saleCount = saleCount;
    }
}
