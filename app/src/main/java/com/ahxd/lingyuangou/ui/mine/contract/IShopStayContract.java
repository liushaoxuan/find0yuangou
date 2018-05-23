package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

/**
 * Created by Administrator on 2018/1/9.
 */

public interface IShopStayContract {

    public interface IShopStayView extends BaseView {
        public void showGetCode(String msg);

        public void showSubmit(String msg);
    }

    public interface IShopStayPresenter {
        public void getCode(String tel);

        public void submitApply(String markerCode, String linkman, String tel,
                                String remark, String code);
    }

    public interface IShopStayModel {

        public void getCode(String tel, IShopStayCallback callback);

        public void submitApply(String markerCode, String linkman, String tel,
                                String remark, String code, IShopStayCallback callback);

        public abstract class IShopStayCallback extends ModelCallback {

            public IShopStayCallback(BaseView view) {
                super(view);
            }

            public void onShopCode(String msg) {}

            public void onSubmit(String msg) {}
        }

    }

}
