package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.MyMemberBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.ui.mine.contract.IConsumptionRecordContract;
import com.ahxd.lingyuangou.ui.mine.contract.IMyMemberContract;
import com.ahxd.lingyuangou.ui.mine.model.ConsumptionRecordModel;
import com.ahxd.lingyuangou.ui.mine.model.MyMemberModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class MyMemberPresenter implements IMyMemberContract.IMyMemberListPresenter {

    private IMyMemberContract.IMyMemberListView mView;
    private MyMemberModel mModel;

    public MyMemberPresenter(IMyMemberContract.IMyMemberListView view) {
        this.mView = view;
        this.mModel = new MyMemberModel();
    }

    @Override
    public void getMyMemberList(int mPage) {
        mModel.getMyMemberList(mPage,new IMyMemberContract.IMyMemberListModel.IMyMemberListCallback(mView) {
            @Override
            public void onMyMemberList(List<MyMemberBean> list) {
                mView.showMyMemberList(list);
            }
        });
    }

}
