package com.ahxd.lingyuangou.ui.main.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseFragment;
import com.ahxd.lingyuangou.bean.CartBean;
import com.ahxd.lingyuangou.listener.OnCartOperateListener;
import com.ahxd.lingyuangou.ui.cart.activity.OnlinePayActivity;
import com.ahxd.lingyuangou.ui.cart.adapter.CartListViewAdapter;
import com.ahxd.lingyuangou.ui.cart.contract.ICartContract;
import com.ahxd.lingyuangou.ui.cart.presenter.CartPresenter;
import com.ahxd.lingyuangou.ui.cart.utils.CartUtils;
import com.ahxd.lingyuangou.utils.L;
import com.ahxd.lingyuangou.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/25.
 */

public class CartFragment extends BaseFragment implements ICartContract.ICartView {

    @BindView(R.id.tv_cart_fragment_title)
    TextView tvCartFragmentTitle;
    @BindView(R.id.rb_cart_tab_cart)
    RadioButton rbCartTabCart;
    @BindView(R.id.rb_cart_tab_exchange)
    RadioButton rbCartTabExchange;
    @BindView(R.id.rg_cart_tab)
    RadioGroup rgCartTab;
    @BindView(R.id.elv_cart_goods_list)
    ExpandableListView elvCartGoodsList;
    @BindView(R.id.tv_cart_goods_total_num)
    TextView tvCartGoodsTotalNum;
    @BindView(R.id.tv_cart_goods_total_price)
    TextView tvCartGoodsTotalPrice;
    @BindView(R.id.tv_cart_goods_check)
    TextView tvCartGoodsCheck;
    @BindView(R.id.tv_cart_no_goods_tips)
    TextView tvCartNoGoodsTips;

    private CartPresenter mPresenter;
    private CartListViewAdapter mAdapter;
    public static int isGift=0;//0代表购物车，1代表兑换购物车

    @Override
    protected View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_cart, null);
        mUnbinder = ButterKnife.bind(this, view);

        elvCartGoodsList.setGroupIndicator(null);

        mAdapter = new CartListViewAdapter(mContext);
        elvCartGoodsList.setAdapter(mAdapter);

        rbCartTabCart.setChecked(true);

        initListener();

        return view;
    }

    @Override
    protected void initData() {
        L.e("CartFragment -> initData");
        mPresenter = new CartPresenter(this);
//        mPresenter.getCartGoods();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isGift==0){
            mPresenter.getCartGoods();
            rbCartTabCart.setChecked(true);
        }else {
            mPresenter.getGiftCartGoods();
            rbCartTabExchange.setChecked(true);
        }

    }

    private void initListener() {
        elvCartGoodsList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });

        mAdapter.setCartOperateListener(new OnCartOperateListener() {
            @Override
            public void onDataChange(String selectCount, String selectMoney) {
                tvCartGoodsTotalPrice.setText(String.format(Locale.CHINA, "总计：%s 元", selectMoney));
                tvCartGoodsTotalNum.setText(String.format("产品总数：%s 件", selectCount));
            }

            @Override
            public void onSelectItem(boolean isSelectedAll) {

            }

            @Override
            public void onDeleteGood(String cartId) {
                if (isGift==0){
                    mPresenter.onDeleteGood(cartId);
                }else {
                    mPresenter.onGiftDeleteGood(cartId);
                }

            }

            @Override
            public void onEditGoods(String content) {
                if (isGift==0){
                    mPresenter.onEditGoods(content);
                }else {
                    mPresenter.onGiftEditGoods(content);
                }

            }

            @Override
            public void onDetailGoods(JSONArray params) {

            }

            @Override
            public void onCheckGoods(String content) {
                if (isGift==0){
                    mPresenter.onSubmitOrder(content);
                }else {
                    mPresenter.onGiftSubmitOrder(content);
                }

            }

            @Override
            public void onChildClicked(CartBean.OrderBean.GoodsBean goodsBean) {

            }
        });

        tvCartGoodsCheck.setOnClickListener(mAdapter.getListener());
    }

    @OnClick({R.id.rb_cart_tab_cart, R.id.rb_cart_tab_exchange, R.id.tv_cart_goods_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_cart_tab_cart:
                isGift=0;
                mPresenter.getCartGoods();
                break;
            case R.id.rb_cart_tab_exchange:
                isGift=1;
                mPresenter.getGiftCartGoods();
                break;
            case R.id.tv_cart_goods_check:
                break;
        }
    }

    @Override
    public void showCartGoods(CartBean cartBean) {
        if (null != cartBean) {
            tvCartGoodsTotalPrice.setText(String.format(Locale.CHINA, "总计：%.2f 元", cartBean.getMoney_sum()));
            tvCartGoodsTotalNum.setText(String.format("产品总数：%s 件", cartBean.getOrder_sum()));
            if (Integer.valueOf(cartBean.getOrder_sum()) == 0) {
                tvCartNoGoodsTips.setVisibility(View.VISIBLE);
                elvCartGoodsList.setVisibility(View.GONE);
            } else {
                tvCartNoGoodsTips.setVisibility(View.GONE);
                elvCartGoodsList.setVisibility(View.VISIBLE);
                mAdapter.setData(cartBean,isGift);
                expandAllGroup(cartBean);
            }
        }
    }

    @Override
    public void showDeleteGood(String msg) {
        mAdapter.removeDelGood();
    }

    @Override
    public void showEditGoods(String msg) {
        ToastUtils.showShort(mContext, msg);
        String[] info = CartUtils.getCartCount(mAdapter.getData());
        tvCartGoodsTotalPrice.setText(String.format(Locale.CHINA, "总计：%s 元", info[1]));
        tvCartGoodsTotalNum.setText(String.format("产品总数：%s 件", info[0]));
    }

    @Override
    public void showSubmitOrder(JSONObject data) {
        Intent intent = new Intent(mContext, OnlinePayActivity.class);
        intent.putExtra("orderInfo", data.toString());
        intent.putExtra("start_from", "cart");

        if (isGift==1){
            intent.putExtra("type", "gift");
        }
        startActivity(intent);
    }

    @Override
    public void showGiftCartGoods(CartBean cartBean) {
        if (null != cartBean) {
            tvCartGoodsTotalPrice.setText(String.format(Locale.CHINA, "总计：%.2f 元", cartBean.getMoney_sum()));
            tvCartGoodsTotalNum.setText(String.format("产品总数：%s 件", cartBean.getOrder_sum()));
            if (Integer.valueOf(cartBean.getOrder_sum()) == 0) {
                tvCartNoGoodsTips.setVisibility(View.VISIBLE);
                elvCartGoodsList.setVisibility(View.GONE);
            } else {
                tvCartNoGoodsTips.setVisibility(View.GONE);
                elvCartGoodsList.setVisibility(View.VISIBLE);
                mAdapter.setData(cartBean,isGift);
                expandAllGroup(cartBean);
            }
        }
    }

    @Override
    public void showGiftDeleteGood(String msg) {
            mAdapter.removeDelGood();
    }

    @Override
    public void showGiftEditGoods(String msg) {
        ToastUtils.showShort(mContext, msg);
        String[] info = CartUtils.getCartCount(mAdapter.getData());
        tvCartGoodsTotalPrice.setText(String.format(Locale.CHINA, "总计：%s 元", info[1]));
        tvCartGoodsTotalNum.setText(String.format("产品总数：%s 件", info[0]));
    }

    @Override
    public void showGiftSubmitOrder(JSONObject data) {
        Intent intent = new Intent(mContext, OnlinePayActivity.class);
        intent.putExtra("orderInfo", data.toString());
        intent.putExtra("start_from", "cart");
        if (isGift==1){
            intent.putExtra("type", "gift");
        }
        startActivity(intent);
    }

    /**
     * 展开所有组
     */
    private void expandAllGroup(CartBean cartBean) {
        if (cartBean == null) {
            return;
        }
        List<CartBean.OrderBean> result = cartBean.getOrder();
        for (int i = 0; i < result.size(); i++) {
            elvCartGoodsList.expandGroup(i);
        }
    }
}
