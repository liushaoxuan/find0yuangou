package com.ahxd.lingyuangou.ui.home.activity;

/**
 * Created by wpc on 2018/1/23.
 */


import android.os.Bundle;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.utils.LocationUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;

//import com.amap.api.maps.AMap;
//import com.amap.api.maps.MapView;
//import com.amap.map3d.demo.R;

public class AmapActivity extends BaseActivity {

    MapView mMapView = null;
    private AMap aMap;
    RouteSearch  routeSearch;
    Double lng,lat;
    private DriveRouteResult driveRouteResult;// 驾车模式查询结果
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amap);
        TextView mToolbarTitle = (TextView) findViewById(R.id.tv_title);
        mToolbarTitle.setText("地图");
        lng=getIntent().getDoubleExtra("lng",0);
        lat=getIntent().getDoubleExtra("lat",0);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
            @Override
            public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

            }

            @Override
            public void onDriveRouteSearched(DriveRouteResult result, int rCode) {
                if (rCode == 1000) {
                    if (result != null && result.getPaths() != null && result.getPaths().size() > 0) {
                        driveRouteResult = result;
                        DrivePath drivePath = driveRouteResult.getPaths().get(0);
                        aMap.clear();// 清理地图上的所有覆盖物
//                        DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(AmapActivity.this, aMap, drivePath, driveRouteResult.getStartPos(),
//                                driveRouteResult.getTargetPos());
//                        drivingRouteOverlay.removeFromMap();
//                        drivingRouteOverlay.addToMap();
//                        drivingRouteOverlay.zoomToSpan();
                    } else {
//                        ToastUtil.show(this, R.string.no_result);
                    }
                }else if(rCode == 1012){
                    ToastUtils.show(AmapActivity.this,"权限不足",0);
                }else {
                    ToastUtils.show(AmapActivity.this,rCode+"",0);
                }
            }

            @Override
            public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

            }

            @Override
            public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

            }
        });
        LatLonPoint startPoint = LocationUtils.getInstance().getLatLonPoint();
        LatLonPoint endPoint=new LatLonPoint(lat,lng);
//        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(startPoint, endPoint);
//        fromAndTo.setDestinationPoiID();
//        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DrivingDefault, null, null, "");
//        routeSearch.calculateDriveRouteAsyn(query);

//        LatLonPoint latLon1 = new LatLonPoint(40.742467, 116.842785);
//        LatLonPoint latLon2 = new LatLonPoint(39.893433, 116.124035);
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(startPoint, endPoint);
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DrivingDefault, null, null, "");
//查询骑行线路
        routeSearch.calculateDriveRouteAsyn(query);
//        routeSearch.calculateRideRouteAsyn(query);
    }

    @Override
    protected void initListener() {

    }
    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_amap;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

}