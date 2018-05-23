package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IGiftGoodDetailContract;
import com.ahxd.lingyuangou.ui.home.contract.IGoodDetailContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */

public class GiftGoodDetailModel implements IGiftGoodDetailContract.IGiftGoodDetailModel {

    @Override
    public void addGiftGoodCart(String good_id, String goodsSpecId, String cartNum, final IGiftGoodDetailCallback callback) {
        OkGo.<String>post(HostUrl.URL_SCORECARTS_ADD)
                .params("good_id", good_id)
                .params("goodsSpecId", goodsSpecId)
                .params("cartNum", cartNum)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onGiftAddCart(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void buyGiftGoodNow(String goodsId, String buyNum, String addressId, final IGiftGoodDetailCallback callback) {
        OkGo.<String>post(HostUrl.URL_SCORECARTS_CART_BUY_NOW)
                .params("goodsId", goodsId)
                .params("buyNum", buyNum)
                .params("addressId", addressId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onGiftBuyNow(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }
}
