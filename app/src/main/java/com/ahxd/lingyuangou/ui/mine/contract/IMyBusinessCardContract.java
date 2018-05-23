package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IMyBusinessCardContract {

    public interface IMyBusinessCardView extends BaseView {
        public void showMyBusinessCard(JSONObject data);
    }

    public interface IMyBusinessCardPresenter {

        public void getMyBusinessCard();
    }

    public interface IMyBusinessCardModel {

        public void getMyBusinessCard(IMyBusinessCardCallback callback);

        public abstract class IMyBusinessCardCallback extends ModelCallback {

            public IMyBusinessCardCallback(BaseView view) {
                super(view);
            }

            public void onMyBusinessCard(JSONObject data) {}
        }

    }


}
