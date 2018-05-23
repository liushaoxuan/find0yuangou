package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */

public interface IGiftGoodDetailContract {

    public interface IGiftGoodDetailView extends BaseView {
        public void showGiftAddCart(String msg);

        public void showGiftBuyNow(JSONObject data);
    }

    public interface IGiftGoodDetailPresenter {
        // 加入购物车
        public void addGiftGoodCart(String good_id, String goodsSpecId, String cartNum);
        // 立即购买
        public void buyGiftGoodNow(String goodsId, String buyNum, String addressId);
    }

    public interface IGiftGoodDetailModel {

        public void addGiftGoodCart(String good_id, String goodsSpecId, String cartNum, IGiftGoodDetailCallback callback);

        public void buyGiftGoodNow(String goodsId, String buyNum, String addressId, IGiftGoodDetailCallback callback);

        public abstract class IGiftGoodDetailCallback extends ModelCallback {

            public IGiftGoodDetailCallback(BaseView view) {
                super(view);
            }

            public void onGiftAddCart(String msg) {}

            public void onGiftBuyNow(JSONObject data) {}

        }

    }

}
