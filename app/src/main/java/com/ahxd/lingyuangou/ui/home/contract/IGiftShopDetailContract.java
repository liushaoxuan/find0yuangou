package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.ShopBean;

/**
 * Created by Administrator on 2018/1/2.
 */

public interface IGiftShopDetailContract {

    public interface IGiftShopDetailView extends BaseView {
        public void showGiftShopDetail(ShopBean bean);

    }

    public interface IGiftShopDetailPresenter {
        public void getGiftShopDetail(String shopId);

    }

    public interface IGiftShopDetailModel {

        public void getGiftShopDetail(String shopId,IGiftShopDetailCallback callback);

        public abstract class IGiftShopDetailCallback extends ModelCallback {

            public IGiftShopDetailCallback(BaseView view) {
                super(view);
            }

            public void onGiftShopDetail(ShopBean banner) {}
        }

    }

}
