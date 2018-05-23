package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.bean.RechargeBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IRechargeContract;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class RechargeModel implements IRechargeContract.IRechargeModel {

    @Override
    public void getRechargeNumList(final IRechargeCallback callback) {
        Type type = new TypeToken<MaoResponse<List<RechargeBean>>>() {}.getType();
        OkGo.<MaoResponse<List<RechargeBean>>>post(HostUrl.URL_RECHARGE_LIST)
                .execute(new MaoJsonCallback<MaoResponse<List<RechargeBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<RechargeBean>>> response) {
                        callback.onRechargeList(response.body().data);
                    }
                });
    }

    @Override
    public void recharge(final int type, String money, final IRechargeCallback callback) {
        OkGo.<String>post(HostUrl.URL_RECHARGE)
                .params("type", type)
                .params("money", money)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        switch (type) {
                            case 1:// type 1 支付宝 2 微信
                                callback.onZhifubaoPay(root.optString(Constant.RESP_DATA));
                                break;
                            case 2:
                                callback.onWeixinPay(root.optJSONObject(Constant.RESP_DATA));
                                break;
                        }
                    }
                });
    }

}
