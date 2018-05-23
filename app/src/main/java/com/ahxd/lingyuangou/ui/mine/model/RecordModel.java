package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.IntegralBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IRecordContract;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class RecordModel implements IRecordContract.IRecordListModel {

    @Override
    public void getRecordList(int mPage,final IRecordListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<RecordBean>>>(){}.getType();
        OkGo.<MaoResponse<List<RecordBean>>>post(HostUrl.URL_RECHANGE_LIST)
                .params("page",mPage)
                .execute(new MaoJsonCallback<MaoResponse<List<RecordBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<RecordBean>>> response) {
                        callback.onRecordList(response.body().data);
                    }
                });
    }

    @Override
    public void getIntegralList(int mPage, final IRecordListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<IntegralBean>>>(){}.getType();
        OkGo.<MaoResponse<List<IntegralBean>>>post(HostUrl.URL_INTEGRAL_LIST)
                .params("page",mPage)
                .execute(new MaoJsonCallback<MaoResponse<List<IntegralBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<IntegralBean>>> response) {
                        callback.onIntegralList(response.body().data);
                    }
                });
    }

}
