package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.AccredBean;
import com.ahxd.lingyuangou.bean.CatBean;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.ui.home.contract.IFoodContract;
import com.ahxd.lingyuangou.ui.home.model.FoodModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class FoodPresenter implements IFoodContract.IFoodPresenter {

    private IFoodContract.IFoodView mView;
    private FoodModel mModel;

    public FoodPresenter(IFoodContract.IFoodView view) {
        this.mView = view;
        this.mModel = new FoodModel();
    }

    @Override
    public void getFoodList(int page, String accredId,  String areaId, String order, String catId, String keywords) {
        mModel.getFoodList(page,accredId, areaId, order, catId, keywords, new IFoodContract.IFoodModel.IFoodCallback(mView) {
            @Override
            public void onFoodList(List<FoodShopBean> result) {
                mView.showFoodList(result);
            }
        });
    }

    @Override
    public void getRecomShopList(int page, String areaId, String goodsCatId, String keywords) {
        mModel.getRecomShopList(page, areaId, goodsCatId, keywords, new IFoodContract.IFoodModel.IFoodCallback(mView) {
            @Override
            public void onFoodList(List<FoodShopBean> result) {
                mView.showFoodList(result);
            }
        });
    }


    @Override
    public void getCatsList() {
        mModel.getCatsList(new IFoodContract.IFoodModel.IFoodCallback(mView) {
            @Override
            public void onCatList(List<CatBean> result) {
                mView.showCatsList(result);
            }

        });
    }

    @Override
    public void getAccredList() {
        mModel.getAccredList(new IFoodContract.IFoodModel.IFoodCallback(mView) {
            @Override
            public void onAccredList(List<AccredBean> result) {
                mView.showAccredList(result);
            }
        });
    }

}
