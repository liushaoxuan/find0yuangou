package com.ahxd.lingyuangou.bean;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mao Zhendong on 2018/1/8.
 * 积分商城首页 Bean
 */

public class ExchangeBean {


    /**
     *
     */
    private String score;
    private List<ExchangeBanner> banner;
    private List<ExchangeCats> cats;
    private List<ExchangeHots> hots;

    public List<ExchangeBanner> getBanner() {
        return banner;
    }

    public void setBanner(List<ExchangeBanner> banner) {
        this.banner = banner;
    }

    public List<ExchangeCats> getCats() {
        return cats;
    }

    public void setCats(List<ExchangeCats> cats) {
        this.cats = cats;
    }

    public List<ExchangeHots> getHots() {
        return hots;
    }

    public void setHots(List<ExchangeHots> hots) {
        this.hots = hots;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public static class ExchangeBanner {

        private String adFile;
        private String adName;
        private String adType;
        private String adTypeData;

        public String getAdFile() {
            return adFile;
        }

        public void setAdFile(String adFile) {
            this.adFile = adFile;
        }

        public String getAdName() {
            return adName;
        }

        public void setAdName(String adName) {
            this.adName = adName;
        }

        public String getAdType() {
            return adType;
        }

        public void setAdType(String adType) {
            this.adType = adType;
        }

        public String getAdTypeData() {
            return adTypeData;
        }

        public void setAdTypeData(String adTypeData) {
            this.adTypeData = adTypeData;
        }
    }
    public static class ExchangeCats {

        private String catId;
        private String catName;
        private String channelIcon;

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getChannelIcon() {
            return channelIcon;
        }

        public void setChannelIcon(String channelIcon) {
            this.channelIcon = channelIcon;
        }
    }
    public static class ExchangeHots {

        private String goodsId;
        private String goodsImg;
        private String goodsName;
        private String marketPrice;
        private String shopPrice;
        private String saleNum;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsImg() {
            return goodsImg;
        }

        public void setGoodsImg(String goodsImg) {
            this.goodsImg = goodsImg;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(String marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getShopPrice() {
            return shopPrice;
        }

        public void setShopPrice(String shopPrice) {
            this.shopPrice = shopPrice;
        }

        public String getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(String saleNum) {
            this.saleNum = saleNum;
        }
    }
}
