package com.ahxd.lingyuangou.ui.mine.model;

import com.ahxd.lingyuangou.bean.MyMemberBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.contract.IMyMemberContract;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class MyMemberModel implements IMyMemberContract.IMyMemberListModel {



    @Override
    public void getMyMemberList(int mPage, final IMyMemberListCallback callback) {
        Type type = new TypeToken<MaoResponse<List<MyMemberBean>>>(){}.getType();
        OkGo.<MaoResponse<List<MyMemberBean>>>post(HostUrl.URL_RECOMMEND_GETRECOMMENDLIST)
                .params("page",mPage)
                .execute(new MaoJsonCallback<MaoResponse<List<MyMemberBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<MyMemberBean>>> response) {
                        callback.onMyMemberList(response.body().data);
                    }
                });
    }
}
