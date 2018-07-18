package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.ui.home.holder.HomeBaseViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.LabelViewHolder;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.List;

/**
 * Created by sxliu on 2018/6/30 1:04
 * E-mail Address 2587294424@qq.com
 * 热门商品分类的label，因为他是grid布局的，所以需要单独给他设置上面的label
 */

public class LaberAcapter extends HomeBaseAdapter<String> {
    public LaberAcapter(Context mContext, LayoutHelper mhelper, List<String> mData) {
        super(mContext, mhelper, mData);
    }

    @Override
    public HomeBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_label,parent,false);
        return new LabelViewHolder(view);
    }
}
