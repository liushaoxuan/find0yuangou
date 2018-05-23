package com.ahxd.lingyuangou.ui.home.model;

import com.ahxd.lingyuangou.bean.ArticleBean;
import com.ahxd.lingyuangou.callback.MaoJsonCallback;
import com.ahxd.lingyuangou.callback.MaoStringCallback;
import com.ahxd.lingyuangou.callback.MaoResponse;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.contract.IArticleListContract;
import com.ahxd.lingyuangou.utils.L;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Mao zhendong on 2018/1/12.
 */

public class ArticleModel implements IArticleListContract.IArticleListModel {

    @Override
    public void getArticleList(String catId, int page, final IArticleCallback callback) {
        Type type = new TypeToken<MaoResponse<List<ArticleBean>>>() {}.getType();
        OkGo.<MaoResponse<List<ArticleBean>>>post(HostUrl.URL_ARTICLE_LIST)
                .params("catId", catId)
                .params("page", page)
                .execute(new MaoJsonCallback<MaoResponse<List<ArticleBean>>>(type, callback) {
                    @Override
                    public void onSuccess(Response<MaoResponse<List<ArticleBean>>> response) {
                        callback.onArticleList(response.body().data);
                    }
                });
    }

    @Override
    public void getArticleDetail(String articleId, final IArticleCallback callback) {
        OkGo.<String>post(HostUrl.URL_ARTICLE_DETAIL)
                .params("articleId", articleId)
                .execute(new MaoStringCallback(callback) {
                    @Override
                    protected void onSuccess(JSONObject root) {
                        L.e(root.toString());
                        callback.onArticleDetail(root.optJSONObject(Constant.RESP_DATA));
                    }
                });
    }
}
