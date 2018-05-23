package com.ahxd.lingyuangou.base;

/**
 * Created by Administrator on 2017/11/23.
 * V层相关接口
 */

public interface BaseView {

    /**
     * 开始请求界面相关处理
     */
    public void showStartRequest();

    /**
     * 结束请求界面相关处理
     */
    public void showEndRequest();

    /**
     * 请求错误界面相关处理
     */
    public void showErrorRequest(String msg);

    /**
     * 重新登录相关处理
     */
    public void showReLogin();

    /**
     * 请求失败界面相关处理
     * @param msg 错误信息
     */
    public void showRequestFailure(String msg);
}
