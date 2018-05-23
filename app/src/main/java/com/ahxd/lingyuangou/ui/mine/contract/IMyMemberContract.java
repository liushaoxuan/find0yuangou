package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.MyMemberBean;
import com.ahxd.lingyuangou.bean.RecordBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public interface IMyMemberContract {

    public interface IMyMemberListView extends BaseView {
        public void showMyMemberList(List<MyMemberBean> list);

    }

    public interface IMyMemberListPresenter {
        public void getMyMemberList(int mPage);
    }

    public interface IMyMemberListModel {

        public void getMyMemberList(int mPage, IMyMemberListCallback callback);

        public abstract class IMyMemberListCallback extends ModelCallback {

            public IMyMemberListCallback(BaseView view) {
                super(view);
            }

            public void onMyMemberList(List<MyMemberBean> list) {}

        }
    }

}
