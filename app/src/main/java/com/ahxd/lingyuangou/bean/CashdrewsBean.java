package com.ahxd.lingyuangou.bean;

import java.io.Serializable;

/**
 * Created by Mao Zhendong on 2018/1/12.
 */

public class CashdrewsBean implements Serializable {

    /**
     * money     提现金额
     * accTargetName 提现类型
     * accNo          账户
     * accUser         账户开户人
     * cashSatus      提现状态 -1失败 0审核中 1成功
     * createTime      提现时间
     * cashNo          提现id
     */

    private String money;
    private String accTargetName;
    private String accNo;
    private String accUser;
    private String cashSatus;
    private String createTime;
    private String cashNo;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAccTargetName() {
        return accTargetName;
    }

    public void setAccTargetName(String accTargetName) {
        this.accTargetName = accTargetName;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccUser() {
        return accUser;
    }

    public void setAccUser(String accUser) {
        this.accUser = accUser;
    }

    public String getCashSatus() {
        return cashSatus;
    }

    public void setCashSatus(String cashSatus) {
        this.cashSatus = cashSatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCashNo() {
        return cashNo;
    }

    public void setCashNo(String cashNo) {
        this.cashNo = cashNo;
    }
}
