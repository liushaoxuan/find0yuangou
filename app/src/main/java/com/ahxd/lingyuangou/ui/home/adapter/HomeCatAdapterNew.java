package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopListActivity;
import com.ahxd.lingyuangou.utils.DeviceUtils;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * GridView加载数据adapter
 */
public class HomeCatAdapterNew extends RecyclerView.Adapter<HomeCatAdapterNew.ViewHolder> {

    private List<HomeCatBean> mData;
    private LayoutInflater mInflater;
    private Context mContext;
    private int mIndex;//页数下标，表示第几页，从0开始
    private int mPagerSize;//每页显示的最大数量

    public HomeCatAdapterNew(Context context, List<HomeCatBean> listData, int mIndex, int mPagerSize) {
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
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return mData.size() > (mIndex + 1) * mPagerSize ? mPagerSize : (mData.size() - mIndex * mPagerSize);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_home_cat_name);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_home_cat_icon);
            ViewGroup.LayoutParams params =  itemView.getLayoutParams();
            params.width = DeviceUtils.getDeviceWidth(mContext) / 4;
            itemView.requestLayout();
        }

        private void setData(int position) {
            //重新确定position（因为拿到的是总的数据源，数据源是分页加载到每页的GridView上的，为了确保能正确的点对不同页上的item）
            //假设mPagerSize=8，假如点击的是第二页（即mIndex=1）上的第二个位置item(position=1),那么这个item的实际位置就是pos=9
            final int pos = position + mIndex * mPagerSize;
            final HomeCatBean bean = mData.get(pos);
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
