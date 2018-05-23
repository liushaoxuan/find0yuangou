package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IMarketingCenterContract {

    public interface IMarketingCenterView extends BaseView {
        public void showMarketingCenter(JSONObject data);
    }

    public interface IMarketingCenterPresenter {

        public void getMarketingCenter();
    }

    public interface IMarketingCenterModel {

        public void getMarketingCenter(IMarketingCenterCallback callback);

        public abstract class IMarketingCenterCallback extends ModelCallback {

            public IMarketingCenterCallback(BaseView view) {
                super(view);
            }

            public void onMarketingCenter(JSONObject data) {}
        }

    }


}
