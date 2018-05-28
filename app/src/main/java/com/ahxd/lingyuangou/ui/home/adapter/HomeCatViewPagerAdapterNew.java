package com.ahxd.lingyuangou.ui.home.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.widget.RecyclerGridView;

import java.util.List;

/**
 * ViewPager的adapter
 */
public class HomeCatViewPagerAdapterNew extends PagerAdapter {

    private List<RecyclerView> viewLists;

    public HomeCatViewPagerAdapterNew(List<RecyclerView> viewLists) {
        this.viewLists = viewLists;
        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }

    @Override
    public int getCount() {
        return viewLists != null ? viewLists.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;//官方文档要求这样写
    }
}
