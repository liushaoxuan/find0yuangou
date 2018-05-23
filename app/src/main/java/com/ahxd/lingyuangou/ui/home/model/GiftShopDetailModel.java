package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.ShopBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeGiftContract;
import com.ahxd.lingyuangou.ui.home.contract.IGiftShopDetailContract;
import com.ahxd.lingyuangou.utils.LocationUtils;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/12/26.
 */

public class GiftShopDetailModel implements IGiftShopDetailContract.IGiftShopDetailModel {

    @Override
    public void getGiftShopDetail(String shopId,final IGiftShopDetailCallback callback) {

        Type type = new TypeToken<MaoResponse<ShopBean>>(){}.getType();
        OkGo.<MaoResponse<ShopBean>>post(HostUrl.URL_SCOREGOODS_SHOPINFO)
                .params("shopId",shopId)
                .execute(new MaoJsonCallback<MaoResponse<ShopBean>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<ShopBean>> response) {
                        callback.onGiftShopDetail(response.body().data);
                    }
                });

    }


}
