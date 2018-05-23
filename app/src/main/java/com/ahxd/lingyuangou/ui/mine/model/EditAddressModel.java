package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.DicAddressBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IEditAddressContract;
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

public class EditAddressModel implements IEditAddressContract.IEditAddressModel {

    @Override
    public void addAddress(String userName, String userPhone, String province, String city,
                           String area, String userAddress, final IEditAddressCallback callback) {
        OkGo.<String>post(HostUrl.URL_ADDRESS_ADD)
                .params("userName", userName)
                .params("userPhone", userPhone)
                .params("province", province)
                .params("city", city)
                .params("area", area)
                .params("userAddress", userAddress)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onAddAddress(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void modifyAddress(String addressId, String userName, String userPhone, String province,
                              String city, String area, String userAddress, final IEditAddressCallback callback) {
        OkGo.<String>post(HostUrl.URL_ADDRESS_MODIFY)
                .params("userName", userName)
                .params("userPhone", userPhone)
                .params("province", province)
                .params("city", city)
                .params("area", area)
                .params("userAddress", userAddress)
                .params("addressId", addressId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onModifyAddress(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void getProvince(final IEditAddressCallback callback) {
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
    public void getCity(String provinceId, final IEditAddressCallback callback) {
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
    public void getArea(String cityId, final IEditAddressCallback callback) {
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
