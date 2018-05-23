package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IForgetContract;
import com.ahxd.lingyuangou.ui.mine.contract.IRegisterContract;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/8.
 */

public class ForgetModel implements IForgetContract.IForgetModel {

    @Override
    public void forget( String loginName, String loginPwd, String reloginPwd, String yzm, final IForgetCallback callback) {
        OkGo.<String>post(HostUrl.URL_FORGET)
                .params("loginName", loginName)
                .params("loginPwd", loginPwd)
                .params("reloginPwd", reloginPwd)
                .params("yzm", yzm)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        callback.onForget(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void getValidateCode(String tel, final IForgetCallback callback) {
        OkGo.<String>post(HostUrl.URL_FORGETGET_VALIDATE_CODE)
                .params("tel", tel)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        callback.onValidateCode(root.optString(Constant.RESP_MSG));
                    }
                });
    }
}
