package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.MarketingBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.mine.adapter.MarketingRevenueRecordListAdapter;
import com.ahxd.lingyuangou.ui.mine.adapter.PresentRecordListAdapter;
import com.ahxd.lingyuangou.ui.mine.contract.IMarketingContract;
import com.ahxd.lingyuangou.ui.mine.contract.IPresentRecordContract;
import com.ahxd.lingyuangou.ui.mine.presenter.MarketingListPresenter;
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
public class MarketingRevenueRecordActivity extends BaseActivity implements IMarketingContract.IMarketingListView {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_list_container)
    SmartRefreshLayout srlListContainer;
    private int mPage = 1;
    private List<MarketingBean> mMarketingList = new ArrayList<>();
    private MarketingRevenueRecordListAdapter mAdapter;
    private MarketingListPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("营销收益记录");
        mAdapter = new MarketingRevenueRecordListAdapter(this);
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
                mMarketingList.clear();
                loadData();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter = new MarketingListPresenter(this);
        loadData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_present_record_list;
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
    public void showMarketingList(List<MarketingBean> list) {
        srlListContainer.finishRefresh();
        srlListContainer.finishLoadmore();
        if (null != list) {
            mMarketingList.addAll(list);
            mAdapter.setData(mMarketingList);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_EDIT_ADDRESS && resultCode == RESULT_OK) {
            mPage = 1;
            mMarketingList.clear();
            mPresenter.getMarketingList(mPage);
        }
    }
    private void loadData() {
        mPresenter.getMarketingList(mPage);
    }

}
