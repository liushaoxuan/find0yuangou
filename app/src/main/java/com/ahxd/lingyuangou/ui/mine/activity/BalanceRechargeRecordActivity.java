package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.AddressBean;
import com.ahxd.lingyuangou.bean.IntegralBean;
import com.ahxd.lingyuangou.bean.OrderBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.listener.OnEditAddressListener;
import com.ahxd.lingyuangou.ui.mine.adapter.AddressListAdapter;
import com.ahxd.lingyuangou.ui.mine.adapter.RecordListAdapter;
import com.ahxd.lingyuangou.ui.mine.contract.IAddressContract;
import com.ahxd.lingyuangou.ui.mine.contract.IRecordContract;
import com.ahxd.lingyuangou.ui.mine.presenter.AddressListPresenter;
import com.ahxd.lingyuangou.ui.mine.presenter.RecordListPresenter;
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
 * Created by Mao Zhendong on 2018/1/11.
 */
public class BalanceRechargeRecordActivity extends BaseActivity implements IRecordContract.IRecordListView {

    @BindView(R.id.rv_balance_recharge_record_list)
    RecyclerView rvList;
    @BindView(R.id.srl_balance_recharge_record_list_container)
    SmartRefreshLayout srlListContainer;
    private int mPage = 1;
    private List<RecordBean> mRecordList = new ArrayList<>();
    private RecordListAdapter mAdapter;
    private RecordListPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("充值记录");
        mAdapter = new RecordListAdapter(this);
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
        srlListContainer.finishRefresh();
        srlListContainer.finishLoadmore();
        if (null != list) {
            mRecordList.addAll(list);
            mAdapter.setData(mRecordList);
        }
    }

    @Override
    public void showIntegralList(List<IntegralBean> list) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_EDIT_ADDRESS && resultCode == RESULT_OK) {
            mPage = 1;
            mRecordList.clear();
            mPresenter.getRecordList(mPage);
        }
    }
    private void loadData() {
        mPresenter.getRecordList(mPage);
    }

}
