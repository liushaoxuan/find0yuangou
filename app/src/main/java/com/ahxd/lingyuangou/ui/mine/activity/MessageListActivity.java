package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.MessageBean;
import com.ahxd.lingyuangou.bean.ScoreBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.home.adapter.ExchangeRecordListAdapter;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeRecordListContract;
import com.ahxd.lingyuangou.ui.home.presenter.ExchangeRecordListPresenter;
import com.ahxd.lingyuangou.ui.mine.adapter.MessageListAdapter;
import com.ahxd.lingyuangou.ui.mine.contract.IMessageListContract;
import com.ahxd.lingyuangou.ui.mine.presenter.MessageListPresenter;
import com.ahxd.lingyuangou.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/2.
 */

public class MessageListActivity extends BaseActivity implements IMessageListContract.IMessageListView {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_list_container)
    SmartRefreshLayout srlListContainer;
    private int mPage = 1;
    private List<MessageBean> mCashdrewsList = new ArrayList<>();
    private MessageListAdapter mAdapter;
    private MessageListPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("消息记录");
        mAdapter = new MessageListAdapter(this);
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
        mPresenter = new MessageListPresenter(this);
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
            mCashdrewsList.clear();
            mPresenter.getMessageList(mPage);
        }
    }
    private void loadData() {

        mPresenter.getMessageList(mPage);
    }

    @Override
    public void showMessageList(List<MessageBean> list) {
        srlListContainer.finishRefresh();
        srlListContainer.finishLoadmore();
        if (null != list) {
            mCashdrewsList.addAll(list);
            mAdapter.setData(list);
        }

        if(null!=getIntent().getStringExtra("setMessage")){
            mPresenter.setMessageList();
        }
    }

    @Override
    public void showSetMessageList(JSONObject data) {

    }
}
