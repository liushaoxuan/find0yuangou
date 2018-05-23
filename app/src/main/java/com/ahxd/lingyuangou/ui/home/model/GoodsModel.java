package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.bean.ShopGoodBean;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IGoodsListContract;
import com.ahxd.lingyuangou.utils.LocationUtils;
import com.lzy.okgo.OkGo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class GoodsModel implements IGoodsListContract.IGoodSListModel {

    @Override
    public void getRecomGoodsList(int mpage,String keyword,String areaId, String goodsCatId, final IGoodsListCallback callback) {
        OkGo.<String>post(HostUrl.URL_RECOM_GOODS_LIST)
//                .params("areaId", LocationUtils.getInstance().getLocation())
                .params("goodsCatId", goodsCatId)
                .params("mpage", mpage)
                .params("keywords", keyword)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        callback.onRecomGoodsList(convertShopGoods(root.optJSONArray(Constant.RESP_DATA)));
                    }
                });
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
