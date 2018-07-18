package com.ahxd.lingyuangou.constant;

/**
 * Created by Administrator on 2017/12/26.
 */

public class Constant {

    // 请求结果参数
    public static final String RESP_CODE = "status";
    public static final String RESP_MSG = "msg";
    public static final String RESP_DATA = "data";

    // 成功
    public static final int RESP_SUCCESS = 1;
    // 失败
    public static final int RESP_FAILURE = 0;
    // 登录超时
    public static final int RESP_RELOGIN = 100;
    // 请求结果参数



    // 电话权限码
    public static final int PERMISSION_PHONE_CALL = 0x50;

    // token
    public static final String KEY_TOKEN = "token";
    // city
    public static final String KEY_CITY = "city";
    // longitude, latitude
    public static final String KEY_LOCATION = "location";

    // 微信APPID
    public static final String WEIXIN_APPID = "wx4c3d134a32dc3bf1";


    // startActivityForResult
    // 编辑地址
    public static final int REQ_EDIT_ADDRESS = 0x2000;
    // 登录
    public static final int REQ_LOGIN = 0x2001;
    // 选择地址
    public static final int REQ_ADDRESS = 0x2002;
    // 编辑地址
    public static final int REQ_EDIT_PERSONAL = 0x2003;
    // 评价
    public static final int REQ_EVALUATE = 0x2004;
    // 支付
    public static final int REQ_PAY = 0x2005;

    // 购买资格
    public static final  int REQ_PURCHASEQUALIFICATIO = 0x2005;

    public static final int PAY_TYPE_WALLET = 0x4001;
    public static final int PAY_TYPE_WX = 2;
    public static final int PAY_TYPE_ALIPAY = 3;

    /**
     * 数据库
     */
    // 数据库名
    public static final String DATABASE_NAME = "lingyuangou.db";
    // 搜索表
    public static final String TABLE_SEARCH_HISTORY = "search_history";
    // 数据库版本
    public static final int DATABASE_VERSION = 1;

    /**
     * 标记
     */
    public static final String SP_WEIXIN_PAY = "sp_weixin_pay";
}
