package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.ArticleBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public interface IArticleListContract {

    public interface IArticleListView extends BaseView {
        public void showArticleList(List<ArticleBean> list);
    }

    public interface IArticleListPresenter {
        // 获取文章列表
        public void getArticleList(String catId, int page);
    }

    public interface IArticleListModel {
        // 获取文章列表
        public void getArticleList(String catId, int page, IArticleCallback callback);
        // 获取文章内容
        public void getArticleDetail(String articleId, IArticleCallback callback);

        public abstract class IArticleCallback extends ModelCallback {

            public IArticleCallback(BaseView view) {
                super(view);
            }

            public void onArticleList(List<ArticleBean> list) {}

            public void onArticleDetail(JSONObject data) {}
        }

    }

}
