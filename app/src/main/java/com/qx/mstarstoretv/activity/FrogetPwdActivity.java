package com.qx.mstarstoretv.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.net.OKHttpRequestUtils;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.viewutils.CountTimerButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 忘记密码
 */
public class FrogetPwdActivity extends BaseActivity {



    @Bind(R.id.id_ed_phone)
    EditText idEPhone;

    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.id_ed_code)
    EditText idEdCode;
    @Bind(R.id.id_ed_pwd)
    EditText idEdPwd;

    @Bind(R.id.id_ed_confirpwd)
    EditText idEdConfirPwd;

    String pwd,code,phone;
    Button  tv_get_auth_code;
    CountTimerButton mCountDownTimerUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forger_password);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void loadNetData() {

    }

    public void onBack(View view) {
        finish();
    }

    protected void initView() {
        titleText.setText("忘记密码");
        tv_get_auth_code = (Button) findViewById(R.id.id_get_code);
        mCountDownTimerUtils = new CountTimerButton(tv_get_auth_code, 60000, 1000);

        tv_get_auth_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = idEPhone.getText().toString();
                if(StringUtils.isEmpty(phone)){
                    ToastManager.showToastReal("请填写手机号码");
                    return;
                }
                mCountDownTimerUtils.start();
                getNetCode();
            }
        });
    }

    public void getNetCode(){
        baseShowWatLoading();

        // 进行登录请求
        String lgUrl = AppURL.URL_CODE_FORGETPWD + "phone=" +phone;
        VolleyRequestUtils.getInstance().getCookieRequest(this, lgUrl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("OKHttpRequestUtils" + result);
                baseHideWatLoading();
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                  //  openActivity(LoginActivity.class,null);
                }
                if (error == 1) {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    ToastManager.showToastReal(message);
                    L.e(message);
                    mCountDownTimerUtils.onFinish();
                }
                if (error == 2) {
                    L.e("重新登录");
                }
                if (error == 3) {
                    L.e("未审核");
                }
            }
            @Override
            public void onFail(String fail) {

            }
        });
    }



    public void onUpdatePwd(View view){
        netUpdatePwd();
    }

    public void netUpdatePwd() {
        pwd = idEdPwd.getText().toString();
        code = idEdCode.getText().toString();
        phone = idEPhone.getText().toString();
        if (StringUtils.isEmpty(pwd)) {
            return;
        }
        if (StringUtils.isEmpty(code)) {
            return;
        }
        if (StringUtils.isEmpty(phone)) {
            return;
        }
        baseShowWatLoading();
        //userModifyPasswordDo?tokenKey=841bf597782dced089a6e5167bcf29de&password=123456&phoneCode=32656
        // 进行登录请求
        String url = AppURL.URL_USER_FORGETPWD +"password=" + pwd + "&phoneCode=" + code+"&phone=" +phone;
        L.e("netLogin" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                baseHideWatLoading();
                L.e(result);
                Gson gson = new Gson();
                String error = gson.fromJson(result, JsonObject.class).get("error").getAsString();
                if (error.equals("0")) {
                   // finish();
                    openActivity(LoginActivity.class,null);
                }if (error.equals("2")) {
                    loginToServer(FrogetPwdActivity.class);
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);
                }
            }

            @Override
            public void onFail(String fail) {

            }

        });

    }

}
