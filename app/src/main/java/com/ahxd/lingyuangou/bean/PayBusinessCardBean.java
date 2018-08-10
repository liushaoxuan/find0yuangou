package com.ahxd.lingyuangou.bean;

import java.io.Serializable;

/**
 * Created by sxliu on 2018/8/10 19:58
 * E-mail Address 2587294424@qq.com
 */

public class PayBusinessCardBean implements Serializable {

    /**
     * userCardBalance : 15.97
     * memberCardCode :
     */
    private double userCardBalance;
    private String memberCardCode;

    public void setUserCardBalance(double userCardBalance) {
        this.userCardBalance = userCardBalance;
    }

    public void setMemberCardCode(String memberCardCode) {
        this.memberCardCode = memberCardCode;
    }

    public double getUserCardBalance() {
        return userCardBalance;
    }

    public String getMemberCardCode() {
        return memberCardCode;
    }
}
