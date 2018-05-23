package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.main.activity.MainActivity;
import com.ahxd.lingyuangou.ui.mine.contract.ILoginContract;
import com.ahxd.lingyuangou.ui.mine.model.LoginModel;
import com.ahxd.lingyuangou.ui.mine.presenter.LoginPresenter;
import com.ahxd.lingyuangou.utils.Base64Utils;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/8.
 */

public class LoginActivity extends BaseActivity implements ILoginContract.ILoginView {

    @BindView(R.id.et_login_account)
    EditText etAccount;
    @BindView(R.id.et_login_password)
    EditText etPassword;
    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;
    @BindView(R.id.tv_login_forget)
    TextView tvLoginForget;
    @BindView(R.id.btn_login_login)
    Button btnLoginLogin;

    private LoginPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initListener() {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initData() {
        String loginName = getIntent().getStringExtra("loginName");
        String loginPassword = getIntent().getStringExtra("login_password");
        if (loginName != null && loginPassword != null) {
            etAccount.setText(loginName);
            etPassword.setText(loginPassword);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.tv_login_register, R.id.tv_login_forget, R.id.btn_login_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_forget:
                Intent forgetIntent = new Intent(this, ForgetActivity.class);
                startActivity(forgetIntent);
                break;
            case R.id.btn_login_login:
                if (validate()) {
                    mPresenter.login(getText(etAccount), Base64Utils.encryptBase64(getText(etPassword)));
                }
                break;
        }
    }

    @Override
    public void showLogin(String msg) {
        ToastUtils.showShort(this, msg);
        setResult(RESULT_OK);
        finish();
    }

    private boolean validate() {
        if (TextUtils.isEmpty(etAccount.getText().toString().trim())) {
            ToastUtils.showShort(this, etAccount.getHint());
            return false;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString().trim())) {
            ToastUtils.showShort(this, etPassword.getHint());
            return false;
        }
        return true;
    }

    private String getText(EditText editText) {
        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            return null;
        } else {
            return editText.getText().toString().trim();
        }
    }

}
