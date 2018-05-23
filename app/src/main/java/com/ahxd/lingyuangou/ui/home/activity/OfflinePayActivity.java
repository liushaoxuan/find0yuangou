package com.ahxd.lingyuangou.ui.home.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.home.contract.IOfflinePayContract;
import com.ahxd.lingyuangou.ui.home.presenter.OfflinePayPresenter;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.PayResult;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.utils.UserUtils;
import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.json.JSONObject;

import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mao Zhendong on 2018/1/10.
 * 线下转账界面
 */

public class OfflinePayActivity extends BaseActivity implements IOfflinePayContract.IOfflinePayView {

    @BindView(R.id.iv_offline_pay_shop_image)
    ImageView ivOfflinePayShopImage;
    @BindView(R.id.iv_offline_pay_shop_name)
    TextView ivOfflinePayShopName;
    @BindView(R.id.tv_shop_open_time)
    TextView tvShopOpenTime;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.tv_offline_pay_ad_money)
    TextView tvOfflinePayAdMoney;
    @BindView(R.id.tv_offline_pay_coin)
    TextView tvOfflinePayCoin;
    @BindView(R.id.tv_pay_to)
    TextView tvPayTo;
    @BindView(R.id.rb_recharge_weixin)
    ImageView rbRechargeWeixin;
    @BindView(R.id.rb_recharge_zhifubao)
    ImageView rbRechargeZhifubao;
    @BindView(R.id.rb_offline_pay_wallet)
    ImageView rbOfflinePayWallet;
    @BindView(R.id.tv_offline_pay_wallet_money)
    TextView tvOfflinePayWalletMoney;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private OfflinePayPresenter mPresenter;

    private String mShopId;
    private float mAdRate;
    private float mMoneyRate;
    private int mPayType = 1; //1余额2微信3支付宝

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("优惠买单");
        rbOfflinePayWallet.setSelected(true);
    }

    @Override
    protected void initListener() {
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etMoney.getText().length() > 0) {
                    float money = Float.parseFloat(s.toString());
                    tvOfflinePayAdMoney.setText(String.format(Locale.CHINA, "%.2f", money * mAdRate));
                    tvOfflinePayCoin.setText(String.format(Locale.CHINA, "%.2f", money * mMoneyRate));
                    btnConfirm.setClickable(true);
                } else {
                    tvOfflinePayAdMoney.setText(null);
                    tvOfflinePayCoin.setText(null);
                    btnConfirm.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        mShopId = getIntent().getStringExtra("shopId");
        mPresenter = new OfflinePayPresenter(this);
        mPresenter.getOfflinePayDetail(UserUtils.getToken(), mShopId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_offline_pay;
    }

    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getOfflinePayDetail(UserUtils.getToken(), mShopId);
    }

    @Override
    public void showShopDetail(JSONObject data) {
        ivOfflinePayShopName.setText(data.optString("shopName"));
        tvShopOpenTime.setText(String.format("营业时间：%s-%s", data.optString("serviceStartTime"),
                data.optString("serviceEndTime")));
        mAdRate = (float) data.optDouble("adRate");
        mMoneyRate = (float) data.optDouble("moneyRate");
        tvOfflinePayWalletMoney.setText(String.format("(剩余：%s)", data.optString("userMoney")));
        Glide.with(this).load(data.optString("shopImg")).into(ivOfflinePayShopImage);
    }

    @Override
    public void showWeixinPay(JSONObject data) {
        if (data != null) {
            PayReq req = new PayReq();
            req.appId = data.optString("appid");
            req.partnerId = data.optString("partnerid");
            req.prepayId = data.optString("prepayid");
            req.nonceStr = data.optString("noncestr");
            req.timeStamp = data.optString("timestamp");
            req.packageValue = data.optString("package");
            req.sign = data.optString("sign");
            ((MaoApplication) getApplication()).getWXApi().sendReq(req);
        }
    }

    private static final int SDK_PAY_FLAG = 0x700;

    @Override
    public void showZhifubaoPay(String data) {
        final String orderInfo = data;   // 订单信息
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(OfflinePayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void showWalletPay(String msg) {
        ToastUtils.showShort(this, msg);
        finish();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    L.e("zhifubao - payResult ", payResult.toString());
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showShort(OfflinePayActivity.this, "支付成功");
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort(OfflinePayActivity.this, "取消支付");
                    } else {
                        ToastUtils.showShort(OfflinePayActivity.this, "支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @OnClick({R.id.rb_recharge_weixin, R.id.rb_recharge_zhifubao, R.id.rb_offline_pay_wallet, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_recharge_weixin:
                mPayType = 2;
                rbOfflinePayWallet.setSelected(false);
                rbRechargeWeixin.setSelected(true);
                rbRechargeZhifubao.setSelected(false);
                break;
            case R.id.rb_recharge_zhifubao:
                mPayType = 3;
                rbOfflinePayWallet.setSelected(false);
                rbRechargeWeixin.setSelected(false);
                rbRechargeZhifubao.setSelected(true);
                break;
            case R.id.rb_offline_pay_wallet:
                mPayType = 1;
                rbOfflinePayWallet.setSelected(true);
                rbRechargeWeixin.setSelected(false);
                rbRechargeZhifubao.setSelected(false);
                break;
            case R.id.btn_confirm:
                if (validate()) {
                    mPresenter.payOfflineOrder((String) SPUtils.get(this, Constant.KEY_TOKEN, ""),
                            mShopId, mPayType, etMoney.getText().toString().trim());
                }
                break;
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(etMoney.getText().toString().trim())) {
            ToastUtils.showShort(this, etMoney.getHint());
            return false;
        } else {
            return true;
        }
    }
}
