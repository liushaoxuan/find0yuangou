package com.ahxd.lingyuangou.ui.home.holder;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.BaseBean;
import com.ahxd.lingyuangou.bean.HomeFoodShopBean;
import com.ahxd.lingyuangou.listener.OnItemClickListener;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopDetailActivity;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sxliu on 2018/6/28 1:48
 * E-mail Address 2587294424@qq.com
 * 首页LinearLayout布局的ViewHolder  酒店、教育、娱乐、汽车、家居等
 */

public class HomeLinearViewHolder extends HomeBaseViewHolder<BaseBean> {
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

    private OnItemClickListener mListener;

    public HomeLinearViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(List<BaseBean> t, int position) {
        if (position == 0) {
            fenlei.setVisibility(View.VISIBLE);
        } else {
            fenlei.setVisibility(View.GONE);
        }

        if (t.size() > 0) {
            //推荐美食
            if (t.get(0) instanceof HomeFoodShopBean) {
                label.setText("推荐美食");
                label.setBackground(ContextCompat.getDrawable(mContext, R.drawable.icon_cat_recomand_foods));
                final HomeFoodShopBean foodShopBean = (HomeFoodShopBean) t.get(position);
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

            }

        }
    }
}
