package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.BankBean;
import com.ahxd.lingyuangou.bean.DicAddressBean;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mao Zhendong on 2018/1/12.
 */

public interface IAddBankCardContract {

    public interface IAddBankCardView extends BaseView {
        public void showConfig(JSONObject data);

        public void showBank(ArrayList<BankBean> list);

        public void showProvince(ArrayList<DicAddressBean> list);

        public void showCity(ArrayList<DicAddressBean> list);

        public void showArea(ArrayList<DicAddressBean> list);
    }

    public interface IAddBankCardPresenter {

        // 获取银行
        public void setConfig(String id,String accTargetId,String accNo,String accUser,String accAreaId,String accAddress,String userName,
                              String userPhone,String userNumber);

        // 获取银行
        public void getBank();

        // 获取省份
        public void getProvince();

        // 获取市
        public void getCity(String provinceId);

        // 获取区域
        public void getArea(String cityId);
    }

    public interface IAddBankCardModel {

        public void setConfig(String id,String accTargetId,String accNo,String accUser,String accAreaId,String accAddress,String userName,
                              String userPhone,String userNumber,IAddBankCardCallback callback);

        public void getBank(IAddBankCardCallback callback);

        public void getProvince(IAddBankCardCallback callback);

        public void getCity(String provinceId, IAddBankCardCallback callback);

        public void getArea(String cityId, IAddBankCardCallback callback);

        public abstract class IAddBankCardCallback extends ModelCallback {

            public IAddBankCardCallback(BaseView view) {
                super(view);
            }

            public void onShowConfig(JSONObject data) {}

            public void onShowBank(ArrayList<BankBean> list) {}

            public void onShowProvince(ArrayList<DicAddressBean> list) {}

            public void onShowCity(ArrayList<DicAddressBean> list) {}

            public void onShowArea(ArrayList<DicAddressBean> list) {}
        }
    }

}
