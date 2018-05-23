package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IForgetContract;
import com.ahxd.lingyuangou.ui.mine.contract.IRegisterContract;
import com.ahxd.lingyuangou.ui.mine.model.ForgetModel;
import com.ahxd.lingyuangou.ui.mine.model.RegisterModel;

/**
 * Created by Administrator on 2018/1/8.
 */

public class ForgetPresenter implements IForgetContract.IForgetPresenter {

    public IForgetContract.IForgetView mView;
    private ForgetModel mModel;

    public ForgetPresenter(IForgetContract.IForgetView view) {
        this.mView = view;
        mModel = new ForgetModel();
    }

    @Override
    public void forget( String loginName, String loginPwd, String reloginPwd, String yzm) {
        mModel.forget( loginName, loginPwd, reloginPwd, yzm, new IForgetContract.IForgetModel.IForgetCallback(mView) {
            @Override
            public void onForget(String msg) {
                mView.showForget(msg);
            }
        });
    }

    @Override
    public void getValidateCode(String tel) {
        mModel.getValidateCode(tel, new IForgetContract.IForgetModel.IForgetCallback(mView) {
            @Override
            public void onValidateCode(String msg) {
                mView.showGetValidate(msg);
            }
        });
    }
}
