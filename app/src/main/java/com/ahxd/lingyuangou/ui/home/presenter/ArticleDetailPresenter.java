package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.ui.home.contract.IArticleDetailContract;
import com.ahxd.lingyuangou.ui.home.contract.IArticleListContract;
import com.ahxd.lingyuangou.ui.home.model.ArticleModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/12.
 */

public class ArticleDetailPresenter implements IArticleDetailContract.IArticleDetailPresenter {

    public IArticleDetailContract.IArticleDetailView mView;
    public ArticleModel mModel;

    public ArticleDetailPresenter(IArticleDetailContract.IArticleDetailView view) {
        this.mView = view;
        mModel = new ArticleModel();
    }

    @Override
    public void getArticleDetail(String articleId) {
        mModel.getArticleDetail(articleId, new IArticleListContract.IArticleListModel.IArticleCallback(mView) {
            @Override
            public void onArticleDetail(JSONObject data) {
                mView.showArticleDetail(data);
            }
        });
    }
}
