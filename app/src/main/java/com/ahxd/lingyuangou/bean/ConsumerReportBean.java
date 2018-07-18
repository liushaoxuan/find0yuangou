package com.ahxd.lingyuangou.bean;

/**
 * Created by sxliu on 2018/7/9 19:57
 * E-mail Address 2587294424@qq.com
 * 商家的报表bean
 */

public class ConsumerReportBean extends BaseBean {
    /**
     * 商家id
     */
    private String shopid;

    /**
     * 年
     */
    private String reportYear;

    /**
     * 月
     */
    private String reportMonth;

    /**
     * 金额
     */
    private double totalMoney;

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getReportYear() {
        return reportYear;
    }

    public void setReportYear(String reportYear) {
        this.reportYear = reportYear;
    }

    public String getReportMonth() {
        return reportMonth;
    }

    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
