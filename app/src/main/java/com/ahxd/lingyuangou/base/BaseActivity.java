package com.ahxd.lingyuangou.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.mine.activity.LoginActivity;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.LoadingView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Mao on 2017/10/26.
 * Activity基类
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private TextView mToolbarTitle;
    private TextView mToolbarSubTitle;
    private Toolbar mToolbar;
    protected String mUserToken;
    protected Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        // 始终竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setLogo(R.mipmap.ic_launcher);
//        toolbar.setTitle("Title");
//        toolbar.setSubtitle("Sub Title");
        mToolbarTitle = (TextView) findViewById(R.id.tv_title);
        mToolbarSubTitle = (TextView) findViewById(R.id.tv_right);
        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            //getTitle()的值是activity的android:lable属性值
            mToolbarTitle.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        initView();
        initListener();
        initData();
    }

    /**
     * 初始化View
     */
    protected void initView() {
        mUnbinder = ButterKnife.bind(this);
    }

    /**
     * 初始化监听事件
     */
    protected abstract void initListener();

    /**
     * 初始化数据相关
     */
    protected abstract void initData();

    /**
     * 刷新数据
     */
    protected void refreshData() {}

    /**
     * 副标题点击
     */
    protected void onMenuClicked() {}

    /**
     * 加载布局
     */
    protected abstract int getLayoutId();

    @Override
    public void showStartRequest() {
        LoadingView.startLoading(this);
    }

    @Override
    public void showEndRequest() {
        LoadingView.stopLoading();
    }

    @Override
    public void showErrorRequest(String msg) {
        LoadingView.stopLoading();
        ToastUtils.showShort(this, msg);
    }

    @Override
    public void showReLogin() {
        ToastUtils.showShort(this, "请重新登录");
        SPUtils.put(this, Constant.KEY_TOKEN, "");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, Constant.REQ_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constant.REQ_LOGIN) {
            refreshData();
        }
    }

    @Override
    public void showRequestFailure(String msg) {
        ToastUtils.showShort(this, msg);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 判断是否有Toolbar,并默认显示返回按钮
         */
        if (null != getToolbar() && isShowBacking()) {
            showBack();
        }
    }

    /**
     * 获取头部标题的TextView
     */
    public TextView getToolbarTitle() {
        return mToolbarTitle;
    }

    /**
     * 获取头部标题的TextView
     */
    public TextView getSubTitle() {
        return mToolbarSubTitle;
    }

    /**
     * 设置头部标题
     */
    public void setToolBarTitle(CharSequence title) {
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        } else {
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
    }

    /**
     * 设置右边副标题
     */
    public void setToolbarSubTitle(CharSequence title) {
        if (mToolbarSubTitle != null) {
            mToolbarSubTitle.setText(title);
        }
        mToolbarSubTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuClicked();
            }
        });
    }

    /**
     * this Activity of tool bar.
     */
    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolbar().setNavigationIcon(R.mipmap.ic_back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     */
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        LoadingView.stopLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mUnbinder) {
            mUnbinder.unbind();
        }
    }
}