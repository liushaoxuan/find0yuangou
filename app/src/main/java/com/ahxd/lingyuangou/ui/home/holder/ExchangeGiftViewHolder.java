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
public class ExchangeGiftViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_exchange_item_icon)
    public ImageView ivIcon;
    @BindView(R.id.tv_exchange_item_tips)
    public TextView tvName;
    @BindView(R.id.tv_exchange_item_price)
    public TextView tvPrice;
    @BindView(R.id.tv_exchange_item_price_old)
    public TextView tvPriceOld;
    @BindView(R.id.tv_exchange_item_sale_num)
    public TextView tvSaleNum;

    private OnItemClickListener mListener;

    public void setItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public ExchangeGiftViewHolder(final View view) {
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