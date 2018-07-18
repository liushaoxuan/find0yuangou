package com.ahxd.lingyuangou.ui.home.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.ui.home.adapter.HomeCatAdapterNew;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sxliu on 2018/6/28 0:07
 * E-mail Address 2587294424@qq.com
 */

public class ClassificationViewHolder extends HomeBaseViewHolder<HomeCatBean> {
    @BindView(R.id.vp_points)
    LinearLayout points;
    @BindView(R.id.recyclerview_sort)
    RecyclerView recyclerView;

    public ClassificationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(List<HomeCatBean> t, int position) {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(mContext);
        layoutManager.setFlexDirection(FlexDirection.COLUMN_REVERSE);
        layoutManager.setJustifyContent(JustifyContent.FLEX_END);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new HomeCatAdapterNew(mContext, t));
    }
}
