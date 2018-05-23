package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/5.
 */

public interface IMineContract {

    public interface IMineView extends BaseView {

        public void showProfile(JSONObject data);

        public void showCustomService(JSONObject data);
    }

    public interface IMinePresenter {
        public void getMyProfile();
        public void getCustomService();
    }

    public interface IMineModel {

        public void getMyProfile(IMineCallback callback);

        public void getCustomService(IMineCallback callback);

        public abstract class IMineCallback extends ModelCallback {

            public IMineCallback(BaseView view) {
                super(view);
            }

            public void onProfileData(JSONObject data) {}

            public void onCustomService(JSONObject data) {}
        }

    }

}
