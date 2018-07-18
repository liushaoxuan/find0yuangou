package com.ahxd.lingyuangou.constant;

/**
 * Created by Administrator on 2017/12/26.
 * 请求URL
 */

public class HostUrl {

//    public static final String HOST = "http://m.findlyg.com/index.php/";
    //测试环境
    public static final String HOST = "http://apptest.findlyg.com/";
    //线上环境
//    public static final String HOST = "http://m.findlyg.com/";

    // Home页数据获取
    public static final String URL_HOME = HOST + "api/index/index";

    /**
     * 获取系统配置接口
     */
    public static String URL_CONFIG = HOST + "api/shopping/geyconfig";

    /**
     * 首页搜索
     */
    public static final String URL_HOME_SEARCH = HOST + "api/plate/search";

    // 获取商城品类列表
    public static final String URL_CAT_LIST = HOST + "api/plate/getCatsList";
    // 获取店铺认证列表
    public static final String URL_ACCRED_LIST = HOST + "api/plate/getAccredsList";
    // 餐饮列表(商铺列表)
    public static final String URL_FOOD_LIST = HOST + "index.php/api/shops/shopList";
    // 餐饮商店详情
    public static final String URL_FOOD_SHOP_DETAIL = HOST + "index.php/api/shops/shopInfo";
    // 商铺评价列表
    public static final String URL_SHOP_EVALUATE_LIST = HOST + "api/appraiseslist/getList";
    // 店铺收藏（商品详情）
    public static final String URL_SHOP_FAVORITE = HOST + "index.php/api/favorites/set";

    // 推荐店铺
    public static final String URL_RECOM_SHOP_LIST = HOST + "api/plate/recommendShops";
    // 推荐商品
    public static final String URL_RECOM_GOODS_LIST = HOST + "api/plate/recommendGoods";
    //获取商品列表
    public static final String URL_GOODS_LIST = HOST + "index.php/api/goods/getList";

    /**
     * 评价
     */
    public static final String URL_EVALUATE_SUBMIT = HOST + "index.php/api/appraises/put";

    /**
     * 文章
     */
    // 文章列表
    public static final String URL_ARTICLE_LIST = HOST + "api/articles/getList";
    // 文章内容
    public static final String URL_ARTICLE_DETAIL = HOST + "api/articles/getInfo";


    /**
     * 商品
     */
    // 商品详情
    public static final String URL_GOOD_DETAIL = HOST + "index.php/api/shops/detailHtml";
    public static final String URL_GIFTGOOD_DETAIL = HOST + "api/scoregoods/getInfoH5";

    /**
     * 个人中心
     */
    // 个人首页
    public static final String URL_MINE = HOST + "index.php/api/users/getInfo";
    // 忘记密码
    public static final String URL_FORGET = HOST + "api/index/forget";
    // 注册
    public static final String URL_REGISTER = HOST + "api/index/register";
    // 获取验证码
    public static final String URL_GET_VALIDATE_CODE = HOST + "api/index/send";
    // 忘记密码验证码
    public static final String URL_FORGETGET_VALIDATE_CODE = HOST + "api/index/forgetSend";
    // 登录
    public static final String URL_LOGIN = HOST + "api/index/login";

    /**
     * 个人资料
     */
    // 获取个人资料
    public static final String URL_PERSONAL_INFO = HOST + "index.php/api/users/getInfo";
    // 个人资料编辑
    public static final String URL_PERSONAL_EDIT = HOST + "index.php/api/users/editInfo";


    /**
     * 商铺入住
     */
    // 商铺入住获取验证码
    public static final String URL_SHOP_STAY_CODE = HOST + "api/shopapplys/applySMS";
    // 商铺入住申请
    public static final String URL_SHOP_STAY_SUBMIT = HOST + "api/shopapplys/applyShop";

    // 优惠买单 商铺信息
    public static final String URL_PAY_SHOP_DETAIL = HOST + "index.php/api/pay/taxPay";
    // 优惠买单 付款
    public static final String URL_PAY_OFFLINE = HOST + "index.php/api/pay/taxPayment";

    /**
     * 个人地址
     */
    // 地址列表
    public static final String URL_ADDRESS_LIST = HOST + "index.php/api/address/addressList";
    // 删除地址
    public static final String URL_ADDRESS_DELETE = HOST + "index.php/api/address/del";
    // 添加地址
    public static final String URL_ADDRESS_ADD = HOST + "index.php/api/address/add";
    // 编辑地址
    public static final String URL_ADDRESS_MODIFY = HOST + "index.php/api/address/edit";
    // 设置默认地址
    public static final String URL_ADDRESS_SET_DEFAULT = HOST + "index.php/api/address/defaultAddress";
    // 获得省份
    public static final String URL_ADDRESS_EDIT_PROVINCE = HOST + "index.php/api/address/get_province";
    // 获取城市
    public static final String URL_ADDRESS_EDIT_CITY = HOST + "index.php/api/address/get_city";
    // 获得区域
    public static final String URL_ADDRESS_EDIT_AREA = HOST + "index.php/api/address/get_area";

    /**
     * 购物车
     */
    // 购物车列表
    public static final String URL_CART_GOODS_LIST = HOST + "api/carts/cartList";
    // 购物车列表(兑换)
    public static final String URL_SCORECARTS_GOODS_LIST = HOST + "api/scorecarts/cartList";
    // 删除商品
    public static final String URL_CART_DELETE_GOOD = HOST + "api/carts/delCart";
    // 删除商品(兑换)
    public static final String URL_SCORECARTS_DELETE_GOOD = HOST + "api/scorecarts/delCart";
    // 编辑购物车
    public static final String URL_CART_EDIT_GOOD = HOST + "api/carts/editCar";
    // 编辑购物车(兑换)
    public static final String URL_SCORECARTS_EDIT_GOOD = HOST + "api/scorecarts/editCar";
    // 提交订单
    public static final String URL_CART_SUBMIT_ORDER = HOST + "index.php/api/pay/cartToOrder";
    // 提交订单(兑换)
    public static final String URL_SCORECARTS_SUBMIT_ORDER = HOST + "index.php/api/scoreorders/cartToOrder";
    // 线上支付
    public static final String URL_ONLINE_PAY = HOST + "index.php/api/pay/cartPay";
    // 线上支付(兑换)
    public static final String URL_SCORECARTS_ONLINE_PAY = HOST + "index.php/api/scoreorders/cartPay";

    /**
     * 商品详情界面
     */
    // 立即购买（下单）
    public static final String URL_CART_BUY_NOW = HOST + "index.php/api/pay/foodPay";
    // 立即购买（兑换下单）
    public static final String URL_SCORECARTS_CART_BUY_NOW = HOST + "index.php/api/scoreorders/quickPay";
    // 添加购物车
    public static final String URL_CART_ADD = HOST + "api/carts/addCart";
    // 添加购物车(兑换)
    public static final String URL_SCORECARTS_ADD = HOST + "api/scorecarts/addCart";
    // 立即购买（支付）
    public static final String URL_ONLINE_PAY_RIGHT_NOW = HOST + "index.php/api/pay/foodPayment";
    // 立即购买（兑换支付）
    public static final String URL_ONLINE_PAY_RIGHTQUICK_NOW = HOST + "index.php/api/scoreorders/quickPayment";

    /**
     * 充值
     */
    // 获取充值数额 列表
    public static final String URL_RECHARGE_LIST = HOST + "api/rechange/getList";
    // 充值
    public static final String URL_RECHARGE = HOST + "index.php/api/Rechange/recharges";

    /**
     * 收藏列表
     */
    public static final String URL_FAVORITE_LIST = HOST + "index.php/api/favorites/getList";
//    收藏列表删除
    public static final String URL_DELETE_FAVORITE_LIST = HOST + "index.php/api/favorites/cancel";

    /**
     * 我的订单
     */
    // 订单列表
    public static final String URL_ORDER_LIST = HOST + "api/orders/getList";
    // 订单列表（兑换）
    public static final String URL_SCORE_ORDER_LIST = HOST + "api/scoreorders/getList";
    // 订单详情
    public static final String URL_ORDER_DETAIL = HOST + "api/orders/getInfo";
    // 订单详情(兑换)
    public static final String URL_SCORE_ORDER_DETAIL = HOST + "api/scoreorders/getInfo";
    // 订单支付详情界面
    public static final String URL_ORDER_PAY_INFO = HOST + "index.php/api/pay/orderInfo";
    // 订单支付详情界面(兑换)
//    public static final String URL_ORDER_PAY_INFO = HOST + "index.php/api/pay/orderInfo";
    // 订单列表，支付接口
    public static final String URL_ONLINE_PAY_ORDER_LIST = HOST + "index.php/api/pay/orderPay";
    // 取消订单
    public static final String URL_ORDER_CANCEL = HOST + "api/orders/cancel";
    // 取消订单(兑换)
    public static final String URL_GIFTORDER_CANCEL = HOST + "api/scoreorders/cancel";
    // 确认收货
    public static final String URL_ORDER_CONFIRM_RECEIVER = HOST + "api/orders/confirm";
    // 确认收货(兑换)
    public static final String URL_SCORE_ORDER_CONFIRM_RECEIVER = HOST + "api/scoreorders/confirm";


    /**
     * 我的钱包
     */
    // 我的钱包
    public static final String URL_WALEET = HOST + "api/users/cash";
    // 我的钱包-积分
    public static final String URL_WALEET_INTEGRAL= HOST + "index.php/api/Cashdraws/scoreToMoneyManage";
    // 我的钱包-积分兑换
    public static final String URL_SCORE_TO_MONEY= HOST + "index.php/api/Cashdraws/scoreToMoney";
    //积分记录
    public static final String URL_INTEGRAL_LIST = HOST + "index.php/api/Cashdraws/giftList";
    // 充值记录
    public static final String URL_RECHANGE_LIST = HOST + "api/rechange/getLogs";
    // 消费记录
    public static final String URL_RECHANGE_BUY_LIST = HOST + "api/rechange/getBuyLogs";
    // 提现记录
    public static final String URL_CASHDRAWS_LIST = HOST + "api/Cashdraws/getLogs";
    // 银行列表
    public static final String URL_CASHDRAWS_BANKS_LIST = HOST + "api/Cashdraws/getBanks";
    // 添加银行卡
    public static final String URL_CASHDRAWS_SETCONFIG = HOST + "api/cashdraws/setConfig";
    // 获取提现账户列表
    public static final String URL_CASHDRAWS_GETCONFIG = HOST + "api/cashdraws/getConfig";
    // 申请提现
    public static final String URL_CASHDRAWS_DRAWMONEY = HOST + "api/Cashdraws/drawMoney";
    //广告收益记录
    public static final String URL_RECOMMEND_RECOMMENDLOG = HOST + "api/recommend/getRecommendLog";
    //营销收益记录
    public static final String URL_MARKETING_INCOMELOG = HOST + "api/marketing/incomeLog";
    //消息记录
    public static final String URL_MESSAGES = HOST + "api/users/getMessages";
    //消息已读
    public static final String URL_PUT_MESSAGES_STATUS = HOST + "api/users/putMessagesStatus";


    /**
     * 文件上传
     */
    public static final String URL_FILE_USER_HEADER = HOST + "index.php/api/users/uploadPicture";


    /**
     * 推广中心
     */
    // 推广中心
    public static final String URL_RECOMMEND_INDEX = HOST + "api/recommend/index";
    // 我的会员
    public static final String URL_RECOMMEND_GETRECOMMENDLIST = HOST + "api/recommend/getRecommendList";
    // 我的名片
    public static final String URL_USERS_CARD = HOST + "api/users/card";

    /**
     * 营销中心
     */
    // 营销人入驻信息
    public static final String URL_INDEX_MARKETING = HOST + "api/index/marketing";
    // 营销人入驻申请
    public static final String URL_MARKETING_APPLY = HOST + "api/marketing/apply";
    // 获取营销店铺
    public static final String URL_MARKETING_SHOPLIST = HOST + "api/marketing/shopList";
    // 获取营销码
    public static final String URL_MARKETING_INDEX = HOST + "api/marketing/index";
    // 获取营销码收益明细
    public static final String URL_MARKETING_SHOOLOG = HOST + "api/marketing/shopLog";


    // 联系我们
    public static final String URL_INDEX_CONTACTUS = HOST + "api/index/contactUs";
    // 客服电话
    public static final String URL_INDEX_CUSTOMSERVICE = HOST + "api/index/customService";


    /**
     * 积分商城
     */
//    积分商城首页
    public static final String URL_SCORE_INDEX = HOST + "api/score/index";
//    兑换礼品列表
    public static final String URL_SCOREGOODS_LIST = HOST + "api/scoregoods/getList";
//    授权店列表
    public static final String URL_SCOREGOODS_SHOPLIST = HOST + "api/scoregoods/getShop";
//    兑换消费记录
    public static final String URL_SCOREGOODS_PAYLIST = HOST + "api/scoreorders/getPayLogs";
//    授权店详细
    public static final String URL_SCOREGOODS_SHOPINFO = HOST + "api/scoregoods/getShopInfo";

//    获取版本号及下载地址
    public static final String URL_PLATE_CHECKVERSION = HOST + "api/plate/checkVersion";

    /**
     * 个人中心
     */

    // 获取某个商家下所有的会员
    public static final String URL_UCERNTER_GETSHOPMEMBERLIST = HOST + "api/Shopping/"+"getShopMemberList";

    //某个商家的报表
    public static final String URL_UCERNTER_SHOPCONSUMERREPORT = HOST + "api/Shopping/"+"shopConsumerReport";

    //某个商家的订单
    public static final String URL_UCERNTER_SHOPCONSUMERORDER = HOST + "api/Shopping/"+"shopConsumerOrder";

    /**
     *  获取商家和卡信息 购买资格
     */
    public static final String URL_UCERNTER_PURCHASE_QUALIFICATION = HOST + "api/Shopping/"+"buycardhtml_zige";

    /**
     *  购买商家卡页 获取商家信息
     */
    public static final String URL_UCERNTER_BUSINESS_INFORMATION = HOST + "api/Shopping/"+"buycardhtml_sj";

    /**
     * 购买商家卡页 获取某商家的所有卡信息
     */
    public static final String URL_UCERNTER_BUSINESS_INFORMATION_CARDS = HOST + "api/Shopping/"+"buycardhtml_sjcard";

    /**
     * 购买资格  购买商家卡 选择卡后的接口
     */
    public static final String URL_UCERNTER_BUYQUALIFICATIONDISPLAY = HOST + "api/Shopping/"+"buyQualificationDisplay";
    /**
     * 购买卡明细
     */
    public static final String URL_UCERNTER_BUYCARDDETAIL = HOST + "api/Shopping/"+"buycarddetail";
    /**
     * 购买商家卡准备之前
     */
    public static final String URL_UCERNTER_BUYQUALIFICATIONREADY = HOST + "api/Shopping/"+"buyQualificationReady";

    /**
     * 购买资格前的准备
     */
    public static final String URL_UCERNTER_BUYUSERCARDREADY = HOST + "api/Shopping/"+"buyUserCardReady";
    /**
     * 购买资格卡完成
     */
    public static final String URL_UCERNTER_BUYQUALIFICATIONCOMPLETE = HOST + "api/Shopping/"+"buyQualificationComplete";

    /**
     * 获取购买商家卡的支付信息
     */
    public static final String URL_UCERNTER_PAYPARAMETER = HOST + "api/pay/payparameter";

}
