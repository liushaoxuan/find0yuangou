package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IMyMarketingCodeContract {

    public interface IMyMarketingCodeView extends BaseView {
        public void showMyMarketingCode(JSONObject data);
    }

    public interface IMyMarketingCodePresenter {

        public void getMyMarketingCode();
    }

    public interface IMyMarketingCodeModel {

        public void getMyMarketingCode(IMyMarketingCodeCallback callback);

        public abstract class IMyMarketingCodeCallback extends ModelCallback {

            public IMyMarketingCodeCallback(BaseView view) {
                super(view);
            }

            public void onMyMarketingCode(JSONObject data) {}
        }

    }


}
