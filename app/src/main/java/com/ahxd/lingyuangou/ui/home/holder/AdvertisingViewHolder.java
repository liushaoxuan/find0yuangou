package com.ahxd.lingyuangou.ui.home.holder;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.ArticlesBean;
import com.ahxd.lingyuangou.ui.home.activity.ArticleListActivity;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sxliu on 2018/6/28 0:21
 * E-mail Address 2587294424@qq.com
 */

public class AdvertisingViewHolder extends HomeBaseViewHolder<ArticlesBean> {
    private static String TAG = AdvertisingViewHolder.class.getSimpleName();
    @BindView(R.id.vf_messages)
    ViewFlipper vfMessages;
    @BindView(R.id.iv_home_article_right)
    ImageView ivHomeArticleRight;
    public AdvertisingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.iv_home_article_right)
    public void onViewClick(View v) {
        Intent intent = new Intent(mContext, ArticleListActivity.class);
        mContext.startActivity(intent);
    }

    public void setData(List<ArticlesBean> t, int position){

        String list = JSON.toJSONString(t);
        JSONArray mArticleData = null;
        try {
            mArticleData = new JSONArray(list);
        } catch (JSONException e) {
            Log.e(TAG,e.getMessage());
        }
        vfMessages.removeAllViews();
        if (mArticleData.length() % 2 == 0) {
            for (int i = 0; i < mArticleData.length(); i = i + 2) {
                final JSONObject data = mArticleData.optJSONObject(i);
                final JSONObject data1 = mArticleData.optJSONObject(i + 1);
                View view = View.inflate(mContext, R.layout.layout_home_message_item, null);
                ((TextView) view.findViewById(R.id.tv_message1)).setText(data.optString("articleTitle"));
                ((TextView) view.findViewById(R.id.tv_message2)).setText(data1.optString("articleTitle"));
                ((TextView) view.findViewById(R.id.tv_message1)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(mContext, data.optString("articleTitle"));
                    }
                });
                ((TextView) view.findViewById(R.id.tv_message2)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(mContext, data1.optString("articleTitle"));
                    }
                });
                 vfMessages.addView(view);
            }
        } else {
            for (int i = 0; i < mArticleData.length(); i = i + 2) {
                final JSONObject data = mArticleData.optJSONObject(i);
                final JSONObject data1;
                View view = View.inflate(mContext, R.layout.layout_home_message_item, null);
                if (i != (mArticleData.length() - 1)) {
                    data1 = mArticleData.optJSONObject(i + 1);
                    ((TextView) view.findViewById(R.id.tv_message2)).setText(data1.optString("articleTitle"));
                    ((TextView) view.findViewById(R.id.tv_message2)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtils.showShort(mContext, data1.optString("articleTitle"));
                        }
                    });
                }
                ((TextView) view.findViewById(R.id.tv_message1)).setText(data.optString("articleTitle"));
                ((TextView) view.findViewById(R.id.tv_message1)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(mContext, data.optString("articleTitle"));
                    }
                });
                 vfMessages.addView(view);
            }
        }
    }
}
