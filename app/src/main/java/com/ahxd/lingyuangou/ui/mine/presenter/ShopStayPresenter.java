package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IShopStayContract;
import com.ahxd.lingyuangou.ui.mine.model.ShopStayModel;

/**
 * Created by Administrator on 2018/1/9.
 */

public class ShopStayPresenter implements IShopStayContract.IShopStayPresenter {

    private IShopStayContract.IShopStayView mView;
    private IShopStayContract.IShopStayModel mModel;

    public ShopStayPresenter(IShopStayContract.IShopStayView view) {
        mView = view;
        mModel = new ShopStayModel();
    }

    @Override
    public void getCode(String tel) {
        mModel.getCode(tel, new IShopStayContract.IShopStayModel.IShopStayCallback(mView) {
            @Override
            public void onShopCode(String msg) {
                mView.showGetCode(msg);
            }
        });
    }

    @Override
    public void submitApply(String markerCode, String linkman, String tel, String remark, String code) {
        mModel.submitApply(markerCode, linkman, tel, remark, code, new IShopStayContract.IShopStayModel.IShopStayCallback(mView) {
            @Override
            public void onSubmit(String msg) {
                mView.showSubmit(msg);
            }
        });
    }
}
