package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.home.activity.RechargeActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.presenter.WalletPresenter;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.PicTextRightItem;
import com.ahxd.lingyuangou.widget.UIAlertView;
import com.ahxd.lingyuangou.widget.UIExchangeView;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by wpc on 2018/1/16.
 */

public class WalletActivity extends BaseActivity implements IWalletContract.IWalletView {


    @BindView(R.id.tv_user_money)
    TextView tvUserMoney;
    @BindView(R.id.tv_user_recommend)
    TextView tvUserRecommend;
    @BindView(R.id.tv_user_marketing)
    TextView tvUserMarketing;
    @BindView(R.id.tv_current_balance)
    TextView tvCurrentBalance;
    @BindView(R.id.tv_cash_balance)
    TextView tvCashBalance;
    @BindView(R.id.tv_jifen_management)
    TextView tvJiFenManagement;
    @BindView(R.id.tv_revenue_management)
    TextView tvRevenueManagement;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.ll_tabone)
    LinearLayout llTabOne;
    @BindView(R.id.ll_tabtwo)
    LinearLayout llTabTwo;
    @BindView(R.id.ll_tabthree)
    LinearLayout llTabThree;
    @BindView(R.id.ptr_balance_recharge_record)
    PicTextRightItem ptrBalanceRechargeRecord;
    @BindView(R.id.ptr_balance_consumption_record)
    PicTextRightItem ptrBalanceConsumptionRecord;
    @BindView(R.id.ptr_present_record)
    PicTextRightItem ptrPresentRecord;
    @BindView(R.id.ptr_advertising_fee_record)
    PicTextRightItem ptrAdvertisingFeeRecord;
    @BindView(R.id.ptr_marketing_revenue_record)
    PicTextRightItem ptrMarketingRevenueRecord;

    private WalletPresenter mPresenter;
    private JSONObject data;
    private JSONObject jifenData;
    private int isRecharge=0;
    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("我的钱包");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getWallet();
        mPresenter.getWalletIntegral();
    }

    @OnClick({R.id.tv_jifen_management,R.id.tv_cash_balance, R.id.tv_revenue_management, R.id.tv_recharge
            ,R.id.ptr_balance_recharge_record,R.id.ptr_balance_consumption_record,
            R.id.ptr_present_record,R.id.ptr_advertising_fee_record,R.id.ptr_marketing_revenue_record,
            R.id.ptr_jifen_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cash_balance:
                llTabOne.setVisibility(View.VISIBLE);
                llTabTwo.setVisibility(View.GONE);
                llTabThree.setVisibility(View.GONE);
                tvCashBalance.setTextColor(getResources().getColor(R.color.color_theme));
                tvRevenueManagement.setTextColor(getResources().getColor(R.color.black));
                tvJiFenManagement.setTextColor(getResources().getColor(R.color.black));
                if (null!=data){
                    tvUserMoney.setText(data.optString("userMoney"));
                }

                tvCurrentBalance.setText(getResources().getString(R.string.string_current_balance));
                tvRecharge.setText("充值");
                isRecharge=0;
                break;
            case R.id.tv_revenue_management:
                llTabOne.setVisibility(View.GONE);
                llTabTwo.setVisibility(View.VISIBLE);
                llTabThree.setVisibility(View.GONE);
                tvCashBalance.setTextColor(getResources().getColor(R.color.black));
                tvJiFenManagement.setTextColor(getResources().getColor(R.color.black));
                tvRevenueManagement.setTextColor(getResources().getColor(R.color.color_theme));
                if (null!=data){
                    tvUserMoney.setText(data.optString("userIncome"));
                }
                tvCurrentBalance.setText(getResources().getString(R.string.string_available_income));
                tvRecharge.setText("提现");
                isRecharge=1;
                break;
            case R.id.tv_jifen_management:
                llTabOne.setVisibility(View.GONE);
                llTabTwo.setVisibility(View.GONE);
                llTabThree.setVisibility(View.VISIBLE);
                tvCashBalance.setTextColor(getResources().getColor(R.color.black));
                tvRevenueManagement.setTextColor(getResources().getColor(R.color.black));
                tvJiFenManagement.setTextColor(getResources().getColor(R.color.color_theme));
                if (null!=jifenData){
                    tvUserMoney.setText(jifenData.optString("userScore"));
                }
                tvCurrentBalance.setText("可兑换积分");
                tvRecharge.setText("兑换");
                isRecharge=2;
                break;
            case R.id.tv_recharge:
                if (isRecharge==0){
                    Intent intent = new Intent(this, RechargeActivity.class);
                    startActivity(intent);
                }else if(isRecharge==1){
                    Intent intent = new Intent(this, WithdrawalsActivity.class);
                    startActivity(intent);
                }else if(isRecharge==2){
                    showLoginOutDialog();
                }
                break;
            case R.id.ptr_jifen_record:
                Intent jifenIntent = new Intent(this, IntegralRecordActivity.class);
                startActivity(jifenIntent);
                break;
            case R.id.ptr_balance_recharge_record:
                Intent balanceRechargeRecordActivityIntent = new Intent(this, BalanceRechargeRecordActivity.class);
                startActivity(balanceRechargeRecordActivityIntent);
                break;
            case R.id.ptr_balance_consumption_record:
                Intent balanceConsumptionRecordActivityIntent = new Intent(this, BalanceConsumptionRecordActivity.class);
                startActivity(balanceConsumptionRecordActivityIntent);
                break;
            case R.id.ptr_present_record:
                Intent presentIntent = new Intent(this, PresentRecordActivity.class);
                startActivity(presentIntent);
                break;
            case R.id.ptr_advertising_fee_record:
                Intent advertisingFeeIntent = new Intent(this, AdvertisingRecordActivity.class);
                startActivity(advertisingFeeIntent);
                break;
            case R.id.ptr_marketing_revenue_record:
                Intent marketingRevenueIntent = new Intent(this, MarketingRevenueRecordActivity.class);
                startActivity(marketingRevenueIntent);
                break;
        }
    }

    @Override
    protected void initData() {
        mPresenter = new WalletPresenter(this);
        mPresenter.getWallet();
        mPresenter.getWalletIntegral();
    }


    @Override
    protected int getLayoutId() {
        return  R.layout.activity_wallet;
    }

    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getWallet();
    }

    @Override
    public void showWallet(JSONObject data) {
        if (null != data) {
            this.data=data;
            if (isRecharge==0){
                tvUserMoney.setText(data.optString("userMoney"));
            }else if(isRecharge==1){
                tvUserMoney.setText(data.optString("userIncome"));
            }
            tvUserRecommend.setText(data.optString("userRecommend"));
            tvUserMarketing.setText(data.optString("userMarketing"));
        }
    }

    @Override
    public void showWalletIntegral(JSONObject data) {
        if (null != data) {
            this.jifenData=data;
            if (isRecharge==2){
                tvUserMoney.setText(data.optString("userScore"));
            }
        }
    }

    @Override
    public void showScoreToMoney(String data) {
        if (null != data) {
            ToastUtils.show(this,data,1);
            mPresenter.getWalletIntegral();
        }
    }

    private void showLoginOutDialog() {
        final UIExchangeView delDialog = new UIExchangeView(this, "请输入兑换金额",
                jifenData.optString("rate"), "取消", "确定");
        delDialog.show();
        delDialog.setClickListener(new UIExchangeView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight(String etExchange) {
                                           delDialog.dismiss();
                                           mPresenter.setScoreToMoney(etExchange);
                                       }
                                   }
        );
    }
}
