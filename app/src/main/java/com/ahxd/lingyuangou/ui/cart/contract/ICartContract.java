package com.ahxd.lingyuangou.ui.cart.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.CartBean;

import org.json.JSONObject;

/**
 * Created by Mao Zhendong on 2018/1/8.
 */

public interface ICartContract {

    public interface ICartView extends BaseView {
        public void showCartGoods(CartBean cartBean);

        public void showDeleteGood(String msg);

        public void showEditGoods(String msg);

        public void showSubmitOrder(JSONObject data);

        public void showGiftCartGoods(CartBean cartBean);

        public void showGiftDeleteGood(String msg);

        public void showGiftEditGoods(String msg);

        public void showGiftSubmitOrder(JSONObject data);

    }

    public interface ICartPresenter {
        // 获取购物车列表
        public void getCartGoods();
        // 删除商品
        public void onDeleteGood(String cartId);
        // 编辑购物车
        public void onEditGoods(String content);
        // 提交订单
        public void onSubmitOrder(String content);

        // 获取购物车列表
        public void getGiftCartGoods();
        // 删除商品
        public void onGiftDeleteGood(String cartId);
        // 编辑购物车
        public void onGiftEditGoods(String content);
        // 提交订单
        public void onGiftSubmitOrder(String content);
    }

    public interface ICartModel {

        // 获取购物车列表
        public void getCartGoods(ICartCallback callback);

        public void onDeleteGood(String cartId, ICartCallback callback);

        public void onEditGoods(String content, ICartCallback callback);

        public void onSubmitOrder(String content, String addressId, ICartCallback callback);

        // 获取购物车列表
        public void getGiftCartGoods(ICartCallback callback);

        public void onGiftDeleteGood(String cartId, ICartCallback callback);

        public void onGiftEditGoods(String content, ICartCallback callback);

        public void onGiftSubmitOrder(String content, String addressId, ICartCallback callback);

        public abstract class ICartCallback extends ModelCallback {

            public ICartCallback(BaseView view) {
                super(view);
            }

            public void onCartGoods(CartBean cartBean) {}

            public void onDeleteGood(String msg) {}

            public void onEditGoods(String msg) {}

            public void onSubmitOrder(JSONObject data) {}

            public void onGiftCartGoods(CartBean cartBean) {}

            public void onGiftDeleteGood(String msg) {}

            public void onGiftEditGoods(String msg) {}

            public void onGiftSubmitOrder(JSONObject data) {}
        }

    }

}
