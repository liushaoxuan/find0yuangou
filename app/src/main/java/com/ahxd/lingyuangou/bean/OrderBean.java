package com.ahxd.lingyuangou.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mao on 2018/1/14.
 */

public class OrderBean implements Serializable {

    /**
     * shopName:
     * shopId:
     * isAppraise: 0
     * orderId : 45
     * orderNo : 18011198485050
     * createTime : 2018-01-11 16:57:31
     * totalMoney : 4800.00
     * realTotalMoney : 4800.00
     * orderStatusName : 已取消
     * orderStatus : -1
     * goods : [{"goodsId":1,"goodsNum":10,"goodsPrice":"480.00","goodsImg":"http://m.findlyg.com/upload/goods/2018-01/5a56cf2b4aa6f.jpg","goodsName":"单人房","goodsSpecNames":"窗户:有窗户、空调:有空调、","goodsType":0,"returnScore":"0.00"}]
     */

    private String shopImg;
    private String shopName;
    private String shopId;
    private String orderId;
    private String orderNo;
    private String createTime;
    private String totalMoney;
    private String realTotalMoney;
    private int orderStatus;
    private int isAppraise;
    private String orderStatusName;
    private List<GoodsBean> goods;

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
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

    public int getIsAppraise() {
        return isAppraise;
    }

    public void setIsAppraise(int isAppraise) {
        this.isAppraise = isAppraise;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getRealTotalMoney() {
        return realTotalMoney;
    }

    public void setRealTotalMoney(String realTotalMoney) {
        this.realTotalMoney = realTotalMoney;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean implements Serializable {
        /**
         * goodsId : 1
         * goodsNum : 10
         * goodsPrice : 480.00
         * goodsImg : http://m.findlyg.com/upload/goods/2018-01/5a56cf2b4aa6f.jpg
         * goodsName : 单人房
         * goodsSpecNames : 窗户:有窗户、空调:有空调、
         * goodsType : 0
         * returnScore : 0.00
         */

        private String goodsId;
        private int goodsNum;
        private String goodsPrice;
        private String goodsImg;
        private String goodsName;
        private String goodsSpecNames;
        private int goodsType;
        private String returnScore;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
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

        public String getGoodsSpecNames() {
            return goodsSpecNames;
        }

        public void setGoodsSpecNames(String goodsSpecNames) {
            this.goodsSpecNames = goodsSpecNames;
        }

        public int getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(int goodsType) {
            this.goodsType = goodsType;
        }

        public String getReturnScore() {
            return returnScore;
        }

        public void setReturnScore(String returnScore) {
            this.returnScore = returnScore;
        }
    }
}
