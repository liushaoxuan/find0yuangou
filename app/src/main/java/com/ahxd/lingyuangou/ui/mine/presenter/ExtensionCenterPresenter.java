package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IExtensionCenterContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.model.ExtensionCenterModel;
import com.ahxd.lingyuangou.ui.mine.model.WalletModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */
public class ExtensionCenterPresenter implements IExtensionCenterContract.IExtensionCenterPresenter{

    private IExtensionCenterContract.IExtensionCenterView mView;
    private ExtensionCenterModel mModel;

    public ExtensionCenterPresenter(IExtensionCenterContract.IExtensionCenterView view) {
        this.mView = view;
        mModel = new ExtensionCenterModel();
    }

    @Override
    public void getExtensionCenter() {
        mModel.getExtensionCenter(new IExtensionCenterContract.IExtensionCenterModel.IExtensionCenterCallback(mView) {
            @Override
            public void onExtensionCenter(JSONObject data) {
                mView.showExtensionCenter(data);
            }
        });
    }
    @Override
    public void getShare() {
        mModel.getShare(new IExtensionCenterContract.IExtensionCenterModel.IExtensionCenterCallback(mView) {
            @Override
            public void onShare(JSONObject data) {
                mView.showShare(data);
            }
        });
    }
}
