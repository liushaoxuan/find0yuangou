package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.AdsBean;
import com.ahxd.lingyuangou.ui.home.holder.HomeBaseViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.PictureAdsViweHolder;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.List;

/**
 * Created by sxliu on 2018/6/28 1:42
 * E-mail Address 2587294424@qq.com
 * 图片广告
 */

public class PictureAdsAdapter extends HomeBaseAdapter<AdsBean> {
    public PictureAdsAdapter(Context mContext, LayoutHelper mhelper, List<AdsBean> mData) {
        super(mContext, mhelper, mData);
    }

    @Override
    public PictureAdsViweHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_ad, parent, false);
        return new PictureAdsViweHolder(view);
    }

    @Override
    public int getItemCount() {
        return mData==null||mData.size()==0?0:1;
    }
}
