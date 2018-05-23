package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IPersonalInfoContract;
import com.ahxd.lingyuangou.ui.mine.model.PersonalInfoModel;

import org.json.JSONObject;

/**
 * Created by Mao on 2018/1/14.
 */

public class PersonalInfoPresenter implements IPersonalInfoContract.IPersonalInfoPresenter {

    private IPersonalInfoContract.IPersonalInfoView mView;
    private PersonalInfoModel mModel;

    public PersonalInfoPresenter(IPersonalInfoContract.IPersonalInfoView view) {
        this.mView = view;
        mModel = new PersonalInfoModel();
    }

    @Override
    public void getPersonalInfo() {
        mModel.getPersonalInfo(new IPersonalInfoContract.IPersonalInfoModel.IPersonalInfoCallback(mView) {
            @Override
            public void onPersonalInfo(JSONObject data) {
                mView.showPersonalInfo(data);
            }
        });
    }
}
