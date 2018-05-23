package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.bean.FoodShopEvaluateBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public interface IExchangeGiftContract {

    public interface IExchangeGiftView extends BaseView {
        public void showBanner( ExchangeBean bean);
        public void showCats( ExchangeBean bean);
        public void showHots( ExchangeBean bean);
    }

    public interface IExchangeGiftPresenter {
        public void getHomeData(int page);

    }

    public interface IExchangeGiftModel {

        public void getExchangeGiftData(int page,IExchangeGiftCallback callback);

        public abstract class IExchangeGiftCallback extends ModelCallback {

            public IExchangeGiftCallback(BaseView view) {
                super(view);
            }

            public void onBannerData(ExchangeBean banner) {}
            public void onCatData(ExchangeBean cats) {}
            public void onHotData(ExchangeBean hots) {}


        }

    }

}
