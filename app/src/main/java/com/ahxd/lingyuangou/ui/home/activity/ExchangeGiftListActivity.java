package com.ahxd.lingyuangou.ui.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.AccredBean;
import com.ahxd.lingyuangou.bean.CatBean;
import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.ui.home.adapter.ExchangeGiftListAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.FoodShopListAdapter;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeGiftListContract;
import com.ahxd.lingyuangou.ui.home.contract.IFoodContract;
import com.ahxd.lingyuangou.ui.home.presenter.ExchangeGiftListPresenter;
import com.ahxd.lingyuangou.ui.home.presenter.FoodPresenter;
import com.ahxd.lingyuangou.utils.SoftInputUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.ListTabView;
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
 * Created by Administrator on 2018/1/2.
 */

public class ExchangeGiftListActivity extends BaseActivity implements IExchangeGiftListContract.IExchangeGiftListView {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rv_food_list)
    RecyclerView rvFoodList;
    @BindView(R.id.srl_food)
    SmartRefreshLayout srlFood;
    @BindView(R.id.iv_search_del)
    ImageView ivSearchDel;
    @BindView(R.id.ll_shop_list_search)
    LinearLayout llShopListSearch;
//    @BindView(R.id.ll_shop_list_tabs)
//    LinearLayout llShopListTabs;
//    @BindView(R.id.ltv_one)
//    ListTabView ltvOne;
//    @BindView(R.id.ltv_two)
//    ListTabView ltvTwo;
//    @BindView(R.id.ltv_three)
//    ListTabView ltvThree;
//    @BindView(R.id.ltv_four)
//    ListTabView ltvFour;

    private ExchangeGiftListPresenter mPresenter;
    private ExchangeGiftListAdapter mAdapter;
    private List<Object> mFoodList = new ArrayList<>();

    private int mPage = 1;
    private String goodsName;
    private String goodsCatId;
    private String AccredId="";
    private String OrderId="1";
    private PopupWindow popupWindowOne;
    private PopupWindow popupWindowThree;
    private PopupWindow popupWindowFour;

    @Override
    protected void initView() {
        super.initView();
        mAdapter = new ExchangeGiftListAdapter(this);
        rvFoodList.setAdapter(mAdapter);
        rvFoodList.setLayoutManager(new LinearLayoutManager(this));

        rvFoodList.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL,
                1, getResources().getColor(R.color.color_bg)));
    }

    @Override
    protected void initListener() {
        srlFood.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                loadData();
            }
        });

        srlFood.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mFoodList.clear();
                loadData();
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
        setToolBarTitle(getIntent().getStringExtra("catName"));
        goodsName = getIntent().getStringExtra("goodsName");
        goodsCatId = getIntent().getStringExtra("goodsCatId");

//       mStartFrom = getIntent().getStringExtra("start_from");
        mPresenter = new ExchangeGiftListPresenter(this);
        mPresenter.getExchangeGiftList(mPage,goodsName,goodsCatId);
//        switch (mStartFrom) {
//            case "label":// 从home的label进入
//                llShopListTabs.setVisibility(View.GONE);
//                mPresenter.getRecomShopList(mPage, null, mCatId, null);
//                break;
//            case "sort":// 从home的分类进入
//                mPresenter.getFoodList(mPage, null, null, mCatId, null);
//                break;
//        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_giftfood_list;
    }

    @OnClick({R.id.iv_search_del, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search_del:
                etSearch.setText(null);
                ivSearchDel.setVisibility(View.INVISIBLE);
                mPage = 1;
                mFoodList.clear();
                loadData();
                break;
            case R.id.tv_search:
                if (validate()) {
                    SoftInputUtils.hideSoftInput(this, getWindow().getDecorView());
                    mPage = 1;
                    mFoodList.clear();
                    mAdapter.notifyDataSetChanged();
                    loadData();
                }
                break;
        }
    }


    private boolean validate() {
        if (TextUtils.isEmpty(etSearch.getText().toString().trim())) {
            ToastUtils.showShort(this, "请输入搜索关键词");
            return false;
        }
        return true;
    }

    private void loadData() {
        mPresenter.getExchangeGiftList(mPage,etSearch.getText().toString().trim(),goodsCatId);
//        switch (mStartFrom) {
//            case "label":
//                mPresenter.getRecomShopList(mPage, null, mCatId, etSearch.getText().toString().trim());
//                break;
//            case "sort":
//                mPresenter.getFoodList(mPage, null, null, mCatId, etSearch.getText().toString().trim());
//                break;
//        }
    }

    @Override
    public void showExchangeGiftList(List<ExchangeBean.ExchangeHots> list) {
        srlFood.finishRefresh();
        srlFood.finishLoadmore();
        if (list != null && !list.isEmpty()) {
            mFoodList.addAll(list);
            mAdapter.setData(mFoodList);
        }
    }

    @Override
    public void showCatsList(List<CatBean> result) {

    }

    @Override
    public void showAccredList(List<AccredBean> result) {

    }
}
