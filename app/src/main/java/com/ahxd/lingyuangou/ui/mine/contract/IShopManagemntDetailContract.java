package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.ShopManagemntDetailBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IShopManagemntDetailContract {

    public interface IShopManagemntDetailView extends BaseView {
        public void showShopManagemntDetail(ShopManagemntDetailBean bean);
    }

    public interface IShopManagemntDetailPresenter {

        public void getShopManagemntDetail(String shopId,int mPage);
    }

    public interface IShopManagemntDetailModel {

        public void getShopManagemntDetail(String shopId,int mPage,IShopManagemntDetailCallback callback);

        public abstract class IShopManagemntDetailCallback extends ModelCallback {

            public IShopManagemntDetailCallback(BaseView view) {
                super(view);
            }

            public void onShopManagemntDetail(ShopManagemntDetailBean bean) {}
        }

    }


}
