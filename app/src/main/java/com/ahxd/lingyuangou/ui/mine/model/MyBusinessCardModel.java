package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IMyBusinessCardContract;
import com.ahxd.lingyuangou.ui.mine.contract.IMyMarketingCodeContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Mao on 2018/1/14.
 */

public  class MyBusinessCardModel implements IMyBusinessCardContract.IMyBusinessCardModel {

    public void getMyBusinessCard(final IMyBusinessCardCallback callback) {
        OkGo.<String>post(HostUrl.URL_USERS_CARD)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onMyBusinessCard(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

}
