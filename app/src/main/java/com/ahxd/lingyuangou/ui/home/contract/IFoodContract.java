package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.AccredBean;
import com.ahxd.lingyuangou.bean.CatBean;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.bean.FoodShopEvaluateBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public interface IFoodContract {

    public interface IFoodView extends BaseView {
        public void showFoodList(List<FoodShopBean> list);

        public void showCatsList(List<CatBean> result);
        public void showAccredList(List<AccredBean> result);
    }

    public interface IFoodPresenter {
        // 获取店铺列表
        public void getFoodList(int page,String accredId, String areaId, String order, String catId, String keywords);
        // 获取推荐商铺列表
        public void getRecomShopList(int page, String areaId, String goodsCatId, String keywords);

        public void getCatsList();
        public void getAccredList();
    }

    public interface IFoodModel {
        // 获取商城品类列表
        public void getCatsList(IFoodCallback callback);
        // 获取商城品类列表
        public void getAccredList(IFoodCallback callback);
        // 获取商铺列表
        public void getFoodList(int page,String accredId, String areaId, String order, String catId, String keywords, IFoodCallback callback);
        // 获取商铺详情
        public void getFoodShopDetail(String catId, String shopId, IFoodCallback callback);
        // 获取商铺评价
        public void getEvaluateList(String shopId, String goodsId, int page, IFoodCallback callback);
        // 店铺收藏
        public void favoriteShop(String targetId, String favoriteType, IFoodCallback callback);

        // 获取推荐商铺列表
        public void getRecomShopList(int page, String areaId, String goodsCatId, String keywords, IFoodCallback callback);

        public abstract class IFoodCallback extends ModelCallback {

            public IFoodCallback(BaseView view) {
                super(view);
            }

            public void onCatList(List<CatBean> result) {}

            public void onAccredList(List<AccredBean> result) {}

            public void onFoodList(List<FoodShopBean> result) {}

            public void onShopInfo(JSONObject shopInfo) {}

            public void onShopGoods(List<ShopGoodBean> list) {}

            public void onEvaluateInfo(List<FoodShopEvaluateBean> list) {}

            public void onShopList(List<FoodShopBean> list) {}

            public void onShopEvaluateList(List<FoodShopEvaluateBean> list) {}

            public void onFavorite(String msg) {}
        }

    }

}
