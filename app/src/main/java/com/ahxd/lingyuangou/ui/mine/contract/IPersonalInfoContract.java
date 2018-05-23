package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Mao on 2018/1/14.
 */

public interface IPersonalInfoContract {

    public interface IPersonalInfoView extends BaseView {
        public void showPersonalInfo(JSONObject data);
    }

    public interface IPersonalInfoPresenter {
        // 获取个人资料
        public void getPersonalInfo();
    }

    public interface IPersonalInfoModel {

        public void getPersonalInfo(IPersonalInfoCallback callback);

        public abstract class IPersonalInfoCallback extends ModelCallback {

            public IPersonalInfoCallback(BaseView view) {
                super(view);
            }

            public void onPersonalInfo(JSONObject data) {}
        }

    }

}
