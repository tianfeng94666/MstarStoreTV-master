package com.qx.mstarstoretv.wxapi;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.qx.mstarstoretv.activity.PaySuccessActivity;
import com.qx.mstarstoretv.base.Global;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("支付结果页面");
        setContentView(tv);

    	api = WXAPIFactory.createWXAPI(this, Global.APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
 			Toast.makeText(this, "支付结果："+resp.errCode+"-->"+resp.errStr, Toast.LENGTH_SHORT).show();
			Intent intent;
			intent = new Intent(this, PaySuccessActivity.class);
			if (!Global.id.equals("")) {
				intent.putExtra("id", Global.id);
				intent.putExtra("type", Global.type + "");
			}
			startActivity(intent);
			finish();
		}
	}
}