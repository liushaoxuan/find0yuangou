package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeGiftBean;
import com.ahxd.lingyuangou.ui.home.holder.ExchangeMallViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.HomeBaseViewHolder;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.List;

/**
 * Created by sxliu on 2018/6/28 1:12
 * E-mail Address 2587294424@qq.com
 */

public class ExchangeMallAdapter extends HomeBaseAdapter<HomeGiftBean> {
    public ExchangeMallAdapter(Context mContext, LayoutHelper mhelper, List<HomeGiftBean> mData) {
        super(mContext, mhelper, mData);
    }

    @Override
    public ExchangeMallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_gift, parent, false);
        return new ExchangeMallViewHolder(view) ;
    }

    @Override
    public int getItemCount() {

        if (mData.size()==0){
            return super.getItemCount();
        }else {
            return 1;
        }
    }
}
