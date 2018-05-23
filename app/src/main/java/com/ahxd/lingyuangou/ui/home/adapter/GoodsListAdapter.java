package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.ShopGoodBean;
import com.ahxd.lingyuangou.listener.OnFavoriteListener;
import com.ahxd.lingyuangou.ui.home.holder.FoodShopGoodViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class GoodsListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Object> mData;
    private OnFavoriteListener onFavoriteListener;

    public GoodsListAdapter(Context context) {
        this.mContext = context;
    } public GoodsListAdapter(Context context,OnFavoriteListener onFavoriteListener) {
        this.mContext = context;
        this.onFavoriteListener = onFavoriteListener;
    }

    public void setData(List<Object> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FoodShopGoodViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.item_home_foods_shop_good, parent, false),onFavoriteListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FoodShopGoodViewHolder) holder).setShopGoodData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if (mData == null || mData.isEmpty()) {
            return 0;
        } else {
            return mData.size();
        }
    }

}
