package com.ahxd.lingyuangou.ui.home.holder;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.HomeGiftBean;
import com.ahxd.lingyuangou.ui.home.activity.ExchangeGiftActivity;
import com.ahxd.lingyuangou.ui.home.activity.GiftGoodDetailActivity;
import com.ahxd.lingyuangou.ui.mine.activity.LoginActivity;
import com.ahxd.lingyuangou.utils.UserUtils;
import com.ahxd.lingyuangou.widget.HomeBigGoodView;
import com.ahxd.lingyuangou.widget.HomeSmallGoodView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sxliu on 2018/6/28 1:02
 * E-mail Address 2587294424@qq.com
 */

public class ExchangeMallViewHolder extends HomeBaseViewHolder<HomeGiftBean> {
    @BindView(R.id.good_first)
    HomeBigGoodView goodFirst;
    @BindView(R.id.good_second)
    HomeSmallGoodView goodSecond;
    @BindView(R.id.good_third)
    HomeSmallGoodView goodThird;
    @BindView(R.id.good_forth)
    HomeSmallGoodView goodForth;
    @BindView(R.id.ll_home_gift_bottom)
    LinearLayout llHomeGiftBottom;
    //积分商城
    @BindView(R.id.tv_jifenshangcheng)
    TextView tvshangcheng;

    private List<HomeGiftBean> mGiftData;

    public ExchangeMallViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final List<HomeGiftBean> mGiftData, int position) {
        if (mGiftData.isEmpty()) {
            return;
        }
        if (mGiftData.size() > 3) {
            llHomeGiftBottom.setVisibility(View.VISIBLE);
            goodFirst.setData(mGiftData.get(0));
            goodSecond.setData(mGiftData.get(1));
            goodThird.setData(mGiftData.get(2));
            goodForth.setData(mGiftData.get(3));
            goodFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, GiftGoodDetailActivity.class);
                    intent.putExtra("goodsId", mGiftData.get(0).getGoodsId());
                    intent.putExtra("goodsName", mGiftData.get(0).getGoodsName());
                    mContext.startActivity(intent);
                }
            });
            goodSecond.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, GiftGoodDetailActivity.class);
                    intent.putExtra("goodsId", mGiftData.get(1).getGoodsId());
                    intent.putExtra("goodsName", mGiftData.get(1).getGoodsName());
                    mContext.startActivity(intent);
                }
            });
            goodThird.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, GiftGoodDetailActivity.class);
                    intent.putExtra("goodsId", mGiftData.get(2).getGoodsId());
                    intent.putExtra("goodsName", mGiftData.get(2).getGoodsName());
                    mContext.startActivity(intent);
                }
            });

            goodForth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, GiftGoodDetailActivity.class);
                    intent.putExtra("goodsId", mGiftData.get(3).getGoodsId());
                    intent.putExtra("goodsName", mGiftData.get(3).getGoodsName());
                    mContext.startActivity(intent);
                }
            });

            tvshangcheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!UserUtils.isLogin()) {
                        Intent intent2 = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(intent2);
                    } else {
                        Intent exchangeGiftIntent = new Intent(mContext, ExchangeGiftActivity.class);
                        mContext.startActivity(exchangeGiftIntent);
                    }
                }
            });
        }

    }
}
