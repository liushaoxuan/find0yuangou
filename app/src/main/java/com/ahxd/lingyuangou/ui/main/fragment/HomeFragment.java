package com.ahxd.lingyuangou.ui.main.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseFragment;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.bean.HomeFinanceBean;
import com.ahxd.lingyuangou.bean.HomeFoodShopBean;
import com.ahxd.lingyuangou.bean.HomeGiftBean;
import com.ahxd.lingyuangou.bean.HomeGoodBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.listener.OnItemClickListener;
import com.ahxd.lingyuangou.ui.home.activity.SearchActivity;
import com.ahxd.lingyuangou.ui.home.adapter.DialogChoseCityAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.HomeAdapter;
import com.ahxd.lingyuangou.ui.home.contract.IHomeContract;
import com.ahxd.lingyuangou.ui.home.presenter.HomePresenter;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/25.
 */

public class HomeFragment extends BaseFragment implements IHomeContract.IHomeView  {

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
    private HomeAdapter mAdapter;
    private LocationBroadcastReceiver mLocationReceiver;

    private DialogChoseCityAdapter cityAdapter;

    private List<String> citys;

    private PopupWindow popupWindow;

    @Override
    protected View initView() {
        citys = new ArrayList<>();
        citys.add("合肥市");
        citys.add("南京市");
        citys.add("上海市");
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_home, null);
        mUnbinder = ButterKnife.bind(this, view);
        rvHome.setLayoutManager(new GridLayoutManager(mContext, 6));
        mAdapter = new HomeAdapter(mContext);
        rvHome.setAdapter(mAdapter);
        rvHome.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                1, getResources().getColor(R.color.color_bg)));
        initBroadcast();
        initPop();
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        L.e("HomeFragment -> initData");
        mPresenter = new HomePresenter(this);
        mPresenter.getHomeData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mLocationReceiver) {
            mContext.unregisterReceiver(mLocationReceiver);
        }
    }

    @Override
    public void showBanner(JSONArray banner) {
        mAdapter.setBannerData(banner);
    }

    @Override
    public void showCats(List<HomeCatBean> cats) {
        mAdapter.setCatData(cats);
    }

    @Override
    public void showArticles(JSONArray articles) {
        mAdapter.setArticleData(articles);
    }

    @Override
    public void showGifts(List<HomeGiftBean> gifts) {
        mAdapter.setGiftData(gifts);
    }

    @Override
    public void showAds(JSONArray ads) {
        mAdapter.setAdData(ads);
    }

    @Override
    public void showFoods(List<HomeFoodShopBean> foods) {
        mAdapter.setFoodData(foods);
    }

    @Override
    public void showHotels(List<HomeFoodShopBean> hotels) {
        mAdapter.setHotelsData(hotels);
    }

    @Override
    public void showEducations(List<HomeFoodShopBean> educations) {
        mAdapter.setEducationsData(educations);
    }

    @Override
    public void showEntertainments(List<HomeFoodShopBean> entertainments) {
        mAdapter.setEntertainmentsData(entertainments);

    }


    @Override
    public void showFinances(List<HomeFinanceBean> finances) {
        mAdapter.setFinanceData(finances);
    }

    @Override
    public void showGoods(List<HomeGoodBean> goods) {
        mAdapter.setGoodsData(goods);
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
//            mAdapter.setLocationRefresh();
//            mPresenter.getHomeData();
            if (mTv_location!=null){
                Object city = SPUtils.get(mContext, Constant.KEY_CITY,"定位中");
                mTv_location.setText(city==null?"定位中":city.toString());
            }
        }
    }

    @OnClick({R.id.tv_location,R.id.et_search})
    void Click(View view){
        switch (view.getId()){
            case R.id.tv_location:
               if (popupWindow==null){
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


    private void initPop(){
        if (popupWindow==null){
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_city_chose,null);
            popupWindow = new PopupWindow(getActivity());
            popupWindow.setContentView(view);
            popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.shape_rect_white));
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            cityAdapter = new DialogChoseCityAdapter(getActivity(),citys);
            recyclerView.setAdapter(cityAdapter);
            cityAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    mTv_location.setText(citys.get(position));
                    popupWindow.dismiss();
                }
            });
        }

    }

}
