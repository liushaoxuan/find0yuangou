package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.RechargeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/9.
 */

public class RechargeNumListAdapter extends BaseAdapter {

    private Context mContext;
    private List<RechargeBean> mData;
    private RechargeBean mRechargeBean;

    public RechargeNumListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<RechargeBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    public RechargeBean getData() {
        return mRechargeBean;
    }

    @Override
    public int getCount() {
        if (mData == null || mData.isEmpty()) {
            return 0;
        } else {
            return mData.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_recharge_num, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final RechargeBean bean = mData.get(position);
        holder.tvRechargeNum.setText(bean.getFieldName());
        if (!TextUtils.isEmpty(bean.getDescription())) {
            holder.tvRechargeDesc.setText(String.format("(%s)", bean.getDescription()));
        } else {
            holder.tvRechargeDesc.setText(null);
        }
        if (bean.isChecked()) {
            holder.ivRechargeCheck.setImageResource(R.mipmap.ic_cart_rb_cart_check);
        } else {
            holder.ivRechargeCheck.setImageResource(R.mipmap.ic_cart_rb_cart_normal);
        }
        holder.ivRechargeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRechargeBean = bean;
                bean.setChecked(true);
                for (int i = 0; i < mData.size(); i++) {
                    if (position != i) {
                        mData.get(i).setChecked(false);
                    }
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_recharge_num)
        TextView tvRechargeNum;
        @BindView(R.id.tv_recharge_desc)
        TextView tvRechargeDesc;
        @BindView(R.id.iv_recharge_check)
        ImageView ivRechargeCheck;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
