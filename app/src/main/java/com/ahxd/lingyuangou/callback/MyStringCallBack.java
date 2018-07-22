package com.ahxd.lingyuangou.callback;

import android.content.Context;

import com.ahxd.lingyuangou.widget.LoadingView;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * Created by sxliu on 2018/7/22 21:44
 * E-mail Address 2587294424@qq.com
 */

public abstract class MyStringCallBack extends AbsCallback<String> {

    private StringConvert convert;
    private Context mContext;

    public MyStringCallBack(Context mContext) {
        this.mContext = mContext;
        convert = new StringConvert();
    }

    @Override
    public String convertResponse(okhttp3.Response response) throws Throwable {
        String s = convert.convertResponse(response);
        response.close();
        return s;
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
        LoadingView.startLoading(mContext);
    }

    @Override
    public void onError(Response<String> response) {
        super.onError(response);
        LoadingView.startLoading(mContext);
    }

    @Override
    public void onFinish() {
        super.onFinish();
        LoadingView.stopLoading();
    }
}
