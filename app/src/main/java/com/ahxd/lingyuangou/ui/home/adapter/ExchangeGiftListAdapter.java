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
import com.ahxd.lingyuangou.bean.FavoriteShopBean;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.listener.OnItemClickListener;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopDetailActivity;
import com.ahxd.lingyuangou.ui.home.activity.GiftGoodDetailActivity;
import com.ahxd.lingyuangou.ui.home.activity.GoodDetailActivity;
import com.ahxd.lingyuangou.ui.home.holder.ExchangeGiftViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2018/1/2.
 */

public class ExchangeGiftListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Object> mData = new ArrayList<>();

    public ExchangeGiftListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<Object> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_exchange_gift, parent, false);
        return new ExchangeGiftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindFoodShopViewHolder((ExchangeGiftViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        if (mData == null || mData.isEmpty()) {
            return 0;
        } else {
            return mData.size();
        }
    }

    private void bindFoodShopViewHolder(ExchangeGiftViewHolder holder, int position) {
        Object obj = mData.get(position);
        if (obj instanceof ExchangeBean.ExchangeHots) {
            final ExchangeBean.ExchangeHots bean = (ExchangeBean.ExchangeHots) mData.get(position);
            holder.tvName.setText(bean.getGoodsName());
            holder.tvPrice.setText("需要货币:"+bean.getShopPrice());
            holder.tvPriceOld.setText("原货币:"+bean.getMarketPrice());
            holder.tvPriceOld.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
            holder.tvSaleNum.setText("已兑换:"+bean.getSaleNum());
            Glide.with(mContext).load(bean.getGoodsImg()).into(holder.ivIcon);
            holder.setItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mContext, GiftGoodDetailActivity.class);
                    intent.putExtra("goodsId", bean.getGoodsId());
                    intent.putExtra("goodsName",bean.getGoodsName());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
