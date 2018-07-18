package com.ahxd.lingyuangou.bean;

/**
 * Created by sxliu on 2018/7/11 21:25
 * E-mail Address 2587294424@qq.com
 * 商家卡bean
 */

public class BusinessCardBean extends BaseBean {
    /**
     * giveMoney : 0.00
     * memberCardid : 3
     * memberCardName : 商家卡
     * needCash : 5.00
     */
    private double giveMoney;
    private String memberCardid;
    private String memberCardName;
    private double needCash;
    private boolean isselected;

    public double getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(double giveMoney) {
        this.giveMoney = giveMoney;
    }

    public String getMemberCardid() {
        return memberCardid;
    }

    public void setMemberCardid(String memberCardid) {
        this.memberCardid = memberCardid;
    }

    public String getMemberCardName() {
        return memberCardName;
    }

    public void setMemberCardName(String memberCardName) {
        this.memberCardName = memberCardName;
    }

    public double getNeedCash() {
        return needCash;
    }

    public void setNeedCash(double needCash) {
        this.needCash = needCash;
    }

    public boolean isIsselected() {
        return isselected;
    }

    public void setIsselected(boolean isselected) {
        this.isselected = isselected;
    }
}
