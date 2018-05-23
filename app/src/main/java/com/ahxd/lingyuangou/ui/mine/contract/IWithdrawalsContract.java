package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.BankBean;
import com.ahxd.lingyuangou.bean.WithdrawalsAccountBean;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IWithdrawalsContract {

    public interface IWithdrawalsView extends BaseView {
        public void showWithdrawalsList(ArrayList<WithdrawalsAccountBean> list);
        public void showWithdrawals(JSONObject data);
    }

    public interface IWithdrawalsPresenter {
        // 获取提现账户列表
        public void getWithdrawalsList();
        // 申请提现
        public void setWithdrawals(String money,String accId);
    }

    public interface IWithdrawalsModel {

        public void getWithdrawalsList(IWithdrawalsCallback callback);

        public void setWithdrawals(String money,String accId,IWithdrawalsCallback callback);

        public abstract class IWithdrawalsCallback extends ModelCallback {

            public IWithdrawalsCallback(BaseView view) {
                super(view);
            }

            public void onWithdrawalsList(ArrayList<WithdrawalsAccountBean> list) {}

            public void onWithdrawals(JSONObject data) {}
        }

    }


}
