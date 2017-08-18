package com.qx.mstarstoretv.base;

import com.qx.mstarstoretv.bean.Ring;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class Global {
    public static int STONE_POINT_CHANGE = 0;
    public  static int divideAmount = 2;
    public static String APP_ID = "wx303dc6296f3aed55";
    //微信支付的类型，方式
    public  static String id;
    public static  String type;
    public static int selectPosition;
    //是否显示导向框
    public static  int isShowPopup ;//状态0不显示，状态1显示点击打开按钮，状态2显示popupwindow
    /**
     * 戒托数据
     */
    public static Ring ring;
}
