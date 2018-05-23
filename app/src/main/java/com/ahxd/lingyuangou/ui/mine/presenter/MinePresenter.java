package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IMineContract;
import com.ahxd.lingyuangou.ui.mine.model.MineModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/5.
 */

public class MinePresenter implements IMineContract.IMinePresenter {

    private IMineContract.IMineView mView;
    private MineModel mModel;

    public MinePresenter(IMineContract.IMineView view) {
        this.mView = view;
        mModel = new MineModel();
    }

    @Override
    public void getMyProfile() {
        mModel.getMyProfile(new IMineContract.IMineModel.IMineCallback(mView) {
            @Override
            public void onProfileData(JSONObject data) {
                mView.showProfile(data);
            }
        });
    }

    @Override
    public void getCustomService() {
        mModel.getCustomService(new IMineContract.IMineModel.IMineCallback(mView) {
            @Override
            public void onCustomService(JSONObject data) {
                mView.showCustomService(data);
            }
        });
    }
}
