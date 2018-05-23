package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.RecommendBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.ui.mine.contract.IAdvertisingRecordContract;
import com.ahxd.lingyuangou.ui.mine.contract.IRecordContract;
import com.ahxd.lingyuangou.ui.mine.model.AdvertisingRecordModel;
import com.ahxd.lingyuangou.ui.mine.model.RecordModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class AdvertisingRecordListPresenter implements IAdvertisingRecordContract.IAdvertisingRecordListPresenter {

    private IAdvertisingRecordContract.IAdvertisingRecordListView mView;
    private AdvertisingRecordModel mRecordModel;

    public AdvertisingRecordListPresenter(IAdvertisingRecordContract.IAdvertisingRecordListView view) {
        this.mView = view;
        this.mRecordModel = new AdvertisingRecordModel();
    }

    @Override
    public void getAdvertisingRecordList(int mPage) {
        mRecordModel.getAdvertisingRecordList(mPage,new IAdvertisingRecordContract.IAdvertisingRecordListModel.IAdvertisingRecordListCallback(mView) {
            @Override
            public void onAdvertisingRecordList(List<RecommendBean> list) {
                mView.showAdvertisingRecordList(list);
            }
        });
    }

}
