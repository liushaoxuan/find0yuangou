package com.ahxd.lingyuangou.bean;

/**
 * Created by wpc on 2018/1/25.
 */

public class MessageBean {
    /**
     *   id	消息id	是	[string]	188	查看
     *	 msgContent	消息内容	是	[string]	订单【18011598489998】因长时间未支付，系统自动取消订单。	查看
     *	 msgStatus	消息状态 0未读 1已读	是	[string]	0	查看
     *	 createTime	消息时间
     *
     */

    private String id;
    private String msgContent;
    private String msgStatus;
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
