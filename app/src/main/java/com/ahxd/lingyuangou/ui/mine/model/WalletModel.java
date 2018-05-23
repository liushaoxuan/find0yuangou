package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Mao on 2018/1/14.
 */

public  class WalletModel implements IWalletContract.IWalletModel {

    public void getWallet(final IWalletCallback callback) {
        OkGo.<String>post(HostUrl.URL_WALEET)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onWallet(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

    @Override
    public void getWalletIntegral(final IWalletCallback callback) {
        OkGo.<String>post(HostUrl.URL_WALEET_INTEGRAL)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onWalletIntegral(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

    @Override
    public void setScoreToMoney(String score,final IWalletCallback callback) {
        OkGo.<String>post(HostUrl.URL_SCORE_TO_MONEY)
                .params("score",score)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onScoreToMoney(root.optString(Constant.RESP_MSG));
                    }
                });
    }



}
