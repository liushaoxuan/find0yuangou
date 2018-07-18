package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.BuyCardDetailBean;
import com.ahxd.lingyuangou.ui.home.holder.BuyCardDetailViewHolder;

import java.util.List;

/**
 * Created by sxliu on 2018/7/15 17:21
 * E-mail Address 2587294424@qq.com
 */

public class BuyCardDetailAdapter extends mBaseAdapter<BuyCardDetailBean,BuyCardDetailViewHolder> {
    public BuyCardDetailAdapter(Context mContext, List<BuyCardDetailBean> mdata) {
        super(mContext, mdata);
    }

    @Override
    public BuyCardDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =mInflater.inflate(R.layout.item_buy_card_detail,parent,false);
        return new BuyCardDetailViewHolder(view);
    }
}
