package com.ahxd.lingyuangou.ui.cart.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.cart.contract.IOnlinePayContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/11.
 */

public class OnlinePayModel implements IOnlinePayContract.IOnlinePayModel {

    @Override
    public void submitGiftOrder(String content, String addressId, IOnlinePayCallback callback) {

    }

    @Override
    public void submitOrder(String content, String addressId, IOnlinePayCallback callback) {

    }

    @Override
    public void payOnlineOrder(String content, String addressId, final int type, final IOnlinePayCallback callback) {
        OkGo.<String>post(HostUrl.URL_ONLINE_PAY)
                .params("content", content)
                .params("addressId", addressId)
                .params("type", type)
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

    @Override
    public void payGiftOrder(String content, String addressId, final IOnlinePayCallback callback) {
        OkGo.<String>post(HostUrl.URL_SCORECARTS_ONLINE_PAY)
                .params("content", content)
                .params("addressId", addressId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onPayGiftOrder(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void payGiftRightNow(String goodsId, String specIds, String buyNum, String addressId, int type, String msg, final IOnlinePayCallback callback) {
        OkGo.<String>post(HostUrl.URL_ONLINE_PAY_RIGHTQUICK_NOW)
                .params("goodsId", goodsId)
//                .params("specIds", specIds)
                .params("buyNum", buyNum)
                .params("addressId", addressId)
//                .params("type", type)
                .params("msg", msg)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onWalletPay(root.optString(Constant.RESP_MSG));

                    }
                });
    }

    @Override
    public void payRightNow(String goodsId, String specIds, String buyNum, String addressId,
                            final int type, String msg, final IOnlinePayCallback callback) {
        OkGo.<String>post(HostUrl.URL_ONLINE_PAY_RIGHT_NOW)
                .params("goodsId", goodsId)
                .params("specIds", specIds)
                .params("buyNum", buyNum)
                .params("addressId", addressId)
                .params("type", type)
                .params("msg", msg)
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

    @Override
    public void payOrderList(final int type, String orderId, String sum, final IOnlinePayCallback callback) {
        OkGo.<String>post(HostUrl.URL_ONLINE_PAY_ORDER_LIST)
                .params("orderId", orderId)
                .params("type", type)
                .params("sum", sum)
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
