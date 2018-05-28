package com.ahxd.lingyuangou.ui.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by sxliu on 2018/5/19 14:40
 * E-mail Address 2587294424@qq.com
 */

public class DialogChoseCityAdapter extends RecyclerView.Adapter<DialogChoseCityAdapter.DialogViewHolder> {

    private List<String> datas;
    private LayoutInflater  inflater;
    private Context mContext;
    private OnItemClickListener listener;
    private int index = -1;

    public DialogChoseCityAdapter(Context mContext,List<String> datas) {
        this.datas = datas;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public DialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.from(mContext).inflate(R.layout.item_city_dialog,parent,false);
        return new DialogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DialogViewHolder holder, int position) {
            holder.setData(datas,position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class DialogViewHolder extends RecyclerView.ViewHolder{
        TextView tvCity;

        public DialogViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tv_city);
        }

        private void setData(List<String> data, final int position){
            tvCity.setText(data.get(position));
            if (index==position){
                itemView.setBackgroundColor(mContext.getResources().getColor(R.color.pickerview_timebtn_nor));
            }else {
                itemView.setBackgroundColor(Color.parseColor("#66000000"));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null){
                        listener.onItemClick(itemView,position);
                    }
                    index = position;
                    notifyDataSetChanged();
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
