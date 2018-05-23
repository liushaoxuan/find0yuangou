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
import com.ahxd.lingyuangou.bean.GoodsBean;
import com.ahxd.lingyuangou.listener.OnItemClickListener;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopDetailActivity;
import com.ahxd.lingyuangou.ui.home.activity.GiftGoodDetailActivity;
import com.ahxd.lingyuangou.ui.home.activity.GoodDetailActivity;
import com.ahxd.lingyuangou.ui.home.holder.ExchangeGiftViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.FoodShopGoodViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.ShopGoodsViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class ShopGoodsListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<GoodsBean> mData = new ArrayList<>();
    private LayoutInflater mInflater;

    public ShopGoodsListAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<GoodsBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopGoodsViewHolder(mContext, mInflater.inflate(R.layout.item_home_foods_shop_good, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindFoodShopGoodViewHolder((ShopGoodsViewHolder) holder, position);
//        new FoodShopGoodViewHolder((FoodShopGoodViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        if (mData == null || mData.isEmpty()) {
            return 0;
        } else {
            return mData.size();
        }
    }
    // 绑定商品
    private void bindFoodShopGoodViewHolder(ShopGoodsViewHolder holder, int position) {
        int pos = position - 2;
        holder.setShopGoodData(mData.get(position));
    }

//    private void bindFoodShopViewHolder(ExchangeGiftViewHolder holder, int position) {
//        Object obj = mData.get(position);
//        if (obj instanceof ExchangeBean.ExchangeHots) {
//            final GoodsBean bean =  mData.get(position);
//            holder.tvName.setText(bean.getGoodsName());
//            holder.tvPrice.setText("需要货币:"+bean.getShopPrice());
//            holder.tvPriceOld.setText("原货币:"+bean.getMarketPrice());
//            holder.tvPriceOld.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
//            holder.tvSaleNum.setText("已兑换:"+bean.getSaleNum());
//            Glide.with(mContext).load(bean.getGoodsImg()).into(holder.ivIcon);
//            holder.setItemClickListener(new OnItemClickListener() {
//                @Override
//                public void onItemClick(View view, int position) {
//                    Intent intent = new Intent(mContext, FoodShopDetailActivity.class);
////                    intent.putExtra("catId", bean.getCatId());
////                    intent.putExtra("shopId", bean.getShopId());
////                    intent.putExtra("shopName", bean.getShopName());
//                    mContext.startActivity(intent);
//                }
//            });
//        }
//    }
}
