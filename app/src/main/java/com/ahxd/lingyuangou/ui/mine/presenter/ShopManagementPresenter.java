package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.MarketingBean;
import com.ahxd.lingyuangou.bean.MyMemberBean;
import com.ahxd.lingyuangou.ui.mine.contract.IMyMemberContract;
import com.ahxd.lingyuangou.ui.mine.contract.IShopManagementContract;
import com.ahxd.lingyuangou.ui.mine.model.MyMemberModel;
import com.ahxd.lingyuangou.ui.mine.model.ShopManagementModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class ShopManagementPresenter implements IShopManagementContract.IShopManagementListPresenter {

    private IShopManagementContract.IShopManagementListView mView;
    private ShopManagementModel mModel;

    public ShopManagementPresenter(IShopManagementContract.IShopManagementListView view) {
        this.mView = view;
        this.mModel = new ShopManagementModel();
    }

    @Override
    public void getShopManagementList(int mPage) {
        mModel.getShopManagementList(mPage,new IShopManagementContract.IShopManagementListModel.IShopManagementListCallback(mView) {
            @Override
            public void onShopManagementList(List<MarketingBean> list) {
                mView.showShopManagementList(list);
            }
        });
    }

}
