package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.ISearchContract;
import com.ahxd.lingyuangou.utils.L;
import com.lzy.okgo.OkGo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/17.
 */

public class SearchModel implements ISearchContract.ISearchModel {

    @Override
    public void searchKeywords(final int type, int page, String content, final ISearchCallback callback) {
        OkGo.<String>post(HostUrl.URL_HOME_SEARCH)
                .params("type", type)
                .params("page", page)
                .params("content", content)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        switch (type) {
                            case 0: // 商品
                                callback.onSearchGood(convertGood(root.optJSONArray(Constant.RESP_DATA)));
                                break;
                            case 1: // 商铺
                                callback.onSearchShop(convertShop(root.optJSONArray(Constant.RESP_DATA)));
                                break;
                        }
                    }
                });
    }

    private List<ShopGoodBean> convertGood(JSONArray data) {
        List<ShopGoodBean> list = new ArrayList<>();
        if (null != data) {
            ShopGoodBean bean;
            for (int i = 0; i < data.length(); i++) {
                bean = new ShopGoodBean();
                bean.setReturnPrice(data.optJSONObject(i).optString("returnPrice"));
                bean.setShopPrice(data.optJSONObject(i).optString("shopPrice"));
                bean.setSaleNum(data.optJSONObject(i).optString("saleNum"));
                bean.setMarketPrice(data.optJSONObject(i).optString("marketPrice"));
                bean.setGoodsImg(data.optJSONObject(i).optString("goodsImg"));
                bean.setGoodsId(data.optJSONObject(i).optString("goodsId"));
                bean.setGoodsTips(data.optJSONObject(i).optString("goodsTips"));
                bean.setGoodsName(data.optJSONObject(i).optString("goodsName"));
                list.add(bean);
            }
        }
        return list;
    }

    private List<FoodShopBean> convertShop(JSONArray data) {
        List<FoodShopBean> list = new ArrayList<>();
        if (null != data) {
            FoodShopBean bean;
            for (int i = 0; i < data.length(); i++) {
                bean = new FoodShopBean();
                bean.setShopName(data.optJSONObject(i).optString("shopName"));
                bean.setShopImg(data.optJSONObject(i).optString("shopImg"));
                bean.setShopId(data.optJSONObject(i).optString("shopId"));
                bean.setShopAddress(data.optJSONObject(i).optString("shopAddress"));
                bean.setSaleCount(data.optJSONObject(i).optString("saleCount"));
                bean.setDistance(data.optJSONObject(i).optString("distance"));
                bean.setCatId(data.optJSONObject(i).optString("catId"));
                bean.setScoreRate(data.optJSONObject(i).optString("scoreRate"));
                list.add(bean);
            }
        }
        return list;
    }

}
