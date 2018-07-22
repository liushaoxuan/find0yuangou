package com.ahxd.lingyuangou.ui.home.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.CartBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.cart.activity.OnlinePayActivity;
import com.ahxd.lingyuangou.ui.home.contract.IGoodDetailContract;
import com.ahxd.lingyuangou.ui.home.presenter.GoodDetailPresenter;
import com.ahxd.lingyuangou.ui.main.activity.MainActivity;
import com.ahxd.lingyuangou.ui.mine.activity.EvaluateActivity;
import com.ahxd.lingyuangou.ui.mine.activity.LoginActivity;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.ShareUtil;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.AdsPopupWindow;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Mao Zhendong on 2018/1/12.
 */

public class GoodDetailActivity extends BaseActivity implements IGoodDetailContract.IGoodDetailView {


    @BindView(R.id.wv_good_detail_webview)
    WebView wvGoodDetailWebview;

    private String mGoodsId;
    private GoodDetailPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        WebSettings webSettings = wvGoodDetailWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkImage(true);
        wvGoodDetailWebview.addJavascriptInterface(new GoodsDetailInterface(), "goodsDetail");
        // 使用自身webview打开网页
        wvGoodDetailWebview.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //加载资源时响应
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                L.e("webview - onLoadResource");
            }

            //在加载页面时响应
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                L.e("webview - onPageStarted");
//                LoadingView.startLoading(GoodsDetailActivity.this);
            }

            //在加载页面结束时响应
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                L.e("webview - onPageFinished");
//                LoadingView.stopLoading();
                wvGoodDetailWebview.getSettings().setBlockNetworkImage(false);
            }

            //在加载出错时响应
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                L.e("webview - onReceivedError");
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        setToolBarTitle(getIntent().getStringExtra("goodsName"));
        mGoodsId = getIntent().getStringExtra("goodsId");
        wvGoodDetailWebview.loadUrl(HostUrl.URL_GOOD_DETAIL + "?goodsId=" + mGoodsId
                + "&token=" + (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));

        mPresenter = new GoodDetailPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_good_detail;
    }

    @Override
    public void showAddCart(String msg) {
        ToastUtils.showShort(this, msg);
    }

    @Override
    public void showBuyNow(JSONObject data) {
        Intent intent = new Intent(this, OnlinePayActivity.class);
        intent.putExtra("orderInfo", data.toString());
        intent.putExtra("start_from", "good");
        startActivityForResult(intent, Constant.REQ_PAY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constant.REQ_LOGIN) {
            wvGoodDetailWebview.loadUrl(HostUrl.URL_GOOD_DETAIL + "?goodsId=" + mGoodsId
                    + "&token=" + (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        }
    }

    class GoodsDetailInterface {
        @JavascriptInterface
        public void androidGoods(String msg) {
            L.e("good_detail", msg);
            try {
                final JSONObject data = new JSONObject(msg);
                switch (data.optString("flag")) {
                    case "gwc":
                        Intent gwcIntent = new Intent(GoodDetailActivity.this, MainActivity.class);
                        gwcIntent.putExtra("type", "cart");
                        startActivity(gwcIntent);
                        break;
                    case "ads":
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AdsPopupWindow popupWindow = new AdsPopupWindow(GoodDetailActivity.this);
                                popupWindow.show(data.optString("incomeRate"));
                            }
                        });
                        break;
                    case "pj":
                        Intent pjIntent = new Intent(GoodDetailActivity.this, FoodShopEvaluateListActivity.class);
                        pjIntent.putExtra("shopId", data.optString("shopId"));
                        pjIntent.putExtra("goodsId", data.optString("goodsId"));
                        startActivity(pjIntent);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void cart(String msg) {
            L.e("good_detail", msg);
            try {
                JSONObject data = new JSONObject(msg);
                mPresenter.addGoodCart(data.optString("good_id"), data.optString("goodsSpecId"),
                        data.optString("cartNum"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void gobuy(String msg) {
//            getCarList(msg);
            L.e("good_detail", msg);
            try {
                JSONObject data = new JSONObject(msg);
                mPresenter.buyGoodNow(data.optString("goodsId"), data.optString("specIds"),
                        data.optString("buyNum"), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void login() {
            Intent intent = new Intent(GoodDetailActivity.this, LoginActivity.class);
            startActivityForResult(intent, Constant.REQ_LOGIN);
        }

    }


    /**
     * 获取购物车列表
     */
    private void getCarList(final String msg) {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        OkGo.<String>post(HostUrl.URL_CART_GOODS_LIST)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            CartBean cartBeans = JSON.parseObject(obj.optString("data"), new TypeReference<CartBean>() {
                            });
                            StringBuffer content = new StringBuffer();
                            if (cartBeans.getOrder() != null && cartBeans.getOrder().size() > 0) {
                                for (CartBean.OrderBean orderBean : cartBeans.getOrder()) {
                                    for (CartBean.OrderBean.GoodsBean goodsBean : orderBean.getGoods()) {
                                        content.append(goodsBean.getGoodsId())
                                                .append(":")
                                                .append(goodsBean.getCartNum())
                                                .append(",");
                                    }
                                }
                            }
                            JSONObject data = new JSONObject(msg);
                            content.append(data.optString("goodsId"))
                                    .append(":")
                                    .append(data.optString("buyNum"))
                                    .append(",");
                            submitOrder(content.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    /**
     * 提交订单
     */
    private void submitOrder(String content) {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("content", content);
        OkGo.<String>post(HostUrl.URL_CART_SUBMIT_ORDER)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject data = new JSONObject(response.body());
                            int status = data.optInt("status");
                            if (status != 1) {
                                ToastUtils.showShort(GoodDetailActivity.this, data.optString("msg"));
                            } else {
                                Intent intent = new Intent(GoodDetailActivity.this, OnlinePayActivity.class);
                                intent.putExtra("orderInfo", data.optString("data"));
                                intent.putExtra("start_from", "cart");
                                startActivity(intent);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public void tvShare(View view){
        ShareUtil.Share(this);
    }

}
