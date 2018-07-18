package com.ahxd.lingyuangou.utils;

import android.content.Intent;
import android.text.TextUtils;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.constant.Constant;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

/**
 * Created by Mao Zhendong on 2018/1/19.
 */

public class LocationUtils {
    private static LocationUtils mInstance;

    private AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOptions = null;

    private OnLocationChangeListener mListener;
    private AMapLocation mLocation;

    public interface OnLocationChangeListener {
        public void onLocationSuccess(String city, String longitude, String latitude);

        public void onLocationFailure(String msg);
    }

    public static LocationUtils getInstance() {
        if (null == mInstance) {
            mInstance = new LocationUtils();
        }
        return mInstance;
    }

    private LocationUtils() {
        initLocation();
    }

    private void initLocation() {
        //初始化client
        mLocationClient = new AMapLocationClient(MaoApplication.getInstance());
        mLocationOptions = getDefaultOption();
        //设置定位参数
        mLocationClient.setLocationOption(mLocationOptions);
        // 设置定位监听
        mLocationClient.setLocationListener(mLocationListener);
    }

    /**
     * 默认的定位参数
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        //可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setGpsFirst(false);
        //可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setHttpTimeOut(30000);
        //可选，设置定位间隔。默认为2秒
        mOption.setInterval(2000);
        //可选，设置是否返回逆地理地址信息。默认是true
        mOption.setNeedAddress(true);
        //可选，设置是否单次定位。默认是false
        mOption.setOnceLocation(true);
        //可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        mOption.setOnceLocationLatest(false);
        //可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        //可选，设置是否使用传感器。默认是false
        mOption.setSensorEnable(false);
        //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setWifiScan(true);
        //可选，设置是否使用缓存定位，默认为true
        mOption.setLocationCacheEnable(true);
        return mOption;
    }

    public void setOnLocationListener(OnLocationChangeListener listener) {
        this.mListener = listener;
    }

    /**
     * 开始定位
     */
    public void startLocation() {
        // 设置定位参数
        mLocationClient.setLocationOption(mLocationOptions);
        // 启动定位
        mLocationClient.startLocation();
    }

    /**
     * 停止定位
     */
    public void stopLocation() {
        // 停止定位
        mLocationClient.stopLocation();
    }

    /**
     * 销毁定位
     */
    public void destroyLocation() {
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
            mLocationClient = null;
            mLocationClient = null;
            mInstance = null;
        }
    }

    /**
     * 获取城市
     */
    public String getCity() {
        if (TextUtils.isEmpty((String) SPUtils.get(MaoApplication.getInstance(), Constant.KEY_CITY, ""))) {
            return "定位中";
        } else {
            return (String) SPUtils.get(MaoApplication.getInstance(), Constant.KEY_CITY, "");
        }
    }

    /**
     * 获取经纬度
     */
    public String getLocation() {
        if (TextUtils.isEmpty((String) SPUtils.get(MaoApplication.getInstance(), Constant.KEY_LOCATION, ""))) {
            return null;
        } else {
            return (String) SPUtils.get(MaoApplication.getInstance(), Constant.KEY_LOCATION, "");
        }
    }
    /**
     * 获取经纬度(LatLonPoint)
     */
    public LatLonPoint getLatLonPoint() {
        if (TextUtils.isEmpty((String) SPUtils.get(MaoApplication.getInstance(), Constant.KEY_LOCATION, ""))) {
            return null;
        } else {
            String lnglat=(String) SPUtils.get(MaoApplication.getInstance(), Constant.KEY_LOCATION, "");
            LatLonPoint lonPoint=new LatLonPoint(Double.parseDouble(lnglat.split(",")[1]),Double.parseDouble(lnglat.split(",")[0]));
            return lonPoint;
        }
    }


    /**
     * 定位监听
     */
    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    mLocation = location;
                    RxBus.getInstance().post(location);
                    ToastUtils.showShort(MaoApplication.getInstance(), "定位成功!");
                    SPUtils.put(MaoApplication.getInstance(), Constant.KEY_CITY, location.getCity().isEmpty()?"定位中":location.getCity());
                    SPUtils.put(MaoApplication.getInstance(), Constant.KEY_LOCATION, location.getLongitude() + "," + location.getLatitude() + "");
                    Intent intent = new Intent("com.ahxd.lingyuangou.location");
                    MaoApplication.getInstance().sendBroadcast(intent);
                    if (null != mListener) {
                        mListener.onLocationSuccess(location.getCity(), location.getLongitude() + "", location.getLatitude() + "");
                    }
                } else {
                    ToastUtils.showShort(MaoApplication.getInstance(), "定位失败!");
                    //定位失败
                    StringBuilder sb = new StringBuilder();
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                    if (null != mListener) {
                        mListener.onLocationFailure(sb.toString());
                    }
                }
            } else {
                ToastUtils.showShort(MaoApplication.getInstance(), "定位失败!");
                if (null != mListener) {
                    mListener.onLocationFailure("定位失败");
                }
            }
        }
    };

    /**
     * 设置搜索信息
     */
    public  void setSearch(String keyWord) {
        /**
         * POI查询对象
         */
        PoiSearch poiSearch;
        /**
         * Poi查询信息对象
         */
        PoiSearch.Query query;
        query = new PoiSearch.Query(keyWord, "", "");
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(1);//设置查询页码
        //构造 PoiSearch 对象，并设置监听。
        poiSearch = new PoiSearch(MaoApplication.getInstance(), query);
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                if (poiResult==null||poiResult.getPois().size()==0){
                    ToastUtils.showShort(MaoApplication.getInstance(),"没有找到结果");
                }else {
                    LatLonPoint point =  poiResult.getPois().get(0).getLatLonPoint();
                    SPUtils.put(MaoApplication.getInstance(), Constant.KEY_LOCATION, point.getLongitude() + "," + point.getLatitude() + "");
                }

            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        //调用 PoiSearch 的 searchPOIAsyn() 方法发送请求。
        poiSearch.searchPOIAsyn();
    }

    /**
     *
     */

    public void setCurrentLocation(String keyWord){
        if (mLocation!=null){
            SPUtils.put(MaoApplication.getInstance(), Constant.KEY_LOCATION, mLocation.getLongitude() + "," + mLocation.getLatitude() + "");
        }else {
            setSearch(keyWord);
        }

    }
}
