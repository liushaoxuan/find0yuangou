package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.ShopBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public interface IGiftShopListContract {

    public interface IGiftShopListView extends BaseView {
        public void showGiftShopList(List<ShopBean> list);
    }

    public interface IGiftShopListPresenter {
        public void getGiftShopList(int page, String keywords);
    }

    public interface IGiftShopListModel {

        // 获取商铺列表
        public void getGiftShopList(int page, String keywords, IGiftShopListCallback callback);

        public abstract class IGiftShopListCallback extends ModelCallback {

            public IGiftShopListCallback(BaseView view) {
                super(view);
            }

            public void onGiftShopList(List<ShopBean> result) {}

        }

    }

}
