package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.IntegralBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.mine.adapter.IntegralListAdapter;
import com.ahxd.lingyuangou.ui.mine.adapter.RecordListAdapter;
import com.ahxd.lingyuangou.ui.mine.contract.IRecordContract;
import com.ahxd.lingyuangou.ui.mine.presenter.RecordListPresenter;
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
public class IntegralRecordActivity extends BaseActivity implements IRecordContract.IRecordListView {

    @BindView(R.id.rv_balance_recharge_record_list)
    RecyclerView rvList;
    @BindView(R.id.srl_balance_recharge_record_list_container)
    SmartRefreshLayout srlListContainer;
    private int mPage = 1;
    private List<IntegralBean> mRecordList = new ArrayList<>();
    private IntegralListAdapter mAdapter;
    private RecordListPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("积分记录");
        mAdapter = new IntegralListAdapter(this);
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
                mRecordList.clear();
                loadData();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter = new RecordListPresenter(this);
        loadData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_balance_recharge_record_list;
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
    public void showRecordList(List<RecordBean> list) {

    }

    @Override
    public void showIntegralList(List<IntegralBean> list) {
        srlListContainer.finishRefresh();
        srlListContainer.finishLoadmore();
        if (null != list) {
            mRecordList.addAll(list);
            mAdapter.setData(mRecordList);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_EDIT_ADDRESS && resultCode == RESULT_OK) {
            mPage = 1;
            mRecordList.clear();
            mPresenter.getIntegralList(mPage);
        }
    }
    private void loadData() {
        mPresenter.getIntegralList(mPage);
    }

}
