package com.ahxd.lingyuangou.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.cart.activity.PayResultActivity;
import com.ahxd.lingyuangou.ui.main.activity.MainActivity;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.SPUtils;
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
        boolean isucernter = (boolean) SPUtils.get(this,Constant.SP_WEIXIN_PAY,false);
        if (resp.errCode == 0) {
            //表示是从购买商家卡和购买资格来的
            ToastUtils.showShort(this, "支付成功");
            if (isucernter){
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("position",3);
                startActivity(intent);
            }else {
                Intent intent = new Intent(this, PayResultActivity.class);
                startActivity(intent);
            }
            finish();
        } else if (resp.errCode == -2) {
            ToastUtils.showShort(this, "取消支付");
            finish();
        } else {
            ToastUtils.showShort(this, "支付失败");
            finish();
        }
        SPUtils.put(this,Constant.SP_WEIXIN_PAY,false);
    }
}