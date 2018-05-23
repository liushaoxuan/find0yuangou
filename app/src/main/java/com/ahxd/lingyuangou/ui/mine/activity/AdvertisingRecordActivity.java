package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.RecommendBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.mine.adapter.AdvertisingRecordListAdapter;
import com.ahxd.lingyuangou.ui.mine.contract.IAdvertisingRecordContract;
import com.ahxd.lingyuangou.ui.mine.contract.IPresentRecordContract;
import com.ahxd.lingyuangou.ui.mine.presenter.AdvertisingRecordListPresenter;
import com.ahxd.lingyuangou.ui.mine.presenter.PresentRecordListPresenter;
import com.ahxd.lingyuangou.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Mao Zhendong on 2018/1/11.
 */
public class AdvertisingRecordActivity extends BaseActivity implements IAdvertisingRecordContract.IAdvertisingRecordListView {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_list_container)
    SmartRefreshLayout srlListContainer;
    private List<RecommendBean> mRecommendList = new ArrayList<>();
    private AdvertisingRecordListAdapter mAdapter;
    private AdvertisingRecordListPresenter mPresenter;
    private int mPage = 1;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("收益记录");
        mAdapter = new AdvertisingRecordListAdapter(this);
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
                mRecommendList.clear();
                loadData();
            }
        });

    }

    @Override
    protected void initData() {
        mPresenter = new AdvertisingRecordListPresenter(this);
        loadData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_present_record_list;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_EDIT_ADDRESS && resultCode == RESULT_OK) {
            mPage = 1;
            mRecommendList.clear();
            mPresenter.getAdvertisingRecordList(mPage);
        }
    }

    private void loadData() {
        mPresenter.getAdvertisingRecordList(mPage);
    }

    @Override
    public void showAdvertisingRecordList(List<RecommendBean> list) {
        srlListContainer.finishRefresh();
        srlListContainer.finishLoadmore();
        if (null != list) {
            mRecommendList.addAll(list);
            mAdapter.setData(mRecommendList);
        }
    }
}
