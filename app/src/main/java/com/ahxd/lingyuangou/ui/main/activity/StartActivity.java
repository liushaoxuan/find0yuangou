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
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ahxd.lingyuangou.MaoApplication;
import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseFragment;
import com.ahxd.lingyuangou.constant.Constant;
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

/**
 * Created by wpc on 2018/2/9.
 */

public class StartActivity extends AppCompatActivity {
    private static final int START_UP = 1002;
    @BindView(R.id.v_start_up)
    View vStarUp;

    private AlphaAnimation mHideAnimation = null;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case START_UP:
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_out, R.anim.activity_in);// 淡出淡入动画效果
                    finish();
                    break;

                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.what = START_UP;
                handler.sendMessage(message);
            }
        });
        thread.start();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.e("MainActivity", "onDestroy");
        LocationUtils.getInstance().stopLocation();
        LocationUtils.getInstance().destroyLocation();
    }

}
