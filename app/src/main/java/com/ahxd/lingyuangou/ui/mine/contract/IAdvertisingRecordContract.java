package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.RecommendBean;
import com.ahxd.lingyuangou.bean.RecordBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public interface IAdvertisingRecordContract {

    public interface IAdvertisingRecordListView extends BaseView {
        public void showAdvertisingRecordList(List<RecommendBean> list);
    }

    public interface IAdvertisingRecordListPresenter {
        public void getAdvertisingRecordList(int mPage);
    }

    public interface IAdvertisingRecordListModel {

        public void getAdvertisingRecordList(int mPage,IAdvertisingRecordListCallback callback);

        public abstract class IAdvertisingRecordListCallback extends ModelCallback {

            public IAdvertisingRecordListCallback(BaseView view) {
                super(view);
            }

            public void onAdvertisingRecordList(List<RecommendBean> list) {}

        }
    }

}
