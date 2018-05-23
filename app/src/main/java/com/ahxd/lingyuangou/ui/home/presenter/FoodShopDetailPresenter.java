package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.bean.FoodShopEvaluateBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;
import com.ahxd.lingyuangou.ui.home.contract.IFoodContract;
import com.ahxd.lingyuangou.ui.home.contract.IFoodShopDetailContract;
import com.ahxd.lingyuangou.ui.home.model.FoodModel;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public class FoodShopDetailPresenter implements IFoodShopDetailContract.IFoodShopDetailPresenter {

    private IFoodShopDetailContract.IFoodShopDetailView mView;
    private FoodModel mFoodModel;

    public FoodShopDetailPresenter(IFoodShopDetailContract.IFoodShopDetailView view) {
        this.mView = view;
        mFoodModel = new FoodModel();
    }

    @Override
    public void getFoodShopDetail(String catId, String shopId) {
        mFoodModel.getFoodShopDetail(catId, shopId, new IFoodContract.IFoodModel.IFoodCallback(mView) {
            @Override
            public void onShopInfo(JSONObject shopInfo) {
                mView.showShopInfo(shopInfo);
            }

            @Override
            public void onShopGoods(List<ShopGoodBean> list) {
                mView.showShopGoods(list);
            }

            @Override
            public void onEvaluateInfo(List<FoodShopEvaluateBean> list) {
                mView.showEvaluateInfo(list);
            }

            @Override
            public void onShopList(List<FoodShopBean> list) {
                mView.showShopList(list);
            }
        });
    }

    @Override
    public void favorite(String targetId, String favoriteType) {
        mFoodModel.favoriteShop(targetId, favoriteType, new IFoodContract.IFoodModel.IFoodCallback(mView) {
            @Override
            public void onFavorite(String msg) {
                mView.showFavorite(msg);
            }
        });
    }
}
