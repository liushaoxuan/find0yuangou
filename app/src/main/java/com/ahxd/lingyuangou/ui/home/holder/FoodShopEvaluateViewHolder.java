package com.ahxd.lingyuangou.ui.home.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodShopEvaluateViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_shop_evaluate_time)
    public TextView tvShopEvaluateTime;
    @BindView(R.id.ratingbar_evaluate_item)
    public RatingBar ratingbarEvaluateItem;
    @BindView(R.id.tv_shop_evaluate_user)
    public TextView tvShopEvaluateUser;
    @BindView(R.id.tv_shop_detail_evaluate_content)
    public TextView tvShopDetailEvaluateContent;

    public FoodShopEvaluateViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}