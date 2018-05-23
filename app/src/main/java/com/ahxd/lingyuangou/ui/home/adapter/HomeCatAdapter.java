package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.ui.home.activity.FoodShopListActivity;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * GridView加载数据adapter
 */
public class HomeCatAdapter extends BaseAdapter {

    private List<HomeCatBean> mData;
    private LayoutInflater mInflater;
    private Context mContext;
    private int mIndex;//页数下标，表示第几页，从0开始
    private int mPagerSize;//每页显示的最大数量

    public HomeCatAdapter(Context context, List<HomeCatBean> listData, int mIndex, int mPagerSize) {
        this.mContext = context;
        this.mData = listData;
        this.mIndex = mIndex;
        this.mPagerSize = mPagerSize;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 先判断数据集的大小是否足够显示满本页？mData.size() > (mIndex + 1)*mPagerSize
     * 如果满足，则此页就显示最大数量mPagerSize的个数
     * 如果不够显示每页的最大数量，那么剩下几个就显示几个 (mData.size() - mIndex*mPagerSize)
     */
    @Override
    public int getCount() {
        return mData.size() > (mIndex + 1) * mPagerSize ? mPagerSize : (mData.size() - mIndex * mPagerSize);
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position + mIndex * mPagerSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPagerSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_home_cat, parent, false);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_home_cat_name);
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_home_cat_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //重新确定position（因为拿到的是总的数据源，数据源是分页加载到每页的GridView上的，为了确保能正确的点对不同页上的item）
        //假设mPagerSize=8，假如点击的是第二页（即mIndex=1）上的第二个位置item(position=1),那么这个item的实际位置就是pos=9
        final int pos = position + mIndex * mPagerSize;
        final HomeCatBean bean = mData.get(pos);
        holder.tvName.setText(bean.getCatName());
        Glide.with(mContext).load(bean.getChannelIcon()).into(holder.ivIcon);
        convertView.setOnClickListener(new View.OnClickListener() {
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
        return convertView;
    }

    private class ViewHolder {
        private TextView tvName;
        private ImageView ivIcon;
    }
}
