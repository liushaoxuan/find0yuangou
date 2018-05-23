package com.ahxd.lingyuangou.bean;

import java.util.List;

/**
 * Created by Mao Zhendong on 2018/1/8.
 */

public class ShopManagemntDetailBean {


    private String shopId;
    private String shopName;
    private String shopImg;
    private String shopStatus;
    private String income;
    private List<DetailLog> log;

    public ShopManagemntDetailBean() {
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public List<DetailLog> getLog() {
        return log;
    }

    public void setLog(List<DetailLog> log) {
        this.log = log;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


    public static class DetailLog {

        private String number;
        private String createTime;
        private String dataRemarks;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDataRemarks() {
            return dataRemarks;
        }

        public void setDataRemarks(String dataRemarks) {
            this.dataRemarks = dataRemarks;
        }
    }

}
