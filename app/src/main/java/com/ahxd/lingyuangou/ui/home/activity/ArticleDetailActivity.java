package com.ahxd.lingyuangou.ui.home.activity;

import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.ui.home.contract.IArticleDetailContract;
import com.ahxd.lingyuangou.ui.home.presenter.ArticleDetailPresenter;
import com.ahxd.lingyuangou.widget.richtext.RichText;

import org.json.JSONObject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/12.
 */

public class ArticleDetailActivity extends BaseActivity implements IArticleDetailContract.IArticleDetailView {

    @BindView(R.id.tv_article_detail_title)
    TextView tvArticleDetailTitle;
    @BindView(R.id.tv_article_detail_time)
    TextView tvArticleDetailTime;
    @BindView(R.id.tv_article_detail_content)
    RichText tvArticleDetailContent;
    private ArticleDetailPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("公告详情");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter = new ArticleDetailPresenter(this);
        mPresenter.getArticleDetail(getIntent().getStringExtra("articleId"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    public void showArticleDetail(JSONObject data) {
        if (null != data) {
            tvArticleDetailTitle.setText(data.optString("articleTitle"));
            tvArticleDetailTime.setText(data.optString("createTime"));
            tvArticleDetailContent.setRichText(data.optString("articleContent"));
        }
    }

}
