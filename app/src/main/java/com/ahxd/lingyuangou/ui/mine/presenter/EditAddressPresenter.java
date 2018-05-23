package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.DicAddressBean;
import com.ahxd.lingyuangou.ui.mine.contract.IEditAddressContract;
import com.ahxd.lingyuangou.ui.mine.model.EditAddressModel;

import java.util.ArrayList;

/**
 * Created by Mao Zhendong on 2018/1/12.
 */

public class EditAddressPresenter implements IEditAddressContract.IEditAddressPresenter {

    private IEditAddressContract.IEditAddressView mView;
    private IEditAddressContract.IEditAddressModel mModel;

    public EditAddressPresenter(IEditAddressContract.IEditAddressView view) {
        this.mView = view;
        mModel = new EditAddressModel();
    }

    @Override
    public void addAddress(String userName, String userPhone, String province, String city,
                           String area, String userAddress) {
        mModel.addAddress(userName, userPhone, province, city, area, userAddress,
                new IEditAddressContract.IEditAddressModel.IEditAddressCallback(mView) {
            @Override
            public void onAddAddress(String msg) {
                mView.showAddAddress(msg);
            }
        });
    }

    @Override
    public void modifyAddress(String addressId, String userName, String userPhone, String province,
                              String city, String area, String userAddress) {
        mModel.modifyAddress(addressId, userName, userPhone, province, city, area, userAddress,
                new IEditAddressContract.IEditAddressModel.IEditAddressCallback(mView) {
                    @Override
                    public void onModifyAddress(String msg) {
                        mView.showModifyAddress(msg);
                    }
                });
    }

    @Override
    public void getProvince() {
        mModel.getProvince(new IEditAddressContract.IEditAddressModel.IEditAddressCallback(mView) {
            @Override
            public void onShowProvince(ArrayList<DicAddressBean> list) {
                mView.showProvince(list);
            }
        });
    }

    @Override
    public void getCity(String provinceId) {
        mModel.getCity(provinceId, new IEditAddressContract.IEditAddressModel.IEditAddressCallback(mView) {
            @Override
            public void onShowCity(ArrayList<DicAddressBean> list) {
                mView.showCity(list);
            }
        });
    }

    @Override
    public void getArea(String cityId) {
        mModel.getArea(cityId, new IEditAddressContract.IEditAddressModel.IEditAddressCallback(mView) {
            @Override
            public void onShowArea(ArrayList<DicAddressBean> list) {
                mView.showArea(list);
            }
        });
    }
}
