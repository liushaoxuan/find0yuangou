package com.ahxd.lingyuangou.bean;

/**
 * Created by sxliu on 2018/7/15 13:57
 * E-mail Address 2587294424@qq.com
 * 购买明细的Bean
 */

public class BuyCardDetailBean extends BaseBean {

    /**
     * 卡名称
     */
    private String memberCardName;

    /**
     * 购买时间
     */
    private String rechargeDate ;

    /**
     * 购买金额
     */
    private double rechargeCash ;

    /**
     * 购买状态( 1-成功0-失败)
     */
    private int recharge_status;

    public String getMemberCardName() {
        return memberCardName;
    }

    public void setMemberCardName(String memberCardName) {
        this.memberCardName = memberCardName;
    }

    public String getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(String rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public double getRechargeCash() {
        return rechargeCash;
    }

    public void setRechargeCash(double rechargeCash) {
        this.rechargeCash = rechargeCash;
    }

    public String getRecharge_status() {
        String mstatu = "";
        if (recharge_status==1){
            mstatu = "购买成功";
        }else {
            mstatu = "购买失败";
        }
        return mstatu;
    }

    public void setRecharge_status(int recharge_status) {
        this.recharge_status = recharge_status;
    }
}
