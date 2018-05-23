package com.ahxd.lingyuangou.bean;

/**
 * Created by wpc on 2018/2/9.
 */

public class IntegralBean {
//    money	积分	是	[string]	18.00	查看
//    remark	明细	是	[string]	交易积分订单【151754602434】消耗积分18个	查看
//    createTime	时间 是	[string]	2018-02-02 12:33:44

    private String money;
    private String remark;
    private String createTime;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
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
