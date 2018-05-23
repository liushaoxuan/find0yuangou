package com.ahxd.lingyuangou.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.cart.activity.PayResultActivity;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MaoApplication) getApplication()).getWXApi().handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        L.e("aaaaaaaaaaa" + resp.errCode);
        if (resp.errCode == 0) {
            ToastUtils.showShort(this, "支付成功");
            Intent intent = new Intent(this, PayResultActivity.class);
            startActivity(intent);
            finish();
        } else if (resp.errCode == -2) {
            ToastUtils.showShort(this, "取消支付");
            finish();
        } else {
            ToastUtils.showShort(this, "支付失败");
            finish();
        }
    }
}