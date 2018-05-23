package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.OrderBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Mao on 2018/1/14.
 */

public interface IOrderContract {

    public interface IOrderView extends BaseView {
        public void showGiftOrderList(List<OrderBean> list);
        public void showOrderList(List<OrderBean> list);

        public void showGiftOrderPayDetail(JSONObject data);
        public void showOrderPayDetail(JSONObject data);

        public void showCancelOrder(String msg);
        public void showGiftCancelOrder(String msg);

        public void showConfirmReceiver(String msg);
        public void showGiftConfirmReceiver(String msg);
    }

    public interface IOrderPresenter {
        // 获取兑换订单列表
        public void getGiftOrderList(String orderStatus, String isAppraise, int page);
        // 获取订单列表
        public void getOrderList(String orderStatus, String isAppraise, int page);


        // 获取订单详情(订单界面，支付详情)
        public void getGiftOrderPayInfo(String orderId);
        // 获取订单详情(订单界面，支付详情)
        public void getOrderPayInfo(String orderId);


        // 取消订单
        public void onCancelOrder(String orderId, String reason);
        public void onGiftCancelOrder(String orderId, String reason);

        // 确认收货
        public void onConfirmReceiver(String orderId);
        public void onGiftConfirmReceiver(String orderId);
    }

    public interface IOrderModel {

        public void getGiftOrderList(String orderStatus, String isAppraise, int page, IOrderCallback callback);
        public void getOrderList(String orderStatus, String isAppraise, int page, IOrderCallback callback);


        public void getOrderDetail(String orderId, IOrderCallback callback);
        public void getGiftOrderDetail(String orderId, IOrderCallback callback);

        public void getOrderPayInfo(String orderId, IOrderCallback callback);
        public void getGiftOrderPayInfo(String orderId, IOrderCallback callback);

        public void onCancelOrder(String orderId, String reason, IOrderCallback callback);
        public void onGiftCancelOrder(String orderId, String reason, IOrderCallback callback);

        public void onConfirmReceiver(String orderId, IOrderCallback callback);
        public void onGiftConfirmReceiver(String orderId, IOrderCallback callback);

        public abstract class IOrderCallback extends ModelCallback {

            public IOrderCallback(BaseView view) {
                super(view);
            }

            public void onGiftOrderList(List<OrderBean> list) {}
            public void onOrderList(List<OrderBean> list) {}

            public void onGiftOrderDetail(JSONObject data) {}
            public void onOrderDetail(JSONObject data) {}

            public void onOrderPayDetail(JSONObject data) {}
            public void onGiftOrderPayDetail(JSONObject data) {}

            public void onCancelOrder(String msg) {}
            public void onGiftCancelOrder(String msg) {}

            public void onConfirmReceiver(String msg) {}
            public void onGiftConfirmReceiver(String msg) {}
        }

    }
}
