package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.ui.mine.contract.IPersonalInfoContract;
import com.ahxd.lingyuangou.ui.mine.presenter.PersonalInfoPresenter;
import com.ahxd.lingyuangou.utils.GlideApp;
import com.ahxd.lingyuangou.widget.CircleImageView;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mao on 2018/1/13.
 */

public class PersonalInfoActivity extends BaseActivity implements IPersonalInfoContract.IPersonalInfoView {

    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_personal_info_header_pic)
    CircleImageView ivPersonalInfoHeaderPic;
    @BindView(R.id.tv_personal_info_name)
    TextView tvPersonalInfoName;
    @BindView(R.id.tv_personal_info_sex)
    TextView tvPersonalInfoSex;
    @BindView(R.id.tv_personal_info_age)
    TextView tvPersonalInfoAge;
    @BindView(R.id.tv_personal_info_phone)
    TextView tvPersonalInfoPhone;
    @BindView(R.id.tv_personal_info_weixin)
    TextView tvPersonalInfoWeixin;

    private PersonalInfoPresenter mPresenter;
    private String mUserInfo;

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("个人资料");
        setToolbarSubTitle("编辑");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter = new PersonalInfoPresenter(this);
        mPresenter.getPersonalInfo();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getPersonalInfo();
    }

    @Override
    public void showPersonalInfo(JSONObject data) {
        if (null != data) {
            mUserInfo = data.toString();
            tvPersonalInfoName.setText(data.optString("userName"));
            tvPersonalInfoAge.setText(data.optString("brithday"));
            switch (data.optInt("userSex")) {
                case 0:
                    tvPersonalInfoSex.setText("保密");
                    break;
                case 1:
                    tvPersonalInfoSex.setText("男");
                    break;
                case 2:
                    tvPersonalInfoSex.setText("女");
                    break;
            }
            tvPersonalInfoPhone.setText(data.optString("userPhone"));
            tvPersonalInfoWeixin.setText(data.optString("userWeixin"));
            GlideApp.with(this).load(data.optString("userPhoto"))
                    .placeholder(R.mipmap.ic_mine_person_header_normal)
                    .error(R.mipmap.ic_mine_person_header_normal)
                    .into(ivPersonalInfoHeaderPic);
        }
    }

    @Override
    protected void onMenuClicked() {
        Intent intent = new Intent(this, EditPersonalActivity.class);
        intent.putExtra("userInfo", mUserInfo);
        startActivityForResult(intent, Constant.REQ_EDIT_PERSONAL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_EDIT_PERSONAL && resultCode == RESULT_OK) {
            mPresenter.getPersonalInfo();
        }
    }
}
