package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.bean.AccredBean;
import com.ahxd.lingyuangou.bean.CartBean;
import com.ahxd.lingyuangou.bean.CatBean;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.bean.FoodShopEvaluateBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
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

public class FoodModel implements IFoodContract.IFoodModel {

    @Override
    public void getCatsList(final IFoodCallback callback) {
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
    public void getAccredList(final IFoodCallback callback) {
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
    public void getFoodList(int page,String accredId, String areaId, String order, String catId, String keywords, final IFoodCallback callback) {
        Type type = new TypeToken<MaoResponse<List<FoodShopBean>>>() {}.getType();
        OkGo.<MaoResponse<List<FoodShopBean>>>post(HostUrl.URL_FOOD_LIST)
                .params("page", page)
                .params("accredId", accredId)
                .params("areaId", LocationUtils.getInstance().getLocation())
                .params("order", order)
                .params("catId", catId)
                .params("keywords", keywords)
                .execute(new MaoJsonCallback<MaoResponse<List<FoodShopBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<FoodShopBean>>> response) {
                        callback.onFoodList(response.body().data);
                    }

                    @Override
                    public void onError(Response<MaoResponse<List<FoodShopBean>>> response) {
                        super.onError(response);
                    }
                });
    }

    @Override
    public void getFoodShopDetail(String catId, String shopId, final IFoodCallback callback) {
        OkGo.<String>post(HostUrl.URL_FOOD_SHOP_DETAIL)
                .params("catId", catId)
                .params("shopId", shopId)
                .params("areaId", LocationUtils.getInstance().getLocation())
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        JSONObject data = root.optJSONObject(Constant.RESP_DATA);
                        JSONObject shopInfo = data.optJSONObject("shopInfo");
                        callback.onShopInfo(shopInfo);

                        JSONArray shopGoods = data.optJSONArray("shopGoods");
                        callback.onShopGoods(convertShopGoods(shopGoods));

                        JSONArray appraises = data.optJSONArray("appraises");
                        callback.onEvaluateInfo(convertEvaluate(appraises));

                        JSONArray shopList = data.optJSONArray("shopList");
                        callback.onShopList(convertShopList(shopList));
                    }
                });
    }

    @Override
    public void getEvaluateList(String shopId, String goodsId, int page, final IFoodCallback callback) {
        Type type = new TypeToken<MaoResponse<List<FoodShopEvaluateBean>>>() {
        }.getType();
        OkGo.<MaoResponse<List<FoodShopEvaluateBean>>>post(HostUrl.URL_SHOP_EVALUATE_LIST)
                .params("shopId", shopId)
                .params("goodsId", goodsId)
                .params("page", page)
                .execute(new MaoJsonCallback<MaoResponse<List<FoodShopEvaluateBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<FoodShopEvaluateBean>>> response) {
                        callback.onShopEvaluateList(response.body().data);
                    }
                });
    }

    @Override
    public void favoriteShop(String targetId, String favoriteType, final IFoodCallback callback) {
        OkGo.<String>post(HostUrl.URL_SHOP_FAVORITE)
                .params("targetId", targetId)
                .params("favoriteType", favoriteType)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        callback.onFavorite(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    @Override
    public void getRecomShopList(int page, String areaId, String goodsCatId, String keywords, final IFoodCallback callback) {
        Type type = new TypeToken<MaoResponse<List<FoodShopBean>>>() {}.getType();
        OkGo.<MaoResponse<List<FoodShopBean>>>post(HostUrl.URL_RECOM_SHOP_LIST)
                .params("page", page)
                .params("areaId", LocationUtils.getInstance().getLocation())
                .params("goodsCatId", goodsCatId)
                .params("keywords", keywords)
                .execute(new MaoJsonCallback<MaoResponse<List<FoodShopBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<FoodShopBean>>> response) {
                        callback.onFoodList(response.body().data);
                    }
                });
    }

    private List<FoodShopEvaluateBean> convertEvaluate(JSONArray data) {
        List<FoodShopEvaluateBean> list = new ArrayList<>();
        FoodShopEvaluateBean bean;
        if (data == null || data.length() == 0) {
            return list;
        }
        for (int i = 0; i < data.length(); i++) {
            bean = new FoodShopEvaluateBean();
            bean.setContent(data.optJSONObject(i).optString("content"));
            bean.setCreateTime(data.optJSONObject(i).optString("createTime"));
            bean.setGoodsScore(data.optJSONObject(i).optString("goodsScore"));
            bean.setLoginName(data.optJSONObject(i).optString("loginName"));
            list.add(bean);
        }
        return list;
    }

    private List<FoodShopBean> convertShopList(JSONArray data) {
        List<FoodShopBean> list = new ArrayList<>();
        FoodShopBean bean;
        if (data == null || data.length() == 0) {
            return list;
        }
        for (int i = 0; i < data.length(); i++) {
            bean = new FoodShopBean();
            bean.setCatId(data.optJSONObject(i).optString("catId"));
            bean.setDistance(data.optJSONObject(i).optString("distance"));
            bean.setSaleCount(data.optJSONObject(i).optString("saleCount"));
            bean.setScoreRate(data.optJSONObject(i).optString("scoreRate"));
            bean.setShopAddress(data.optJSONObject(i).optString("shopAddress"));
            bean.setShopId(data.optJSONObject(i).optString("shopId"));
            bean.setShopImg(data.optJSONObject(i).optString("shopImg"));
            bean.setShopName(data.optJSONObject(i).optString("shopName"));
            list.add(bean);
        }
        return list;
    }

    private List<ShopGoodBean> convertShopGoods(JSONArray data) {
        List<ShopGoodBean> list = new ArrayList<>();
        ShopGoodBean bean;
        if (data == null || data.length() == 0) {
            return list;
        }
        for (int i = 0; i < data.length(); i++) {
            bean = new ShopGoodBean();
            bean.setGoodsId(data.optJSONObject(i).optString("goodsId"));
            bean.setGoodsImg(data.optJSONObject(i).optString("goodsImg"));
            bean.setGoodsName(data.optJSONObject(i).optString("goodsName"));
            bean.setGoodsTips(data.optJSONObject(i).optString("goodsTips"));
            bean.setMarketPrice(data.optJSONObject(i).optString("marketPrice"));
            bean.setSaleNum(data.optJSONObject(i).optString("saleNum"));
            bean.setShopPrice(data.optJSONObject(i).optString("shopPrice"));
            bean.setReturnPrice(data.optJSONObject(i).optString("returnPrice"));
            list.add(bean);
        }
        return list;
    }

}
