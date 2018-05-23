package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.ArticleBean;
import com.ahxd.lingyuangou.bean.GoodsBean;
import com.ahxd.lingyuangou.ui.home.contract.IShopGoodsListContract;
import com.ahxd.lingyuangou.ui.home.model.ShopGoodsModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class ShopGoodsListPresenter implements IShopGoodsListContract.IShopGoodsPresenter {

    private IShopGoodsListContract.IShopGoodsListView mView;
    private ShopGoodsModel mModel;

    public ShopGoodsListPresenter(IShopGoodsListContract.IShopGoodsListView view) {
        this.mView = view;
        mModel = new ShopGoodsModel();
    }

    @Override
    public void getShopGoodsList(String shopId, int page, String keywords) {
        mModel.getShopGoodsList(shopId, page, keywords,new IShopGoodsListContract.IShopGoodsListModel.IShopGoodsCallback(mView) {
            @Override
            public void onShopGoodsList(List<GoodsBean> list) {
                mView.showShopGoodsList(list);
            }
        });
    }
}
