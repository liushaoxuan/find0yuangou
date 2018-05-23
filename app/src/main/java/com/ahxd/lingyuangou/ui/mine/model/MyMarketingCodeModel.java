package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IMyMarketingCodeContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Mao on 2018/1/14.
 */

public  class MyMarketingCodeModel implements IMyMarketingCodeContract.IMyMarketingCodeModel {

    public void getMyMarketingCode(final IMyMarketingCodeCallback callback) {
        OkGo.<String>post(HostUrl.URL_MARKETING_INDEX)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onMyMarketingCode(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

}
