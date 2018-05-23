package com.ahxd.lingyuangou.ui.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.ShopGoodBean;
import com.ahxd.lingyuangou.ui.home.adapter.GoodsListAdapter;
import com.ahxd.lingyuangou.ui.home.contract.IGoodsListContract;
import com.ahxd.lingyuangou.ui.home.presenter.GoodsListPresenter;
import com.ahxd.lingyuangou.utils.SoftInputUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/12.
 */

public class GoodsListActivity extends BaseActivity implements IGoodsListContract.IGoodsListView {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.iv_search_del)
    ImageView ivSearchDel;
    @BindView(R.id.rv_goods_list)
    RecyclerView rvGoodsList;
    @BindView(R.id.srl_goods_container)
    SmartRefreshLayout srlGoodsContainer;

    private GoodsListPresenter mPresenter;
    private GoodsListAdapter mAdapter;
    private int mPage = 1;
    private List<Object> mGoodList = new ArrayList<>();

    @Override
    protected void initView() {
        super.initView();
        mAdapter = new GoodsListAdapter(this);
        rvGoodsList.setAdapter(mAdapter);
        rvGoodsList.setLayoutManager(new LinearLayoutManager(this));
        rvGoodsList.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL,
                2, getResources().getColor(R.color.color_bg)));

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
    protected void initListener() {

        srlGoodsContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mGoodList.clear();
                mPresenter.getRecomGoodsList(mPage,etSearch.getText().toString().trim(),null, null);
            }
        });

        srlGoodsContainer.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                mPresenter.getRecomGoodsList(mPage,etSearch.getText().toString().trim(),null, null);
            }
        });

    }

    @Override
    protected void initData() {
        setToolBarTitle(getIntent().getStringExtra("titleName"));
        mPresenter = new GoodsListPresenter(this);
        mPresenter.getRecomGoodsList(mPage,etSearch.getText().toString().trim(),null, null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_list;
    }


    @OnClick({R.id.iv_search_del, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search_del:
                etSearch.setText(null);
                ivSearchDel.setVisibility(View.INVISIBLE);
                mPage = 1;
                mGoodList.clear();
                mPresenter.getRecomGoodsList(mPage,etSearch.getText().toString().trim(),null, null);
                break;
            case R.id.tv_search:
                if (validate()) {
                    SoftInputUtils.hideSoftInput(this, getWindow().getDecorView());
                    mPage = 1;
                    mGoodList.clear();
                    mAdapter.notifyDataSetChanged();
                    mPresenter.getRecomGoodsList(mPage,etSearch.getText().toString().trim(),null, null);
                }
        }
    }

    @Override
    public void showRecomGoodsList(List<ShopGoodBean> list) {
        if (null != list) {
            mGoodList.addAll(list);
            mAdapter.setData(mGoodList);
        }
    }
    private boolean validate() {
        if (TextUtils.isEmpty(etSearch.getText().toString().trim())) {
            ToastUtils.showShort(this, "请输入搜索关键词");
            return false;
        }
        return true;
    }
}
