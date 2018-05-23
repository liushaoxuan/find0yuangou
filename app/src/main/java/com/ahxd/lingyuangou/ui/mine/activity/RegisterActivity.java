package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IRegisterContract;
import com.ahxd.lingyuangou.ui.mine.presenter.RegisterPresenter;
import com.ahxd.lingyuangou.utils.Base64Utils;
import com.ahxd.lingyuangou.utils.TimeCount;
import com.ahxd.lingyuangou.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/8.
 */

public class RegisterActivity extends BaseActivity implements IRegisterContract.IRegisterView {

    @BindView(R.id.et_register_invite)
    EditText etRegisterInvite;
    @BindView(R.id.et_register_account)
    EditText etRegisterAccount;
    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;
    @BindView(R.id.et_register_password_again)
    EditText etRegisterPasswordAgain;
    @BindView(R.id.et_register_validate_code)
    EditText etRegisterValidateCode;
    @BindView(R.id.btn_register_get_code)
    Button btnRegisterGetCode;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private TimeCount mTimeCount;
    private RegisterPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        mTimeCount = new TimeCount(60000, 1000, btnRegisterGetCode);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter = new RegisterPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.btn_register_get_code, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register_get_code:
                if (TextUtils.isEmpty(etRegisterAccount.getText().toString().trim())) {
                    ToastUtils.showShort(this, etRegisterAccount.getHint());
                    return;
                }
                mPresenter.getValidateCode(etRegisterAccount.getText().toString().trim());
                break;
            case R.id.btn_register:
                if (validate()) {
                    mPresenter.register(getText(etRegisterInvite), getText(etRegisterAccount),
                            Base64Utils.encryptBase64(getText(etRegisterPassword)),
                            Base64Utils.encryptBase64(getText(etRegisterPasswordAgain)),
                            getText(etRegisterValidateCode));
                }
                break;
        }
    }

    @Override
    public void showGetValidate(String msg) {
        ToastUtils.showShort(this, msg);
        mTimeCount.start();
    }

    @Override
    public void showRegister(String msg) {
        ToastUtils.showShort(this, msg);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("loginName", getText(etRegisterAccount));
        intent.putExtra("login_password", getText(etRegisterPassword));
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimeCount != null) {
            mTimeCount.onFinish();
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(etRegisterAccount.getText().toString().trim())) {
            ToastUtils.showShort(this, etRegisterAccount.getHint());
            return false;
        }
        if (TextUtils.isEmpty(etRegisterPassword.getText().toString().trim())) {
            ToastUtils.showShort(this, etRegisterPassword.getHint());
            return false;
        }
        if (TextUtils.isEmpty(etRegisterPasswordAgain.getText().toString().trim())) {
            ToastUtils.showShort(this, etRegisterPasswordAgain.getHint());
            return false;
        }
        if (TextUtils.isEmpty(etRegisterValidateCode.getText().toString().trim())) {
            ToastUtils.showShort(this, etRegisterValidateCode.getHint());
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
