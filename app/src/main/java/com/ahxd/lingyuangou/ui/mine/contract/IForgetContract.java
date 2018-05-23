package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

/**
 * Created by Mao on 2018/1/17.
 */

public interface IForgetContract {

    public interface IForgetView extends BaseView {
        public void showGetValidate(String msg);

        public void showForget(String msg);

    }

    public interface IForgetPresenter {
        // 提交
        public void forget( String loginName, String loginPwd, String reloginPwd, String yzm);
        // 获取验证码
        public void getValidateCode(String tel);
    }

    public interface IForgetModel {
        public void forget(String loginName, String loginPwd, String reloginPwd,
                             String yzm, IForgetCallback callback);

        public void getValidateCode(String tel, IForgetCallback callback);

        public abstract class IForgetCallback extends ModelCallback {

            public IForgetCallback(BaseView view) {
                super(view);
            }
            public void onForget(String msg) {}

            public void onValidateCode(String msg) {}
        }
    }

}
