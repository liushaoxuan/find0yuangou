package com.ahxd.lingyuangou.bean;

import java.io.Serializable;

/**
 * Created by Mao Zhendong on 2018/1/12.
 */

public class WithdrawalsAccountBean implements Serializable {

    /**
     * id     主键
     * accTargetId  银行卡id
     * accNo  银行卡号
     * accUser  持卡人
     * accAreaId  开户地区
     * accAddress  开户行信息
     */
    private String id;
    private String accTargetId;
    private String accNo;
    private String accUser;
    private String accAreaId;
    private String accAddress;
    private Boolean isSelected=false;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccTargetId() {
        return accTargetId;
    }

    public void setAccTargetId(String accTargetId) {
        this.accTargetId = accTargetId;
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

    public String getAccAreaId() {
        return accAreaId;
    }

    public void setAccAreaId(String accAreaId) {
        this.accAreaId = accAreaId;
    }

    public String getAccAddress() {
        return accAddress;
    }

    public void setAccAddress(String accAddress) {
        this.accAddress = accAddress;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
