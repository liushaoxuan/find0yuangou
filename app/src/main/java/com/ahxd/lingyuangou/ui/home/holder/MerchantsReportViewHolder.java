package com.ahxd.lingyuangou.ui.home.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.ConsumerReportBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by sxliu on 2018/7/9 22:01
 * E-mail Address 2587294424@qq.com
 */

public class MerchantsReportViewHolder extends mBaseViewHolder<ConsumerReportBean> {
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.tv_money)
    TextView tvMoney;

    public MerchantsReportViewHolder(View itemView) {
        super(itemView);

    }

    @Override
    public void setData(List<ConsumerReportBean> data, int position) {
        ConsumerReportBean item = data.get(position);
        tvYear.setText(item.getReportYear());
        tvMonth.setText(item.getReportMonth());
        tvMoney.setText(item.getTotalMoney()+"");
    }
}
