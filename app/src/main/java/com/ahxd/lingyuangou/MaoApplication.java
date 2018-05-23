package com.ahxd.lingyuangou;

import android.app.Application;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.utils.SHA1Utils;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpParams;
import com.mob.MobSDK;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.logging.Level;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import okhttp3.OkHttpClient;

import static java.security.AccessController.getContext;

/**
 * Created by Administrator on 2017/12/23.
 */

public class MaoApplication extends Application {

    /**
     * 是否是debug状态
     */
    public static final boolean DEBUG = true;

    public static  boolean ISDOWN = true;
    public static final String FILE= Environment.getExternalStorageDirectory().getPath() + "/lyg";
    public static Handler handler = new Handler();

    /**
     * 默认TAG
     */
    public static final String TAG = "LingYuanGou";

    public static MaoApplication mInstance;

    private IWXAPI mWeixinApi;

    public static MaoApplication getInstance() {
        return mInstance;
    }

    public IWXAPI getWXApi() {
        return mWeixinApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        // 初始化OkGo
        initOkGo();

        // 初始化微信支付
        initWeixinPay();

//        //分享初始化
        MobSDK.init(this);

    }

    /**
     * 初始化OkGo
     */
    private void initOkGo() {
        //必须调用初始化
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        // Cookie
//        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
        builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));


        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        OkGo.getInstance().init(this)
//                .addCommonParams(params)
                .setOkHttpClient(builder.build());
    }


    private void initWeixinPay() {
        mWeixinApi = WXAPIFactory.createWXAPI(this, Constant.WEIXIN_APPID, true);
        mWeixinApi.registerApp(Constant.WEIXIN_APPID);
    }

}
