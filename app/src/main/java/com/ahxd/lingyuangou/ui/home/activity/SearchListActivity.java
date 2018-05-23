package com.ahxd.lingyuangou.ui.home.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;
import com.ahxd.lingyuangou.ui.home.adapter.FoodShopListAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.GoodsListAdapter;
import com.ahxd.lingyuangou.ui.home.contract.ISearchContract;
import com.ahxd.lingyuangou.ui.home.presenter.SearchPresenter;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/17.
 */

public class SearchListActivity extends BaseActivity implements ISearchContract.ISearchView {

    @BindView(R.id.rb_search_list_tab_good)
    RadioButton rbSearchListTabGood;
    @BindView(R.id.rb_search_list_tab_shop)
    RadioButton rbSearchListTabShop;
    @BindView(R.id.rg_search_list_tab)
    RadioGroup rgSearchListTab;
    @BindView(R.id.rv_search_list)
    RecyclerView rvSearchList;
    @BindView(R.id.srl_search_container)
    SmartRefreshLayout srlSearchContainer;
    @BindView(R.id.iv_search_del)
    ImageView ivSearchDel;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;

    private String mKeywords;
    private int mType = 0; // type 0商品 1店铺
    private int mPage = 1;
    private SearchPresenter mPresenter;
    private GoodsListAdapter mGoodAdapter;
    private List<Object> mGoodList = new ArrayList<>();
    private FoodShopListAdapter mShopAdapter;
    private List<Object> mShopList = new ArrayList<>();

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("搜索结果");
        mGoodAdapter = new GoodsListAdapter(this);
        mShopAdapter = new FoodShopListAdapter(this);
        rvSearchList.setAdapter(mGoodAdapter);

        rvSearchList.setLayoutManager(new LinearLayoutManager(this));

        rvSearchList.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL,
                1, getResources().getColor(R.color.color_bg)));
        rbSearchListTabGood.setChecked(true);
    }

    @Override
    protected void initListener() {
        srlSearchContainer.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                mPresenter.searchKeywords(mType, mPage, etSearch.getText().toString().trim());
            }
        });

        srlSearchContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mGoodList.clear();
                mShopList.clear();
                mPresenter.searchKeywords(mType, mPage, etSearch.getText().toString().trim());
            }
        });

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
        mKeywords = getIntent().getStringExtra("keywords");
        etSearch.setText(mKeywords);
        mPresenter = new SearchPresenter(this);
        mPresenter.searchKeywords(mType, mPage, mKeywords);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_list;
    }

    @OnClick({R.id.rb_search_list_tab_good, R.id.rb_search_list_tab_shop, R.id.iv_search_del, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_search_list_tab_good:
                mType = 0;
                mPage = 1;
                mShopList.clear();
                mGoodList.clear();
                rvSearchList.setAdapter(mGoodAdapter);
                rbSearchListTabGood.setChecked(true);
                if (validate()) {
                    mPresenter.searchKeywords(mType, mPage, etSearch.getText().toString().trim());
                }
                break;
            case R.id.rb_search_list_tab_shop:
                mType = 1;
                mPage = 1;
                mShopList.clear();
                mGoodList.clear();
                rvSearchList.setAdapter(mShopAdapter);
                rbSearchListTabShop.setChecked(true);
                if (validate()) {
                    mPresenter.searchKeywords(mType, mPage, etSearch.getText().toString().trim());
                }
                break;
            case R.id.iv_search_del:
                etSearch.setText(null);
                ivSearchDel.setVisibility(View.INVISIBLE);
                mPage = 1;
                mShopList.clear();
                mGoodList.clear();
                mShopAdapter.notifyDataSetChanged();
                mGoodAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_search:
                if (validate()) {
                    SoftInputUtils.hideSoftInput(this, getWindow().getDecorView());
                    mPage = 1;
                    mShopList.clear();
                    mGoodList.clear();
                    mGoodAdapter.notifyDataSetChanged();
                    mShopAdapter.notifyDataSetChanged();
                    mPresenter.searchKeywords(mType, mPage, etSearch.getText().toString().trim());
                }
                break;
        }
    }

    @Override
    public void showSearchGood(List<ShopGoodBean> list) {
        srlSearchContainer.finishLoadmore();
        srlSearchContainer.finishRefresh();
        if (null != list) {
            mGoodList.addAll(list);
            mGoodAdapter.setData(mGoodList);
        }
    }

    @Override
    public void showSearchShop(List<FoodShopBean> list) {
        srlSearchContainer.finishLoadmore();
        srlSearchContainer.finishRefresh();
        if (null != list) {
            mShopList.addAll(list);
            mShopAdapter.setData(mShopList);
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
