package com.ahxd.lingyuangou.bean;

/**
 * Created by sxliu on 2018/7/9 22:18
 * E-mail Address 2587294424@qq.com
 * 商家的订单Bean
 */

public class shopConsumerOrderBean extends BaseBean {
    /**
     * 商家id
     */
    private String shopid;

    /**
     * 类型
     */
    private String datatype;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 姓名
     */
    private String trueName;
    /**
     * 电话
     */
    private String userPhone;
    /**
     * 金额
     */
    private double totalMoney;
    /**
     * 生成时间
     */
    private String createTime;

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
