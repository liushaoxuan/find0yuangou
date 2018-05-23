package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.listener.OnMarketingListener;
import com.ahxd.lingyuangou.ui.home.activity.RechargeActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IMyMarketingCodeContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.presenter.MyMarketingCodePresenter;
import com.ahxd.lingyuangou.ui.mine.presenter.WalletPresenter;
import com.ahxd.lingyuangou.utils.GlideApp;
import com.ahxd.lingyuangou.widget.CircleImageView;
import com.ahxd.lingyuangou.widget.PicTextRightItem;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wpc on 2018/1/16.
 */

public class MyMarketingCodeActivity extends BaseActivity implements IMyMarketingCodeContract.IMyMarketingCodeView{


    @BindView(R.id.iv_mine_person_header)
    CircleImageView ivMinePersonHeader;
    @BindView(R.id.tv_mine_person_name)
    TextView tvMinePersonName;
    @BindView(R.id.tv_marketing_code)
    TextView tvMarketingCode;


    private MyMarketingCodePresenter mPresenter;
    private JSONObject data;
    private boolean isRecharge=true;
    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("我的营销码");
    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void initData() {
        mPresenter = new MyMarketingCodePresenter(this);
        mPresenter.getMyMarketingCode();
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_my_marketing_code;
    }

    @Override
    public void showMyMarketingCode(JSONObject data) {
        if (null != data) {
            this.data=data;
            tvMinePersonName.setText(data.optString("userName"));
            tvMarketingCode.setText(data.optString("markerCode"));
            GlideApp.with(this).load(data.optString("userPhoto"))
                    .placeholder(R.mipmap.ic_mine_person_header_normal)
                    .error(R.mipmap.ic_mine_person_header_normal)
                    .into(ivMinePersonHeader);
        }
    }

}
