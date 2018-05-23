package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.CashdrewsBean;
import com.ahxd.lingyuangou.bean.ScoreBean;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeRecordListContract;
import com.ahxd.lingyuangou.ui.home.model.ExchangeRecordListModel;
import com.ahxd.lingyuangou.ui.mine.contract.IPresentRecordContract;
import com.ahxd.lingyuangou.ui.mine.model.PresentRecordModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class ExchangeRecordListPresenter implements IExchangeRecordListContract.IExchangeRecordListPresenter {

    private IExchangeRecordListContract.IExchangeRecordListView mView;
    private ExchangeRecordListModel mRecordModel;

    public ExchangeRecordListPresenter(IExchangeRecordListContract.IExchangeRecordListView view) {
        this.mView = view;
        this.mRecordModel = new ExchangeRecordListModel();
    }

    @Override
    public void getExchangeRecordList(int mPage) {
        mRecordModel.getExchangeRecordList(mPage,new IExchangeRecordListContract.IExchangeRecordListModel.IExchangeRecordListCallback(mView) {
            @Override
            public void onExchangeRecordList(List<ScoreBean> list) {
                mView.showExchangeRecordList(list);
            }
        });
    }

}
