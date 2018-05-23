package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IMainContract {

    public interface IMainView extends BaseView {
        public void showApk(JSONObject data);
    }

    public interface IMainPresenter {
        public void getApk();
    }

    public interface IMainModel {

        public void getApk(IMainContract.IMainModel.IMainCallback  callback);

        public abstract class IMainCallback extends ModelCallback {

            public IMainCallback(BaseView view) {
                super(view);
            }

            public void onApk(JSONObject data) {}
        }

    }


}
