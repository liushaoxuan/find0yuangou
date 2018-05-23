package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.MarketingBean;
import com.ahxd.lingyuangou.bean.MyMemberBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public interface IShopManagementContract {

    public interface IShopManagementListView extends BaseView {
        public void showShopManagementList(List<MarketingBean> list);

    }

    public interface IShopManagementListPresenter {
        public void getShopManagementList(int mPage);
    }

    public interface IShopManagementListModel {

        public void getShopManagementList(int mPage, IShopManagementListCallback callback);

        public abstract class IShopManagementListCallback extends ModelCallback {

            public IShopManagementListCallback(BaseView view) {
                super(view);
            }

            public void onShopManagementList(List<MarketingBean> list) {}

        }
    }

}
