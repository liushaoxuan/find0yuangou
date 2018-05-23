package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */

public interface IOrderDetailContract {

    public interface IOrderDetailView extends BaseView {
        public void showOrderDetail(JSONObject data);
        public void showGiftOrderDetail(JSONObject data);

        public void showCancelOrder(String msg);
        public void showGiftCancelOrder(String msg);

        public void showOrderPayDetail(JSONObject data);
        public void showGiftOrderPayDetail(JSONObject data);

        public void showConfirmReceiver(String msg);
        public void showGiftConfirmReceiver(String msg);
    }

    public interface IOrderDetailPresenter {
        // 获取订单详情
        public void getOrderDetail(String orderId);
        public void getGiftOrderDetail(String orderId);
        // 取消订单
        public void onCancelOrder(String orderId, String reason);
        public void onGiftCancelOrder(String orderId, String reason);
        // 获取支付详情
        public void getOrderPayInfo(String orderId);
        public void getGiftOrderPayInfo(String orderId);
        // 确认收货
        public void onConfirmReceiver(String orderId);
        public void onGiftConfirmReceiver(String orderId);
    }

}
