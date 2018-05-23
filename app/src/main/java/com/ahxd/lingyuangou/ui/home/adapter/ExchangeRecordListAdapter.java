package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.CashdrewsBean;
import com.ahxd.lingyuangou.bean.ScoreBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/11.
 */

public class ExchangeRecordListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ScoreBean> mData;

    public ExchangeRecordListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<ScoreBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_record_list, null);
        return new ExchangeRecordListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ExchangeRecordListAdapter.ViewHolder) holder).bindOrderViewHolder(mData.get(position));
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

        @BindView(R.id.tv_record_list_time)
        TextView tvRecordListTime;
        @BindView(R.id.tv_record_list_money)
        TextView tvRecordListMoney;
        @BindView(R.id.tv_record_list_type)
        TextView tvRecordListType;
        @BindView(R.id.tv_record_list_status)
        TextView tvRecordListStatus;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindOrderViewHolder(final ScoreBean bean) {
            if (null != bean) {
                tvRecordListType.setVisibility(View.INVISIBLE);
                tvRecordListTime.setText(bean.getCreateTime());
                tvRecordListMoney.setText(""+bean.getDataRemarks());
                tvRecordListStatus.setText("-"+bean.getScore());

            }
        }
    }
}