package com.ahxd.lingyuangou.ui.home.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeRecomendCarBean;
import com.ahxd.lingyuangou.listener.OnItemClickListener;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopDetailActivity;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

// 推荐汽车
public class RecomendCarViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_home_food_item_icon)
    public ImageView ivHomeFoodItemIcon;
    @BindView(R.id.tv_home_food_item_name)
    public TextView tvHomeFoodItemName;
    @BindView(R.id.tv_home_food_item_distance)
    public TextView tvHomeFoodItemDistance;
    @BindView(R.id.tv_home_food_item_tips)
    public TextView tvHomeFoodItemTips;
    @BindView(R.id.tv_home_food_item_price)
    public TextView tvHomeFoodItemPrice;
    @BindView(R.id.tv_home_food_item_sale_num)
    public TextView tvHomeFoodItemSaleNum;

    private OnItemClickListener mListener;

    private Context mContext;

    public void setItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public RecomendCarViewHolder(final View view) {
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
        mContext = view.getContext();
    }

    public void setData(List<HomeRecomendCarBean> datas,int position){
        final HomeRecomendCarBean item = datas.get(position);

         tvHomeFoodItemName.setText(item.getShopName());
        if (Float.valueOf(item.getDistance()) <= 0) {
             tvHomeFoodItemDistance.setVisibility(View.INVISIBLE);
        } else {
             tvHomeFoodItemDistance.setVisibility(View.VISIBLE);
             tvHomeFoodItemDistance.setText(String.format("%skm", item.getDistance()));
        }
         tvHomeFoodItemTips.setText(String.format("地址:%s", item.getShopAddress()));
         tvHomeFoodItemSaleNum.setText(String.format("销量:%s", item.getSaleCount()));
         tvHomeFoodItemPrice.setText(String.format("增加:%s", item.getScoreRate()));
         tvHomeFoodItemPrice.setText(String.format("增加:%s%%",
                String.format(Locale.CHINA, "%.0f", Float.parseFloat(item.getScoreRate()) * 100)));
        Glide.with(mContext).load(item.getShopImg()).into( ivHomeFoodItemIcon);
         setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, FoodShopDetailActivity.class);
                intent.putExtra("shopId", item.getShopId());
                intent.putExtra("shopName", item.getShopName());
                mContext.startActivity(intent);
            }
        });
    }
}