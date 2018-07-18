package com.ahxd.lingyuangou.ui.home.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by sxliu on 2018/7/8 19:26
 * E-mail Address 2587294424@qq.com
 */

public abstract class mBaseViewHolder<T> extends RecyclerView.ViewHolder {
    protected Context mContext;
    public mBaseViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this,itemView);
    }

    public abstract void setData(List<T> data,int position);
}
