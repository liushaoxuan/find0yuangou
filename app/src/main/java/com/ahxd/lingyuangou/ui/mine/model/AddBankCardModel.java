package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.BankBean;
import com.ahxd.lingyuangou.bean.DicAddressBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IAddBankCardContract;
import com.ahxd.lingyuangou.utils.L;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class AddBankCardModel implements IAddBankCardContract.IAddBankCardModel {


    @Override
    public void setConfig(String id, String accTargetId, String accNo, String accUser, String accAreaId, String accAddress, String userName, String userPhone, String userNumber,final IAddBankCardCallback callback) {
        OkGo.<String>post(HostUrl.URL_CASHDRAWS_SETCONFIG)
                .params("id",id)
                .params("accTargetId",accTargetId)
                .params("accNo",accNo)
                .params("accUser",accUser)
                .params("accAreaId",accAreaId)
                .params("accAddress",accAddress)
                .params("userName",userName)
                .params("userPhone",userPhone)
                .params("userNumber",userNumber)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onShowConfig(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }

    @Override
    public void getBank(final IAddBankCardCallback callback) {
        Type type = new TypeToken<MaoResponse<List<BankBean>>>() {}.getType();
        OkGo.<MaoResponse<List<BankBean>>>post(HostUrl.URL_CASHDRAWS_BANKS_LIST)
                .execute(new MaoJsonCallback<MaoResponse<List<BankBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<BankBean>>> response) {
                        callback.onShowBank((ArrayList<BankBean>) response.body().data);
                    }
                });
    }

    @Override
    public void getProvince(final IAddBankCardCallback callback) {
        Type type = new TypeToken<MaoResponse<List<DicAddressBean>>>() {}.getType();
        OkGo.<MaoResponse<List<DicAddressBean>>>post(HostUrl.URL_ADDRESS_EDIT_PROVINCE)
                .execute(new MaoJsonCallback<MaoResponse<List<DicAddressBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<DicAddressBean>>> response) {
                        callback.onShowProvince((ArrayList<DicAddressBean>) response.body().data);
                    }
                });
    }

    @Override
    public void getCity(String provinceId, final IAddBankCardCallback callback) {
        Type type = new TypeToken<MaoResponse<List<DicAddressBean>>>() {}.getType();
        OkGo.<MaoResponse<List<DicAddressBean>>>post(HostUrl.URL_ADDRESS_EDIT_CITY)
                .params("provinceId", provinceId)
                .execute(new MaoJsonCallback<MaoResponse<List<DicAddressBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<DicAddressBean>>> response) {
                        callback.onShowCity((ArrayList<DicAddressBean>) response.body().data);
                    }
                });
    }

    @Override
    public void getArea(String cityId, final IAddBankCardCallback callback) {
        Type type = new TypeToken<MaoResponse<List<DicAddressBean>>>() {}.getType();
        OkGo.<MaoResponse<List<DicAddressBean>>>post(HostUrl.URL_ADDRESS_EDIT_AREA)
                .params("cityId", cityId)
                .execute(new MaoJsonCallback<MaoResponse<List<DicAddressBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<DicAddressBean>>> response) {
                        callback.onShowArea((ArrayList<DicAddressBean>) response.body().data);
                    }
                });
    }
}
