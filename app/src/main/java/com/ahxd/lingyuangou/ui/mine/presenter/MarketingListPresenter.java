package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.MarketingBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.ui.mine.contract.IMarketingContract;
import com.ahxd.lingyuangou.ui.mine.contract.IRecordContract;
import com.ahxd.lingyuangou.ui.mine.model.MarketingModel;
import com.ahxd.lingyuangou.ui.mine.model.RecordModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class MarketingListPresenter implements IMarketingContract.IMarketingListPresenter {

    private IMarketingContract.IMarketingListView mView;
    private MarketingModel mRecordModel;

    public MarketingListPresenter(IMarketingContract.IMarketingListView view) {
        this.mView = view;
        this.mRecordModel = new MarketingModel();
    }

    @Override
    public void getMarketingList(int mPage) {
        mRecordModel.getMarketingList(mPage,new IMarketingContract.IMarketingListModel.IMarketingListCallback(mView) {
            @Override
            public void onMarketingList(List<MarketingBean> list) {
                mView.showMarketingList(list);
            }
        });
    }

}
