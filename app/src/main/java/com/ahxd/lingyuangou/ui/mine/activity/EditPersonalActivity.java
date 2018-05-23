package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.bean.SexBean;
import com.ahxd.lingyuangou.listener.OnFileUploadListener;
import com.ahxd.lingyuangou.ui.mine.contract.IEditPersonalContract;
import com.ahxd.lingyuangou.ui.mine.presenter.EditPersonalPresenter;
import com.ahxd.lingyuangou.utils.GlideApp;
import com.ahxd.lingyuangou.utils.SoftInputUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.utils.UploadUtils;
import com.ahxd.lingyuangou.widget.CircleImageView;
import com.ahxd.lingyuangou.widget.wheel.TimePickerView;
import com.ahxd.lingyuangou.widget.wheel.WheelUtils;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;

/**
 * Created by Mao on 2018/1/14.
 */

public class EditPersonalActivity extends BaseActivity implements IEditPersonalContract.IEditPersonalView {

    @BindView(R.id.iv_header_pic)
    CircleImageView ivHeaderPic;
    @BindView(R.id.ll_personal_edit_header)
    LinearLayout llPersonalEditHeader;
    @BindView(R.id.et_personal_edit_name)
    EditText etPersonalEditName;
    @BindView(R.id.et_personal_edit_sex)
    EditText etPersonalEditSex;
    @BindView(R.id.et_personal_edit_age)
    EditText etPersonalEditAge;
    @BindView(R.id.et_personal_edit_phone)
    EditText etPersonalEditPhone;
    @BindView(R.id.et_personal_edit_weixin)
    EditText etPersonalEditWeixin;

    private static ArrayList<SexBean> mSexs = new ArrayList<>();
    private int mSex = 0;
    private String mHeaderImage;
    private boolean isUploading = false;

    private EditPersonalPresenter mPresenter;

    static {
        mSexs.add(new SexBean("保密", 0));
        mSexs.add(new SexBean("男", 1));
        mSexs.add(new SexBean("女", 2));
    }

    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("编辑资料");
        setToolbarSubTitle("完成");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        try {
            String userInfo = getIntent().getStringExtra("userInfo");
            if (null != userInfo) {
                JSONObject json = new JSONObject(userInfo);
                etPersonalEditName.setText(json.optString("userName"));
                switch (json.optInt("userSex")) {
                    case 0:
                        etPersonalEditSex.setText("保密");
                        break;
                    case 1:
                        etPersonalEditSex.setText("男");
                        break;
                    case 2:
                        etPersonalEditSex.setText("女");
                        break;
                }
                mHeaderImage = json.optString("userPhoto");
                etPersonalEditAge.setText(json.optString("brithday"));
                etPersonalEditPhone.setText(json.optString("userPhone"));
                etPersonalEditWeixin.setText(json.optString("userWeixin"));
                GlideApp.with(this).load(json.optString("userPhoto"))
                        .placeholder(R.mipmap.ic_mine_person_header_normal)
                        .error(R.mipmap.ic_mine_person_header_normal)
                        .into(ivHeaderPic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mPresenter = new EditPersonalPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_info_edit;
    }

    @Override
    protected void onMenuClicked() {
        if (validate()) {
            mPresenter.submitPersonal(getText(etPersonalEditName), mHeaderImage, getText(etPersonalEditAge),
                    getText(etPersonalEditPhone), getText(etPersonalEditWeixin), mSex);
        }
    }

    @OnClick({R.id.ll_personal_edit_header, R.id.et_personal_edit_age, R.id.et_personal_edit_sex})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ll_personal_edit_header:
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(this, PhotoPicker.REQUEST_CODE);
                break;
            case R.id.et_personal_edit_age:
                chooseDate();
                break;
            case R.id.et_personal_edit_sex:
                chooseSex();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PhotoPicker.REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                GlideApp.with(this).load(photos.get(0)).into(ivHeaderPic);
                UploadUtils.uploadFile("users", photos.get(0), new OnFileUploadListener() {
                    @Override
                    public void onStart() {
                        isUploading = true;
                        ToastUtils.showShort(EditPersonalActivity.this, "开始上传...");
                    }

                    @Override
                    public void onProgress(int progress) {

                    }

                    @Override
                    public void onComplete(String url) {
                        isUploading = false;
                        mHeaderImage = url;
                        ToastUtils.showShort(EditPersonalActivity.this, "完成上传!");
                    }
                });
            }
        }
    }

    @Override
    public void showSubmitPersonal(String msg) {
        ToastUtils.showShort(this, msg);
        setResult(RESULT_OK);
        finish();
    }

    private void chooseDate() {
        SoftInputUtils.hideSoftInput(this, getWindow().getDecorView());
        WheelUtils.alertTimerPicker(EditPersonalActivity.this,
                TimePickerView.Type.YEAR_MONTH_DAY, "yyyy-MM-dd",
                new WheelUtils.TimerPickerCallBack() {
                    @Override
                    public void onTimeSelect(String date) {
                        etPersonalEditAge.setText(date);
                    }
                });
    }

    private void chooseSex() {
        SoftInputUtils.hideSoftInput(this, getWindow().getDecorView());
        WheelUtils.alertBottomWheel(EditPersonalActivity.this, mSexs, new WheelUtils.OnWheelViewClick() {
            @Override
            public void onClick(View view, int position) {
                mSex = mSexs.get(position).getType();
                etPersonalEditSex.setText(mSexs.get(position).getSex());
            }
        });
    }

    private String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    private boolean validate() {
        if (isUploading) {
            ToastUtils.showShort(this, "正在上传头像，请等待上传完成。");
            return false;
        }
        if (TextUtils.isEmpty(getText(etPersonalEditName))) {
            ToastUtils.showShort(this, etPersonalEditName.getText());
            return false;
        }
        if (TextUtils.isEmpty(getText(etPersonalEditPhone))) {
            ToastUtils.showShort(this, etPersonalEditPhone.getText());
            return false;
        }
        return true;
    }

}
