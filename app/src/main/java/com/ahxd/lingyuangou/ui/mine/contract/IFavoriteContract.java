package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.FavoriteGoodBean;
import com.ahxd.lingyuangou.bean.FavoriteShopBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Mao on 2018/1/12.
 */

public interface IFavoriteContract {

    public interface IFavoriteView extends BaseView {
        public void showShopFavorite(List<FavoriteShopBean> list);
        public void showGoodFavorite(List<FavoriteGoodBean> list);
        public void showDeleteFavorite(String data);
    }

    public interface IFavoritePresenter {
        public void getFavoriteList(int favoriteType);
        public void deleteFavoriteList(String favoriteId);
    }

    public interface IFavoriteModel {

        public void getFavoriteList(int favoriteType, IFavoriteCallback callback);

        public void deleteFavoriteList(String favoriteId, IFavoriteCallback callback);

        public abstract class IFavoriteCallback extends ModelCallback {

            public IFavoriteCallback(BaseView view) {
                super(view);
            }

            public void onShopFavorite(List<FavoriteShopBean> list) {}

            public void onGoodFavorite(List<FavoriteGoodBean> list) {}

            public void onDeleteFavorite(String data) {}


        }
    }
}
