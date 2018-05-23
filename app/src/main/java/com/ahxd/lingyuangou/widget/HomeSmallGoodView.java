package com.ahxd.lingyuangou.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeGiftBean;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/27.
 */

public class HomeSmallGoodView extends RelativeLayout {

    @BindView(R.id.tv_home_small_good_name)
    TextView tvHomeSmallGoodName;
    @BindView(R.id.tv_home_small_good_price)
    TextView tvHomeSmallGoodPrice;
    @BindView(R.id.iv_home_small_good_image)
    ImageView ivHomeSmallGoodImage;

    private Context mContext;

    public HomeSmallGoodView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public HomeSmallGoodView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public void setData(HomeGiftBean gift) {
        tvHomeSmallGoodName.setText(gift.getGoodsName());
        tvHomeSmallGoodPrice.setText(String.format("货币¥%s", gift.getShopPrice()));
        Glide.with(mContext).load(gift.getGoodsImg()).into(ivHomeSmallGoodImage);
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_home_small_good, this, true);
        ButterKnife.bind(this);
    }

}
