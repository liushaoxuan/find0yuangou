package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IEditPersonalContract;
import com.ahxd.lingyuangou.ui.mine.model.EditPersonalModel;

/**
 * Created by Mao on 2018/1/15.
 */

public class EditPersonalPresenter implements IEditPersonalContract.IEditPersonalPresenter {

    private IEditPersonalContract.IEditPersonalView mView;
    private EditPersonalModel mModel;

    public EditPersonalPresenter(IEditPersonalContract.IEditPersonalView view) {
        this.mView = view;
        mModel = new EditPersonalModel();
    }

    @Override
    public void submitPersonal(String userName, String userPhoto, String birthday, String userPhone,
                               String userWeixin, int userSex) {
        mModel.submitPersonal(userName, userPhoto, birthday, userPhone, userWeixin, userSex,
                new IEditPersonalContract.IEditPersonalModel.IEditPersonalCallback(mView) {
                    @Override
                    public void onSubmitPersonal(String msg) {
                        mView.showSubmitPersonal(msg);
                    }
                });
    }
}
