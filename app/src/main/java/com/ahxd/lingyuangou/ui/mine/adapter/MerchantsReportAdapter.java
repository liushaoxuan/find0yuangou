package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.ConsumerReportBean;
import com.ahxd.lingyuangou.bean.MerchantsMemberBean;
import com.ahxd.lingyuangou.ui.home.holder.MerchantsReportViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.mBaseViewHolder;

import java.util.List;

/**
 * Created by sxliu on 2018/7/9 22:05
 * E-mail Address 2587294424@qq.com
 * 商家报表adapter
 */

public class MerchantsReportAdapter extends mBaseAdapter<ConsumerReportBean,MerchantsReportViewHolder> {
    public MerchantsReportAdapter(Context mContext, List<ConsumerReportBean> mdata) {
        super(mContext, mdata);
    }

    @Override
    public mBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = mInflater.inflate(R.layout.item_merchants_report, parent,false);
        return new MerchantsReportViewHolder(view);
    }
}
