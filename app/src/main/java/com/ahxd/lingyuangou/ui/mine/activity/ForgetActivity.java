package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IForgetContract;
import com.ahxd.lingyuangou.ui.mine.presenter.ForgetPresenter;
import com.ahxd.lingyuangou.ui.mine.presenter.RegisterPresenter;
import com.ahxd.lingyuangou.utils.Base64Utils;
import com.ahxd.lingyuangou.utils.TimeCount;
import com.ahxd.lingyuangou.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mao on 2018/1/17.
 */
public class ForgetActivity extends BaseActivity implements IForgetContract.IForgetView{

    @BindView(R.id.et_login_account)
    EditText etLoginAccount;
    @BindView(R.id.et_forget_validate_code)
    EditText etForgetValidateCode;
    @BindView(R.id.btn_forget_get_code)
    Button btnForgetGetCode;
    @BindView(R.id.et_forget_password)
    EditText etForgetPassword;
    @BindView(R.id.et_forget_password_again)
    EditText etForgetPasswordAgain;
    @BindView(R.id.btn_forget_confirm)
    Button btnForgetConfirm;

    private TimeCount mTimeCount;
    private ForgetPresenter mPresenter;
    @Override
    protected void initView() {
        super.initView();
        mTimeCount = new TimeCount(60000, 1000, btnForgetGetCode);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter=new ForgetPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget;
    }

    @OnClick({R.id.btn_forget_get_code, R.id.btn_forget_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_forget_get_code:
                if (TextUtils.isEmpty(etLoginAccount.getText().toString().trim())) {
                    ToastUtils.showShort(this, etLoginAccount.getHint());
                    return;
                }
                mPresenter.getValidateCode(etLoginAccount.getText().toString().trim());
                break;
            case R.id.btn_forget_confirm:
                if (validate()) {
                    mPresenter.forget(getText(etLoginAccount),
                            Base64Utils.encryptBase64(getText(etForgetPassword)),
                            Base64Utils.encryptBase64(getText(etForgetPasswordAgain)),
                            getText(etForgetValidateCode));
                }
//                mPresenter.getValidateCode(etLoginAccount.getText().toString().trim());
                break;
        }
    }

    @Override
    public void showGetValidate(String msg) {
        ToastUtils.showShort(this, msg);
        mTimeCount.start();
    }

    @Override
    public void showForget(String msg) {
        ToastUtils.showShort(this, msg);
//        Intent intent = new Intent(this, LoginActivity.class);
//        intent.putExtra("loginName", etLoginAccount.getText().toString());
//        intent.putExtra("login_password", etForgetPassword.getText().toString());
//        startActivity(intent);
        finish();
    }

    private boolean validate() {
        if (TextUtils.isEmpty(etLoginAccount.getText().toString().trim())) {
            ToastUtils.showShort(this, etLoginAccount.getHint());
            return false;
        }
        if (TextUtils.isEmpty(etForgetValidateCode.getText().toString().trim())) {
            ToastUtils.showShort(this, etForgetValidateCode.getHint());
            return false;
        }
        if (TextUtils.isEmpty(etForgetPassword.getText().toString().trim())) {
            ToastUtils.showShort(this, etForgetPassword.getHint());
            return false;
        }
        if (TextUtils.isEmpty(etForgetPasswordAgain.getText().toString().trim())) {
            ToastUtils.showShort(this, etForgetPasswordAgain.getHint());
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
