package com.ahxd.lingyuangou.utils;

import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.listener.OnFileUploadListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.io.File;

/**
 * Created by Administrator on 2018/1/17.
 */

public class UploadUtils {

    public static void uploadFile(String dir, String filePath, final OnFileUploadListener listener) {
        listener.onStart();
        OkGo.<String>post(HostUrl.URL_FILE_USER_HEADER)
                .params("dir", dir)
                .params("file", new File(filePath))
                .params("token", UserUtils.getToken())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject root = new JSONObject(response.body());
                            if (root.optInt(Constant.RESP_CODE) == Constant.RESP_SUCCESS) {
                                listener.onComplete(HostUrl.HOST + root.optJSONObject(Constant.RESP_DATA).optString("savePath")
                                        + root.optJSONObject(Constant.RESP_DATA).optString("name"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        listener.onProgress((int) (progress.currentSize / progress.totalSize) * 100);
                    }
                });
    }

}
