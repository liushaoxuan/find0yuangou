package com.ahxd.lingyuangou.ui.home.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeGoodBean;
import com.ahxd.lingyuangou.ui.home.adapter.HomeBaseAdapter;
import com.ahxd.lingyuangou.ui.home.holder.HomeBaseViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.HotGoodsViewHolder;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.List;

/**
 * Created by sxliu on 2018/6/28 22:53
 * E-mail Address 2587294424@qq.com
 */

public class HotGoodsAdapter extends HomeBaseAdapter<HomeGoodBean> {
    public HotGoodsAdapter(Context mContext, LayoutHelper mhelper, List<HomeGoodBean> mData) {
        super(mContext, mhelper, mData);
    }

    @Override
    public HomeBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_hot_good, parent, false);
        return new HotGoodsViewHolder(view);
    }
}
