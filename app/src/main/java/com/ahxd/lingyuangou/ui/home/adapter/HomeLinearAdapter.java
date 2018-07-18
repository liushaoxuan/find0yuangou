package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.BaseBean;
import com.ahxd.lingyuangou.ui.home.holder.HomeLinearViewHolder;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.List;

/**
 * Created by sxliu on 2018/6/28 2:00
 * E-mail Address 2587294424@qq.com
 */

public class HomeLinearAdapter<T extends BaseBean> extends HomeBaseAdapter<T> {
    public HomeLinearAdapter(Context mContext, LayoutHelper mhelper, List<T> mData) {
        super(mContext, mhelper, mData);
    }

    @Override
    public HomeLinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_foods_shop, parent, false);
        return new HomeLinearViewHolder(view);
    }
}
