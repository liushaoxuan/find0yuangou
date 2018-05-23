package com.ahxd.lingyuangou.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.utils.DeviceUtils;

import java.util.Locale;

/**
 * Created by Administrator on 2018/1/4.
 */

public class AdsPopupWindow extends PopupWindow {

    private View mView;
    private Activity mContext;
    private TextView mTvNum;

    public AdsPopupWindow(Activity context) {
        super(context);
        this.mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.layout_ads_popupwindow, null);
        setContentView(mView);

        this.setWidth(DeviceUtils.getDeviceWidth(context) * 7 / 10);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setOutsideTouchable(false);
        setFocusable(true);

        // 去处黑色背景
        setBackgroundDrawable(new ColorDrawable());

        mView.findViewById(R.id.btn_ads_pop_window_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });

        mTvNum = (TextView) mView.findViewById(R.id.tv_ads_pop_window_num);

        setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setPopBackground(1.0f);
            }
        });
    }

    public void show(String incomeRate) {
        setPopBackground(0.5f);
        if (!TextUtils.isEmpty(incomeRate)) {
            mTvNum.setText(String.format(Locale.CHINA, "%.2f", Float.parseFloat(incomeRate) * 100));
        }
        showAtLocation(mView, Gravity.CENTER, 0, 0);
    }

    private void hide() {
        setPopBackground(1.0f);
        this.dismiss();
    }

    private void setPopBackground(float num) {
        // 背景变灰色
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = num;
        mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mContext.getWindow().setAttributes(lp);
    }

}
