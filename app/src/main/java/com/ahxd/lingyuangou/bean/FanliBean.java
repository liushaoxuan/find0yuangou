package com.ahxd.lingyuangou.bean;

/**
 * Created by sxliu on 2018/7/15 16:50
 * E-mail Address 2587294424@qq.com
 */

public class FanliBean extends BaseBean {

    /**
     * freeUserincomebalance : 0.00
     * BQDuserincome : 0.00
     * outReturnVal : 0
     * BQDneedCash : 5.00
     */
    /**
     * 购卡金额
     */
    private String BQDneedCash;
    /**
     * 返利余额
     */
    private String BQDuserincome;
    /**
     * 可使用余额
     */
    private double freeUserincomebalance;

    private int outReturnVal;

    public void setFreeUserincomebalance(double freeUserincomebalance) {
        this.freeUserincomebalance = freeUserincomebalance;
    }

    public void setBQDuserincome(String BQDuserincome) {
        this.BQDuserincome = BQDuserincome;
    }

    public void setOutReturnVal(int outReturnVal) {
        this.outReturnVal = outReturnVal;
    }

    public void setBQDneedCash(String BQDneedCash) {
        this.BQDneedCash = BQDneedCash;
    }

    public double getFreeUserincomebalance() {
        return freeUserincomebalance;
    }

    public String getBQDuserincome() {
        return BQDuserincome;
    }

    public int getOutReturnVal() {
        return outReturnVal;
    }

    public String getBQDneedCash() {
        return BQDneedCash;
    }
}
