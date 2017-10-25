package com.qx.mstarstoretv.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.adapter.FinishTableLessAdapter;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.Global;
import com.qx.mstarstoretv.json.FinishTableLessResult;
import com.qx.mstarstoretv.json.RecListBean;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public class FinishTableLessActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.lv_sending_tables)
    ListView lvSendingTables;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.tv_is_show_cost_price)
    TextView tvIsShowCostPrice;
    @Bind(R.id.iv_is_show_cost_price)
    ImageView ivIsShowCostPrice;
    @Bind(R.id.rl_is_show_cost_price)
    RelativeLayout rlIsShowCostPrice;
    private String orderNumber;
    private FinishTableLessAdapter finishTableLessAdapter;
    private Context context;
    private String type;
    private String isMainAccent;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_finish_table_less);
        ButterKnife.bind(this);
        context = this;
        init();
        getDate();
        loadNetData();
    }

    private void init() {
        titleText.setText("结算单");
        idIgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        isMainAccent = Global.isMainAccount;
        if("1".equals(isMainAccent)){
            rlIsShowCostPrice.setVisibility(View.VISIBLE);
        }else {
            rlIsShowCostPrice.setVisibility(View.GONE);
        }
        rlIsShowCostPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goIntoEncryptionSettings();
            }
        });
    }
    private boolean passwordIsRight(String string) {
        String pwd = BaseApplication.spUtils.getString(SpUtils.key_password);
        if (pwd.equals(string)) {
            return true;
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void goIntoEncryptionSettings() {
        final EditText editText = new EditText(this);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMarginStart(64);
        layoutParams.setMarginEnd(64);
        editText.setLayoutParams(layoutParams);
        LinearLayout ll = new LinearLayout(context);
        ll.addView(editText);
        dialog = new AlertDialog.Builder(this)
                .setTitle("用户密码")
                .setView(ll)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (passwordIsRight(editText.getText().toString())) {

                            if(Global.isShowCost==0){
                                Global.isShowCost = 1;
                                ivIsShowCostPrice.setImageResource(R.drawable.icon_switch_on);
                            }else {
                                Global.isShowCost = 0;
                                ivIsShowCostPrice.setImageResource(R.drawable.icon_switch_off);
                            }
                            if(finishTableLessAdapter!=null){
                                lvSendingTables.setAdapter(finishTableLessAdapter);
                            }else {
                                loadNetData();
                            }

                        } else {
                            ToastManager.showToastReal("密码错误！");
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
    private void getDate() {
        orderNumber = getIntent().getStringExtra("orderNumber");
        type = getIntent().getStringExtra("type");
    }

    @Override
    public void loadNetData() {
        String url = "";
        url = AppURL.URL_CODE_FINISH + "tokenKey=" + BaseApplication.getToken() + "&orderNum=" + orderNumber;
        if (StringUtils.isEmpty(url)) {
            return;
        }
        L.e("获取地址" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    FinishTableLessResult finishTableLessResult = new Gson().fromJson(result, FinishTableLessResult.class);
                    if (finishTableLessResult.getData() != null) {
                        List<RecListBean> list = finishTableLessResult.getData().getRecList();
                        if (list != null) {
                            finishTableLessAdapter = new FinishTableLessAdapter(context, list, type);
                            lvSendingTables.setAdapter(finishTableLessAdapter);
                        }
                    }
                } else if (error.equals("2")) {
                    loginToServer(FinishTableLessActivity.class);
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    ToastManager.showToastWhendebug(message);
                    L.e(message);
                }
            }

            @Override
            public void onFail(String fail) {
            }

        });
    }
}
