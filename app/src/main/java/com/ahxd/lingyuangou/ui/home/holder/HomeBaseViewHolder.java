package com.ahxd.lingyuangou.ui.home.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by sxliu on 2018/6/27 22:55
 * E-mail Address 2587294424@qq.com
 */

public abstract class HomeBaseViewHolder<T> extends RecyclerView.ViewHolder {
    protected Context mContext;
    public HomeBaseViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
    }

    public   abstract void setData(List<T> t,int position);
}
