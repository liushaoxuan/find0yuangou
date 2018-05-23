package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.IntegralBean;
import com.ahxd.lingyuangou.bean.RecordBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public interface IRecordContract {

    public interface IRecordListView extends BaseView {
        public void showRecordList(List<RecordBean> list);
        public void showIntegralList(List<IntegralBean> list);

    }

    public interface IRecordListPresenter {
        public void getRecordList(int mPage);
        public void getIntegralList(int mPage);
    }

    public interface IRecordListModel {

        public void getRecordList(int mPage,IRecordListCallback callback);
        public void getIntegralList(int mPage,IRecordListCallback callback);

        public abstract class IRecordListCallback extends ModelCallback {

            public IRecordListCallback(BaseView view) {
                super(view);
            }

            public void onRecordList(List<RecordBean> list) {}
            public void onIntegralList(List<IntegralBean> list) {}

        }
    }

}
