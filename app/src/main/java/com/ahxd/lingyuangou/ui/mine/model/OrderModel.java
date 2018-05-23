package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.OrderBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IOrderContract;
import com.ahxd.lingyuangou.utils.L;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Mao on 2018/1/14.
 */

public class OrderModel implements IOrderContract.IOrderModel {

    @Override
    public void getGiftOrderList(String orderStatus, String isAppraise, int page, final IOrderCallback callback) {
        Type type = new TypeToken<MaoResponse<List<OrderBean>>>() {}.getType();
        OkGo.<MaoResponse<List<OrderBean>>>post(HostUrl.URL_SCORE_ORDER_LIST)
                .params("orderStatus", orderStatus)
                .params("isAppraise", isAppraise)
                .params("page", page)
                .execute(new MaoJsonCallback<MaoResponse<List<OrderBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<OrderBean>>> response) {
                        callback.onGiftOrderList(response.body().data);
                    }
                });
    }

    @Override
    public void getOrderList(String orderStatus, String isAppraise, int page, final IOrderCallback callback) {
        Type type = new TypeToken<MaoResponse<List<OrderBean>>>() {}.getType();
        OkGo.<MaoResponse<List<OrderBean>>>post(HostUrl.URL_ORDER_LIST)
                .params("orderStatus", orderStatus)
                .params("isAppraise", isAppraise)
                .params("page", page)
                .execute(new MaoJsonCallback<MaoResponse<List<OrderBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<OrderBean>>> response) {
                        callback.onOrderList(response.body().data);
                    }
                });
    }

    @Override
    public void getOrderDetail(String orderId, final IOrderCallback callback) {
        OkGo.<String>post(HostUrl.URL_ORDER_DETAIL)
                .params("orderId", orderId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onOrderDetail(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

    @Override
    public void getGiftOrderDetail(String orderId, final IOrderCallback callback) {
        OkGo.<String>post(HostUrl.URL_SCORE_ORDER_DETAIL)
                .params("orderId", orderId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onGiftOrderDetail(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

    @Override
    public void getOrderPayInfo(String orderId, final IOrderCallback callback) {
        OkGo.<String>post(HostUrl.URL_ORDER_PAY_INFO)
                .params("orderId", orderId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onOrderPayDetail(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

    @Override
    public void getGiftOrderPayInfo(String orderId, final IOrderCallback callback) {
        OkGo.<String>post(HostUrl.URL_ORDER_PAY_INFO)
                .params("orderId", orderId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onGiftOrderPayDetail(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

    @Override
    public void onCancelOrder(String orderId, String reason, final IOrderCallback callback) {
        OkGo.<String>post(HostUrl.URL_ORDER_CANCEL)
                .params("orderId", orderId)
                .params("reason", reason)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onCancelOrder(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void onGiftCancelOrder(String orderId, String reason, final IOrderCallback callback) {
        OkGo.<String>post(HostUrl.URL_GIFTORDER_CANCEL)
                .params("orderId", orderId)
                .params("reason", reason)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onGiftCancelOrder(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void onConfirmReceiver(String orderId, final IOrderCallback callback) {
        OkGo.<String>post(HostUrl.URL_ORDER_CONFIRM_RECEIVER)
                .params("orderId", orderId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onConfirmReceiver(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void onGiftConfirmReceiver(String orderId, final IOrderCallback callback) {
        OkGo.<String>post(HostUrl.URL_SCORE_ORDER_CONFIRM_RECEIVER)
                .params("orderId", orderId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onGiftConfirmReceiver(root.optString(Constant.RESP_MSG));
                    }
                });
    }

}
