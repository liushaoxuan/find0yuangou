package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.BannerBean;
import com.ahxd.lingyuangou.ui.home.activity.SearchActivity;
import com.ahxd.lingyuangou.ui.home.holder.HomeBaseViewHolder;
import com.ahxd.lingyuangou.utils.GlideImageLoader;
import com.alibaba.android.vlayout.LayoutHelper;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sxliu on 2018/6/27 23:00
 * E-mail Address 2587294424@qq.com
 */

public class BannerAdapter extends HomeBaseAdapter<BannerBean> {
    public BannerAdapter(Context mContext, LayoutHelper mhelper, List<BannerBean> mData) {
        super(mContext, mhelper, mData);
    }

    @Override
    public HomeBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_banner, parent, false);
        BannerViewHolder holder = new BannerViewHolder(view);
        return holder;
    }


    @Override
    public int getItemCount() {
        return (mData==null||mData.size()==0)?0:1;
    }

    // Banner
    public class BannerViewHolder extends HomeBaseViewHolder<BannerBean> {

        @BindView(R.id.banner)
        Banner banner;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.et_search)
        EditText etSearch;

        public BannerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void setData(List<BannerBean> t, int position) {
            BannerBean item = t.get(position);
            List<String> imageUrls = new ArrayList<>();
            final List<String> adUrls = new ArrayList<>();
            for (int i = 0; i < t.size(); i++) {
                imageUrls.add(item.getAdFile());
                adUrls.add(item.getAdTypeData());
            }
           banner.setImages(imageUrls).setImageLoader(new GlideImageLoader()).start();
           banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    //注意这里的position是从1开始的

                }
            });
        }
        @OnClick(R.id.et_search)
        public void onViewClicked() {
            Intent intent = new Intent(mContext, SearchActivity.class);
            mContext.startActivity(intent);
        }

    }
}
