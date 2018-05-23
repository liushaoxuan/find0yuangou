package com.ahxd.lingyuangou.ui.mine.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.BankBean;
import com.ahxd.lingyuangou.bean.DicAddressBean;
import com.ahxd.lingyuangou.ui.mine.contract.IAddBankCardContract;
import com.ahxd.lingyuangou.ui.mine.contract.IAlipayWechatContract;
import com.ahxd.lingyuangou.ui.mine.contract.IEditAddressContract;
import com.ahxd.lingyuangou.ui.mine.presenter.AddBankCardPresenter;
import com.ahxd.lingyuangou.ui.mine.presenter.EditAddressPresenter;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.PicTextRightItem;
import com.ahxd.lingyuangou.widget.wheel.WheelUtils;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wpc on 2018/1/16.
 */

public class AddBankCardAccountActivity extends BaseActivity implements IAddBankCardContract.IAddBankCardView{

    @BindView(R.id.et_address_edit_province)
    TextView etAddressEditProvince;
    @BindView(R.id.rl_province)
    RelativeLayout rlProvince;
    @BindView(R.id.et_address_edit_city)
    TextView etAddressEditCity;
    @BindView(R.id.rl_city)
    RelativeLayout rlCity;
    @BindView(R.id.et_address_edit_area)
    TextView etAddressEditArea;
    @BindView(R.id.rl_area)
    RelativeLayout rlArea;
    @BindView(R.id.rl_bank)
    RelativeLayout rlBank;
    @BindView(R.id.et_edit_bank)
    TextView etEditBank;


    @BindView(R.id.et_add_bank_name)
    EditText etAddBankName;
    @BindView(R.id.et_add_bank_phone)
    EditText etAddBankPhone;
    @BindView(R.id.et_add_bank_id)
    EditText etAddBankID;
    @BindView(R.id.et_add_bank_open)
    EditText etAddBankOpen;
    @BindView(R.id.et_add_bank_cardholder)
    EditText etAddBankCardholder;
    @BindView(R.id.et_add_bank_card)
    EditText etAddBankCard;
    @BindView(R.id.btn_add_account)
    Button btnAddAccount;

    private String mProvinceId;
    private String mCityId;
    private String mAreaId;
    private String mBankId;
    private AddBankCardPresenter mPresenter;
    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("添加银行卡");
    }

    @Override
    protected void initListener() {

    }
    @OnClick({R.id.rl_province, R.id.rl_city, R.id.rl_area,R.id.rl_bank,R.id.btn_add_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_bank:
                mPresenter.getBank();
                break;
            case R.id.rl_province:
                etAddressEditCity.setText(null);
                etAddressEditArea.setText(null);
                mPresenter.getProvince();
                break;
            case R.id.rl_city:
                if (mProvinceId == null) {
                    ToastUtils.showShort(this, "请先选择省份！");
                    return;
                }
                etAddressEditArea.setText(null);
                mPresenter.getCity(mProvinceId);
                break;
            case R.id.rl_area:
                if (mProvinceId == null) {
                    ToastUtils.showShort(this, "请先选择省份！");
                    return;
                }
                if (mCityId == null) {
                    ToastUtils.showShort(this, "请先选择市区！");
                    return;
                }
                mPresenter.getArea(mCityId);
                break;
            case R.id.btn_add_account:
                if(etAddBankName.getText().toString().equals("")){
                    ToastUtils.showShort(this, "请填写姓名！");
                }
                if(etAddBankPhone.getText().toString().equals("")){
                    ToastUtils.showShort(this, "请填写电话！");
                }
                if(etAddBankID.getText().toString().equals("")){
                    ToastUtils.showShort(this, "请填写身份证！");
                }
                if(etAddBankOpen.getText().toString().equals("")){
                    ToastUtils.showShort(this, "请填写开户行！");
                }
                if(etAddBankCardholder.getText().toString().equals("")){
                    ToastUtils.showShort(this, "请填写持卡人姓名！");
                }
                if(etAddBankCard.getText().toString().equals("")){
                    ToastUtils.showShort(this, "请填写卡号！");
                }
                if (mBankId == null) {
                    ToastUtils.showShort(this, "请先选择银行！");
                    return;
                }
                if (mProvinceId == null) {
                    ToastUtils.showShort(this, "请先选择省份！");
                    return;
                }
                if (mProvinceId == null) {
                    ToastUtils.showShort(this, "请先选择省份！");
                    return;
                }
                if (mAreaId == null) {
                    ToastUtils.showShort(this, "请先选择区域！");
                    return;
                }

                mPresenter.setConfig("",mBankId,etAddBankCard.getText().toString(),etAddBankCardholder.getText().toString()
                ,mAreaId,etAddBankOpen.getText().toString(),etAddBankName.getText().toString(),etAddBankPhone.getText().toString(),
                        etAddBankID.getText().toString());
                break;
        }
    }

    @Override
    protected void initData() {
        mPresenter = new AddBankCardPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_add_bank_card_account;
    }


    @Override
    public void showConfig(JSONObject data) {
        ToastUtils.showShort(this, "添加成功！");
            finish();
    }

    @Override
    public void showBank(final ArrayList<BankBean> list) {
        WheelUtils.alertBottomWheel(this, list, new WheelUtils.OnWheelViewClick() {
            @Override
            public void onClick(View view, int position) {
                String bankName = list.get(position).getBankName();
                mBankId= list.get(position).getBankId();
                etEditBank.setText(bankName);
            }
        });
    }

    @Override
    public void showProvince(final ArrayList<DicAddressBean> list) {
        WheelUtils.alertBottomWheel(this, list, new WheelUtils.OnWheelViewClick() {
            @Override
            public void onClick(View view, int position) {
                String area = list.get(position).getAreaName();
                mProvinceId = list.get(position).getAreaId();
                etAddressEditProvince.setText(area);
            }
        });
    }

    @Override
    public void showCity(final ArrayList<DicAddressBean> list) {
        WheelUtils.alertBottomWheel(this, list, new WheelUtils.OnWheelViewClick() {
            @Override
            public void onClick(View view, int position) {
                String area = list.get(position).getAreaName();
                mCityId = list.get(position).getAreaId();
                etAddressEditCity.setText(area);
            }
        });
    }

    @Override
    public void showArea(final ArrayList<DicAddressBean> list) {
        WheelUtils.alertBottomWheel(this, list, new WheelUtils.OnWheelViewClick() {
            @Override
            public void onClick(View view, int position) {
                String area = list.get(position).getAreaName();
                mAreaId = list.get(position).getAreaId();
                etAddressEditArea.setText(area);
            }
        });
    }
}
