package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IAlipayWechatContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONObject;

/**
 * Created by Mao on 2018/1/14.
 */

public  class AlipayWechatModel implements IAlipayWechatContract.IAlipayWechatModel {


    @Override
    public void setAlipayWechat(String id, String accTargetId, String accNo, String userName, String userPhone, String userNumber, final IAlipayWechatCallback callback) {
        OkGo.<String>post(HostUrl.URL_CASHDRAWS_SETCONFIG)
                .params("id",id)
                .params("accTargetId",accTargetId)
                .params("accNo",accNo)
                .params("userName",userName)
                .params("userPhone",userPhone)
                .params("userNumber",userNumber)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onAlipayWechat(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }
}
