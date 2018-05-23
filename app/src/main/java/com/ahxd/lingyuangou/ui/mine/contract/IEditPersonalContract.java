package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

/**
 * Created by Mao on 2018/1/15.
 */

public interface IEditPersonalContract {

    public interface IEditPersonalView extends BaseView {
        public void showSubmitPersonal(String msg);
    }

    public interface IEditPersonalPresenter {
        public void submitPersonal(String userName, String userPhoto, String birthday,
                                   String userPhone, String userWeixin, int userSex);
    }

    public interface IEditPersonalModel {

        public void submitPersonal(String userName, String userPhoto, String birthday,
                                   String userPhone, String userWeixin, int userSex, IEditPersonalCallback callback);

        public abstract class IEditPersonalCallback extends ModelCallback{

            public IEditPersonalCallback(BaseView view) {
                super(view);
            }

            public void onSubmitPersonal(String msg) {}
        }

    }

}
