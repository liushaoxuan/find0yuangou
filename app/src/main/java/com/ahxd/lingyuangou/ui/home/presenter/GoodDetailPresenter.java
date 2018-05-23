package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.ui.home.contract.IGoodDetailContract;
import com.ahxd.lingyuangou.ui.home.model.GoodDetailModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */

public class GoodDetailPresenter implements IGoodDetailContract.IGoodDetailPresenter {

    private IGoodDetailContract.IGoodDetailView mView;
    private IGoodDetailContract.IGoodDetailModel mModel;

    public GoodDetailPresenter(IGoodDetailContract.IGoodDetailView view) {
        this.mView = view;
        mModel = new GoodDetailModel();
    }

    @Override
    public void addGoodCart(String good_id, String goodsSpecId, String cartNum) {
        mModel.addGoodCart(good_id, goodsSpecId, cartNum,
                new IGoodDetailContract.IGoodDetailModel.IGoodDetailCallback(mView) {
            @Override
            public void onAddCart(String msg) {
                mView.showAddCart(msg);
            }
        });
    }

    @Override
    public void buyGoodNow(String goodsId, String specIds, String buyNum, String addressId) {
        mModel.buyGoodNow(goodsId, specIds, buyNum, addressId, new IGoodDetailContract.IGoodDetailModel.IGoodDetailCallback(mView) {
            @Override
            public void onBuyNow(JSONObject data) {
                mView.showBuyNow(data);
            }
        });
    }
}
