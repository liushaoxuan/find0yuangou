package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IOrderContract;
import com.ahxd.lingyuangou.ui.mine.contract.IOrderDetailContract;
import com.ahxd.lingyuangou.ui.mine.model.OrderModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */

public class OrderDetailPresenter implements IOrderDetailContract.IOrderDetailPresenter {

    private IOrderDetailContract.IOrderDetailView mView;
    private OrderModel mModel;

    public OrderDetailPresenter(IOrderDetailContract.IOrderDetailView view) {
        this.mView = view;
        mModel = new OrderModel();
    }

    @Override
    public void getOrderDetail(String orderId) {
        mModel.getOrderDetail(orderId, new IOrderContract.IOrderModel.IOrderCallback(mView) {
            @Override
            public void onOrderDetail(JSONObject data) {
                mView.showOrderDetail(data);
            }
        });
    }

    @Override
    public void getGiftOrderDetail(String orderId) {
        mModel.getGiftOrderDetail(orderId, new IOrderContract.IOrderModel.IOrderCallback(mView) {
            @Override
            public void onGiftOrderDetail(JSONObject data) {
                mView.showGiftOrderDetail(data);
            }
        });
    }

    @Override
    public void onCancelOrder(String orderId, String reason) {
        mModel.onCancelOrder(orderId, reason, new IOrderContract.IOrderModel.IOrderCallback(mView) {
            @Override
            public void onCancelOrder(String msg) {
                mView.showCancelOrder(msg);
            }
        });
    }

    @Override
    public void onGiftCancelOrder(String orderId, String reason) {
        mModel.onGiftCancelOrder(orderId, reason, new IOrderContract.IOrderModel.IOrderCallback(mView) {
            @Override
            public void onGiftCancelOrder(String msg) {
                mView.showGiftCancelOrder(msg);
            }
        });
    }

    @Override
    public void getOrderPayInfo(String orderId) {
        mModel.getOrderPayInfo(orderId, new IOrderContract.IOrderModel.IOrderCallback(mView) {
            @Override
            public void onOrderPayDetail(JSONObject data) {
                mView.showOrderPayDetail(data);
            }
        });
    }

    @Override
    public void getGiftOrderPayInfo(String orderId) {
        mModel.getGiftOrderPayInfo(orderId, new IOrderContract.IOrderModel.IOrderCallback(mView) {
            @Override
            public void onGiftOrderPayDetail(JSONObject data) {
                mView.showGiftOrderPayDetail(data);
            }
        });
    }

    @Override
    public void onConfirmReceiver(String orderId) {
        mModel.onConfirmReceiver(orderId, new IOrderContract.IOrderModel.IOrderCallback(mView) {
            @Override
            public void onConfirmReceiver(String msg) {
                mView.showConfirmReceiver(msg);
            }
        });
    }

    @Override
    public void onGiftConfirmReceiver(String orderId) {
        mModel.onGiftConfirmReceiver(orderId, new IOrderContract.IOrderModel.IOrderCallback(mView) {
            @Override
            public void onGiftConfirmReceiver(String msg) {
                mView.showGiftConfirmReceiver(msg);
            }
        });
    }
}
