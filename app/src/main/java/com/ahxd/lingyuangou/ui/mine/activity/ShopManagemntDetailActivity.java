package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.MarketingBean;
import com.ahxd.lingyuangou.bean.ShopManagemntDetailBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.home.activity.RechargeActivity;
import com.ahxd.lingyuangou.ui.mine.adapter.ShopManagementDetailListAdapter;
import com.ahxd.lingyuangou.ui.mine.contract.IShopManagemntDetailContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.presenter.ShopManagemntDetailPresenter;
import com.ahxd.lingyuangou.ui.mine.presenter.WalletPresenter;
import com.ahxd.lingyuangou.utils.GlideApp;
import com.ahxd.lingyuangou.widget.PicTextRightItem;
import com.ahxd.lingyuangou.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wpc on 2018/1/16.
 */

public class ShopManagemntDetailActivity extends BaseActivity implements IShopManagemntDetailContract.IShopManagemntDetailView {


    @BindView(R.id.im_logo)
    ImageView imLogo;
    @BindView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.tv_body)
    TextView tvBody;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_list_container)
    SmartRefreshLayout srlListContainer;

    private int mPage = 1;
    private List<ShopManagemntDetailBean.DetailLog> mList = new ArrayList<>();
    private ShopManagemntDetailPresenter mPresenter;
    private ShopManagementDetailListAdapter mAdapter;
    private ShopManagemntDetailBean bean;
    private String shopId;
    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("营销管理");
        shopId=getIntent().getStringExtra("shopId");
        mAdapter=new ShopManagementDetailListAdapter(this);
        rvList.setAdapter(mAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL,
                2, getResources().getColor(R.color.color_bg)));
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
        mPresenter = new ShopManagemntDetailPresenter(this);
        loadData();

    }

    private void loadData() {
        mPresenter.getShopManagemntDetail(shopId,mPage);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_EDIT_ADDRESS && resultCode == RESULT_OK) {
            mPage = 1;
            mList.clear();
            mPresenter.getShopManagemntDetail(shopId,mPage);
        }
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_shop_list_detail;
    }
    @Override
    public void showShopManagemntDetail(ShopManagemntDetailBean bean) {
        if (null != bean) {
            this.bean=bean;
            int a=this.mPage;
            tvBody.setText(this.bean.getIncome()+"元");
            tvDetailTitle.setText(this.bean.getShopName());
            GlideApp.with(this).load(HostUrl.HOST+bean.getShopImg()).into(imLogo);
            srlListContainer.finishRefresh();
            srlListContainer.finishLoadmore();
            if (null != bean.getLog()) {
                mList.addAll(bean.getLog());
                mAdapter.setData(mList);
            }
        }
    }
}
