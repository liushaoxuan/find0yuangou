package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.BankBean;
import com.ahxd.lingyuangou.bean.DicAddressBean;
import com.ahxd.lingyuangou.ui.mine.contract.IAddBankCardContract;
import com.ahxd.lingyuangou.ui.mine.contract.IEditAddressContract;
import com.ahxd.lingyuangou.ui.mine.model.AddBankCardModel;
import com.ahxd.lingyuangou.ui.mine.model.EditAddressModel;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mao Zhendong on 2018/1/12.
 */

public class AddBankCardPresenter implements IAddBankCardContract.IAddBankCardPresenter {

    private IAddBankCardContract.IAddBankCardView mView;
    private IAddBankCardContract.IAddBankCardModel mModel;

    public AddBankCardPresenter(IAddBankCardContract.IAddBankCardView view) {
        this.mView = view;
        mModel = new AddBankCardModel();
    }

    @Override
    public void getBank() {
        mModel.getBank(new IAddBankCardContract.IAddBankCardModel.IAddBankCardCallback(mView) {
            @Override
            public void onShowBank(ArrayList<BankBean> list) {
                mView.showBank(list);
            }
        });
    }
    @Override
    public void getProvince() {
        mModel.getProvince(new IAddBankCardContract.IAddBankCardModel.IAddBankCardCallback(mView) {
            @Override
            public void onShowProvince(ArrayList<DicAddressBean> list) {
                mView.showProvince(list);
            }
        });
    }

    @Override
    public void getCity(String provinceId) {
        mModel.getCity(provinceId, new IAddBankCardContract.IAddBankCardModel.IAddBankCardCallback(mView) {
            @Override
            public void onShowCity(ArrayList<DicAddressBean> list) {
                mView.showCity(list);
            }
        });
    }

    @Override
    public void getArea(String cityId) {
        mModel.getArea(cityId, new IAddBankCardContract.IAddBankCardModel.IAddBankCardCallback(mView) {
            @Override
            public void onShowArea(ArrayList<DicAddressBean> list) {
                mView.showArea(list);
            }
        });
    }
    @Override
    public void setConfig(String id,String accTargetId,String accNo,String accUser,String accAreaId,String accAddress,String userName,
                          String userPhone,String userNumber) {
        mModel.setConfig(id,accTargetId,accNo,accUser,accAreaId,accAddress,userName,userPhone,userNumber, new IAddBankCardContract.IAddBankCardModel.IAddBankCardCallback(mView) {
            @Override
            public void onShowConfig(JSONObject data) {
                mView.showConfig(data);
            }
        });
    }
}
