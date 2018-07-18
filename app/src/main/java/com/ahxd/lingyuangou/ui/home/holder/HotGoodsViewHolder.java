package com.ahxd.lingyuangou.ui.home.holder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeGoodBean;
import com.ahxd.lingyuangou.ui.home.activity.GoodDetailActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sxliu on 2018/6/28 22:48
 * E-mail Address 2587294424@qq.com
 * 首页热门商品ViewHolder
 */

public class HotGoodsViewHolder extends HomeBaseViewHolder<HomeGoodBean> {
    @BindView(R.id.iv_home_goods_image)
    ImageView ivHomeGoodsImage;
    @BindView(R.id.iv_home_goods_type)
    ImageView ivHomeGoodsType;
    @BindView(R.id.tv_home_goods_name)
    TextView tvHomeGoodsName;
    @BindView(R.id.tv_home_goods_tips)
    TextView tvHomeGoodsTips;
    @BindView(R.id.tv_home_goods_price)
    TextView tvHomeGoodsPrice;
    @BindView(R.id.tv_home_goods_back_num)
    TextView tvHomeGoodsBackNum;

    public HotGoodsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(List<HomeGoodBean> t, int position) {
        final HomeGoodBean mBean = t.get(position);
        tvHomeGoodsName.setText(mBean.getGoodsName());
        tvHomeGoodsTips.setText(mBean.getGoodsTips());
        tvHomeGoodsPrice.setText(String.format("￥%s", mBean.getShopPrice()));
        tvHomeGoodsBackNum.setText(String.format("增加:%s", mBean.getReturnPrice()));
        Glide.with(mContext).load(mBean.getGoodsImg()).into(ivHomeGoodsImage);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodDetailActivity.class);
                intent.putExtra("goodsId", mBean.getGoodsId());
                intent.putExtra("goodsName", mBean.getGoodsName());
                mContext.startActivity(intent);
            }
        });
    }
}
