package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.RecommendBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IAdvertisingRecordContract;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class AdvertisingRecordModel implements IAdvertisingRecordContract.IAdvertisingRecordListModel {

    @Override
    public void getAdvertisingRecordList(int mPage,final IAdvertisingRecordListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<RecommendBean>>>(){}.getType();
        OkGo.<MaoResponse<List<RecommendBean>>>post(HostUrl.URL_RECOMMEND_RECOMMENDLOG)
                .params("page",mPage)
                .execute(new MaoJsonCallback<MaoResponse<List<RecommendBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<RecommendBean>>> response) {
                        callback.onAdvertisingRecordList(response.body().data);
                    }
                });
    }

}
