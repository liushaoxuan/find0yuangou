package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.ui.home.contract.IMainContract;
import com.ahxd.lingyuangou.ui.home.model.MainModel;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.model.WalletModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */
public class MainPresenter implements IMainContract.IMainPresenter{

    private IMainContract.IMainView mView;
    private MainModel mModel;

    public MainPresenter(IMainContract.IMainView view) {
        this.mView = view;
        mModel = new MainModel();
    }

    @Override
    public void getApk() {
        mModel.getApk(new IMainContract.IMainModel.IMainCallback(mView) {
            @Override
            public void onApk(JSONObject data) {
                mView.showApk(data);
            }
        });
    }
}
