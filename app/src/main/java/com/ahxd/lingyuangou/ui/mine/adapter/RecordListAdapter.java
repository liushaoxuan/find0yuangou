package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.AddressBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.listener.OnEditAddressListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/11.
 */

public class RecordListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<RecordBean> mData;

    public RecordListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<RecordBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_record_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RecordListAdapter.ViewHolder) holder).bindOrderViewHolder(mData.get(position));
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
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindOrderViewHolder(final RecordBean bean) {
            if (null != bean) {
                tvRecordListTime.setText(bean.getEnd_time());
                tvRecordListMoney.setText("充值"+bean.getMoney()+"元");
                tvRecordListType.setVisibility(View.INVISIBLE);
            }
        }
    }
}
