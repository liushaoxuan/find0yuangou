package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.ShopBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeGiftListContract;
import com.ahxd.lingyuangou.ui.home.contract.IGiftShopListContract;
import com.ahxd.lingyuangou.utils.LocationUtils;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class GiftShopListModel implements IGiftShopListContract.IGiftShopListModel {

    @Override
    public void getGiftShopList(int page,String keywords, final IGiftShopListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<ShopBean>>>() {}.getType();
        OkGo.<MaoResponse<List<ShopBean>>>post(HostUrl.URL_SCOREGOODS_SHOPLIST)
                .params("page", page)
                .params("shopName", keywords)
                .params("areaId", LocationUtils.getInstance().getLocation())
                .execute(new MaoJsonCallback<MaoResponse<List<ShopBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<ShopBean>>> response) {
                        callback.onGiftShopList(response.body().data);
                    }
                });
    }


}
