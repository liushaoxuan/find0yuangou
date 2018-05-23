package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.bean.HomeFinanceBean;
import com.ahxd.lingyuangou.bean.HomeFoodShopBean;
import com.ahxd.lingyuangou.bean.HomeGiftBean;
import com.ahxd.lingyuangou.bean.HomeGoodBean;
import com.ahxd.lingyuangou.bean.MarketingBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeGiftContract;
import com.ahxd.lingyuangou.ui.home.contract.IHomeContract;
import com.ahxd.lingyuangou.utils.LocationUtils;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public class ExchangeGiftModel implements IExchangeGiftContract.IExchangeGiftModel {

    @Override
    public void getExchangeGiftData(int page,final IExchangeGiftContract.IExchangeGiftModel.IExchangeGiftCallback callback) {

        Type type = new TypeToken<MaoResponse<ExchangeBean>>(){}.getType();
        OkGo.<MaoResponse<ExchangeBean>>post(HostUrl.URL_SCORE_INDEX)
                .params("areaId",LocationUtils.getInstance().getLocation())
                .params("page",page)
                .execute(new MaoJsonCallback<MaoResponse<ExchangeBean>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<ExchangeBean>> response) {
                        callback.onBannerData(response.body().data);
                        callback.onCatData(response.body().data);
                        callback.onHotData(response.body().data);
                    }
                });

    }


}
