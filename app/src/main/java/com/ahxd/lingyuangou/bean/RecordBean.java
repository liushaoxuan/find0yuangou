package com.ahxd.lingyuangou.bean;

import java.io.Serializable;

/**
 * Created by Mao Zhendong on 2018/1/12.
 */

public class RecordBean implements Serializable {

    /**
     * type	类型 0：平台 1：支付宝，2：微信
     *	 money	金额
     *  r_orderNum	订单号
     * end_time	生成时间
     */

    private String type;
    private String money;
    private String r_orderNum;
    private String end_time;

    /**
     * remark  标题
     * createTime   时间
     */
    private String remark;
    private String createTime;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getR_orderNum() {
        return r_orderNum;
    }

    public void setR_orderNum(String r_orderNum) {
        this.r_orderNum = r_orderNum;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
