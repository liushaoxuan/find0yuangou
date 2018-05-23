package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.AccredBean;
import com.ahxd.lingyuangou.bean.CatBean;
import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeGiftListContract;
import com.ahxd.lingyuangou.ui.home.contract.IFoodContract;
import com.ahxd.lingyuangou.ui.home.model.ExchangeGiftListModel;
import com.ahxd.lingyuangou.ui.home.model.FoodModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class ExchangeGiftListPresenter implements IExchangeGiftListContract.IExchangeGiftListPresenter {

    private IExchangeGiftListContract.IExchangeGiftListView mView;
    private ExchangeGiftListModel mModel;

    public ExchangeGiftListPresenter(IExchangeGiftListContract.IExchangeGiftListView view) {
        this.mView = view;
        this.mModel = new ExchangeGiftListModel();
    }

    @Override
    public void getExchangeGiftList(int page, String keywords,String goodsCatId) {
        mModel.getExchangeGiftList(page,keywords,goodsCatId, new IExchangeGiftListContract.IExchangeGiftListModel.IExchangeGiftListCallback(mView) {
            @Override
            public void onExchangeGiftList(List<ExchangeBean.ExchangeHots> result) {
                mView.showExchangeGiftList(result);
            }
        });
    }

    @Override
    public void getCatsList() {
        mModel.getCatsList(new IExchangeGiftListContract.IExchangeGiftListModel.IExchangeGiftListCallback(mView) {
            @Override
            public void onCatList(List<CatBean> result) {
                mView.showCatsList(result);
            }

        });
    }

    @Override
    public void getAccredList() {
        mModel.getAccredList(new IExchangeGiftListContract.IExchangeGiftListModel.IExchangeGiftListCallback(mView) {
            @Override
            public void onAccredList(List<AccredBean> result) {
                mView.showAccredList(result);
            }

        });
    }

}
