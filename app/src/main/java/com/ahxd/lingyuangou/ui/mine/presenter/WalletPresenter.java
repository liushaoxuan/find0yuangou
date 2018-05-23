package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.ILoginContract;
import com.ahxd.lingyuangou.ui.mine.contract.IOrderContract;
import com.ahxd.lingyuangou.ui.mine.contract.IOrderDetailContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.model.OrderModel;
import com.ahxd.lingyuangou.ui.mine.model.WalletModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */
public class WalletPresenter implements IWalletContract.IWalletPresenter{

    private IWalletContract.IWalletView mView;
    private WalletModel mModel;

    public WalletPresenter(IWalletContract.IWalletView view) {
        this.mView = view;
        mModel = new WalletModel();
    }

    @Override
    public void getWallet() {
        mModel.getWallet(new IWalletContract.IWalletModel.IWalletCallback(mView) {
            @Override
            public void onWallet(JSONObject data) {
                mView.showWallet(data);
            }
        });
    }

    @Override
    public void getWalletIntegral() {
        mModel.getWalletIntegral(new IWalletContract.IWalletModel.IWalletCallback(mView) {
            @Override
            public void onWalletIntegral(JSONObject data) {
                mView.showWalletIntegral(data);
            }
        });
    }

    @Override
    public void setScoreToMoney(String score) {
        mModel.setScoreToMoney(score,new IWalletContract.IWalletModel.IWalletCallback(mView) {
            @Override
            public void onScoreToMoney(String data) {
                mView.showScoreToMoney(data);
            }
        });
    }

}
