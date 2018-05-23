package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.DicAddressBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mao Zhendong on 2018/1/12.
 */

public interface IEditAddressContract {

    public interface IEditAddressView extends BaseView {
        public void showAddAddress(String msg);

        public void showModifyAddress(String msg);

        public void showProvince(ArrayList<DicAddressBean> list);

        public void showCity(ArrayList<DicAddressBean> list);

        public void showArea(ArrayList<DicAddressBean> list);
    }

    public interface IEditAddressPresenter {
        // 添加地址
        public void addAddress(String userName, String userPhone, String province, String city,
                               String area, String userAddress);

        public void modifyAddress(String addressId, String userName, String userPhone, String province,
                                  String city, String area, String userAddress);

        // 获取省份
        public void getProvince();

        // 获取市
        public void getCity(String provinceId);

        // 获取区域
        public void getArea(String cityId);
    }

    public interface IEditAddressModel {

        public void addAddress(String userName, String userPhone, String province, String city,
                               String area, String userAddress, IEditAddressCallback callback);

        public void modifyAddress(String addressId, String userName, String userPhone, String province,
                                  String city, String area, String userAddress, IEditAddressCallback callback);

        public void getProvince(IEditAddressCallback callback);

        public void getCity(String provinceId, IEditAddressCallback callback);

        public void getArea(String cityId, IEditAddressCallback callback);

        public abstract class IEditAddressCallback extends ModelCallback {

            public IEditAddressCallback(BaseView view) {
                super(view);
            }

            public void onAddAddress(String msg) {}

            public void onModifyAddress(String msg) {}

            public void onShowProvince(ArrayList<DicAddressBean> list) {}

            public void onShowCity(ArrayList<DicAddressBean> list) {}

            public void onShowArea(ArrayList<DicAddressBean> list) {}
        }
    }

}
