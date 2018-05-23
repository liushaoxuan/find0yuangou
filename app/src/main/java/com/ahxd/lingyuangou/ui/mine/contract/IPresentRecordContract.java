package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.CashdrewsBean;
import com.ahxd.lingyuangou.bean.RecordBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public interface IPresentRecordContract {

    public interface IPresentRecordListView extends BaseView {
        public void showPresentRecordList(List<CashdrewsBean> list);

    }

    public interface IPresentRecordListPresenter {
        public void getPresentRecordList(int mPage);
    }

    public interface IPresentRecordListModel {

        public void getPresentRecordList(int mPage,IPresentRecordListCallback callback);

        public abstract class IPresentRecordListCallback extends ModelCallback {

            public IPresentRecordListCallback(BaseView view) {
                super(view);
            }

            public void onPresentRecordList(List<CashdrewsBean> list) {}

        }
    }

}
