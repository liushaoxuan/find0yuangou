package com.ahxd.lingyuangou.ui.main.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseFragment;
import com.ahxd.lingyuangou.bean.AdsBean;
import com.ahxd.lingyuangou.bean.ArticlesBean;
import com.ahxd.lingyuangou.bean.BannerBean;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.bean.HomeFinanceBean;
import com.ahxd.lingyuangou.bean.HomeFoodShopBean;
import com.ahxd.lingyuangou.bean.HomeGiftBean;
import com.ahxd.lingyuangou.bean.HomeGoodBean;
import com.ahxd.lingyuangou.bean.HomeRecomendCarBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.listener.OnItemClickListener;
import com.ahxd.lingyuangou.ui.home.activity.HotGoodsAdapter;
import com.ahxd.lingyuangou.ui.home.activity.SearchActivity;
import com.ahxd.lingyuangou.ui.home.adapter.AdvertisingAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.BannerAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.ClassificationAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.DialogChoseCityAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.ExchangeMallAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.HomeLinearAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.LaberAcapter;
import com.ahxd.lingyuangou.ui.home.adapter.PictureAdsAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.RecommendCarAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.RecommendCommerceAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.RecommendEduAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.RecommendEntertainmentAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.RecommendFinancesAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.RecommendFoodsAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.RecommendHealthAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.RecommendHotelAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.RecommendHouseHoldAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.RecommendHouseKeepingAdapter;
import com.ahxd.lingyuangou.ui.home.contract.IHomeContract;
import com.ahxd.lingyuangou.ui.home.presenter.HomePresenter;
import com.ahxd.lingyuangou.ui.main.activity.MainActivity;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.LocationUtils;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.widget.RecyclerViewDivider;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.fastjson.JSON;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/25.
 */

public class HomeFragment extends BaseFragment implements IHomeContract.IHomeView {

    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.srl_home)
    SmartRefreshLayout srlHome;
    /**
     * 位置
     */
    @BindView(R.id.tv_location)
    TextView mTv_location;

    private HomePresenter mPresenter;
    private LocationBroadcastReceiver mLocationReceiver;

    private DialogChoseCityAdapter cityAdapter;

    //轮播图集合
    private List<BannerBean> bannerList;
    private BannerAdapter bannerAdapter;

    //分类集合
    private List<HomeCatBean> catList;
    private ClassificationAdapter classificationAdapter;

    //上下滚动广告
    private AdvertisingAdapter advertisingAdapter;
    private List<ArticlesBean> articlesBeans;

    //货币兑换商城
    private ExchangeMallAdapter exchangeMallAdapter;
    private List<HomeGiftBean> giftBeans ;

    //图片广告
    private PictureAdsAdapter pictureAdsAdapter;
    private List<AdsBean> adsBeans;

    //推荐美食
    private RecommendFoodsAdapter foodsAdapter;
    private List<HomeFoodShopBean> foodsList;

    //推荐酒店
    private RecommendHotelAdapter hotelAdapter;
    private List<HomeFoodShopBean> hotelList;
    //推荐教育
    private RecommendEduAdapter eduAdapter;
    private List<HomeFoodShopBean> eduList;

    //推荐娱乐
    private RecommendEntertainmentAdapter entertainmentAdapter;
    private List<HomeFoodShopBean> entertainmentList;

    //推荐健康
    private RecommendHealthAdapter healthAdapter;
    private List<HomeFoodShopBean> healthList;

    //推荐汽车
    private RecommendCarAdapter carAdapter;
    private List<HomeFoodShopBean> carList;

    //推荐家居
    private RecommendHouseHoldAdapter houseHoldAdapter;
    private List<HomeFoodShopBean> householdList;

    //推荐家政
    private RecommendHouseKeepingAdapter houseKeepingAdapter;
    private List<HomeFoodShopBean> housekeeping;

    //推荐商务
    private RecommendCommerceAdapter commerceAdapter;
    private List<HomeFoodShopBean> commerce;

    //推荐金融
    private RecommendFinancesAdapter financesAdapter;
    private List<HomeFoodShopBean> financesList;


    /*******热门商品的label********/
    private LaberAcapter laberAcapter;
    private List<String> labels;

    /*******最后一个品类********/
    //热门商品
    private HotGoodsAdapter hotGoodsAdapter;
    private List<HomeGoodBean> hotGoodsList;

    private List<String> citys;

    private PopupWindow popupWindow;

    private DelegateAdapter adapter;
    //构造 layoutHelper 列表
    List<LayoutHelper> helpers = new LinkedList<>();

    @Override
    protected View initView() {
        bannerList = new ArrayList<>();
        catList = new ArrayList<>();
        articlesBeans = new ArrayList<>();
        giftBeans = new ArrayList<>();
        adsBeans = new ArrayList<>();
        foodsList = new ArrayList<>();
        hotelList = new ArrayList<>();
        eduList = new ArrayList<>();
        entertainmentList = new ArrayList<>();
        healthList = new ArrayList<>();
        carList = new ArrayList<>();
        housekeeping = new ArrayList<>();
        householdList = new ArrayList<>();
        commerce = new ArrayList<>();
        financesList = new ArrayList<>();
        labels = new ArrayList<>();

        hotGoodsList = new ArrayList<>();
        citys = new ArrayList<>();
        citys.add("合肥市");
        citys.add("南京市");
        citys.add("上海市");
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_home, null);
        mUnbinder = ButterKnife.bind(this, view);
//        rvHome.setLayoutManager(new GridLayoutManager(mContext, 6));
        VirtualLayoutManager manager = new VirtualLayoutManager(mContext);
        adapter = new DelegateAdapter(manager, false);
        rvHome.setLayoutManager(manager);
        rvHome.setAdapter(adapter);
        initLayoutHelper();
        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rvHome.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(4, 10);

        rvHome.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                1, getResources().getColor(R.color.color_bg)));
        initBroadcast();
        initPop();
        return view;
    }

    /**
     * initlayoutHelper
     */
    private void initLayoutHelper() {
        //轮播图
        LinearLayoutHelper helper1 = new LinearLayoutHelper();
        helper1.setItemCount(1);
        helpers.add(helper1);
        bannerAdapter = new BannerAdapter(mContext, helper1, bannerList);
        adapter.addAdapter(bannerAdapter);

        //分类
        LinearLayoutHelper helper2 = new LinearLayoutHelper();
        helper2.setItemCount(1);
        helpers.add(helper2);
        classificationAdapter = new ClassificationAdapter(mContext, helper2, catList);
        adapter.addAdapter(classificationAdapter);

        //滚动文章
        LinearLayoutHelper helper3 = new LinearLayoutHelper();
        helper3.setItemCount(1);
        helpers.add(helper3);
        advertisingAdapter = new AdvertisingAdapter(mContext, helper3, articlesBeans);
        adapter.addAdapter(advertisingAdapter);


        //积分兑换商城
        LinearLayoutHelper helper4 = new LinearLayoutHelper();
        helper4.setItemCount(1);
        helpers.add(helper4);
        exchangeMallAdapter = new ExchangeMallAdapter(mContext, helper4, giftBeans);
        adapter.addAdapter(exchangeMallAdapter);

        //图片广告
        LinearLayoutHelper helper5 = new LinearLayoutHelper();
        helper5.setItemCount(1);
        helpers.add(helper5);
        pictureAdsAdapter = new PictureAdsAdapter(mContext, helper5, adsBeans);
        adapter.addAdapter(pictureAdsAdapter);

        //推荐美食
        LinearLayoutHelper helper6 = new LinearLayoutHelper();
        helper6.setItemCount(1);
        helpers.add(helper6);
        foodsAdapter = new RecommendFoodsAdapter(mContext, helper6, foodsList);
        adapter.addAdapter(foodsAdapter);

        //推荐酒店
        LinearLayoutHelper helper7 = new LinearLayoutHelper();
        helper7.setItemCount(1);
        helpers.add(helper7);
        hotelAdapter = new RecommendHotelAdapter(mContext, helper7, hotelList);
        adapter.addAdapter(hotelAdapter);

        //推荐教育
        LinearLayoutHelper helper8 = new LinearLayoutHelper();
        helper8.setItemCount(1);
        helpers.add(helper8);
        eduAdapter = new RecommendEduAdapter(mContext, helper8, eduList);
        adapter.addAdapter(eduAdapter);

        //推荐娱乐
        LinearLayoutHelper helper9 = new LinearLayoutHelper();
        helper9.setItemCount(1);
        helpers.add(helper9);
        entertainmentAdapter = new RecommendEntertainmentAdapter(mContext, helper9, entertainmentList);
        adapter.addAdapter(entertainmentAdapter);

        //推荐健康
        LinearLayoutHelper helper10 = new LinearLayoutHelper();
        helper10.setItemCount(1);
        helpers.add(helper10);
        healthAdapter = new RecommendHealthAdapter(mContext, helper10, healthList);
        adapter.addAdapter(healthAdapter);

        //推荐汽车
        LinearLayoutHelper helper11 = new LinearLayoutHelper();
        helper11.setItemCount(1);
        helpers.add(helper11);
        carAdapter = new RecommendCarAdapter(mContext, helper11, carList);
        adapter.addAdapter(carAdapter);

        //推荐金融
        LinearLayoutHelper helper13 = new LinearLayoutHelper();
        helper13.setItemCount(1);
        helpers.add(helper13);
        financesAdapter = new RecommendFinancesAdapter(mContext, helper13, financesList);
        adapter.addAdapter(financesAdapter);

        //推荐家居
        LinearLayoutHelper helper12 = new LinearLayoutHelper();
        helper12.setItemCount(1);
        helpers.add(helper12);
        houseHoldAdapter = new RecommendHouseHoldAdapter(mContext, helper12, householdList);
        adapter.addAdapter(houseHoldAdapter);

        //推荐家政
        LinearLayoutHelper hpjz = new LinearLayoutHelper();
        hpjz.setItemCount(1);
        helpers.add(hpjz);
        houseKeepingAdapter = new RecommendHouseKeepingAdapter(mContext,hpjz,housekeeping);
        adapter.addAdapter(houseKeepingAdapter);

        //推荐商务
        LinearLayoutHelper hpsw = new LinearLayoutHelper();
        hpsw.setItemCount(1);
        helpers.add(hpsw);
        commerceAdapter = new RecommendCommerceAdapter(mContext,hpsw,commerce);
        adapter.addAdapter(commerceAdapter);


        //热门商品的label
        LinearLayoutHelper helper14 = new LinearLayoutHelper();
        helper14.setItemCount(1);
        helpers.add(helper14);
        laberAcapter = new LaberAcapter(mContext, helper14, labels);
        adapter.addAdapter(laberAcapter);

        //热门商品
        GridLayoutHelper helperlast= new GridLayoutHelper(2);
        helperlast.setItemCount(2);
        helpers.add(helperlast);
        hotGoodsAdapter = new HotGoodsAdapter(mContext, helperlast, hotGoodsList);
        adapter.addAdapter(hotGoodsAdapter);

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
        super.initData();
        L.e("HomeFragment -> initData");
        try {
            mPresenter = new HomePresenter(this);
            mPresenter.getHomeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mLocationReceiver) {
            mContext.unregisterReceiver(mLocationReceiver);
        }
    }

    @Override
    public void showBanner(List<BannerBean> banner) {
        bannerList.clear();
        bannerList.addAll(banner);
        bannerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCats(List<HomeCatBean> cats) {
        catList.clear();
        catList.addAll(cats);
        classificationAdapter.notifyDataSetChanged();
    }

    @Override
    public void showArticles(JSONArray articles) {
        articlesBeans.clear();
        articlesBeans.addAll(JSON.parseArray(articles.toString(),ArticlesBean.class));
        advertisingAdapter.notifyDataSetChanged();
    }

    @Override
    public void showGifts(List<HomeGiftBean> gifts) {
        giftBeans.clear();
        giftBeans.addAll(gifts);
        exchangeMallAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAds(JSONArray ads) {
        adsBeans.clear();
        adsBeans.addAll(JSON.parseArray(ads.toString(),AdsBean.class));
        pictureAdsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFoods(List<HomeFoodShopBean> foods) {
        foodsList.clear();
        foodsList.addAll(foods);
        foodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showHotels(List<HomeFoodShopBean> hotels) {
        hotelList.clear();
        hotelList.addAll(hotels);
        hotelAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEducations(List<HomeFoodShopBean> educations) {
        eduList.clear();
        eduList.addAll(educations);
        eduAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEntertainments(List<HomeFoodShopBean> entertainments) {
        entertainmentList.clear();
        entertainmentList.addAll(entertainments);
        entertainmentAdapter.notifyDataSetChanged();

    }


    @Override
    public void showFinances(List<HomeFoodShopBean> finances) {
        financesList.clear();
        financesList.addAll(finances);
        financesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showGoods(List<HomeGoodBean> goods) {
        hotGoodsList.addAll(goods);
        hotGoodsAdapter.notifyDataSetChanged();
        labels.clear();
        if (hotGoodsList.size()>0){
            labels.add("");
        }
        laberAcapter.notifyDataSetChanged();
    }

    @Override
    public void showCars(List<HomeFoodShopBean> cars) {
        carList.clear();
        carList.addAll(cars);
        carAdapter.notifyDataSetChanged();
    }

    @Override
    public void showHealth(List<HomeFoodShopBean> healths) {
        healthList.clear();
        healthList.addAll(healths);
        healthAdapter.notifyDataSetChanged();
    }


    //商务
    @Override
    public void showCommerceData(List<HomeFoodShopBean> commerces) {
        commerce.clear();
        commerce.addAll(commerces);
        commerceAdapter.notifyDataSetChanged();
    }

    //家政
    @Override
    public void showHousekeeping(List<HomeFoodShopBean> housekeepings) {
        housekeeping.clear();
        housekeeping.addAll(housekeepings);
        houseKeepingAdapter.notifyDataSetChanged();
    }

    //家居
    @Override
    public void showHomes(List<HomeFoodShopBean> homes) {
        householdList.clear();
        householdList.addAll(homes);
        houseHoldAdapter.notifyDataSetChanged();
    }

    private void initBroadcast() {
        mLocationReceiver = new LocationBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ahxd.lingyuangou.location");
        mContext.registerReceiver(mLocationReceiver, intentFilter);
    }

    public class LocationBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mTv_location != null) {
                Object city = SPUtils.get(mContext, Constant.KEY_CITY, "定位中");
                mTv_location.setText(city == null ? "定位中" : city.toString());
            }
        }
    }

    @OnClick({R.id.tv_location, R.id.et_search})
    void Click(View view) {
        switch (view.getId()) {
            case R.id.tv_location:
                if (popupWindow == null) {
                    initPop();
                }
                popupWindow.showAsDropDown(view);
                break;
            case R.id.et_search:
                Intent intent = new Intent(mContext, SearchActivity.class);
                mContext.startActivity(intent);
                break;
            default:
                break;
        }
    }


    private void initPop() {
        if (popupWindow == null) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_city_chose, null);
            popupWindow = new PopupWindow(getActivity());
            popupWindow.setContentView(view);
            popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
            popupWindow.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.shape_rect_white));
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            cityAdapter = new DialogChoseCityAdapter(getActivity(), citys);
            recyclerView.setAdapter(cityAdapter);
            cityAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    mTv_location.setText(citys.get(position));
                    if (position == 0) {
                        LocationUtils.getInstance().setCurrentLocation(citys.get(position));
                    } else {
                        LocationUtils.getInstance().setSearch(citys.get(position));
                    }
                    initData();
                    ((MainActivity) getActivity()).reloadNear();
                    popupWindow.dismiss();
                }
            });
        }

    }

}
