package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeFoodShopBean;
import com.ahxd.lingyuangou.ui.home.holder.RecommendEduViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.RecommendHealthViewHolder;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.List;

/**
 * Created by sxliu on 2018/6/28 22:42
 * E-mail Address 2587294424@qq.com
 * 推荐健康adapter
 */

public class RecommendHealthAdapter extends HomeBaseAdapter<HomeFoodShopBean> {
    public RecommendHealthAdapter(Context mContext, LayoutHelper mhelper, List<HomeFoodShopBean> mData) {
        super(mContext, mhelper, mData);
    }

    @Override
    public RecommendHealthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_foods_shop, parent, false);
        return new RecommendHealthViewHolder(view);
    }
}
