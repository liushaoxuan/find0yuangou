package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.MarketingBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IMarketingContract;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class MarketingModel implements IMarketingContract.IMarketingListModel {

    @Override
    public void getMarketingList(int mPage,final IMarketingListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<MarketingBean>>>(){}.getType();
        OkGo.<MaoResponse<List<MarketingBean>>>post(HostUrl.URL_MARKETING_INCOMELOG)
                .params("page",mPage)
                .execute(new MaoJsonCallback<MaoResponse<List<MarketingBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<MarketingBean>>> response) {
                        callback.onMarketingList(response.body().data);
                    }
                });
    }

}
