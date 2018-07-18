package com.ahxd.lingyuangou.ui.main.activity;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseFragment;
import com.ahxd.lingyuangou.constant.Constant;
import com.ahxd.lingyuangou.listener.onRushToBuyListener;
import com.ahxd.lingyuangou.permission.PermissionResultAdapter;
import com.ahxd.lingyuangou.permission.PermissionUtil;
import com.ahxd.lingyuangou.ui.home.contract.IMainContract;
import com.ahxd.lingyuangou.ui.home.presenter.MainPresenter;
import com.ahxd.lingyuangou.ui.main.fragment.CartFragment;
import com.ahxd.lingyuangou.ui.main.fragment.HomeFragment;
import com.ahxd.lingyuangou.ui.main.fragment.MineFragment;
import com.ahxd.lingyuangou.ui.main.fragment.NearFragment;
import com.ahxd.lingyuangou.ui.mine.activity.LoginActivity;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.LocationUtils;
import com.ahxd.lingyuangou.utils.SHA1Utils;
import com.ahxd.lingyuangou.utils.UserUtils;
import com.ahxd.lingyuangou.utils.VersionUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;

public class MainActivity extends AppCompatActivity implements IMainContract.IMainView, onRushToBuyListener {
    private static final int MSG_SET_ALIAS = 1001;
    @BindView(R.id.fm_container)
    FrameLayout fmContainer;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_near)
    RadioButton rbNear;
    @BindView(R.id.rb_cart)
    RadioButton rbCart;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    @BindView(R.id.rg_container)
    RadioGroup rgContainer;

    private Fragment mCurrentFragment;
    private List<BaseFragment> mFragmentList;
    private NearFragment nearFragment;
    private int mOldPosition; // 点击购物车前，选中的position；
    MainPresenter mPresenter;
    ProgressDialog aboutDialog;
    private AlphaAnimation mHideAnimation = null;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Set<String> set = new HashSet<String>();
                    set.add("android");
                    break;

                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String sha1 = SHA1Utils.sHA1(this);
        Log.e("*******************", sha1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initLocation();
        initFragment();
        rbHome.setChecked(true);
        mPresenter = new MainPresenter(this);
        aboutDialog = new ProgressDialog(this);
        aboutDialog.setCancelable(false);
        aboutDialog.setCanceledOnTouchOutside(false);
        switchFragment(null, mFragmentList.get(0));

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getApk();
        if (null != getIntent().getStringExtra("type")) {
            if (getIntent().getStringExtra("type").equals("cart")) {
                rbCart.setChecked(true);
                CartFragment.isGift = 0;
                switchFragment(mCurrentFragment, mFragmentList.get(2));
            } else if (getIntent().getStringExtra("type").equals("giftcart")) {

                rbCart.setChecked(true);
                CartFragment.isGift = 1;
                switchFragment(mCurrentFragment, mFragmentList.get(2));
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.e("MainActivity", "onDestroy");
        LocationUtils.getInstance().stopLocation();
        LocationUtils.getInstance().destroyLocation();
    }

    private void initFragment() {
        CartFragment cartFragment = new CartFragment();
        nearFragment = new NearFragment();
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(nearFragment);
        mFragmentList.add(cartFragment);
        mFragmentList.add(new MineFragment());
        cartFragment.setOnRushToBuyListener(this);
    }

    private void initLocation() {
        PermissionUtil.getInstance().request(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE},
                new PermissionResultAdapter() {
                    @Override
                    public void onPermissionGranted() {
                        LocationUtils.getInstance().startLocation();
                    }

                    @Override
                    public void onPermissionDenied(String... permissions) {
                        new AppSettingsDialog.Builder(MainActivity.this)
                                .setRationale("没有该权限，此应用程序可能无法正常工作。打开应用设置界面以修改应用权限")
                                .setTitle("必需权限")
                                .build()
                                .show();
                    }
                });
    }

    @OnClick({R.id.rb_home, R.id.rb_near, R.id.rb_cart, R.id.rb_mine})
    public void onRadioButtonClicked(CompoundButton view) {
        int position = 0;
        switch (view.getId()) {
            case R.id.rb_home:
                position = 0;
                break;
            case R.id.rb_near:
                position = 1;
                break;
            case R.id.rb_cart:
                position = 2;
                break;
            case R.id.rb_mine:
                position = 3;
                break;
        }
        showFragment(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQ_LOGIN && resultCode == RESULT_OK) {
            mOldPosition = 2;
            rbCart.setChecked(true);
            switchFragment(mCurrentFragment, getFragment(mOldPosition));
        }
    }

    private void showFragment(int position) {
        // 点击购物车，如果没有登录，则跳转到登录界面
        if (position == 2) {
            if (!UserUtils.isLogin()) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent, Constant.REQ_LOGIN);
                switch (mOldPosition) {
                    case 0:
                        rbHome.setChecked(true);
                        break;
                    case 1:
                        rbNear.setChecked(true);
                        break;
                    case 3:
                        rbMine.setChecked(true);
                        break;
                }
            } else {
                mOldPosition = position;
                switchFragment(mCurrentFragment, getFragment(position));
            }
        } else {
            mOldPosition = position;
            switchFragment(mCurrentFragment, getFragment(position));
        }
    }

    private void switchFragment(Fragment from, Fragment to) {
        if (mCurrentFragment != to) {
            mCurrentFragment = to;
            if (to != null) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (!to.isAdded()) {
                    if (from != null) {
                        transaction.hide(from);
                    }
                    transaction.add(R.id.fm_container, to).commit();
                } else {
                    if (from != null) {
                        transaction.hide(from);
                    }
                    transaction.show(to).commit();
                }
            }
        }
    }

    private BaseFragment getFragment(int position) {
        if (mFragmentList != null && mFragmentList.size() > 0) {
            return mFragmentList.get(position);
        }
        return null;
    }

    @Override
    public void showApk(JSONObject data) {
        if (null != data) {
//            String a=VersionUtils.getVersionName(this)+"."+VersionUtils.getVersionCode(this);
            if (!(VersionUtils.getVersionName(this) + "." + VersionUtils.getVersionCode(this)).equals(data.optString("version"))) {
                if (MaoApplication.ISDOWN) {
                    VersionUtils.aboutEasyReport(this, data.optString("download"), aboutDialog, handler);
                }
            }
        }

    }


    @Override
    public void showStartRequest() {

    }

    @Override
    public void showEndRequest() {

    }

    @Override
    public void showErrorRequest(String msg) {

    }

    @Override
    public void showReLogin() {

    }

    @Override
    public void showRequestFailure(String msg) {

    }

    /**
     * 购物车模块 抢购回调
     */
    @Override
    public void onRushToBuy() {
        rbHome.setChecked(true);
        showFragment(0);
    }

    /**
     * 更换地址后重新加载附近数据
     */
    public void reloadNear() {
        if (nearFragment.srlNear != null) {
            nearFragment.srlNear.autoRefresh();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int position = intent.getIntExtra("position", 0);
        showFragment(position);
    }
}
