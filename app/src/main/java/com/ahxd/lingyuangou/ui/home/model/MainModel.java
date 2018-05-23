package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IMainContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Mao on 2018/1/14.
 */

public  class MainModel implements IMainContract.IMainModel {

    public void getApk(final IMainCallback callback) {
        OkGo.<String>post(HostUrl.URL_PLATE_CHECKVERSION)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onApk(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

}
