package com.qx.mstarstoretv.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.bean.Type;
import com.qx.mstarstoretv.json.PlaceResult;
import com.qx.mstarstoretv.net.OKHttpRequestUtils;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.viewutils.CountTimerButton;
import com.qx.mstarstoretv.viewutils.CustomSelectButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/7.
 */
public class RegisterActivity extends BaseActivity {


    Button tv_get_auth_code;
    CountTimerButton mCountDownTimerUtils;
    @Bind(R.id.id_ed_name)
    EditText idEdName;
    @Bind(R.id.id_ed_ername)
    EditText idEdErname;
    @Bind(R.id.id_ed_phone)
    EditText idEdPhone;
    @Bind(R.id.id_ed_password)
    EditText idEdPassword;
    @Bind(R.id.id_ed_repassword)
    EditText idEdRepassword;
    @Bind(R.id.id_ed_code)
    EditText idEdCode;
    @Bind(R.id.tv_get_auth_code)
    Button tvGetAuthCode;

    @Bind(R.id.title_text)
    TextView titleText;
    String userName, truName, phone, pwd, rePwd, code, userType;

    @Bind(R.id.root_view)
    ScrollView rootView;
    @Bind(R.id.bt_place)
    CustomSelectButton btPlace;
    private String memberAreaId;
    private PlaceResult placeResult;
    private List<Type> areaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_1);
        ButterKnife.bind(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNetData();
    }

    @Override
    public void loadNetData() {
        baseShowWatLoading();
        String lgUrl = AppURL.URL_GET_PLACE + "tokenKey=" + BaseApplication.getToken();
        L.e("netLogin" + lgUrl);
        VolleyRequestUtils.getInstance().getCookieRequest(this, lgUrl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                baseHideWatLoading();
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    placeResult = new Gson().fromJson(result, PlaceResult.class);
                    if (placeResult.getData() == null) {
                        return;
                    }
                    initView();
                } else if (error.equals("2")) {
                    loginToServer(RegisterActivity.class);
                } else {

                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    ToastManager.showToastWhendebug(message);
                    L.e(message);
                }
            }

            @Override
            public void onFail(String fail) {
                baseHideWatLoading();
            }


        });
    }

    private void initView() {
        titleText.setText("用户注册");
        tv_get_auth_code = (Button) findViewById(R.id.tv_get_auth_code);
        mCountDownTimerUtils = new CountTimerButton(tv_get_auth_code, 60000, 1000);

        tv_get_auth_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimerUtils.start();
                getNetCode();
            }
        });
        areaList = placeResult.getData().getMemberArealist();
        btPlace.setOnSelectData(new CustomSelectButton.OnselectData() {
            @Override
            public List<Type> getData() {
                return areaList;
            }

            @Override
            public void getSelectId(Type type) {
                memberAreaId = type.getId();
            }

            @Override
            public String getTitle() {
                return "选择区域";
            }

            @Override
            public View getRootView() {
                return btPlace;
            }
        });
    }

    public void onBack(View view) {
        finish();
    }

    public void getNetCode() {
        phone = idEdPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            ToastManager.showToastReal("请填写手机号码");
            return;
        }
        baseShowWatLoading();
        // 进行登录请求
        String lgUrl = AppURL.URL_REGISTER_CODE + "phone=" + phone;
        VolleyRequestUtils.getInstance().getCookieRequest(this, lgUrl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("OKHttpRequestUtils" + result);
                baseHideWatLoading();
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                }
                if (error == 1) {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);
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


    public void netRegister(View view) {
        userName = idEdName.getText().toString();
        truName = idEdErname.getText().toString();
        phone = idEdPhone.getText().toString();
        pwd = idEdPassword.getText().toString();
        rePwd = idEdRepassword.getText().toString();
        code = idEdCode.getText().toString();
        if (StringUtils.isEmpty(userName)) {
            showToastReal("用户名未填写");
            return;
        }
        if (StringUtils.isEmpty(truName)) {
            showToastReal("真名未填写");
            return;
        }
        if (StringUtils.isEmpty(phone)) {
            showToastReal("手机号未填写");
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            showToastReal("密码未填写");
            return;
        }
        if (StringUtils.isEmpty(rePwd)) {
            showToastReal("确认密码未填写");
            return;
        }
        if (StringUtils.isEmpty(code)) {
            showToastReal("验证码未填写");
            return;
        }
        if (StringUtils.isEmpty(memberAreaId)) {
            showToastReal("所属区域未选择");
            return;
        }
        String url = AppURL.URL_REGISTER + "userName=" + userName + "&password=" + pwd + "&trueName=" + truName + "&phone=" + phone + "&phoneCode=" + code + "&userType=1" + "&memberAreaId=" + memberAreaId;
        L.e(url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e(result);
                Gson gson = new Gson();
                int error = gson.fromJson(result, JsonObject.class).get("error").getAsInt();
                L.e("error" + error);
                if (error == 0) {
                    L.e("成功");
                    BaseApplication.spUtils.saveString(SpUtils.key_username, userName);
                    BaseApplication.spUtils.saveString(SpUtils.key_password, pwd);
                    openActivity(MainActivity.class, null);
                }
                if (error == 1) {
                    String message = gson.fromJson(result, JsonObject.class).get("message").getAsString();
                    ToastManager.showToastReal(message);
                    L.e(message);
                }
                if (error == 2) {
                    L.e("重新登录");
                }
                if (error == 3) {
                    L.e("未审核");
                } else {
                    L.e("操作失败");
                }
            }

            @Override
            public void onFail(String fail) {

            }

        });


    }

    public void onLogin(View v) {
        openActivity(LoginActivity.class, null);
    }


}
