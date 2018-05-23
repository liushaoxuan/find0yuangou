package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.ui.home.activity.RechargeActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IMarketingCenterContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.presenter.MarketingCenterPresenter;
import com.ahxd.lingyuangou.ui.mine.presenter.WalletPresenter;
import com.ahxd.lingyuangou.widget.PicTextRightItem;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wpc on 2018/1/18.
 */

public class MarketingCenterActivity extends BaseActivity implements IMarketingCenterContract.IMarketingCenterView {


    @BindView(R.id.ptr_administration)
    PicTextRightItem ptrAdministration;
    @BindView(R.id.ptr_profit)
    PicTextRightItem ptrProfit;
    @BindView(R.id.ptr_marketing_code)
    PicTextRightItem ptrMarketingCode;
    @BindView(R.id.tv_user_marketing)
    TextView tvUserMarketing;

    private MarketingCenterPresenter mPresenter;
    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("营销人中心");
    }

    @Override
    protected void initListener() {

    }
    @OnClick({R.id.ptr_administration,R.id.ptr_profit,R.id.ptr_marketing_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ptr_administration:
                Intent intent=new Intent(this,ShopManagementListActivity.class);
                startActivity(intent);
                break;
            case R.id.ptr_profit:
                Intent marketingRevenueIntent = new Intent(this, MarketingRevenueRecordActivity.class);
                startActivity(marketingRevenueIntent);
                break;
            case R.id.ptr_marketing_code:

                Intent codeIntent = new Intent(this, MyMarketingCodeActivity.class);
                startActivity(codeIntent);

                break;
        }
    }

    @Override
    protected void initData() {
        mPresenter = new MarketingCenterPresenter(this);
        mPresenter.getMarketingCenter();
    }
    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getMarketingCenter();
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_marketing_center;
    }

    @Override
    public void showMarketingCenter(JSONObject data) {
        if (null != data) {
            tvUserMarketing.setText(data.optString("userMarketing"));
        }
    }
}
