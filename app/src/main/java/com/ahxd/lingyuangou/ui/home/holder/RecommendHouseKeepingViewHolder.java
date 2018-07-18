package com.ahxd.lingyuangou.ui.home.holder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeFoodShopBean;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopDetailActivity;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopListActivity;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sxliu on 2018/6/28 22:38
 * E-mail Address 2587294424@qq.com
 * 推荐家政
 */

public class RecommendHouseKeepingViewHolder extends HomeBaseViewHolder<HomeFoodShopBean> {
    @BindView(R.id.iv_home_food_item_icon)
    public ImageView ivHomeFoodItemIcon;
    @BindView(R.id.tv_home_food_item_name)
    public TextView tvHomeFoodItemName;
    @BindView(R.id.tv_home_food_item_distance)
    public TextView tvHomeFoodItemDistance;
    @BindView(R.id.tv_home_food_item_tips)
    public TextView tvHomeFoodItemTips;
    @BindView(R.id.tv_home_food_item_price)
    public TextView tvHomeFoodItemPrice;
    @BindView(R.id.tv_home_food_item_sale_num)
    public TextView tvHomeFoodItemSaleNum;
    @BindView(R.id.include)
    public LinearLayout fenlei;
    //分类图标名称
    @BindView(R.id.tv_home_label_name)
    public TextView label;
    //更多
    @BindView(R.id.tv_home_label_more)
    public TextView more;
    public RecommendHouseKeepingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(List<HomeFoodShopBean> mFoodsData, int position) {
        label.setText("推荐家政");
        label.setBackgroundResource(R.drawable.icon_cat_recomand_jiazheng);
        if (position == 0) {
            fenlei.setVisibility(View.VISIBLE);
        } else {
            fenlei.setVisibility(View.GONE);
        }
        final HomeFoodShopBean foodShopBean = mFoodsData.get(position);
        tvHomeFoodItemName.setText(foodShopBean.getShopName());
        if (Float.valueOf(foodShopBean.getDistance()) <= 0) {
            tvHomeFoodItemDistance.setVisibility(View.INVISIBLE);
        } else {
            tvHomeFoodItemDistance.setVisibility(View.VISIBLE);
            tvHomeFoodItemDistance.setText(String.format("%skm", foodShopBean.getDistance()));
        }
        tvHomeFoodItemTips.setText(String.format("地址:%s", foodShopBean.getShopAddress()));
        tvHomeFoodItemSaleNum.setText(String.format("销量:%s", foodShopBean.getSaleCount()));
        tvHomeFoodItemPrice.setText(String.format("增加:%s", foodShopBean.getScoreRate()));
        tvHomeFoodItemPrice.setText(String.format("增加:%s%%",
                String.format(Locale.CHINA, "%.0f", Float.parseFloat(foodShopBean.getScoreRate()) * 100)));
        Glide.with(mContext).load(foodShopBean.getShopImg()).into(ivHomeFoodItemIcon);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoodShopDetailActivity.class);
                intent.putExtra("shopId", foodShopBean.getShopId());
                intent.putExtra("shopName", foodShopBean.getShopName());
                mContext.startActivity(intent);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoodShopListActivity.class);
                intent.putExtra("catId", "370");
                intent.putExtra("catName", "推荐家政");
                intent.putExtra("start_from", "label");
                mContext.startActivity(intent);
            }
        });
    }
}
