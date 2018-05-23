package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IExtensionCenterContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Mao on 2018/1/14.
 */

public  class ExtensionCenterModel implements IExtensionCenterContract.IExtensionCenterModel {

    public void getExtensionCenter(final IExtensionCenterCallback callback) {
        OkGo.<String>post(HostUrl.URL_RECOMMEND_INDEX)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onExtensionCenter(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

    @Override
    public void getShare(final IExtensionCenterCallback callback) {
        OkGo.<String>post(HostUrl.URL_USERS_CARD)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onShare(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

}
