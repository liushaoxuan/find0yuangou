package com.ahxd.lingyuangou.utils;

import android.content.Context;
import android.util.Log;

import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by sxliu on 2018/7/22 11:37
 * E-mail Address 2587294424@qq.com
 * 分享
 */

public class ShareUtil {
    private static String TAG = ShareUtil.class.getSimpleName();

    public static void Share(final Context context) {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(context, Constant.KEY_TOKEN, ""));
        OkGo.<String>post(HostUrl.URL_USERS_CARD)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            JSONObject data = obj.optJSONObject(Constant.RESP_DATA);
                            if (data != null) {
                                OnekeyShare oks = new OnekeyShare();
                                //关闭sso授权
                                oks.disableSSOWhenAuthorize();
                                // title标题，微信、QQ和QQ空间等平台使用
                                oks.setTitle(data.optString("codeTitle"));
                                // titleUrl QQ和QQ空间跳转链接
                                oks.setTitleUrl(data.optString("codeUrl"));
                                // text是分享文本，所有平台都需要这个字段
                                oks.setText(data.optString("codeDesc"));
                                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                                oks.setImagePath("/assets/ic_app_start.png");//确保SDcard下面存在此张图片
                                oks.setImageUrl(data.optString("userPhoto"));
                                // url在微信、微博，Facebook等平台中使用
                                oks.setUrl(data.optString("codeUrl"));
                                //        //没有下面这两个微信分享的就是文本
                                //        oks.setSite(data.optString("codeTitle"));
                                //        oks.setSiteUrl(data.optString("codeUrl"));
                                // comment是我对这条分享的评论，仅在人人网使用
                                //        oks.setComment(data.optString("codeDesc"));
                                // 启动分享GUI
                                oks.show(context);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, e.getMessage());
                        }
                    }
                });
    }
}
