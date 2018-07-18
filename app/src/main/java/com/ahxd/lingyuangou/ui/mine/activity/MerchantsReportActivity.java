package com.ahxd.lingyuangou.ui.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.ConsumerReportBean;
import com.ahxd.lingyuangou.bean.UserInfoBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.adapter.MerchantsReportAdapter;
import com.ahxd.lingyuangou.utils.GlideApp;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
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
 * 商家报表
 */
public class MerchantsReportActivity extends BaseActivity {


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
    @BindView(R.id.tv_ids)
    TextView tvIds;
    @BindView(R.id.rv_report)
    RecyclerView rvReport;
    @BindView(R.id.tv_member_total_cost)
    TextView tvMemberTotalCost;
    @BindView(R.id.tv_member_cart_amount)
    TextView tvMemberCartAmount;
    /**
     * 用户数据
     */
    private UserInfoBean userInfoBean;

    private List<ConsumerReportBean> datas;
    private MerchantsReportAdapter adapter;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        datas = new ArrayList<>();
        setToolBarTitle("商家报表");
        initExtras();
        adapter = new MerchantsReportAdapter(this, datas);
        rvReport.setLayoutManager(new LinearLayoutManager(this));
        rvReport.setAdapter(adapter);
        if (userInfoBean != null) {
            GlideApp.with(this).load(userInfoBean.getUserPhoto()).into(ivUserImg);
            tvIds.setText(userInfoBean.getUserId() + "");
            tvName.setText(userInfoBean.getUserName());
            tvRebate.setText(userInfoBean.getUserIncome() + "元");
            tvIntegral.setText(userInfoBean.getUserScore());
            getBusinessReport();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_merchants_report;
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
     * 获取商家的报表
     */
    private void getBusinessReport() {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("shopid", userInfoBean.getUserId());
        OkGo.<String>post(HostUrl.URL_UCERNTER_SHOPCONSUMERREPORT)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            JSONArray array = obj.optJSONArray("data");
                            if (array != null) {
                                List<ConsumerReportBean> templist = JSON.parseArray(array.toString(), ConsumerReportBean.class);
                                if (templist != null) {
                                    datas.clear();
                                    datas.addAll(templist);
                                    adapter.notifyDataSetChanged();
                                    计算总金额();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 计算总消费金额和卡金额
     */
    private void 计算总金额() {
        double total = 0;
        for (ConsumerReportBean item : datas) {
            total = total + item.getTotalMoney();
        }

        tvMemberTotalCost.setText(total + "");
    }

}
