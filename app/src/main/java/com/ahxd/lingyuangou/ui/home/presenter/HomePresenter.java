package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.BannerBean;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.bean.HomeFinanceBean;
import com.ahxd.lingyuangou.bean.HomeFoodShopBean;
import com.ahxd.lingyuangou.bean.HomeGiftBean;
import com.ahxd.lingyuangou.bean.HomeGoodBean;
import com.ahxd.lingyuangou.bean.HomeRecomendCarBean;
import com.ahxd.lingyuangou.ui.home.contract.IHomeContract;
import com.ahxd.lingyuangou.ui.home.model.HomeModel;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public class HomePresenter implements IHomeContract.IHomePresenter {

    private IHomeContract.IHomeView mView;
    private IHomeContract.IHomeModel mModel;

    public HomePresenter(IHomeContract.IHomeView view) {
        this.mView = view;
        mModel = new HomeModel();
    }

    @Override
    public void getHomeData() {
        mModel.getHomeData(new IHomeContract.IHomeModel.IHomeCallback(mView) {
            @Override
            public void onBannerData(List<BannerBean> banner) {
                mView.showBanner(banner);
            }

            @Override
            public void onCatData(List<HomeCatBean> cats) {
                mView.showCats(cats);
            }

            @Override
            public void onArticleData(JSONArray articles) {
                mView.showArticles(articles);
            }

            @Override
            public void onGiftData(List<HomeGiftBean> gifts) {
                mView.showGifts(gifts);
            }

            @Override
            public void onAdData(JSONArray ads) {
                mView.showAds(ads);
            }

            @Override
            public void onFoodData(List<HomeFoodShopBean> foods) {
                mView.showFoods(foods);
            }

            @Override
            public void onHotelsData(List<HomeFoodShopBean> foods) {
                mView.showHotels(foods);
            }

            @Override
            public void onEducationData(List<HomeFoodShopBean> foods) {
                mView.showEducations(foods);
            }

            @Override
            public void onEntertainmentData(List<HomeFoodShopBean> foods) {
                mView.showEntertainments(foods);
            }

            @Override
            public void onFinanceData(List<HomeFoodShopBean> finances) {
                mView.showFinances(finances);
            }

            @Override
            public void onGoodsData(List<HomeGoodBean> goods) {
                mView.showGoods(goods);
            }

            @Override
            public void onCarsData(List<HomeFoodShopBean> cars) {
                mView.showCars(cars);
            }

            @Override
            public void onHealthData(List<HomeFoodShopBean> healths) {
                mView.showHealth(healths);
            }

            @Override
            public void onCommerceData(List<HomeFoodShopBean> commerce) {
                mView.showCommerceData(commerce);
            }

            @Override
            public void onHousekeepingData(List<HomeFoodShopBean> housekeeping) {
                mView.showHousekeeping(housekeeping);
            }

            @Override
            public void onHomeData(List<HomeFoodShopBean> homes) {
                mView.showHomes(homes);
            }
        });
    }
}
