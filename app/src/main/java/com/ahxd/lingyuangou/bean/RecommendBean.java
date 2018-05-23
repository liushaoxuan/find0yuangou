package com.ahxd.lingyuangou.bean;

/**
 * Created by wpc on 2018/1/17.
 */

public class RecommendBean {
    /**
     * userId		是	[string]	来源用户ID
     *	 userName		是	[string]	来源用户昵称
     *	 loginName		是	[string]	来源用户账号
     *	 dataId		是	[string]	日志id
     *	 number		是	[string]	收益金额
     *	 dataRemarks		是	[string]	收益备注
     *	 createTime		是	[string]	收益时间
     */

    private  String userId;
    private  String userName;
    private  String loginName;
    private  String dataId;
    private  String number;
    private  String dataRemarks;
    private  String createTime;

    public RecommendBean() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDataRemarks() {
        return dataRemarks;
    }

    public void setDataRemarks(String dataRemarks) {
        this.dataRemarks = dataRemarks;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
