package com.ahxd.lingyuangou.callback;

import com.ahxd.lingyuangou.base.RequestCallback;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.UserUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2017/11/24.
 */

public abstract class MaoStringCallback extends StringCallback {

    private RequestCallback callBack;

    public MaoStringCallback(RequestCallback callBack) {
        super();
        this.callBack = callBack;
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
        L.e("Request -> onStart");
        L.e("Request -> onStart");
        L.e("Request -> onStart");
        request.params(Constant.KEY_TOKEN, UserUtils.getToken());
        callBack.onStartRequest();
    }

    @Override
    public void onFinish() {
        super.onFinish();
        L.e("Request -> onFinish");
        L.e("Request -> onFinish");
        L.e("Request -> onFinish");
        callBack.onEndRequest();
    }

    @Override
    public void onError(Response<String> response) {
        super.onError(response);
        L.e("Request -> onError");
        L.e("Request -> onError");
        L.e("Request -> onError");
        String msg;
        Throwable exception = response.getException();
        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            msg = "网络连接失败，请连接网络！";
        } else if (exception instanceof SocketTimeoutException) {
            msg = "网络请求超时，请重新请求！";
        } else {
            msg = "服务器未知错误！";
        }
        callBack.onErrorRequest(msg);
    }

    @Override
    public void onSuccess(Response<String> response) {
        try {
            JSONObject root = new JSONObject(response.body());
            if (root.optInt(Constant.RESP_CODE) == Constant.RESP_SUCCESS) {
                onSuccess(root);
//                JSONObject data = root.optJSONObject(Constant.RESP_DATA);
//                onSuccess(data, root.optString(Constant.RESP_MSG));
            } else if (root.optInt(Constant.RESP_CODE) == Constant.RESP_RELOGIN) {
                callBack.onReLogin();
            } else {
                callBack.onFailure(root.optString(Constant.RESP_MSG));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected abstract void onSuccess(JSONObject root);
}
