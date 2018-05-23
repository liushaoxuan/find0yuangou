package com.ahxd.lingyuangou.listener;

import com.ahxd.lingyuangou.bean.AddressBean;

/**
 * Created by Administrator on 2018/1/12.
 */

public interface OnEditAddressListener {

    // 删除地址
    public void onDeleteAddress(AddressBean bean);

    // 编辑地址
    public void onEditAddress(AddressBean bean);

    // 设置默认地址
    public void onSetDefault(AddressBean bean);

}
