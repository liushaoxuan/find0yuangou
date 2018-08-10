package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.ui.home.contract.IOfflinePayContract;
import com.ahxd.lingyuangou.ui.home.model.OfflinePayModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/10.
 */

public class OfflinePayPresenter implements IOfflinePayContract.IOfflinePayPresenter {

    private IOfflinePayContract.IOfflinePayView mView;
    private IOfflinePayContract.IOfflinePayModel mModel;

    public OfflinePayPresenter(IOfflinePayContract.IOfflinePayView view) {
        this.mView = view;
        mModel = new OfflinePayModel();
    }

    @Override
    public void getOfflinePayDetail(String token, String shopId) {
        mModel.getOfflinePayDetail(token, shopId, new IOfflinePayContract.IOfflinePayModel.IOfflinePayCallback(mView) {
            @Override
            public void onShopDetail(JSONObject data) {
                mView.showShopDetail(data);
            }
        });
    }

    @Override
    public void payOfflineOrder(String token, String shopId, int type, String money) {
        mModel.payOfflineOrder(token, shopId, type, money, new IOfflinePayContract.IOfflinePayModel.IOfflinePayCallback(mView) {

            @Override
            public void onWeixinPay(JSONObject data) {
                mView.showWeixinPay(data);
            }

            @Override
            public void onZhifubaoPay(String data) {
                mView.showZhifubaoPay(data);
            }

            @Override
            public void onWalletPay(String msg) {
                mView.showWalletPay(msg);
            }

            @Override
            public void onSjk(String msg) {
                mView.showSjk(msg);
            }
        });
    }
}
