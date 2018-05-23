package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.FoodShopEvaluateBean;
import com.ahxd.lingyuangou.ui.home.contract.IFoodContract;
import com.ahxd.lingyuangou.ui.home.contract.IShopEvaluateContract;
import com.ahxd.lingyuangou.ui.home.model.FoodModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/4.
 */

public class ShopEvaluatePresenter implements IShopEvaluateContract.IShopEvaluatePresenter {

    private IShopEvaluateContract.IShopEvaluateView mView;
    private FoodModel mModel;

    public ShopEvaluatePresenter(IShopEvaluateContract.IShopEvaluateView view) {
        this.mView = view;
        mModel = new FoodModel();
    }

    @Override
    public void getShopEvaluateList(String shopId, String goodsId, int page) {
        mModel.getEvaluateList(shopId, goodsId, page, new IFoodContract.IFoodModel.IFoodCallback(mView) {
            @Override
            public void onShopEvaluateList(List<FoodShopEvaluateBean> list) {
                mView.showEvaluateList(list);
            }
        });
    }
}
