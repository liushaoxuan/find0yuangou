package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.AddressBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IAddressContract;
import com.ahxd.lingyuangou.utils.L;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class AddressModel implements IAddressContract.IAddressListModel {

    @Override
    public void getAddressList(final IAddressListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<AddressBean>>>(){}.getType();
        OkGo.<MaoResponse<List<AddressBean>>>post(HostUrl.URL_ADDRESS_LIST)
                .execute(new MaoJsonCallback<MaoResponse<List<AddressBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<AddressBean>>> response) {
                        callback.onAddressList(response.body().data);
                    }
                });
    }

    @Override
    public void onDeleteAddress(String addressId, final IAddressListCallback callback) {
        OkGo.<String>post(HostUrl.URL_ADDRESS_DELETE)
                .params("addressId", addressId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onDeleteAddress(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void setDefaultAddress(String addressId, final IAddressListCallback callback) {
        OkGo.<String>post(HostUrl.URL_ADDRESS_SET_DEFAULT)
                .params("addressId", addressId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onDefaultAddress(root.optString(Constant.RESP_MSG));
                    }
                });
    }
}
