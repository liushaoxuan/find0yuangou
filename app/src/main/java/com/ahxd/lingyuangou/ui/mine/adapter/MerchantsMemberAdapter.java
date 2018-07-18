package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.MerchantsMemberBean;
import com.ahxd.lingyuangou.ui.home.holder.MerchantsMemberViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.mBaseViewHolder;

import java.util.List;

/**
 * Created by sxliu on 2018/7/8 20:37
 * E-mail Address 2587294424@qq.com
 */

public class MerchantsMemberAdapter extends mBaseAdapter<MerchantsMemberBean,MerchantsMemberViewHolder> {
    public MerchantsMemberAdapter(Context mContext, List<MerchantsMemberBean> mdata) {
        super(mContext, mdata);
    }

    @Override
    public mBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_merchants_member,parent,false);
        return new MerchantsMemberViewHolder(view);
    }
}
