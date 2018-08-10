package com.ahxd.lingyuangou.ui.mine.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.BusinessCardBean;
import com.ahxd.lingyuangou.bean.FanliBean;
import com.ahxd.lingyuangou.bean.ShopBean;
import com.ahxd.lingyuangou.bean.UserInfoBean;
import com.ahxd.lingyuangou.bean.payReadyBean;
import com.ahxd.lingyuangou.callback.MyStringCallBack;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.listener.OnChangeItemClickListener;
import com.ahxd.lingyuangou.ui.main.activity.MainActivity;
import com.ahxd.lingyuangou.ui.mine.adapter.PurchaseQualificatioCardAdapter;
import com.ahxd.lingyuangou.ui.mine.adapter.SpinnerAdapter;
import com.ahxd.lingyuangou.utils.GlideApp;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.PayResult;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商家充值卡
 */
public class MerchantsTopUpActivity extends BaseActivity implements OnChangeItemClickListener {
    private static String TAG = MerchantsTopUpActivity.class.getSimpleName();
    private static final int SDK_PAY_FLAG = 0x701;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_user_img)
    ImageView ivUserImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_rebate)
    TextView tvRebate;
    @BindView(R.id.tv_integral)
    TextView tvIntegral;
    @BindView(R.id.btn_details)
    Button btnDetails;
    @BindView(R.id.sp_report_merchants)
    Spinner spReportMerchants;
    @BindView(R.id.rv_cards)
    RecyclerView rvCards;
    @BindView(R.id.tv_report_rest_money)
    TextView tvReportRestMoney;
    @BindView(R.id.tv_report_userd_money)
    TextView tvReportUserdMoney;
    @BindView(R.id.ll_report)
    LinearLayout llReport;
    @BindView(R.id.rb_online_pay_wallet)
    ImageView rbOnlinePayWallet;
    @BindView(R.id.iv_online_pay_wallet)
    ImageView ivOnlinePayWallet;
    @BindView(R.id.tv_online_pay_wallet_name)
    TextView tvOnlinePayWalletName;
    @BindView(R.id.tv_online_pay_wallet_money)
    TextView tvOnlinePayWalletMoney;
    @BindView(R.id.rb_online_pay_weixin)
    ImageView rbOnlinePayWeixin;
    @BindView(R.id.iv_icon_weixin)
    ImageView ivIconWeixin;
    @BindView(R.id.rl_online_pay_weixin)
    RelativeLayout rlOnlinePayWeixin;
    @BindView(R.id.rb_online_pay_zhifubao)
    ImageView rbOnlinePayZhifubao;
    @BindView(R.id.iv_icon_zhifubao)
    ImageView ivIconZhifubao;
    @BindView(R.id.rl_online_pay_zhifubao)
    RelativeLayout rlOnlinePayZhifubao;
    @BindView(R.id.ll_gift_gone)
    LinearLayout llGiftGone;
    @BindView(R.id.btn_report_top_up)
    TextView btnReportTopUp;
    @BindView(R.id.ll_shengyu)
    RelativeLayout llShengyu;
    @BindView(R.id.cb_fanli)
    CheckBox rbFanli;


    private PurchaseQualificatioCardAdapter adapter;
    private List<BusinessCardBean> cardList;

    private List<ShopBean> shopList;

    /**
     * 用户数据
     */
    private UserInfoBean userInfoBean;

    /**
     * shopId
     */
    private String shopId;

    private SpinnerAdapter spinnerAdapter;
    private List<String> sponerList;

    /**
     * 支付方式
     */
    private int payType = -1;

    /**
     * 商家卡Id
     */
    private String membercardId = "";

    /**
     * 商家卡
     */
    private BusinessCardBean businessCardBean;

    /**
     * 返利
     */
    private FanliBean fanliBean;

    /**
     * 是否使用返利  1使用 0不使用
     */
    private int isuseadfee = 0;

    private boolean payflag = false;

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
        btnReportTopUp.setEnabled(false);
        llReport.setVisibility(View.GONE);
        shopList = new ArrayList<>();
        sponerList = new ArrayList<>();
        setToolBarTitle("商家充值卡");
        cardList = new ArrayList<>();
        llShengyu.setVisibility(View.GONE);
        adapter = new PurchaseQualificatioCardAdapter(this, cardList);
        rvCards.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClickListener(this);
        rbFanli.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                if (checked) {
                    isuseadfee = 1;
                } else {
                    isuseadfee = 0;
                }


                //选择了商家卡 并且选择了支付方式
                if (ischeckedCard() && payflag) {
                    btnReportTopUp.setEnabled(true);
                    btnReportTopUp.setSelected(true);
                }
                //未选择支付方式
                else if (!payflag) {
                    //选中
                    if (checked) {
                        ///购卡金额大于可用余额
                        if (businessCardBean != null && businessCardBean.getNeedCash() > fanliBean.getFreeUserincomebalance()) {
                            btnReportTopUp.setEnabled(false);
                            btnReportTopUp.setSelected(false);
                        }
                        ///购卡金额西小于等于可用余额
                        else {
                            btnReportTopUp.setEnabled(true);
                            btnReportTopUp.setSelected(true);
                        }
                    }
                    //未选中
                    else {
                        btnReportTopUp.setEnabled(false);
                        btnReportTopUp.setSelected(false);
                    }
                }
            }
        });
        rvCards.setAdapter(adapter);

        spinnerAdapter = new SpinnerAdapter(this, sponerList);
        spReportMerchants.setAdapter(spinnerAdapter);
        spReportMerchants.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                shopId = shopList.get(i).getShopId();
                获取商家名下所有卡信息();
                btnReportTopUp.setSelected(false);
                btnReportTopUp.setEnabled(false);
                llReport.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        initExtras();
        if (userInfoBean != null) {
            获取商家列表信息();
            GlideApp.with(this).load(userInfoBean.getUserPhoto()).into(ivUserImg);
            tvName.setText(userInfoBean.getUserName());
            tvRebate.setText(userInfoBean.getUserIncome() + "元");
            tvIntegral.setText(userInfoBean.getUserScore());
        }

        choseWeixinPay();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_merchants_top_up;
    }

    @OnClick({R.id.btn_details, R.id.btn_report_top_up, R.id.rb_online_pay_weixin, R.id.rb_online_pay_zhifubao, R.id.rb_online_pay_wallet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_details:
                Intent intent = new Intent(this, BuyCardDetailActivity.class);
                intent.putExtra("userInfoBean", userInfoBean);
                startActivity(intent);
                break;
            case R.id.btn_report_top_up:
                购买商家卡之前的准备();
                break;
            case R.id.rb_online_pay_weixin:
                choseWeixinPay();
                break;
            case R.id.rb_online_pay_zhifubao:
                payflag = true;
                if (ischeckedCard()) {
                    btnReportTopUp.setEnabled(true);
                    btnReportTopUp.setSelected(true);
                }
                payType = Constant.PAY_TYPE_ALIPAY;
                rbOnlinePayWallet.setSelected(false);
                rbOnlinePayWeixin.setSelected(false);
                rbOnlinePayZhifubao.setSelected(true);
                break;
        }
    }

    /**
     * 选中微信支付
     */
    private void choseWeixinPay() {
        payflag = true;
        if (ischeckedCard()) {
            btnReportTopUp.setEnabled(true);
            btnReportTopUp.setSelected(true);
        }
        payType = Constant.PAY_TYPE_WX;
        rbOnlinePayWallet.setSelected(false);
        rbOnlinePayWeixin.setSelected(true);
        rbOnlinePayZhifubao.setSelected(false);
    }

    /**
     * 获取个人中心带过来的用户数据
     */
    private void initExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userInfoBean = (UserInfoBean) extras.get("userInfoBean");
        }
    }

    /**
     * 购买商家卡
     */
    private void 获取商家名下所有卡信息() {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("shopid", shopId);
        OkGo.<String>get(HostUrl.URL_UCERNTER_BUSINESS_INFORMATION_CARDS)
                .params(params)
                .execute(new MyStringCallBack(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            List<BusinessCardBean> tempList = JSON.parseArray(obj.optString("data").toString(), BusinessCardBean.class);
                            if (tempList != null) {
                                cardList.clear();
                                cardList.addAll(tempList);
                                if (cardList.size()>0){
                                    cardList.get(0).setIsselected(true);
                                    membercardId = cardList.get(0).getMemberCardid();
                                    businessCardBean = cardList.get(0);
                                    获取卡信息();
                                }
                                adapter.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, e.getMessage());
                        }
                    }
                });
    }

    /**
     * 获取商家信息
     */
    private void 获取商家列表信息() {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("shopid", userInfoBean.getUserId());
        OkGo.<String>get(HostUrl.URL_UCERNTER_BUSINESS_INFORMATION)
                .params(params)
                .execute(new MyStringCallBack(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            List<ShopBean> templist = JSON.parseArray(obj.optString("data").toString(), ShopBean.class);
                            if (templist != null && templist.size() > 0) {
                                shopId = templist.get(0).getShopId();
                                shopList.clear();
                                shopList.addAll(templist);
                                sponerList.clear();
                                for (int i = 0; i < shopList.size(); i++) {
                                    sponerList.add(i, shopList.get(i).getShopName());
                                }

                                spReportMerchants.setSelection(0);
                                spinnerAdapter.notifyDataSetChanged();
                                获取商家名下所有卡信息();
                            } else {
                                ToastUtils.showShort(MerchantsTopUpActivity.this, obj.optString("msg").toString());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, e.getMessage());
                        }
                    }
                });
    }

    @Override
    public void onItemClick(View view, int position, int lastPosition) {
        for (BusinessCardBean item : cardList) {
            item.setIsselected(false);
        }
        if (!membercardId.equals(cardList.get(position).getMemberCardid())) {
            rbFanli.setChecked(false);
            isuseadfee = 0;
            membercardId = cardList.get(position).getMemberCardid();
            businessCardBean = cardList.get(position);
            cardList.get(position).setIsselected(true);
            adapter.notifyItemChanged(position);
            adapter.notifyItemChanged(lastPosition);
            获取卡信息();
            if (payflag) {
                btnReportTopUp.setEnabled(true);
                btnReportTopUp.setSelected(true);
            }
        }

    }

    /**
     * 获取 卡信息 buyQualificationDisplay
     */
    private void 获取卡信息() {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("userid", userInfoBean.getUserId());
        params.put("shopid", shopId);
        params.put("membercardid", membercardId);
        OkGo.<String>get(HostUrl.URL_UCERNTER_BUYQUALIFICATIONDISPLAY)
                .params(params)
                .execute(new MyStringCallBack(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            List<FanliBean> tempList = JSON.parseArray(obj.optString("data").toString(), FanliBean.class);
                            if (tempList != null && tempList.size() > 0) {
                                fanliBean = tempList.get(0);
                                llReport.setVisibility(View.VISIBLE);
                                tvReportRestMoney.setText(fanliBean.getBQDuserincome() + "元");
                                tvReportUserdMoney.setText(fanliBean.getFreeUserincomebalance() + "元");
                                rbFanli.setChecked(true);
                            } else {
                                llReport.setVisibility(View.GONE);
                                ToastUtils.showShort(MerchantsTopUpActivity.this, obj.optString("msg").toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(TAG, e.getMessage());
                        }
                    }
                });
    }

    /**
     * 购买商家卡准备之前
     */
    private void 购买商家卡之前的准备() {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("userid", userInfoBean.getUserId());
        params.put("isuseadfee", isuseadfee);
        params.put("membercardid", membercardId);
        OkGo.<String>get(HostUrl.URL_UCERNTER_BUYUSERCARDREADY)
                .params(params)
                .execute(new MyStringCallBack(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            payReadyBean readyBean = JSON.parseObject(obj.optString("data").toString(), payReadyBean.class);
                            if (readyBean != null) {
                                //如果大于0，调用 api/pay/payparameter
                                if (readyBean.getBMCneedCash() > 0) {
                                    获取支付信息(readyBean);
                                }
                                //等于0调用buyQualificationComplete完成订单
                                else if (readyBean.getBMCneedCash() == 0) {
                                    购买商家卡完成(readyBean.getBMCrechargeOrderid());
                                }
                            } else {
                                ToastUtils.showShort(MerchantsTopUpActivity.this, obj.optString("msg").toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     *
     */
    private void 购买商家卡完成(String cardorderid) {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("userid", userInfoBean.getUserId());
        params.put("cardorderid", cardorderid);
        OkGo.<String>get(HostUrl.URL_UCERNTER_BUYUSERCARDCOMPLETE)
                .params(params)
                .execute(new MyStringCallBack(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            ToastUtils.showShort(MerchantsTopUpActivity.this, obj.optString("msg").toString());
//                            Intent intent = new Intent(MerchantsTopUpActivity.this, MainActivity.class);
//                            intent.putExtra("position", 3);
//                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    /**
     * 获取支付信息
     */
    private void 获取支付信息(payReadyBean readyBean) {
        HttpParams params = new HttpParams();
        params.put("paytype", payType);
        params.put("paycash", readyBean.getBMCneedCash());
//        params.put("paycash", 0.01);  这是调试的时候设置的最少的钱支付
        params.put("payorder", readyBean.getBMCrechargeOrderid());
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        OkGo.<String>get(HostUrl.URL_UCERNTER_PAYPARAMETER)
                .params(params)
                .execute(new MyStringCallBack(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            //微信支付
                            if (payType == Constant.PAY_TYPE_WX) {
                                showWeixinPay(obj.optJSONObject("data"));
                            }
                            //支付宝支付
                            else if (payType == Constant.PAY_TYPE_ALIPAY) {
                                showZhifubaoPay(obj.optString("data"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //微信支付
    private void showWeixinPay(JSONObject data) {
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
            SPUtils.put(this, Constant.SP_WEIXIN_PAY, true);
        }
    }

    public void showZhifubaoPay(String data) {
        final String orderInfo = data;   // 订单信息
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(MerchantsTopUpActivity.this);
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
                        ToastUtils.showShort(MerchantsTopUpActivity.this, "支付成功");
                        Intent intent = new Intent(MerchantsTopUpActivity.this, MainActivity.class);
                        intent.putExtra("position", 3);
                        startActivity(intent);
                        finish();
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort(MerchantsTopUpActivity.this, "取消支付");
                    } else {
                        ToastUtils.showShort(MerchantsTopUpActivity.this, "支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * 检查是否选择了商家卡
     */
    private boolean ischeckedCard() {
        for (BusinessCardBean bean : cardList) {
            if (bean.isIsselected()) {
                return true;
            }
        }
        return false;
    }
}
