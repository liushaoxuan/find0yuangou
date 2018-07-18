package com.ahxd.lingyuangou.ui.home.holder;

import android.view.View;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.MerchantsMemberBean;
import com.ahxd.lingyuangou.bean.shopConsumerOrderBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by sxliu on 2018/7/8 20:32
 * E-mail Address 2587294424@qq.com
 * 商家会员ViewHolder
 */

public class MerchantsOrderViewHolder extends mBaseViewHolder<shopConsumerOrderBean> {
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_userPhone)
    TextView tvUserPhone;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_consumption_amount)
    TextView tvConsumptionAmount;
    @BindView(R.id.tv_cart_amount)
    TextView tvCreatTime;

    public MerchantsOrderViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(List<shopConsumerOrderBean> data, int position) {
        shopConsumerOrderBean item = data.get(position);
        tvPosition.setText((position + 1) + "");
        tvUserName.setText(item.getTrueName());
        tvUserPhone.setText(item.getUserPhone());
        tvConsumptionAmount.setText(item.getTotalMoney() + "");
        tvCreatTime.setText(item.getCreateTime() + "");


    }
}
