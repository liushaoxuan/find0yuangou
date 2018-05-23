package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IMyBusinessCardContract;
import com.ahxd.lingyuangou.ui.mine.contract.IMyMarketingCodeContract;
import com.ahxd.lingyuangou.ui.mine.model.MyBusinessCardModel;
import com.ahxd.lingyuangou.ui.mine.model.MyMarketingCodeModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */
public class MyBusinessCardPresenter implements IMyBusinessCardContract.IMyBusinessCardPresenter{

    private IMyBusinessCardContract.IMyBusinessCardView mView;
    private MyBusinessCardModel mModel;

    public MyBusinessCardPresenter(IMyBusinessCardContract.IMyBusinessCardView view) {
        this.mView = view;
        mModel = new MyBusinessCardModel();
    }

    @Override
    public void getMyBusinessCard() {
        mModel.getMyBusinessCard(new IMyBusinessCardContract.IMyBusinessCardModel.IMyBusinessCardCallback(mView) {
            @Override
            public void onMyBusinessCard(JSONObject data) {
                mView.showMyBusinessCard(data);
            }
        });
    }
}
