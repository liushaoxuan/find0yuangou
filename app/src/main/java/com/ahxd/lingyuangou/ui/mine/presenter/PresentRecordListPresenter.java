package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.CashdrewsBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.ui.mine.contract.IPresentRecordContract;
import com.ahxd.lingyuangou.ui.mine.contract.IRecordContract;
import com.ahxd.lingyuangou.ui.mine.model.PresentRecordModel;
import com.ahxd.lingyuangou.ui.mine.model.RecordModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class PresentRecordListPresenter implements IPresentRecordContract.IPresentRecordListPresenter {

    private IPresentRecordContract.IPresentRecordListView mView;
    private PresentRecordModel mRecordModel;

    public PresentRecordListPresenter(IPresentRecordContract.IPresentRecordListView view) {
        this.mView = view;
        this.mRecordModel = new PresentRecordModel();
    }

    @Override
    public void getPresentRecordList(int mPage) {
        mRecordModel.getPresentRecordList(mPage,new IPresentRecordContract.IPresentRecordListModel.IPresentRecordListCallback(mView) {
            @Override
            public void onPresentRecordList(List<CashdrewsBean> list) {
                mView.showPresentRecordList(list);
            }
        });
    }

}
