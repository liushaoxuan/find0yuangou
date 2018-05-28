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
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.listener.OnItemClickListener;
import com.ahxd.lingyuangou.ui.home.activity.ArticleListActivity;
import com.ahxd.lingyuangou.ui.home.activity.ExchangeGiftActivity;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopDetailActivity;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopListActivity;
import com.ahxd.lingyuangou.ui.home.activity.GiftGoodDetailActivity;
import com.ahxd.lingyuangou.ui.home.activity.GiftShopListActivity;
import com.ahxd.lingyuangou.ui.home.activity.GoodDetailActivity;
import com.ahxd.lingyuangou.ui.home.activity.GoodsListActivity;
import com.ahxd.lingyuangou.ui.home.activity.RechargeActivity;
import com.ahxd.lingyuangou.ui.home.activity.SearchActivity;
import com.ahxd.lingyuangou.ui.home.holder.EducationsShopViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.EntertainmentsShopViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.FinanceViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.FoodsShopViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.HotelsShopViewHolder;
import com.ahxd.lingyuangou.utils.GlideImageLoader;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.HomeBigGoodView;
import com.ahxd.lingyuangou.widget.HomeSmallGoodView;
import com.ahxd.lingyuangou.widget.RecyclerGridView;
import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
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

public class HomeAdapter extends RecyclerView.Adapter {

    // 轮播banner
    private static final int HOME_TYPE_BANNER = 0;
    // 分类
    private static final int HOME_TYPE_SORT = 1;
    // 广告
    private static final int HOME_TYPE_MESSAGE = 2;
    //
    private static final int HOME_TYPE_THREE = 3;
    // 热门礼品 内容
    private static final int HOME_TYPE_GIFT = 4;
    // LABEL
    private static final int HOME_TYPE_LABEL = 5;
    // 图片广告
    private static final int HOME_TYPE_AD = 6;
    // 餐饮
    private static final int HOME_TYPE_FOOD = 7;
    // 推荐酒店
    private static final int HOME_TYPE_HOTEL = 10;
    // 推荐教育
    private static final int HOME_TYPE_EDUCATION = 11;
    // 推荐娱乐
    private static final int HOME_TYPE_AMUSEEMENT = 12;
    // 金融
    private static final int HOME_TYPE_FINANCE = 8;
    // 推荐商品
    private static final int HOME_TYPE_GOOD = 9;


    private Context mContext;
    private LayoutInflater mInflater;

    private ImageView[] mIvPoints;

    private JSONArray mBannerData = new JSONArray();
    private JSONArray mArticleData = new JSONArray();
    private JSONArray mAdsData = new JSONArray();
    private List<HomeCatBean> mCatData = new ArrayList<>();
    private List<HomeGiftBean> mGiftData = new ArrayList<>();
    private List<HomeFoodShopBean> mFoodsData = new ArrayList<>();

    private List<HomeFoodShopBean> mHotelsData = new ArrayList<>();
    private List<HomeFoodShopBean> mEducationsData = new ArrayList<>();
    private List<HomeFoodShopBean> mEntertainmentsData = new ArrayList<>();

    private List<HomeFinanceBean> mFinanceData = new ArrayList<>();
    private List<HomeGoodBean> mGoodsData = new ArrayList<>();



    public HomeAdapter(Context context) {
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
//        mCatData.add(new HomeCatBean("123456","商务","https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3502465005,4153501499&fm=200&gp=0.jpg"));
//        mCatData.add(new HomeCatBean("1234567","家政","https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3502465005,4153501499&fm=200&gp=0.jpg"));
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

    public void setHotelsData(List<HomeFoodShopBean> hotels) {
        this.mHotelsData = hotels;
    }

    public void setEducationsData(List<HomeFoodShopBean> educations) {
        this.mEducationsData = educations;
    }

    public void setEntertainmentsData(List<HomeFoodShopBean> entertainments) {
        this.mEntertainmentsData = entertainments;
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
            case HOME_TYPE_MESSAGE:
                return new MessageViewHolder(mInflater.inflate(R.layout.layout_home_message, parent, false));
            case HOME_TYPE_THREE:
                return new ThreeViewHolder(mInflater.inflate(R.layout.layout_home_three, parent, false));
            case HOME_TYPE_GIFT:
                return new GiftViewHolder(mInflater.inflate(R.layout.layout_home_gift, parent, false));
            case HOME_TYPE_LABEL:
                return new LabelViewHolder(mInflater.inflate(R.layout.layout_home_label, parent, false));
            case HOME_TYPE_AD:
                return new ADViewHolder(mInflater.inflate(R.layout.layout_home_ad, parent, false));
            case HOME_TYPE_FOOD:
                return new FoodsShopViewHolder(mInflater.inflate(R.layout.item_home_foods_shop, parent, false));

            case HOME_TYPE_HOTEL:
                return new HotelsShopViewHolder(mInflater.inflate(R.layout.item_home_foods_shop, parent, false));
            case HOME_TYPE_EDUCATION:
                return new EducationsShopViewHolder(mInflater.inflate(R.layout.item_home_foods_shop, parent, false));
            case HOME_TYPE_AMUSEEMENT:
                return new EntertainmentsShopViewHolder(mInflater.inflate(R.layout.item_home_foods_shop, parent, false));

            case HOME_TYPE_FINANCE:
                return new FinanceViewHolder(mInflater.inflate(R.layout.item_home_finance, parent, false));
            case HOME_TYPE_GOOD:
                return new GoodsViewHolder(mInflater.inflate(R.layout.item_home_hot_good, parent, false));
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
        }  else if (holder instanceof HotelsShopViewHolder) {
            bindHotelShopViewHolder((HotelsShopViewHolder) holder, position);
        }  else if (holder instanceof EducationsShopViewHolder) {
            bindEducationShopViewHolder((EducationsShopViewHolder) holder, position);
        }  else if (holder instanceof EntertainmentsShopViewHolder) {
            bindEntertainmentShopViewHolder((EntertainmentsShopViewHolder) holder, position);
        } else if (holder instanceof FinanceViewHolder) {
            bindFinanceViewHolder((FinanceViewHolder) holder, position);
        } else if (holder instanceof GoodsViewHolder) {
            bindGoodViewHolder((GoodsViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {

        return 13 + mFoodsData.size()+mHotelsData.size() +mEducationsData.size()
                +mEntertainmentsData.size() + mFinanceData.size() + mGoodsData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HOME_TYPE_BANNER;
        } else if (position == 1) {
            return HOME_TYPE_SORT;
        } else if (position == 2) {
            return HOME_TYPE_MESSAGE;
        } else if (position == 3) {
            return HOME_TYPE_THREE;
        } else if (position == 4 || position == 7 || position == 8 + mFoodsData.size()
                ||  position == (9 + mFoodsData.size()+mHotelsData.size())
                ||position == (10 + mFoodsData.size() + mHotelsData.size()+mEducationsData.size())
                || position == (11 + mFoodsData.size() + mHotelsData.size()+mEducationsData.size()+mEntertainmentsData.size())
                || position ==(12 + mFoodsData.size() + mHotelsData.size()+mEducationsData.size()+mEntertainmentsData.size()+mFinanceData.size())) {
            return HOME_TYPE_LABEL;
        } else if (position == 5) {
            return HOME_TYPE_GIFT;
        } else if (position == 6) {
            return HOME_TYPE_AD;
        } else if (7 < position && position <= 7 + mFoodsData.size()) {
            return HOME_TYPE_FOOD;
        } else if ((8 + mFoodsData.size()) < position && position <= ((8 + mFoodsData.size()) + mHotelsData.size())) {
            return HOME_TYPE_HOTEL;
        }else if ((9 + mFoodsData.size() + mHotelsData.size()) < position && position <= (9 + mFoodsData.size() + mHotelsData.size()+mEducationsData.size())) {
            return HOME_TYPE_EDUCATION;
        }else if ((10 +(mFoodsData.size() + mHotelsData.size()+mEducationsData.size())) < position && position <= ((10 + mFoodsData.size()) + mHotelsData.size()+mEducationsData.size()+mEntertainmentsData.size())) {
            return HOME_TYPE_AMUSEEMENT;
        }else if ((11 + mFoodsData.size()+mHotelsData.size()+mEducationsData.size()+mEntertainmentsData.size()) < position && position <= ((11 + mFoodsData.size()) +mHotelsData.size()+mEducationsData.size()+mEntertainmentsData.size()+ mFinanceData.size())) {
            return HOME_TYPE_FINANCE;
        } else {
            return HOME_TYPE_GOOD;
        }
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
                        case HOME_TYPE_SORT:
                        case HOME_TYPE_MESSAGE:
                        case HOME_TYPE_THREE:
                        case HOME_TYPE_GIFT:
                        case HOME_TYPE_AD:
                        case HOME_TYPE_LABEL:
                        case HOME_TYPE_FOOD:
                        case HOME_TYPE_HOTEL:
                        case HOME_TYPE_EDUCATION:
                        case HOME_TYPE_AMUSEEMENT:
                        case HOME_TYPE_FINANCE:
                            return gridManager.getSpanCount();
                        case HOME_TYPE_GOOD:
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
    }

    private void bindSortViewHolder(SortViewHolder holder, int position) {
        if (mCatData == null || mCatData.isEmpty()) {
            return;
        }
        int pageSize = 8;
        int totalPage = (int) Math.ceil(mCatData.size() * 1.0 / pageSize);
        List<RecyclerView> viewPagerList = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
//            RecyclerGridView gridView = (RecyclerGridView) View.inflate(mContext, R.layout.layout_home_cat_viewpager, null);
            RecyclerView recyclerView = (RecyclerView) View.inflate(mContext, R.layout.layout_home_sort_new, null);
            FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(mContext);
            layoutManager.setFlexDirection(FlexDirection.COLUMN_REVERSE);
            layoutManager.setJustifyContent(JustifyContent.FLEX_END);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(new HomeCatAdapterNew(mContext, mCatData, i, pageSize));
            viewPagerList.add(recyclerView);
        }
        holder.viewPager.requestFocus();
        holder.viewPager.setAdapter(new HomeCatViewPagerAdapterNew(viewPagerList));
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
            holder.tvHomeLabelMore.setVisibility(View.INVISIBLE);
            holder.tvHomeLabelMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        } else if (position == 7) {// 热门餐厅
            holder.tvHomeLabelName.setText(R.string.string_home_hot_foods);
            holder.tvHomeLabelMore.setVisibility(View.VISIBLE);
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
        }
//        position == 4 || position == 7 || position == 8 + mFoodsData.size()
//                ||  position == (9 + mFoodsData.size()+mHotelsData.size())
//                ||position == (10 + mFoodsData.size() + mHotelsData.size()+mEducationsData.size())
//                || position == (11 + mFoodsData.size() + mHotelsData.size()+mEducationsData.size()+mAmuseementdsData.size())
//                || position ==(12 + mFoodsData.size() + mHotelsData.size()+mEducationsData.size()+mAmuseementdsData.size()+mFinanceData.size())
        else if (position == (8 + mFoodsData.size())) {// 推荐酒店
            holder.tvHomeLabelName.setText(R.string.string_home_hot_hotels);
            holder.tvHomeLabelMore.setVisibility(View.VISIBLE);
            holder.tvHomeLabelMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FoodShopListActivity.class);
                    intent.putExtra("catId", "367");
                    intent.putExtra("catName", "推荐酒店");
                    intent.putExtra("start_from", "label");
                    mContext.startActivity(intent);
                }
            });
        } else if (position == (9 + mFoodsData.size()+mHotelsData.size())) {// 推荐教育
            holder.tvHomeLabelName.setText(R.string.string_home_hot_educations);
            holder.tvHomeLabelMore.setVisibility(View.VISIBLE);
            holder.tvHomeLabelMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FoodShopListActivity.class);
                    intent.putExtra("catId", "368");
                    intent.putExtra("catName", "推荐教育");
                    intent.putExtra("start_from", "label");
                    mContext.startActivity(intent);
                }
            });
        } else if (position == (10 + mFoodsData.size()+mHotelsData.size()+mEducationsData.size())) {// 推荐娱乐
            holder.tvHomeLabelName.setText(R.string.string_home_hot_amuseementds);
            holder.tvHomeLabelMore.setVisibility(View.VISIBLE);
            holder.tvHomeLabelMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FoodShopListActivity.class);
                    intent.putExtra("catId", "369");
                    intent.putExtra("catName", "推荐娱乐");
                    intent.putExtra("start_from", "label");
                    mContext.startActivity(intent);
                }
            });
        } else if (position == (11 + mFoodsData.size()+mHotelsData.size()+mEducationsData.size()+mEntertainmentsData.size())) {// 推荐金融
            holder.tvHomeLabelName.setText(R.string.string_home_hot_finance);
            holder.tvHomeLabelMore.setVisibility(View.VISIBLE);
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
        } else if (position == (12 + mFoodsData.size() + mHotelsData.size()+mEducationsData.size()+mEntertainmentsData.size()+ mFinanceData.size())) { // 推荐商品
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
            holder.goodFifth.setData(mGiftData.get(4));
            holder.goodForth.setOnClickListener(mListener);
            holder.goodFifth.setOnClickListener(mListener);
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
        holder.tvHomeFoodItemPrice.setText(String.format("返货币:%s", foodShopBean.getScoreRate()));
        holder.tvHomeFoodItemPrice.setText(String.format("返货币:%s%%",
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

    private void bindHotelShopViewHolder(HotelsShopViewHolder holder, int position) {
        int pos = position - (9 + mFoodsData.size());
        final HomeFoodShopBean foodShopBean = mHotelsData.get(pos);
        holder.tvHomeFoodItemName.setText(foodShopBean.getShopName());
        if (Float.valueOf(foodShopBean.getDistance()) <= 0) {
            holder.tvHomeFoodItemDistance.setVisibility(View.INVISIBLE);
        } else {
            holder.tvHomeFoodItemDistance.setVisibility(View.VISIBLE);
            holder.tvHomeFoodItemDistance.setText(String.format("%skm", foodShopBean.getDistance()));
        }
        holder.tvHomeFoodItemTips.setText(String.format("地址:%s", foodShopBean.getShopAddress()));
        holder.tvHomeFoodItemSaleNum.setText(String.format("销量:%s", foodShopBean.getSaleCount()));
        holder.tvHomeFoodItemPrice.setText(String.format("返货币:%s", foodShopBean.getScoreRate()));
        holder.tvHomeFoodItemPrice.setText(String.format("返货币:%s%%",
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

    private void bindEducationShopViewHolder(EducationsShopViewHolder holder, int position) {
        int pos = position - (10 + mFoodsData.size()+mHotelsData.size());
        final HomeFoodShopBean foodShopBean = mEducationsData.get(pos);
        holder.tvHomeFoodItemName.setText(foodShopBean.getShopName());
        if (Float.valueOf(foodShopBean.getDistance()) <= 0) {
            holder.tvHomeFoodItemDistance.setVisibility(View.INVISIBLE);
        } else {
            holder.tvHomeFoodItemDistance.setVisibility(View.VISIBLE);
            holder.tvHomeFoodItemDistance.setText(String.format("%skm", foodShopBean.getDistance()));
        }
        holder.tvHomeFoodItemTips.setText(String.format("地址:%s", foodShopBean.getShopAddress()));
        holder.tvHomeFoodItemSaleNum.setText(String.format("销量:%s", foodShopBean.getSaleCount()));
        holder.tvHomeFoodItemPrice.setText(String.format("返货币:%s", foodShopBean.getScoreRate()));
        holder.tvHomeFoodItemPrice.setText(String.format("返货币:%s%%",
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

    private void bindEntertainmentShopViewHolder(EntertainmentsShopViewHolder holder, int position) {
        int pos = position - (11 + mFoodsData.size()+mHotelsData.size()+mEducationsData.size());
        final HomeFoodShopBean foodShopBean = mEntertainmentsData.get(pos);
        holder.tvHomeFoodItemName.setText(foodShopBean.getShopName());
        if (Float.valueOf(foodShopBean.getDistance()) <= 0) {
            holder.tvHomeFoodItemDistance.setVisibility(View.INVISIBLE);
        } else {
            holder.tvHomeFoodItemDistance.setVisibility(View.VISIBLE);
            holder.tvHomeFoodItemDistance.setText(String.format("%skm", foodShopBean.getDistance()));
        }
        holder.tvHomeFoodItemTips.setText(String.format("地址:%s", foodShopBean.getShopAddress()));
        holder.tvHomeFoodItemSaleNum.setText(String.format("销量:%s", foodShopBean.getSaleCount()));
        holder.tvHomeFoodItemPrice.setText(String.format("返货币:%s", foodShopBean.getScoreRate()));
        holder.tvHomeFoodItemPrice.setText(String.format("返货币:%s%%",
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
        int pos = position - (12 + mFoodsData.size()+mHotelsData.size()+mEducationsData.size()+mEntertainmentsData.size());
        final HomeFinanceBean financeBean = mFinanceData.get(pos);
        holder.tvHomeFinanceItemName.setText(financeBean.getShopName());
        holder.tvHomeFinanceItemTips.setText(String.format("%s", financeBean.getShopAddress()));
        holder.tvHomeFinanceItemSaleNum.setText(String.format("销量:%s", financeBean.getSaleCount()));
        holder.tvHomeFinanceItemPrice.setText(String.format("返货币:%s", financeBean.getScoreRate()));
        holder.tvHomeFinanceItemPrice.setText(String.format("返货币:%s%%",
                String.format(Locale.CHINA, "%.0f", Float.parseFloat(financeBean.getScoreRate()) * 100)));
        Glide.with(mContext).load(financeBean.getShopImg()).into(holder.ivHomeFinanceItemIcon);
        holder.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, FoodShopDetailActivity.class);
                intent.putExtra("shopId", financeBean.getShopId());
                intent.putExtra("shopName", financeBean.getShopName());
                intent.putExtra("catId", "373");
                mContext.startActivity(intent);
            }
        });
    }

    private void bindGoodViewHolder(GoodsViewHolder holder, int position) {
        int pos = position - (13 + mFoodsData.size() + mHotelsData.size()+mEducationsData.size()+mEntertainmentsData.size()+ mFinanceData.size());
        HomeGoodBean goodBean = mGoodsData.get(pos);
        holder.tvHomeGoodsName.setText(goodBean.getGoodsName());
        holder.tvHomeGoodsTips.setText(goodBean.getGoodsTips());
        holder.tvHomeGoodsPrice.setText(String.format("￥%s", goodBean.getShopPrice()));
        holder.tvHomeGoodsBackNum.setText(String.format("返货币:%s", goodBean.getReturnPrice()));
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
//                    if (!UserUtils.isLogin()) {
//                        Intent intent2 = new Intent(mContext, LoginActivity.class);
//                        mContext.startActivity(intent2);
//                    }else {
                        Intent exchangeGiftIntent = new Intent(mContext, ExchangeGiftActivity.class);
                        mContext.startActivity(exchangeGiftIntent);
//                    }

//                    ToastUtils.showShort(mContext, "请耐心等待，功能正在建设中...");
                    break;
                case R.id.iv_home_three_third:
//                    if (!UserUtils.isLogin()) {
//                        Intent intent2 = new Intent(mContext, LoginActivity.class);
//                        mContext.startActivity(intent2);
//                    }else {
                        Intent gifyShopIntent = new Intent(mContext, GiftShopListActivity.class);
                        mContext.startActivity(gifyShopIntent);
//                    }

//                    ToastUtils.showShort(mContext, "请耐心等待，功能正在建设中...");
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
        @BindView(R.id.good_fifth)
        HomeSmallGoodView goodFifth;
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
            Intent intent = new Intent(mContext, GiftGoodDetailActivity.class);
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
                case R.id.good_fifth:
                    intent.putExtra("goodsId", mGiftData.get(4).getGoodsId());
                    intent.putExtra("goodsName", mGiftData.get(4).getGoodsName());
                    mContext.startActivity(intent);
                    break;
            }
        }
    };

}
