package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.bean.AddressBean;
import com.ahxd.lingyuangou.ui.mine.contract.IAddressContract;
import com.ahxd.lingyuangou.ui.mine.model.AddressModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class AddressListPresenter implements IAddressContract.IAddressListPresenter {

    private IAddressContract.IAddressListView mView;
    private AddressModel mAddressModel;

    public AddressListPresenter(IAddressContract.IAddressListView view) {
        this.mView = view;
        this.mAddressModel = new AddressModel();
    }

    @Override
    public void getAddressList() {
        mAddressModel.getAddressList(new IAddressContract.IAddressListModel.IAddressListCallback(mView) {
            @Override
            public void onAddressList(List<AddressBean> list) {
                mView.showAddressList(list);
            }
        });
    }

    @Override
    public void onDeleteAddress(String addressId) {
        mAddressModel.onDeleteAddress(addressId, new IAddressContract.IAddressListModel.IAddressListCallback(mView) {
            @Override
            public void onDeleteAddress(String msg) {
                mView.showDeleteAddress(msg);
            }
        });
    }

    @Override
    public void setDefaultAddress(String addressId) {
        mAddressModel.setDefaultAddress(addressId, new IAddressContract.IAddressListModel.IAddressListCallback(mView) {
            @Override
            public void onDefaultAddress(String msg) {
                mView.showDefaultAddress(msg);
            }
        });
    }
}
