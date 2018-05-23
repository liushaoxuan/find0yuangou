package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.MyMemberBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/11.
 */

public class MembershipIncomeListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<MyMemberBean> mData;

    public MembershipIncomeListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<MyMemberBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_consumption_record_list, null);
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mymember_list, null);
        return new MembershipIncomeListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MembershipIncomeListAdapter.ViewHolder) holder).bindOrderViewHolder(mData.get(position),position);
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

        @BindView(R.id.tv_serial_number)
        TextView tvSerialNumber;
        @BindView(R.id.tv_member)
        TextView tvMember;
        @BindView(R.id.tv_the_number_of)
        TextView tvTheNumberOf;
        @BindView(R.id.tv_rebate)
        TextView tvRebate;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        public void bindOrderViewHolder(final MyMemberBean bean,int i) {
            if (null != bean) {
                i+=1;
                tvSerialNumber.setText((i)+"");
                tvMember.setText(bean.getUserName());
                tvTheNumberOf.setText(bean.getLevel());
                tvRebate.setText(bean.getIncome());
            }
        }
    }
}
