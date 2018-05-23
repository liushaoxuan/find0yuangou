package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IMyMarketingCodeContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.model.MyMarketingCodeModel;
import com.ahxd.lingyuangou.ui.mine.model.WalletModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */
public class MyMarketingCodePresenter implements IMyMarketingCodeContract.IMyMarketingCodePresenter{

    private IMyMarketingCodeContract.IMyMarketingCodeView mView;
    private MyMarketingCodeModel mModel;

    public MyMarketingCodePresenter(IMyMarketingCodeContract.IMyMarketingCodeView view) {
        this.mView = view;
        mModel = new MyMarketingCodeModel();
    }

    @Override
    public void getMyMarketingCode() {
        mModel.getMyMarketingCode(new IMyMarketingCodeContract.IMyMarketingCodeModel.IMyMarketingCodeCallback(mView) {
            @Override
            public void onMyMarketingCode(JSONObject data) {
                mView.showMyMarketingCode(data);
            }
        });
    }
}
