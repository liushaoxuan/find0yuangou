package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IConsumptionRecordContract;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class ConsumptionRecordModel implements IConsumptionRecordContract.IConsumptionRecordListModel {

    @Override
    public void getConsumptionRecordList(int mPage,final IConsumptionRecordListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<RecordBean>>>(){}.getType();
        OkGo.<MaoResponse<List<RecordBean>>>post(HostUrl.URL_RECHANGE_BUY_LIST)
                .params("page",mPage)
                .execute(new MaoJsonCallback<MaoResponse<List<RecordBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<RecordBean>>> response) {
                        callback.onConsumptionRecordList(response.body().data);
                    }
                });
    }

}
