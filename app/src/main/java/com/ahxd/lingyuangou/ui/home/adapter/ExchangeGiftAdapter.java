package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.bean.HomeFinanceBean;
import com.ahxd.lingyuangou.bean.HomeFoodShopBean;
import com.ahxd.lingyuangou.bean.HomeGiftBean;
import com.ahxd.lingyuangou.bean.HomeGoodBean;
import com.ahxd.lingyuangou.listener.OnItemClickListener;
import com.ahxd.lingyuangou.ui.home.activity.ArticleListActivity;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopDetailActivity;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopListActivity;
import com.ahxd.lingyuangou.ui.home.activity.GoodDetailActivity;
import com.ahxd.lingyuangou.ui.home.activity.GoodsListActivity;
import com.ahxd.lingyuangou.ui.home.activity.RechargeActivity;
import com.ahxd.lingyuangou.ui.home.activity.SearchActivity;
import com.ahxd.lingyuangou.ui.home.holder.FinanceViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.FoodsShopViewHolder;
import com.ahxd.lingyuangou.utils.GlideImageLoader;
import com.ahxd.lingyuangou.utils.LocationUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.HomeBigGoodView;
import com.ahxd.lingyuangou.widget.HomeSmallGoodView;
import com.ahxd.lingyuangou.widget.RecyclerGridView;
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
 * Created by Administrator on 2017/12/26.
 * Home页Adapter
 */

public class ExchangeGiftAdapter extends RecyclerView.Adapter {

    // 轮播banner
    private static final int HOME_TYPE_BANNER = 0;
    //TAB
    private static final int HOME_TYPE_TAB = 1;
    // 分类
    private static final int HOME_TYPE_SORT = 2;
    // 热门
    private static final int HOME_TYPE_HOT = 3;

    private Context mContext;
    private LayoutInflater mInflater;

    private ImageView[] mIvPoints;

    private JSONArray mBannerData = new JSONArray();
    private JSONArray mArticleData = new JSONArray();
    private JSONArray mAdsData = new JSONArray();
    private List<HomeCatBean> mCatData = new ArrayList<>();
    private List<HomeGiftBean> mGiftData = new ArrayList<>();
    private List<HomeFoodShopBean> mFoodsData = new ArrayList<>();
    private List<HomeFinanceBean> mFinanceData = new ArrayList<>();
    private List<HomeGoodBean> mGoodsData = new ArrayList<>();

    public ExchangeGiftAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public void setBannerData(JSONArray banner) {
        this.mBannerData = banner;
        notifyItemChanged(0);
    }

    public void setLocationRefresh() {
        notifyItemChanged(0);
    }

    public void setCatData(List<HomeCatBean> sorts) {
        this.mCatData = sorts;
        notifyItemChanged(1);
    }

    public void setArticleData(JSONArray articles) {
        this.mArticleData = articles;
    }

    public void setGiftData(List<HomeGiftBean> gifts) {
        this.mGiftData = gifts;
    }

    public void setAdData(JSONArray ads) {
        this.mAdsData = ads;
    }

    public void setFoodData(List<HomeFoodShopBean> foods) {
        this.mFoodsData = foods;
    }

    public void setFinanceData(List<HomeFinanceBean> finances) {
        this.mFinanceData = finances;
    }

    public void setGoodsData(List<HomeGoodBean> goods) {
        this.mGoodsData = goods;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HOME_TYPE_BANNER:
                return new BannerViewHolder(mInflater.inflate(R.layout.layout_home_banner, parent, false));
            case HOME_TYPE_SORT:
                return new SortViewHolder(mInflater.inflate(R.layout.layout_home_sort, parent, false));
            case HOME_TYPE_TAB:
                return new ThreeViewHolder(mInflater.inflate(R.layout.layout_home_three, parent, false));
            case HOME_TYPE_HOT:
                return new FoodsShopViewHolder(mInflater.inflate(R.layout.item_home_foods_shop, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            bindBannerViewHolder((BannerViewHolder) holder, position);
        } else if (holder instanceof SortViewHolder) {
            bindSortViewHolder((SortViewHolder) holder, position);
        } else if (holder instanceof MessageViewHolder) {
            bindMessageViewHolder((MessageViewHolder) holder, position);
        } else if (holder instanceof LabelViewHolder) {
            bindLabelViewHolder((LabelViewHolder) holder, position);
        } else if (holder instanceof GiftViewHolder) {
            bindGiftViewHolder((GiftViewHolder) holder, position);
        } else if (holder instanceof ThreeViewHolder) {

        } else if (holder instanceof ADViewHolder) {
            bindADViewHolder((ADViewHolder) holder, position);
        } else if (holder instanceof FoodsShopViewHolder) {
            bindFoodShopViewHolder((FoodsShopViewHolder) holder, position);
        } else if (holder instanceof FinanceViewHolder) {
            bindFinanceViewHolder((FinanceViewHolder) holder, position);
        } else if (holder instanceof GoodsViewHolder) {
            bindGoodViewHolder((GoodsViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return 10 + mFoodsData.size() + mFinanceData.size() + mGoodsData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HOME_TYPE_BANNER;
        } else if (position == 1) {
            return HOME_TYPE_TAB;
        } else if (position == 2) {
            return HOME_TYPE_SORT;
        } else if (position == 3) {
            return HOME_TYPE_HOT;
        }
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case HOME_TYPE_BANNER:
                        case HOME_TYPE_TAB:
                        case HOME_TYPE_SORT:
                        case HOME_TYPE_HOT:
                            return 3;
                        default:
                            return 3;
                    }
                }
            });
        }
    }

    private void bindBannerViewHolder(BannerViewHolder holder, int position) {
        List<String> imageUrls = new ArrayList<>();
        final List<String> adUrls = new ArrayList<>();
        for (int i = 0; i < mBannerData.length(); i++) {
            imageUrls.add(mBannerData.optJSONObject(i).optString("adFile"));
            adUrls.add(mBannerData.optJSONObject(i).optString("adTypeData"));
        }
        holder.banner.setImages(imageUrls).setImageLoader(new GlideImageLoader()).start();
        holder.banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                //注意这里的position是从1开始的

            }
        });
        holder.tvLocation.setText(LocationUtils.getInstance().getCity());
    }

    private void bindSortViewHolder(SortViewHolder holder, int position) {
        if (mCatData == null || mCatData.isEmpty()) {
            return;
        }
        int pageSize = 8;
        int totalPage = (int) Math.ceil(mCatData.size() * 1.0 / pageSize);
        List<RecyclerGridView> viewPagerList = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            RecyclerGridView gridView = (RecyclerGridView) View.inflate(mContext, R.layout.layout_home_cat_viewpager, null);
            gridView.setAdapter(new HomeCatAdapter(mContext, mCatData, i, pageSize));
            viewPagerList.add(gridView);
        }
        holder.viewPager.setAdapter(new HomeCatViewPagerAdapter(viewPagerList));
        //小圆点指示器
        mIvPoints = new ImageView[totalPage];
        holder.points.removeAllViews();
        for (int i = 0; i < mIvPoints.length; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            if (i == 0) {
                imageView.setBackgroundResource(R.mipmap.page__selected_indicator);
            } else {
                imageView.setBackgroundResource(R.mipmap.page__normal_indicator);
            }
            mIvPoints[i] = imageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            holder.points.addView(imageView, layoutParams);
        }
        holder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setImageBackground(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 改变点点点的切换效果
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < mIvPoints.length; i++) {
            if (i == selectItems) {
                mIvPoints[i].setBackgroundResource(R.mipmap.page__selected_indicator);
            } else {
                mIvPoints[i].setBackgroundResource(R.mipmap.page__normal_indicator);
            }
        }
    }

    private void bindMessageViewHolder(MessageViewHolder holder, int position) {
        holder.vfMessages.removeAllViews();
        if (mArticleData.length() % 2 == 0) {
            for (int i = 0; i < mArticleData.length(); i = i + 2) {
                final JSONObject data = mArticleData.optJSONObject(i);
                final JSONObject data1 = mArticleData.optJSONObject(i + 1);
                View view = View.inflate(mContext, R.layout.layout_home_message_item, null);
                ((TextView) view.findViewById(R.id.tv_message1)).setText(data.optString("articleTitle"));
                ((TextView) view.findViewById(R.id.tv_message2)).setText(data1.optString("articleTitle"));
                ((TextView) view.findViewById(R.id.tv_message1)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(mContext, data.optString("articleTitle"));
                    }
                });
                ((TextView) view.findViewById(R.id.tv_message2)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(mContext, data1.optString("articleTitle"));
                    }
                });
                holder.vfMessages.addView(view);
            }
        } else {
            for (int i = 0; i < mArticleData.length(); i = i + 2) {
                final JSONObject data = mArticleData.optJSONObject(i);
                final JSONObject data1;
                View view = View.inflate(mContext, R.layout.layout_home_message_item, null);
                if (i != (mArticleData.length() - 1)) {
                    data1 = mArticleData.optJSONObject(i + 1);
                    ((TextView) view.findViewById(R.id.tv_message2)).setText(data1.optString("articleTitle"));
                    ((TextView) view.findViewById(R.id.tv_message2)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtils.showShort(mContext, data1.optString("articleTitle"));
                        }
                    });
                }
                ((TextView) view.findViewById(R.id.tv_message1)).setText(data.optString("articleTitle"));
                ((TextView) view.findViewById(R.id.tv_message1)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(mContext, data.optString("articleTitle"));
                    }
                });
                holder.vfMessages.addView(view);
            }
        }
    }

    private void bindLabelViewHolder(LabelViewHolder holder, int position) {
        if (position == 4) {// 热门礼品
            holder.tvHomeLabelName.setText(R.string.string_home_hot_gift);
            holder.tvHomeLabelMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else if (position == 7) {// 热门餐厅
            holder.tvHomeLabelName.setText(R.string.string_home_hot_foods);
            holder.tvHomeLabelMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FoodShopListActivity.class);
                    intent.putExtra("catId", "366");
                    intent.putExtra("catName", "推荐餐饮");
                    intent.putExtra("start_from", "label");
                    mContext.startActivity(intent);
                }
            });
        } else if (position == (8 + mFoodsData.size())) {// 推荐金融
            holder.tvHomeLabelName.setText(R.string.string_home_hot_finance);
            holder.tvHomeLabelMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FoodShopListActivity.class);
                    intent.putExtra("catId", "373");
                    intent.putExtra("catName", "推荐金融");
                    intent.putExtra("start_from", "label");
                    mContext.startActivity(intent);
                }
            });
        } else if (position == (9 + mFoodsData.size() + mFinanceData.size())) { // 推荐商品
            holder.tvHomeLabelName.setText(R.string.string_home_home_goods);
            holder.tvHomeLabelMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, GoodsListActivity.class);
                    intent.putExtra("titleName", "推荐商品");
                    mContext.startActivity(intent);
                }
            });
        }
    }

    private void bindGiftViewHolder(GiftViewHolder holder, int position) {
        if (mGiftData.isEmpty()) {
            return;
        }
        if (mGiftData.size() == 5) {
            holder.llHomeGiftBottom.setVisibility(View.VISIBLE);
            holder.goodFirst.setData(mGiftData.get(0));
            holder.goodSecond.setData(mGiftData.get(1));
            holder.goodThird.setData(mGiftData.get(2));
            holder.goodForth.setData(mGiftData.get(3));
            holder.goodForth.setOnClickListener(mListener);
        } else if (mGiftData.size() == 3) {
            holder.llHomeGiftBottom.setVisibility(View.GONE);
            holder.goodFirst.setData(mGiftData.get(0));
            holder.goodSecond.setData(mGiftData.get(1));
            holder.goodThird.setData(mGiftData.get(2));
        }
        holder.goodFirst.setOnClickListener(mListener);
        holder.goodSecond.setOnClickListener(mListener);
        holder.goodThird.setOnClickListener(mListener);
    }

    private void bindADViewHolder(ADViewHolder holder, int position) {
        if (mAdsData == null || mAdsData.isNull(0)) {
            return;
        }
        JSONObject json = mAdsData.optJSONObject(0);
        Glide.with(mContext).load(json.optString("adFile")).into(holder.ivHomeAd);
        holder.ivHomeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(mContext, "click ad!");
            }
        });
    }

    private void bindFoodShopViewHolder(FoodsShopViewHolder holder, int position) {
        int pos = position - 8;
        final HomeFoodShopBean foodShopBean = mFoodsData.get(pos);
        holder.tvHomeFoodItemName.setText(foodShopBean.getShopName());
        if (Float.valueOf(foodShopBean.getDistance()) <= 0) {
            holder.tvHomeFoodItemDistance.setVisibility(View.INVISIBLE);
        } else {
            holder.tvHomeFoodItemDistance.setVisibility(View.VISIBLE);
            holder.tvHomeFoodItemDistance.setText(String.format("%skm", foodShopBean.getDistance()));
        }
        holder.tvHomeFoodItemTips.setText(String.format("地址:%s", foodShopBean.getShopAddress()));
        holder.tvHomeFoodItemSaleNum.setText(String.format("销量:%s", foodShopBean.getSaleCount()));
        holder.tvHomeFoodItemPrice.setText(String.format("增加:%s", foodShopBean.getScoreRate()));
        holder.tvHomeFoodItemPrice.setText(String.format("增加:%s%%",
                String.format(Locale.CHINA, "%.0f", Float.parseFloat(foodShopBean.getScoreRate()) * 100)));
        Glide.with(mContext).load(foodShopBean.getShopImg()).into(holder.ivHomeFoodItemIcon);
        holder.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, FoodShopDetailActivity.class);
                intent.putExtra("shopId", foodShopBean.getShopId());
                intent.putExtra("shopName", foodShopBean.getShopName());
                mContext.startActivity(intent);
            }
        });
    }

    private void bindFinanceViewHolder(FinanceViewHolder holder, int position) {
        int pos = position - (9 + mFoodsData.size());
        final HomeFinanceBean financeBean = mFinanceData.get(pos);
        holder.tvHomeFinanceItemName.setText(financeBean.getShopName());
        holder.tvHomeFinanceItemTips.setText(String.format("%s", financeBean.getShopAddress()));
        holder.tvHomeFinanceItemSaleNum.setText(String.format("销量:%s", financeBean.getSaleCount()));
        holder.tvHomeFinanceItemPrice.setText(String.format("增加:%s", financeBean.getScoreRate()));
        holder.tvHomeFinanceItemPrice.setText(String.format("增加:%s%%",
                String.format(Locale.CHINA, "%.0f", Float.parseFloat(financeBean.getScoreRate()) * 100)));
        Glide.with(mContext).load(financeBean.getShopImg()).into(holder.ivHomeFinanceItemIcon);
        holder.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, FoodShopDetailActivity.class);
                intent.putExtra("shopId", financeBean.getShopId());
                intent.putExtra("shopName", financeBean.getShopName());
                mContext.startActivity(intent);
            }
        });
    }

    private void bindGoodViewHolder(GoodsViewHolder holder, int position) {
        int pos = position - (10 + mFoodsData.size() + mFinanceData.size());
        HomeGoodBean goodBean = mGoodsData.get(pos);
        holder.tvHomeGoodsName.setText(goodBean.getGoodsName());
        holder.tvHomeGoodsTips.setText(goodBean.getGoodsTips());
        holder.tvHomeGoodsPrice.setText(String.format("￥%s", goodBean.getShopPrice()));
        holder.tvHomeGoodsBackNum.setText(String.format("增加:%s", goodBean.getReturnPrice()));
        Glide.with(mContext).load(goodBean.getGoodsImg()).into(holder.ivHomeGoodsImage);
        holder.setGoodData(goodBean);
    }

    // Banner
    public class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        Banner banner;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.et_search)
        EditText etSearch;

        public BannerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.et_search)
        public void onViewClicked() {
            Intent intent = new Intent(mContext, SearchActivity.class);
            mContext.startActivity(intent);
        }

    }

    // 分类
    public class SortViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vp_container)
        ViewPager viewPager;
        @BindView(R.id.vp_points)
        LinearLayout points;

        public SortViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    // 广告
    public class MessageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vf_messages)
        ViewFlipper vfMessages;
        @BindView(R.id.iv_home_article_right)
        ImageView ivHomeArticleRight;

        public MessageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.iv_home_article_right)
        public void onViewClick(View v) {
            Intent intent = new Intent(mContext, ArticleListActivity.class);
            mContext.startActivity(intent);
        }
    }

    // 三张图片
    public class ThreeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_home_three_first)
        ImageView ivHomeThreeFirst;
        @BindView(R.id.iv_home_three_second)
        ImageView ivHomeThreeSecond;
        @BindView(R.id.iv_home_three_third)
        ImageView ivHomeThreeThird;

        public ThreeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick({R.id.iv_home_three_first, R.id.iv_home_three_second, R.id.iv_home_three_third})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.iv_home_three_first:
                    Intent intent = new Intent(mContext, RechargeActivity.class);
                    mContext.startActivity(intent);
                    break;
                case R.id.iv_home_three_second:
                    ToastUtils.showShort(mContext, "请耐心等待，功能正在建设中...");
                    break;
                case R.id.iv_home_three_third:
                    ToastUtils.showShort(mContext, "请耐心等待，功能正在建设中...");
                    break;
            }
        }

    }

    // label
    public class LabelViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_home_label_name)
        TextView tvHomeLabelName;
        @BindView(R.id.tv_home_label_more)
        TextView tvHomeLabelMore;

        public LabelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    // 广告
    public class ADViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_home_ad)
        ImageView ivHomeAd;

        public ADViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    // 热门礼品
    public class GiftViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.good_first)
        HomeBigGoodView goodFirst;
        @BindView(R.id.good_second)
        HomeSmallGoodView goodSecond;
        @BindView(R.id.good_third)
        HomeSmallGoodView goodThird;
        @BindView(R.id.good_forth)
        HomeSmallGoodView goodForth;
        @BindView(R.id.ll_home_gift_bottom)
        LinearLayout llHomeGiftBottom;

        public GiftViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    // 推荐商品
    public class GoodsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_home_goods_image)
        ImageView ivHomeGoodsImage;
        @BindView(R.id.iv_home_goods_type)
        ImageView ivHomeGoodsType;
        @BindView(R.id.tv_home_goods_name)
        TextView tvHomeGoodsName;
        @BindView(R.id.tv_home_goods_tips)
        TextView tvHomeGoodsTips;
        @BindView(R.id.tv_home_goods_price)
        TextView tvHomeGoodsPrice;
        @BindView(R.id.tv_home_goods_back_num)
        TextView tvHomeGoodsBackNum;

        private HomeGoodBean mBean;

        public void setGoodData(HomeGoodBean bean) {
            this.mBean = bean;
        }

        public GoodsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, GoodDetailActivity.class);
                    intent.putExtra("goodsId", mBean.getGoodsId());
                    intent.putExtra("goodsName", mBean.getGoodsName());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, GoodDetailActivity.class);
            switch (v.getId()) {
                case R.id.good_first:
                    intent.putExtra("goodsId", mGiftData.get(0).getGoodsId());
                    intent.putExtra("goodsName", mGiftData.get(0).getGoodsName());
                    mContext.startActivity(intent);
                    break;
                case R.id.good_second:
                    intent.putExtra("goodsId", mGiftData.get(1).getGoodsId());
                    intent.putExtra("goodsName", mGiftData.get(1).getGoodsName());
                    mContext.startActivity(intent);
                    break;
                case R.id.good_third:
                    intent.putExtra("goodsId", mGiftData.get(2).getGoodsId());
                    intent.putExtra("goodsName", mGiftData.get(2).getGoodsName());
                    mContext.startActivity(intent);
                    break;
                case R.id.good_forth:
                    intent.putExtra("goodsId", mGiftData.get(3).getGoodsId());
                    intent.putExtra("goodsName", mGiftData.get(3).getGoodsName());
                    mContext.startActivity(intent);
                    break;

            }
        }
    };

}
