package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IMarketingApplyContract;
import com.ahxd.lingyuangou.ui.mine.contract.IMineContactContract;
import com.ahxd.lingyuangou.ui.mine.model.MarketingApplyModel;
import com.ahxd.lingyuangou.ui.mine.model.MineContactModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */
public class MineContactPresenter implements IMineContactContract.IMineContactPresenter{

    private IMineContactContract.IMineContactView mView;
    private MineContactModel mModel;

    public MineContactPresenter(IMineContactContract.IMineContactView view) {
        this.mView = view;
        mModel = new MineContactModel();
    }

    @Override
    public void getMineContact() {
        mModel.getMineContact(new IMineContactContract.IMineContactModel.IMineContactCallback(mView) {
            @Override
            public void onMineContact(JSONObject data) {
                mView.showMineContact(data);
            }
        });
    }


}
