package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.OrderBean;
import com.ahxd.lingyuangou.bean.RecommendBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.ui.mine.activity.OrderDetailActivity;
import com.ahxd.lingyuangou.widget.OrderGoodView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mao on 2018/1/14.
 */

public class AdvertisingRecordListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<RecommendBean> mData;

    public AdvertisingRecordListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<RecommendBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_advertising_record_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindOrderViewHolder(mData.get(position));
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

        @BindView(R.id.tv_username)
        TextView tvUserName;
        @BindView(R.id.tv_createtime)
        TextView tvCreateTime;
        @BindView(R.id.tv_dataremarks)
        TextView tvDataRemarks;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        public void bindOrderViewHolder(final RecommendBean bean) {
            if (null != bean) {
                tvUserName.setText("会员"+bean.getUserName());
                tvCreateTime.setText(bean.getCreateTime());
                tvDataRemarks.setText(bean.getDataRemarks());
                tvNumber.setText("+"+bean.getNumber()+"元");
            }
        }
    }
}
