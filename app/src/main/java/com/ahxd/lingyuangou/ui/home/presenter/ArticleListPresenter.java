package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.ArticleBean;
import com.ahxd.lingyuangou.ui.home.contract.IArticleListContract;
import com.ahxd.lingyuangou.ui.home.model.ArticleModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class ArticleListPresenter implements IArticleListContract.IArticleListPresenter {

    private IArticleListContract.IArticleListView mView;
    private ArticleModel mModel;

    public ArticleListPresenter(IArticleListContract.IArticleListView view) {
        this.mView = view;
        mModel = new ArticleModel();
    }

    @Override
    public void getArticleList(String catId, int page) {
        mModel.getArticleList(catId, page, new IArticleListContract.IArticleListModel.IArticleCallback(mView) {
            @Override
            public void onArticleList(List<ArticleBean> list) {
                mView.showArticleList(list);
            }
        });
    }
}
