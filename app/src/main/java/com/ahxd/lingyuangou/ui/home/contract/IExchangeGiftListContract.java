package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.AccredBean;
import com.ahxd.lingyuangou.bean.CatBean;
import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.bean.FoodShopEvaluateBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public interface IExchangeGiftListContract {

    public interface IExchangeGiftListView extends BaseView {
        public void showExchangeGiftList(List<ExchangeBean.ExchangeHots> list);
        public void showCatsList(List<CatBean> result);
        public void showAccredList(List<AccredBean> result);
    }

    public interface IExchangeGiftListPresenter {
        public void getExchangeGiftList(int page,String keywords,String goodsCatId);
        public void getCatsList();
        public void getAccredList();
    }

    public interface IExchangeGiftListModel {
        // 获取商城品类列表
        public void getCatsList(IExchangeGiftListCallback callback);
        // 获取商城品类列表
        public void getAccredList(IExchangeGiftListCallback callback);

        // 获取商铺列表
        public void getExchangeGiftList(int page,String keywords, String goodsCatId,IExchangeGiftListCallback callback);

        public abstract class IExchangeGiftListCallback extends ModelCallback {

            public IExchangeGiftListCallback(BaseView view) {
                super(view);
            }
            public void onCatList(List<CatBean> result) {}
            public void onAccredList(List<AccredBean> result) {}
            public void onExchangeGiftList(List<ExchangeBean.ExchangeHots> result) {}

        }

    }

}
