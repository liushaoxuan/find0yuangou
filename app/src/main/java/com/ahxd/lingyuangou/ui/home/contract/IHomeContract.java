package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.BannerBean;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.bean.HomeFinanceBean;
import com.ahxd.lingyuangou.bean.HomeFoodShopBean;
import com.ahxd.lingyuangou.bean.HomeGiftBean;
import com.ahxd.lingyuangou.bean.HomeGoodBean;
import com.ahxd.lingyuangou.bean.HomeRecomendCarBean;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public interface IHomeContract {

    interface IHomeView extends BaseView {
        void showBanner(List<BannerBean> banner);

        void showCats(List<HomeCatBean> cats);

        void showArticles(JSONArray articles);

        void showGifts(List<HomeGiftBean> gifts);

        void showAds(JSONArray ads);

        void showFoods(List<HomeFoodShopBean> foods);

        void showHotels(List<HomeFoodShopBean> foods);

        void showEducations(List<HomeFoodShopBean> foods);

        void showEntertainments(List<HomeFoodShopBean> foods);

        void showFinances(List<HomeFoodShopBean> finances);

        void showGoods(List<HomeGoodBean> goods);

        //设置推荐汽车
        void showCars(List<HomeFoodShopBean> goods);

        //设置推荐健康
        void showHealth(List<HomeFoodShopBean> healths);

        //推荐商务
        void showCommerceData(List<HomeFoodShopBean> commerce);

        //推荐家政
        void showHousekeeping(List<HomeFoodShopBean> housekeeping);

        //推荐家居
        void showHomes(List<HomeFoodShopBean> homes);
    }

    interface IHomePresenter {
        // 获取Home数据
        void getHomeData();
    }

    interface IHomeModel {
        // 获取Home数据
        void getHomeData(IHomeCallback callback);

        abstract class IHomeCallback extends ModelCallback {

            public IHomeCallback(BaseView view) {
                super(view);
            }

            public void onBannerData(List<BannerBean> banner) {
            }

            public void onCatData(List<HomeCatBean> cats) {
            }

            public void onArticleData(JSONArray articles) {
            }

            public void onGiftData(List<HomeGiftBean> gifts) {
            }

            public void onAdData(JSONArray ads) {
            }

            public void onFoodData(List<HomeFoodShopBean> foods) {
            }

            public void onHotelsData(List<HomeFoodShopBean> hotels) {
            }

            public void onEducationData(List<HomeFoodShopBean> educations) {
            }

            public void onEntertainmentData(List<HomeFoodShopBean> entertainments) {
            }

            public void onFinanceData(List<HomeFoodShopBean> finances) {
            }

            public void onGoodsData(List<HomeGoodBean> goods) {
            }

            public void onHealthData(List<HomeFoodShopBean> healths) {
            }

            public void onCarsData(List<HomeFoodShopBean> cars) {
            }

            public void onCommerceData(List<HomeFoodShopBean> commerce) {
            }

            public void onHousekeepingData(List<HomeFoodShopBean> housekeeping) {
            }

            public void onHomeData(List<HomeFoodShopBean> homes) {
            }
        }

    }

}
