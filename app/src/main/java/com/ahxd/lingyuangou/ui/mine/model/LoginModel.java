package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.ILoginContract;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/8.
 */

public class LoginModel implements ILoginContract.ILoginModel {


    @Override
    public void login(String loginName, String loginPwd, final ILoginCallback callback) {
        OkGo.<String>post(HostUrl.URL_LOGIN)
                .params("loginName", loginName)
                .params("loginPwd", loginPwd)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        SPUtils.put(MaoApplication.getInstance(), Constant.KEY_TOKEN, root.optString(Constant.RESP_DATA));
                        callback.onLogin(root.optString(Constant.RESP_MSG));
                    }
                });
    }
}
