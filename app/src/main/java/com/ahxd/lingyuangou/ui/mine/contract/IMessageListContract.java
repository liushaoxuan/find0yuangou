package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.MessageBean;
import com.ahxd.lingyuangou.bean.ScoreBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public interface IMessageListContract {

    public interface IMessageListView extends BaseView {
        public void showMessageList(List<MessageBean> list);
        public void showSetMessageList(JSONObject data);

    }

    public interface IMessageListPresenter {
        public void getMessageList(int mPage);
        public void setMessageList();
    }

    public interface IMessageListModel {

        public void getMessageList(int mPage, IMessageListCallback callback);
        public void setMessageList(IMessageListCallback callback);

        public abstract class IMessageListCallback extends ModelCallback {

            public IMessageListCallback(BaseView view) {
                super(view);
            }

            public void onMessageList(List<MessageBean> list) {}
            public void onSetMessageList(JSONObject data) {}

        }
    }

}
