package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.FavoriteGoodBean;
import com.ahxd.lingyuangou.bean.FavoriteShopBean;
import com.ahxd.lingyuangou.ui.mine.contract.IFavoriteContract;
import com.ahxd.lingyuangou.ui.mine.model.FavoriteModel;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Mao on 2018/1/12.
 */

public class FavoritePresenter implements IFavoriteContract.IFavoritePresenter {

    private IFavoriteContract.IFavoriteView mView;
    private IFavoriteContract.IFavoriteModel mModel;

    public FavoritePresenter(IFavoriteContract.IFavoriteView view) {
        this.mView = view;
        mModel = new FavoriteModel();
    }

    @Override
    public void getFavoriteList(int favoriteType) {
        mModel.getFavoriteList(favoriteType, new IFavoriteContract.IFavoriteModel.IFavoriteCallback(mView) {

            @Override
            public void onShopFavorite(List<FavoriteShopBean> list) {
                mView.showShopFavorite(list);
            }

            @Override
            public void onGoodFavorite(List<FavoriteGoodBean> list) {
                mView.showGoodFavorite(list);
            }
        });
    }

    @Override
    public void deleteFavoriteList(String favoriteId) {
        mModel.deleteFavoriteList(favoriteId, new IFavoriteContract.IFavoriteModel.IFavoriteCallback(mView) {
            @Override
            public void onDeleteFavorite(String data) {
                mView.showDeleteFavorite(data);
            }

        });
    }
}
