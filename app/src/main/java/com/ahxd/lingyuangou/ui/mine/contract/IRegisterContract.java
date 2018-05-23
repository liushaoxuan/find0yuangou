package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

/**
 * Created by Administrator on 2018/1/8.
 */

public interface IRegisterContract {

    public interface IRegisterView extends BaseView {
        public void showGetValidate(String msg);

        public void showRegister(String msg);
    }

    public interface IRegisterPresenter {
        // 注册
        public void register(String invite, String loginName, String loginPwd, String reloginPwd, String yzm);
        // 获取验证码
        public void getValidateCode(String tel);
    }

    public interface IRegisterModel {

        public void register(String invite, String loginName, String loginPwd, String reloginPwd,
                             String yzm, IRegisterCallback callback);

        public void getValidateCode(String tel, IRegisterCallback callback);

        public abstract class IRegisterCallback extends ModelCallback {

            public IRegisterCallback(BaseView view) {
                super(view);
            }

            public void onRegister(String msg) {}

            public void onValidateCode(String msg) {}
        }

    }

}
