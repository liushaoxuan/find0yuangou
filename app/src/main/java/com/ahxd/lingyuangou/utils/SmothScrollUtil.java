package com.ahxd.lingyuangou.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by sxliu on 2018/7/8 22:23
 * E-mail Address 2587294424@qq.com
 */

public class SmothScrollUtil {

    //解决嵌套滑动不流畅问题LinearLayout
    public static void ScrollSmooth_LinearLayout(Context context,RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    //解决嵌套滑动不流畅问题 GrideLayout
    public static void ScrollSmooth_GrideLayout(Context context,RecyclerView recyclerView, int column) {
        GridLayoutManager layoutManager = new GridLayoutManager(context, column);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }
}
