package com.ahxd.lingyuangou.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.ahxd.lingyuangou.utils.L;

/**
 * Created by Administrator on 2017/11/23.
 */

public class LoadingView {

    private static ProgressDialog mDialog;

    public static void startLoading(Context context) {
        L.e("LoadingView - startLoading");
        if (mDialog == null) {
            mDialog = new ProgressDialog(context);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDialog.setMessage("请求网络中...");
        }
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public static void stopLoading() {
        L.e("LoadingView - stopLoading");
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

}
