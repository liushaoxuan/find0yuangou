package com.ahxd.lingyuangou.bean;

import java.io.Serializable;

/**
 * Created by Mao Zhendong on 2018/1/12.
 */

public class BankBean implements Serializable {

    /**
     * bankId     银行id
     * bankName 银行名称

     */
    private String bankId;
    private String bankName;

    public BankBean(String bankId, String bankName) {
        this.bankId = bankId;
        this.bankName = bankName;
    }


    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    //这个用来显示在PickerView上面的字符串,PickerView会通过反射获取getPickerViewText方法显示出来。
    public String getPickerViewText() {
        //这里还可以判断文字超长截断再提供显示
        return bankName;
    }
}
