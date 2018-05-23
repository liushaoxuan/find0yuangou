package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IMarketingCenterContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.model.MarketingCenterModel;
import com.ahxd.lingyuangou.ui.mine.model.WalletModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */
public class MarketingCenterPresenter implements IMarketingCenterContract.IMarketingCenterPresenter{

    private IMarketingCenterContract.IMarketingCenterView mView;
    private MarketingCenterModel mModel;

    public MarketingCenterPresenter(IMarketingCenterContract.IMarketingCenterView view) {
        this.mView = view;
        mModel = new MarketingCenterModel();
    }

    @Override
    public void getMarketingCenter() {
        mModel.getMarketingCenter(new IMarketingCenterContract.IMarketingCenterModel.IMarketingCenterCallback(mView) {
            @Override
            public void onMarketingCenter(JSONObject data) {
                mView.showMarketingCenter(data);
            }
        });
    }
}
