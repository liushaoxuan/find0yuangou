package com.ahxd.lingyuangou.ui.near.presenter;

import com.ahxd.lingyuangou.bean.AccredBean;
import com.ahxd.lingyuangou.bean.CatBean;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.ui.home.contract.IFoodContract;
import com.ahxd.lingyuangou.ui.home.model.FoodModel;
import com.ahxd.lingyuangou.ui.near.contract.INearContract;

import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 */

public class NearPresenter implements INearContract.INearPresenter {

    private INearContract.INearView mView;
    private FoodModel mModel;

    public NearPresenter(INearContract.INearView view) {
        this.mView = view;
        mModel = new FoodModel();
    }

    @Override
    public void getNearList(int page,String accredId, String areaId, String order, String catId, String keywords) {
        mModel.getFoodList(page, accredId,accredId, order, catId, keywords, new IFoodContract.IFoodModel.IFoodCallback(mView) {
            @Override
            public void onFoodList(List<FoodShopBean> result) {
                mView.showNearList(result);
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
