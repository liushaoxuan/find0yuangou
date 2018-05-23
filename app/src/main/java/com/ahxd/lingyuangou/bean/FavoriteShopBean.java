package com.ahxd.lingyuangou.bean;

/**
 * Created by Mao on 2018/1/12.
 */

public class FavoriteShopBean {

    /**
     * favoriteId : 111
     * shopId : 17
     * shopName : 测试1
     * shopImg : http://m.findlyg.com/upload/shops/2018-01/5a57680eb1e6a.jpg
     * shopAddress : 测试1测试1测试1测试1
     * scoreRate : 1.00
     */

    private String favoriteId;
    private String shopId;
    private String shopName;
    private String shopImg;
    private String shopAddress;
    private String scoreRate;

    public String getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(String favoriteId) {
        this.favoriteId = favoriteId;
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

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getScoreRate() {
        return scoreRate;
    }

    public void setScoreRate(String scoreRate) {
        this.scoreRate = scoreRate;
    }
}
