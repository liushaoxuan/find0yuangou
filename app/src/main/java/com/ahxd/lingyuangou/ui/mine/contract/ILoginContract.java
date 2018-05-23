package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

/**
 * Created by Administrator on 2018/1/8.
 */

public interface ILoginContract {

    public interface ILoginView extends BaseView {
        public void showLogin(String msg);
    }

    public interface ILoginPresenter {
        public void login(String loginName, String loginPwd);
    }

    public interface ILoginModel {

        public void login(String loginName, String loginPwd, ILoginCallback callback);

        public abstract class ILoginCallback extends ModelCallback {

            public ILoginCallback(BaseView view) {
                super(view);
            }

            public void onLogin(String msg) {}
        }

    }

}
