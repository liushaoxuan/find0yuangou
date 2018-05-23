package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IWalletContract {

    public interface IWalletView extends BaseView {
        public void showWallet(JSONObject data);
        public void showWalletIntegral(JSONObject data);
        public void showScoreToMoney(String data);
    }

    public interface IWalletPresenter {
        // 获取钱包详情
        public void getWallet();
        public void getWalletIntegral();
        public void setScoreToMoney(String score);
    }

    public interface IWalletModel {

        public void getWallet(IWalletContract.IWalletModel.IWalletCallback callback);

        public void getWalletIntegral(IWalletContract.IWalletModel.IWalletCallback callback);

        public void setScoreToMoney(String score,IWalletContract.IWalletModel.IWalletCallback callback);

        public abstract class IWalletCallback extends ModelCallback {

            public IWalletCallback(BaseView view) {
                super(view);
            }

            public void onWallet(JSONObject data) {}

            public void onWalletIntegral(JSONObject data) {}

            public void onScoreToMoney(String data) {}
        }

    }


}
