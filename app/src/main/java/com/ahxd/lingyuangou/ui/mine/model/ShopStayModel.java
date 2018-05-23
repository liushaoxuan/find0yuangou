package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IShopStayContract;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/9.
 */

public class ShopStayModel implements IShopStayContract.IShopStayModel {

    @Override
    public void getCode(String tel, final IShopStayCallback callback) {
        OkGo.<String>post(HostUrl.URL_SHOP_STAY_CODE)
                .params("tel", tel)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        callback.onShopCode(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void submitApply(String markerCode, String linkman, String tel,
                            String remark, String code, final IShopStayCallback callback) {
        OkGo.<String>post(HostUrl.URL_SHOP_STAY_SUBMIT)
                .params("markerCode", markerCode)
                .params("linkman", linkman)
                .params("tel", tel)
                .params("remark", remark)
                .params("code", code)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        callback.onSubmit(root.optString(Constant.RESP_MSG));
                    }
                });

    }
}
