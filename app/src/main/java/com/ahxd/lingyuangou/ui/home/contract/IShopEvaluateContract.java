package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.bean.FoodShopEvaluateBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/4.
 */

public interface IShopEvaluateContract {

    public interface IShopEvaluateView extends BaseView {
        public void showEvaluateList(List<FoodShopEvaluateBean> list);
    }

    public interface IShopEvaluatePresenter {
        public void getShopEvaluateList(String shopId, String goodsId, int page);
    }

}
