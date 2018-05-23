package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.OrderBean;
import com.ahxd.lingyuangou.listener.OnOrderOperateListener;
import com.ahxd.lingyuangou.ui.mine.activity.EvaluateActivity;
import com.ahxd.lingyuangou.ui.mine.activity.OrderDetailActivity;
import com.ahxd.lingyuangou.widget.OrderGoodView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mao on 2018/1/14.
 */

public class OrderListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<OrderBean> mData;
    private OnOrderOperateListener mListener;
    private int isGift;

    public OrderListAdapter(Context context, OnOrderOperateListener listener,int isGift) {
        this.mContext = context;
        this.mListener = listener;
        this.isGift = isGift;
    }

    public void setData(List<OrderBean> list,int isGift) {
        this.mData = list;
        this.isGift = isGift;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order, null);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((OrderViewHolder) holder).bindOrderViewHolder(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if (null == mData || mData.isEmpty()) {
            return 0;
        } else {
            return mData.size();
        }
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_order_state)
        TextView tvOrderState;
        @BindView(R.id.tv_order_shop_name)
        TextView tvOrderShopName;
        @BindView(R.id.tv_order_time)
        TextView tvOrderTime;
        @BindView(R.id.ll_order_goods_container)
        LinearLayout llOrderGoodsContainer;
        @BindView(R.id.tv_order_total_price)
        TextView tvOrderTotalPrice;
        @BindView(R.id.btn_order_button)
        TextView btnOrderButton;
        @BindView(R.id.btn_order_button1)
        TextView btnOrderButton1;

        public OrderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindOrderViewHolder(final OrderBean bean) {
            if (null != bean) {
                tvOrderState.setText(bean.getOrderStatusName());
                tvOrderShopName.setText(bean.getShopName());
                tvOrderTime.setText(bean.getCreateTime());
                tvOrderTotalPrice.setText(String.format("共计：%s元", bean.getRealTotalMoney()));
                // orderStatus -2 代付款 -1用户取消 0 代发货 1 代收货 2 已确认收货 3 退货  null 全部
                // isAppraise 0 待评价
                btnOrderButton.setVisibility(View.INVISIBLE);
                switch (bean.getOrderStatus()) {
                    case -2:
                        if (isGift==0){
                            btnOrderButton.setVisibility(View.VISIBLE);
                        }else {
                            btnOrderButton.setVisibility(View.INVISIBLE);
                        }


                        btnOrderButton.setText("立即付款");
                        btnOrderButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (null != mListener) {
                                    mListener.onPayOrder(bean);
                                }
                            }
                        });
                        btnOrderButton1.setVisibility(View.VISIBLE);
                        btnOrderButton1.setText("取消订单");
                        btnOrderButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (null != mListener) {
                                    mListener.onCancelOrder(bean);
                                }
                            }
                        });
                        break;
                    case 1:
                        btnOrderButton.setVisibility(View.VISIBLE);
                        btnOrderButton.setText("确认收货");
                        btnOrderButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (null != mListener) {
                                    mListener.onConfirmReceiver(bean);
                                }
                            }
                        });
                        btnOrderButton1.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        if (bean.getIsAppraise() == 0) {
                            if (isGift==0){
                                btnOrderButton.setVisibility(View.VISIBLE);
                            }else {
                                btnOrderButton.setVisibility(View.INVISIBLE);
                            }
                            btnOrderButton.setText("立即评价");
                            btnOrderButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (null != mListener) {
                                        mListener.onShopEvaluate(bean);
                                    }
                                }
                            });

                            btnOrderButton1.setVisibility(View.INVISIBLE);
                        } else {
                            btnOrderButton.setVisibility(View.INVISIBLE);
                            btnOrderButton1.setVisibility(View.INVISIBLE);
                        }
                        break;
                    case 0:
                        btnOrderButton.setVisibility(View.VISIBLE);
                        btnOrderButton.setText("取消订单");
                        btnOrderButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (null != mListener) {
                                    mListener.onCancelOrder(bean);
                                }
                            }
                        });
                        btnOrderButton1.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        btnOrderButton.setVisibility(View.INVISIBLE);
                        btnOrderButton1.setVisibility(View.INVISIBLE);
                        break;
                }
                OrderGoodView item;
                llOrderGoodsContainer.removeAllViews();
                if (bean.getGoods() != null && !bean.getGoods().isEmpty()) {
                    for (int i = 0; i < bean.getGoods().size(); i++) {
                        item = new OrderGoodView(mContext);
                        item.setData(bean.getGoods().get(i));
                        llOrderGoodsContainer.addView(item);
                    }
                }

                llOrderGoodsContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, OrderDetailActivity.class);
                        intent.putExtra("orderId", bean.getOrderId());
                        if (isGift==0){
                            intent.putExtra("type", "gouwu");
                        }else {
                            intent.putExtra("type", "gift");
                        }
                        mContext.startActivity(intent);
                    }
                });
            }
        }
    }
}
