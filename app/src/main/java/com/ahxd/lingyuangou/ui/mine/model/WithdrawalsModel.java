package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.BankBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.bean.WithdrawalsAccountBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWithdrawalsContract;
import com.ahxd.lingyuangou.utils.L;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mao on 2018/1/14.
 */

public  class WithdrawalsModel implements IWithdrawalsContract.IWithdrawalsModel {

    @Override
    public void getWithdrawalsList(final IWithdrawalsCallback callback) {
        Type type = new TypeToken<MaoResponse<List<WithdrawalsAccountBean>>>(){}.getType();
        OkGo.<MaoResponse<List<WithdrawalsAccountBean>>>post(HostUrl.URL_CASHDRAWS_GETCONFIG)
                .execute(new MaoJsonCallback<MaoResponse<List<WithdrawalsAccountBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<WithdrawalsAccountBean>>> response) {
                        callback.onWithdrawalsList((ArrayList<WithdrawalsAccountBean>) response.body().data);
                    }
                });
    }

    @Override
    public void setWithdrawals(String money, String accId, final IWithdrawalsCallback callback) {
        OkGo.<String>post(HostUrl.URL_CASHDRAWS_DRAWMONEY)
                .params("money",money)
                .params("accId",accId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onWithdrawals(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }
}
