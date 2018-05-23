package com.ahxd.lingyuangou.bean;

/**
 * Created by Administrator on 2017/12/28.
 * 首页推荐金融
 */

public class HomeFinanceBean {

//    {
//            "shopAddress": "\u6d4b\u8bd5\u5730\u5740",
//            "shopName": "0\u5143\u8d2d\u81ea\u8425\u603b\u90e8",
//            "shopImg": "http:\/\/m.findlyg.com\/upload\/shops\/2017-11\/5a127ac32fa97.png",
//            "distance": "1.3",
//            "scoreRate": "0.10",
//            "saleCount": 0
//            "shopId": 1
//    }

    private String shopId;
    private String shopAddress;
    private String shopName;
    private String shopImg;
    private String distance;
    private String scoreRate;
    private String saleCount;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

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
