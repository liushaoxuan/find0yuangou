package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.CartBean;
import com.ahxd.lingyuangou.bean.OrderBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.listener.OnOrderOperateListener;
import com.ahxd.lingyuangou.ui.cart.activity.OnlinePayActivity;
import com.ahxd.lingyuangou.ui.home.adapter.GoodsListAdapter;
import com.ahxd.lingyuangou.ui.mine.adapter.OrderListAdapter;
import com.ahxd.lingyuangou.ui.mine.contract.IOrderContract;
import com.ahxd.lingyuangou.ui.mine.presenter.OrderPresenter;
import com.ahxd.lingyuangou.utils.ToastUtils;
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
 * Created by Mao on 2018/1/14.
 */

public class OrderListActivity extends BaseActivity implements IOrderContract.IOrderView, OnOrderOperateListener {

    @BindView(R.id.rv_order_list)
    RecyclerView rvOrderList;
    @BindView(R.id.srl_order_list_container)
    SmartRefreshLayout srlOrderListContainer;
    @BindView(R.id.tl_order_list_tab)
    TabLayout tlOrderListTab;
    @BindView(R.id.tl_giftorder_list_tab)
    TabLayout tlGifOrderListTab;
    @BindView(R.id.tv_gouwu)
    TextView tvGouwu;
    @BindView(R.id.tv_duihuan)
    TextView tvDuihuan;

    private int isGift = 0;//0是购物1是兑换


    private OrderPresenter mPresenter;
    private int mPage = 1;
    private String mOrderStatus;
    private String mIsAppraise;
    private List<OrderBean> mOrderList = new ArrayList<>();
    private OrderListAdapter mAdapter;
    String type="";

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("我的订单");
        initTab();
        if (null==getIntent().getStringExtra("type")){
            type ="" ;
        }else {
            type = getIntent().getStringExtra("type");
        }

        mAdapter = new OrderListAdapter(this, this,isGift);
        rvOrderList.setAdapter(mAdapter);

        rvOrderList.setLayoutManager(new LinearLayoutManager(this));
        rvOrderList.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL,
                2, getResources().getColor(R.color.color_bg)));
    }

    @Override
    protected void initListener() {
        srlOrderListContainer.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                loadData();
            }
        });

        srlOrderListContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mOrderList.clear();
                loadData();
            }
        });

        tlOrderListTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPage = 1;
                mOrderList.clear();
                switch (tab.getPosition()) {
                    case 0: // 全部
                        mOrderStatus = null;
                        mIsAppraise = null;
                        break;
                    case 1: //
                        mOrderStatus = "-2";
                        mIsAppraise = null;
                        break;
                    case 2: // 代发货
                        mOrderStatus = "0";
                        mIsAppraise = null;
                        break;
                    case 3:// 待收货
                        mOrderStatus = "1";
                        mIsAppraise = null;
                        break;
                    case 4: // 已收货
                        mOrderStatus = "2";
                        mIsAppraise = null;
                        break;
                    case 5: // 待评价
                        mOrderStatus = "2";
                        mIsAppraise = "0";
                        break;
                    case 6: // 退货
                        mOrderStatus = "3";
                        mIsAppraise = null;
                        break;
                    case 7: // 已取消
                        mOrderStatus = "-1";
                        mIsAppraise = null;
                        break;
                }
                loadData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        tlGifOrderListTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPage = 1;
                mOrderList.clear();
                switch (tab.getPosition()) {
                    case 0: // 全部
                        mOrderStatus = null;
                        mIsAppraise = null;
                        break;
                    case 1: // 代发货
                        mOrderStatus = "0";
                        mIsAppraise = null;
                        break;
                    case 2:// 待收货
                        mOrderStatus = "1";
                        mIsAppraise = null;
                        break;

                }
                loadData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    @OnClick({R.id.tv_gouwu, R.id.tv_duihuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_gouwu:
                isGift=0;
                mPage=1;
                tvGouwu.setTextColor(getResources().getColor(R.color.color_theme));
                tvDuihuan.setTextColor(getResources().getColor(R.color.black));
                mOrderList.clear();
                tlGifOrderListTab.setVisibility(View.GONE);
                tlOrderListTab.setVisibility(View.VISIBLE);
                loadData();
                break;
            case R.id.tv_duihuan:
                isGift=1;
                mPage=1;
                tvGouwu.setTextColor(getResources().getColor(R.color.black));
                tvDuihuan.setTextColor(getResources().getColor(R.color.color_theme));
                mOrderList.clear();
                tlGifOrderListTab.setVisibility(View.VISIBLE);
                tlOrderListTab.setVisibility(View.GONE);
                loadData();
                break;
        }
    }
    @Override
    protected void initData() {
        mPresenter = new OrderPresenter(this);
        // orderStatus -2 代付款 -1用户取消 0 代发货 1 代收货 2 已确认收货 3 退货  null 全部
        // isAppraise 0 待评价
        mOrderStatus = null;
        mIsAppraise = null;
        if (type.equals("gift")){
            isGift=1;
            mPage=1;
            tvGouwu.setTextColor(getResources().getColor(R.color.black));
            tvDuihuan.setTextColor(getResources().getColor(R.color.color_theme));
            mOrderList.clear();
            tlGifOrderListTab.setVisibility(View.VISIBLE);
            tlOrderListTab.setVisibility(View.GONE);
            loadData();
        }else {
            isGift=0;
            mPage=1;
            tvGouwu.setTextColor(getResources().getColor(R.color.color_theme));
            tvDuihuan.setTextColor(getResources().getColor(R.color.black));
            mOrderList.clear();
            tlGifOrderListTab.setVisibility(View.GONE);
            tlOrderListTab.setVisibility(View.VISIBLE);
            loadData();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void refreshData() {
        super.refreshData();
        mOrderStatus = null;
        mIsAppraise = null;
        loadData();
    }



    @Override
    public void showGiftOrderList(List<OrderBean> list) {
        srlOrderListContainer.finishRefresh();
        srlOrderListContainer.finishLoadmore();
        if (null != list) {
            mOrderList.addAll(list);
            mAdapter.setData(mOrderList,isGift);
        }
    }

    @Override
    public void showOrderList(List<OrderBean> list) {
        srlOrderListContainer.finishRefresh();
        srlOrderListContainer.finishLoadmore();
        if (null != list) {
            mOrderList.addAll(list);
            mAdapter.setData(mOrderList,isGift);
        }
    }

    @Override
    public void showGiftOrderPayDetail(JSONObject data) {
        if (null != data) {
            Intent intent = new Intent(this, OnlinePayActivity.class);
            intent.putExtra("orderInfo", data.toString());
            intent.putExtra("start_from", "order");
            intent.putExtra("type", "gift");
            startActivityForResult(intent, Constant.REQ_PAY);
        }
    }

    @Override
    public void showOrderPayDetail(JSONObject data) {
        if (null != data) {
            Intent intent = new Intent(this, OnlinePayActivity.class);
            intent.putExtra("orderInfo", data.toString());
            intent.putExtra("start_from", "order");
            startActivityForResult(intent, Constant.REQ_PAY);
        }
    }

    @Override
    public void showCancelOrder(String msg) {
        ToastUtils.showShort(this, msg);
        mPage = 1;
        mOrderList.clear();
        loadData();
    }

    @Override
    public void showGiftCancelOrder(String msg) {
        ToastUtils.showShort(this, msg);
        mPage = 1;
        mOrderList.clear();
        loadData();
    }

    @Override
    public void showConfirmReceiver(String msg) {
        ToastUtils.showShort(this, msg);
        mPage = 1;
        mOrderList.clear();
        loadData();
    }

    @Override
    public void showGiftConfirmReceiver(String msg) {
        ToastUtils.showShort(this, msg);
        mPage = 1;
        mOrderList.clear();
        loadData();
    }

    @Override
    public void onShopEvaluate(OrderBean bean) {
        Intent intent = new Intent(this, EvaluateActivity.class);
        intent.putExtra("shopId", bean.getShopId());
        intent.putExtra("orderId", bean.getOrderId());
        intent.putExtra("shopImg", bean.getShopImg());
        intent.putExtra("shopName", bean.getShopName());
        startActivityForResult(intent, Constant.REQ_EVALUATE);
    }

    @Override
    public void onPayOrder(OrderBean bean) {
        if (null != bean) {
            if (isGift==0){
                mPresenter.getOrderPayInfo(bean.getOrderId());
            }else {
                mPresenter.getGiftOrderPayInfo(bean.getOrderId());
            }

        }
    }

    @Override
    public void onCancelOrder(OrderBean bean) {
        if (null != bean) {
            if(isGift==0){
                mPresenter.onCancelOrder(bean.getOrderId(), null);
            }else {
                mPresenter.onGiftCancelOrder(bean.getOrderId(), null);
            }
        }
    }

    @Override
    public void onConfirmReceiver(OrderBean bean) {
        if (null != bean) {
            if(isGift==0){
                mPresenter.onConfirmReceiver(bean.getOrderId());
            }else {
                mPresenter.onGiftConfirmReceiver(bean.getOrderId());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mPage = 1;
            mOrderList.clear();
            loadData();
        }
//        if (requestCode == Constant.REQ_EVALUATE && resultCode == RESULT_OK) {
//            mPage = 1;
//            mOrderList.clear();
//            loadData();
//        }
    }

    private void initTab() {


//        tlGifOrderListTab.setTabMode(TabLayout.MODE_SCROLLABLE);

        tlGifOrderListTab.addTab(tlGifOrderListTab.newTab().setText("全部"));
        tlGifOrderListTab.addTab(tlGifOrderListTab.newTab().setText("待发货"));
        tlGifOrderListTab.addTab(tlGifOrderListTab.newTab().setText("待收货"));
        tlGifOrderListTab.setTabGravity(TabLayout.GRAVITY_FILL);
//        // 设置TabLayout分割线
        LinearLayout linearLayout2 = (LinearLayout) tlGifOrderListTab.getChildAt(0);
        linearLayout2.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout2.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.line_order_tab_devider));

        List<String> tabList = new ArrayList<>();
        tabList.add("全部");
        tabList.add("待付款");
        tabList.add("待发货");
        tabList.add("待收货");
        tabList.add("已收货");
        tabList.add("待评价");
        tabList.add("退货");
        tabList.add("已取消");
        TabLayout.Tab tab;
        tlOrderListTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < tabList.size(); i++) {
            tab = tlOrderListTab.newTab();
            tab.setText(tabList.get(i));
            tlOrderListTab.addTab(tab);
        }
        // 设置TabLayout分割线
        LinearLayout linearLayout = (LinearLayout) tlOrderListTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.line_order_tab_devider));



    }

    private void loadData() {
        if (isGift==0){
            mPresenter.getOrderList(mOrderStatus, mIsAppraise, mPage);
        }else {
            mPresenter.getGiftOrderList(mOrderStatus, mIsAppraise, mPage);
        }

    }
}
