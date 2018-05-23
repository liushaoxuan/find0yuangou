package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IMineContactContract {

    public interface IMineContactView extends BaseView {
        public void showMineContact(JSONObject data);
    }

    public interface IMineContactPresenter {

        public void getMineContact();
    }

    public interface IMineContactModel {

        public void getMineContact(IMineContactCallback callback);

        public abstract class IMineContactCallback extends ModelCallback {

            public IMineContactCallback(BaseView view) {
                super(view);
            }

            public void onMineContact(JSONObject data) {}

        }

    }


}
