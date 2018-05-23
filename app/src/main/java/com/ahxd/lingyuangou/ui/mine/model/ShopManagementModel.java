package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.MarketingBean;
import com.ahxd.lingyuangou.bean.MyMemberBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IMyMemberContract;
import com.ahxd.lingyuangou.ui.mine.contract.IShopManagementContract;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class ShopManagementModel implements IShopManagementContract.IShopManagementListModel {



    @Override
    public void getShopManagementList(int mPage, final IShopManagementListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<MarketingBean>>>(){}.getType();
        OkGo.<MaoResponse<List<MarketingBean>>>post(HostUrl.URL_MARKETING_SHOPLIST)
                .params("page",mPage)
                .execute(new MaoJsonCallback<MaoResponse<List<MarketingBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<MarketingBean>>> response) {
                        callback.onShopManagementList(response.body().data);
                    }
                });
    }
}
