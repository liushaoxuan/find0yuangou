package com.ahxd.lingyuangou.ui.home.contract;

import com.ahxd.lingyuangou.base.BaseView;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/12.
 */

public interface IArticleDetailContract {

    public interface IArticleDetailView extends BaseView {
        public void showArticleDetail(JSONObject data);
    }

    public interface IArticleDetailPresenter {
        public void getArticleDetail(String articleId);
    }

}
