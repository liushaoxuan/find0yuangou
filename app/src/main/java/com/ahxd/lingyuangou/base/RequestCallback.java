package com.ahxd.lingyuangou.base;

/**
 * Created by Administrator on 2017/11/23.
 * P层相关回调接口
 */

public interface RequestCallback {

    /**
     * 开始请求
     */
    public void onStartRequest();

    /**
     * 请求结束
     */
    public void onEndRequest();

    /**
     * 请求失败
     */
    public void onErrorRequest(String msg);

    /**
     * 重新登录
     */
    public void onReLogin();

    /**
     * 请求失败错误信息
     */
    public void onFailure(String msg);
}
