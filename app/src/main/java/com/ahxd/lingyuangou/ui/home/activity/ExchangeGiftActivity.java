package com.ahxd.lingyuangou.ui.home.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.MarketingBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.listener.OnMarketingListener;
import com.ahxd.lingyuangou.ui.home.adapter.ExchangeGiftCatAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.HomeAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.HomeCatAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.HomeCatViewPagerAdapter;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeGiftContract;
import com.ahxd.lingyuangou.ui.home.presenter.ExchangeGiftPresenter;
import com.ahxd.lingyuangou.ui.mine.activity.ShopManagemntDetailActivity;
import com.ahxd.lingyuangou.ui.mine.adapter.ShopManagementListAdapter;
import com.ahxd.lingyuangou.ui.mine.contract.IShopManagementContract;
import com.ahxd.lingyuangou.ui.mine.presenter.ShopManagementPresenter;
import com.ahxd.lingyuangou.utils.GlideImageLoader;
import com.ahxd.lingyuangou.utils.LocationUtils;
import com.ahxd.lingyuangou.widget.RecyclerGridView;
import com.ahxd.lingyuangou.widget.RecyclerViewDivider;
import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wpc on 2018/1/18.
 * 积分商城
 */
public class ExchangeGiftActivity extends BaseActivity implements IExchangeGiftContract.IExchangeGiftView {

    @BindView(R.id.srl_list_container)
    SmartRefreshLayout srlListContainer;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.vp_container)
    ViewPager viewPager;
    @BindView(R.id.vp_points)
    LinearLayout points;
    @BindView(R.id.ll_addview)
    LinearLayout llAddview;
    @BindView(R.id.ll_shop_list_search)
    LinearLayout llShopListSearch;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    private int mPage=1;
    private ExchangeBean  exchangeBean;
    private List<ExchangeBean.ExchangeHots> mHots=new ArrayList<>();
    private ExchangeGiftPresenter mPresenter;
    private ImageView[] mIvPoints;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("积分商城");
    }

    @Override
    protected void initListener() {
        srlListContainer.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                loadData();
            }
        });

        srlListContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mHots.clear();
                llAddview.removeAllViews();
                loadData();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter = new ExchangeGiftPresenter(this);
        loadData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exchang_gift;
    }

    @OnClick({R.id.ll_shop_list_search,R.id.ll_record})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ll_shop_list_search:
                Intent intent = new Intent(this, ExchangeGiftListActivity.class);
                intent.putExtra("catName", "兑换列表");
                startActivity(intent);
                break;
            case R.id.ll_record:
                Intent ecordIntent = new Intent(this, ExchangeRecordListActivity.class);
                startActivity(ecordIntent);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_EDIT_ADDRESS && resultCode == RESULT_OK) {
            mPresenter.getHomeData(mPage);
        }
    }

    private void loadData() {
        mPresenter.getHomeData(mPage);
    }

    @Override
    public void showBanner(ExchangeBean bean) {
        exchangeBean=bean;
        tvPrice.setText(exchangeBean.getScore());
        List<String> imageUrls = new ArrayList<>();
        final List<String> adUrls = new ArrayList<>();
        for (int i = 0; i < exchangeBean.getBanner().size(); i++) {
            imageUrls.add(exchangeBean.getBanner().get(i).getAdFile());
            adUrls.add(exchangeBean.getBanner().get(i).getAdTypeData());
        }
        banner.setImages(imageUrls).setImageLoader(new GlideImageLoader()).start();
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                //注意这里的position是从1开始的
            }
        });
    }

    @Override
    public void showCats(ExchangeBean cats) {
        exchangeBean=cats;
        if (exchangeBean.getCats() == null) {
            return;
        }
        int pageSize = 8;
        int totalPage = (int) Math.ceil(exchangeBean.getCats().size() * 1.0 / pageSize);
        List<RecyclerGridView> viewPagerList = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            RecyclerGridView gridView = (RecyclerGridView) View.inflate(this, R.layout.layout_home_cat_viewpager, null);
            gridView.setAdapter(new ExchangeGiftCatAdapter(this, exchangeBean.getCats(), i, pageSize));
            viewPagerList.add(gridView);
        }
        viewPager.setAdapter(new HomeCatViewPagerAdapter(viewPagerList));
        //小圆点指示器
        mIvPoints = new ImageView[totalPage];
        points.removeAllViews();
        for (int i = 0; i < mIvPoints.length; i++) {
            ImageView imageView = new ImageView(this);
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
            points.addView(imageView, layoutParams);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    @Override
    public void showHots(ExchangeBean hots) {
        srlListContainer.finishRefresh();
        srlListContainer.finishLoadmore();
        if (hots.getHots() != null) {
            mHots.addAll(hots.getHots());
            for (int i=0;i<hots.getHots().size();i++){
                addDateView(hots.getHots().get(i));
            }
        }


    }

    @SuppressLint("SimpleDateFormat")
    private void addDateView(final ExchangeBean.ExchangeHots bean) {
        LayoutInflater factory = LayoutInflater.from(this);
        final View view = factory.inflate(R.layout.item_exchange_gift, null);

        TextView tvName= (TextView) view.findViewById(R.id.tv_exchange_item_tips);
        ImageView imIcon= (ImageView) view.findViewById(R.id.iv_exchange_item_icon);
        TextView tvPrice= (TextView) view.findViewById(R.id.tv_exchange_item_price);
        TextView tvPriceOld= (TextView) view.findViewById(R.id.tv_exchange_item_price_old);
        TextView tvSaleNum= (TextView) view.findViewById(R.id.tv_exchange_item_sale_num);

        tvName.setText(bean.getGoodsName());
        tvPrice.setText("需要货币:"+bean.getShopPrice());
        tvPriceOld.setText("原货币:"+bean.getMarketPrice());
        tvPriceOld.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        tvSaleNum.setText("已兑换:"+bean.getSaleNum());
        Glide.with(this).load(bean.getGoodsImg()).into(imIcon);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExchangeGiftActivity.this, GiftGoodDetailActivity.class);
                intent.putExtra("goodsId", bean.getGoodsId());
                intent.putExtra("goodsName", bean.getGoodsName());
                startActivity(intent);
            }
        });

        llAddview.addView(view);

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
}