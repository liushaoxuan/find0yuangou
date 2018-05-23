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
import com.ahxd.lingyuangou.bean.MyMemberBean;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.listener.OnMarketingListener;
import com.ahxd.lingyuangou.utils.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/11.
 */

public class ShopManagementListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<MarketingBean> mData;
    private OnMarketingListener onMarketingListener;

    public ShopManagementListAdapter(Context context,OnMarketingListener onMarketingListener) {
        this.mContext = context;
        this.onMarketingListener = onMarketingListener;
    }

    public void setData(List<MarketingBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_consumption_record_list, null);
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shop_management_list, null);
        return new ShopManagementListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ShopManagementListAdapter.ViewHolder) holder).bindOrderViewHolder(mData.get(position),position);
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

        @BindView(R.id.im_logo)
        ImageView imLogo;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_body)
        TextView tvBody;
        @BindView(R.id.tv_come)
        TextView tvCome;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        public void bindOrderViewHolder(final MarketingBean bean, int i) {
            if (null != bean) {
                tvTitle.setText(bean.getShopName());
                tvBody.setText("为您带来总收益    "+bean.getIncomes()+"元");
                GlideApp.with(mContext).load(bean.getShopImg()).into(imLogo);
                tvCome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onMarketingListener.onCome(bean.getShopId());
                    }
                });

            }
        }
    }
}
