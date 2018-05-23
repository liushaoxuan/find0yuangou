package com.ahxd.lingyuangou.ui.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.ShopBean;
import com.ahxd.lingyuangou.ui.home.contract.IGiftShopDetailContract;
import com.ahxd.lingyuangou.ui.home.presenter.GiftShopDetailPresenter;
import com.ahxd.lingyuangou.ui.navigation.CheckPermissionsActivity;
import com.ahxd.lingyuangou.utils.GlideImageLoader;
import com.ahxd.lingyuangou.widget.richtext.RichText;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/12.
 */

public class GiftShopDetailActivity extends CheckPermissionsActivity implements IGiftShopDetailContract.IGiftShopDetailView , INaviInfoCallback {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_map)
    LinearLayout llMap;
    @BindView(R.id.ic_shop_detail_phone)
    ImageView icShopDetailPhone;
    @BindView(R.id.tv_shop_detail_phone)
    TextView tvShopDetailPhone;
    @BindView(R.id.tv_body)
    RichText tvBody;

    GiftShopDetailPresenter mPresenter;
    ShopBean bean;
    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("商店详情");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter = new GiftShopDetailPresenter(this);
        mPresenter.getGiftShopDetail(getIntent().getStringExtra("shopId"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gift_shop_detail;
    }

    @OnClick({R.id.ic_shop_detail_phone,R.id.ll_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_shop_detail_phone:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:" + bean.getTelephone());
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.ll_map:
//                if (AMapUtil.isInstallByRead("com.autonavi.minimap")){
//                    AMapUtil.goToNaviActivity(this,"导航",null,bean.getLat(),bean.getLng(),"1","2");
//                }else {
//                    ToastUtils.show(this,"请先安装高德app",1);
//                }
                LatLng parama = new LatLng(Double.parseDouble(bean.getLat()),Double.parseDouble(bean.getLng()));
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), new AmapNaviParams(null, null, new Poi(bean.getShopName(), parama, ""), AmapNaviType.DRIVER), GiftShopDetailActivity.this);

                break;
        }
    }



    @Override
    public void showGiftShopDetail(ShopBean bean) {
        if (null != bean) {
            this.bean=bean;
            //Banner
            List<String> imageUrls = new ArrayList<>();
            final List<String> adUrls = new ArrayList<>();
            for (int i = 0; i < bean.getShopAds().size(); i++) {
                imageUrls.add(bean.getShopAds().get(i));
            }
            banner.setImages(imageUrls).setImageLoader(new GlideImageLoader()).start();
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    //注意这里的position是从1开始的
                }
            });
            tvName.setText(bean.getShopName());
            tvAddress.setText(bean.getShopAddress());
            tvShopDetailPhone.setText("联系电话:"+bean.getTelephone());
            tvBody.setRichText(bean.getShopContent());

        }
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
}
