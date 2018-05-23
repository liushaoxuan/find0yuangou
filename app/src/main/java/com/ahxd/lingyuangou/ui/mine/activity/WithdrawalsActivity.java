package com.ahxd.lingyuangou.ui.mine.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.RechargeBean;
import com.ahxd.lingyuangou.bean.WithdrawalsAccountBean;
import com.ahxd.lingyuangou.ui.home.activity.RechargeActivity;
import com.ahxd.lingyuangou.ui.home.adapter.RechargeNumListAdapter;
import com.ahxd.lingyuangou.ui.home.contract.IRechargeContract;
import com.ahxd.lingyuangou.ui.home.presenter.RechargePresenter;
import com.ahxd.lingyuangou.ui.mine.adapter.WithdrawalsAccountAdapter;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWithdrawalsContract;
import com.ahxd.lingyuangou.ui.mine.presenter.WalletPresenter;
import com.ahxd.lingyuangou.ui.mine.presenter.WithdrawalsPresenter;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.PayResult;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.ListViewForScrollView;
import com.ahxd.lingyuangou.widget.PicTextRightItem;
import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/9.
 */

public class WithdrawalsActivity extends BaseActivity implements IWithdrawalsContract.IWithdrawalsView {



    @BindView(R.id.tv_add_present_account)
    TextView tvAddPresentAccount;
    @BindView(R.id.ll_withdrawals_addview)
    LinearLayout llWithdrawalsAddview;
    @BindView(R.id.ll_yue)
    LinearLayout llYuE;
    @BindView(R.id.rb_recharge_zhifubao)
    ImageView imRechargeYuE;
    @BindView(R.id.lv_account)
    ListView lvAccount;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.btn_withdrawals)
    Button btnWithdrawals;

    private WithdrawalsAccountAdapter adapter;
    private WithdrawalsPresenter mPresenter;
    private String accId;
    private ArrayList<WithdrawalsAccountBean> list=new ArrayList<>();
    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("提现");
        etMoney.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

    }

    @Override
    protected void initListener() {
        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                accId=list.get(i).getId();
                imRechargeYuE.setSelected(false);
                adapter.refresh(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getWithdrawalsList();
    }

    @OnClick({R.id.tv_add_present_account,R.id.ll_yue,R.id.btn_withdrawals})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add_present_account:
                Intent intent = new Intent(this, AddPresentAccountActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_yue:
                accId="0";
                imRechargeYuE.setSelected(true);
                if (null!=adapter){
                    adapter.refresh(-1);
                }

                break;
            case R.id.btn_withdrawals:
                if (etMoney.getText().toString().equals("")){
                    ToastUtils.showShort(this, "请输入金额！");
                    return;
                }
                if (accId==null){
                    ToastUtils.showShort(this, "请选择到款方式！");
                    return;
                }
              mPresenter.setWithdrawals(etMoney.getText().toString(),accId);
                break;
        }
    }

    @Override
    protected void initData() {
        mPresenter = new WithdrawalsPresenter(this);

        mPresenter.getWithdrawalsList();
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_withdrawals;
    }

    @Override
    public void showWithdrawalsList(ArrayList<WithdrawalsAccountBean> list) {
        this.list=list;
        adapter=new WithdrawalsAccountAdapter(this,this.list);
        lvAccount.setAdapter(adapter);

    }

    @Override
    public void showWithdrawals(JSONObject data) {
        ToastUtils.showShort(this, "提现成功！");
        finish();
//            tvUserMoney.setText(data.optString("userMoney"));
    }

}