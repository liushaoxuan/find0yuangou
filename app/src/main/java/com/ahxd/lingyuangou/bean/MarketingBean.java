package com.ahxd.lingyuangou.bean;

/**
 * Created by wpc on 2018/1/17.
 *
 */

public class MarketingBean {
    /**
     * number	金额
     * 	 createTime	生成时间
     * 	 dataRemarks	备注
     * 	 shopId	所属店铺ID
     * 	 shopName	店铺名称
     * 	 shopImg	店铺图片
     * 	 shopStatus	店铺状态
     *   incomes	总收益
     */

    private  String  number;
    private  String  createTime;
    private  String  dataRemarks;
    private  String  shopId;
    private  String  shopName;
    private  String  shopImg;
    private  String  shopStatus;
    private  String  incomes;

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

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public String getIncomes() {
        return incomes;
    }

    public void setIncomes(String incomes) {
        this.incomes = incomes;
    }
}
