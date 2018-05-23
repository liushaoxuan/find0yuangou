package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.ui.home.contract.IGiftGoodDetailContract;
import com.ahxd.lingyuangou.ui.home.contract.IGoodDetailContract;
import com.ahxd.lingyuangou.ui.home.model.GiftGoodDetailModel;
import com.ahxd.lingyuangou.ui.home.model.GoodDetailModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */

public class GiftGoodDetailPresenter implements IGiftGoodDetailContract.IGiftGoodDetailPresenter {

    private IGiftGoodDetailContract.IGiftGoodDetailView mView;
    private IGiftGoodDetailContract.IGiftGoodDetailModel mModel;

    public GiftGoodDetailPresenter(IGiftGoodDetailContract.IGiftGoodDetailView view) {
        this.mView = view;
        mModel = new GiftGoodDetailModel();
    }

    @Override
    public void addGiftGoodCart(String good_id, String goodsSpecId, String cartNum) {
        mModel.addGiftGoodCart(good_id, goodsSpecId, cartNum,
                new IGiftGoodDetailContract.IGiftGoodDetailModel.IGiftGoodDetailCallback(mView) {
            @Override
            public void onGiftAddCart(String msg) {
                mView.showGiftAddCart(msg);
            }
        });
    }

    @Override
    public void buyGiftGoodNow(String goodsId, String buyNum, String addressId) {
        mModel.buyGiftGoodNow(goodsId, buyNum, addressId, new IGiftGoodDetailContract.IGiftGoodDetailModel.IGiftGoodDetailCallback(mView) {
            @Override
            public void onGiftBuyNow(JSONObject data) {
                mView.showGiftBuyNow(data);
            }
        });
    }
}
