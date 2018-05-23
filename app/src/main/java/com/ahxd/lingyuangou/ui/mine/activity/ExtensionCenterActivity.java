package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IExtensionCenterContract;
import com.ahxd.lingyuangou.ui.mine.presenter.ExtensionCenterPresenter;
import com.ahxd.lingyuangou.ui.mine.presenter.WalletPresenter;
import com.ahxd.lingyuangou.widget.PicTextRightItem;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by wpc on 2018/1/17.
 */

public class ExtensionCenterActivity extends BaseActivity implements IExtensionCenterContract.IExtensionCenterView{
    @BindView(R.id.ptr_my_members)
    PicTextRightItem ptrMyMembers;
    @BindView(R.id.ptr_membership_income)
    PicTextRightItem ptrMembershipIncome;
    @BindView(R.id.ptr_member_sharing)
    PicTextRightItem ptrMemberSharing;
    @BindView(R.id.ptr_my_business_card)
    PicTextRightItem ptrMyBusinessCard;
    @BindView(R.id.tv_user_recommend)
    TextView tvUserRecommend;

    private ExtensionCenterPresenter mPresenter;
    @Override
    protected void initListener() {
        mPresenter = new ExtensionCenterPresenter(this);
        mPresenter.getExtensionCenter();
    }
    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("推广中心");
    }
    @Override
    protected void initData() {

    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_extension_center;
    }
    @OnClick({R.id.ptr_my_members,R.id.ptr_membership_income,R.id.ptr_member_sharing,R.id.ptr_my_business_card})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ptr_my_members:
                Intent myMembersActivityIntent = new Intent(this, MyMembersActivity.class);
                startActivity(myMembersActivityIntent);
                break;
            case R.id.ptr_membership_income:
                Intent marketingRevenueIntent = new Intent(this, MarketingRevenueRecordActivity.class);
                startActivity(marketingRevenueIntent);
                break;
            case R.id.ptr_member_sharing:
                mPresenter.getShare();
                break;
            case R.id.ptr_my_business_card:
                Intent businessCardIntent = new Intent(this, MyBusinessCardActivity.class);
                startActivity(businessCardIntent);
                break;
        }
    }
    @Override
    public void showExtensionCenter(JSONObject data) {
        if (null != data) {
            tvUserRecommend.setText(data.optString("userRecommend"));
            ptrMyMembers.setLabelText("我的会员（当前"+data.optString("userRecommendUsers")+"人)");
//            ptrMyMembers.setTipsText().setText(data.optString("userRecommendUsers"));
        }
    }

    @Override
    public void showShare(JSONObject data) {
        if(null!=data){
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(data.optString("codeTitle"));
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(data.optString("codeUrl"));
        // text是分享文本，所有平台都需要这个字段
        oks.setText(data.optString("codeDesc"));
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/assets/ic_app_start.png");//确保SDcard下面存在此张图片
        oks.setImageUrl(data.optString("userPhoto"));
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(data.optString("codeUrl"));
//        //没有下面这两个微信分享的就是文本
//        oks.setSite(data.optString("codeTitle"));
//        oks.setSiteUrl(data.optString("codeUrl"));
            // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment(data.optString("codeDesc"));
        // 启动分享GUI
        oks.show(this);
        }
    }
}
