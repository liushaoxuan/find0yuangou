package com.ahxd.lingyuangou.ui.home.holder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopListActivity;
import com.ahxd.lingyuangou.ui.home.activity.GoodsListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sxliu on 2018/6/30 0:57
 * E-mail Address 2587294424@qq.com
 */

public class LabelViewHolder extends HomeBaseViewHolder<String> {
    //分类图标名称
    @BindView(R.id.tv_home_label_name)
    public TextView label;
    //更多
    @BindView(R.id.tv_home_label_more)
    public TextView more;
    public LabelViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(List<String> t, int position) {
        label.setText("热门商品");
        label.setBackgroundResource(R.mipmap.icon_cat_10);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, GoodsListActivity.class);
                intent.putExtra("titleName", "推荐商品");
                mContext.startActivity(intent);
            }
        });
    }
}
