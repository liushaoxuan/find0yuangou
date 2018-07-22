package com.ahxd.lingyuangou.ui.home.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.FoodShopBean;
import com.ahxd.lingyuangou.bean.FoodShopEvaluateBean;
import com.ahxd.lingyuangou.bean.ShopGoodBean;
import com.ahxd.lingyuangou.listener.OnNaviListener;
import com.ahxd.lingyuangou.listener.ShopDetailClickListener;
import com.ahxd.lingyuangou.permission.PermissionResultAdapter;
import com.ahxd.lingyuangou.permission.PermissionUtil;
import com.ahxd.lingyuangou.ui.home.adapter.FoodShopDetailAdapter;
import com.ahxd.lingyuangou.ui.home.contract.IFoodShopDetailContract;
import com.ahxd.lingyuangou.ui.home.presenter.FoodShopDetailPresenter;
import com.ahxd.lingyuangou.ui.navigation.CheckPermissionsActivity;
import com.ahxd.lingyuangou.utils.ShareUtil;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.AdsPopupWindow;
import com.ahxd.lingyuangou.widget.RecyclerViewDivider;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;

/**
 * Created by Administrator on 2018/1/2.
 */

public class FoodShopDetailActivity extends CheckPermissionsActivity implements IFoodShopDetailContract.IFoodShopDetailView,
        ShopDetailClickListener, INaviInfoCallback, OnNaviListener {

    @BindView(R.id.rv_food_shop_detail)
    RecyclerView rvFoodShopDetail;
    @BindView(R.id.btn_food_shop_detail_check)
    Button btnCheck;

    private String catId;

    private JSONObject mShopInfo;
    private FoodShopDetailAdapter mAdapter;
    private FoodShopDetailPresenter mPresenter;

    // 店铺是否收藏
    private boolean isFavorite;

    @Override
    protected void initView() {
        super.initView();
        mAdapter = new FoodShopDetailAdapter(this, this);
        rvFoodShopDetail.setAdapter(mAdapter);
        rvFoodShopDetail.setLayoutManager(new LinearLayoutManager(this));
        rvFoodShopDetail.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL,
                1, getResources().getColor(R.color.color_bg)));
        mAdapter.setOnNaviListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        setToolBarTitle(getIntent().getStringExtra("shopName"));
        mPresenter = new FoodShopDetailPresenter(this);
        catId = getIntent().getStringExtra("catId");
        mPresenter.getFoodShopDetail(getIntent().getStringExtra("catId"), getIntent().getStringExtra("shopId"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_food_shop_detail;
    }

    @Override
    public void showShopInfo(JSONObject shopInfo) {
        mShopInfo = shopInfo;
        if (shopInfo.optInt("isPayBill") == 1) {
            // tag_sxliu 如果是金融类的，不显示结账按钮
            if ("373".equals(catId)) {
                btnCheck.setVisibility(View.GONE);
            } else {
                btnCheck.setVisibility(View.VISIBLE);
            }
        } else {
            btnCheck.setVisibility(View.GONE);
        }
        mAdapter.setShopInfo(shopInfo);
    }

    @Override
    public void showShopGoods(List<ShopGoodBean> list) {
        mAdapter.setShopGoods(list);
    }

    @Override
    public void showEvaluateInfo(List<FoodShopEvaluateBean> list) {
        mAdapter.setEvaluateInfo(list);
    }

    @Override
    public void showShopList(List<FoodShopBean> list) {
        mAdapter.setShopList(list);
    }

    @Override
    public void showFavorite(String msg) {
        ToastUtils.showShort(this, msg);
        mAdapter.setFavorite(!isFavorite);
    }

    public void call(final String phone) {
        PermissionUtil.getInstance().request(this, new String[]{Manifest.permission.CALL_PHONE},
                new PermissionResultAdapter() {
                    @Override
                    public void onPermissionGranted() {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                        // 开启拨号界面
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onPermissionDenied(String... permissions) {
                        new AppSettingsDialog.Builder(FoodShopDetailActivity.this)
                                .setRationale("没有该权限，此应用程序可能无法正常工作。打开应用设置界面以修改应用权限")
                                .setTitle("必需权限")
                                .build()
                                .show();
                    }

                });
    }

    @Override
    public void favorite(String targetId, boolean isFavorite) {
        this.isFavorite = isFavorite;
        // 店铺为1，商品为0
        mPresenter.favorite(targetId, "1");
    }

    @Override
    public void showAds(String incomeRate) {
        AdsPopupWindow popupWindow = new AdsPopupWindow(this);
        popupWindow.show(incomeRate);
    }

    @OnClick(R.id.btn_food_shop_detail_check)
    public void onClickView() {
        Intent intent = new Intent(this, OfflinePayActivity.class);
        intent.putExtra("shopId", mShopInfo.optString("shopId"));
        startActivity(intent);
    }

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onArriveDestination(boolean b) {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onStopSpeaking() {

    }

    @Override
    public void onReCalculateRoute(int i) {

    }

    @Override
    public void onExitPage(int i) {

    }

    @Override
    public void onStrategyChanged(int i) {

    }

    @Override
    public View getCustomNaviBottomView() {
        return null;
    }

    @Override
    public View getCustomNaviView() {
        return null;
    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onNaviClickListener(JSONObject object) {
        LatLng parama = new LatLng(Double.parseDouble(mShopInfo.optString("lat")), Double.parseDouble(mShopInfo.optString("lng")));
        AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), new AmapNaviParams(null, null, new Poi(mShopInfo.optString("shopName"), parama, ""), AmapNaviType.DRIVER), FoodShopDetailActivity.this);

    }

    public void tvShare(View view){
        ShareUtil.Share(this);
    }
}
