package com.ahxd.lingyuangou.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.mine.activity.LoginActivity;
import com.ahxd.lingyuangou.utils.NetUtils;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.LoadingView;

import butterknife.Unbinder;


/**
 * Created by Mao on 2017/10/24.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    protected Activity mContext;
    protected Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract View initView();

    protected void initData() {

    }

    @Override
    public void showStartRequest() {
        LoadingView.startLoading(mContext);
    }

    @Override
    public void showEndRequest() {
        LoadingView.stopLoading();
    }

    @Override
    public void showErrorRequest(String msg) {
        LoadingView.stopLoading();
        ToastUtils.showShort(mContext, msg);
    }

    @Override
    public void showReLogin() {
        ToastUtils.showShort(mContext, "请重新登录！");
        SPUtils.put(mContext, Constant.KEY_TOKEN, "");
        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivityForResult(intent, Constant.REQ_LOGIN);
    }

    @Override
    public void showRequestFailure(String msg) {
        ToastUtils.showShort(mContext, msg);
    }
}
