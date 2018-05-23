package com.ahxd.lingyuangou.bean;

/**
 * Created by Mao Zhendong on 2018/1/10.
 *
 * 商铺详情-》商品bean
 */

public class ShopGoodBean {


    /**
     * goodsId : 2
     * goodsName : 测试商品2
     * goodsImg : http://m.findlyg.com/upload/shops/2018-01/5a51b786a3b0e.png
     * marketPrice : 1.00
     * shopPrice : 1.00
     * saleNum : 0
     * goodsTips :
     * returnPrice : 480
     */

    private String goodsId;
    private String goodsName;
    private String goodsImg;
    private String marketPrice;
    private String shopPrice;
    private String saleNum;
    private String goodsTips;
    private String returnPrice;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getGoodsTips() {
        return goodsTips;
    }

    public void setGoodsTips(String goodsTips) {
        this.goodsTips = goodsTips;
    }

    public String getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(String returnPrice) {
        this.returnPrice = returnPrice;
    }
}
