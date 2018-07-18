package com.ahxd.lingyuangou.ui.home.holder;

import android.view.View;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.MerchantsMemberBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by sxliu on 2018/7/8 20:32
 * E-mail Address 2587294424@qq.com
 * 商家会员ViewHolder
 */

public class MerchantsMemberViewHolder extends mBaseViewHolder<MerchantsMemberBean> {
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_userPhone)
    TextView tvUserPhone;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_consumption_amount)
    TextView tvConsumptionAmount;
    @BindView(R.id.tv_cart_amount)
    TextView tvCartAmount;

    public MerchantsMemberViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(List<MerchantsMemberBean> data, int position) {
        MerchantsMemberBean item = data.get(position);
        tvPosition.setText((position + 1) + "");
        tvUserName.setText(item.getUsername());
        tvUserPhone.setText(item.getPhone());
        tvConsumptionAmount.setText(item.getTotalMoney() + "");
        tvCartAmount.setText(item.getUserCardMoney() + "");


    }
}
