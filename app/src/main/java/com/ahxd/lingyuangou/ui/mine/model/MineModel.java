package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IMineContract;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/5.
 */

public class MineModel implements IMineContract.IMineModel {

    @Override
    public void getMyProfile(final IMineCallback callback) {
        OkGo.<String>post(HostUrl.URL_MINE)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        JSONObject data = root.optJSONObject(Constant.RESP_DATA);
                        callback.onProfileData(data);
                    }
                });
    }

    @Override
    public void getCustomService(final IMineCallback callback) {
        OkGo.<String>post(HostUrl.URL_INDEX_CUSTOMSERVICE)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        callback.onCustomService(root);
                    }
                });
    }

}
