package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.MarketingBean;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/11.
 */

public class MarketingRevenueRecordListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<MarketingBean> mData;

    public MarketingRevenueRecordListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<MarketingBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_consumption_record_list, null);
        return new MarketingRevenueRecordListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MarketingRevenueRecordListAdapter.ViewHolder) holder).bindOrderViewHolder(mData.get(position));
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

        @BindView(R.id.tv_createtime)
        TextView tvCreatetime;
        @BindView(R.id.tv_shopname)
        TextView tvShopname;
        @BindView(R.id.tv_dataremarks)
        TextView tvDataremarks;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.im_shopimg)
        ImageView tvshopImg;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindOrderViewHolder(final MarketingBean bean) {
            if (null != bean) {
                tvCreatetime.setText(bean.getCreateTime());
                tvShopname.setText(bean.getShopName());
                tvDataremarks.setText(bean.getDataRemarks());
                tvMoney.setText("+"+bean.getNumber());
                Glide.with(mContext).load(HostUrl.HOST+bean.getShopImg()).into(tvshopImg);
            }
        }
    }
}