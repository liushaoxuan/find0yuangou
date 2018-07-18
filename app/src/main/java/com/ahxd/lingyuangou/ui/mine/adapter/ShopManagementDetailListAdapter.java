package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.ShopManagemntDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/11.
 */

public class ShopManagementDetailListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ShopManagemntDetailBean.DetailLog> mData;

    public ShopManagementDetailListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<ShopManagemntDetailBean.DetailLog> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_consumption_record_list, null);
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shop_management_detail_list, null);
        return new ShopManagementDetailListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ShopManagementDetailListAdapter.ViewHolder) holder).bindOrderViewHolder(mData.get(position),position);
    }
    @Override
    public int getItemCount() {
        if (null == mData || mData.isEmpty()) {
            return 0;
        } else {
            return mData.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        public void bindOrderViewHolder(final ShopManagemntDetailBean.DetailLog bean, int i) {
            if (null != bean) {
                tvTitle.setText(bean.getDataRemarks());
                tvTime.setText(bean.getCreateTime());
                tvMoney.setText("+"+bean.getNumber()+"元");
            }
        }
    }
}
