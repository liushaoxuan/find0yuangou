package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.bean.ArticleBean;
import com.ahxd.lingyuangou.bean.GoodsBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IShopGoodsListContract;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Mao zhendong on 2018/1/12.
 */

public class ShopGoodsModel implements IShopGoodsListContract.IShopGoodsListModel {

    @Override
    public void getShopGoodsList(String shopId, int page, String keywords,final IShopGoodsCallback callback) {
        Type type = new TypeToken<MaoResponse<List<GoodsBean>>>() {}.getType();
        OkGo.<MaoResponse<List<GoodsBean>>>post(HostUrl.URL_GOODS_LIST)
                .params("shopId", shopId)
                .params("page", page)
                .params("keywords", keywords)
                .execute(new MaoJsonCallback<MaoResponse<List<GoodsBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<GoodsBean>>> response) {
                        callback.onShopGoodsList(response.body().data);
                    }
                });
    }


}
