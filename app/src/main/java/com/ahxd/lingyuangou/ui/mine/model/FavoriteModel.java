package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.FavoriteGoodBean;
import com.ahxd.lingyuangou.bean.FavoriteShopBean;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IFavoriteContract;
import com.lzy.okgo.OkGo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mao on 2018/1/12.
 */

public class FavoriteModel implements IFavoriteContract.IFavoriteModel {

    @Override
    public void getFavoriteList(final int favoriteType, final IFavoriteCallback callback) {
        OkGo.<String>post(HostUrl.URL_FAVORITE_LIST)
                .params("favoriteType", favoriteType)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        if (favoriteType == 0) {
                            callback.onGoodFavorite(convertGood(root.optJSONArray(Constant.RESP_DATA)));
                        } else {
                            callback.onShopFavorite(convertShop(root.optJSONArray(Constant.RESP_DATA)));
                        }
                    }
                });
    }

    @Override
    public void deleteFavoriteList(String favoriteId, final IFavoriteCallback callback) {
        OkGo.<String>post(HostUrl.URL_DELETE_FAVORITE_LIST)
                .params("favoriteId", favoriteId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                            callback.onDeleteFavorite(root.optString(Constant.RESP_MSG));
                    }
                });
    }

    private List<FavoriteShopBean> convertShop(JSONArray data) {
        List<FavoriteShopBean> list = new ArrayList<>();
        if (data == null) {
            return list;
        }
        FavoriteShopBean bean;
        for (int i = 0; i < data.length(); i++) {
            bean = new FavoriteShopBean();
            bean.setFavoriteId(data.optJSONObject(i).optString("favoriteId"));
            bean.setScoreRate(data.optJSONObject(i).optString("scoreRate"));
            bean.setShopAddress(data.optJSONObject(i).optString("shopAddress"));
            bean.setShopId(data.optJSONObject(i).optString("shopId"));
            bean.setShopImg(data.optJSONObject(i).optString("shopImg"));
            bean.setShopName(data.optJSONObject(i).optString("shopName"));
            list.add(bean);
        }
        return list;
    }

    private List<FavoriteGoodBean> convertGood(JSONArray data) {
        List<FavoriteGoodBean> list = new ArrayList<>();
        if (data == null) {
            return list;
        }
        FavoriteGoodBean bean;
        for (int i = 0; i < data.length(); i++) {
            bean = new FavoriteGoodBean();
            bean.setFavoriteId(data.optJSONObject(i).optString("favoriteId"));
            bean.setGoodsId(data.optJSONObject(i).optString("goodsId"));
            bean.setGoodsImg(data.optJSONObject(i).optString("goodsImg"));
            bean.setGoodsName(data.optJSONObject(i).optString("goodsName"));
            bean.setReturnPrice(data.optJSONObject(i).optString("returnPrice"));
            bean.setScoreRate(data.optJSONObject(i).optString("scoreRate"));
            bean.setShopPrice(data.optJSONObject(i).optString("shopPrice"));
            list.add(bean);
        }
        return list;
    }

}
