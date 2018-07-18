package com.ahxd.lingyuangou.ui.mine.activity;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IMyBusinessCardContract;
import com.ahxd.lingyuangou.ui.mine.presenter.MyBusinessCardPresenter;
import com.ahxd.lingyuangou.utils.DeviceUtils;
import com.ahxd.lingyuangou.utils.GlideApp;
import com.ahxd.lingyuangou.widget.CircleImageView;
import com.ahxd.lingyuangou.zxing.encoding.EncodingHandler;
import com.google.zxing.WriterException;

import org.json.JSONObject;

import butterknife.BindView;

/**
 * Created by wpc on 2018/1/16.
 */

public class MyBusinessCardActivity extends BaseActivity implements IMyBusinessCardContract.IMyBusinessCardView{


    @BindView(R.id.iv_mine_person_header)
    CircleImageView ivMinePersonHeader;
    @BindView(R.id.tv_mine_person_name)
    TextView tvMinePersonName;
    @BindView(R.id.im_my_card)
    ImageView imMyCard;


    private MyBusinessCardPresenter mPresenter;
    private JSONObject data;
    private boolean isRecharge=true;
    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("我的名片");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getMyBusinessCard();
    }
    @Override
    protected void initData() {
        mPresenter = new MyBusinessCardPresenter(this);
        mPresenter.getMyBusinessCard();
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_my_business_card;
    }


    @Override
    public void showMyBusinessCard(JSONObject data) {
        if (null != data) {
            this.data=data;
            tvMinePersonName.setText(data.optString("userName"));
            GlideApp.with(this).load(data.optString("userPhoto"))
                    .placeholder(R.mipmap.ic_mine_person_header_normal)
                    .error(R.mipmap.ic_mine_person_header_normal)
                    .into(ivMinePersonHeader);
            try {
                Bitmap bitmap= EncodingHandler.createQRCode(data.optString("codeUrl"),(DeviceUtils.getDeviceWidth(this)*2)/3);
                imMyCard.setImageBitmap(bitmap);
               } catch (WriterException e) {
                e.printStackTrace();
            }

        }
    }
}
