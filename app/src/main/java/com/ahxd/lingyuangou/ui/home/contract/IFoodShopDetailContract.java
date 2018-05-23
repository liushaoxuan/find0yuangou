package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.bean.FoodShopEvaluateBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public interface IFoodShopDetailContract {

    public interface IFoodShopDetailView extends BaseView {
        public void showShopInfo(JSONObject shopInfo);

        public void showShopGoods(List<ShopGoodBean> list);

        public void showEvaluateInfo(List<FoodShopEvaluateBean> list);

        public void showShopList(List<FoodShopBean> list);

        public void showFavorite(String msg);
    }

    public interface IFoodShopDetailPresenter {
        public void getFoodShopDetail(String catId, String shopId);

        public void favorite(String targetId, String favoriteType);
    }
}
