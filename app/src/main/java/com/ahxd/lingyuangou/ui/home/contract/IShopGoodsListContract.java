package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.ArticleBean;
import com.ahxd.lingyuangou.bean.GoodsBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public interface IShopGoodsListContract {

    public interface IShopGoodsListView extends BaseView {
        public void showShopGoodsList(List<GoodsBean> list);
    }

    public interface IShopGoodsPresenter {
        public void getShopGoodsList(String catId, int page,String keywords);
    }

    public interface IShopGoodsListModel {
        public void getShopGoodsList(String catId, int page,String keywords, IShopGoodsCallback callback);
        public abstract class IShopGoodsCallback extends ModelCallback {

            public IShopGoodsCallback(BaseView view) {
                super(view);
            }

            public void onShopGoodsList(List<GoodsBean> list) {}
        }

    }

}
