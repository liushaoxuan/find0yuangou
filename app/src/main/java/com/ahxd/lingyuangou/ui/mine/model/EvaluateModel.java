package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IEvaluateContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/16.
 */

public class EvaluateModel implements IEvaluateContract.IEvaluateModel {

    @Override
    public void submitEvaluate(String orderId, String shopId, String goodsId, String content, String goodsScore,
                               String serviceScore, String timeScore, final IEvaluateCallback callback) {
        OkGo.<String>post(HostUrl.URL_EVALUATE_SUBMIT)
                .params("shopId", shopId)
                .params("orderId", orderId)
                .params("goodsId", goodsId)
                .params("content", content)
                .params("goodsScore", goodsScore)
                .params("serviceScore", serviceScore)
                .params("timeScore", timeScore)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onSubmitEvaluate(root.optString(Constant.RESP_MSG));
                    }
                });
    }
}
