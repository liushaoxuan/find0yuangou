package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.MessageBean;
import com.ahxd.lingyuangou.bean.ScoreBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/11.
 */

public class MessageListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<MessageBean> mData;

    public MessageListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<MessageBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_message_list, null);
        return new MessageListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MessageListAdapter.ViewHolder) holder).bindOrderViewHolder(mData.get(position));
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

        @BindView(R.id.tv_message_time)
        TextView tvMessageTime;
        @BindView(R.id.v_message_new)
        View vMessageNew;
        @BindView(R.id.tv_message_body)
        TextView tvMessageBody;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindOrderViewHolder(final MessageBean bean) {
            if (null != bean) {
                if (bean.getMsgStatus().equals("0")){
                    vMessageNew.setVisibility(View.VISIBLE);
                }else {
                    vMessageNew.setVisibility(View.GONE);
                }
                tvMessageTime.setText(bean.getCreateTime());
                tvMessageBody.setText(bean.getMsgContent());
            }
        }
    }
}