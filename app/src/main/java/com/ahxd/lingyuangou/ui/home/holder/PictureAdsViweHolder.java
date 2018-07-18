package com.ahxd.lingyuangou.ui.home.holder;

import android.view.View;
import android.widget.ImageView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.AdsBean;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sxliu on 2018/6/28 1:34
 * E-mail Address 2587294424@qq.com
 * 图片广告
 */

public class PictureAdsViweHolder extends HomeBaseViewHolder<AdsBean> {
    @BindView(R.id.iv_home_ad)
    ImageView ivHomeAd;
    public PictureAdsViweHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(List<AdsBean> t, int position) {
         if (t.size()>0){
             AdsBean item = t.get(0) ;
             Glide.with(mContext).load(item.getAdFile()).into(ivHomeAd);
             ivHomeAd.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     ToastUtils.showShort(mContext, "click ad!");
                 }
             });
         }

    }
}
