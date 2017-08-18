package com.qx.mstarstoretv.pay;

import android.app.Activity;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayUtil {
	private IWXAPI api;
	private Activity activity;
	public WXPayUtil(Activity activity, String app_id) {
		this.activity = activity;
		this.api = WXAPIFactory.createWXAPI(activity, app_id);
	}
	/**
	 * 调用微信的支付SDK完成支付
	 * @param result  关于该参数里的属性一个不能少
	 */
	public void pay(WXPayInfo result){
		boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
		if(isPaySupported){
			api.registerApp(WXPayInfo.APP_ID);
			System.out.println(result);
			sendPayReq(result);
			Toast.makeText(activity, "微信已启动",Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(activity, "您的微信版本暂不支持支付！", Toast.LENGTH_SHORT).show();
		}
	}
	private void sendPayReq(WXPayInfo result) {
		
		PayReq req = new PayReq();
		req.appId = WXPayInfo.APP_ID;
		req.partnerId = result.PARTNER_ID;
		req.prepayId = result.prepayId;
		req.nonceStr = result.nonceStr;
		req.timeStamp = String.valueOf(result.timeStamp);
		req.packageValue = "Sign=WXPay";
		req.sign = result.sign;
		
		// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
		boolean flag = api.sendReq(req);
		System.out.println("flag="+flag);
	}
}
