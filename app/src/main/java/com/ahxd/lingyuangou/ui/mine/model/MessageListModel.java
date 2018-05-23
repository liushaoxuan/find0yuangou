package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.MessageBean;
import com.ahxd.lingyuangou.bean.ScoreBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeRecordListContract;
import com.ahxd.lingyuangou.ui.mine.contract.IMessageListContract;
import com.ahxd.lingyuangou.utils.L;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class MessageListModel implements IMessageListContract.IMessageListModel {


    @Override
    public void getMessageList(int mPage,final IMessageListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<MessageBean>>>(){}.getType();
        OkGo.<MaoResponse<List<MessageBean>>>post(HostUrl.URL_MESSAGES)
                .params("page",mPage)
                .execute(new MaoJsonCallback<MaoResponse<List<MessageBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<MessageBean>>> response) {
                        callback.onMessageList(response.body().data);
                    }
                });
    }



    @Override
    public void setMessageList(final IMessageListCallback callback) {

        OkGo.<String>post(HostUrl.URL_PUT_MESSAGES_STATUS)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onSetMessageList(root.optJSONObject(Constant.RESP_DATA));
                    }
                });



    }
}
