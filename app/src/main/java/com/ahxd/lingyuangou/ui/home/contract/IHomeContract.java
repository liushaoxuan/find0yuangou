package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.bean.HomeFinanceBean;
import com.ahxd.lingyuangou.bean.HomeFoodShopBean;
import com.ahxd.lingyuangou.bean.HomeGiftBean;
import com.ahxd.lingyuangou.bean.HomeGoodBean;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public interface IHomeContract {

    public interface IHomeView extends BaseView {
        public void showBanner(JSONArray banner);

        public void showCats(List<HomeCatBean> cats);

        public void showArticles(JSONArray articles);

        public void showGifts(List<HomeGiftBean> gifts);

        public void showAds(JSONArray ads);

        public void showFoods(List<HomeFoodShopBean> foods);

        public void showHotels(List<HomeFoodShopBean> foods);
        public void showEducations(List<HomeFoodShopBean> foods);
        public void showEntertainments(List<HomeFoodShopBean> foods);

        public void showFinances(List<HomeFinanceBean> finances);

        public void showGoods(List<HomeGoodBean> goods);
    }

    public interface IHomePresenter {
        // 获取Home数据
        public void getHomeData();
    }

    public interface IHomeModel {
        // 获取Home数据
        public void getHomeData(IHomeCallback callback);

        public abstract class IHomeCallback extends ModelCallback {

            public IHomeCallback(BaseView view) {
                super(view);
            }

            public void onBannerData(JSONArray banner) {}

            public void onCatData(List<HomeCatBean> cats) {}

            public void onArticleData(JSONArray articles) {}

            public void onGiftData(List<HomeGiftBean> gifts) {}

            public void onAdData(JSONArray ads) {}

            public void onFoodData(List<HomeFoodShopBean> foods) {}

            public void onHotelsData(List<HomeFoodShopBean> hotels) {}
            public void onEducationData(List<HomeFoodShopBean> educations) {}
            public void onEntertainmentData(List<HomeFoodShopBean> entertainments) {}

            public void onFinanceData(List<HomeFinanceBean> finances) {}

            public void onGoodsData(List<HomeGoodBean> goods) {}
        }

    }

}
