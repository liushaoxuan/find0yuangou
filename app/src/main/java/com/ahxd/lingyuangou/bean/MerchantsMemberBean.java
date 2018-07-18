package com.ahxd.lingyuangou.bean;

import java.io.Serializable;

/**
 * Created by sxliu on 2018/7/8 19:18
 * E-mail Address 2587294424@qq.com
 * 商家会员bean
 */

public class MerchantsMemberBean extends BaseBean {


    /**
     * userCardMoney : 0.00
     * phone : null
     * totalMoney : 2650.03
     * userid : 53
     * username : null
     */
    private double userCardMoney;
    private String phone;
    private double totalMoney;
    private int userid;
    private String username;

    public double getUserCardMoney() {
        return userCardMoney;
    }

    public void setUserCardMoney(double userCardMoney) {
        this.userCardMoney = userCardMoney;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username==null?"":username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
