package com.ahxd.lingyuangou.ui.home.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.BusinessCardBean;
import com.ahxd.lingyuangou.utils.GlideApp;

import java.util.List;

import butterknife.BindView;

/**
 * Created by sxliu on 2018/7/11 20:36
 * E-mail Address 2587294424@qq.com
 * 购买商家卡的card ViewHolder
 */

public class PurchaseQualificatioCardViewHolder extends mBaseViewHolder<BusinessCardBean> {
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_card_name)
    TextView tvCardName;
    @BindView(R.id.tv_card_price)
    TextView tvCardPrice;

    public PurchaseQualificatioCardViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(List<BusinessCardBean> data, int position) {
        BusinessCardBean item = data.get(position);
        GlideApp.with(mContext).load(item.isIsselected()?R.mipmap.ic_cart_rb_cart_check:R.mipmap.ic_cart_rb_cart_normal).into(ivIcon);
        tvCardName.setText(item.getMemberCardName());
        tvCardPrice.setText(item.getNeedCash()+"");

    }
}
