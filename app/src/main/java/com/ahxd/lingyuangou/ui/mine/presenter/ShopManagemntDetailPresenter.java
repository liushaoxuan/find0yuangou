package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.ShopManagemntDetailBean;
import com.ahxd.lingyuangou.ui.mine.contract.IShopManagemntDetailContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.model.ShopManagemntDetailModel;
import com.ahxd.lingyuangou.ui.mine.model.WalletModel;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */
public class ShopManagemntDetailPresenter implements IShopManagemntDetailContract.IShopManagemntDetailPresenter{

    private IShopManagemntDetailContract.IShopManagemntDetailView mView;
    private ShopManagemntDetailModel mModel;

    public ShopManagemntDetailPresenter(IShopManagemntDetailContract.IShopManagemntDetailView view) {
        this.mView = view;
        mModel = new ShopManagemntDetailModel();
    }

    @Override
    public void getShopManagemntDetail(String shopId,int mPage) {
        mModel.getShopManagemntDetail(shopId,mPage,new IShopManagemntDetailContract.IShopManagemntDetailModel.IShopManagemntDetailCallback(mView) {
            @Override
            public void onShopManagemntDetail(ShopManagemntDetailBean bean) {
                mView.showShopManagemntDetail(bean);
            }
        });
    }
}
