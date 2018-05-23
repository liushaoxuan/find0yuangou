package com.ahxd.lingyuangou.utils;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.FileProvider;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/11/8.
 */

public class VersionUtils {
    static int downedFileLength = 0;
    static long totalLength;
    /**
     * 获取本地软件版本号
     */
    public static int getVersionCode(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
    //检查更新
    public  static void aboutEasyReport(final Context mContext, final String updatapath , final ProgressDialog progressDialog, final Handler handler) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                .setTitle("提示")
                .setMessage("现新版本,确定要下载吗？");
        builder.setCancelable(false);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        MaoApplication.ISDOWN=false;
                        //下载需要写SD卡权限, targetSdkVersion>=23 需要动态申请权限
                        RxPermissions.getInstance(mContext)
                                // 申请权限
                                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(new Action1<Boolean>() {
                                    @Override
                                    public void call(Boolean granted) {
                                        if(granted){
                                            //请求成功
                                            progressDialog
                                                    .setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                                            progressDialog.setTitle("更新");

                                            progressDialog.setMessage("下载中...");

                                            progressDialog.setIndeterminate(false);
                                            progressDialog.setProgress(100);

                                            progressDialog.setCancelable(true);

//                                            if (ApiUtils.DEFAULT_BASE_URL.equals("http://csapi.zimtek.cn/action_app")){
//                                                downFile(mContext,
//                                                        aboutTaskPlusModel.androidDownloadDev,
//                                                        aboutTaskPlusModel.version,progressDialog,handler);
//                                            }else {
                                                downFile(mContext,updatapath,
                                                        "LingYuanGou",progressDialog,handler);
//                                            }

                                        }else{
                                            // 请求失败回收当前服务

                                        }
                                    }
                                });



                    }
                });
        builder.create().show();
    }

    /**
     * 下载文件
     */
    public static void downFile(final Context contact, final String url,
                                final String name,final ProgressDialog progressDialog,final Handler handler) {
        progressDialog.show();
        FileUtils.createDirFile(MaoApplication.FILE+"/android_lyg");
        final File file = new File(MaoApplication.FILE+"/android_lyg", name);
        FileProvider.getUriForFile(contact, "com.wpc.fileprovider", file);
        new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    totalLength = entity.getContentLength();
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            progressDialog.setMax((int) totalLength / 1024);

                        }
                    });

                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];
                        int ch = -1;

                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            downedFileLength += ch;
                            handler.post(new Runnable() {

                                @Override
                                public void run() {
                                    progressDialog.setProgress(downedFileLength / 1024);

                                }
                            });
//

                        }

                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    down(contact, name,progressDialog);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();

    }


    static void down(final Context contact, final String name,final ProgressDialog progressDialog) {
        MaoApplication.handler.post(new Runnable() {
            public void run() {
                progressDialog.cancel();
                update(contact, name);
            }
        });
    }
    static void update(final Context contact, String name) {


        Intent intent = new Intent(Intent.ACTION_VIEW);
//            Uri data;
        File file=new File(MaoApplication.FILE+"/android_lyg", name);
//        File file = new File(
//                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//                , "myApp.apk");
//            data = FileProvider.getUriForFile(contact, "com.wpc.fileprovider", file);
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            intent.setDataAndType(data, "application/vnd.android.package-archive");
//            contact.startActivity(intent);


        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT>=24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri =
                    FileProvider.getUriForFile(contact, "com.wpc.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        }else{
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        contact.startActivity(intent);
        MaoApplication.ISDOWN=true;

    }

}
