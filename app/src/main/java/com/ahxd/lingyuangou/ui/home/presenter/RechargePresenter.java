package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.RechargeBean;
import com.ahxd.lingyuangou.ui.home.contract.IRechargeContract;
import com.ahxd.lingyuangou.ui.home.model.RechargeModel;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class RechargePresenter implements IRechargeContract.IRechargePresenter {

    private IRechargeContract.IRechargeView mView;
    private IRechargeContract.IRechargeModel mModel;

    public RechargePresenter(IRechargeContract.IRechargeView view) {
        this.mView = view;
        mModel = new RechargeModel();
    }

    @Override
    public void getRechargeNumList() {
        mModel.getRechargeNumList(new IRechargeContract.IRechargeModel.IRechargeCallback(mView) {
            @Override
            public void onRechargeList(List<RechargeBean> list) {
                mView.showRechargeList(list);
            }
        });
    }

    @Override
    public void recharge(int type, String money) {
        mModel.recharge(type, money, new IRechargeContract.IRechargeModel.IRechargeCallback(mView) {
            @Override
            public void onZhifubaoPay(String data) {
                mView.showZhifubaoPay(data);
            }

            @Override
            public void onWeixinPay(JSONObject data) {
                mView.showWeixinPay(data);
            }
        });
    }
}
