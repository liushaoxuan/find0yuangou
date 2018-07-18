package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.ui.home.holder.ClassificationViewHolder;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.List;

/**
 * Created by sxliu on 2018/6/28 0:05
 * E-mail Address 2587294424@qq.com
 * 首页分类adapter
 */

public class ClassificationAdapter extends HomeBaseAdapter<HomeCatBean> {
    public ClassificationAdapter(Context mContext, LayoutHelper mhelper, List<HomeCatBean> mData) {
        super(mContext, mhelper, mData);
    }

    @Override
    public ClassificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_sort, parent, false);
        return new ClassificationViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mData==null||mData.size()==0?0:1;
    }
}
