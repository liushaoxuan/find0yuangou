package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.ui.home.holder.HomeBaseViewHolder;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;

import java.util.List;

/**
 * Created by sxliu on 2018/6/27 22:49
 * E-mail Address 2587294424@qq.com
 * V-layout 的baseAdapter
 */

public abstract class HomeBaseAdapter<T> extends DelegateAdapter.Adapter<HomeBaseViewHolder> {
    protected Context mContext;
    protected LayoutHelper mhelper;
    protected List<T> mData;

    public HomeBaseAdapter(Context mContext, LayoutHelper mhelper, List<T> mData) {
        this.mContext = mContext;
        this.mhelper = mhelper;
        this.mData = mData;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mhelper;
    }

    @Override
    public void onBindViewHolder(HomeBaseViewHolder holder, int position) {
        holder.setData(mData,position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
