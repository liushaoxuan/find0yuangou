package com.ahxd.lingyuangou.bean;

/**
 * Created by Administrator on 2018/1/3.
 * 商铺详情中的评价BEAN
 */

public class FoodShopEvaluateBean {

//    {
//            "goodsScore": 3,
//            "content": "\u6d4b\u8bd5\u6d4b\u8bd5\u6d4b\u8bd5\u6d4b\u8bd5\u6d4b\u8bd5\u6d4b\u8bd5\u6d4b\u8bd5\u6d4b\u8bd5\u6d4b\u8bd5\u6d4b\u8bd5",
//            "createTime": "2017-11-20 14:39:31",
//            "loginName": "lingyuangou"
//    }

    private String goodsScore;
    private String content;
    private String createTime;
    private String loginName;

    public String getGoodsScore() {
        return goodsScore;
    }

    public void setGoodsScore(String goodsScore) {
        this.goodsScore = goodsScore;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String users) {
        this.loginName = users;
    }
}
