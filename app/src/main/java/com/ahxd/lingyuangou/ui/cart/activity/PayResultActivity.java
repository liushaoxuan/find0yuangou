package com.ahxd.lingyuangou.ui.cart.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.ui.main.activity.MainActivity;
import com.ahxd.lingyuangou.ui.mine.activity.OrderListActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/16.
 */

public class PayResultActivity extends BaseActivity {

    @BindView(R.id.tv_pay_result_tips)
    TextView tvPayResultTips;
    @BindView(R.id.btn_pay_result_order)
    Button btnPayResultOrder;
    @BindView(R.id.btn_pay_result_home)
    Button btnPayResultHome;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("支付结果");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_success;
    }

    @OnClick({R.id.btn_pay_result_order, R.id.btn_pay_result_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pay_result_order:
                Intent orderIntent = new Intent(this, OrderListActivity.class);
                orderIntent.putExtra("type",getIntent().getStringExtra("type"));
                startActivity(orderIntent);
                finish();
                break;
            case R.id.btn_pay_result_home:
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                finish();
                break;
        }
    }
}
