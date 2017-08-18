package com.qx.mstarstoretv.pay;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.qx.mstarstoretv.base.Global;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 微信支付显示结果的页面
 * @author asus1
 *
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	private IWXAPI api;

	public WXPayEntryActivity() {
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.setContentView(2130903107);
		this.api = WXAPIFactory.createWXAPI(this, Global.APP_ID);
		this.api.handleIntent(this.getIntent(), this);
	}

	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		this.setIntent(intent);
		this.api.handleIntent(intent, this);
	}

	public void onReq(BaseReq req) {
	}

	public void onResp(BaseResp resp) {
		if(resp.getType() == 5) {
			Toast.makeText(this, "支付结果：" + resp.errCode + "-->" + resp.errStr, 1).show();
			this.finish();
		}

	}
}