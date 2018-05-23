package com.ahxd.lingyuangou.listener;

import com.ahxd.lingyuangou.bean.CartBean;

import org.json.JSONArray;

public interface OnCartOperateListener {

    void onDataChange(String selectCount, String selectMoney);

    void onSelectItem(boolean isSelectedAll);

    void onDeleteGood(String cartId);

    void onEditGoods(String content);

    void onDetailGoods(JSONArray params);

    void onCheckGoods(String content);

    void onChildClicked(CartBean.OrderBean.GoodsBean goodsBean);
}
