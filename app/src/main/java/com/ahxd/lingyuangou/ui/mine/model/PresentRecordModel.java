package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.CashdrewsBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IPresentRecordContract;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class PresentRecordModel implements IPresentRecordContract.IPresentRecordListModel {

    @Override
    public void getPresentRecordList(int mPage,final IPresentRecordListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<CashdrewsBean>>>(){}.getType();
        OkGo.<MaoResponse<List<CashdrewsBean>>>post(HostUrl.URL_CASHDRAWS_LIST)
                .params("page",mPage)
                .execute(new MaoJsonCallback<MaoResponse<List<CashdrewsBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<CashdrewsBean>>> response) {
                        callback.onPresentRecordList(response.body().data);
                    }
                });
    }

}
