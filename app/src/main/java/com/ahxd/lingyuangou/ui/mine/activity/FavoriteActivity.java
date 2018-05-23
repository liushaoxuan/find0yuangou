package com.ahxd.lingyuangou.ui.mine.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.FavoriteGoodBean;
import com.ahxd.lingyuangou.bean.FavoriteShopBean;
import com.ahxd.lingyuangou.listener.OnFavoriteListener;
import com.ahxd.lingyuangou.ui.home.adapter.FoodShopListAdapter;
import com.ahxd.lingyuangou.ui.home.adapter.GoodsListAdapter;
import com.ahxd.lingyuangou.ui.mine.contract.IFavoriteContract;
import com.ahxd.lingyuangou.ui.mine.presenter.FavoritePresenter;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mao on 2018/1/12.
 */

public class FavoriteActivity extends BaseActivity implements IFavoriteContract.IFavoriteView,OnFavoriteListener{

    @BindView(R.id.rb_favorite_goods)
    RadioButton rbFavoriteGoods;
    @BindView(R.id.rb_favorite_foods)
    RadioButton rbFavoriteFoods;
    @BindView(R.id.rb_favorite_hotels)
    RadioButton rbFavoriteHotels;
    @BindView(R.id.rb_favorite_shops)
    RadioButton rbFavoriteShops;
    @BindView(R.id.rv_favorite_list)
    RecyclerView rvFavoriteList;
    @BindView(R.id.srl_favorite_container)
    SmartRefreshLayout srlFavoriteContainer;
    @BindView(R.id.rg_favorite_label)
    RadioGroup rgFavoriteLabel;

    private FavoritePresenter mPresenter;
    private int mFavoriteType = 0; // （0商品 1店铺 2餐饮 3酒店）
    private List<Object> mShopList = new ArrayList<>();
    private List<Object> mGoodList = new ArrayList<>();
    private GoodsListAdapter mGoodsListAdapter;
    private FoodShopListAdapter mFoodShopAdapter;


    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("我的收藏");
        rbFavoriteGoods.setChecked(true);
        mFoodShopAdapter = new FoodShopListAdapter(this,this);
        mGoodsListAdapter = new GoodsListAdapter(this,this);
        rvFavoriteList.setAdapter(mGoodsListAdapter);

        rvFavoriteList.setLayoutManager(new LinearLayoutManager(this));
        rvFavoriteList.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL,
                1, getResources().getColor(R.color.color_bg)));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter = new FavoritePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_favorite;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mShopList.clear();
        mGoodList.clear();
        mPresenter.getFavoriteList(mFavoriteType);
    }

    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getFavoriteList(mFavoriteType);
    }

    @OnClick({R.id.rb_favorite_goods, R.id.rb_favorite_foods, R.id.rb_favorite_hotels, R.id.rb_favorite_shops})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_favorite_goods:
                mFavoriteType = 0;
                mGoodList.clear();
                rbFavoriteGoods.setChecked(true);
                rvFavoriteList.setAdapter(mGoodsListAdapter);
                break;
            case R.id.rb_favorite_foods:
                mFavoriteType = 2;
                mShopList.clear();
                rbFavoriteFoods.setChecked(true);
                rvFavoriteList.setAdapter(mFoodShopAdapter);
                break;
            case R.id.rb_favorite_hotels:
                mFavoriteType = 3;
                mShopList.clear();
                rbFavoriteHotels.setChecked(true);
                rvFavoriteList.setAdapter(mFoodShopAdapter);
                break;
            case R.id.rb_favorite_shops:
                mFavoriteType = 1;
                mShopList.clear();
                rbFavoriteShops.setChecked(true);
                rvFavoriteList.setAdapter(mFoodShopAdapter);
                break;
        }
        mPresenter.getFavoriteList(mFavoriteType);
    }

    @Override
    public void showShopFavorite(List<FavoriteShopBean> list) {
        if (null != list) {
            this.mShopList.addAll(list);
            mFoodShopAdapter.setData(mShopList);
        }
    }

    @Override
    public void showGoodFavorite(List<FavoriteGoodBean> list) {
        if (null != list) {
            this.mGoodList.addAll(list);
            mGoodsListAdapter.setData(mGoodList);
        }
    }

    @Override
    public void showDeleteFavorite(String data) {
        ToastUtils.show(this,"删除成功",0);
        mShopList.clear();
        mGoodList.clear();
        mPresenter.getFavoriteList(mFavoriteType);
    }

    @Override
    public void onDelete(String favoriteId) {
        mPresenter.deleteFavoriteList(favoriteId);
    }
}
