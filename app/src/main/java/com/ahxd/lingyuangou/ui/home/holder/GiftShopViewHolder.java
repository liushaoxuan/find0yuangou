package com.ahxd.lingyuangou.ui.home.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiftShopViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_item_icon)
    public ImageView ivIcon;
    @BindView(R.id.tv_name)
    public TextView tvName;
    @BindView(R.id.tv_phone)
    public TextView tvPhone;
    @BindView(R.id.tv_address)
    public TextView tvAddress;

    private OnItemClickListener mListener;

    public void setItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public GiftShopViewHolder(final View view) {
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