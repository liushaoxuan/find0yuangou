package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IAddAlipayWechatContract {

    public interface IWalletView extends BaseView {
        public void showWallet(JSONObject data);
    }

    public interface IWalletPresenter {
        // 获取钱包详情
        public void getWallet();
    }

    public interface IWalletModel {

        public void getWallet(IWalletCallback callback);

        public abstract class IWalletCallback extends ModelCallback {

            public IWalletCallback(BaseView view) {
                super(view);
            }

            public void onWallet(JSONObject data) {}
        }

    }


}
