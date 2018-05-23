package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.CartBean;
import com.ahxd.lingyuangou.bean.ShopManagemntDetailBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IShopManagemntDetailContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.utils.L;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by Mao on 2018/1/14.
 */

public  class ShopManagemntDetailModel implements IShopManagemntDetailContract.IShopManagemntDetailModel {

    public void getShopManagemntDetail(String shopId,int mPage,final IShopManagemntDetailCallback callback) {
        Type type = new TypeToken<MaoResponse<ShopManagemntDetailBean>>() {}.getType();
        OkGo.<MaoResponse<ShopManagemntDetailBean>>post(HostUrl.URL_MARKETING_SHOOLOG)
                .params("shopId",shopId)
                .params("page",mPage)
                .execute(new MaoJsonCallback<MaoResponse<ShopManagemntDetailBean>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<ShopManagemntDetailBean>> response) {
                        callback.onShopManagemntDetail(response.body().data);
                    }
                });
        
    }

}
