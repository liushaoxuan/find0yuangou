package com.ahxd.lingyuangou.ui.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.UserInfoBean;
import com.ahxd.lingyuangou.bean.shopConsumerOrderBean;
import com.ahxd.lingyuangou.callback.MyStringCallBack;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.adapter.MerchantsOrderAdapter;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商家订单
 */
public class MerchantsOrderActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{


    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_user_img)
    ImageView ivUserImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_rebate)
    TextView tvRebate;
    @BindView(R.id.tv_integral)
    TextView tvIntegral;
    @BindView(R.id.rp)
    RadioGroup radioGroup;
    @BindView(R.id.rb_today)
    RadioButton rbToday;
    @BindView(R.id.rb_yestoday)
    RadioButton rbYestoday;
    @BindView(R.id.rb_all)
    RadioButton rbAll;
    @BindView(R.id.rv_member_recyclerview)
    RecyclerView rvMemberRecyclerview;
    @BindView(R.id.tv_member_total_cost)
    TextView tvMemberTotalCost;
    @BindView(R.id.tv_member_cart_amount)
    TextView tvMemberCartAmount;
    /**
     * 用户数据
     */
    private UserInfoBean userInfoBean;

    private List<shopConsumerOrderBean> datas;
    private MerchantsOrderAdapter adapter;

    /**
     * 类型 1 – 当天，2 – 昨天，3 – 全部
     */
    private int inDisplayType = 1;


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        datas = new ArrayList<>();
        setToolBarTitle("商家订单");
        radioGroup.setOnCheckedChangeListener(this);
        initExtras();
        rvMemberRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MerchantsOrderAdapter(this,datas);
        rvMemberRecyclerview.setAdapter(adapter);
        if (userInfoBean != null) {
            tvName.setText(userInfoBean.getUserName());
            tvRebate.setText(userInfoBean.getUserIncome()+"元");
            tvIntegral.setText(userInfoBean.getUserScore());
            getOrders();
        }

        rbToday.setChecked(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_merchants_order;
    }

    /**
     * 获取个人中心带过来的用户数据
     */
    private void initExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userInfoBean = (UserInfoBean) extras.get("userInfoBean");
        }
    }

    /**
     * 获取商家的订单
     */
    private void getOrders() {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("shopid", userInfoBean.getUserId());
        params.put("displaytype", inDisplayType);
        OkGo.<String>post(HostUrl.URL_UCERNTER_SHOPCONSUMERORDER)
                .params(params)
                .execute(new MyStringCallBack(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            JSONArray array = obj.optJSONArray("data");
                            if (array != null) {
                                List<shopConsumerOrderBean> templist = JSON.parseArray(array.toString(), shopConsumerOrderBean.class);
                                if (templist != null) {
                                    datas.clear();
                                    datas.addAll(templist);
                                    adapter.notifyDataSetChanged();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        switch (id){
            case R.id.rb_today:
                inDisplayType = 1;
                break;
            case R.id.rb_yestoday:
                inDisplayType = 2;
                break;
            case R.id.rb_all:
                inDisplayType = 3;
                break;

        }
        getOrders();
    }
}
