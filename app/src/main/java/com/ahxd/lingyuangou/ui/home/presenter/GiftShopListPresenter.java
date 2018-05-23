package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.ShopBean;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeGiftListContract;
import com.ahxd.lingyuangou.ui.home.contract.IGiftShopListContract;
import com.ahxd.lingyuangou.ui.home.model.ExchangeGiftListModel;
import com.ahxd.lingyuangou.ui.home.model.GiftShopListModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class GiftShopListPresenter implements IGiftShopListContract.IGiftShopListPresenter {

    private IGiftShopListContract.IGiftShopListView mView;
    private GiftShopListModel mModel;

    public GiftShopListPresenter(IGiftShopListContract.IGiftShopListView view) {
        this.mView = view;
        this.mModel = new GiftShopListModel();
    }

    @Override
    public void getGiftShopList(int page, String keywords) {
        mModel.getGiftShopList(page,keywords, new IGiftShopListContract.IGiftShopListModel.IGiftShopListCallback(mView) {
            @Override
            public void onGiftShopList(List<ShopBean> result) {
                mView.showGiftShopList(result);
            }
        });
    }

}
