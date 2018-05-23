package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IEditPersonalContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Mao on 2018/1/15.
 */

public class EditPersonalModel implements IEditPersonalContract.IEditPersonalModel {

    @Override
    public void submitPersonal(String userName, String userPhoto, String birthday, String userPhone,
                               String userWeixin, int userSex, final IEditPersonalCallback callback) {
        OkGo.<String>post(HostUrl.URL_PERSONAL_EDIT)
                .params("userName", userName)
                .params("userPhoto", userPhoto)
                .params("brithday", birthday)
                .params("userPhone", userPhone)
                .params("userWeixin", userWeixin)
                .params("userSex", userSex)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onSubmitPersonal(root.optString(Constant.RESP_MSG));
                    }
                });

    }
}
