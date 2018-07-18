package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.bean.BannerBean;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.bean.HomeFinanceBean;
import com.ahxd.lingyuangou.bean.HomeFoodShopBean;
import com.ahxd.lingyuangou.bean.HomeGiftBean;
import com.ahxd.lingyuangou.bean.HomeGoodBean;
import com.ahxd.lingyuangou.bean.HomeRecomendCarBean;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IHomeContract;
import com.ahxd.lingyuangou.utils.LocationUtils;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public class HomeModel implements IHomeContract.IHomeModel {

    @Override
    public void getHomeData(final IHomeCallback callback) {
        String  areaid =  LocationUtils.getInstance().getLocation();
        areaid.isEmpty();
        OkGo.<String>post(HostUrl.URL_HOME)
                .params("areaId", LocationUtils.getInstance().getLocation())
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        JSONObject data = root.optJSONObject(Constant.RESP_DATA);

                        JSONArray banner = data.optJSONArray("banner");
                        callback.onBannerData(JSON.parseArray(banner.toString(),BannerBean.class));

                        JSONArray cats = data.optJSONArray("cats");
                        callback.onCatData(getCatsData(cats));

                        JSONArray ads = data.optJSONArray("ads");
                        callback.onAdData(ads);

                        JSONArray hotels = data.optJSONArray("hotel");
                        callback.onHotelsData(getFoodsData(hotels));

                        JSONArray educations = data.optJSONArray("education");
                        callback.onEducationData(getFoodsData(educations));

                        JSONArray entertainments = data.optJSONArray("entertainment");
                        callback.onEntertainmentData(getFoodsData(entertainments));

                        JSONArray foods = data.optJSONArray("foods");
                        callback.onFoodData(getFoodsData(foods));

                        JSONArray finances = data.optJSONArray("finances");
                        callback.onFinanceData(getFinanceData(finances));

                        JSONArray goods = data.optJSONArray("goods");
                        callback.onGoodsData(getGoodsData(goods));

                        JSONArray articles = data.optJSONArray("articles");
                        callback.onArticleData(articles);

                        JSONArray gifts = data.optJSONArray("gifts");
                        callback.onGiftData(getGiftsData(gifts));

                        //商务
                        JSONArray commerce = data.optJSONArray("commerce");
                        callback.onCommerceData(JSON.parseArray(commerce.toString(),HomeFoodShopBean.class));
                        //家政
                        JSONArray housekeeping = data.optJSONArray("housekeeping");
                        callback.onHousekeepingData(JSON.parseArray(housekeeping.toString(),HomeFoodShopBean.class));

                        JSONArray healths = data.optJSONArray("health");
                        callback.onHealthData(JSON.parseArray(healths.toString(),HomeFoodShopBean.class));

                        JSONArray cars = data.optJSONArray("car");
                        callback.onCarsData(JSON.parseArray(cars.toString(),HomeFoodShopBean.class));

                        //家居
                        JSONArray home = data.optJSONArray("home");
                        callback.onHomeData(JSON.parseArray(home.toString(),HomeFoodShopBean.class));
                    }
                });
    }

    private List<HomeCatBean> getCatsData(JSONArray cats) {
        List<HomeCatBean> catsList = new ArrayList<>();
        HomeCatBean catBean;
        for (int i = 0; i < cats.length(); i++) {
            catBean = new HomeCatBean();
            catBean.setCatId(cats.optJSONObject(i).optString("catId"));
            catBean.setCatName(cats.optJSONObject(i).optString("catName"));
            catBean.setChannelIcon(cats.optJSONObject(i).optString("channelIcon"));
            catsList.add(catBean);
        }
        return catsList;
    }

    private List<HomeGiftBean> getGiftsData(JSONArray gifts) {
        List<HomeGiftBean> giftBeanList = new ArrayList<>();
        HomeGiftBean giftBean;
        for (int i = 0; i < gifts.length(); i++) {
            giftBean = new HomeGiftBean();
            giftBean.setGoodsId(gifts.optJSONObject(i).optString("goodsId"));
            giftBean.setGoodsImg(gifts.optJSONObject(i).optString("goodsImg"));
            giftBean.setGoodsName(gifts.optJSONObject(i).optString("goodsName"));
            giftBean.setShopPrice(gifts.optJSONObject(i).optString("shopPrice"));
            giftBeanList.add(giftBean);
        }
        return giftBeanList;
    }

    private List<HomeFoodShopBean> getFoodsData(JSONArray foods) {

        List<HomeFoodShopBean> foodShopBeanList = new ArrayList<>();
        HomeFoodShopBean foodShopBean;
        if (null!=foods) {
            for (int i = 0; i < foods.length(); i++) {
                foodShopBean = new HomeFoodShopBean();
                foodShopBean.setShopId(foods.optJSONObject(i).optString("shopId"));
                foodShopBean.setShopName(foods.optJSONObject(i).optString("shopName"));
                foodShopBean.setShopImg(foods.optJSONObject(i).optString("shopImg"));
                foodShopBean.setDistance(foods.optJSONObject(i).optString("distance"));
                foodShopBean.setScoreRate(foods.optJSONObject(i).optString("scoreRate"));
                foodShopBean.setSaleCount(foods.optJSONObject(i).optString("saleCount"));
                foodShopBean.setShopAddress(foods.optJSONObject(i).optString("shopAddress"));
                foodShopBeanList.add(foodShopBean);
            }
        }
        return foodShopBeanList;

    }

    private List<HomeFoodShopBean> getFinanceData(JSONArray finances) {
        List<HomeFoodShopBean> financeBeanList = new ArrayList<>();
        HomeFoodShopBean homeFinanceBean;
        for (int i = 0; i < finances.length(); i++) {
            homeFinanceBean = new HomeFoodShopBean();
            homeFinanceBean.setShopId(finances.optJSONObject(i).optString("shopId"));
            homeFinanceBean.setShopName(finances.optJSONObject(i).optString("shopName"));
            homeFinanceBean.setShopImg(finances.optJSONObject(i).optString("shopImg"));
            homeFinanceBean.setDistance(finances.optJSONObject(i).optString("distance"));
            homeFinanceBean.setScoreRate(finances.optJSONObject(i).optString("scoreRate"));
            homeFinanceBean.setSaleCount(finances.optJSONObject(i).optString("saleCount"));
            homeFinanceBean.setShopAddress(finances.optJSONObject(i).optString("shopAddress"));
            financeBeanList.add(homeFinanceBean);
        }
        return financeBeanList;
    }

    private List<HomeGoodBean> getGoodsData(JSONArray goods) {
        List<HomeGoodBean> goodBeanList = new ArrayList<>();
        HomeGoodBean goodBean;
        for (int i = 0; i < goods.length(); i++) {
            goodBean = new HomeGoodBean();
            goodBean.setGoodsId(goods.optJSONObject(i).optString("goodsId"));
            goodBean.setGoodsImg(goods.optJSONObject(i).optString("goodsImg"));
            goodBean.setGoodsName(goods.optJSONObject(i).optString("goodsName"));
            goodBean.setGoodsTips(goods.optJSONObject(i).optString("goodsTips"));
            goodBean.setReturnPrice(goods.optJSONObject(i).optString("returnPrice"));
            goodBean.setShopPrice(goods.optJSONObject(i).optString("shopPrice"));
            goodBeanList.add(goodBean);
        }
        return goodBeanList;
    }

}
