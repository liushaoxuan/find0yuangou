package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.RechargeBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Mao Zhendong on 2018/1/9.
 */

public interface IRechargeContract {

    public interface IRechargeView extends BaseView {
        public void showRechargeList(List<RechargeBean> list);

        public void showZhifubaoPay(String data);

        public void showWeixinPay(JSONObject data);
    }

    public interface IRechargePresenter {
        public void getRechargeNumList();

        public void recharge(int type, String money);
    }

    public interface IRechargeModel {

        public void getRechargeNumList(IRechargeCallback callback);

        public void recharge(int type, String money, IRechargeCallback callback);

        public abstract class IRechargeCallback extends ModelCallback {

            public IRechargeCallback(BaseView view) {
                super(view);
            }

            public void onRechargeList(List<RechargeBean> list) {}

            public void onZhifubaoPay(String data) {}

            public void onWeixinPay(JSONObject data) {}
        }

    }

}
