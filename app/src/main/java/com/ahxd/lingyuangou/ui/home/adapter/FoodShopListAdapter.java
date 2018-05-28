package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.FavoriteShopBean;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.listener.OnFavoriteListener;
import com.ahxd.lingyuangou.listener.OnItemClickListener;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopDetailActivity;
import com.ahxd.lingyuangou.ui.home.holder.FoodsShopViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2018/1/2.
 */

public class FoodShopListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Object> mData = new ArrayList<>();
    private OnFavoriteListener onFavoriteListener;
    private String mCatId;
    public FoodShopListAdapter(Context context) {
        this.mContext = context;
    }
    public FoodShopListAdapter(Context context,OnFavoriteListener onFavoriteListener) {
        this.mContext = context;
        this.onFavoriteListener = onFavoriteListener;
    }

    public void setData(List<Object> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_foods_shop, parent, false);
        return new FoodsShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindFoodShopViewHolder((FoodsShopViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        if (mData == null || mData.isEmpty()) {
            return 0;
        } else {
            return mData.size();
        }
    }

    private void bindFoodShopViewHolder(FoodsShopViewHolder holder, int position) {
        Object obj = mData.get(position);
        if (obj instanceof FoodShopBean) {
            final FoodShopBean foodShopBean = (FoodShopBean) mData.get(position);
            holder.tvHomeFoodItemName.setText(foodShopBean.getShopName());
            if (Float.valueOf(foodShopBean.getDistance()) <= 0) {
                holder.tvHomeFoodItemDistance.setVisibility(View.INVISIBLE);
            } else {
                holder.tvHomeFoodItemDistance.setVisibility(View.VISIBLE);
                holder.tvHomeFoodItemDistance.setText(String.format("%skm", foodShopBean.getDistance()));
            }
            holder.tvHomeFoodItemSaleDelete.setVisibility(View.GONE);
            holder.tvHomeFoodItemTips.setText(foodShopBean.getShopAddress());
            holder.tvHomeFoodItemSaleNum.setText(String.format("销量:%s", foodShopBean.getSaleCount()));
            holder.tvHomeFoodItemPrice.setText(String.format("返货币:%s%%",
                    String.format(Locale.CHINA, "%.0f", Float.parseFloat(foodShopBean.getScoreRate()) * 100)));
            Glide.with(mContext).load(foodShopBean.getShopImg()).into(holder.ivHomeFoodItemIcon);
            holder.setItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mContext, FoodShopDetailActivity.class);
                    intent.putExtra("catId", foodShopBean.getCatId());
                    intent.putExtra("shopId", foodShopBean.getShopId());
                    intent.putExtra("shopName", foodShopBean.getShopName());
                    mContext.startActivity(intent);
                }
            });
        } else {
            final FavoriteShopBean favoriteShopBean = (FavoriteShopBean) mData.get(position);
            holder.tvHomeFoodItemName.setText(favoriteShopBean.getShopName());
            holder.tvHomeFoodItemDistance.setVisibility(View.GONE);
//            holder.tvHomeFoodItemDistance.setText(String.format("%skm", favoriteShopBean.getDistance()));
            holder.tvHomeFoodItemTips.setText(favoriteShopBean.getShopAddress());
            holder.tvHomeFoodItemSaleNum.setVisibility(View.GONE);
            holder.tvHomeFoodItemSaleDelete.setVisibility(View.VISIBLE);
            holder.tvHomeFoodItemSaleDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onFavoriteListener.onDelete(favoriteShopBean.getFavoriteId());
                }
            });

//            holder.tvHomeFoodItemSaleNum.setText(String.format("销量:%s", favoriteShopBean.getSaleCount()));
            holder.tvHomeFoodItemPrice.setText(String.format("返货币:%s%%",
                    String.format(Locale.CHINA, "%.0f", Float.parseFloat(favoriteShopBean.getScoreRate()) * 100)));
            Glide.with(mContext).load(favoriteShopBean.getShopImg()).into(holder.ivHomeFoodItemIcon);
            holder.setItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mContext, FoodShopDetailActivity.class);
//                    intent.putExtra("catId", favoriteShopBean.getCatId());
                    intent.putExtra("shopId", favoriteShopBean.getShopId());
                    intent.putExtra("shopName", favoriteShopBean.getShopName());
                    intent.putExtra("mCatId", favoriteShopBean.getShopName());
                    mContext.startActivity(intent);
                }
            });
        }

    }

    public void setmCatId(String mCatId){
        this.mCatId = mCatId;
    }
}
