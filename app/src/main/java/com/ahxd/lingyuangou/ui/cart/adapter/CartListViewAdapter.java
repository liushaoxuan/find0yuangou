package com.ahxd.lingyuangou.ui.cart.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.CartBean;
import com.ahxd.lingyuangou.listener.OnCartOperateListener;
import com.ahxd.lingyuangou.ui.cart.utils.CartUtils;
import com.ahxd.lingyuangou.utils.ToastUtils;
import com.ahxd.lingyuangou.widget.UIAlertView;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mao Zhendong on 2018/1/8.
 */

public class CartListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private CartBean mCartBean;

    // 是否全部商品选中
    private boolean isSelectedAll = false;
    private OnCartOperateListener mOperateListener;
    private int mCurGroupPos;
    private int mCurChildPos;

    int isGift=0;//0代表购物车，1代表兑换购物车

    public CartListViewAdapter(Context context) {
        this.mContext = context;
    }

    public void setCartOperateListener(OnCartOperateListener listener) {
        this.mOperateListener = listener;
    }

    /**
     * 获取监听
     */
    public View.OnClickListener getListener() {
        return mListener;
    }

    public void setData(CartBean cartBean,int isGift) {
        this.mCartBean = cartBean;
        this.isGift = isGift;
        notifyDataSetChanged();
    }

    public CartBean getData() {
        return mCartBean;
    }

    @Override
    public int getGroupCount() {
        if (mCartBean == null || mCartBean.getOrder().isEmpty()) {
            return 0;
        } else {
            return mCartBean.getOrder().size();
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mCartBean.getOrder().get(groupPosition).getGoods().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mCartBean.getOrder().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mCartBean.getOrder().get(groupPosition).getGoods().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cart_elv_group, parent, false);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        CartBean.OrderBean orderBean = mCartBean.getOrder().get(groupPosition);
        holder.tvCartGroupShopName.setText(orderBean.getShopName());
        holder.tvCartGroupEdit.setText(orderBean.isEditing() ? "完成" : "编辑");
        holder.tvCartGroupEdit.setTag(groupPosition);

        orderBean.setGroupSelected(CartUtils.isGroupSelected(orderBean));
        CartUtils.checkItem(orderBean.isGroupSelected(), holder.rbCartGroupCheck);

        holder.rbCartGroupCheck.setTag(groupPosition);

        holder.tvCartGroupEdit.setOnClickListener(mListener);
        holder.rbCartGroupCheck.setOnClickListener(mListener);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cart_elv_child, parent, false);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }

        CartBean.OrderBean.GoodsBean goodsBean = mCartBean.getOrder().get(groupPosition).getGoods().get(childPosition);

        goodsBean.setChildSelected(goodsBean.getIsCheck() == 1);

        // 设置商品是否选中状态
        boolean isChildSelected = goodsBean.isChildSelected();
        CartUtils.checkItem(isChildSelected, holder.rbCartChildCheck);
        // 设置商品是否编辑状态
        boolean isEditing = goodsBean.isEditing();
        if (isEditing) {
            holder.llCartGoodInfo.setVisibility(View.GONE);
            holder.llCartEditGoodInfo.setVisibility(View.VISIBLE);
        } else {
            holder.llCartGoodInfo.setVisibility(View.VISIBLE);
            holder.llCartEditGoodInfo.setVisibility(View.GONE);
        }

        holder.tvCartChildGoodName.setText(goodsBean.getGoodsName());
        holder.tvCartChildEditGoodName.setText(goodsBean.getGoodsName());
        holder.tvCartChildGoodNowPrice.setText(String.format("￥:%s", goodsBean.getShopPrice()));
        holder.tvCartChildGoodOldPrice.setText(String.format("￥:%s", goodsBean.getMarketPrice()));
        holder.tvCartChildGoodOldPrice.getPaint().setAntiAlias(true);
        holder.tvCartChildGoodOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        holder.tvCartChildGoodParams.setText(String.format("规格:%s", goodsBean.getGoodsSpecId()));
        holder.tvCartChildGoodNum.setText(String.format("数量:%d", goodsBean.getCartNum()));
        holder.ivCartChildEditGoodNum.setText(goodsBean.getCartNum() + "");
        Glide.with(mContext).load(goodsBean.getGoodsImg()).into(holder.ivCartChildGoodImage);

        holder.rbCartChildCheck.setTag(groupPosition + "," + childPosition);
        holder.ivCartChildEditGoodAdd.setTag(groupPosition + "," + childPosition);
        holder.ivCartChildEditGoodReduce.setTag(groupPosition + "," + childPosition);
        holder.tvCartChildEditGoodDel.setTag(groupPosition + "," + childPosition);

        holder.ivCartChildEditGoodAdd.setOnClickListener(mListener);
        holder.ivCartChildEditGoodReduce.setOnClickListener(mListener);
        holder.tvCartChildEditGoodDel.setOnClickListener(mListener);
        holder.rbCartChildCheck.setOnClickListener(mListener);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    static class GroupViewHolder {
        @BindView(R.id.rb_cart_group_check)
        ImageView rbCartGroupCheck;
        @BindView(R.id.tv_cart_group_shop_name)
        TextView tvCartGroupShopName;
        @BindView(R.id.tv_cart_group_edit)
        TextView tvCartGroupEdit;

        public GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.rb_cart_child_check)
        ImageView rbCartChildCheck;
        @BindView(R.id.iv_cart_child_good_image)
        ImageView ivCartChildGoodImage;
        @BindView(R.id.tv_cart_child_good_now_price)
        TextView tvCartChildGoodNowPrice;
        @BindView(R.id.tv_cart_child_good_old_price)
        TextView tvCartChildGoodOldPrice;
        @BindView(R.id.tv_cart_child_good_num)
        TextView tvCartChildGoodNum;
        @BindView(R.id.tv_cart_child_good_name)
        TextView tvCartChildGoodName;
        @BindView(R.id.tv_cart_child_good_params)
        TextView tvCartChildGoodParams;
        @BindView(R.id.ll_cart_good_info)
        RelativeLayout llCartGoodInfo;
        @BindView(R.id.tv_cart_child_edit_good_del)
        TextView tvCartChildEditGoodDel;
        @BindView(R.id.tv_cart_child_edit_good_name)
        TextView tvCartChildEditGoodName;
        @BindView(R.id.iv_cart_child_edit_good_add)
        ImageView ivCartChildEditGoodAdd;
        @BindView(R.id.iv_cart_child_edit_good_num)
        TextView ivCartChildEditGoodNum;
        @BindView(R.id.iv_cart_child_edit_good_reduce)
        ImageView ivCartChildEditGoodReduce;
        @BindView(R.id.ll_cart_edit_good_info)
        RelativeLayout llCartEditGoodInfo;

        public ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_cart_group_edit: // 编辑
                    int groupPosition = (int) v.getTag();
                    boolean isEditing = !mCartBean.getOrder().get(groupPosition).isEditing();
                    mCartBean.getOrder().get(groupPosition).setEditing(isEditing);
                    StringBuffer content = new StringBuffer();
                    for (int i = 0; i < mCartBean.getOrder().get(groupPosition).getGoods().size(); i++) {
                        mCartBean.getOrder().get(groupPosition).getGoods().get(i).setEditing(isEditing);
                        if (!isEditing) {
                            content.append(mCartBean.getOrder().get(groupPosition).getGoods().get(i).getCartId())
                                    .append(":")
                                    .append(mCartBean.getOrder().get(groupPosition).getGoods().get(i).getCartNum())
                                    .append(",");
                        }
                    }
                    if (!isEditing) {
                        editGoods(content.toString());
                    }
                    notifyDataSetChanged();
                    break;

                case R.id.rb_cart_group_check: // 组全选
                    int groupPosition1 = (int) v.getTag();
                    isSelectedAll = CartUtils.selectGroup(mCartBean, groupPosition1);
                    onSelectAll();
                    setTotalInfo();
                    notifyDataSetChanged();
                    break;
                case R.id.iv_cart_child_edit_good_add: // 增加商品数量
                    String addString = String.valueOf(v.getTag());
                    if (addString.contains(",")) {
                        String s[] = addString.split(",");
                        int group = Integer.parseInt(s[0]);
                        int child = Integer.parseInt(s[1]);
                        CartBean.OrderBean.GoodsBean goodsBean = mCartBean.getOrder().get(group).getGoods().get(child);
                        goodsBean.setCartNum(goodsBean.getCartNum() + 1);
                    }
                    notifyDataSetChanged();
                    break;
                case R.id.iv_cart_child_edit_good_reduce: // 减少商品数量
                    String reduceString = String.valueOf(v.getTag());
                    if (reduceString.contains(",")) {
                        String s[] = reduceString.split(",");
                        int group = Integer.parseInt(s[0]);
                        int child = Integer.parseInt(s[1]);
                        CartBean.OrderBean.GoodsBean goodsBean = mCartBean.getOrder().get(group).getGoods().get(child);
                        if (goodsBean.getCartNum() - 1 <= 0) {
                            showDelDialog(group, child);
                        } else {
                            goodsBean.setCartNum(goodsBean.getCartNum() - 1);
                        }
                    }
                    notifyDataSetChanged();
                    break;
                case R.id.tv_cart_child_edit_good_del:
                    String delString = String.valueOf(v.getTag());
                    if (delString.contains(",")) {
                        String s[] = delString.split(",");
                        int group = Integer.parseInt(s[0]);
                        int child = Integer.parseInt(s[1]);
                        showDelDialog(group, child);
                    }
                    notifyDataSetChanged();
                    break;
                case R.id.rb_cart_child_check:
                    String goodString = String.valueOf(v.getTag());
                    if (goodString.contains(",")) {
                        String s[] = goodString.split(",");
                        int group = Integer.parseInt(s[0]);
                        int child = Integer.parseInt(s[1]);
                        isSelectedAll = CartUtils.selectOne(mCartBean, group, child);
                        onSelectAll();
                        setTotalInfo();
                    }
                    notifyDataSetChanged();
                    break;
                case R.id.tv_cart_goods_check:
                    if (mCartBean != null && CartUtils.getTotalCount(mCartBean) > 0 && CartUtils.hasSelectedGoods(mCartBean)) {
                        submitOrder();
                    } else {
                        ToastUtils.showShort(mContext, "请先选择商品！");
                    }
                    break;
            }
        }
    };

    /**
     * 商品全部选中
     */
    private void onSelectAll() {
        if (null != mOperateListener) {
            mOperateListener.onSelectItem(isSelectedAll);
        }
    }

    /**
     * 删除商品
     *
     * @param cartId
     */
    private void onDeleteGood(String cartId) {
        if (null != mOperateListener) {
            mOperateListener.onDeleteGood(cartId);
        }
    }

    /**
     * 更新商品数量和总计价格
     */
    private void setTotalInfo() {
        String[] infos = CartUtils.getCartCount(mCartBean);
        //删除或者选择商品之后，需要通知结算按钮，更新自己的数据；
        if (mOperateListener != null && infos != null) {
            mOperateListener.onDataChange(infos[0], infos[1]);
        }
    }

    /**
     * 编辑购物车
     */
    private void editGoods(String content) {
        if (null != mOperateListener) {
            mOperateListener.onEditGoods(content);
        }
    }

    /**
     * 删除商品
     */
    public void removeDelGood() {
        mCartBean.getOrder().get(mCurGroupPos).getGoods().remove(mCurChildPos);
        if (mCartBean.getOrder().get(mCurGroupPos).getGoods().size() == 0) {
            mCartBean.getOrder().remove(mCurGroupPos);
        }
        setTotalInfo();
        notifyDataSetChanged();
    }

    /**
     * 提交订单（一键支付）
     */
    private void submitOrder() {
        String content = CartUtils.getSelectGood(mCartBean);
        if (null != mOperateListener) {
            mOperateListener.onCheckGoods(content);
        }
    }

    private void showDelDialog(final int groupPosition, final int childPosition) {
        final UIAlertView delDialog = new UIAlertView(mContext, mContext.getResources().getString(R.string.string_dialog_title),
                mContext.getResources().getString(R.string.string_dialog_cart_good_del),
                mContext.getResources().getString(R.string.string_dialog_cancel),
                mContext.getResources().getString(R.string.string_dialog_ok));
        delDialog.show();
        delDialog.setClickListener(new UIAlertView.ClickListenerInterface() {

                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           delDialog.dismiss();
                                           String cartId = mCartBean.getOrder().get(groupPosition)
                                                   .getGoods().get(childPosition).getCartId();
                                           mCurGroupPos = groupPosition;
                                           mCurChildPos = childPosition;
                                           onDeleteGood(cartId);
                                       }
                                   }
        );
    }

}
