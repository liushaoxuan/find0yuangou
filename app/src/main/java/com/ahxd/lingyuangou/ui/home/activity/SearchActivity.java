package com.ahxd.lingyuangou.ui.home.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.SearchHistoryBean;
import com.ahxd.lingyuangou.database.SearchHistoryManager;
import com.ahxd.lingyuangou.utils.SoftInputUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mao on 2018/1/15.
 */

public class SearchActivity extends BaseActivity {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.iv_search_history_delete)
    ImageView ivSearchHistoryDelete;
    @BindView(R.id.fl_search_history_container)
    FlowLayout flSearchHistoryContainer;
    @BindView(R.id.iv_search_del)
    ImageView ivSearchDel;

    private static SearchHistoryManager mDBManager;
    private List<SearchHistoryBean> mSearchHistories = new ArrayList<>();

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("搜索");
    }

    @Override
    protected void initListener() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etSearch.getText().toString().trim().length() > 0) {
                    ivSearchDel.setVisibility(View.VISIBLE);
                } else {
                    ivSearchDel.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        mDBManager = SearchHistoryManager.getInstance(this);
        showFlowData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @OnClick({R.id.tv_search, R.id.iv_search_history_delete, R.id.iv_search_del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                SoftInputUtils.hideSoftInput(this, getWindow().getDecorView());
                searchKeyword();
                if (validate()) {
                    startSearchList(etSearch.getText().toString().trim());
                }
                break;
            case R.id.iv_search_history_delete:
                SoftInputUtils.hideSoftInput(this, getWindow().getDecorView());
                deleteHistory();
                break;
            case R.id.iv_search_del:
                etSearch.setText(null);
                ivSearchDel.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void showFlowData() {
        flSearchHistoryContainer.removeAllViews();
        mSearchHistories = mDBManager.getAllHistory();
        for (int i = 0; i < mSearchHistories.size(); i++) {
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.view_search_flow_item,
                    flSearchHistoryContainer, false);
            tv.setText(mSearchHistories.get(i).getContent());
            final String str = tv.getText().toString();
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startSearchList(str);
                }
            });
            flSearchHistoryContainer.addView(tv);
        }
    }

    private void deleteHistory() {
        mDBManager.deleteAllHistory();
        flSearchHistoryContainer.removeAllViews();
        showFlowData();
    }

    private void searchKeyword() {
        String keyword = etSearch.getText().toString().trim();
        if (!TextUtils.isEmpty(keyword) && !isContainHistory(keyword)) {
            mDBManager.addHistory(keyword);
            showFlowData();
        }
    }

    private boolean isContainHistory(String content) {
        for (int i = 0; i < mSearchHistories.size(); i++) {
            if (mSearchHistories.get(i).getContent().equals(content)) {
                return true;
            }
        }
        return false;
    }

    private boolean validate() {
        if (TextUtils.isEmpty(etSearch.getText().toString().trim())) {
            ToastUtils.showShort(this, etSearch.getHint());
            return false;
        }
        return true;
    }

    private void startSearchList(String keywords) {
        Intent intent = new Intent(this, SearchListActivity.class);
        intent.putExtra("keywords", keywords);
        startActivity(intent);
    }

}
