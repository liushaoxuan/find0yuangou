package com.ahxd.lingyuangou.bean;

import java.util.List;

/**
 * Created by Mao Zhendong on 2018/1/8.
 * 购物车 Bean
 */

public class CartBean {


    /**
     * order_sum : 3
     * money_sum : 57
     * order : [{"shopName":"0元购自营总部","goods":[{"cartId":2,"goodsId":1,"isCheck":1,"cartNum":2,"goodsSpecId":"","goodsName":"测试商品","goodsStock":19,"goodsImg":"http://m.findlyg.com/upload/goods/2017-12/5a2616fc06cc8.png","shopPrice":"11.00","shopId":1},{"cartId":5,"goodsId":3,"isCheck":1,"cartNum":3,"goodsSpecId":"标配一/高配一/","goodsName":"测试商品3","goodsStock":10,"goodsImg":"http://m.findlyg.com/upload/goods/2017-12/5a41e53534175.jpg","shopPrice":"11.00","shopId":1}]},{"shopName":"店铺2","goods":[{"cartId":4,"goodsId":2,"isCheck":1,"cartNum":2,"goodsSpecId":"","goodsName":"测试商品2","goodsStock":99,"goodsImg":"http://m.findlyg.com/upload/goods/2017-12/5a3b78f3c9fd8.png","shopPrice":"1.00","shopId":3}]}]
     */

    private String order_sum;
    private float money_sum;
    private List<OrderBean> order;

    public String getOrder_sum() {
        return order_sum;
    }

    public void setOrder_sum(String order_sum) {
        this.order_sum = order_sum;
    }

    public float getMoney_sum() {
        return money_sum;
    }

    public void setMoney_sum(float money_sum) {
        this.money_sum = money_sum;
    }

    public List<OrderBean> getOrder() {
        return order;
    }

    public void setOrder(List<OrderBean> order) {
        this.order = order;
    }

    public static class OrderBean {
        /**
         * shopName : 0元购自营总部
         * goods : [{"cartId":2,"goodsId":1,"isCheck":1,"cartNum":2,"goodsSpecId":"","goodsName":"测试商品","goodsStock":19,"goodsImg":"http://m.findlyg.com/upload/goods/2017-12/5a2616fc06cc8.png","shopPrice":"11.00","shopId":1},{"cartId":5,"goodsId":3,"isCheck":1,"cartNum":3,"goodsSpecId":"标配一/高配一/","goodsName":"测试商品3","goodsStock":10,"goodsImg":"http://m.findlyg.com/upload/goods/2017-12/5a41e53534175.jpg","shopPrice":"11.00","shopId":1}]
         */

        private String shopName;
        private List<GoodsBean> goods;

        public boolean isEditing() {
            return isEditing;
        }

        public void setEditing(boolean editing) {
            isEditing = editing;
        }

        public boolean isGroupSelected() {
            return isGroupSelected;
        }

        public void setGroupSelected(boolean groupSelected) {
            isGroupSelected = groupSelected;
        }

        /** 是否处于编辑状态 */
        private boolean isEditing;
        /** 组是否被选中 */
        private boolean isGroupSelected;

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * cartId : 2
             * goodsId : 1
             * isCheck : 1
             * cartNum : 2
             * goodsSpecId :
             * goodsName : 测试商品
             * goodsStock : 19
             * goodsImg : http://m.findlyg.com/upload/goods/2017-12/5a2616fc06cc8.png
             * shopPrice : 11.00
             * shopId : 1
             */

            /**
             * 是否是编辑状态
             */
            private boolean isEditing = false;
            /**
             * 是否被选中
             */
            private boolean isChildSelected = false;

            private String cartId;
            private String goodsId;
            private int isCheck;
            private int cartNum;
            private String goodsSpecId;
            private String goodsName;
            private String goodsStock;
            private String goodsImg;
            private String shopPrice;
            private String shopId;
            private String marketPrice;

            public boolean isEditing() {
                return isEditing;
            }

            public void setEditing(boolean editing) {
                isEditing = editing;
            }

            public boolean isChildSelected() {
                return isChildSelected;
            }

            public void setChildSelected(boolean childSelected) {
                isChildSelected = childSelected;
            }

            public String getCartId() {
                return cartId;
            }

            public void setCartId(String cartId) {
                this.cartId = cartId;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public int getIsCheck() {
                return isCheck;
            }

            public void setIsCheck(int isCheck) {
                this.isCheck = isCheck;
            }

            public int getCartNum() {
                return cartNum;
            }

            public void setCartNum(int cartNum) {
                this.cartNum = cartNum;
            }

            public String getGoodsSpecId() {
                return goodsSpecId;
            }

            public void setGoodsSpecId(String goodsSpecId) {
                this.goodsSpecId = goodsSpecId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsStock() {
                return goodsStock;
            }

            public void setGoodsStock(String goodsStock) {
                this.goodsStock = goodsStock;
            }

            public String getGoodsImg() {
                return goodsImg;
            }

            public void setGoodsImg(String goodsImg) {
                this.goodsImg = goodsImg;
            }

            public String getShopPrice() {
                return shopPrice;
            }

            public void setShopPrice(String shopPrice) {
                this.shopPrice = shopPrice;
            }

            public String getShopId() {
                return shopId;
            }

            public void setShopId(String shopId) {
                this.shopId = shopId;
            }

            public String getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(String marketPrice) {
                this.marketPrice = marketPrice;
            }
        }
    }
}
