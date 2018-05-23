package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.OrderBean;
import com.ahxd.lingyuangou.ui.mine.contract.IOrderContract;
import com.ahxd.lingyuangou.ui.mine.model.OrderModel;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Mao on 2018/1/14.
 */

public class OrderPresenter implements IOrderContract.IOrderPresenter {

    private IOrderContract.IOrderView mView;
    private IOrderContract.IOrderModel mModel;

    public OrderPresenter(IOrderContract.IOrderView view) {
        this.mView = view;
        mModel = new OrderModel();
    }

    @Override
    public void getOrderList(String orderStatus, String isAppraise, int page) {
        mModel.getOrderList(orderStatus, isAppraise, page, new IOrderContract.IOrderModel.IOrderCallback(mView) {
            @Override
            public void onOrderList(List<OrderBean> list) {
                mView.showOrderList(list);
            }
        });
    }

    @Override
    public void getGiftOrderList(String orderStatus, String isAppraise, int page) {
        mModel.getGiftOrderList(orderStatus, isAppraise, page, new IOrderContract.IOrderModel.IOrderCallback(mView) {
            @Override
            public void onGiftOrderList(List<OrderBean> list) {
                mView.showGiftOrderList(list);
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
