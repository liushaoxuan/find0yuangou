package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;
import com.ahxd.lingyuangou.ui.home.contract.ISearchContract;
import com.ahxd.lingyuangou.ui.home.model.SearchModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/17.
 */

public class SearchPresenter implements ISearchContract.ISearchPresenter {

    private ISearchContract.ISearchView mView;
    private ISearchContract.ISearchModel mModel;

    public SearchPresenter(ISearchContract.ISearchView view) {
        this.mView = view;
        this.mModel = new SearchModel();
    }

    @Override
    public void searchKeywords(int type, int page, String content) {
        mModel.searchKeywords(type, page, content, new ISearchContract.ISearchModel.ISearchCallback(mView) {
            @Override
            public void onSearchGood(List<ShopGoodBean> list) {
                mView.showSearchGood(list);
            }

            @Override
            public void onSearchShop(List<FoodShopBean> list) {
                mView.showSearchShop(list);
            }
        });
    }
}
