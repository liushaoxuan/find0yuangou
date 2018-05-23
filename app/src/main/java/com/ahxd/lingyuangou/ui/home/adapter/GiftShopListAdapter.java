package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.ShopBean;
import com.ahxd.lingyuangou.listener.OnItemClickListener;
import com.ahxd.lingyuangou.ui.home.activity.GiftShopDetailActivity;
import com.ahxd.lingyuangou.ui.home.holder.GiftShopViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class GiftShopListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Object> mData = new ArrayList<>();

    public GiftShopListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<Object> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gift_shop, parent, false);
        return new GiftShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindFoodShopViewHolder((GiftShopViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        if (mData == null || mData.isEmpty()) {
            return 0;
        } else {
            return mData.size();
        }
    }

    private void bindFoodShopViewHolder(GiftShopViewHolder holder, int position) {
        Object obj = mData.get(position);
        if (obj instanceof ShopBean) {
            final ShopBean bean = (ShopBean) mData.get(position);
            holder.tvName.setText(bean.getShopName());
            holder.tvPhone.setText("联系电话:"+bean.getTelephone());
            holder.tvAddress.setText("地址:"+bean.getShopAddress());
            Glide.with(mContext).load(bean.getShopImg()).into(holder.ivIcon);

            holder.setItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mContext,GiftShopDetailActivity.class);
                    intent.putExtra("shopId", bean.getShopId());
                    mContext.startActivity(intent);
                }
            });
        }

    }
}
