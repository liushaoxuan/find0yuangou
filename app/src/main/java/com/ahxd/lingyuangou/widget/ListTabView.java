package com.ahxd.lingyuangou.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.CatBean;
import com.ahxd.lingyuangou.utils.L;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/2.
 */

public class ListTabView extends RelativeLayout {

    @BindView(R.id.tv_tab_name)
    TextView tvTabName;
    @BindView(R.id.iv_tab_icon)
    ImageView ivTabIcon;
    private Context mContext;

    public ListTabView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public ListTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ListTabView);
        tvTabName.setText(a.getString(R.styleable.ListTabView_text));
        ivTabIcon.setImageResource(a.getResourceId(R.styleable.ListTabView_img, R.mipmap.ic_launcher));
        a.recycle();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_list_tab, this);
        ButterKnife.bind(this);
    }


}
