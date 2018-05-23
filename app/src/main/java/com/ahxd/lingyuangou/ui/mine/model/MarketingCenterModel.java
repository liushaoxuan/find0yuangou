package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IMarketingCenterContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Wpc on 2018/1/18.
 */

public  class MarketingCenterModel implements IMarketingCenterContract.IMarketingCenterModel {

    public void getMarketingCenter(final IMarketingCenterCallback callback) {
        OkGo.<String>post(HostUrl.URL_WALEET)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onMarketingCenter(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

}
