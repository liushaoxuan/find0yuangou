package com.ahxd.lingyuangou.ui.cart.presenter;

import com.ahxd.lingyuangou.bean.CartBean;
import com.ahxd.lingyuangou.ui.cart.contract.ICartContract;
import com.ahxd.lingyuangou.ui.cart.model.CartModel;

import org.json.JSONObject;

/**
 * Created by Mao Zhendong on 2018/1/8.
 */

public class CartPresenter implements ICartContract.ICartPresenter {

    private ICartContract.ICartView mView;
    private CartModel mModel;

    public CartPresenter(ICartContract.ICartView view) {
        this.mView = view;
        mModel = new CartModel();
    }

    @Override
    public void getCartGoods() {
        mModel.getCartGoods(new ICartContract.ICartModel.ICartCallback(mView) {
            @Override
            public void onCartGoods(CartBean cartBean) {
                mView.showCartGoods(cartBean);
            }
        });
    }

    @Override
    public void onDeleteGood(String cartId) {
        mModel.onDeleteGood(cartId, new ICartContract.ICartModel.ICartCallback(mView) {
            @Override
            public void onDeleteGood(String msg) {
                mView.showDeleteGood(msg);
            }
        });
    }

    @Override
    public void onEditGoods(String content) {
        mModel.onEditGoods(content, new ICartContract.ICartModel.ICartCallback(mView) {
            @Override
            public void onEditGoods(String msg) {
                mView.showEditGoods(msg);
            }
        });
    }

    @Override
    public void onSubmitOrder(String content) {
        mModel.onSubmitOrder(content, null, new ICartContract.ICartModel.ICartCallback(mView) {
            @Override
            public void onSubmitOrder(JSONObject data) {
                mView.showSubmitOrder(data);
            }
        });
    }
    @Override
    public void getGiftCartGoods() {
        mModel.getGiftCartGoods(new ICartContract.ICartModel.ICartCallback(mView) {
            @Override
            public void onGiftCartGoods(CartBean cartBean) {
                mView.showGiftCartGoods(cartBean);
            }
        });
    }

    @Override
    public void onGiftDeleteGood(String cartId) {
        mModel.onGiftDeleteGood(cartId, new ICartContract.ICartModel.ICartCallback(mView) {
            @Override
            public void onGiftDeleteGood(String msg) {
                mView.showGiftDeleteGood(msg);
            }
        });
    }

    @Override
    public void onGiftEditGoods(String content) {
        mModel.onGiftEditGoods(content, new ICartContract.ICartModel.ICartCallback(mView) {
            @Override
            public void onGiftEditGoods(String msg) {
                mView.showGiftEditGoods(msg);
            }
        });
    }

    @Override
    public void onGiftSubmitOrder(String content) {
        mModel.onGiftSubmitOrder(content, null, new ICartContract.ICartModel.ICartCallback(mView) {
            @Override
            public void onGiftSubmitOrder(JSONObject data) {
                mView.showGiftSubmitOrder(data);
            }
        });
    }


}
