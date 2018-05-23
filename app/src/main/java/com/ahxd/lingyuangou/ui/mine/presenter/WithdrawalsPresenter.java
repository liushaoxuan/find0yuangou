package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.WithdrawalsAccountBean;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWithdrawalsContract;
import com.ahxd.lingyuangou.ui.mine.model.WalletModel;
import com.ahxd.lingyuangou.ui.mine.model.WithdrawalsModel;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/15.
 */
public class WithdrawalsPresenter implements IWithdrawalsContract.IWithdrawalsPresenter{

    private IWithdrawalsContract.IWithdrawalsView mView;
    private WithdrawalsModel mModel;

    public WithdrawalsPresenter(IWithdrawalsContract.IWithdrawalsView view) {
        this.mView = view;
        mModel = new WithdrawalsModel();
    }

    @Override
    public void getWithdrawalsList() {
        mModel.getWithdrawalsList(new IWithdrawalsContract.IWithdrawalsModel.IWithdrawalsCallback(mView) {
            @Override
            public void onWithdrawalsList(ArrayList<WithdrawalsAccountBean> list) {
                mView.showWithdrawalsList(list);
            }
        });
    }

    @Override
    public void setWithdrawals(String money, String accId) {
        mModel.setWithdrawals(money,accId,new IWithdrawalsContract.IWithdrawalsModel.IWithdrawalsCallback(mView) {
            @Override
            public void onWithdrawals(JSONObject data) {
                mView.showWithdrawals(data);
            }
        });
    }
}
