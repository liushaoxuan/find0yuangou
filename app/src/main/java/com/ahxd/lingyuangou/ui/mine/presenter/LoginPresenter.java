package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.ILoginContract;
import com.ahxd.lingyuangou.ui.mine.model.LoginModel;

/**
 * Created by Administrator on 2018/1/8.
 */

public class LoginPresenter implements ILoginContract.ILoginPresenter {

    private ILoginContract.ILoginView mView;
    private LoginModel mModel;

    public LoginPresenter(ILoginContract.ILoginView view) {
        this.mView = view;
        mModel = new LoginModel();
    }

    @Override
    public void login(String loginName, String loginPwd) {
        mModel.login(loginName, loginPwd, new ILoginContract.ILoginModel.ILoginCallback(mView) {
            @Override
            public void onLogin(String token) {
                mView.showLogin(token);
            }
        });
    }
}
