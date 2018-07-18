package com.ahxd.lingyuangou.ui.home.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.BuyCardDetailBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by sxliu on 2018/7/15 13:51
 * E-mail Address 2587294424@qq.com
 */

public class BuyCardDetailViewHolder extends mBaseViewHolder<BuyCardDetailBean> {
    @BindView(R.id.tv_cardName)
    TextView tvCardName;
    @BindView(R.id.tv_buy_time)
    TextView tvBuyTime;
    @BindView(R.id.tv_card_statu)
    TextView tvCardStatu;

    public BuyCardDetailViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(List<BuyCardDetailBean> data, int position) {
        BuyCardDetailBean item = data.get(position);
        tvCardName.setText(item.getMemberCardName());
        tvBuyTime.setText(item.getRechargeDate());
        tvCardStatu.setText(item.getRecharge_status());
    }
}
