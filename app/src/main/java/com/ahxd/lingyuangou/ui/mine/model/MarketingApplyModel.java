package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IMarketingApplyContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Mao on 2018/1/14.
 */

public  class MarketingApplyModel implements IMarketingApplyContract.IMarketingApplyModel {

    public void getMarketingApply(final IMarketingApplyCallback callback) {
        OkGo.<String>post(HostUrl.URL_INDEX_MARKETING)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onMarketingData(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }
    public void setMarketingApply(final IMarketingApplyCallback callback) {
        OkGo.<String>post(HostUrl.URL_MARKETING_APPLY)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onMarketingApply(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

}
