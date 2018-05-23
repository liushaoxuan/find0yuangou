package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.ShopBean;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeGiftContract;
import com.ahxd.lingyuangou.ui.home.contract.IGiftShopDetailContract;
import com.ahxd.lingyuangou.ui.home.model.ExchangeGiftModel;
import com.ahxd.lingyuangou.ui.home.model.GiftShopDetailModel;

/**
 * Created by Administrator on 2018/1/23.
 */

public class GiftShopDetailPresenter implements IGiftShopDetailContract.IGiftShopDetailPresenter {

    private IGiftShopDetailContract.IGiftShopDetailView mView;
    private IGiftShopDetailContract.IGiftShopDetailModel mModel;

    public GiftShopDetailPresenter(IGiftShopDetailContract.IGiftShopDetailView view) {
        this.mView = view;
        mModel = new GiftShopDetailModel();
    }

    @Override
    public void getGiftShopDetail(String shopId) {
        mModel.getGiftShopDetail(shopId,new IGiftShopDetailContract.IGiftShopDetailModel.IGiftShopDetailCallback(mView) {
            @Override
            public void onGiftShopDetail(ShopBean banner) {
                mView.showGiftShopDetail(banner);
            }


        });
    }
}
