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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.BusinessCardBean;
import com.ahxd.lingyuangou.bean.FanliBean;
import com.ahxd.lingyuangou.bean.PurchaseQualificationBean;
import com.ahxd.lingyuangou.bean.UserInfoBean;
import com.ahxd.lingyuangou.bean.payReadyBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.listener.OnChangeItemClickListener;
import com.ahxd.lingyuangou.ui.cart.activity.PayResultActivity;
import com.ahxd.lingyuangou.ui.mine.adapter.PurchaseQualificatioCardAdapter;
import com.ahxd.lingyuangou.utils.GlideApp;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.PayResult;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 购买资格
 */
public class PurchaseQualificatioActivity extends BaseActivity implements OnChangeItemClickListener {
    private static String TAG = PurchaseQualificatioActivity.class.getSimpleName();

    private static final int SDK_PAY_FLAG = 0x702;
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
    @BindView(R.id.tv_ids)
    TextView tvIds;
    @BindView(R.id.btn_details)
    Button btnDetails;
    @BindView(R.id.tv_merchant)
    TextView tvMerchant;
    @BindView(R.id.rv_cards)
    RecyclerView rvCards;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.tv_rest_money)
    TextView tvRestMoney;
    @BindView(R.id.tv_userd_money)
    TextView tvUserdMoney;
    @BindView(R.id.ll_chose_card)
    LinearLayout llChoseCard;
    @BindView(R.id.rb_online_pay_wallet)
    ImageView rbOnlinePayWallet;
    @BindView(R.id.iv_online_pay_wallet)
    ImageView ivOnlinePayWallet;
    @BindView(R.id.tv_online_pay_wallet_name)
    TextView tvOnlinePayWalletName;
    @BindView(R.id.tv_online_pay_wallet_money)
    TextView tvOnlinePayWalletMoney;
    @BindView(R.id.ll_shengyu)
    RelativeLayout llShengyu;
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
    @BindView(R.id.btn_buy)
    TextView btnBuy;


    private PurchaseQualificationBean qualificationBean;
    private PurchaseQualificatioCardAdapter adapter;
    private List<BusinessCardBean> list;

    /**
     * 用户数据
     */
    private UserInfoBean userInfoBean;

    /**
     * 支付方式
     */
    private int payType = -1;

    /**
     * 商家卡Id
     */
    private String membercardId = "";

    /**
     * 是否选择返利 0 fou 1 true
     */
    private int isuseadfee = 0;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        btnBuy.setEnabled(false);
        llShengyu.setVisibility(View.GONE);
        llChoseCard.setVisibility(View.GONE);
        list = new ArrayList<>();
        setToolBarTitle("购买资格");
        initExtras();
        adapter = new PurchaseQualificatioCardAdapter(this, list);
        rvCards.setLayoutManager(new LinearLayoutManager(this));
        rvCards.setAdapter(adapter);
        adapter.setOnClickListener(this);
        if (userInfoBean != null) {
            获取商家和卡信息();
            GlideApp.with(this).load(userInfoBean.getUserPhoto()).into(ivUserImg);
            tvIds.setText(userInfoBean.getUserId() + "");
            tvName.setText(userInfoBean.getUserName());
            tvRebate.setText(userInfoBean.getUserIncome() + "元");
            tvIntegral.setText(userInfoBean.getUserScore());
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_purchase_qualificatio;
    }

    @OnClick({R.id.btn_details, R.id.btn_pay, R.id.btn_buy, R.id.rb_online_pay_weixin, R.id.rb_online_pay_zhifubao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_details:
                Intent intent = new Intent(this, BuyCardDetailActivity.class);
                intent.putExtra("userInfoBean", userInfoBean);
                startActivity(intent);
                break;
            case R.id.btn_buy:
                购买商家卡之前的准备();
                break;
            case R.id.rb_online_pay_weixin:
                if (!membercardId.isEmpty()) {
                    btnBuy.setEnabled(true);
                    btnBuy.setSelected(true);
                }
                payType = Constant.PAY_TYPE_WX;
                rbOnlinePayWallet.setSelected(false);
                rbOnlinePayWeixin.setSelected(true);
                rbOnlinePayZhifubao.setSelected(false);
                break;
            case R.id.rb_online_pay_zhifubao:
                if (!membercardId.isEmpty()) {
                    btnBuy.setEnabled(true);
                    btnBuy.setSelected(true);
                }
                payType = Constant.PAY_TYPE_ALIPAY;
                rbOnlinePayWallet.setSelected(false);
                rbOnlinePayWeixin.setSelected(false);
                rbOnlinePayZhifubao.setSelected(true);
                break;
        }
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
     * 获取商家和卡信息
     */
    private void 获取商家和卡信息() {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("shopid", userInfoBean.getUserId());
        OkGo.<String>get(HostUrl.URL_UCERNTER_PURCHASE_QUALIFICATION)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            qualificationBean = JSON.parseObject(obj.optString("data").toString(), PurchaseQualificationBean.class);
                            if (qualificationBean != null && qualificationBean.getCards() != null) {
                                list.clear();
                                list.addAll(qualificationBean.getCards());
                                adapter.notifyDataSetChanged();
                                if (qualificationBean.getShops() != null) {
                                    tvMerchant.setText(qualificationBean.getShops().getShopName());
                                }
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
        for (BusinessCardBean item : list) {
            item.setIsselected(false);
        }
        membercardId = qualificationBean.getCards().get(position).getMemberCardid();
        list.get(position).setIsselected(true);
        adapter.notifyItemChanged(position);
        adapter.notifyItemChanged(lastPosition);
        获取卡信息(qualificationBean.getCards().get(position).getMemberCardid());
    }

    /**
     * 获取 卡信息 buyQualificationDisplay
     */
    private void 获取卡信息(String membercardid) {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("userid", userInfoBean.getUserId());
        params.put("shopid", qualificationBean.getShops().getShopId());
        params.put("membercardid", membercardid);
        OkGo.<String>get(HostUrl.URL_UCERNTER_BUYQUALIFICATIONDISPLAY)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            List<FanliBean> tempList = JSON.parseArray(obj.optString("data").toString(), FanliBean.class);
                            if (tempList != null && tempList.size() > 0) {
                                FanliBean bean = tempList.get(0);
                                llChoseCard.setVisibility(View.VISIBLE);
                                tvRestMoney.setText(bean.getBQDuserincome() + "元");
                                tvUserdMoney.setText(bean.getFreeUserincomebalance() + "元");
                            } else {
                                llChoseCard.setVisibility(View.GONE);
                                ToastUtils.showShort(PurchaseQualificatioActivity.this, obj.optString("msg").toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 购买资格卡准备之前
     */
    private void 购买商家卡之前的准备() {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("userid", userInfoBean.getUserId());
        params.put("isuseadfee", isuseadfee);
        params.put("membercardid", membercardId);
        OkGo.<String>get(HostUrl.URL_UCERNTER_BUYQUALIFICATIONREADY)
                .params(params)
                .execute(new StringCallback() {
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
                                    购买资格卡完成(readyBean.getBMCrechargeOrderid());
                                }
                            } else {
                                ToastUtils.showShort(PurchaseQualificatioActivity.this, obj.optString("msg").toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(TAG, e.getMessage());
                        }
                    }
                });
    }

    /**
     *
     */
    private void 购买资格卡完成(String cardorderid) {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("userid", userInfoBean.getUserId());
        params.put("cardorderid", cardorderid);
        OkGo.<String>get(HostUrl.URL_UCERNTER_BUYQUALIFICATIONCOMPLETE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            ToastUtils.showShort(PurchaseQualificatioActivity.this, obj.optString("msg").toString());
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
//        params.put("paycash", 0.01); //这是调试的时候设置的最少的钱支付
        params.put("payorder", readyBean.getBMCrechargeOrderid());
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        OkGo.<String>get(HostUrl.URL_UCERNTER_PAYPARAMETER)
                .params(params)
                .execute(new StringCallback() {
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
                PayTask alipay = new PayTask(PurchaseQualificatioActivity.this);
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
                        ToastUtils.showShort(PurchaseQualificatioActivity.this, "支付成功");
                        Intent intent = new Intent(PurchaseQualificatioActivity.this, PayResultActivity.class);
                        intent.putExtra("type", "");
                        startActivity(intent);
                        finish();
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort(PurchaseQualificatioActivity.this, "取消支付");
                    } else {
                        ToastUtils.showShort(PurchaseQualificatioActivity.this, "支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
}
