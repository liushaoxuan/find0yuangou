package com.ahxd.lingyuangou.ui.mine.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.AddressBean;
import com.ahxd.lingyuangou.bean.DicAddressBean;
import com.ahxd.lingyuangou.ui.mine.contract.IEditAddressContract;
import com.ahxd.lingyuangou.ui.mine.presenter.EditAddressPresenter;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.wheel.WheelUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.provider.ContactsContract.CommonDataKinds.StructuredPostal.CITY;

/**
 * Created by Mao Zhendong on 2018/1/12.
 * 编辑地址界面
 */

public class EditAddressActivity extends BaseActivity implements IEditAddressContract.IEditAddressView {


    @BindView(R.id.et_address_edit_receiver)
    EditText etAddressEditReceiver;
    @BindView(R.id.et_address_edit_phone)
    EditText etAddressEditPhone;
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
    @BindView(R.id.et_address_edit_detail)
    EditText etAddressEditDetail;
    @BindView(R.id.btn_address_edit_add)
    Button etAddressEditAdd;

    private AddressBean mAddressBean;
    private String mAddressId;
    private String mProvinceId;
    private String mCityId;
    private String mAreaId;
    private EditAddressPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mAddressBean = (AddressBean) getIntent().getSerializableExtra("addressBean");
        if (null != mAddressBean) {
            setToolBarTitle("编辑地址");
            etAddressEditReceiver.setText(mAddressBean.getUserName());
            etAddressEditPhone.setText(mAddressBean.getUserPhone());
            etAddressEditProvince.setText(mAddressBean.getProvince());
            etAddressEditCity.setText(mAddressBean.getCity());
            etAddressEditArea.setText(mAddressBean.getArea());
            etAddressEditDetail.setText(mAddressBean.getUserAddress());
            mAddressId = mAddressBean.getAddressId();
            mProvinceId = mAddressBean.getProvinceId();
            mCityId = mAddressBean.getCityId();
            mAreaId = mAddressBean.getAreaId();
        } else {
            setToolBarTitle("添加地址");
        }
        mPresenter = new EditAddressPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_edit;
    }

    @OnClick({R.id.rl_province, R.id.rl_city, R.id.rl_area, R.id.btn_address_edit_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.btn_address_edit_add:
                if (validate()) {
                    if (null == mAddressId) {
                        mPresenter.addAddress(getText(etAddressEditReceiver), getText(etAddressEditPhone),
                                mProvinceId, mCityId, mAreaId, getText(etAddressEditDetail));
                    } else {
                        mPresenter.modifyAddress(mAddressId, getText(etAddressEditReceiver), getText(etAddressEditPhone),
                                mProvinceId, mCityId, mAreaId, getText(etAddressEditDetail));
                    }
                }
                break;
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(getText(etAddressEditReceiver))) {
            ToastUtils.showShort(this, etAddressEditReceiver.getHint());
            return false;
        }
        if (TextUtils.isEmpty(getText(etAddressEditPhone))) {
            ToastUtils.showShort(this, etAddressEditPhone.getHint());
            return false;
        }
        if (TextUtils.isEmpty(etAddressEditProvince.getText().toString().trim())) {
            ToastUtils.showShort(this, "请选择省份！");
            return false;
        }
        if (TextUtils.isEmpty(etAddressEditCity.getText().toString().trim())) {
            ToastUtils.showShort(this, "请选择城市！");
            return false;
        }
        if (TextUtils.isEmpty(etAddressEditArea.getText().toString().trim())) {
            ToastUtils.showShort(this, "请选择区域！");
            return false;
        }
        if (TextUtils.isEmpty(getText(etAddressEditDetail))) {
            ToastUtils.showShort(this, etAddressEditDetail.getHint());
            return false;
        }
        return true;
    }


    private String getText(TextView textView) {
        if (TextUtils.isEmpty(textView.getText().toString().trim())) {
            return null;
        } else {
            return textView.getText().toString().trim();
        }
    }

    @Override
    public void showAddAddress(String msg) {
        ToastUtils.showShort(this, msg);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showModifyAddress(String msg) {
        ToastUtils.showShort(this, msg);
        setResult(RESULT_OK);
        finish();
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
