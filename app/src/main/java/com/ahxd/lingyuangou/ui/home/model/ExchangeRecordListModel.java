package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.bean.CashdrewsBean;
import com.ahxd.lingyuangou.bean.ScoreBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeRecordListContract;
import com.ahxd.lingyuangou.ui.mine.contract.IPresentRecordContract;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class ExchangeRecordListModel implements IExchangeRecordListContract.IExchangeRecordListModel {

    @Override
    public void getExchangeRecordList(int mPage,final IExchangeRecordListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<ScoreBean>>>(){}.getType();
        OkGo.<MaoResponse<List<ScoreBean>>>post(HostUrl.URL_SCOREGOODS_PAYLIST)
                .params("page",mPage)
                .execute(new MaoJsonCallback<MaoResponse<List<ScoreBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<ScoreBean>>> response) {
                        callback.onExchangeRecordList(response.body().data);
                    }
                });
    }

}
