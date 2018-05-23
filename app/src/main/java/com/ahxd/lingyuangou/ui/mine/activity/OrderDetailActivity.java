package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.cart.activity.OnlinePayActivity;
import com.ahxd.lingyuangou.ui.home.activity.GoodDetailActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IOrderDetailContract;
import com.ahxd.lingyuangou.ui.mine.presenter.OrderDetailPresenter;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.OrderGoodView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import butterknife.BindInt;
import butterknife.BindView;

/**
 * Created by Mao Zhendong on 2018/1/15.
 */

public class OrderDetailActivity extends BaseActivity implements IOrderDetailContract.IOrderDetailView {

    @BindView(R.id.tv_order_detail_receiver)
    TextView tvOrderDetailReceiver;
    @BindView(R.id.tv_order_detail_phone)
    TextView tvOrderDetailPhone;
    @BindView(R.id.tv_order_detail_address)
    TextView tvOrderDetailAddress;
    @BindView(R.id.tv_order_detail_tips)
    TextView tvOrderDetailTips;
    @BindView(R.id.tv_order_detail_total_money)
    TextView tvOrderDetailTotalMoney;
    @BindView(R.id.tv_order_detail_freight)
    TextView tvOrderDetailFreight;
    @BindView(R.id.tv_order_detail_status)
    TextView tvOrderDetailStatus;
    @BindView(R.id.ll_order_detail_goods_container)
    LinearLayout llOrderDetailGoodsContainer;
    @BindView(R.id.btn_order_detail_button)
    TextView btnOrderDetailButton;
    @BindView(R.id.btn_order_detail_button1)
    TextView btnOrderDetailButton1;
    @BindView(R.id.tv_order_detail_total_price)
    TextView tvOrderDetailTotalPrice;
    @BindView(R.id.tv_order_detail_orderid)
    TextView tvOrderDetailOrderid;
    @BindView(R.id.tv_order_detail_shop_name)
    TextView tvOrderDetailShopName;
    @BindView(R.id.tv_order_detail_shop_phone)
    TextView tvOrderDetailShopPhone;
    @BindView(R.id.tv_order_detail_ispay)
    TextView tvOrderDetailIsPay;
    @BindView(R.id.tv_order_detail_express)
    TextView tvOrderDetailExpress;
    @BindView(R.id.ll_order_detail_address_container)
    LinearLayout llOrderDetailAddressContainer;

    private OrderDetailPresenter mPresenter;
    private String mOrderId;
    private String type;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("我的订单");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mOrderId = getIntent().getStringExtra("orderId");
        type = getIntent().getStringExtra("type");
        mPresenter = new OrderDetailPresenter(this);
        if(type.equals("gift")){
            mPresenter.getGiftOrderDetail(mOrderId);
        }else {
            mPresenter.getOrderDetail(mOrderId);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void showOrderDetail(JSONObject data) {
        if (null != data) {
            final JSONObject info = data.optJSONObject("info");
            tvOrderDetailIsPay.setText(info.optInt("isPay") == 0 ? "买家未付款" : "买家已付款");
            tvOrderDetailReceiver.setText(info.optString("userName"));
            tvOrderDetailPhone.setText(info.optString("userPhone"));
            tvOrderDetailAddress.setText(info.optString("userAddress"));
            if (TextUtils.isEmpty(info.optString("userAddress"))) {
                llOrderDetailAddressContainer.setVisibility(View.GONE);
            } else {
                llOrderDetailAddressContainer.setVisibility(View.VISIBLE);
            }
            tvOrderDetailOrderid.setText(String.format("订单号：%s", info.optString("orderNo")));
            tvOrderDetailShopName.setText(String.format("商家：%s", info.optString("shopName")));
            tvOrderDetailShopPhone.setText(String.format("商家电话：%s", info.optString("shopTel")));
            tvOrderDetailStatus.setText(String.format("订单状态：%s", info.optString("orderStatusName")));
            tvOrderDetailTotalMoney.setText(String.format("订单金额(含邮费)：%s", info.optString("totalMoney")));
            tvOrderDetailFreight.setText(String.format("订单运费：%s", info.optString("deliverMoney")));
            tvOrderDetailTotalPrice.setText(String.format("付款：%s元", info.optString("realTotalMoney")));
            tvOrderDetailTips.setText(String.format("订单备注：%s", info.optString("orderRemarks")));
            if (TextUtils.isEmpty(info.optString("expressName")) || "null".equals(info.optString("expressName"))) {
                tvOrderDetailExpress.setText(String.format("物流信息：%s", "暂无物流信息"));
            } else {
                tvOrderDetailExpress.setText(String.format("物流信息：%s,单号：%s", info.optString("expressName"), info.optString("expressNo")));
            }
            switch (info.optInt("orderStatus")) {
                case -2:
                    btnOrderDetailButton.setVisibility(View.VISIBLE);
                    btnOrderDetailButton.setText("立即付款");
                    btnOrderDetailButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(type.equals("gift")){
                                mPresenter.getGiftOrderPayInfo(mOrderId);
                            }else {
                                mPresenter.getOrderPayInfo(mOrderId);
                            }
                        }
                    });
                    btnOrderDetailButton1.setVisibility(View.VISIBLE);
                    btnOrderDetailButton1.setText("取消订单");
                    btnOrderDetailButton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(type.equals("gift")){
                                mPresenter.onGiftCancelOrder(mOrderId, null);
                            }else {
                                mPresenter.onCancelOrder(mOrderId, null);
                            }
                        }
                    });
                    break;
                case 1:
                    btnOrderDetailButton.setVisibility(View.VISIBLE);
                    btnOrderDetailButton.setText("确认收货");
                    btnOrderDetailButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(type.equals("gift")){
                                mPresenter.onGiftConfirmReceiver(mOrderId);
                            }else {
                                mPresenter.onConfirmReceiver(mOrderId);
                            }

                        }
                    });
                    btnOrderDetailButton1.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    if (info.optInt("isAppraise") == 0) {
                        if(type.equals("gift")){
                            btnOrderDetailButton.setVisibility(View.INVISIBLE);
                        }else {
                            btnOrderDetailButton.setVisibility(View.VISIBLE);
                        }
                        btnOrderDetailButton.setText("立即评价");
                        btnOrderDetailButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(OrderDetailActivity.this, EvaluateActivity.class);
                                intent.putExtra("shopId", info.optString("shopId"));
                                intent.putExtra("orderId", info.optString("orderId"));
                                intent.putExtra("shopImg", info.optString("shopImg"));
                                intent.putExtra("shopName", info.optString("shopName"));
                                startActivityForResult(intent, Constant.REQ_EVALUATE);
                            }
                        });
                        btnOrderDetailButton1.setVisibility(View.INVISIBLE);
                    } else {
                        btnOrderDetailButton.setVisibility(View.INVISIBLE);
                        btnOrderDetailButton1.setVisibility(View.INVISIBLE);
                    }
                    break;
                case 0:
                    btnOrderDetailButton.setVisibility(View.VISIBLE);
                    btnOrderDetailButton.setText("取消订单");
                    btnOrderDetailButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(type.equals("gift")){
                                mPresenter.onGiftCancelOrder(mOrderId, null);
                            }else {
                                mPresenter.onCancelOrder(mOrderId, null);
                            }

                        }
                    });
                    btnOrderDetailButton1.setVisibility(View.INVISIBLE);
                    break;
                default:
                    btnOrderDetailButton.setVisibility(View.INVISIBLE);
                    btnOrderDetailButton1.setVisibility(View.INVISIBLE);
                    break;
            }

            JSONArray goods = data.optJSONArray("goods");
            OrderGoodView view;
            llOrderDetailGoodsContainer.removeAllViews();
            for (int i = 0; i < goods.length(); i++) {
                view = new OrderGoodView(this);
                view.setData(goods.optJSONObject(i));
                llOrderDetailGoodsContainer.addView(view);
            }
        }
    }

    @Override
    public void showGiftOrderDetail(JSONObject data) {
        if (null != data) {
            final JSONObject info = data.optJSONObject("info");
            tvOrderDetailIsPay.setText(info.optInt("isPay") == 0 ? "买家未付款" : "买家已付款");
            tvOrderDetailReceiver.setText(info.optString("userName"));
            tvOrderDetailPhone.setText(info.optString("userPhone"));
            tvOrderDetailAddress.setText(info.optString("userAddress"));
            if (TextUtils.isEmpty(info.optString("userAddress"))) {
                llOrderDetailAddressContainer.setVisibility(View.GONE);
            } else {
                llOrderDetailAddressContainer.setVisibility(View.VISIBLE);
            }
            tvOrderDetailOrderid.setText(String.format("订单号：%s", info.optString("orderNo")));
            tvOrderDetailShopName.setText(String.format("商家：%s", info.optString("shopName")));
            tvOrderDetailShopPhone.setText(String.format("商家电话：%s", info.optString("shopTel")));
            tvOrderDetailStatus.setText(String.format("订单状态：%s", info.optString("orderStatusName")));
            tvOrderDetailTotalMoney.setText(String.format("订单金额(含邮费)：%s", info.optString("totalMoney")));
            tvOrderDetailFreight.setText(String.format("订单运费：%s", info.optString("deliverMoney")));
            tvOrderDetailTotalPrice.setText(String.format("付款：%s元", info.optString("realTotalMoney")));
            tvOrderDetailTips.setText(String.format("订单备注：%s", info.optString("orderRemarks")));
            if (TextUtils.isEmpty(info.optString("expressName")) || "null".equals(info.optString("expressName"))) {
                tvOrderDetailExpress.setText(String.format("物流信息：%s", "暂无物流信息"));
            } else {
                tvOrderDetailExpress.setText(String.format("物流信息：%s,单号：%s", info.optString("expressName"), info.optString("expressNo")));
            }
            switch (info.optInt("orderStatus")) {
                case -2:
                    btnOrderDetailButton.setVisibility(View.VISIBLE);
                    btnOrderDetailButton.setText("立即付款");
                    btnOrderDetailButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(type.equals("gift")){
                                mPresenter.getGiftOrderPayInfo(mOrderId);
                            }else {
                                mPresenter.getOrderPayInfo(mOrderId);
                            }
                        }
                    });
                    btnOrderDetailButton1.setVisibility(View.VISIBLE);
                    btnOrderDetailButton1.setText("取消订单");
                    btnOrderDetailButton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(type.equals("gift")){
                                mPresenter.onGiftCancelOrder(mOrderId, null);
                            }else {
                                mPresenter.onCancelOrder(mOrderId, null);
                            }
                        }
                    });
                    break;
                case 1:
                    btnOrderDetailButton.setVisibility(View.VISIBLE);
                    btnOrderDetailButton.setText("确认收货");
                    btnOrderDetailButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(type.equals("gift")){
                                mPresenter.onGiftConfirmReceiver(mOrderId);
                            }else {
                                mPresenter.onConfirmReceiver(mOrderId);
                            }

                        }
                    });
                    btnOrderDetailButton1.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    if (info.optInt("isAppraise") == 0) {
                        if(type.equals("gift")){
                            btnOrderDetailButton.setVisibility(View.INVISIBLE);
                        }else {
                            btnOrderDetailButton.setVisibility(View.VISIBLE);
                        }
                        btnOrderDetailButton.setText("立即评价");
                        btnOrderDetailButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(OrderDetailActivity.this, EvaluateActivity.class);
                                intent.putExtra("shopId", info.optString("shopId"));
                                intent.putExtra("orderId", info.optString("orderId"));
                                intent.putExtra("shopImg", info.optString("shopImg"));
                                intent.putExtra("shopName", info.optString("shopName"));
                                startActivityForResult(intent, Constant.REQ_EVALUATE);
                            }
                        });
                        btnOrderDetailButton1.setVisibility(View.INVISIBLE);
                    } else {
                        btnOrderDetailButton.setVisibility(View.INVISIBLE);
                        btnOrderDetailButton1.setVisibility(View.INVISIBLE);
                    }
                    break;
                case 0:
                    btnOrderDetailButton.setVisibility(View.VISIBLE);
                    btnOrderDetailButton.setText("取消订单");
                    btnOrderDetailButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(type.equals("gift")){
                                mPresenter.onGiftCancelOrder(mOrderId, null);
                            }else {
                                mPresenter.onCancelOrder(mOrderId, null);
                            }

                        }
                    });
                    btnOrderDetailButton1.setVisibility(View.INVISIBLE);
                    break;
                default:
                    btnOrderDetailButton.setVisibility(View.INVISIBLE);
                    btnOrderDetailButton1.setVisibility(View.INVISIBLE);
                    break;
            }

            JSONArray goods = data.optJSONArray("goods");
            OrderGoodView view;
            llOrderDetailGoodsContainer.removeAllViews();
            for (int i = 0; i < goods.length(); i++) {
                view = new OrderGoodView(this);
                view.setData(goods.optJSONObject(i));
                llOrderDetailGoodsContainer.addView(view);
            }
        }
    }

    @Override
    public void showCancelOrder(String msg) {
        ToastUtils.showShort(this, msg);
        if(type.equals("gift")){
            mPresenter.getGiftOrderDetail(mOrderId);
        }else {
            mPresenter.getOrderDetail(mOrderId);
        }

    }

    @Override
    public void showGiftCancelOrder(String msg) {
        ToastUtils.showShort(this, msg);
        if(type.equals("gift")){
            mPresenter.getGiftOrderDetail(mOrderId);
        }else {
            mPresenter.getOrderDetail(mOrderId);
        }

    }

    @Override
    public void showOrderPayDetail(JSONObject data) {
        if (null != data) {
            Intent intent = new Intent(this, OnlinePayActivity.class);
            intent.putExtra("orderInfo", data.toString());
            intent.putExtra("start_from", "order");
            startActivityForResult(intent, Constant.REQ_PAY);
        }
    }

    @Override
    public void showGiftOrderPayDetail(JSONObject data) {
        if (null != data) {
            Intent intent = new Intent(this, OnlinePayActivity.class);
            intent.putExtra("orderInfo", data.toString());
            intent.putExtra("start_from", "order");
            if(type.equals("gift")){
                intent.putExtra("type", "gift");
            }else {
                intent.putExtra("type", "order");
            }
            startActivityForResult(intent, Constant.REQ_PAY);
        }
    }

    @Override
    public void showConfirmReceiver(String msg) {
        ToastUtils.showShort(this, msg);
        if(type.equals("gift")){
            mPresenter.getGiftOrderDetail(mOrderId);
        }else {
            mPresenter.getOrderDetail(mOrderId);
        }
    }

    @Override
    public void showGiftConfirmReceiver(String msg) {
        ToastUtils.showShort(this, msg);
        if(type.equals("gift")){
            mPresenter.getGiftOrderDetail(mOrderId);
        }else {
            mPresenter.getOrderDetail(mOrderId);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_EVALUATE && resultCode == RESULT_OK) {
            if(type.equals("gift")){
                mPresenter.getGiftOrderDetail(mOrderId);
            }else {
                mPresenter.getOrderDetail(mOrderId);
            }
        }
    }
}
