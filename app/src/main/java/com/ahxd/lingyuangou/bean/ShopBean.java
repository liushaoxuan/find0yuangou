package com.ahxd.lingyuangou.bean;

import java.util.List;

/**
 * Created by wpc on 2018/1/22.
 */

public class ShopBean {
    /**
     *  shopId	店铺ID	是	[string]	1	查看
     *	 shopName	名称	是	[string]	0元购自营总部	查看
     *	 shopImg	图片	是	[string]	http://m.findlyg.com/upload/shops/2018-01/5a51b786a3b0e.png	查看
     *	 telephone	联系电话	是	[string]	15956947523	查看
     *	 shopAddress	地址    是	[string]
     *	 shopAds	轮播图
     *	 shopContent	店铺详细    是	[string]
     */
    private String shopId;
    private String shopName;
    private String shopImg;
    private String telephone;
    private String shopAddress;
    private List<String> shopAds;;
    private String shopContent;
    private String lng;
    private String lat;

    public ShopBean() {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }


    public List<String> getShopAds() {
        return shopAds;
    }

    public void setShopAds(List<String> shopAds) {
        this.shopAds = shopAds;
    }

    public String getShopContent() {
        return shopContent;
    }

    public void setShopContent(String shopContent) {
        this.shopContent = shopContent;
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
}
