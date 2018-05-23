package com.ahxd.lingyuangou.ui.home.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.ArticleBean;
import com.ahxd.lingyuangou.listener.OnArticleItemListener;
import com.ahxd.lingyuangou.ui.home.adapter.ArticleListAdapter;
import com.ahxd.lingyuangou.ui.home.contract.IArticleListContract;
import com.ahxd.lingyuangou.ui.home.presenter.ArticleListPresenter;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Mao Zhendong on 2018/1/12.
 */

public class ArticleListActivity extends BaseActivity implements IArticleListContract.IArticleListView, OnArticleItemListener {

    @BindView(R.id.rv_article_list)
    RecyclerView rvArticleList;
    @BindView(R.id.srl_article_container)
    SmartRefreshLayout srlArticleContainer;

    private ArticleListPresenter mPresenter;
    private ArticleListAdapter mAdapter;

    private int mPage = 1;
    private List<ArticleBean> mArticles = new ArrayList<>();

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("商城公告");
        mAdapter = new ArticleListAdapter(this, this);
        rvArticleList.setAdapter(mAdapter);
        rvArticleList.setLayoutManager(new LinearLayoutManager(this));

        rvArticleList.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL,
                2, getResources().getColor(R.color.color_bg)));
    }

    @Override
    protected void initListener() {
        srlArticleContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mArticles.clear();
                mPresenter.getArticleList("1", mPage);
            }
        });

        srlArticleContainer.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                mPresenter.getArticleList("1", mPage);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter = new ArticleListPresenter(this);
        // 1 为商城公告
        mPresenter.getArticleList("1", mPage);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_list;
    }

    @Override
    public void showArticleList(List<ArticleBean> list) {
        srlArticleContainer.finishLoadmore();
        srlArticleContainer.finishRefresh();
        if (null != list) {
            mArticles.addAll(list);
            mAdapter.setData(mArticles);
        }
    }

    @Override
    public void showArticleDetail(ArticleBean bean) {
        if (null != bean) {
            Intent intent = new Intent(this, ArticleDetailActivity.class);
            intent.putExtra("articleId", bean.getArticleId());
            startActivity(intent);
        }
    }
}
