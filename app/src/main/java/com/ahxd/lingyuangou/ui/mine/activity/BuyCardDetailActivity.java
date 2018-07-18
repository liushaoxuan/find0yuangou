package com.ahxd.lingyuangou.ui.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.BuyCardDetailBean;
import com.ahxd.lingyuangou.bean.UserInfoBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.adapter.BuyCardDetailAdapter;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 购买卡明细
 */

public class BuyCardDetailActivity extends BaseActivity {
    private static String TAG = BuyCardDetailActivity.class.getSimpleName();
    @BindView(R.id.rv_card_list)
    RecyclerView rvCardList;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private BuyCardDetailAdapter adapter;
    private List<BuyCardDetailBean> list;
    /**
     * 用户数据
     */
    private UserInfoBean userInfoBean;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        initExtras();
        setToolBarTitle("充值明细");
        list = new ArrayList<>();
        adapter = new BuyCardDetailAdapter(this, list);
        rvCardList.setLayoutManager(new LinearLayoutManager(this));
        rvCardList.setAdapter(adapter);
        if (userInfoBean!=null){
            充值明细();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_card_detail;
    }

    /**
     * 获取上个页面带过来的用户数据
     */
    private void initExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userInfoBean = (UserInfoBean) extras.get("userInfoBean");
        }
    }

    /**
     * 获取充值明细
     */
    private void 充值明细(){
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("userid", userInfoBean.getUserId());
        OkGo.<String>get(HostUrl.URL_UCERNTER_BUYCARDDETAIL)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            List<BuyCardDetailBean> temp = JSON.parseArray(obj.optString("data").toString(),BuyCardDetailBean.class);
                            if (temp!=null){
                                list.clear();
                                list.addAll(temp);
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG,e.getMessage());
                        }
                    }
                });
    }
}
