package com.ahxd.lingyuangou.ui.home.holder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.FavoriteGoodBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;
import com.ahxd.lingyuangou.listener.OnFavoriteListener;
import com.ahxd.lingyuangou.ui.home.activity.GoodDetailActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodShopGoodViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_home_shop_item_good_icon)
    public ImageView ivHomeShopItemGoodIcon;
    @BindView(R.id.tv_home_shop_good_item_sale_num)
    public TextView tvHomeShopGoodItemSaleNum;
    @BindView(R.id.tv_home_shop_item_good_now_price)
    public TextView tvHomeShopItemGoodNowPrice;
    @BindView(R.id.tv_home_shop_item_good_old_price)
    public TextView tvHomeShopItemGoodOldPrice;
    @BindView(R.id.tv_home_shop_good_item_name)
    public TextView tvHomeShopGoodItemName;
    @BindView(R.id.tv_home_food_item_return_price)
    public TextView tvHomeFoodItemReturnPrice;
    @BindView(R.id.tv_home_food_item_sale_delete)
    public TextView tvHomeFoodItemSaleDelete;
    @BindView(R.id.tv_home_food_item_tips)
    public TextView tvHomeFoodItemTips;

    private Context mContext;
    private Object mBean;
    private OnFavoriteListener onFavoriteListener;

    public FoodShopGoodViewHolder(Context context, View view) {
        super(view);
        ButterKnife.bind(this, view);
        this.mContext = context;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mBean) {
                    Intent intent = new Intent(mContext, GoodDetailActivity.class);
                    if (mBean instanceof ShopGoodBean) {
                        intent.putExtra("goodsId", ((ShopGoodBean) mBean).getGoodsId());
                        intent.putExtra("goodsName", ((ShopGoodBean) mBean).getGoodsName());
                    } else {
                        intent.putExtra("goodsId", ((FavoriteGoodBean) mBean).getGoodsId());
                        intent.putExtra("goodsName", ((FavoriteGoodBean) mBean).getGoodsName());
                    }
                    mContext.startActivity(intent);
                }
            }
        });
    }

    public FoodShopGoodViewHolder(Context context, View view,OnFavoriteListener onFavoriteListener) {
        super(view);
        ButterKnife.bind(this, view);
        this.mContext = context;
        this.onFavoriteListener = onFavoriteListener;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mBean) {
                    Intent intent = new Intent(mContext, GoodDetailActivity.class);
                    if (mBean instanceof ShopGoodBean) {
                        intent.putExtra("goodsId", ((ShopGoodBean) mBean).getGoodsId());
                        intent.putExtra("goodsName", ((ShopGoodBean) mBean).getGoodsName());
                    } else {
                        intent.putExtra("goodsId", ((FavoriteGoodBean) mBean).getGoodsId());
                        intent.putExtra("goodsName", ((FavoriteGoodBean) mBean).getGoodsName());
                    }
                    mContext.startActivity(intent);
                }
            }
        });
    }
    public void setShopGoodData(final Object bean) {
        this.mBean = bean;
        if (bean instanceof ShopGoodBean) {
            tvHomeFoodItemSaleDelete.setVisibility(View.GONE);
            tvHomeShopGoodItemName.setText(((ShopGoodBean) bean).getGoodsName());
            tvHomeFoodItemTips.setText(((ShopGoodBean) bean).getGoodsTips());
            tvHomeFoodItemReturnPrice.setText(String.format("增加:%s", ((ShopGoodBean) bean).getReturnPrice()));
            tvHomeShopItemGoodOldPrice.setText(String.format("￥%s", ((ShopGoodBean) bean).getMarketPrice()));
            tvHomeShopItemGoodNowPrice.setText(String.format("￥%s", ((ShopGoodBean) bean).getShopPrice()));
            tvHomeShopItemGoodOldPrice.getPaint().setAntiAlias(true);
            tvHomeShopItemGoodOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            tvHomeShopGoodItemSaleNum.setText(String.format("销量:%s", ((ShopGoodBean) bean).getSaleNum()));
            Glide.with(mContext).load(((ShopGoodBean) bean).getGoodsImg()).into(ivHomeShopItemGoodIcon);
        } else if (bean instanceof FavoriteGoodBean) {
            tvHomeShopGoodItemName.setText(((FavoriteGoodBean) bean).getGoodsName());
            tvHomeFoodItemTips.setVisibility(View.INVISIBLE);
//            tvHomeFoodItemTips.setText(((FavoriteGoodBean) bean).getGoodsTips());
            tvHomeFoodItemReturnPrice.setText(String.format("增加:%s", ((FavoriteGoodBean) bean).getReturnPrice()));
            tvHomeShopItemGoodOldPrice.setVisibility(View.GONE);
            tvHomeFoodItemSaleDelete.setVisibility(View.VISIBLE);
            tvHomeFoodItemSaleDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onFavoriteListener.onDelete(((FavoriteGoodBean) bean).getFavoriteId());
                }
            });
//            tvHomeShopItemGoodOldPrice.setText(String.format("￥%s", ((FavoriteGoodBean) bean).getMarketPrice()));
            tvHomeShopItemGoodNowPrice.setText(String.format("￥%s", ((FavoriteGoodBean) bean).getShopPrice()));
//            tvHomeShopItemGoodOldPrice.getPaint().setAntiAlias(true);
//            tvHomeShopItemGoodOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            tvHomeShopGoodItemSaleNum.setVisibility(View.GONE);
//            tvHomeShopGoodItemSaleNum.setText(String.format("销量:%s", ((FavoriteGoodBean) bean).getSaleNum()));
            Glide.with(mContext).load(((FavoriteGoodBean) bean).getGoodsImg()).into(ivHomeShopItemGoodIcon);
        }
    }
}