package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IRegisterContract;
import com.ahxd.lingyuangou.ui.mine.model.RegisterModel;

/**
 * Created by Administrator on 2018/1/8.
 */

public class RegisterPresenter implements IRegisterContract.IRegisterPresenter {

    public IRegisterContract.IRegisterView mView;
    private RegisterModel mModel;

    public RegisterPresenter(IRegisterContract.IRegisterView view) {
        this.mView = view;
        mModel = new RegisterModel();
    }

    @Override
    public void register(String invite, String loginName, String loginPwd, String reloginPwd, String yzm) {
        mModel.register(invite, loginName, loginPwd, reloginPwd, yzm, new IRegisterContract.IRegisterModel.IRegisterCallback(mView) {
            @Override
            public void onRegister(String msg) {
                mView.showRegister(msg);
            }
        });
    }

    @Override
    public void getValidateCode(String tel) {
        mModel.getValidateCode(tel, new IRegisterContract.IRegisterModel.IRegisterCallback(mView) {
            @Override
            public void onValidateCode(String msg) {
                mView.showGetValidate(msg);
            }
        });
    }
}
