package com.ahxd.lingyuangou.ui.main.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseFragment;
import com.ahxd.lingyuangou.bean.UserInfoBean;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.constant.HostUrl;
import com.ahxd.lingyuangou.ui.mine.activity.AddressListActivity;
import com.ahxd.lingyuangou.ui.mine.activity.ExtensionCenterActivity;
import com.ahxd.lingyuangou.ui.mine.activity.FavoriteActivity;
import com.ahxd.lingyuangou.ui.mine.activity.LoginActivity;
import com.ahxd.lingyuangou.ui.mine.activity.MarketingApplyActivity;
import com.ahxd.lingyuangou.ui.mine.activity.MarketingCenterActivity;
import com.ahxd.lingyuangou.ui.mine.activity.MerchantsMemberActivity;
import com.ahxd.lingyuangou.ui.mine.activity.MerchantsOrderActivity;
import com.ahxd.lingyuangou.ui.mine.activity.MerchantsReportActivity;
import com.ahxd.lingyuangou.ui.mine.activity.MerchantsTopUpActivity;
import com.ahxd.lingyuangou.ui.mine.activity.MessageListActivity;
import com.ahxd.lingyuangou.ui.mine.activity.MineContactActivity;
import com.ahxd.lingyuangou.ui.mine.activity.MyBusinessCardActivity;
import com.ahxd.lingyuangou.ui.mine.activity.OrderListActivity;
import com.ahxd.lingyuangou.ui.mine.activity.PersonalInfoActivity;
import com.ahxd.lingyuangou.ui.mine.activity.PurchaseQualificatioActivity;
import com.ahxd.lingyuangou.ui.mine.activity.SettingActivity;
import com.ahxd.lingyuangou.ui.mine.activity.ShopStayActivity;
import com.ahxd.lingyuangou.ui.mine.activity.WalletActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IMineContract;
import com.ahxd.lingyuangou.ui.mine.presenter.MinePresenter;
import com.ahxd.lingyuangou.utils.GlideApp;
import com.ahxd.lingyuangou.utils.SPUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.utils.UserUtils;
import com.ahxd.lingyuangou.widget.CircleImageView;
import com.ahxd.lingyuangou.widget.PicTextRightItem;
import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/12/25.
 */

public class MineFragment extends BaseFragment implements IMineContract.IMineView {


    @BindView(R.id.iv_mine_setting)
    ImageView ivMineSetting;
    @BindView(R.id.iv_mine_message)
    ImageView ivMineMessage;
    @BindView(R.id.tv_message_number)
    TextView tvMessageNumber;
    @BindView(R.id.iv_mine_person_header)
    CircleImageView ivMinePersonHeader;
    @BindView(R.id.tv_mine_person_name)
    TextView tvMinePersonName;
    @BindView(R.id.tv_mine_person_login)
    TextView tvMinePersonLogin;
    @BindView(R.id.ll_mine_wallet)
    LinearLayout llMineWallet;
    @BindView(R.id.ll_mine_order)
    LinearLayout llMineOrder;
    @BindView(R.id.ll_mine_favorite)
    LinearLayout llMineFavorite;
    @BindView(R.id.ll_mine_spread)
    LinearLayout llMineSpread;
    @BindView(R.id.tv_mine_card_balance)
    TextView tvMineCardBalance;
    @BindView(R.id.tv_mine_ads_money)
    TextView tvMineAdsMoney;
    @BindView(R.id.tv_mine_money_points)
    TextView tvMineMoneyPoints;
    @BindView(R.id.ptr_mine_card)
    PicTextRightItem ptrMineCard;
    @BindView(R.id.ptr_mine_profile)
    PicTextRightItem ptrMineProfile;
    @BindView(R.id.ptr_mine_location)
    PicTextRightItem ptrMineLocation;
    @BindView(R.id.ptr_mine_market_center)
    PicTextRightItem ptrMineMarketCenter;
    @BindView(R.id.ptr_mine_purchase_qualificatio)
    PicTextRightItem ptrMinePurchaseQualificatio;
    @BindView(R.id.ptr_mine_merchant_charging_card)
    PicTextRightItem ptrMineMerchantChargingCard;
    @BindView(R.id.ptr_mine_merchant_members)
    PicTextRightItem ptrMineMerchantMembers;
    @BindView(R.id.ptr_mine_business_report)
    PicTextRightItem ptrMineBusinessReport;
    @BindView(R.id.ptr_mine_business_order)
    PicTextRightItem ptrMineBusinessOrder;
    @BindView(R.id.ptr_mine_online_service)
    PicTextRightItem ptrMineOnlineService;
    @BindView(R.id.ptr_mine_contact_us)
    PicTextRightItem ptrMineContactUs;
    @BindView(R.id.ptr_mine_shop_stay)
    PicTextRightItem ptrMineShopStay;
    private MinePresenter mPresenter;
    private int mApplyStatus = -9;
    private String mFailReason;
    JSONObject data;

    /**
     * 用户信息
     */
    UserInfoBean userInfoBean;

    @Override
    public void onResume() {
        super.onResume();
        if (!UserUtils.isLogin()) {
            tvMinePersonName.setVisibility(View.GONE);
            tvMinePersonLogin.setVisibility(View.VISIBLE);
            tvMineCardBalance.setText("--");
            tvMineAdsMoney.setText("--");
            tvMineMoneyPoints.setText("--");
            ptrMineShopStay.setTipsText(null);
            ivMinePersonHeader.setImageResource(R.mipmap.ic_mine_person_header_normal);
        } else {
            tvMinePersonName.setVisibility(View.VISIBLE);
            tvMinePersonLogin.setVisibility(View.GONE);
            mPresenter.getMyProfile();
        }
    }

    @Override
    protected View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_mine, null);
        mUnbinder = ButterKnife.bind(this, view);
        if (!UserUtils.isLogin()) {
            tvMinePersonName.setVisibility(View.GONE);
            tvMinePersonLogin.setVisibility(View.VISIBLE);
        } else {
            tvMinePersonName.setVisibility(View.VISIBLE);
            tvMinePersonLogin.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    protected void initData() {
        getConfig();
        mPresenter = new MinePresenter(this);

//        mPresenter.getMyProfile();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_LOGIN && resultCode == mContext.RESULT_OK) {
            mPresenter.getMyProfile();
        }
    }

    @Override
    public void showProfile(JSONObject data) {
        this.data = data;
        userInfoBean = JSON.parseObject(data.toString(), UserInfoBean.class);
        if (!UserUtils.isLogin()) {
            tvMinePersonName.setVisibility(View.GONE);
            tvMinePersonLogin.setVisibility(View.VISIBLE);
        } else {
            tvMinePersonName.setVisibility(View.VISIBLE);
            tvMinePersonLogin.setVisibility(View.GONE);
        }
        if (null != data.optString("messagesCount")) {
            tvMessageNumber.setText(data.optString("messagesCount"));
            if (data.optString("messagesCount").equals("0")) {
                tvMessageNumber.setVisibility(View.GONE);
            } else {
                tvMessageNumber.setVisibility(View.VISIBLE);
            }
        }


        tvMinePersonName.setText(data.optString("userName"));
        tvMineCardBalance.setText(data.optString("userMoney"));
        tvMineAdsMoney.setText(data.optString("userIncome"));
        tvMineMoneyPoints.setText(data.optString("userScore"));

        // 店铺入住审核
        JSONObject applyShop = data.optJSONObject("applyShop");
        mApplyStatus = applyShop.optInt("applyStatus");
        mFailReason = applyShop.optString("handleDesc");
        switch (mApplyStatus) {
            case -9:
                break;
            case 0:
                ptrMineShopStay.setTipsText("正在审核");
                break;
            case 1:
                ptrMineShopStay.setTipsText("审核成功");
                break;
            case -1:
                ptrMineShopStay.setTipsText("审核失败");
                break;
        }
        GlideApp.with(this).load(data.optString("userPhoto"))
                .placeholder(R.mipmap.ic_mine_person_header_normal)
                .error(R.mipmap.ic_mine_person_header_normal)
                .into(ivMinePersonHeader);
    }

    @Override
    public void showCustomService(JSONObject data) {
        if (null != data) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + data.opt("data"));
            intent.setData(uri);
            startActivity(intent);
        }
        //用intent启动拨打电话


    }

    @OnClick({R.id.iv_mine_setting, R.id.iv_mine_message, R.id.ll_mine_wallet, R.id.ll_mine_order,
            R.id.ll_mine_favorite, R.id.ll_mine_spread, R.id.ptr_mine_card, R.id.ptr_mine_profile,
            R.id.ptr_mine_location, R.id.ptr_mine_market_center, R.id.ptr_mine_online_service,
            R.id.ptr_mine_contact_us, R.id.ptr_mine_shop_stay, R.id.tv_mine_person_login,
            R.id.iv_mine_person_header,
            R.id.ptr_mine_purchase_qualificatio, R.id.ptr_mine_merchant_charging_card, R.id.ptr_mine_merchant_members, R.id.ptr_mine_business_report, R.id.ptr_mine_business_order})
    public void onViewClicked(View view) {
        if (!UserUtils.isLogin()) {
            ToastUtils.showShort(getActivity(), "请先登录");
            return;
        }
        switch (view.getId()) {
            case R.id.iv_mine_setting:
                Intent settingIntent = new Intent(mContext, SettingActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.iv_mine_message:
                Intent messageIntent = new Intent(mContext, MessageListActivity.class);
                if (null != data.optString("messagesCount")) {
                    if (data.optString("messagesCount").equals("0")) {
                    } else {
                        messageIntent.putExtra("setMessage", "no");
                    }
                }
                startActivity(messageIntent);
                break;
            case R.id.iv_mine_person_header:
//                Intent personalIntent = new Intent(mContext, PersonalInfoActivity.class);
//                startActivity(personalIntent);
                break;
            case R.id.ll_mine_wallet:
                Intent walletIntent = new Intent(mContext, WalletActivity.class);
                startActivity(walletIntent);
                break;
            case R.id.ll_mine_order:
                Intent orderIntent = new Intent(mContext, OrderListActivity.class);
                startActivity(orderIntent);
                break;
            case R.id.ll_mine_favorite:
                Intent favoriteIntent = new Intent(mContext, FavoriteActivity.class);
                startActivity(favoriteIntent);
                break;
            case R.id.ll_mine_spread:
                Intent spreadIntent = new Intent(mContext, ExtensionCenterActivity.class);
                startActivity(spreadIntent);
                break;
            case R.id.ptr_mine_card:
                Intent businessCardIntent = new Intent(mContext, MyBusinessCardActivity.class);
                startActivity(businessCardIntent);
                break;
            case R.id.ptr_mine_profile:
                Intent personalIntent = new Intent(mContext, PersonalInfoActivity.class);
                startActivity(personalIntent);
                break;
            case R.id.ptr_mine_location:
                Intent addressIntent = new Intent(mContext, AddressListActivity.class);
                startActivity(addressIntent);
                break;
            case R.id.ptr_mine_market_center:
                if (this.data != null) {
                    if (this.data.optString("isMarketing").equals("0")) {
                        //申请入驻营销
                        Intent marketingIntent = new Intent(mContext, MarketingApplyActivity.class);
                        startActivity(marketingIntent);
                    } else {
                        //直接进入营销中心
                        Intent marketingIntent = new Intent(mContext, MarketingCenterActivity.class);
                        startActivity(marketingIntent);
                    }
                } else {
                    //TODO  这里应该是要到登陆页面
                }


                break;
            case R.id.ptr_mine_online_service:
                mPresenter.getCustomService();
                break;
            case R.id.ptr_mine_contact_us:

                Intent mineContactIntent = new Intent(mContext, MineContactActivity.class);
                startActivity(mineContactIntent);

                break;
            case R.id.ptr_mine_shop_stay:
                switch (mApplyStatus) {
                    case -9:
                        Intent shopStayIntent = new Intent(mContext, ShopStayActivity.class);
                        startActivity(shopStayIntent);
                        break;
                    case 0:
                        ToastUtils.showShort(mContext, "正在审核，请稍等!");
                        break;
                    case 1:
                        ToastUtils.showShort(mContext, "请登录PC端管理店铺!");
                        break;
                    case -1:
                        ToastUtils.showShort(mContext, "审核失败，原因：" + mFailReason);
                        break;
                }
                break;
            case R.id.tv_mine_person_login:
                Intent loginIntent = new Intent(mContext, LoginActivity.class);
                startActivityForResult(loginIntent, Constant.REQ_LOGIN);
                break;

            //购买资格
            case R.id.ptr_mine_purchase_qualificatio:
                Intent purchaseQualificatioIntent = new Intent(mContext, PurchaseQualificatioActivity.class);
                purchaseQualificatioIntent.putExtra("userInfoBean", userInfoBean);
                startActivityForResult(purchaseQualificatioIntent, Constant.REQ_PURCHASEQUALIFICATIO);
                break;

            //商家充值卡
            case R.id.ptr_mine_merchant_charging_card:
                Intent toUpIntent = new Intent(mContext, MerchantsTopUpActivity.class);
                toUpIntent.putExtra("userInfoBean", userInfoBean);
                startActivityForResult(toUpIntent, Constant.REQ_PURCHASEQUALIFICATIO);
                break;

            //商家会员
            case R.id.ptr_mine_merchant_members:
                Intent memberIntent = new Intent(mContext, MerchantsMemberActivity.class);
                memberIntent.putExtra("userInfoBean", (Serializable) userInfoBean);
                startActivityForResult(memberIntent, Constant.REQ_PURCHASEQUALIFICATIO);
                break;

            //商家报表
            case R.id.ptr_mine_business_report:
                Intent reportIntent = new Intent(mContext, MerchantsReportActivity.class);
                reportIntent.putExtra("userInfoBean", userInfoBean);
                startActivityForResult(reportIntent, Constant.REQ_PURCHASEQUALIFICATIO);

                break;

            //商家订单
            case R.id.ptr_mine_business_order:
                Intent MerchantsorderIntent = new Intent(mContext, MerchantsOrderActivity.class);
                MerchantsorderIntent.putExtra("userInfoBean", userInfoBean);
                startActivityForResult(MerchantsorderIntent, Constant.REQ_PURCHASEQUALIFICATIO);
                break;
        }
    }

    /**
     * 获取系统配置
     */
    private void getConfig() {
        HttpParams params = new HttpParams();
        params.put("token", (String) SPUtils.get(getActivity(), Constant.KEY_TOKEN, ""));
        OkGo.<String>get(HostUrl.URL_CONFIG)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject obj = new JSONObject(body);
                            int statu = obj.optJSONObject("data").optInt("fieldValue");
                            //启用新模块
                            if (statu == 1) {
                                ptrMinePurchaseQualificatio.setVisibility(View.VISIBLE);
                                ptrMineMerchantChargingCard.setVisibility(View.VISIBLE);
                                ptrMineMerchantMembers.setVisibility(View.VISIBLE);
                                ptrMineBusinessReport.setVisibility(View.VISIBLE);
                                ptrMineBusinessOrder.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
