package com.ahxd.lingyuangou.listener;

import com.ahxd.lingyuangou.bean.OrderBean;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface OnOrderOperateListener {

    /**
     * 订单列表，商铺评价
     * @param bean
     */
    public void onShopEvaluate(OrderBean bean);

    /**
     * 立即支付
     * @param bean
     */
    public void onPayOrder(OrderBean bean);

    /**
     * 取消订单
     * @param bean
     */
    public void onCancelOrder(OrderBean bean);

    /**
     * 确认收货
     * @param bean
     */
    public void onConfirmReceiver(OrderBean bean);
}
