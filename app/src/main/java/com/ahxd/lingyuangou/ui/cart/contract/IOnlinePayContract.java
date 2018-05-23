package com.ahxd.lingyuangou.ui.cart.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Mao Zhendong on 2018/1/11.
 */

public interface IOnlinePayContract {

    public interface IOnlinePayView extends BaseView {
        public void showSubmitGiftOrder(JSONObject data);

        public void showPayGiftOrder(String data);

        public void showSubmitOrder(JSONObject data);

        public void showWeixinPay(JSONObject data);

        public void showZhifubaoPay(String data);

        public void showWalletPay(String msg);

        public void showOrderDetail(JSONObject data);
    }

    public interface IOnlinePayPresenter {
        // 提交订单/选择地址会重新调用，因为要刷新运费
        public void submitGiftOrder(String content, String addressId);

        // 提交订单/选择地址会重新调用，因为要刷新运费
        public void submitOrder(String content, String addressId);
        // 线上支付
        public void payOnlineOrder(String content, String addressId, int type);
        // 兑换支付
        public void payGiftOrder(String content, String addressId);
        // 商品详情（兑换立即购买）
        public void payGiftRightNow(String goodsId, String specIds, String buyNum, String addressId,
                                int type, String msg);
        // 商品详情（立即购买）
        public void payRightNow(String goodsId, String specIds, String buyNum, String addressId,
                                int type, String msg);
        // 订单列表，进入支付详情的支付
        public void payOrderList(int type, String orderId, String sum);

        public void chooseAddressRefresh(String goodsId, String specIds, String buyNum, String addressId);
    }

    public interface IOnlinePayModel {

        public void submitGiftOrder(String content, String addressId, IOnlinePayCallback callback);

        public void submitOrder(String content, String addressId, IOnlinePayCallback callback);

        public void payOnlineOrder(String content, String addressId, int type, IOnlinePayCallback callback);

        public void payGiftOrder(String content, String addressId, IOnlinePayCallback callback);

        public void payGiftRightNow(String goodsId, String specIds, String buyNum, String addressId,
                                int type, String msg, IOnlinePayCallback callback);

        public void payRightNow(String goodsId, String specIds, String buyNum, String addressId,
                                int type, String msg, IOnlinePayCallback callback);

        public void payOrderList(int type, String orderId, String sum, IOnlinePayCallback callback);

        public abstract class IOnlinePayCallback extends ModelCallback {

            public IOnlinePayCallback(BaseView view) {
                super(view);
            }


            public void onPayGiftOrder(String data) {}

            public void onWeixinPay(JSONObject data) {}

            public void onZhifubaoPay(String data) {}

            public void onWalletPay(String msg) {}

        }
    }

}
