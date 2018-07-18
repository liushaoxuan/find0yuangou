package com.ahxd.lingyuangou.bean;

/**
 * Created by Administrator on 2017/12/28.
 * 首页热门商品bean
 */

public class HomeGoodBean {

//    {
//            "goodsId": 1,
//            "goodsImg": "http:\/\/m.findlyg.com\/upload\/goods\/2017-12\/5a2616fc06cc8.png",
//            "goodsName": "111",
//            "shopPrice": "11.00",
//            "returnPrice": 1.1
//            "goodsTips": 1.1
//    }

    private String goodsId;
    private String goodsImg;
    private String goodsName;
    private String shopPrice;
    private String returnPrice;
    private String goodsTips;

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

    public String getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(String returnPrice) {
        this.returnPrice = returnPrice;
    }

    public String getGoodsTips() {
        return goodsTips;
    }

    public void setGoodsTips(String goodsTips) {
        this.goodsTips = goodsTips;
    }
}
