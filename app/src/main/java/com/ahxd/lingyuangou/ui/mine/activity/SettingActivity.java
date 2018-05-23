package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.utils.UserUtils;
import com.ahxd.lingyuangou.utils.VersionUtils;
import com.ahxd.lingyuangou.widget.UIAlertView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mao on 2018/1/13.
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.btn_setting_login_out)
    Button btnSettingLoginOut;
    @BindView(R.id.rl_setting_forget_pwd)
    RelativeLayout rlSettingForgetPwd;
    @BindView(R.id.rl_setting_change_pwd)
    RelativeLayout rlSettingChangePwd;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.rl_setting_version)
    RelativeLayout rlSettingVersion;
    @BindView(R.id.tv_cache_size)
    TextView tvCacheSize;
    @BindView(R.id.rl_setting_cache)
    RelativeLayout rlSettingCache;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("我的设置");
        btnSettingLoginOut.setVisibility(UserUtils.isLogin() ? View.VISIBLE : View.GONE);
        tvVersion.setText(String.format("v%s.%s", VersionUtils.getVersionName(this), VersionUtils.getVersionCode(this)));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }


    @OnClick({R.id.rl_setting_forget_pwd, R.id.rl_setting_change_pwd, R.id.rl_setting_cache, R.id.btn_setting_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_setting_forget_pwd:
                break;
            case R.id.rl_setting_change_pwd:
                break;
            case R.id.rl_setting_cache:
                ToastUtils.showShort(this, "清理缓存成功！");
                break;
            case R.id.btn_setting_login_out:
                showLoginOutDialog();
                break;
        }
    }

    private void showLoginOutDialog() {
        final UIAlertView delDialog = new UIAlertView(this, "温馨提示",
                "是否确定退出?", "取消", "确定");
        delDialog.show();
        delDialog.setClickListener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           delDialog.dismiss();
                                           SPUtils.put(MaoApplication.getInstance(), Constant.KEY_TOKEN, "");
                                           Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                                           startActivity(intent);
                                           finish();
                                       }
                                   }
        );
    }

}
