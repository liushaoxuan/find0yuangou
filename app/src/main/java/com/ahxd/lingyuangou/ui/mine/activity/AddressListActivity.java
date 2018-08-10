package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.AddressBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.listener.OnEditAddressListener;
import com.ahxd.lingyuangou.ui.cart.activity.OnlinePayActivity;
import com.ahxd.lingyuangou.ui.mine.adapter.AddressListAdapter;
import com.ahxd.lingyuangou.ui.mine.contract.IAddressContract;
import com.ahxd.lingyuangou.ui.mine.presenter.AddressListPresenter;
import com.ahxd.lingyuangou.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mao Zhendong on 2018/1/11.
 */

public class AddressListActivity extends BaseActivity implements IAddressContract.IAddressListView, OnEditAddressListener {

    @BindView(R.id.lv_mine_address_list)
    ListView lvMineAddressList;
    @BindView(R.id.btn_mine_address_add)
    Button btnMineAddressAdd;

    private AddressListAdapter mAdapter;
    private AddressListPresenter mPresenter;
    private String mStartFrom;
    private List<AddressBean> list;
    private AddressBean bean;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("我的地址");
        mAdapter = new AddressListAdapter(this, this);
        lvMineAddressList.setAdapter(mAdapter);
        lvMineAddressList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                bean = list.get(position);
                for (int i = 0; i < list.size(); i++) {
                    if (i == position) {
                        if (bean.getIsDefault() == 1) {
                            bean.setIsDefault(0);
                        } else {
                            bean.setIsDefault(1);
                        }
                    } else {
                        list.get(i).setIsDefault(0);
                    }
                }
                mPresenter.setDefaultAddress(bean.getAddressId());
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mStartFrom = getIntent().getStringExtra("start_from");
        mPresenter = new AddressListPresenter(this);
        mPresenter.getAddressList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_list;
    }

    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getAddressList();
    }

    @OnClick(R.id.btn_mine_address_add)
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_mine_address_add:
                Intent intent = new Intent(this, EditAddressActivity.class);
                startActivityForResult(intent, Constant.REQ_EDIT_ADDRESS);
                break;
        }
    }

    @Override
    public void showAddressList(List<AddressBean> list) {
        mAdapter.setData(list);
        this.list = list;
    }

    @Override
    public void showDeleteAddress(String msg) {
        ToastUtils.showShort(this, msg);
        mPresenter.getAddressList();
    }

    @Override
    public void showDefaultAddress(String msg) {
        ToastUtils.showShort(this, msg);
        if ("设置成功".equals(msg)) {
            mAdapter.notifyDataSetChanged();
            if (mStartFrom != null && "online_pay".equals(mStartFrom)) {
                Intent intent = new Intent();
                intent.putExtra("addressBean", bean);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    @Override
    public void onDeleteAddress(AddressBean bean) {
        if (null != bean) {
            mPresenter.onDeleteAddress(bean.getAddressId());
        }
    }

    @Override
    public void onEditAddress(AddressBean bean) {
        Intent intent = new Intent(this, EditAddressActivity.class);
        intent.putExtra("addressBean", bean);
        startActivityForResult(intent, Constant.REQ_EDIT_ADDRESS);
    }

    @Override
    public void onSetDefault(AddressBean bean) {
        if (null != bean) {
            mPresenter.setDefaultAddress(bean.getAddressId());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_EDIT_ADDRESS && resultCode == RESULT_OK) {
            mPresenter.getAddressList();
        }
    }


}
