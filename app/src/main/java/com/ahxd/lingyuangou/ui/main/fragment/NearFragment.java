package com.ahxd.lingyuangou.ui.main.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseFragment;
import com.ahxd.lingyuangou.bean.AccredBean;
import com.ahxd.lingyuangou.bean.CatBean;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.ui.home.adapter.FoodShopListAdapter;
import com.ahxd.lingyuangou.ui.near.contract.INearContract;
import com.ahxd.lingyuangou.ui.near.presenter.NearPresenter;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.ListTabView;
import com.ahxd.lingyuangou.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/25.
 */

public class NearFragment extends BaseFragment implements INearContract.INearView {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rv_near_list)
    RecyclerView rvNearList;
    @BindView(R.id.srl_near)
    public SmartRefreshLayout srlNear;
    @BindView(R.id.iv_search_del)
    ImageView ivSearchDel;
    @BindView(R.id.ltv_one)
    ListTabView ltvOne;
    @BindView(R.id.ltv_two)
    ListTabView ltvTwo;
    @BindView(R.id.ltv_three)
    ListTabView ltvThree;
    @BindView(R.id.ltv_four)
    ListTabView ltvFour;

    private PopupWindow popupWindowOne;
    private PopupWindow popupWindowThree;
    private PopupWindow popupWindowFour;
    private NearPresenter mPresenter;
    private FoodShopListAdapter mAdapter;
    private List<Object> mNearList = new ArrayList<>();

    private int mPage = 1;
    private String catId="";
    private String AccredId="";
    private String OrderId="1";

    @Override
    protected View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_near, null);
        mUnbinder = ButterKnife.bind(this, view);

        mAdapter = new FoodShopListAdapter(mContext);
        rvNearList.setAdapter(mAdapter);

        rvNearList.setLayoutManager(new LinearLayoutManager(mContext));
        rvNearList.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.HORIZONTAL,
                1, getResources().getColor(R.color.color_bg)));

        initListener();

        return view;
    }

    @Override
    protected void initData() {
        mPresenter = new NearPresenter(this);
        mPresenter.getNearList(mPage, AccredId, null, OrderId, catId, null);
    }

    private void initListener() {
        srlNear.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                mPresenter.getNearList(mPage, AccredId, null, OrderId, catId, etSearch.getText().toString().trim());
            }
        });

        srlNear.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mNearList.clear();
                mPresenter.getNearList(mPage, AccredId, null, OrderId, catId, etSearch.getText().toString().trim());
            }
        });

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
    public void showNearList(List<FoodShopBean> list) {
        srlNear.finishRefresh();
        srlNear.finishLoadmore();
        if (list != null && !list.isEmpty()) {
            mNearList.addAll(list);
            mAdapter.setData(mNearList);
        }
    }

    @Override
    public void showCatsList(List<CatBean> result) {
        if(null!=result){
            showSortWindowOne(ltvOne,result);
        }

    }

    @Override
    public void showAccredList(List<AccredBean> result) {
        if(null!=result){
            showSortWindowFour(ltvFour,result);
        }
    }

    @OnClick({R.id.tv_search, R.id.iv_search_del,R.id.ltv_one,R.id.ltv_two,R.id.ltv_three,R.id.ltv_four})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                if (validate()) {
                    mPage = 1;
                    mNearList.clear();
                    mAdapter.notifyDataSetChanged();
                    mPresenter.getNearList(mPage, AccredId, null, OrderId, catId, etSearch.getText().toString().trim());
                }
                break;
            case R.id.iv_search_del:
                etSearch.setText(null);
                ivSearchDel.setVisibility(View.INVISIBLE);
                mPage = 1;
                mNearList.clear();
                mPresenter.getNearList(mPage, AccredId, null, OrderId, catId, null);
                break;
            case R.id.ltv_one:
                mPresenter.getCatsList();
                break;
            case R.id.ltv_two:
                OrderId="1";
                mPage = 1;
                mNearList.clear();
                mAdapter.notifyDataSetChanged();
                mPresenter.getNearList(mPage, AccredId,null, OrderId, catId, etSearch.getText().toString().trim());
                break;
            case R.id.ltv_three:
                showSortWindowThree(ltvThree);
                break;
            case R.id.ltv_four:
                mPresenter.getAccredList();
                break;
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(etSearch.getText().toString().trim())) {
            ToastUtils.showShort(mContext, "请输入搜索关键词");
            return false;
        }
        return true;
    }

    public void showSortWindowOne(final View sortView, final List<CatBean> list) {
        if (popupWindowOne==null){
        LayoutInflater layoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(
                R.layout.pop_one, null);
        view.setAlpha(1);
        final LinearLayout lin_diss = (LinearLayout) view.findViewById(R.id.car_type_ll);
        LinearLayout lladdView = (LinearLayout) view.findViewById(R.id.ll_addview);
        for (int i=0;i<list.size();i++){
            addDateViewOne(lladdView,list.get(i),1);
        }
//            sortAdapter = new ListViewPopWindoWsynthesisAdapter(mContext,
//                    arrList);
//            lvGroup.setAdapter(sortAdapter);

        // 创建一个PopuWidow对象
            popupWindowOne = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT);
            popupWindowOne.update();
            popupWindowOne.setTouchable(true);
			/* 设置点击menu以外其他地方以及返回键退出 */
            popupWindowOne.setFocusable(true);
        // 设置允许在外点击消失
            popupWindowOne.setOutsideTouchable(true);
//            popupSortWindow.setAnimationStyle(R.style.AnimationRightFade);
        /**
         * 1.解决再次点击MENU键无反应问题 2.sub_view是PopupWindow的子View
         */
        view.setFocusableInTouchMode(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
            popupWindowOne.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
        int xPos = windowManager.getDefaultDisplay().getWidth() / 3
                - popupWindowOne.getWidth() / 3;

        lin_diss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindowOne.dismiss();
            }
        });
        }

        popupWindowOne.showAsDropDown(sortView, 0, 0);
    }
    @SuppressLint("SimpleDateFormat")
    private void addDateViewOne(LinearLayout addViewLL, final CatBean cat,final int type) {
        LayoutInflater factory = LayoutInflater.from(mContext);
        final View view = factory.inflate(R.layout.item_list_tab_view, null);
        TextView carName=(TextView)view.findViewById(R.id.tv_name);
        carName.setText(cat.getCatName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catId =cat.getCatId();
                popupWindowOne.dismiss();
                mPage = 1;
                mNearList.clear();
                mAdapter.notifyDataSetChanged();
                mPresenter.getNearList(mPage, AccredId,null, OrderId, catId, etSearch.getText().toString().trim());

            }
        });
        addViewLL.addView(view);

    }


    public void showSortWindowThree(final View sortView) {
        if (popupWindowThree==null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(
                    R.layout.pop_one, null);
            view.setAlpha(1);
            final LinearLayout lin_diss = (LinearLayout) view.findViewById(R.id.car_type_ll);
            LinearLayout lladdView = (LinearLayout) view.findViewById(R.id.ll_addview);
                CatBean catBean=new CatBean();
                catBean.setCatId("2");
                catBean.setCatName("按下单量");
                CatBean catBean2=new CatBean();
                catBean2.setCatId("3");
                catBean2.setCatName("按返货币比率");
                addDateViewThree(lladdView,catBean,1);
                addDateViewThree(lladdView,catBean2,1);
//            sortAdapter = new ListViewPopWindoWsynthesisAdapter(mContext,
//                    arrList);
//            lvGroup.setAdapter(sortAdapter);

            // 创建一个PopuWidow对象
            popupWindowThree = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT);
            popupWindowThree.update();
            popupWindowThree.setTouchable(true);
			/* 设置点击menu以外其他地方以及返回键退出 */
            popupWindowThree.setFocusable(true);
            // 设置允许在外点击消失
            popupWindowThree.setOutsideTouchable(true);
//            popupSortWindow.setAnimationStyle(R.style.AnimationRightFade);
            /**
             * 1.解决再次点击MENU键无反应问题 2.sub_view是PopupWindow的子View
             */
            view.setFocusableInTouchMode(true);
            // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
            popupWindowThree.setBackgroundDrawable(new BitmapDrawable());
            WindowManager windowManager = (WindowManager) mContext
                    .getSystemService(Context.WINDOW_SERVICE);
            // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
            int xPos = windowManager.getDefaultDisplay().getWidth() / 3
                    - popupWindowThree.getWidth() / 3;

            lin_diss.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    popupWindowThree.dismiss();
                }
            });
        }

        popupWindowThree.showAsDropDown(sortView, 0, 0);
    }
    @SuppressLint("SimpleDateFormat")
    private void addDateViewThree(LinearLayout addViewLL, final CatBean cat,final int type) {
        LayoutInflater factory = LayoutInflater.from(mContext);
        final View view = factory.inflate(R.layout.item_list_tab_view, null);
        TextView carName=(TextView)view.findViewById(R.id.tv_name);
        carName.setText(cat.getCatName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderId =cat.getCatId();
                popupWindowThree.dismiss();
                mPage = 1;
                mNearList.clear();
                mAdapter.notifyDataSetChanged();
                mPresenter.getNearList(mPage, AccredId,null, OrderId, catId, etSearch.getText().toString().trim());
            }
        });
        addViewLL.addView(view);

    }


    public void showSortWindowFour(final View sortView, final List<AccredBean> list) {
        if (popupWindowFour==null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(
                    R.layout.pop_one, null);
            view.setAlpha(1);
            final LinearLayout lin_diss = (LinearLayout) view.findViewById(R.id.car_type_ll);
            LinearLayout lladdView = (LinearLayout) view.findViewById(R.id.ll_addview);
            for (int i=0;i<list.size();i++){
                addDateViewFour(lladdView,list.get(i),1);
            }
//            sortAdapter = new ListViewPopWindoWsynthesisAdapter(mContext,
//                    arrList);
//            lvGroup.setAdapter(sortAdapter);

            // 创建一个PopuWidow对象
            popupWindowFour = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT);
            popupWindowFour.update();
            popupWindowFour.setTouchable(true);
			/* 设置点击menu以外其他地方以及返回键退出 */
            popupWindowFour.setFocusable(true);
            // 设置允许在外点击消失
            popupWindowFour.setOutsideTouchable(true);
//            popupSortWindow.setAnimationStyle(R.style.AnimationRightFade);
            /**
             * 1.解决再次点击MENU键无反应问题 2.sub_view是PopupWindow的子View
             */
            view.setFocusableInTouchMode(true);
            // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
            popupWindowFour.setBackgroundDrawable(new BitmapDrawable());
            WindowManager windowManager = (WindowManager) mContext
                    .getSystemService(Context.WINDOW_SERVICE);
            // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
            int xPos = windowManager.getDefaultDisplay().getWidth() / 3
                    - popupWindowFour.getWidth() / 3;

            lin_diss.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    popupWindowFour.dismiss();
                }
            });
        }

        popupWindowFour.showAsDropDown(sortView, 0, 0);
    }
    @SuppressLint("SimpleDateFormat")
    private void addDateViewFour(LinearLayout addViewLL, final AccredBean cat,final int type) {
        LayoutInflater factory = LayoutInflater.from(mContext);
        final View view = factory.inflate(R.layout.item_list_tab_view, null);
        TextView carName=(TextView)view.findViewById(R.id.tv_name);
        carName.setText(cat.getAccredName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccredId =cat.getAccredId();
                popupWindowFour.dismiss();
                mPage = 1;
                mNearList.clear();
                mAdapter.notifyDataSetChanged();
                mPresenter.getNearList(mPage, AccredId,null, OrderId, catId, etSearch.getText().toString().trim());
            }
        });
        addViewLL.addView(view);

    }

}
