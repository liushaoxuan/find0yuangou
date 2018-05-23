package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IMarketingApplyContract;
import com.ahxd.lingyuangou.ui.mine.contract.IMineContactContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Mao on 2018/1/14.
 */

public  class MineContactModel implements IMineContactContract.IMineContactModel {

    public void getMineContact(final IMineContactCallback callback) {
        OkGo.<String>post(HostUrl.URL_INDEX_CONTACTUS)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onMineContact(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }


}
