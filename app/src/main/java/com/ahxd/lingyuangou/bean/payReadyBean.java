package com.ahxd.lingyuangou.bean;

/**
 * Created by sxliu on 2018/7/16 21:00
 * E-mail Address 2587294424@qq.com
 * 商家卡  资格卡g 购买前准备的bean
 */

public class payReadyBean extends BaseBean {

    /**
     * BMCrechargeOrderid : 2018071620573025
     * BMCneedCash : 5.00
     * outReturnVal : 0
     */
    /**
     * 订单id
     */
    private String BMCrechargeOrderid;
    /**
     * 需要支付的现金   判断  BMCneedCash是否大于0，如果等于0调用buyQualificationComplete完成订单，如果大于0，调用 api/pay/payparameter
     */
    private double BMCneedCash;
    private int outReturnVal;

    public void setBMCrechargeOrderid(String BMCrechargeOrderid) {
        this.BMCrechargeOrderid = BMCrechargeOrderid;
    }

    public void setBMCneedCash(double BMCneedCash) {
        this.BMCneedCash = BMCneedCash;
    }

    public void setOutReturnVal(int outReturnVal) {
        this.outReturnVal = outReturnVal;
    }

    public String getBMCrechargeOrderid() {
        return BMCrechargeOrderid;
    }

    public double getBMCneedCash() {
        return BMCneedCash;
    }

    public int getOutReturnVal() {
        return outReturnVal;
    }
}
