package com.ahxd.lingyuangou.ui.mine.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IAlipayWechatContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.presenter.AddBankCardPresenter;
import com.ahxd.lingyuangou.ui.mine.presenter.AlipayWechatPresenter;
import com.ahxd.lingyuangou.utils.ToastUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wpc on 2018/1/16.
 */

public class AddAlipayAccountActivity extends BaseActivity implements IAlipayWechatContract.IAlipayWechatView{

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;

    @BindView(R.id.btn_add_account)
    Button btnAddAccount;

    private AlipayWechatPresenter mPresenter;
    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("添加支付宝");
        tvAccountName.setText("支付宝账号");
        etAccount.setHint("输入支付宝账号");
    }

    @Override
    protected void initListener() {

    }
    @OnClick({R.id.btn_add_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_add_account:

                if(etName.getText().toString().equals("")){
                    ToastUtils.showShort(this, "请填写姓名！");
                }
                if(etPhone.getText().toString().equals("")){
                    ToastUtils.showShort(this, "请填写电话！");
                }
                if(etId.getText().toString().equals("")){
                    ToastUtils.showShort(this, "请填写身份证！");
                }
                if(etAccount.getText().toString().equals("")){
                    ToastUtils.showShort(this, "请填写支付宝账号！");
                }
                mPresenter.setAlipayWechat("","1",etAccount.getText().toString(),
                        etName.getText().toString(),etPhone.getText().toString(),
                        etId.getText().toString());
                break;
        }
    }

    @Override
    protected void initData() {
        mPresenter=new AlipayWechatPresenter(this);
    }
    @Override
    protected int getLayoutId() {
        return  R.layout.activity_add_alipay_account;
    }

    @Override
    public void showAlipayWechat(JSONObject data) {
        ToastUtils.showShort(this, "添加成功！");
            finish();
    }
}
