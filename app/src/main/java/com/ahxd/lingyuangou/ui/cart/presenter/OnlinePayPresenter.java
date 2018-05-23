package com.ahxd.lingyuangou.ui.cart.presenter;

import com.ahxd.lingyuangou.bean.CartBean;
import com.ahxd.lingyuangou.ui.cart.contract.ICartContract;
import com.ahxd.lingyuangou.ui.cart.contract.IOnlinePayContract;
import com.ahxd.lingyuangou.ui.cart.model.CartModel;
import com.ahxd.lingyuangou.ui.cart.model.OnlinePayModel;
import com.ahxd.lingyuangou.ui.home.contract.IGoodDetailContract;
import com.ahxd.lingyuangou.ui.home.model.GoodDetailModel;

import org.json.JSONObject;

/**
 * Created by Mao Zhendong on 2018/1/11.
 */

public class OnlinePayPresenter implements IOnlinePayContract.IOnlinePayPresenter {

    private IOnlinePayContract.IOnlinePayView mView;
    private CartModel mCartModel;
    private OnlinePayModel mOnlinePayModel;
    private GoodDetailModel mGoodDetailModel;

    public OnlinePayPresenter(IOnlinePayContract.IOnlinePayView view) {
        this.mView = view;
        mCartModel = new CartModel();
        mOnlinePayModel = new OnlinePayModel();
        mGoodDetailModel = new GoodDetailModel();
    }

    @Override
    public void submitGiftOrder(String content, String addressId) {
        mCartModel.onGiftSubmitOrder(content, addressId, new ICartContract.ICartModel.ICartCallback(mView) {
            @Override
            public void onGiftSubmitOrder(JSONObject data) {
                mView.showSubmitGiftOrder(data);
            }
        });
    }

    @Override
    public void submitOrder(String content, String addressId) {
        mCartModel.onSubmitOrder(content, addressId, new ICartContract.ICartModel.ICartCallback(mView) {
            @Override
            public void onSubmitOrder(JSONObject data) {
                mView.showSubmitOrder(data);
            }
        });
    }

    @Override
    public void payOnlineOrder(String content, String addressId, int type) {
        mOnlinePayModel.payOnlineOrder(content, addressId, type, new IOnlinePayContract.IOnlinePayModel.IOnlinePayCallback(mView) {
            @Override
            public void onWeixinPay(JSONObject data) {
                mView.showWeixinPay(data);
            }

            @Override
            public void onZhifubaoPay(String data) {
                mView.showZhifubaoPay(data);
            }

            @Override
            public void onWalletPay(String msg) {
                mView.showWalletPay(msg);
            }
        });
    }

    @Override
    public void payGiftOrder(String content, String addressId) {
        mOnlinePayModel.payGiftOrder(content, addressId, new IOnlinePayContract.IOnlinePayModel.IOnlinePayCallback(mView) {
            @Override
            public void onPayGiftOrder(String data) {
                mView.showPayGiftOrder(data);
            }

        });
    }

    @Override
    public void payGiftRightNow(String goodsId, String specIds, String buyNum, String addressId, int type, String msg) {
        mOnlinePayModel.payGiftRightNow(goodsId, specIds, buyNum, addressId, type, msg,
                new IOnlinePayContract.IOnlinePayModel.IOnlinePayCallback(mView) {
                    @Override
                    public void onWalletPay(String msg) {
                        mView.showWalletPay(msg);
                    }
                });
    }

    @Override
    public void payRightNow(String goodsId, String specIds, String buyNum, String addressId, int type,
                            String msg) {
        mOnlinePayModel.payRightNow(goodsId, specIds, buyNum, addressId, type, msg,
                new IOnlinePayContract.IOnlinePayModel.IOnlinePayCallback(mView) {
                    @Override
                    public void onWeixinPay(JSONObject data) {
                        mView.showWeixinPay(data);
                    }

                    @Override
                    public void onZhifubaoPay(String data) {
                        mView.showZhifubaoPay(data);
                    }

                    @Override
                    public void onWalletPay(String msg) {
                        mView.showWalletPay(msg);
                    }
                });
    }

    @Override
    public void payOrderList(int type, String orderId, String sum) {
        mOnlinePayModel.payOrderList(type, orderId, sum, new IOnlinePayContract.IOnlinePayModel.IOnlinePayCallback(mView) {
            @Override
            public void onWeixinPay(JSONObject data) {
                mView.showWeixinPay(data);
            }

            @Override
            public void onZhifubaoPay(String data) {
                mView.showZhifubaoPay(data);
            }

            @Override
            public void onWalletPay(String msg) {
                mView.showWalletPay(msg);
            }
        });
    }

    @Override
    public void chooseAddressRefresh(String goodsId, String specIds, String buyNum, String addressId) {
        mGoodDetailModel.buyGoodNow(goodsId, specIds, buyNum, addressId, new IGoodDetailContract.IGoodDetailModel.IGoodDetailCallback(mView) {
            @Override
            public void onBuyNow(JSONObject data) {
                mView.showOrderDetail(data);
            }
        });
    }
}
