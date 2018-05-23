package com.ahxd.lingyuangou.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mao Zhendong on 2018/1/11.
 */

public class GoodsOrderView extends RelativeLayout {

    @BindView(R.id.tv_goods_order_shop_name)
    TextView tvGoodsOrderShopName;
    @BindView(R.id.ll_goods_order_goods_container)
    LinearLayout llGoodsOrderGoodsContainer;
    @BindView(R.id.tv_goods_order_express_fee)
    TextView tvGoodsOrderExpressFee;
    @BindView(R.id.et_goods_order_remark)
    EditText etGoodsOrderRemark;
    @BindView(R.id.tv_goods_order_total_money)
    TextView tvGoodsOrderTotalMoney;
    @BindView(R.id.tv_goods_order_good_num)
    TextView tvGoodsOrderGoodNum;


    private Context mContext;
    private boolean isEditing = false;

    public GoodsOrderView(Context context, boolean isEditing) {
        super(context);
        this.mContext = context;
        this.isEditing = isEditing;
        LayoutInflater.from(context).inflate(R.layout.view_goods_order, this, true);
        ButterKnife.bind(this);
    }

    public void setShopData(JSONObject data) {
        tvGoodsOrderShopName.setText(data.optString("shopName"));
        tvGoodsOrderExpressFee.setText(data.optDouble("freight") <= 0 ?
                "包邮" : String.format(Locale.CHINA, "￥%.2f元", (float) data.optDouble("freight")));
        tvGoodsOrderTotalMoney.setText(String.format(Locale.CHINA, "￥%.2f元", (float) data.optDouble("sum")));
        tvGoodsOrderGoodNum.setText(String.format("共%s件商品", data.optString("num")));
        JSONArray goods = data.optJSONArray("goods");
        GoodsOrderGoodView view;
        for (int i = 0; i < goods.length(); i++) {
            view = new GoodsOrderGoodView(mContext);
            view.setGoodData(goods.optJSONObject(i));
            llGoodsOrderGoodsContainer.addView(view);
        }
        this.setTag(etGoodsOrderRemark);
        if (isEditing) {
            etGoodsOrderRemark.setEnabled(true);
            etGoodsOrderRemark.setFocusable(true);
        } else {
            etGoodsOrderRemark.setEnabled(false);
            etGoodsOrderRemark.setText(data.optString("orderRemarks"));
        }
    }

    public class GoodsOrderGoodView extends RelativeLayout {

        @BindView(R.id.iv_goods_order_good_image)
        ImageView ivGoodsOrderGoodImage;
        @BindView(R.id.tv_goods_order_good_price)
        TextView tvGoodsOrderGoodPrice;
        @BindView(R.id.iv_goods_order_good_name)
        TextView ivGoodsOrderGoodName;
        @BindView(R.id.iv_goods_order_good_num)
        TextView ivGoodsOrderGoodNum;
        @BindView(R.id.iv_goods_order_good_spec)
        TextView ivGoodsOrderGoodSpec;

        public GoodsOrderGoodView(final Context context) {
            super(context);
            LayoutInflater.from(context).inflate(R.layout.view_goods_order_good, this, true);
            ButterKnife.bind(this);
        }

        public void setGoodData(JSONObject good) {
            ivGoodsOrderGoodName.setText(good.optString("goodsName"));
            tvGoodsOrderGoodPrice.setText(String.format("￥%s", good.optString("shopPrice")));
            Glide.with(mContext).load(good.optString("goodsImg")).into(ivGoodsOrderGoodImage);
            ivGoodsOrderGoodNum.setText(String.format("数量：%s", good.optString("num")));
            ivGoodsOrderGoodSpec.setText(String.format("规格：%s", good.optString("spec")));
        }

    }

}
