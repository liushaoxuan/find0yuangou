package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.ui.mine.contract.IConsumptionRecordContract;
import com.ahxd.lingyuangou.ui.mine.contract.IRecordContract;
import com.ahxd.lingyuangou.ui.mine.model.ConsumptionRecordModel;
import com.ahxd.lingyuangou.ui.mine.model.RecordModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class ConsumptionRecordListPresenter implements IConsumptionRecordContract.IConsumptionRecordListPresenter {

    private IConsumptionRecordContract.IConsumptionRecordListView mView;
    private ConsumptionRecordModel mRecordModel;

    public ConsumptionRecordListPresenter(IConsumptionRecordContract.IConsumptionRecordListView view) {
        this.mView = view;
        this.mRecordModel = new ConsumptionRecordModel();
    }

    @Override
    public void getConsumptionRecordList(int mPage) {
        mRecordModel.getConsumptionRecordList(mPage,new IConsumptionRecordContract.IConsumptionRecordListModel.IConsumptionRecordListCallback(mView) {
            @Override
            public void onConsumptionRecordList(List<RecordBean> list) {
                mView.showConsumptionRecordList(list);
            }
        });
    }

}
