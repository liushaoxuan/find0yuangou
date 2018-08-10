package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.AddressBean;
import com.ahxd.lingyuangou.listener.OnEditAddressListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/11.
 */

public class AddressListAdapter extends BaseAdapter {

    private Context mContext;
    private OnEditAddressListener mListener;
    private List<AddressBean> mData;

    public AddressListAdapter(Context context, OnEditAddressListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    public void setData(List<AddressBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    public AddressBean getData() {
        if (null != mData) {
            for (int i = 0; i < mData.size();i++) {
                if (mData.get(i).getIsDefault() == 1) {
                    return mData.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        if (null == mData || mData.isEmpty()) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_address_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AddressBean bean = mData.get(position);
        holder.tvMineAddressReceiver.setText(bean.getUserName());
        holder.tvMineAddressPhone.setText(bean.getUserPhone());
        holder.tvMineAddressDetail.setText(String.format("%s%s%s%s", bean.getProvince(), bean.getCity(),
                bean.getArea(), bean.getUserAddress()));
        if (bean.getIsDefault() == 1) {
            holder.tvMineAddressCheck.setImageResource(R.mipmap.ic_cart_rb_cart_check);
            holder.llAddressItemContainer.setBackgroundColor(mContext.getResources().getColor(R.color.color_theme));
        } else {
            holder.tvMineAddressCheck.setImageResource(R.mipmap.ic_cart_rb_cart_normal);
            holder.llAddressItemContainer.setBackgroundColor(mContext.getResources().getColor(R.color.color_bg));
        }

        holder.tvMineAddressEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onEditAddress(bean);
                }
            }
        });

//        holder.tvMineAddressCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    for (int i = 0; i < mData.size(); i++) {
//                        if (i == position) {
//                            if (bean.getIsDefault() == 1) {
//                                bean.setIsDefault(0);
//                            } else {
//                                bean.setIsDefault(1);
//                            }
//                        } else {
//                            mData.get(i).setIsDefault(0);
//                        }
//                    }
//                    mListener.onSetDefault(bean);
//                }
//            }
//        });

        holder.tvMineAddressDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onDeleteAddress(bean);
                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_mine_address_receiver)
        TextView tvMineAddressReceiver;
        @BindView(R.id.tv_mine_address_phone)
        TextView tvMineAddressPhone;
        @BindView(R.id.tv_mine_address_detail)
        TextView tvMineAddressDetail;
        @BindView(R.id.tv_mine_address_delete)
        TextView tvMineAddressDelete;
        @BindView(R.id.tv_mine_address_edit)
        TextView tvMineAddressEdit;
        @BindView(R.id.tv_mine_address_check)
        ImageView tvMineAddressCheck;
        @BindView(R.id.ll_address_item_container)
        LinearLayout llAddressItemContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
