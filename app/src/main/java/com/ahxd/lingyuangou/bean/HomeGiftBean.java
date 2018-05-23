package com.ahxd.lingyuangou.bean;

/**
 * Created by Administrator on 2017/12/27.
 * 首页热门礼品bean
 */

public class HomeGiftBean {

//    {
//            "goodsId": 1,
//            "goodsImg": "http:\/\/m.findlyg.com\/upload\/shops\/2016-10\/57f8641d41b80.jpg",
//            "goodsName": "1111111111111111",
//            "shopPrice": "1099.00"
//    }

    private String goodsId;
    private String goodsImg;
    private String goodsName;
    private String shopPrice;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }
}
