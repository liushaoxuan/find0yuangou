package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.BusinessCardBean;
import com.ahxd.lingyuangou.bean.PurchaseQualificationBean;
import com.ahxd.lingyuangou.listener.OnChangeItemClickListener;
import com.ahxd.lingyuangou.listener.OnItemClickListener;
import com.ahxd.lingyuangou.ui.home.holder.PurchaseQualificatioCardViewHolder;
import com.ahxd.lingyuangou.ui.home.holder.mBaseViewHolder;

import java.util.List;

/**
 * Created by sxliu on 2018/7/11 20:42
 * E-mail Address 2587294424@qq.com
 */

public class PurchaseQualificatioCardAdapter extends mBaseAdapter<BusinessCardBean,PurchaseQualificatioCardViewHolder> {

    private OnChangeItemClickListener listener;
    private int lastposition = 0;
    public PurchaseQualificatioCardAdapter(Context mContext, List<BusinessCardBean> mdata) {
        super(mContext, mdata);
    }

    @Override
    public mBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_purchase_qualification_cards,parent,false);
        return new PurchaseQualificatioCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(mBaseViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null&& !mdata.get(position).isIsselected()){
                    listener.onItemClick(view,position,lastposition);
                    lastposition = position;
                }
            }
        });
    }

    public void setOnClickListener(OnChangeItemClickListener listener){
        this.listener = listener;
    }
}
