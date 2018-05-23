package com.ahxd.lingyuangou.ui.cart.utils;

import android.widget.ImageView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.CartBean;
import com.ahxd.lingyuangou.utils.DecimalUtil;

import java.util.List;

/**
 * Created by Mao Zhendong on 2018/1/8.
 */

public class CartUtils {

    /**
     * 判断group是否要勾选
     */
    public static boolean isGroupSelected(CartBean.OrderBean orderBean) {
        for (int i = 0; i < orderBean.getGoods().size(); i++) {
            CartBean.OrderBean.GoodsBean cartGoods = orderBean.getGoods().get(i);
            if (cartGoods.getIsCheck() != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 勾与不勾选中选项
     *
     * @param isSelect 原先状态
     * @param ivCheck
     * @return 是否勾上，之后状态
     */
    public static boolean checkItem(boolean isSelect, ImageView ivCheck) {
        if (isSelect) {
            ivCheck.setImageResource(R.mipmap.ic_cart_rb_cart_check);
        } else {
            ivCheck.setImageResource(R.mipmap.ic_cart_rb_cart_normal);
        }
        return isSelect;
    }

    /**
     * 选中整个组
     *
     * @param groupPosition
     * @return
     */
    public static boolean selectGroup(CartBean cartBean, int groupPosition) {
        boolean isSelectAll;
        boolean isSelected = !(cartBean.getOrder().get(groupPosition).isGroupSelected());
        cartBean.getOrder().get(groupPosition).setGroupSelected(isSelected);
        for (int i = 0; i < cartBean.getOrder().get(groupPosition).getGoods().size(); i++) {
            cartBean.getOrder().get(groupPosition).getGoods().get(i).setChildSelected(isSelected);
            cartBean.getOrder().get(groupPosition).getGoods().get(i).setIsCheck(isSelected ? 1 : 0);
        }
        isSelectAll = isSelectAllGroup(cartBean);
        return isSelectAll;
    }

    /**
     * 族内的所有组，是否都被选中，即全选
     */
    private static boolean isSelectAllGroup(CartBean cartBean) {
        for (int i = 0; i < cartBean.getOrder().size(); i++) {
            boolean isSelectGroup = cartBean.getOrder().get(i).isGroupSelected();
            if (!isSelectGroup) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取结算信息，肯定需要获取总价和数量，但是数据结构改变了，这里处理也要变；
     *
     * @return 0=选中的商品数量；1=选中的商品总价
     */
    public static String[] getCartCount(CartBean cartBean) {
        String[] infos = new String[2];
        String selectedCount = "0";
        String selectedMoney = "0";
        for (int i = 0; i < cartBean.getOrder().size(); i++) {
            for (int j = 0; j < cartBean.getOrder().get(i).getGoods().size(); j++) {
                boolean isSelected = cartBean.getOrder().get(i).getGoods().get(j).isChildSelected();
                if (isSelected) {
                    String price = cartBean.getOrder().get(i).getGoods().get(j).getShopPrice();
                    String num = cartBean.getOrder().get(i).getGoods().get(j).getCartNum() + "";
                    String countMoney = DecimalUtil.multiply(price, num);
                    selectedMoney = DecimalUtil.add(selectedMoney, countMoney);
                    selectedCount = DecimalUtil.add(selectedCount, num);
                }
            }
        }
        infos[0] = selectedCount;
        infos[1] = selectedMoney;
        return infos;
    }

    /**
     * 单选一个，需要判断整个组的标志，整个族的标志，是否被全选，取消，则
     * 除了选择全部和选择单个可以单独设置背景色，其他都是通过改变值，然后notify
     *
     * @return 是否选择全部
     */
    public static boolean selectOne(CartBean cartBean, int groupPosition, int childPosition) {
        boolean isSelectAll;
        boolean isSelectedOne = !(cartBean.getOrder().get(groupPosition).getGoods().get(childPosition).isChildSelected());
        cartBean.getOrder().get(groupPosition).getGoods().get(childPosition).setChildSelected(isSelectedOne);//单个图标的处理
        cartBean.getOrder().get(groupPosition).getGoods().get(childPosition).setIsCheck(isSelectedOne ? 1 : 0);
        boolean isSelectCurrentGroup = isSelectAllChild(cartBean.getOrder().get(groupPosition).getGoods());
        cartBean.getOrder().get(groupPosition).setGroupSelected(isSelectCurrentGroup);//组图标的处理
        isSelectAll = isSelectAllGroup(cartBean);
        return isSelectAll;
    }

    /**
     * 组内所有子选项是否全部被选中
     *
     * @param list
     * @return
     */
    private static boolean isSelectAllChild(List<CartBean.OrderBean.GoodsBean> list) {
        for (int i = 0; i < list.size(); i++) {
            boolean isSelectGroup = list.get(i).isChildSelected();
            if (!isSelectGroup) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否有勾选商品
     *
     * @return
     */
    public static boolean hasSelectedGoods(CartBean cartBean) {
        String count = getCartCount(cartBean)[0];
        if ("0".equals(count)) {
            return false;
        }
        return true;
    }

    /**
     * 获得购物车中商品总数量(包括未勾选的)
     * @param cartBean
     * @return
     */
    public static int getTotalCount(CartBean cartBean) {
        if (cartBean.getOrder_sum() != null) {
            return Integer.valueOf(cartBean.getOrder_sum());
        } else {
            return 0;
        }
    }

    /**
     * 获取选中的商品
     *
     * @param cartBean
     * @return
     */
    public static String getSelectGood(CartBean cartBean) {
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < cartBean.getOrder().size(); i++) {
            for (int j = 0; j < cartBean.getOrder().get(i).getGoods().size(); j++) {
                boolean isSelected = cartBean.getOrder().get(i).getGoods().get(j).isChildSelected();
                if (isSelected) {
                    content.append(cartBean.getOrder().get(i).getGoods().get(j).getCartId())
                            .append(":")
                            .append(cartBean.getOrder().get(i).getGoods().get(j).getCartNum())
                            .append(",");
                }
            }
        }
        return content.toString();
    }

}
