package com.ahxd.lingyuangou.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.ArticleBean;
import com.ahxd.lingyuangou.listener.OnArticleItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/12.
 */

public class ArticleListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private OnArticleItemListener mListener;
    private List<ArticleBean> mData = new ArrayList<>();

    public ArticleListAdapter(Context context, OnArticleItemListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    public void setData(List<ArticleBean> list) {
        if (null != list) {
            this.mData = list;
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_article_list, parent, false);
        return new ArticleListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ArticleBean bean = mData.get(position);
        ((ArticleListViewHolder) holder).bindData(bean);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ArticleListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_article_item_title)
        TextView tvArticleItemTitle;
        @BindView(R.id.tv_article_item_time)
        TextView tvArticleItemTime;

        private ArticleBean mBean;

        public ArticleListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.showArticleDetail(mBean);
                    }
                }
            });
        }

        public void bindData(ArticleBean bean) {
            this.mBean = bean;
            tvArticleItemTime.setText(bean.getCreateTime());
            tvArticleItemTitle.setText(bean.getArticleTitle());
        }
    }

}
