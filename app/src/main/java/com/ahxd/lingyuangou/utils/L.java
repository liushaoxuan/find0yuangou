package com.ahxd.lingyuangou.utils;

import android.util.Log;

import com.ahxd.lingyuangou.MaoApplication;

/**
 * Log统一管理类
 */
public class L {

    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 下面四个是默认tag的函数  
    public static void i(String msg) {
        if (MaoApplication.DEBUG)
            Log.i(MaoApplication.TAG, msg);
    }

    public static void d(String msg) {
        if (MaoApplication.DEBUG)
            Log.d(MaoApplication.TAG, msg);
    }

    public static void e(String msg) {
        if (MaoApplication.DEBUG)
            Log.e(MaoApplication.TAG, msg);
    }

    public static void v(String msg) {
        if (MaoApplication.DEBUG)
            Log.v(MaoApplication.TAG, msg);
    }

    // 下面是传入自定义tag的函数  
    public static void i(String tag, String msg) {
        if (MaoApplication.DEBUG)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (MaoApplication.DEBUG)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (MaoApplication.DEBUG)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (MaoApplication.DEBUG)
            Log.i(tag, msg);
    }
}  