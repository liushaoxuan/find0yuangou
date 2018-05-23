package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.bean.AccredBean;
import com.ahxd.lingyuangou.bean.CatBean;
import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.bean.FoodShopEvaluateBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeGiftListContract;
import com.ahxd.lingyuangou.ui.home.contract.IFoodContract;
import com.ahxd.lingyuangou.utils.LocationUtils;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class ExchangeGiftListModel implements IExchangeGiftListContract.IExchangeGiftListModel {

    @Override
    public void getCatsList(final IExchangeGiftListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<CatBean>>>() {}.getType();
        OkGo.<MaoResponse<List<CatBean>>>post(HostUrl.URL_CAT_LIST)
                .execute(new MaoJsonCallback<MaoResponse<List<CatBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<CatBean>>> response) {
                        callback.onCatList(response.body().data);
                    }
                });
    }

    @Override
    public void getAccredList(final IExchangeGiftListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<AccredBean>>>() {}.getType();
        OkGo.<MaoResponse<List<AccredBean>>>post(HostUrl.URL_ACCRED_LIST)
                .execute(new MaoJsonCallback<MaoResponse<List<AccredBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<AccredBean>>> response) {
                        callback.onAccredList(response.body().data);
                    }
                });
    }

    @Override
    public void getExchangeGiftList(int page,String keywords,String goodsCatId, final IExchangeGiftListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<ExchangeBean.ExchangeHots>>>() {}.getType();
        OkGo.<MaoResponse<List<ExchangeBean.ExchangeHots>>>post(HostUrl.URL_SCOREGOODS_LIST)
                .params("page", page)
                .params("goodsName", keywords)
                .params("goodsCatId", goodsCatId)
                .params("areaId", LocationUtils.getInstance().getLocation())
                .execute(new MaoJsonCallback<MaoResponse<List<ExchangeBean.ExchangeHots>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<ExchangeBean.ExchangeHots>>> response) {
                        callback.onExchangeGiftList(response.body().data);
                    }
                });
    }


}
