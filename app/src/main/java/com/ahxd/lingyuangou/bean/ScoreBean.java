package com.ahxd.lingyuangou.bean;

/**
 * Created by wpc on 2018/1/22.
 */

public class ScoreBean {
    /**
     *  score	消耗积分	是	[string]	20.00	查看
     *	 dataRemarks	备注	是	[string]	交易订单【18011710057102】消费积分20个	查看
     *	 createTime	时间
     */
    private  String score;
    private  String dataRemarks;
    private  String createTime;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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
