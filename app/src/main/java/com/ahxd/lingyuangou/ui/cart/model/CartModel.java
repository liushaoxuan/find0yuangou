package com.ahxd.lingyuangou.ui.cart.model;

import com.ahxd.lingyuangou.bean.CartBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.cart.contract.ICartContract;
import com.ahxd.lingyuangou.utils.L;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by Mao Zhendong on 2018/1/8.
 */

public class CartModel implements ICartContract.ICartModel {

    @Override
    public void getCartGoods(final ICartCallback callback) {
        Type type = new TypeToken<MaoResponse<CartBean>>() {}.getType();
        OkGo.<MaoResponse<CartBean>>post(HostUrl.URL_CART_GOODS_LIST)
                .execute(new MaoJsonCallback<MaoResponse<CartBean>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<CartBean>> response) {
                        callback.onCartGoods(response.body().data);
                    }
                });
    }

    @Override
    public void onDeleteGood(String cartId, final ICartCallback callback) {
        OkGo.<String>post(HostUrl.URL_CART_DELETE_GOOD)
                .params("cartId", cartId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        callback.onDeleteGood(root.optString(Constant.RESP_MSG));
                    }

                });
    }

    @Override
    public void onEditGoods(String content, final ICartCallback callback) {
        OkGo.<String>post(HostUrl.URL_CART_EDIT_GOOD)
                .params("content", content)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onEditGoods(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void onSubmitOrder(String content, String addressId, final ICartCallback callback) {
        OkGo.<String>post(HostUrl.URL_CART_SUBMIT_ORDER)
                .params("content", content)
                .params("addressId", addressId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onSubmitOrder(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

    //兑换
    @Override
    public void getGiftCartGoods(final ICartCallback callback) {
        Type type = new TypeToken<MaoResponse<CartBean>>() {}.getType();
        OkGo.<MaoResponse<CartBean>>post(HostUrl.URL_SCORECARTS_GOODS_LIST)
                .execute(new MaoJsonCallback<MaoResponse<CartBean>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<CartBean>> response) {
                        callback.onGiftCartGoods(response.body().data);
                    }
                });
    }

    @Override
    public void onGiftDeleteGood(String cartId, final ICartCallback callback) {
        OkGo.<String>post(HostUrl.URL_SCORECARTS_DELETE_GOOD)
                .params("cartId", cartId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        callback.onGiftDeleteGood(root.optString(Constant.RESP_MSG));
                    }

                });
    }

    @Override
    public void onGiftEditGoods(String content, final ICartCallback callback) {
        OkGo.<String>post(HostUrl.URL_SCORECARTS_EDIT_GOOD)
                .params("content", content)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onGiftEditGoods(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void onGiftSubmitOrder(String content, String addressId, final ICartCallback callback) {
        OkGo.<String>post(HostUrl.URL_SCORECARTS_SUBMIT_ORDER)
                .params("content", content)
                .params("addressId", addressId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onGiftSubmitOrder(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }
}
