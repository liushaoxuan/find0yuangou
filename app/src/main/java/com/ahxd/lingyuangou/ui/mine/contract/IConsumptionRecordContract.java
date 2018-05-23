package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.RecordBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public interface IConsumptionRecordContract {

    public interface IConsumptionRecordListView extends BaseView {
        public void showConsumptionRecordList(List<RecordBean> list);

    }

    public interface IConsumptionRecordListPresenter {
        public void getConsumptionRecordList(int mPage);
    }

    public interface IConsumptionRecordListModel {

        public void getConsumptionRecordList(int mPage,IConsumptionRecordListCallback callback);

        public abstract class IConsumptionRecordListCallback extends ModelCallback {

            public IConsumptionRecordListCallback(BaseView view) {
                super(view);
            }

            public void onConsumptionRecordList(List<RecordBean> list) {}

        }
    }

}
