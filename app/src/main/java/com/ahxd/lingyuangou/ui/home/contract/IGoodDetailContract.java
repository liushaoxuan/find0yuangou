package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */

public interface IGoodDetailContract {

    public interface IGoodDetailView extends BaseView {
        public void showAddCart(String msg);

        public void showBuyNow(JSONObject data);
    }

    public interface IGoodDetailPresenter {
        // 加入购物车
        public void addGoodCart(String good_id, String goodsSpecId, String cartNum);
        // 立即购买
        public void buyGoodNow(String goodsId, String specIds, String buyNum, String addressId);
    }

    public interface IGoodDetailModel {

        public void addGoodCart(String good_id, String goodsSpecId, String cartNum, IGoodDetailCallback callback);

        public void buyGoodNow(String goodsId, String specIds, String buyNum, String addressId, IGoodDetailCallback callback);

        public abstract class IGoodDetailCallback extends ModelCallback {

            public IGoodDetailCallback(BaseView view) {
                super(view);
            }

            public void onAddCart(String msg) {}

            public void onBuyNow(JSONObject data) {}

        }

    }

}
