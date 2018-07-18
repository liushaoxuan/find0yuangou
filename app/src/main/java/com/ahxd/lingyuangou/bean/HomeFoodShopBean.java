package com.ahxd.lingyuangou.bean;

/**
 * Created by Administrator on 2017/12/28.
 * 首页热门餐饮店铺bean
 */

public class HomeFoodShopBean extends BaseBean {
    private String orderNum;
    private String lng;
    private String lat;
    private String shopId;
    private String shopName;
    private String shopImg;
    private String distance;
    private String scoreRate;
    private String saleCount;
    private String shopAddress;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }
}
