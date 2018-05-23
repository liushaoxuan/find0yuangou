package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.FoodShopEvaluateBean;
import com.ahxd.lingyuangou.ui.home.holder.FoodShopEvaluateViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/1/4.
 */

public class FoodShopEvaluateListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<FoodShopEvaluateBean> mData;

    public FoodShopEvaluateListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<FoodShopEvaluateBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FoodShopEvaluateViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_food_shop_evaluate, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindShopEvaluateViewHolder((FoodShopEvaluateViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        if (mData == null || mData.isEmpty()) {
            return 0;
        } else {
            return mData.size();
        }
    }

    private void bindShopEvaluateViewHolder(FoodShopEvaluateViewHolder holder, int position) {
        FoodShopEvaluateBean bean = mData.get(position);
        holder.ratingbarEvaluateItem.setRating(Float.valueOf(bean.getGoodsScore()));
        holder.tvShopDetailEvaluateContent.setText(bean.getContent());
        holder.tvShopEvaluateUser.setText(bean.getLoginName());
        holder.tvShopEvaluateTime.setText(bean.getCreateTime());
    }

}
