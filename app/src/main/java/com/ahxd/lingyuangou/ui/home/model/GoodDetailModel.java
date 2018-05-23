package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IGoodDetailContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */

public class GoodDetailModel implements IGoodDetailContract.IGoodDetailModel {

    @Override
    public void addGoodCart(String good_id, String goodsSpecId, String cartNum, final IGoodDetailCallback callback) {
        OkGo.<String>post(HostUrl.URL_CART_ADD)
                .params("good_id", good_id)
                .params("goodsSpecId", goodsSpecId)
                .params("cartNum", cartNum)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onAddCart(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void buyGoodNow(String goodsId, String specIds, String buyNum, String addressId, final IGoodDetailCallback callback) {
        OkGo.<String>post(HostUrl.URL_CART_BUY_NOW)
                .params("goodsId", goodsId)
                .params("specIds", specIds)
                .params("buyNum", buyNum)
                .params("addressId", addressId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onBuyNow(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }
}
