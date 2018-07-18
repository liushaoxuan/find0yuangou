package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.ahxd.lingyuangou.bean.BaseBean;
import com.ahxd.lingyuangou.ui.home.holder.mBaseViewHolder;

import java.util.List;

/**
 * Created by sxliu on 2018/7/8 19:25
 * E-mail Address 2587294424@qq.com
 */

public abstract class mBaseAdapter<T extends BaseBean,H extends mBaseViewHolder> extends RecyclerView.Adapter<mBaseViewHolder> {
    protected List<T> mdata;
    protected Context mContext;
    protected LayoutInflater mInflater;

    public mBaseAdapter(Context mContext, List<T> mdata) {
        this.mdata = mdata;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void onBindViewHolder(mBaseViewHolder holder, int position) {
        holder.setData(mdata,position);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }
}
