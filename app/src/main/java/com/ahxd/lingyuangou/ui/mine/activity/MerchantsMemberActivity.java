package com.ahxd.lingyuangou.ui.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.MerchantsMemberBean;
import com.ahxd.lingyuangou.bean.UserInfoBean;
import com.ahxd.lingyuangou.callback.MyStringCallBack;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.adapter.MerchantsMemberAdapter;
import com.ahxd.lingyuangou.utils.GlideApp;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.SmothScrollUtil;
import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商家会员
 */
public class MerchantsMemberActivity extends BaseActivity {


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
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<MerchantsMemberBean> datas;
    private MerchantsMemberAdapter adapter;

    /**
     * 用户数据
     */
    private UserInfoBean userInfoBean;

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
        datas = new ArrayList<>();
        setToolBarTitle("商家会员");
        adapter = new MerchantsMemberAdapter(this, datas);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
        initExtras();
        if (userInfoBean != null) {
            GlideApp.with(this).load(userInfoBean.getUserPhoto()).into(ivUserImg);
            tvName.setText(userInfoBean.getUserName());
            tvRebate.setText(userInfoBean.getUserIncome() + "元");
            tvIntegral.setText(userInfoBean.getUserScore());
            获取商家会员();
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_merchants_member;
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
     * 获取商家名下所有会员
     */
    private void 获取商家会员() {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(this, Constant.KEY_TOKEN, ""));
        params.put("shopid", userInfoBean.getUserId());
        OkGo.<String>get(HostUrl.URL_UCERNTER_GETSHOPMEMBERLIST)
                .params(params)
                .execute(new MyStringCallBack(this) {
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            JSONObject obj = new JSONObject(body);
                            List<MerchantsMemberBean> templist = JSON.parseArray(obj.optString("data").toString(), MerchantsMemberBean.class);
                            if (templist != null) {
                                datas.clear();
                                datas.addAll(templist);
                                adapter.notifyDataSetChanged();
//                                计算总金额();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

//    /**
//     * 计算总消费金额和卡金额
//     */
//    private void 计算总金额() {
//        double total = 0;
//        double cartotal = 0;
//        for (MerchantsMemberBean item : datas) {
//            total = total + item.getTotalMoney();
//            cartotal = cartotal + item.getUserCardMoney();
//        }
//
//        tvMemberTotalCost.setText(total + "");
//        tvMemberCartAmount.setText(cartotal + "");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
