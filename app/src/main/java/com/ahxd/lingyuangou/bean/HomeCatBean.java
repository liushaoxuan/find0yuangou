package com.ahxd.lingyuangou.bean;

/**
 * Created by Administrator on 2017/12/27.
 * 首页分类bean
 */

public class HomeCatBean {

//    {
//            "catId": 366,
//            "catName": "\u7f8e\u98df",
//            "channelIcon": "http:\/\/m.findlyg.com\/\/upload\/goodscats\/1.png"
//    }

    private String catId;
    private String catName;
    private String channelIcon;

    public HomeCatBean() {
    }

    public HomeCatBean(String catId, String catName, String channelIcon) {
        this.catId = catId;
        this.catName = catName;
        this.channelIcon = channelIcon;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getChannelIcon() {
        return channelIcon;
    }

    public void setChannelIcon(String channelIcon) {
        this.channelIcon = channelIcon;
    }
}
