package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.bean.FoodShopEvaluateBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;
import com.ahxd.lingyuangou.listener.OnItemClickListener;
import com.ahxd.lingyuangou.listener.OnNaviListener;
import com.ahxd.lingyuangou.listener.ShopDetailClickListener;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopDetailActivity;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopEvaluateListActivity;
import com.ahxd.lingyuangou.ui.home.activity.RechargeActivity;
import com.ahxd.lingyuangou.ui.home.activity.ShopGoodsListActivity;
import com.ahxd.lingyuangou.ui.home.holder.FoodShopEvaluateViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.FoodShopGoodViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.FoodsShopViewHolder;
import com.ahxd.lingyuangou.utils.GlideImageLoader;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/3.
 */

public class FoodShopDetailAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater mInflater;

    // 商店头部
    private static final int TYPE_SHOP_HEADER = 0;
    // 评价
    private static final int TYPE_SHOP_EVALUATE = 1;
    // 展开评价
    private static final int TYPE_SHOP_EXPAND_EVALUATE = 2;
    // 附近商铺 >>
    private static final int TYPE_SHOP_NEAR_LABEL = 3;
    // 商铺
    private static final int TYPE_SHOP_NEAR_SHOP = 4;
    // 商品
    private static final int TYPE_SHOP_GOODS = 5;
    // 商铺评价积分
    private static final int TYPE_SHOP_EVALUATE_HEADER = 6;

    // 评价数量
    private String mEvaluateNum;
    // 商铺ID
    private String mShopId;
    // 是否收藏
    private boolean isFavorite = false;

    //导航点击回调
    private OnNaviListener naviListener;

    private JSONObject mShopInfo = new JSONObject();
    private List<FoodShopEvaluateBean> mEvaluateInfo = new ArrayList<>();
    private List<FoodShopBean> mShopList = new ArrayList<>();
    private List<ShopGoodBean> mShopGoodsList = new ArrayList<>();
    private ShopDetailClickListener mListener;

    public FoodShopDetailAdapter(Context context, ShopDetailClickListener listener) {
        this.mContext = context;
        this.mListener = listener;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public void setShopInfo(JSONObject shopInfo) {
        this.mShopInfo = shopInfo;
        isFavorite = (mShopInfo.optInt("isFavorites") != 0);
//        notifyItemChanged(0);
    }

    public void setShopGoods(List<ShopGoodBean> list) {
        this.mShopGoodsList = list;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
        notifyItemChanged(0);
    }

    public void setEvaluateInfo(List<FoodShopEvaluateBean> list) {
        this.mEvaluateInfo = list;
    }

    public void setShopList(List<FoodShopBean> list) {
        this.mShopList = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_SHOP_HEADER:
                return new FoodShopHeaderViewHolder(mInflater.inflate(R.layout.layout_food_shop_header, parent, false));
            case TYPE_SHOP_GOODS:
                return new FoodShopGoodViewHolder(mContext, mInflater.inflate(R.layout.item_home_foods_shop_good, parent, false));
            case TYPE_SHOP_EVALUATE_HEADER:
                return new FoodShopEvaluateHeaderViewHolder(mInflater.inflate(R.layout.layout_home_shop_evaluate_header, parent, false));
            case TYPE_SHOP_EVALUATE:
                return new FoodShopEvaluateViewHolder(mInflater.inflate(R.layout.layout_food_shop_evaluate, parent, false));
            case TYPE_SHOP_EXPAND_EVALUATE:
                return new FoodShopExpandEvaluateViewHolder(mInflater.inflate(R.layout.layout_food_shop_more_evaluate, parent, false));
            case TYPE_SHOP_NEAR_LABEL:
                return new FoodShopNearLabelViewHolder(mInflater.inflate(R.layout.layout_food_shop_near_label, parent, false));
            case TYPE_SHOP_NEAR_SHOP:
                return new FoodsShopViewHolder(mInflater.inflate(R.layout.item_home_foods_shop, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FoodShopHeaderViewHolder) {
            bindFoodShopHeaderViewHolder((FoodShopHeaderViewHolder) holder, position);
        } else if (holder instanceof FoodShopEvaluateViewHolder) {
            bindFoodShopEvaluateViewHolder((FoodShopEvaluateViewHolder) holder, position);
        } else if (holder instanceof FoodShopExpandEvaluateViewHolder) {
            bindFoodShopExpandEvaluateViewHolder((FoodShopExpandEvaluateViewHolder) holder, position);
        } else if (holder instanceof FoodsShopViewHolder) {
            bindFoodShopViewHolder((FoodsShopViewHolder) holder, position);
        } else if (holder instanceof FoodShopEvaluateHeaderViewHolder) {
            bindFoodShopEvaluateHeaderViewHolder((FoodShopEvaluateHeaderViewHolder) holder, position);
        } else if (holder instanceof FoodShopGoodViewHolder) {
            bindFoodShopGoodViewHolder((FoodShopGoodViewHolder) holder, position);
        } else if (holder instanceof FoodShopNearLabelViewHolder) {
            bindFoodShopLabelViewHolder((FoodShopNearLabelViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        if (mShopInfo.optInt("isGoodsSale") == 1) { // 商铺里面有商品
            return 5 + mEvaluateInfo.size() + mShopList.size() + mShopGoodsList.size();
        } else {
            return 3 + mEvaluateInfo.size() + mShopList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mShopInfo.optInt("isGoodsSale") == 1) { // 商铺里面有商品
            if (position == 0) {
                return TYPE_SHOP_HEADER;
            } else if ((position == 1) || position == (4 + mShopGoodsList.size() + mEvaluateInfo.size())) {
                return TYPE_SHOP_NEAR_LABEL;
            } else if (1 < position && position < 2 + mShopGoodsList.size()) {
                return TYPE_SHOP_GOODS;
            } else if ((2 + mShopGoodsList.size()) == position) {
                return TYPE_SHOP_EVALUATE_HEADER;
            } else if ((2 + mShopGoodsList.size()) < position && position < (3 + mShopGoodsList.size() + mEvaluateInfo.size())) {
                return TYPE_SHOP_EVALUATE;
            } else if (position == (3 + mShopGoodsList.size() + mEvaluateInfo.size())) {
                return TYPE_SHOP_EXPAND_EVALUATE;
            } else if (position > (4 + mShopGoodsList.size() + mEvaluateInfo.size())) {
                return TYPE_SHOP_NEAR_SHOP;
            }
        } else { // 商铺里面没有商品
            if (position == 0) {
                return TYPE_SHOP_HEADER;
            } else if (position == 1) {
                return TYPE_SHOP_EVALUATE_HEADER;
            } else if (1 < position && position < 2 + mEvaluateInfo.size()) {
                return TYPE_SHOP_EVALUATE;
            } else if (2 + mEvaluateInfo.size() == position) {
                return TYPE_SHOP_EXPAND_EVALUATE;
            } else if (3 + mEvaluateInfo.size() == position) {
                return TYPE_SHOP_NEAR_LABEL;
            } else if ((3 + mEvaluateInfo.size()) < position) {
                return TYPE_SHOP_NEAR_SHOP;
            }
        }
        return super.getItemViewType(position);
    }

    // 绑定商铺header数据
    private void bindFoodShopHeaderViewHolder(FoodShopHeaderViewHolder holder, int position) {
        mShopId = mShopInfo.optString("shopId");
        holder.tvShopDetailShopName.setText(mShopInfo.optString("shopName"));
//        holder.tvShopDetailPrice.setText(String.format("返货币：%s", mShopInfo.optString("scoreRate")));
        if (!mShopInfo.optString("scoreRate").equals("")){
            holder.tvShopDetailPrice.setText(String.format("返货币:%s%%",
                    String.format(Locale.CHINA, "%.0f", Float.parseFloat(mShopInfo.optString("scoreRate")) * 100)));
        }
      holder.tvShopDetailShopTips.setText(mShopInfo.optString("shopDesc"));
        holder.tvShopDetailShopAddress.setText(mShopInfo.optString("shopAddress"));
        Glide.with(mContext).load(mShopInfo.optString("shopImg")).into(holder.ivShopDetailLogo);

        if (!isFavorite) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ic_favorite_normal);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tvShopDetailFavorite.setCompoundDrawables(null, drawable, null, null);
            holder.tvShopDetailFavorite.setText(R.string.string_shop_detail_no_favorite);
        } else {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ic_favorite_check);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tvShopDetailFavorite.setCompoundDrawables(null, drawable, null, null);
            holder.tvShopDetailFavorite.setText(R.string.string_shop_detail_favorite);
        }

        JSONArray shopAds = mShopInfo.optJSONArray("shopAds");
        if (shopAds == null) {
            return;
        }
        List<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < shopAds.length(); i++) {
            imageUrls.add(shopAds.optString(i));
        }
        holder.shopHeaderBanner.setImages(imageUrls).setImageLoader(new GlideImageLoader()).start();
        holder.shopHeaderBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                //注意这里的position是从1开始的

            }
        });
    }

    // 绑定商品
    private void bindFoodShopGoodViewHolder(FoodShopGoodViewHolder holder, int position) {
        int pos = position - 2;
        holder.setShopGoodData(mShopGoodsList.get(pos));
    }

    // 绑定评价header
    private void bindFoodShopEvaluateHeaderViewHolder(FoodShopEvaluateHeaderViewHolder holder, int position) {
        holder.ratingbarEvaluate.setRating(Float.parseFloat(String.format(Locale.CHINA, "%.1f", mShopInfo.optDouble("shopScore"))));
        mEvaluateNum = mShopInfo.optString("shopScoreUsers");
        holder.tvShopDetailEvaluateNum.setText(String.format("共%s条评论", mEvaluateNum));
        holder.tvShopDetailShopScore.setText(String.format(Locale.CHINA, "%.1f", mShopInfo.optDouble("shopScore")));
    }

    // 绑定商铺评价
    private void bindFoodShopEvaluateViewHolder(FoodShopEvaluateViewHolder holder, int position) {
        int pos = 0;
        if (mShopInfo.optInt("isGoodsSale") == 1) { // 有商品
            pos = position - (3 + mShopGoodsList.size());
        } else {
            pos = position - 2;
        }
        FoodShopEvaluateBean bean = mEvaluateInfo.get(pos);
        holder.ratingbarEvaluateItem.setRating(Float.valueOf(bean.getGoodsScore()));
        holder.tvShopDetailEvaluateContent.setText(bean.getContent());
        holder.tvShopEvaluateUser.setText(bean.getLoginName());
        holder.tvShopEvaluateTime.setText(bean.getCreateTime());
    }

    //　绑定查看全部评价
    private void bindFoodShopExpandEvaluateViewHolder(FoodShopExpandEvaluateViewHolder holder, int position) {
        holder.tvFoodDetailExpandEvaluate.setText(String.format("查看全部评价（%s）>", mEvaluateNum));
    }

    // 绑定label
    private void bindFoodShopLabelViewHolder(FoodShopNearLabelViewHolder holder, int position) {

        if (mShopInfo.optInt("isGoodsSale") == 1) { // 有商品
            if (position == 1) {
                holder.tvShopDetailLabelName.setText("推荐商品");
                holder.tvMore.setVisibility(View.VISIBLE);
                holder.tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, ShopGoodsListActivity.class);
                        intent.putExtra("shopId", mShopId);
                        mContext.startActivity(intent);
                    }
                });
            } else {
                holder.tvShopDetailLabelName.setText("附近商铺");
                holder.tvMore.setVisibility(View.INVISIBLE);
            }
        } else {
            holder.tvShopDetailLabelName.setText("附近商铺");
            holder.tvMore.setVisibility(View.INVISIBLE);
        }
    }

    // 绑定商铺
    private void bindFoodShopViewHolder(FoodsShopViewHolder holder, int position) {
        int pos = 0;
        if (mShopInfo.optInt("isGoodsSale") == 1) { // 有商品
            pos = position - (5 + mEvaluateInfo.size() + mShopGoodsList.size());
        } else {
            pos = position - (3 + mEvaluateInfo.size());
        }
        final FoodShopBean foodShopBean = mShopList.get(pos);
        holder.tvHomeFoodItemName.setText(foodShopBean.getShopName());
        holder.tvHomeFoodItemDistance.setText(String.format("%skm", foodShopBean.getDistance()));
        holder.tvHomeFoodItemTips.setText(foodShopBean.getShopAddress());
        holder.tvHomeFoodItemSaleNum.setText(String.format("最近销量:%s", foodShopBean.getSaleCount()));
//        holder.tvHomeFoodItemPrice.setText(String.format("返货币:%s", foodShopBean.getScoreRate()));
        if (!foodShopBean.getScoreRate().equals("")){
            holder.tvHomeFoodItemPrice.setText(String.format("返货币:%s%%",
                    String.format(Locale.CHINA, "%.0f", Float.parseFloat(foodShopBean.getScoreRate()) * 100)));
        }


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
    }

    public class FoodShopHeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_header_banner)
        Banner shopHeaderBanner;
        @BindView(R.id.iv_shop_detail_logo)
        ImageView ivShopDetailLogo;
        @BindView(R.id.tv_shop_detail_price)
        TextView tvShopDetailPrice;
        @BindView(R.id.tv_shop_detail_shop_name)
        TextView tvShopDetailShopName;
        @BindView(R.id.tv_shop_detail_shop_tips)
        TextView tvShopDetailShopTips;
        @BindView(R.id.tv_shop_detail_add)
        TextView tvShopDetailAdd;
        @BindView(R.id.tv_shop_detail_favorite)
        TextView tvShopDetailFavorite;
        @BindView(R.id.tv_shop_detail_shop_address)
        TextView tvShopDetailShopAddress;
        @BindView(R.id.tv_go_recharge)
        TextView tvGoRecharge;
        @BindView(R.id.ll_food_shop_go_recharge)
        LinearLayout llFoodShopGoRecharge;
        @BindView(R.id.iv_shop_detail_call)
        ImageView ivShopDetailCall;
        @BindView(R.id.ll_address)
        LinearLayout llAddress;

        public FoodShopHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.iv_shop_detail_call,R.id.ll_address, R.id.tv_shop_detail_favorite, R.id.tv_shop_detail_add, R.id.ll_food_shop_go_recharge})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.iv_shop_detail_call:
                    String tel = mShopInfo.optString("telephone");
                    if (tel == null || TextUtils.isEmpty(tel)) {
                        ToastUtils.showShort(mContext, "没有商铺电话!");
                        return;
                    }
                    if (mListener != null) {
                        mListener.call(tel);
                    }
                    break;
                case R.id.tv_shop_detail_favorite:
                    if (mListener != null) {
                        mListener.favorite(mShopInfo.optString("shopId"), isFavorite);
                    }
                    break;
                case R.id.tv_shop_detail_add:
                    if (mListener != null) {
                        mListener.showAds(mShopInfo.optString("incomeRate"));
                    }
                    break;
                case R.id.ll_food_shop_go_recharge:
                    Intent intent = new Intent(mContext, RechargeActivity.class);
                    mContext.startActivity(intent);
                    break;
                case R.id.ll_address:
//                    if (AMapUtil.isInstallByRead("com.autonavi.minimap")){
//                        AMapUtil.goToNaviActivity(mContext,"导航",null,mShopInfo.optString("lat"),mShopInfo.optString("lng"),"1","2");
//                    }else {
//                        ToastUtils.show(mContext,"请先安装高德app",1);
//                    }

                    if (naviListener!=null){
                        naviListener.onNaviClickListener(mShopInfo);
                    }
                    break;
            }
        }
    }

    public class FoodShopExpandEvaluateViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_food_detail_expand_evaluate)
        TextView tvFoodDetailExpandEvaluate;

        public FoodShopExpandEvaluateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.tv_food_detail_expand_evaluate)
        public void onViewClicked() {
            Intent intent = new Intent(mContext, FoodShopEvaluateListActivity.class);
            intent.putExtra("shopId", mShopId);
            intent.putExtra("goodsId", "0");
            mContext.startActivity(intent);
        }
    }

    public class FoodShopNearLabelViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_shop_near_label)
        LinearLayout rlShopNearLabel;
        @BindView(R.id.tv_shop_detail_label_name)
        TextView tvShopDetailLabelName;
        @BindView(R.id.tv_more)
        TextView tvMore;

        public FoodShopNearLabelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.rl_shop_near_label)
        public void onViewClicked() {
//            ToastUtils.showShort(mContext, "MORE");
        }
    }

    public class FoodShopEvaluateHeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shop_detail_shop_score)
        TextView tvShopDetailShopScore;
        @BindView(R.id.ratingbar_evaluate)
        RatingBar ratingbarEvaluate;
        @BindView(R.id.tv_shop_detail_evaluate_num)
        TextView tvShopDetailEvaluateNum;

        public FoodShopEvaluateHeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setOnNaviListener(OnNaviListener naviListener){
        this.naviListener = naviListener;
    }
}
