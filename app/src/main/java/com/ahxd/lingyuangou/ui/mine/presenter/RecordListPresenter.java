package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.AddressBean;
import com.ahxd.lingyuangou.bean.IntegralBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.ui.mine.contract.IAddressContract;
import com.ahxd.lingyuangou.ui.mine.contract.IRecordContract;
import com.ahxd.lingyuangou.ui.mine.model.AddressModel;
import com.ahxd.lingyuangou.ui.mine.model.RecordModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class RecordListPresenter implements IRecordContract.IRecordListPresenter {

    private IRecordContract.IRecordListView mView;
    private RecordModel mRecordModel;

    public RecordListPresenter(IRecordContract.IRecordListView view) {
        this.mView = view;
        this.mRecordModel = new RecordModel();
    }

    @Override
    public void getRecordList(int mPage) {
        mRecordModel.getRecordList(mPage,new IRecordContract.IRecordListModel.IRecordListCallback(mView) {
            @Override
            public void onRecordList(List<RecordBean> list) {
                mView.showRecordList(list);
            }
        });
    }

    @Override
    public void getIntegralList(int mPage) {
        mRecordModel.getIntegralList(mPage,new IRecordContract.IRecordListModel.IRecordListCallback(mView) {
            @Override
            public void onIntegralList(List<IntegralBean> list) {
                mView.showIntegralList(list);
            }
        });
    }

}
