package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IExtensionCenterContract {

    public interface IExtensionCenterView extends BaseView {
        public void showExtensionCenter(JSONObject data);

        public void showShare(JSONObject data);


    }

    public interface IExtensionCenterPresenter {
        public void getExtensionCenter();
        public void getShare();
    }

    public interface IExtensionCenterModel {

        public void getExtensionCenter(IExtensionCenterCallback callback);

        public void getShare(IExtensionCenterCallback callback);

        public abstract class IExtensionCenterCallback extends ModelCallback {

            public IExtensionCenterCallback(BaseView view) {
                super(view);
            }

            public void onExtensionCenter(JSONObject data) {}
            public void onShare(JSONObject data) {}
        }

    }


}
