package com.qx.mstarstoretv.base;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.utils.UIUtils;

/**
 * Created by Administrator on 2016/9/21.
 */
public class AppURL {
//     static String QxVersion = "&QxVersion="+ "beta"+"&";
    static String QxVersion = "QxVersion="+ UIUtils.getContext().getResources().getString(R.string.app_version)+"&";
//    static String QxVersion = "QxVersion="+ "beta1.2"+"&"+"";
    /*"http://192.168.1.240:9112/api/Aproxy/*/
    private static String baseUrl = "http://appapi1.fanerweb.com/api/aproxy/";
    //测试 http://appapi1.fanerweb.com/api/Aproxy/
    private static String baseUrl1 = "http://appapi.fanerweb.com/api/aproxy/";
    // private static String baseUrl="http://192.168.1.240:9112/api/Aproxy/";
    public static String URL_LOGIN = baseUrl + "userLoginDo?"+QxVersion;


    public static String URL_REGISTER = baseUrl + "userRegisterDo?"+QxVersion;

    public static String URL_HOME = baseUrl + "userAdminPage?"+QxVersion;

    /*修改密码*/
    public static String URL_UPDATE_PWD = baseUrl + "userModifyPasswordDo?"+QxVersion;
    //userModifyPasswordDo?tokenKey=841bf597782dced089a6e5167bcf29de&password=123456&phoneCode=32656
    /*添加收货地址*/
    public static String URL_SHOP_ADDRESS = baseUrl + "userAddAddressPage?"+QxVersion;
    /*得到*/   // getCity?tokenKey=944df2f27ffce557042887589986c193&id=18
    public static String URL_GET_CITY = baseUrl + "getCity?"+QxVersion;
    /**/   //getArea?tokenKey=944df2f27ffce557042887589986c193&id=278
    public static String URL_GET_AREA = baseUrl + "getArea?"+QxVersion;

    /*添加地址*/
    // addUserAddressDo?tokenKey=944df2f27ffce557042887589986c193&name=广播站&provinceId=20&cityId=319&areaId=3230&phone=15994767200&addr=神木林百度洞44&isDefault=1
    public static String URL_ADD_ARDRESS = baseUrl + "addUserAddressDo?"+QxVersion;

    public static String URL_ADDRESS_MANAGER = baseUrl + "AddressListPage?"+QxVersion;

    /*选择地址*/
    public static String URL_ADDRESS_SELECT = baseUrl + "AddressListPageForSelect?"+QxVersion;

    /*删除地址*/
    public static String URL_DEL_ADDRESS = baseUrl + "deleteAddressDo?"+QxVersion;

    /*修改地址页面获取原地址
    * */
    public static String URL_MODIFY_ADDRESS = baseUrl + "userModifyAddressPage?"+QxVersion;

    /*修改提交*/
    public static String URL_MODIFY_ADDRESS_DO = baseUrl + "modifyAddressDo?"+QxVersion;
    // modifyAddressDo?tokenKey=944df2f27ffce557042887589986c193&name=广播站&provinceId=20&cityId=319&areaId=3230&phone=15994767200&addr=神木林百度洞44&id=4&isDefault=1

    /*设置默认地址*/
    public static String URL_DEFAULT_ADDRESS = baseUrl + "setDefaultAddressDo?"+QxVersion;
    // setDefaultAddressDo?tokenKey=944df2f27ffce557042887589986c193&id=2

    //            http://appapi.fanerweb.com/api/aproxy/modelListPage?tokenKey=4402575f73660a61eabc2b8bcc56fef8

    //modelListPage?tokenKey=944df2f27ffce557042887589986c193&category=1,2,3,4,5,78,lo,34&keyWord=mm|xx|vv|%27gg|莱尔|xx&price=6000|10000&weight=0.9|1.8
    /*款号列表*/
    public static String URL_MODE_LIST = baseUrl + "modelListPage?"+QxVersion;

    /*上传头像*/
    public static String URL_UPLOAD_PiC = baseUrl + "userModifyHeadPicDo?"+QxVersion;


    /*款号详情   ModelDetailPage?tokenKey=944df2f27ffce557042887589986c193&id=1*/
    public static String URL_MODEL_DETAIL = baseUrl + "ModelDetailPage?"+QxVersion;

    /*款号修改   ModelDetailPage?tokenKey=944df2f27ffce557042887589986c193&id=1*/
    public static String URL_MODEL_UPDATE = baseUrl + "ModelDetailPageForCurrentOrderEditPage?"+QxVersion;

    //?itemId=16&tokenKey=10b588002228fa805231a59bb7976bf4
    //订单页修改款号
    public static String URL_ORDER_MODEL_UPDATE = baseUrl+ "ModelOrderWaitCheckModelDetailPageForCurrentOrderEditPage?"+QxVersion;

    /*获得石头价格*/
    public static String URL_STONE_PRICE = baseUrl + "getStonePrice?"+QxVersion;
    //getStonePrice?tokenKey=944df2f27ffce557042887589986c193&colorId=4&categoryId=4&specId=1&purityId=2


    /*添加到当前订单*/
    public static String URL_CURRENT_ORDER = baseUrl + "OrderDoCurrentModelItemDo?"+QxVersion;
    //OrderDoCurrentModelItemDo?productId=1&categoryId=8&number=2&handSize=3&stone=1|3|2|3|4|5&stoneA=1|2|2|3|4|5&stoneB=1|2|3|3|4|5&stoneC=1|2|3|3|4|6&tokenKey=10b588002228fa805231a59bb7976bf4

    /*修改添加添加到当前订单*/
    public static String URL_CURRENT_EDIT_ORDER = baseUrl + "OrderCurrentEditModelItemDo?"+QxVersion;


    //订单页跳转修改页修改
    public static String URL_UPDATE_ORDER_WATET = baseUrl + "ModelOrderWaitCheckOrderCurrentEditModelItemDo?"+QxVersion;
    //?itemId=16&number=2&handSize=8&stone=1|3|2|3|3|3&stoneA=1|2|2|3|3|3&stoneB=1|2|2|3|3|5&stoneC=1|2|3|3|3|3&tokenKey=10b588002228fa805231a59bb7976bf4
    /*类型选择*/
    // modelFilerPage?tokenKey=944df2f27ffce557042887589986c193

    public static String URL_MODEL_FILTER = baseUrl + "modelFilerPage?"+QxVersion;


   /*当前订单*/
    //OrderDoCurrentModelItemDo?productId=1&categoryId=8&number=2&handSize=3&stone=1|3|2|3|4|5&stoneA=1|2|2|3|4|5&stoneB=1|2|3|3|4|5&stoneC=1|2|3|3|4|6&tokenKey=10b588002228fa805231a59bb7976bf4


    /*生成订单*/
    //OrderListPage?tokenKey=10b588002228fa805231a59bb7976bf4&cpage=1
    public static String URL_ORDER_LIST = baseUrl + "OrderListPage?"+QxVersion;


    //ModelOrderWaitCheckDetail?tokenKey=10b588002228fa805231a59bb7976bf4&cpage=1&orderId=13
    public static String URL_ORDER_DETAIL = baseUrl + "ModelOrderWaitCheckDetail?"+QxVersion;


    /*搜索客户*/
    //IsHaveCustomer?keyword=广西|平果&tokenKey=944df2f27ffce557042887589986c193
    public static String URL_HAVE_CUSTOMER = baseUrl + "IsHaveCustomer?"+QxVersion;

    /*客户列表*/
    //GetCustomerList?keyword=湖南|益阳&cpage=1&tokenKey=944df2f27ffce557042887589986c193
    public static String URL_CUSTOMER_LIST = baseUrl + "GetCustomerList?"+QxVersion;



    /*删除订单*/
    //GetCustomerList?keyword=湖南|益阳&cpage=1&tokenKey=944df2f27ffce557042887589986c193
    public static String URL_ORDER_DELETE = baseUrl + "OrderCurrentDeleteModelItemDo?"+QxVersion;

    //审核页跳转订单页
   // ?itemId=3&tokenKey=10b588002228fa805231a59bb7976bf4
    public static String URL_ORDER_WAIT_DELETE = baseUrl + "ModelOrderWaitCheckDetailDeleteModelItemDo?"+QxVersion;
    // ?itemId=1&tokenKey=10b588002228fa805231a59bb7976bf4


    /*提交订单*/
    //OrderCurrentSubmitDo?itemId=1|2|3&addressId=1&purityId=3&qualityId=2&tokenKey=944df2f27ffce557042887589986c193
    public static String URL_ORDER_SUBMIT = baseUrl + "OrderCurrentSubmitDo?"+QxVersion;

    /*获取单个石头价格*/
   // GetOrderPricePageList?tokenKey=944df2f27ffce557042887589986c193&cpage=1&purityId=1&qualityId=1
    public static String URL_ORDER_PRICE = baseUrl + "GetOrderPricePageList?"+QxVersion;

    /*定金页价格修改*/
   // ?purityId=1&qualityId=1&orderId=13&tokenKey=10b588002228fa805231a59bb7976bf4
    public static String URL_WATI_ORDER_PRICE = baseUrl + "ModelOrderWaitCheckModifyGetOrderPricePageListDo?";
    /*订单详情修改地址*/
    // ?purityId=1&qualityId=1&orderId=13&tokenKey=10b588002228fa805231a59bb7976bf4
    public static String URL_ORDER_ADRESS_CHANGE = baseUrl + "ModelOrderWaitCheckDetailModifyAddressDo?";

    /*待审核订单  ModelOrderWaitCheckList?tokenKey=10b588002228fa805231a59bb7976bf4 */
    public static String URL_ORDER_WAITCHECK = baseUrl + "ModelOrderWaitCheckList?"+QxVersion;


    /*生产中的订单  ModelOrderProduceListPage?tokenKey=10b588002228fa805231a59bb7976bf4&cpage=2 */
    public static String URL_ORDER_MODEL_LIST = baseUrl + "ModelOrderProduceListPage?"+QxVersion;
    //修改自印和客户ID
    public static String URL_WATI_ORDER_MODIINFO = baseUrl + "ModelOrderWaitCheckDetailModifyInfoDo?"+QxVersion;

    /*审核页面挑战取消订单*/
   // ?orderId=10&tokenKey=10b588002228fa805231a59bb7976bf4
    public static String URL_ORDER_WAIT_CANCEL = baseUrl + "ModelOrderWaitCheckCancelDo?"+QxVersion;


    /*订单 详情  ModelOrderProduceDetailPage?orderNum=IAR201611101415&tokenKey=10b588002228fa805231a59bb7976bf4  */
    public static String URL_PD_ORDER_DETAIL = baseUrl + "ModelOrderProduceDetailPage?"+QxVersion;

    /**
     * 订单历史详情
     */
    public static String URL_PD_ORDER_DETAIL_HISTORY = baseUrl+"ModelOrderProduceDetailHistoryPage?"+QxVersion;
    /*登录验证码*/
    public static String URL_LOGING_CODE=baseUrl+"GetLoginVerifyCodeDo?"+QxVersion;

    /*注册验证码*/
    public static String URL_REGISTER_CODE=baseUrl+"GetRegisterVerifyCodeDo?"+QxVersion;


    /*修改密码验证码*/
    public static String URL_UPDATA_PWD_CODE=baseUrl+"GetUserModifyPasswordVerifyCodeDo?"+QxVersion;

    /*ModelInvoicePage?tokenKey=944df2f27ffce557042887589986c193 获取发票*/
    public static String URL_MODELINVOICE_PAGE=baseUrl+"ModelInvoicePage?"+QxVersion;


    /*1.1.2.4生产进度*/
    public static String URL_MODELPRODUCE_PROGRESS=baseUrl+"ModelOrderProduceDetailShowRateProgressPage?"+QxVersion;
   // ?tokenKey=509e15ec5b23808d098ecaf1f533c7de&orderNum=AP2016121617116


    /*1.1.2验证输入的规格*/
    public static String URL_CHECKSPEIC= baseUrl + "CheckSpecificationsForm?"+QxVersion;


    /*个人设置页面*/
    public static String URL_USER_MODIFY= baseUrl + "userModifyPage?"+QxVersion;
    //http://appapi1.fanerweb.com/api/Aproxy/userModifyPage?tokenKey=509e15ec5b23808d098ecaf1f533c7de
    /**
     * 是否显示价格
     */
    public static String URL_IS_SHOW_PRICE =baseUrl + "UpdateIsShowPrice?"+QxVersion;
    //忘记密码
    public static String URL_USER_FORGETPWD= baseUrl + "userForgetPasswordDo?"+QxVersion;

    //http://appapi.fanerweb.com/api/Aproxy/GetForgetPasswordVerifyCodeDo?phone=xxxxx  忘记密码获取验证码
    public static String URL_CODE_FORGETPWD= baseUrl + "GetForgetPasswordVerifyCodeDo?"+QxVersion;

    //结算单
    public static String URL_CODE_FINISH= baseUrl + "ModelFinishBillList?"+QxVersion;
    //结算单详情
    public static String URL_CODE_FINISH_DETAIL= baseUrl + "ModelBillFinishDetailRec?"+QxVersion;
    //已货单列表 http://appapi1.fanerweb.com/api/Aproxy/ModelBillList?cpage=3&tokenKey=7cdcf3a6a47904dbff1e7da86b8ef225
    public static String URL_CODE_SENDING= baseUrl + "ModelBillList?"+QxVersion;
    //已货单详情
    public static String URL_CODE_SENDING_DETAIL= baseUrl + "ModelArriveBillMo?"+QxVersion;
    //获得版本信息
    public static String URL_CODE_VERSION = baseUrl + "currentVersion?"+QxVersion;

    //订单搜索页面
    public static String URL_CODE_ORDER_SEARCH = baseUrl + "ModelUserOrderSearchPage?"+QxVersion;
    //搜索订单列表页
    public static String URL_CODE_ORDER_SEARCH_LIST = baseUrl + "ModelOrderSearch?"+QxVersion;
    //搜索详情页
    public static String URL_CODE_ORDER_SEARCH_DETAIL = baseUrl + "ModelOrderSearchDetail?"+QxVersion;
    //搜索结算详情页
    public static String URL_CODE_SEARCH_FINSIH_DETAIL = baseUrl + "ModelBillFinishDetailRecForSearch?"+QxVersion;
    //搜索出库详情页
    public static String URL_CODE_SEARCH_DELIVERY_DETAIL = baseUrl + "ModelArriveBillMoForSearch?"+QxVersion;
    /*1.1.2.4搜索页面的生产进度*/
    public static String URL_MODELPRODUCE_PROGRESS2=baseUrl+"ModelOrderProduceDetailShowRateProgressPageForSearch?"+QxVersion;
    /*订单 详情  ModelOrderProduceDetailPage?  */
    public static String URL_PD_ORDER_DETAIL2 = baseUrl + "ModelOrderProduceDetailHistoryPageForSearch?"+QxVersion;
    /*裸石库  http://appapi1.fanerweb.com/api/Aproxy/stoneSearchInfo?tokenKey=e9884c4435d75a135ce41fb668788827*/
    public static String URL_STONE_SEARCHINFO = baseUrl + "stoneSearchInfoInhk?"+QxVersion;
    /*搜索裸石列表  http://appapi1.fanerweb.com/api/Aproxy/stoneList?tokenKey=e9884c4435d75a135ce41fb668788827&cpage=1&purity=vvs2&color=h,f*/
    public static String URL_STONE_LIST = baseUrl + "stoneList?"+QxVersion;
    /**
     *裸石报价
     */
    public static String URL_STONE_QUOTED_PRICE = baseUrl+ "stoneOffer?"+QxVersion;

    /**
     *提交要支付的订单
     */
    public static String URL_PAY_CURRENT_ORDER = baseUrl+ "PaymentCurrentOrderPage?"+QxVersion;
    /**
     * 支付宝回调接口
     * http://appapi1.fanerweb.com/api/Payment/ReceiveAilpayNotice
     */
    public static String URL_PAY_ALIPAY= "http://appapi1.fanerweb.com/api/Payment/ReceiveAilpayNotice?"+QxVersion;
    /**
     * 订单支付宝回调接口
     * http://appapi1.fanerweb.com/api/Payment/GetAilpayPayStr?tokenKey=69a875e5c234b5b49d1bead689f84832
     */
    public static String URL_GETAILPAY= "http://appapi1.fanerweb.com/api/Payment/GetAilpayModelOrderPayStr?"+QxVersion;
    /**
     * 获取微信支付数据接口
     * http://appapi1.fanerweb.com/api/Payment/ReceiveAilpayNotice
     */
    public static String URL_PAY_WEIXIN= "http://appapi1.fanerweb.com/api/Payment/GetWxpayModelParameter?"+QxVersion;
    /**
     * 石头确认下单
     */
    public static String URL_STONE_PLACE_ORDER=  baseUrl + "stoneOrderListPage?"+QxVersion;
    /**
     * 石头订单详情
     */
    public static String URL_STONE_ORDER_DETAIL=  baseUrl + "stoneOrderDetailpage?"+QxVersion;
    /**石头 获取发票*/
    public static String URL_STONE_INVOICE=baseUrl+"StoneInvoicePage?"+QxVersion;
    /**
     * 石头订单提交
     */
    public static String URL_STONE_COMMIT_ORDER=baseUrl+"stoneSubmitOrderDo?"+QxVersion;
    /**
     * 石头支付界面
     */
    public static String URL_PAY_CURRENT_STONE_ORDER = baseUrl+ "PaymentCurrentOrderStonePage?"+QxVersion;
    /**
     * 石头待付款
     */
    public static String URL_STONE_ORDER_WAIT_PAY = baseUrl+ "stoneWaitPayOrderList?"+QxVersion;
    /**
     * 石头已付款
     */
    public static String URL_STONE_ORDER_PAYED = baseUrl+ "stoneAlreadyPayOrderList?"+QxVersion;
    /**
     * 石头已发货
     */
    public static String URL_STONE_ORDER_SENDING = baseUrl+ "stoneAlreadyDeliverGoodsOrderList?"+QxVersion;
    /**
     * 石头已发货
     *
     */
    public static String URL_STONE_ORDER_FINISH = baseUrl+ "stoneAlreadyFinishOrderList?"+QxVersion;
    /**
     * 石头订单支付宝回调接口
     *
     */
    public static String URL_GET_STONE_AILPAY= "http://appapi1.fanerweb.com/api/Payment/GetAilpayStoneOrderPayStr?"+QxVersion;
    /**
     * 石头订单微信回调接口
     *
     */
    public static String URL_GET_STONE_WEIXIN= "http://appapi1.fanerweb.com/api/Payment/GetWxpayStoneParameter?"+QxVersion;
    /**
     * 取消石头代付款订单
     */
    public static String URL_STONE_CANCLE_ORDER= baseUrl+ "stoneCancelOrderDo?"+QxVersion;

    //简版的
    /**添加到当前订单*/
    public static String URL_CURRENT_ORDER1 = baseUrl + "OrderCurrentDoModelItemForDefaultDo?"+QxVersion;
    //OrderDoCurrentModelItemDo?productId=1&categoryId=8&number=2&handSize=3&stone=1|3|2|3|4|5&stoneA=1|2|2|3|4|5&stoneB=1|2|3|3|4|5&stoneC=1|2|3|3|4|6&tokenKey=10b588002228fa805231a59bb7976bf4

    /**修改添加添加到当前订单*/
    public static String URL_CURRENT_EDIT_ORDER1 = baseUrl + "OrderCurrentEditModelItemForDefaultDo?"+QxVersion;


    /**订单页跳转修改页修改*/
    public static String URL_UPDATE_ORDER_WATET1 = baseUrl + "ModelOrderWaitCheckOrderCurrentEditModelItemForDefaultDo?"+QxVersion;
    /**
     * 获取主页图片
     */
    public static String URL_GET_HOME_PIC = baseUrl + "IndexPageForQxzx?"+QxVersion;
    /**
     * 是否显示成本价
     * http://appapi0.fanerweb.com/api/Aproxy/modifyUserIsShowOriginalPriceDo?tokenKey=be5fc01f10a42d5d8d5d4537ed808bad&isShow=1
     */
    public static String URL_ISHOW_COST_PRICE=baseUrl + "modifyUserIsShowOriginalPriceDo?"+QxVersion;
    /**
     * 改变加点
     * http://appapi0.fanerweb.com/api/Aproxy/modifyUserModelAddtionDo?tokenKey=be5fc01f10a42d5d8d5d4537ed808bad&value=3.2
     */
    public static String URL_MODIFY_ADDTION =baseUrl + "modifyUserModelAddtionDo?"+QxVersion;
    /***
     * 改变石头加点
     * http://appapi0.fanerweb.com/api/Aproxy/modifyUserStoneAddtionDo?tokenKey=be5fc01f10a42d5d8d5d4537ed808bad&value=3.2
     */
    public static String URL_MODIFY_STONE_ADDTION =baseUrl + "modifyUserStoneAddtionDo?"+QxVersion;

    /**
     * 获取默认地址
     *http://appapi1.fanerweb.com/api/Aproxy/InitDataForQxzx?tokenKey=be5fc01f10a42d5d8d5d4537ed808bad&QxVersion=1.7
     */
    public static String URL_GET_ADDRESS =baseUrl + "InitDataForQxzx?"+QxVersion;
    /**
     * 快速定制
     * http://appapi1.fanerweb.com/api/Aproxy/OrderCurrentSubmitQuickNowDo?tokenKey=be5fc01f10a42d5d8d5d4537ed808bad&productId=4609&modelPurityId=2&modelQualityId=1&number=2&word=
     *
     */
    public static String URL_QUICK_MAKING=baseUrl + "OrderCurrentSubmitQuickNowDo?"+QxVersion;
    /**
     * 获取是否更新
     * getUpdateVersionForMstar
     */
    public static String URL_GET_UPDATE_VERSION="http://appapi1.fanerweb.com/api/Public/" + "getUpdateVersionForMstar?";
}


