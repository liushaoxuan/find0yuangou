package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.CashdrewsBean;
import com.ahxd.lingyuangou.bean.RecordBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/11.
 */

public class PresentRecordListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<CashdrewsBean> mData;

    public PresentRecordListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<CashdrewsBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_record_list, null);
        return new PresentRecordListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PresentRecordListAdapter.ViewHolder) holder).bindOrderViewHolder(mData.get(position));
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

        public void bindOrderViewHolder(final CashdrewsBean bean) {
            if (null != bean) {
                tvRecordListTime.setText(bean.getCreateTime());
                tvRecordListMoney.setText("充值"+bean.getMoney()+"元");
                tvRecordListType.setText(bean.getAccTargetName());
                if (bean.getCashSatus().equals("-1")){
                    tvRecordListStatus.setText("提现失败");
                }else if(bean.getCashSatus().equals("1")){
                    tvRecordListStatus.setText("提现成功");
                }else {
                    tvRecordListStatus.setText("审核中");
                }
            }
        }
    }
}