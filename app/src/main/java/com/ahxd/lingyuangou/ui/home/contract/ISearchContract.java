package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/17.
 */

public interface ISearchContract {

    public interface ISearchView extends BaseView {
        public void showSearchGood(List<ShopGoodBean> list);

        public void showSearchShop(List<FoodShopBean> list);
    }

    public interface ISearchPresenter {
        // 关键词搜索
        public void searchKeywords(int type, int page, String content);
    }

    public interface ISearchModel {

        public void searchKeywords(int type, int page, String content, ISearchCallback callback);

        public abstract class ISearchCallback extends ModelCallback {

            public ISearchCallback(BaseView view) {
                super(view);
            }

            public void onSearchGood(List<ShopGoodBean> list) {}

            public void onSearchShop(List<FoodShopBean> list) {}
        }

    }

}
