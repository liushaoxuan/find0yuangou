package com.ahxd.lingyuangou.bean;

/**
 * Created by wpc on 2018/1/18.
 */

public class MyMemberBean {
    /**
     *   userName	下级用户昵称	是	[string]
     *	 loginName	下级用户账号	是	[string]
     *	 level	所属层级	是	[string]
     *	 income	带来收益	是	[string]
     */
    private String userName;
    private String loginName;
    private String level;
    private String income;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }
}
