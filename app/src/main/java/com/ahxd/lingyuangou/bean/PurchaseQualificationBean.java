package com.ahxd.lingyuangou.bean;

import java.util.List;

/**
 * Created by sxliu on 2018/7/11 20:18
 * E-mail Address 2587294424@qq.com
 * 购买资格和商家卡的bean
 */

public class PurchaseQualificationBean extends BaseBean {

    /**
     * cards : [{"giveMoney":"0.00","memberCardid":3,"memberCardName":"商家卡","needCash":"5.00"},{"giveMoney":"0.00","memberCardid":4,"memberCardName":"合伙人","needCash":"6.00"}]
     * shops : {"shopName":"0元购自营总部","shopId":1}
     */
    private List<BusinessCardBean> cards;
    private ShopBean shops;

    public void setCards(List<BusinessCardBean> cards) {
        this.cards = cards;
    }

    public void setShops(ShopBean shops) {
        this.shops = shops;
    }

    public List<BusinessCardBean> getCards() {
        return cards;
    }

    public ShopBean getShops() {
        return shops;
    }

}
