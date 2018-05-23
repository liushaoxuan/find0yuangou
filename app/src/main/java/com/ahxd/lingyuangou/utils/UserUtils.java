package com.ahxd.lingyuangou.utils;

import android.text.TextUtils;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.constant.Constant;

/**
 * Created by Mao on 2018/1/13.
 */

public class UserUtils {

    /**
     * 获取token
     * @return
     */
    public static String getToken() {
        if (TextUtils.isEmpty((String) SPUtils.get(MaoApplication.getInstance(), Constant.KEY_TOKEN, ""))) {
            return null;
        } else {
            return (String) SPUtils.get(MaoApplication.getInstance(), Constant.KEY_TOKEN, "");
        }
    }

    /**
     * 是否登录
     * @return
     */
    public static boolean isLogin() {
        if (TextUtils.isEmpty((String) SPUtils.get(MaoApplication.getInstance(), Constant.KEY_TOKEN, ""))) {
            return false;
        } else {
            return true;
        }
    }

}
