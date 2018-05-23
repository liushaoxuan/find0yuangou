package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IMarketingApplyContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.presenter.MarketingApplyPresenter;
import com.ahxd.lingyuangou.ui.mine.presenter.WalletPresenter;
import com.ahxd.lingyuangou.widget.richtext.RichText;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wpc on 2018/1/18.
 */

public class MarketingApplyActivity extends BaseActivity implements IMarketingApplyContract.IMarketingApplyView {


    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.rt_marketing)
    RichText rtMarketing;
    @BindView(R.id.btn_apply_settled)
    Button btnApplySettled;

    private MarketingApplyPresenter mPresenter;
    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("营销人入驻");
    }

    @Override
    protected void initListener() {

    }
    @OnClick({R.id.btn_apply_settled})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_apply_settled:
                mPresenter.setMarketingApply();
//                Intent marketingIntent = new Intent(this, MarketingCenterActivity.class);
//                startActivity(marketingIntent);
                break;
        }
    }
    @Override
    protected void initData() {
        mPresenter = new MarketingApplyPresenter(this);
        mPresenter.getMarketingApply();
    }
    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getMarketingApply();
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_marketing_apply;
    }

    @Override
    public void showMarketingData(JSONObject data) {
        if (null != data) {
            tvNumber.setText("已有"+data.optString("count")+"人成为零元购营销人");
            rtMarketing.setRichText(data.optString("articleContent"));
        }
    }

    @Override
    public void showMarketingApply(JSONObject data) {
        Intent marketingIntent = new Intent(this, MarketingCenterActivity.class);
        startActivity(marketingIntent);
    }
}
