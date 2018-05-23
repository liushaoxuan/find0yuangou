package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopListActivity;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by sxliu on 2018/5/20 19:20
 * E-mail Address 2587294424@qq.com
 */

public class HomeCatRecyclerAdapter extends RecyclerView.Adapter<HomeCatRecyclerAdapter.ViewHolder> {

    private List<HomeCatBean> mData;
    private LayoutInflater mInflater;
    private Context mContext;
    private int mIndex;//页数下标，表示第几页，从0开始
    private int mPagerSize;//每页显示的最大数量

    public HomeCatRecyclerAdapter(Context context, List<HomeCatBean> listData, int mIndex, int mPagerSize) {
        this.mContext = context;
        this.mData = listData;
        this.mIndex = mIndex;
        this.mPagerSize = mPagerSize;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_home_cat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setData(mData.get(position),  position);
    }

    @Override
    public int getItemCount() {
        return mData.size() > (mIndex + 1) * mPagerSize ? mPagerSize : (mData.size() - mIndex * mPagerSize);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private ImageView ivIcon;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_home_cat_name);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_home_cat_icon);
        }

        private void setData(final HomeCatBean bean,int position){
            tvName.setText(bean.getCatName());
            Glide.with(mContext).load(bean.getChannelIcon()).into(ivIcon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                ToastUtils.showShort(mContext, pos + "");
                    Intent intent = new Intent(mContext, FoodShopListActivity.class);
                    intent.putExtra("catId", bean.getCatId());
                    intent.putExtra("catName", bean.getCatName());
                    intent.putExtra("start_from", "sort");
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
