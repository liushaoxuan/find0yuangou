package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.MessageBean;
import com.ahxd.lingyuangou.bean.ScoreBean;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeRecordListContract;
import com.ahxd.lingyuangou.ui.home.model.ExchangeRecordListModel;
import com.ahxd.lingyuangou.ui.mine.contract.IMessageListContract;
import com.ahxd.lingyuangou.ui.mine.model.MessageListModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class MessageListPresenter implements IMessageListContract.IMessageListPresenter {

    private IMessageListContract.IMessageListView mView;
    private MessageListModel mRecordModel;

    public MessageListPresenter(IMessageListContract.IMessageListView view) {
        this.mView = view;
        this.mRecordModel = new MessageListModel();
    }

    @Override
    public void getMessageList(int mPage) {
        mRecordModel.getMessageList(mPage,new IMessageListContract.IMessageListModel.IMessageListCallback(mView) {
            @Override
            public void onMessageList(List<MessageBean> list) {
                mView.showMessageList(list);
            }
        });
    }

    @Override
    public void setMessageList() {

        mRecordModel.setMessageList(new IMessageListContract.IMessageListModel.IMessageListCallback(mView) {
            @Override
            public void onMessageList(List<MessageBean> list) {
                mView.showMessageList(list);
            }
        });
    }

}
