package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.ShopGoodBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public interface IGoodsListContract {


    public interface IGoodsListView extends BaseView {
        public void showRecomGoodsList(List<ShopGoodBean> list);
    }

    public interface IGoodsListPresenter {
        public void getRecomGoodsList(int mpage,String keyword,String areaId, String goodsCatId);
    }

    public interface IGoodSListModel {

        public void getRecomGoodsList(int mpage,String keyword,String areaId, String goodsCatId, IGoodsListCallback callback);

        public abstract class IGoodsListCallback extends ModelCallback {

            public IGoodsListCallback(BaseView view) {
                super(view);
            }

            public void onRecomGoodsList(List<ShopGoodBean> list) {}
        }
    }

}
