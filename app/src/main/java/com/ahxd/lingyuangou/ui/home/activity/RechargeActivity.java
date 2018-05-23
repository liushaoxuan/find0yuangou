package com.ahxd.lingyuangou.ui.home.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.RechargeBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.home.adapter.RechargeNumListAdapter;
import com.ahxd.lingyuangou.ui.home.contract.IRechargeContract;
import com.ahxd.lingyuangou.ui.home.presenter.RechargePresenter;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.PayResult;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/9.
 */

public class RechargeActivity extends BaseActivity implements IRechargeContract.IRechargeView {

    @BindView(R.id.lv_recharge_num)
    ListView lvRechargeNum;
    @BindView(R.id.rb_recharge_zhifubao)
    ImageView rbRechargeZhifubao;
    @BindView(R.id.rb_recharge_weixin)
    ImageView rbRechargeWeixin;
    @BindView(R.id.btn_recharge_pay)
    Button btnRechargePay;

    private RechargePresenter mPresenter;
    private RechargeNumListAdapter mAdapter;
    private int mPayType = 1; // 1支付宝2微信

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("充值");
        mAdapter = new RechargeNumListAdapter(this);
        lvRechargeNum.setAdapter(mAdapter);
        rbRechargeZhifubao.setSelected(true);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter = new RechargePresenter(this);
        mPresenter.getRechargeNumList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getRechargeNumList();
    }

    @Override
    public void showRechargeList(List<RechargeBean> list) {
        mAdapter.setData(list);
    }

    private static final int SDK_PAY_FLAG = 0x699;

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
                        ToastUtils.showShort(RechargeActivity.this, "支付成功");
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort(RechargeActivity.this, "取消支付");
                    } else {
                        ToastUtils.showShort(RechargeActivity.this, "支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    public void showZhifubaoPay(String data) {
        final String orderInfo = data;   // 订单信息
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(RechargeActivity.this);
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

    @OnClick({R.id.rb_recharge_zhifubao, R.id.rb_recharge_weixin, R.id.btn_recharge_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_recharge_zhifubao:
                mPayType = 1;
                rbRechargeZhifubao.setSelected(true);
                rbRechargeWeixin.setSelected(false);
                break;
            case R.id.rb_recharge_weixin:
                mPayType = 2;
                rbRechargeZhifubao.setSelected(false);
                rbRechargeWeixin.setSelected(true);
                break;
            case R.id.btn_recharge_pay:
                if (mAdapter.getData() != null) {
                    mPresenter.recharge(mPayType, mAdapter.getData().getFieldValue());
                } else {
                    ToastUtils.showShort(this, "请选择充值金额!");
                }
                break;
        }
    }

}
