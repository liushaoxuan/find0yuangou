package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.ui.home.activity.RechargeActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.presenter.WalletPresenter;
import com.ahxd.lingyuangou.widget.PicTextRightItem;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wpc on 2018/1/16.
 */

public class AddPresentAccountActivity extends BaseActivity{


    @BindView(R.id.ptr_add_alipay)
    PicTextRightItem ptrAddAipay;
    @BindView(R.id.ptr_add_weixin)
    PicTextRightItem ptrAddWeixin;
    @BindView(R.id.ptr_add_bank_card)
    PicTextRightItem ptrAddBankCard;

    private JSONObject data;
    private boolean isRecharge=true;
    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("添加账户");
        ptrAddWeixin.setVisibility(View.GONE);
        ptrAddAipay.setVisibility(View.GONE);
    }

    @Override
    protected void initListener() {

    }
    @OnClick({R.id.ptr_add_alipay,R.id.ptr_add_weixin,R.id.ptr_add_bank_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ptr_add_alipay:
                Intent alipayIntent = new Intent(this, AddAlipayAccountActivity.class);
                startActivity(alipayIntent);
                break;
            case R.id.ptr_add_weixin:
                Intent wechatIntent = new Intent(this, AddWechatAccountActivity.class);
                startActivity(wechatIntent);
                break;
            case R.id.ptr_add_bank_card:
                Intent bankCardIntent = new Intent(this, AddBankCardAccountActivity.class);
                startActivity(bankCardIntent);
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_add_present_account;
    }

}
