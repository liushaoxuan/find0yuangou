package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.AdsBean;
import com.ahxd.lingyuangou.bean.ArticlesBean;
import com.ahxd.lingyuangou.ui.home.holder.AdvertisingViewHolder;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.List;

/**
 * Created by sxliu on 2018/6/28 0:29
 * E-mail Address 2587294424@qq.com
 */

public class AdvertisingAdapter extends HomeBaseAdapter<ArticlesBean> {

    public AdvertisingAdapter(Context mContext, LayoutHelper mhelper, List<ArticlesBean> mData) {
        super(mContext, mhelper, mData);
    }

    @Override
    public AdvertisingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_message, parent, false);
        return new AdvertisingViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mData==null||mData.size()==0?0:1;
    }
}
