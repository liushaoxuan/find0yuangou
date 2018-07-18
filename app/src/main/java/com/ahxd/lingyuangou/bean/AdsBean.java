package com.ahxd.lingyuangou.bean;

/**
 * Created by sxliu on 2018/6/27 23:04
 * E-mail Address 2587294424@qq.com
 * 首页广告bean
 */

public class AdsBean extends BaseBean {


    /**
     * adName : 1
     * adType : 0
     * adTypeData : null
     * adFile : http://m.findlyg.com/upload/adspic/2018-03/5aa7649443521.jpg
     */
    private String adName;
    private String adType;
    private String adTypeData;
    private String adFile;

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getAdTypeData() {
        return adTypeData;
    }

    public void setAdTypeData(String adTypeData) {
        this.adTypeData = adTypeData;
    }

    public String getAdFile() {
        return adFile;
    }

    public void setAdFile(String adFile) {
        this.adFile = adFile;
    }
}
