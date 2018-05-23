package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.AddressBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public interface IAddressContract {

    public interface IAddressListView extends BaseView {
        public void showAddressList(List<AddressBean> list);

        public void showDeleteAddress(String msg);

        public void showDefaultAddress(String msg);
    }

    public interface IAddressListPresenter {
        // 获取地址列表
        public void getAddressList();
        // 删除地址
        public void onDeleteAddress(String addressId);
        // 设置默认地址
        public void setDefaultAddress(String addressId);
    }

    public interface IAddressListModel {

        public void getAddressList(IAddressListCallback callback);

        public void onDeleteAddress(String addressId, IAddressListCallback callback);

        public void setDefaultAddress(String addressId, IAddressListCallback callback);

        public abstract class IAddressListCallback extends ModelCallback {

            public IAddressListCallback(BaseView view) {
                super(view);
            }

            public void onAddressList(List<AddressBean> list) {}

            public void onDeleteAddress(String msg) {}

            public void onDefaultAddress(String msg) {}
        }
    }

}
