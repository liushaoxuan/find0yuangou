package com.ahxd.lingyuangou.ui.home.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

// 推荐餐饮
public class FoodsShopViewHolder extends RecyclerView.ViewHolder {

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
    @BindView(R.id.tv_home_food_item_sale_delete)
    public TextView tvHomeFoodItemSaleDelete;

    private OnItemClickListener mListener;

    public void setItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public FoodsShopViewHolder(final View view) {
        super(view);
        ButterKnife.bind(this, view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onItemClick(view, getLayoutPosition());
                }
            }
        });
    }
}