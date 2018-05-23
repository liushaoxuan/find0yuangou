package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IMarketingApplyContract {

    public interface IMarketingApplyView extends BaseView {
        public void showMarketingData(JSONObject data);
        public void showMarketingApply(JSONObject data);
    }

    public interface IMarketingApplyPresenter {

        public void getMarketingApply();
        public void setMarketingApply();
    }

    public interface IMarketingApplyModel {

        public void getMarketingApply(IMarketingApplyCallback callback);

        public void setMarketingApply(IMarketingApplyCallback callback);

        public abstract class IMarketingApplyCallback extends ModelCallback {

            public IMarketingApplyCallback(BaseView view) {
                super(view);
            }

            public void onMarketingData(JSONObject data) {}

            public void onMarketingApply(JSONObject data) {}
        }

    }


}
