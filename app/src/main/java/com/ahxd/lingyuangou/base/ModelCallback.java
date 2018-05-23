package com.ahxd.lingyuangou.base;

/**
 * Created by Administrator on 2017/11/24.
 * P层共有属性处理
 */

public class ModelCallback implements RequestCallback {

    private BaseView mView;

    public ModelCallback(BaseView view) {
        this.mView = view;
    }

    @Override
    public void onStartRequest() {
        mView.showStartRequest();
    }

    @Override
    public void onEndRequest() {
        mView.showEndRequest();
    }

    @Override
    public void onErrorRequest(String msg) {
        mView.showErrorRequest(msg);
    }

    @Override
    public void onReLogin() {
        mView.showReLogin();
    }

    @Override
    public void onFailure(String msg) {
        mView.showRequestFailure(msg);
    }
}
