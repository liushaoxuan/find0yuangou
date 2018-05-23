package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IMarketingApplyContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.model.MarketingApplyModel;
import com.ahxd.lingyuangou.ui.mine.model.WalletModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */
public class MarketingApplyPresenter implements IMarketingApplyContract.IMarketingApplyPresenter{

    private IMarketingApplyContract.IMarketingApplyView mView;
    private MarketingApplyModel mModel;

    public MarketingApplyPresenter(IMarketingApplyContract.IMarketingApplyView view) {
        this.mView = view;
        mModel = new MarketingApplyModel();
    }

    @Override
    public void getMarketingApply() {
        mModel.getMarketingApply(new IMarketingApplyContract.IMarketingApplyModel.IMarketingApplyCallback(mView) {
            @Override
            public void onMarketingData(JSONObject data) {
                mView.showMarketingData(data);
            }
        });
    }

    @Override
    public void setMarketingApply() {
        mModel.setMarketingApply(new IMarketingApplyContract.IMarketingApplyModel.IMarketingApplyCallback(mView) {
            @Override
            public void onMarketingApply(JSONObject data) {
                mView.showMarketingApply(data);
            }
        });

    }
}
