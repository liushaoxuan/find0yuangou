package com.ahxd.lingyuangou.callback;

import com.ahxd.lingyuangou.base.RequestCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.callback.SimpleResponse;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.exception.MaoException;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.UserUtils;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Mao on 2017/10/27.
 */

public abstract class MaoJsonCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;
    private RequestCallback callBack;

    public MaoJsonCallback(RequestCallback callBack) {
        this.callBack = callBack;
    }

    public MaoJsonCallback(Type type, RequestCallback callBack) {
        this.callBack = callBack;
        this.type = type;
    }

    public MaoJsonCallback(Class<T> clazz, RequestCallback callBack) {
        this.clazz = clazz;
        this.callBack = callBack;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
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
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        L.e("Request -> onError");
        L.e("Request -> onError");
        L.e("Request -> onError");
        String msg;
        Throwable exception = response.getException();
        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            msg = "网络连接失败，请连接网络！";
            callBack.onErrorRequest(msg);
        } else if (exception instanceof SocketTimeoutException) {
            msg = "网络请求超时，请重新请求！";
            callBack.onErrorRequest(msg);
        } else if (exception instanceof MaoException) {
            if (((MaoException) exception).getCode() == Constant.RESP_FAILURE) {
                callBack.onFailure(exception.getMessage());
            } else if (((MaoException) exception).getCode() == Constant.RESP_RELOGIN) {
                callBack.onReLogin();
            } else {
                msg = exception.getMessage();
                callBack.onErrorRequest(msg);
            }
        } else {
            msg = "服务器未知错误！";
            callBack.onErrorRequest(msg);
        }
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
//        ResponseBody body = response.body();
//        if (body == null) return null;
//
//        T data = null;
//
//        Gson gson = new Gson();
//        JsonReader reader = new JsonReader(body.charStream());
//        if (type != null) {
//            data = gson.fromJson(reader, type);
//        } else if (clazz != null) {
//            data = gson.fromJson(reader, clazz);
//        } else {
//            Type genType = getClass().getGenericSuperclass();
//            Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];
//            data = gson.fromJson(reader, type);
//        }
//        return data;

        Type rawType = ((ParameterizedType) type).getRawType();
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];

        ResponseBody body = response.body();
        if (null == body) return null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());

        if (rawType != MaoResponse.class) {
            T data = gson.fromJson(jsonReader, type);
            response.close();
            return data;
        } else {
            if (typeArgument == Void.class) {
                SimpleResponse simpleResponse = gson.fromJson(jsonReader, SimpleResponse.class);
                response.close();
                return (T) simpleResponse.toMaoResponse();
            } else {
                MaoResponse maoResponse = gson.fromJson(jsonReader, type);
                response.close();
                int status = maoResponse.status;
                if (status == Constant.RESP_SUCCESS) {
                    return (T) maoResponse;
                } else if (status == Constant.RESP_FAILURE) {
                    throw new MaoException(Constant.RESP_FAILURE, maoResponse.msg);
                } else if (status == Constant.RESP_RELOGIN) {
                    throw new MaoException(Constant.RESP_RELOGIN, maoResponse.msg);
                } else {
                    throw new MaoException("服务器未知错误！");
                }
            }
        }
    }

}
