package com.ahxd.lingyuangou.ui.mine.activity;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IEvaluateContract;
import com.ahxd.lingyuangou.ui.mine.presenter.EvaluatePresenter;
import com.ahxd.lingyuangou.utils.GlideApp;
import com.ahxd.lingyuangou.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mao Zhendong on 2018/1/16.
 */

public class EvaluateActivity extends BaseActivity implements IEvaluateContract.IEvaluateView {


    @BindView(R.id.iv_evaluate_image)
    ImageView ivEvaluateImage;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.rbar_evaluate_goods)
    RatingBar rbarEvaluateGoods;
    @BindView(R.id.tv_evaluate_name)
    TextView tvEvaluateName;
    @BindView(R.id.et_evaluate_tip)
    EditText etEvaluateTip;
    @BindView(R.id.rbar_evaluate_server)
    RatingBar rbarEvaluateServer;
    @BindView(R.id.rbar_evaluate_send)
    RatingBar rbarEvaluateSend;
    @BindView(R.id.btn_evaluate_submit)
    Button btnEvaluateSubmit;

    private EvaluatePresenter mPresenter;
    private String mOrderId;
    private String mShopId;
    private String mShopImg;
    private String mShopName;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("评价");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mOrderId = getIntent().getStringExtra("orderId");
        mShopId = getIntent().getStringExtra("shopId");
        mShopImg = getIntent().getStringExtra("shopImg");
        mShopName = getIntent().getStringExtra("shopName");

        tvEvaluateName.setText(mShopName);
        GlideApp.with(this).load(mShopImg).into(ivEvaluateImage);

        mPresenter = new EvaluatePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluate;
    }

    @OnClick(R.id.btn_evaluate_submit)
    public void onViewClicked() {
        if (validate()) {
            // 商铺评价时候：goodsId= 0
            mPresenter.submitEvaluate(mOrderId, mShopId, "0", getText(etEvaluateTip),
                    String.valueOf(rbarEvaluateGoods.getRating()), String.valueOf(rbarEvaluateServer.getRating()),
                    String.valueOf(rbarEvaluateSend.getRating()));
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(etEvaluateTip.getText().toString().trim())) {
            ToastUtils.showShort(this, etEvaluateTip.getHint());
            return false;
        }
        return true;
    }

    private String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    @Override
    public void showSubmitResult(String msg) {
        ToastUtils.showShort(this, msg);
        setResult(RESULT_OK);
        finish();
    }
}
