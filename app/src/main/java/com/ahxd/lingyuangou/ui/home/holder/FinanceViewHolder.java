package com.ahxd.lingyuangou.ui.home.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

// 推荐金融
public class FinanceViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_home_finance_item_icon)
    public ImageView ivHomeFinanceItemIcon;
    @BindView(R.id.tv_home_finance_item_price)
    public TextView tvHomeFinanceItemPrice;
    @BindView(R.id.tv_home_finance_item_sale_num)
    public TextView tvHomeFinanceItemSaleNum;
    @BindView(R.id.tv_home_finance_item_name)
    public TextView tvHomeFinanceItemName;
    @BindView(R.id.tv_home_finance_item_tips)
    public TextView tvHomeFinanceItemTips;

    private OnItemClickListener mListener;

    public void setItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public FinanceViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onItemClick(v, getLayoutPosition());
                }
            }
        });
    }
}