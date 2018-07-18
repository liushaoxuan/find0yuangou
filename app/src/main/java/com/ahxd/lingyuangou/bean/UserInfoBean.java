package com.ahxd.lingyuangou.bean;

import java.io.Serializable;

/**
 * Created by sxliu on 2018/7/7 19:38
 * E-mail Address 2587294424@qq.com
 */

public class UserInfoBean extends BaseBean {

    /**
     * userSex : 0
     * userPhoto : http://m.findlyg.com/upload/sysconfigs/2018-01/5a584daf8de45.png
     * applyShop : {"handleDesc":"","applyStatus":-9}
     * userPhone : 18356987769
     * userMoney : 0.00
     * isMarketing : 1
     * userScore : 0.00
     * userName : 18356987769
     * userId : 372
     * userIncome : 0.00
     * recommendCode : 18356987769
     * userWeixin : null
     * markerCode : 18356987769
     * brithday : null
     * loginName : 18356987769
     * messagesCount : 0
     */
    private int userSex;
    private String userPhoto;
    private ApplyShopEntity applyShop;
    private String userPhone;
    private String userMoney;
    private int isMarketing;
    /**
     * 积分
     */
    private String userScore;
    private String userName;
    private int userId;
    /**
     * 返利
     */
    private String userIncome;
    private String recommendCode;
    private String userWeixin;
    private String markerCode;
    private String brithday;
    private String loginName;
    private int messagesCount;

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public void setApplyShop(ApplyShopEntity applyShop) {
        this.applyShop = applyShop;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserMoney(String userMoney) {
        this.userMoney = userMoney;
    }

    public void setIsMarketing(int isMarketing) {
        this.isMarketing = isMarketing;
    }

    public void setUserScore(String userScore) {
        this.userScore = userScore;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserIncome(String userIncome) {
        this.userIncome = userIncome;
    }

    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode;
    }

    public void setUserWeixin(String userWeixin) {
        this.userWeixin = userWeixin;
    }

    public void setMarkerCode(String markerCode) {
        this.markerCode = markerCode;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setMessagesCount(int messagesCount) {
        this.messagesCount = messagesCount;
    }

    public int getUserSex() {
        return userSex;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public ApplyShopEntity getApplyShop() {
        return applyShop;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserMoney() {
        return userMoney;
    }

    public int getIsMarketing() {
        return isMarketing;
    }

    public String getUserScore() {
        return userScore;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserIncome() {
        return userIncome;
    }

    public String getRecommendCode() {
        return recommendCode;
    }

    public String getUserWeixin() {
        return userWeixin;
    }

    public String getMarkerCode() {
        return markerCode;
    }

    public String getBrithday() {
        return brithday;
    }

    public String getLoginName() {
        return loginName;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public class ApplyShopEntity extends BaseBean{
        /**
         * handleDesc :
         * applyStatus : -9
         */
        private String handleDesc;
        private int applyStatus;

        public void setHandleDesc(String handleDesc) {
            this.handleDesc = handleDesc;
        }

        public void setApplyStatus(int applyStatus) {
            this.applyStatus = applyStatus;
        }

        public String getHandleDesc() {
            return handleDesc;
        }

        public int getApplyStatus() {
            return applyStatus;
        }
    }
}
