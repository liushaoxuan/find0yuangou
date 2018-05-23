package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IOfflinePayContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Mao Zhendong on 2018/1/10.
 */

public class OfflinePayModel implements IOfflinePayContract.IOfflinePayModel {

    @Override
    public void getOfflinePayDetail(String token, String shopId, final IOfflinePayCallback callback) {
        OkGo.<String>post(HostUrl.URL_PAY_SHOP_DETAIL)
                .params("token", token)
                .params("shopId", shopId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        callback.onShopDetail(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

    @Override
    public void payOfflineOrder(String token, String shopId, final int type, String money, final IOfflinePayCallback callback) {
        OkGo.<String>post(HostUrl.URL_PAY_OFFLINE)
                .params("token", token)
                .params("shopId", shopId)
                .params("type", type)
                .params("money", money)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        switch (type) {
                            case 1:
                                callback.onWalletPay(root.optString(Constant.RESP_MSG));
                                break;
                            case 2:
                                callback.onWeixinPay(root.optJSONObject(Constant.RESP_DATA));
                                break;
                            case 3:
                                callback.onZhifubaoPay(root.optString(Constant.RESP_DATA));
                                break;
                        }
                    }
                });
    }
}
