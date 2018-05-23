package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.main.activity.MainActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IShopStayContract;
import com.ahxd.lingyuangou.ui.mine.presenter.ShopStayPresenter;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.TimeCount;
import com.ahxd.lingyuangou.utils.ToastUtils;

import java.sql.Time;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/9.
 */

public class ShopStayActivity extends BaseActivity implements IShopStayContract.IShopStayView {


    @BindView(R.id.et_mine_shop_stay_name)
    EditText etMineShopStayName;
    @BindView(R.id.et_mine_shop_stay_tel)
    EditText etMineShopStayTel;
    @BindView(R.id.et_mine_shop_stay_shop_invite)
    EditText etMineShopStayShopInvite;
    @BindView(R.id.et_mine_shop_stay_shop_code)
    EditText etMineShopStayShopCode;
    @BindView(R.id.btn_mine_shop_stay_get_code)
    Button btnMineShopStayGetCode;
    @BindView(R.id.et_mine_shop_stay_remake)
    EditText etMineShopStayRemake;
    @BindView(R.id.btn_mine_shop_stay_commit)
    Button btnMineShopStayCommit;

    private ShopStayPresenter mPresenter;

    private TimeCount mTimeCount;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("商铺入住");
        mTimeCount = new TimeCount(60000, 1000, btnMineShopStayGetCode);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter = new ShopStayPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_stay;
    }

    @OnClick({R.id.btn_mine_shop_stay_get_code, R.id.btn_mine_shop_stay_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_mine_shop_stay_get_code:
                if (TextUtils.isEmpty(etMineShopStayTel.getText().toString().trim())) {
                    ToastUtils.showShort(this, etMineShopStayTel.getHint());
                    return;
                }
                mPresenter.getCode(getText(etMineShopStayTel));
                break;
            case R.id.btn_mine_shop_stay_commit:
                if (validate()) {
                    mPresenter.submitApply(getText(etMineShopStayShopInvite), getText(etMineShopStayName),
                            getText(etMineShopStayTel), getText(etMineShopStayRemake), getText(etMineShopStayShopCode));
                }
                break;
        }
    }

    @Override
    public void showGetCode(String msg) {
        ToastUtils.showShort(this, msg);
        mTimeCount.start();
    }

    @Override
    public void showSubmit(String msg) {
        ToastUtils.showShort(this, msg);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimeCount != null) {
            mTimeCount.onFinish();
        }
    }

    private String getText(EditText editText) {
        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            return null;
        } else {
            return editText.getText().toString().trim();
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(getText(etMineShopStayName))) {
            ToastUtils.showShort(this, etMineShopStayName.getHint());
            return false;
        }
        if (TextUtils.isEmpty(getText(etMineShopStayTel))) {
            ToastUtils.showShort(this, etMineShopStayTel.getHint());
            return false;
        }
//        if (TextUtils.isEmpty(getText(etMineShopStayShopCode))) {
//            ToastUtils.showShort(this, etMineShopStayShopCode.getHint());
//            return false;
//        }
        return true;
    }

}
