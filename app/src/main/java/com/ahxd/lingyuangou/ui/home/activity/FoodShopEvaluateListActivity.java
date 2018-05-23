package com.ahxd.lingyuangou.ui.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.FoodShopEvaluateBean;
import com.ahxd.lingyuangou.ui.home.adapter.FoodShopEvaluateListAdapter;
import com.ahxd.lingyuangou.ui.home.contract.IShopEvaluateContract;
import com.ahxd.lingyuangou.ui.home.presenter.ShopEvaluatePresenter;
import com.ahxd.lingyuangou.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/4.
 * 商铺评价列表
 */

public class FoodShopEvaluateListActivity extends BaseActivity implements IShopEvaluateContract.IShopEvaluateView {


    @BindView(R.id.rv_shop_evaluate)
    RecyclerView rvShopEvaluate;
    @BindView(R.id.srl_shop_evaluate_container)
    SmartRefreshLayout srlShopEvaluateContainer;

    // 商铺id
    private String mShopId;
    // 大于0时为获取商品评价
    private String mGoodsId;
    private int mPage = 1;
    private List<FoodShopEvaluateBean> mEvaluateList = new ArrayList<>();

    private FoodShopEvaluateListAdapter mAdapter;
    private ShopEvaluatePresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("评价列表");
        mAdapter = new FoodShopEvaluateListAdapter(this);
        rvShopEvaluate.setAdapter(mAdapter);
        rvShopEvaluate.setLayoutManager(new LinearLayoutManager(this));
        rvShopEvaluate.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL,
                1, getResources().getColor(R.color.color_bg)));
    }

    @Override
    protected void initListener() {
        srlShopEvaluateContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mEvaluateList.clear();
                mPresenter.getShopEvaluateList(mShopId, mGoodsId, mPage);
            }
        });

        srlShopEvaluateContainer.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                mPresenter.getShopEvaluateList(mShopId, mGoodsId, mPage);
            }
        });
    }

    @Override
    protected void initData() {
        mShopId = getIntent().getStringExtra("shopId");
        mGoodsId = getIntent().getStringExtra("goodsId");
        mPresenter = new ShopEvaluatePresenter(this);
        mPresenter.getShopEvaluateList(mShopId, mGoodsId, mPage);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_evaluate_list;
    }

    @Override
    public void showEvaluateList(List<FoodShopEvaluateBean> list) {
        srlShopEvaluateContainer.finishLoadmore();
        srlShopEvaluateContainer.finishRefresh();
        if (null != list) {
            mEvaluateList.addAll(list);
            mAdapter.setData(mEvaluateList);
        }
    }
}
