package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.CashdrewsBean;
import com.ahxd.lingyuangou.bean.ScoreBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public interface IExchangeRecordListContract {

    public interface IExchangeRecordListView extends BaseView {
        public void showExchangeRecordList(List<ScoreBean> list);

    }

    public interface IExchangeRecordListPresenter {
        public void getExchangeRecordList(int mPage);
    }

    public interface IExchangeRecordListModel {

        public void getExchangeRecordList(int mPage, IExchangeRecordListCallback callback);

        public abstract class IExchangeRecordListCallback extends ModelCallback {

            public IExchangeRecordListCallback(BaseView view) {
                super(view);
            }

            public void onExchangeRecordList(List<ScoreBean> list) {}

        }
    }

}
