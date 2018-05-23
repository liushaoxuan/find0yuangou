package com.ahxd.lingyuangou.ui.cart.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.AddressBean;
import com.ahxd.lingyuangou.bean.OrderBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.cart.contract.IOnlinePayContract;
import com.ahxd.lingyuangou.ui.cart.presenter.OnlinePayPresenter;
import com.ahxd.lingyuangou.ui.mine.activity.AddressListActivity;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.PayResult;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.GoodsOrderView;
import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/11.
 */

public class OnlinePayActivity extends BaseActivity implements IOnlinePayContract.IOnlinePayView {

    @BindView(R.id.btn_online_pay_add_address)
    Button btnOnlinePayAddAddress;
    @BindView(R.id.tv_online_pay_receiver)
    TextView tvOnlinePayReceiver;
    @BindView(R.id.tv_online_pay_phone)
    TextView tvOnlinePayPhone;
    @BindView(R.id.tv_online_pay_address)
    TextView tvOnlinePayAddress;
    @BindView(R.id.ll_online_pay_address_container)
    LinearLayout llOnlinePayAddressContainer;
    @BindView(R.id.ll_online_pay_good_container)
    LinearLayout llOnlinePayGoodContainer;
    @BindView(R.id.sv_cart_online_pay_container)
    ScrollView svCartOnlinePayContainer;
    @BindView(R.id.tv_online_pay_total_money)
    TextView tvOnlinePayTotalMoney;
    @BindView(R.id.btn_online_pay)
    Button btnOnlinePay;
    @BindView(R.id.tv_online_pay_wallet_money)
    TextView tvOnlinePayWalletMoney;
    @BindView(R.id.rb_online_pay_weixin)
    ImageView rbOnlinePayWeixin;
    @BindView(R.id.rb_online_pay_zhifubao)
    ImageView rbOnlinePayZhifubao;
    @BindView(R.id.rb_online_pay_wallet)
    ImageView rbOnlinePayWallet;
    @BindView(R.id.iv_online_pay_address_right)
    ImageView ivOnlinePayAddressRight;
    @BindView(R.id.rb_huobi)
    RelativeLayout rbHuobi;
    @BindView(R.id.ll_gift_gone)
    LinearLayout llGiftGone;

    private JSONObject mOrderInfo;
    private String mAddressId;
    private int mPayType = 1; // 1余额2微信3支付宝
    private String mStartFrom;
    private String mGoodsId;
    private String mSpecIds;
    private String mBuyNum;
    private String type="";
    private boolean isEditing = false;

    private OnlinePayPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("支付");
        rbOnlinePayWallet.setSelected(true);
        mPayType = 1;
        //嵌套必须
        svCartOnlinePayContainer.smoothScrollTo(0, 0);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mStartFrom = getIntent().getStringExtra("start_from");
        if (null==getIntent().getStringExtra("type")){
            type ="" ;
        }else {
            type = getIntent().getStringExtra("type");
        }

        if(null!=type){
            if(type.equals("gift")){
                rbHuobi.setVisibility(View.VISIBLE);
                llGiftGone.setVisibility(View.GONE);
            }else {
                rbHuobi.setVisibility(View.GONE);
                llGiftGone.setVisibility(View.VISIBLE);
            }
        }else {
            rbHuobi.setVisibility(View.GONE);
            llGiftGone.setVisibility(View.VISIBLE);
        }

        switch (mStartFrom) {
            case "cart": // 购物车（一键支付）
            case "good": // 商品详情（立即购买）
                isEditing = true;
                break;
            case "order": // 订单列表支付
                isEditing = false;
                ivOnlinePayAddressRight.setVisibility(View.INVISIBLE);
                break;
        }
        try {
            mOrderInfo = new JSONObject(getIntent().getStringExtra("orderInfo"));
            mGoodsId = mOrderInfo.optJSONArray("goods").optJSONObject(0).optJSONArray("goods").optJSONObject(0).optString("goodsId");
            mSpecIds = mOrderInfo.optJSONArray("goods").optJSONObject(0).optJSONArray("goods").optJSONObject(0).optString("specIds");
            mBuyNum = mOrderInfo.optJSONArray("goods").optJSONObject(0).optJSONArray("goods").optJSONObject(0).optString("num");
            showAddress(mOrderInfo.optJSONObject("address"));
            showGoods(mOrderInfo.optJSONArray("goods"));
            showTotalMoney(mOrderInfo.optString("sum"));
            showWalletMoney(mOrderInfo.optString("userMoney"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mPresenter = new OnlinePayPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_online_pay;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_ADDRESS && resultCode == RESULT_OK) {
            AddressBean bean = (AddressBean) data.getSerializableExtra("addressBean");
            if (null != bean) {
                switch (mStartFrom) {
                    case "cart": // 购物车（一键支付）
                        btnOnlinePayAddAddress.setVisibility(View.GONE);
                        llOnlinePayAddressContainer.setVisibility(View.VISIBLE);
                        tvOnlinePayReceiver.setText(String.format("收件人：%s", bean.getUserName()));
                        tvOnlinePayPhone.setText(bean.getUserPhone());
                        tvOnlinePayAddress.setText(bean.getUserAddress());
                        mAddressId = bean.getAddressId();
                        if (type.equals("gift")){
                            mPresenter.submitGiftOrder(getOrderContent(), mAddressId);
                        }else {
                            mPresenter.submitOrder(getOrderContent(), mAddressId);
                        }


                        break;
                    case "good": // 商品详情（立即购买）
                        mAddressId = bean.getAddressId();
                        mPresenter.chooseAddressRefresh(mGoodsId, mSpecIds, mBuyNum, mAddressId);
                        break;
                }
            }
        }
    }

    @OnClick({R.id.btn_online_pay_add_address, R.id.ll_online_pay_address_container, R.id.btn_online_pay,
            R.id.rb_online_pay_weixin, R.id.rb_online_pay_zhifubao, R.id.rb_online_pay_wallet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_online_pay_add_address:
                Intent addAddress = new Intent(this, AddressListActivity.class);
                addAddress.putExtra("start_from", "online_pay");
                startActivityForResult(addAddress, Constant.REQ_ADDRESS);
                break;
            case R.id.ll_online_pay_address_container:
                if (isEditing) {
                    Intent chooseAddress = new Intent(this, AddressListActivity.class);
                    chooseAddress.putExtra("start_from", "online_pay");
                    startActivityForResult(chooseAddress, Constant.REQ_ADDRESS);
                }
                break;
            case R.id.btn_online_pay:
                switch (mStartFrom) {
                    case "cart": // 购物车（一键支付）
                        if(null!=type){
                            if (type.equals("gift")){
                                mPresenter.payGiftOrder(getOrderContent(), mAddressId);
                            }else {
                                mPresenter.payOnlineOrder(getOrderContent(), mAddressId, mPayType);
                            }
                        }else {
                            mPresenter.payOnlineOrder(getOrderContent(), mAddressId, mPayType);
                        }


                        break;
                    case "good": // 商品详情（立即购买）
                        JSONObject good = mOrderInfo.optJSONArray("goods").optJSONObject(0);
                        String goodsId = good.optJSONArray("goods").optJSONObject(0).optString("goodsId");
                        String specIds = good.optJSONArray("goods").optJSONObject(0).optString("specIds");
                        String buyNum = good.optString("num");

                        if (type.equals("gift")){
                            mPresenter.payGiftRightNow(goodsId, specIds, buyNum, mAddressId, mPayType, getContent());
                        }else {
                            mPresenter.payRightNow(goodsId, specIds, buyNum, mAddressId, mPayType, getContent());
                        }
                        break;
                    case "order": // 订单列表支付
                        if (null != mOrderInfo) {
                            if (type.equals("gift")){
//                                mPresenter.payGiftOrder(getOrderContent(), mAddressId);
                            }else {
                                mPresenter.payOrderList(mPayType, mOrderInfo.optString("orderId"), mOrderInfo.optString("sum"));
                            }
                        }
                        break;
                }
                break;
            case R.id.rb_online_pay_weixin:
                mPayType = 2;
                rbOnlinePayWallet.setSelected(false);
                rbOnlinePayWeixin.setSelected(true);
                rbOnlinePayZhifubao.setSelected(false);
                break;
            case R.id.rb_online_pay_zhifubao:
                mPayType = 3;
                rbOnlinePayWallet.setSelected(false);
                rbOnlinePayWeixin.setSelected(false);
                rbOnlinePayZhifubao.setSelected(true);
                break;
            case R.id.rb_online_pay_wallet:
                mPayType = 1;
                rbOnlinePayWallet.setSelected(true);
                rbOnlinePayWeixin.setSelected(false);
                rbOnlinePayZhifubao.setSelected(false);
                break;
        }
    }

    @Override
    public void showSubmitGiftOrder(JSONObject data) {
        mOrderInfo = data;
        showAddress(mOrderInfo.optJSONObject("address"));
        showGoods(mOrderInfo.optJSONArray("goods"));
        showTotalMoney(mOrderInfo.optString("sum"));
        showWalletMoney(mOrderInfo.optString("userMoney"));
    }

    @Override
    public void showPayGiftOrder(String data) {
        ToastUtils.showShort(this, data);
        Intent intent = new Intent(OnlinePayActivity.this, PayResultActivity.class);
        intent.putExtra("type",type);
        startActivity(intent);
        finish();
    }

    @Override
    public void showSubmitOrder(JSONObject data) {
        mOrderInfo = data;
        showAddress(mOrderInfo.optJSONObject("address"));
        showGoods(mOrderInfo.optJSONArray("goods"));
        showTotalMoney(mOrderInfo.optString("sum"));
        showWalletMoney(mOrderInfo.optString("userMoney"));
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

    private static final int SDK_PAY_FLAG = 0x701;

    @Override
    public void showZhifubaoPay(String data) {
        final String orderInfo = data;   // 订单信息
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(OnlinePayActivity.this);
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
        Intent intent = new Intent(OnlinePayActivity.this, PayResultActivity.class);
        intent.putExtra("type",type);
        startActivity(intent);
        finish();
    }

    @Override
    public void showOrderDetail(JSONObject data) {
        if (null != data) {
            mOrderInfo = data;
            showAddress(mOrderInfo.optJSONObject("address"));
            showGoods(mOrderInfo.optJSONArray("goods"));
            showTotalMoney(mOrderInfo.optString("sum"));
            showWalletMoney(mOrderInfo.optString("userMoney"));
        }
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
                        ToastUtils.showShort(OnlinePayActivity.this, "支付成功");
                        Intent intent = new Intent(OnlinePayActivity.this, PayResultActivity.class);
                        intent.putExtra("type",type);
                        startActivity(intent);
                        finish();
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort(OnlinePayActivity.this, "取消支付");
                    } else {
                        ToastUtils.showShort(OnlinePayActivity.this, "支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    private void showAddress(JSONObject address) {
        if (address == null || TextUtils.isEmpty(address.toString())) {
            btnOnlinePayAddAddress.setVisibility(View.VISIBLE);
            llOnlinePayAddressContainer.setVisibility(View.GONE);
            mAddressId = null;
        } else {
            btnOnlinePayAddAddress.setVisibility(View.GONE);
            llOnlinePayAddressContainer.setVisibility(View.VISIBLE);
            tvOnlinePayReceiver.setText(String.format("收件人：%s", address.optString("userName")));
            tvOnlinePayPhone.setText(address.optString("userPhone"));
            tvOnlinePayAddress.setText(address.optString("province") + address.optString("city")
                    + address.optString("area") + address.optString("userAddress"));
            mAddressId = address.optString("addressId");
        }
    }

    private void showGoods(JSONArray goods) {
        if (null != goods) {
            GoodsOrderView view;
            llOnlinePayGoodContainer.removeAllViews();
            for (int i = 0; i < goods.length(); i++) {
                view = new GoodsOrderView(this, isEditing);
                view.setShopData(goods.optJSONObject(i));
                llOnlinePayGoodContainer.addView(view);
            }
        }
    }

    private void showTotalMoney(String totalMoney) {
        tvOnlinePayTotalMoney.setText(String.format("总计：%s元", totalMoney));
    }

    private void showWalletMoney(String userMoney) {
        tvOnlinePayWalletMoney.setText(String.format("(剩余：%s元)", userMoney));
    }

    private String getOrderContent() {
        StringBuilder content = new StringBuilder();
        JSONArray goods = mOrderInfo.optJSONArray("goods");
        for (int i = 0; i < goods.length(); i++) {
            String remark = ((EditText) llOnlinePayGoodContainer.getChildAt(i).getTag()).getText().toString().trim();
            for (int j = 0; j < goods.optJSONObject(i).optJSONArray("goods").length(); j++) {
                content.append(goods.optJSONObject(i).optJSONArray("goods").optJSONObject(j).optString("cartId"))
                        .append(":")
                        .append(goods.optJSONObject(i).optJSONArray("goods").optJSONObject(j).optString("num"))
                        .append(":")
                        .append(remark)
                        .append(",");
            }
        }
        L.e(content.toString());
        return content.toString();
    }

    private String getContent() {
        return ((EditText) llOnlinePayGoodContainer.getChildAt(0).getTag()).getText().toString().trim();
    }

}
