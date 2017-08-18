package com.qx.mstarstoretv.pay;
/**
 * 用于构造微信支付的实体类数据
 * @author gwm
 *
 */
public class WXPayInfo {
	public String sign;
	public static String APP_ID;
	public String PARTNER_ID;
	public String prepayId;
	public String nonceStr;
	public String timeStamp;
	@Override
	public String toString() {
		return "WXPayInfo [APP_ID=" +APP_ID+ ",sign=" + sign + ", PARTNER_ID=" + PARTNER_ID
				+ ", prepayId=" + prepayId + ", nonceStr=" + nonceStr
				+ ", timeStamp=" + timeStamp + "]";
	}

}
