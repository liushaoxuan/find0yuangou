package com.ahxd.lingyuangou.listener;

/**
 * Created by Mao Zhendong on 2018/1/17.
 */

public interface OnFileUploadListener {

    /**
     * 开始上传
     */
    public void onStart();

    /**
     * 上传进度
     * @param progress
     */
    public void onProgress(int progress);

    /**
     * 上传完成
     * @param url
     */
    public void onComplete(String url);

}
