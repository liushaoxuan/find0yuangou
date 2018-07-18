package com.ahxd.lingyuangou.widget;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.OrderBean;
import com.ahxd.lingyuangou.ui.home.activity.GoodDetailActivity;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mao on 2018/1/14.
 * 订单页中的商品View
 */

public class OrderGoodView extends RelativeLayout {

    @BindView(R.id.iv_order_good_icon)
    ImageView ivOrderGoodIcon;
    @BindView(R.id.tv_order_good_sale_num)
    TextView tvOrderGoodSaleNum;
    @BindView(R.id.tv_order_good_now_price)
    TextView tvOrderGoodNowPrice;
    @BindView(R.id.tv_order_good_old_price)
    TextView tvOrderGoodOldPrice;
    @BindView(R.id.tv_order_good_name)
    TextView tvOrderGoodName;
    @BindView(R.id.tv_order_good_tips)
    TextView tvOrderGoodTips;
    @BindView(R.id.tv_order_good_return_price)
    TextView tvOrderGoodReturnPrice;
    private Context mContext;

    public OrderGoodView(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_order_good, this, true);
        ButterKnife.bind(this);
    }

    public void setData(final OrderBean.GoodsBean bean) {
        if (null != bean) {
            tvOrderGoodName.setText(bean.getGoodsName());
            tvOrderGoodTips.setText(bean.getGoodsSpecNames());
            tvOrderGoodNowPrice.setText(String.format("￥%s", bean.getGoodsPrice()));
            if (null==bean.getReturnScore()){
                tvOrderGoodReturnPrice.setVisibility(GONE);
            }
            tvOrderGoodReturnPrice.setText(bean.getReturnScore());
            tvOrderGoodOldPrice.setVisibility(GONE);
            tvOrderGoodSaleNum.setText(String.format("数量:%s", bean.getGoodsNum()));
//            tvOrderGoodOldPrice.getPaint().setAntiAlias(true);
//            tvOrderGoodOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            Glide.with(mContext).load(bean.getGoodsImg()).into(ivOrderGoodIcon);
        }
    }


    public void setData(final JSONObject data) {
        if (null != data) {
            tvOrderGoodName.setText(data.optString("goodsName"));
            tvOrderGoodTips.setText(data.optString("goodsSpecNames"));
            tvOrderGoodNowPrice.setText(String.format("￥%s", data.optString("goodsPrice")));
            tvOrderGoodReturnPrice.setText(data.optString("returnScore"));
            if (data.optString("returnScore").equals("")){
                tvOrderGoodReturnPrice.setVisibility(GONE);
            }
            tvOrderGoodOldPrice.setVisibility(GONE);
            tvOrderGoodSaleNum.setText(String.format("数量:%s", data.optString("goodsNum")));
//            tvOrderGoodOldPrice.getPaint().setAntiAlias(true);
//            tvOrderGoodOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            Glide.with(mContext).load(data.optString("goodsImg")).into(ivOrderGoodIcon);
            setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.optInt("goodsId") != 0) {
                        Intent intent = new Intent(mContext, GoodDetailActivity.class);
                        intent.putExtra("goodsName", data.optString("goodsName"));
                        intent.putExtra("goodsId", data.optString("goodsId"));
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }
}
