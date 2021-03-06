package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.MyMemberBean;
import com.ahxd.lingyuangou.bean.RecordBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.mine.adapter.ConsumptionRecordListAdapter;
import com.ahxd.lingyuangou.ui.mine.adapter.MyMemberListAdapter;
import com.ahxd.lingyuangou.ui.mine.contract.IConsumptionRecordContract;
import com.ahxd.lingyuangou.ui.mine.contract.IMyMemberContract;
import com.ahxd.lingyuangou.ui.mine.presenter.ConsumptionRecordListPresenter;
import com.ahxd.lingyuangou.ui.mine.presenter.MyMemberPresenter;
import com.ahxd.lingyuangou.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wpc on 2018/1/18.
 */
public class MyMembersActivity extends BaseActivity implements IMyMemberContract.IMyMemberListView {

    @BindView(R.id.rv_mymembers_list)
    RecyclerView rvList;
    @BindView(R.id.srl_mymembers_list_container)
    SmartRefreshLayout srlListContainer;
    private int mPage = 1;
    private List<MyMemberBean> mList = new ArrayList<>();
    private MyMemberListAdapter mAdapter;
    private MyMemberPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("消费记录");
        mAdapter = new MyMemberListAdapter(this);
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
        mPresenter = new MyMemberPresenter(this);
        loadData();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_members_list;
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
    public void showMyMemberList(List<MyMemberBean> list) {
        srlListContainer.finishRefresh();
        srlListContainer.finishLoadmore();
        if (null != list) {
            mList.addAll(list);
            mAdapter.setData(mList);
        }

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_EDIT_ADDRESS && resultCode == RESULT_OK) {
            mPage = 1;
            mList.clear();
            mPresenter.getMyMemberList(mPage);
        }
    }

    private void loadData() {
        mPresenter.getMyMemberList(mPage);
    }


}
