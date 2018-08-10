package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Mao Zhendong on 2018/1/10.
 */

public interface IOfflinePayContract {

    public interface IOfflinePayView extends BaseView {
        public void showShopDetail(JSONObject data);

        public void showWeixinPay(JSONObject data);

        public void showZhifubaoPay(String data);

        public void showWalletPay(String msg);
        void showSjk(String msg);
    }

    public interface IOfflinePayPresenter {
        // 优惠买单获取商铺详情
        public void getOfflinePayDetail(String token, String shopId);
        // 线下优惠买单 支付
        public void payOfflineOrder(String token, String shopId, int type, String money);
    }

    public interface IOfflinePayModel {

        public void getOfflinePayDetail(String token, String shopId, IOfflinePayCallback callback);

        public void payOfflineOrder(String token, String shopId, int type, String money, IOfflinePayCallback callback);

        public abstract class IOfflinePayCallback extends ModelCallback {

            public IOfflinePayCallback(BaseView view) {
                super(view);
            }

            public void onShopDetail(JSONObject data) {}

            public void onWeixinPay(JSONObject data) {}

            public void onZhifubaoPay(String data) {}

            public void onWalletPay(String msg) {}

            public void onSjk(String msg){}
        }
    }

}
