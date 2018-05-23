package com.ahxd.lingyuangou.ui.home.activity;

import android.content.Intent;
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
import com.ahxd.lingyuangou.bean.GoodsBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.home.adapter.ShopGoodsListAdapter;
import com.ahxd.lingyuangou.ui.home.contract.IShopGoodsListContract;
import com.ahxd.lingyuangou.ui.home.presenter.ShopGoodsListPresenter;
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
 * Created by wpc on 2018/1/18.
 */
public class GiftGoodsListActivity extends BaseActivity implements IShopGoodsListContract.IShopGoodsListView {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.iv_search_del)
    ImageView ivSearchDel;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_list_container)
    SmartRefreshLayout srlListContainer;

    private int mPage = 1;
    private String shopId ;

    private List<GoodsBean> mList = new ArrayList<>();
    private ShopGoodsListAdapter mAdapter;
    private ShopGoodsListPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("热门商品");
        shopId=getIntent().getStringExtra("shopId");
        mAdapter = new ShopGoodsListAdapter(this);
        rvList.setAdapter(mAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL,
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
        srlListContainer.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                loadData();
            }
        });

        srlListContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mList.clear();
                loadData();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter = new ShopGoodsListPresenter(this);
        loadData();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_goods_list;
    }

    @OnClick({R.id.iv_search_del, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search_del:
                etSearch.setText(null);
                ivSearchDel.setVisibility(View.INVISIBLE);
                mPage = 1;
                mList.clear();
                mPresenter.getShopGoodsList(shopId,mPage,etSearch.getText().toString().trim());
                break;
            case R.id.tv_search:
                if (validate()) {
                    SoftInputUtils.hideSoftInput(this, getWindow().getDecorView());
                    mPage = 1;
                    mList.clear();
                    mAdapter.notifyDataSetChanged();
                    mPresenter.getShopGoodsList(shopId,mPage,etSearch.getText().toString().trim());
                }
        }
    }

//    @OnClick(R.id.btn_mine_address_add)
//    public void onViewClicked(View v) {
//        switch (v.getId()) {
//            case R.id.btn_mine_address_add:
//                Intent intent = new Intent(this, EditAddressActivity.class);
//                startActivityForResult(intent, Constant.REQ_EDIT_ADDRESS);
//                break;
//        }
//    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_EDIT_ADDRESS && resultCode == RESULT_OK) {
            mPage = 1;
            mList.clear();
            mPresenter.getShopGoodsList(shopId,mPage,"");
        }
    }

    private void loadData() {
        mPresenter.getShopGoodsList(shopId,mPage,"");
    }


    @Override
    public void showShopGoodsList(List<GoodsBean> list) {
        srlListContainer.finishRefresh();
        srlListContainer.finishLoadmore();
        if (null != list) {
            mList.addAll(list);
            mAdapter.setData(mList);
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
