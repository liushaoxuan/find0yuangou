package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.ShopGoodBean;
import com.ahxd.lingyuangou.ui.home.contract.IGoodsListContract;
import com.ahxd.lingyuangou.ui.home.model.GoodsModel;

import java.util.List;

/**
 * Created by Mao zhendong on 2018/1/12.
 */

public class GoodsListPresenter implements IGoodsListContract.IGoodsListPresenter {

    private IGoodsListContract.IGoodsListView mView;
    private GoodsModel mModel;

    public GoodsListPresenter(IGoodsListContract.IGoodsListView view) {
        this.mView = view;
        mModel = new GoodsModel();
    }

    @Override
    public void getRecomGoodsList(int mpage,String keyword,String areaId, String goodsCatId) {
        mModel.getRecomGoodsList(mpage,keyword,areaId, goodsCatId, new IGoodsListContract.IGoodSListModel.IGoodsListCallback(mView) {
            @Override
            public void onRecomGoodsList(List<ShopGoodBean> list) {
                mView.showRecomGoodsList(list);
            }
        });
    }
}
