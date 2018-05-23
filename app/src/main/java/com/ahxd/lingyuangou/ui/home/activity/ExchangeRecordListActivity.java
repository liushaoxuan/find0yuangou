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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.CashdrewsBean;
import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.ScoreBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.home.adapter.ExchangeGiftListAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.ExchangeRecordListAdapter;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeGiftListContract;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeRecordListContract;
import com.ahxd.lingyuangou.ui.home.presenter.ExchangeGiftListPresenter;
import com.ahxd.lingyuangou.ui.home.presenter.ExchangeRecordListPresenter;
import com.ahxd.lingyuangou.ui.mine.adapter.PresentRecordListAdapter;
import com.ahxd.lingyuangou.ui.mine.contract.IPresentRecordContract;
import com.ahxd.lingyuangou.ui.mine.presenter.PresentRecordListPresenter;
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
 * Created by Administrator on 2018/1/2.
 */

public class ExchangeRecordListActivity extends BaseActivity implements IExchangeRecordListContract.IExchangeRecordListView {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_list_container)
    SmartRefreshLayout srlListContainer;
    private int mPage = 1;
    private List<ScoreBean> mCashdrewsList = new ArrayList<>();
    private ExchangeRecordListAdapter mAdapter;
    private ExchangeRecordListPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("兑换记录");
        mAdapter = new ExchangeRecordListAdapter(this);
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
                mCashdrewsList.clear();
                loadData();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter = new ExchangeRecordListPresenter(this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_EDIT_ADDRESS && resultCode == RESULT_OK) {
            mPage = 1;
            mCashdrewsList.clear();
            mPresenter.getExchangeRecordList(mPage);
        }
    }
    private void loadData() {

        mPresenter.getExchangeRecordList(mPage);
    }

    @Override
    public void showExchangeRecordList(List<ScoreBean> list) {
        srlListContainer.finishRefresh();
        srlListContainer.finishLoadmore();
        if (null != list) {
            mCashdrewsList.addAll(list);
            mAdapter.setData(list);
        }
    }
}
