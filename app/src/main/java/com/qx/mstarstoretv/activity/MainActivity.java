package com.qx.mstarstoretv.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.Global;
import com.qx.mstarstoretv.bean.Ring;
import com.qx.mstarstoretv.json.CustomerEntity;
import com.qx.mstarstoretv.json.GetAddressResult;
import com.qx.mstarstoretv.json.MainPicResult;
import com.qx.mstarstoretv.json.VersionResult;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.BadgeView;
import com.qx.mstarstoretv.viewutils.FlyBanner;
import com.qx.mstarstoretv.viewutils.LeftPopupWindow;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    //新主页
    @Bind(R.id.fly_main)
    FlyBanner flyMain;
    @Bind(R.id.iv_home)
    TextView ivHome;
    @Bind(R.id.iv_stone)
    TextView ivStone;
    @Bind(R.id.iv_product)
    TextView ivProduct;
    @Bind(R.id.iv_mine)
    TextView ivMine;
    @Bind(R.id.iv_ring_stone)
    ImageView ivRingStone;
    @Bind(R.id.iv_stone_ring)
    ImageView ivStoneRing;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.ll_banner)
    LinearLayout llBanner;
    @Bind(R.id.ll_show_less)
    LinearLayout llShowLess;

    private int nowId;
    private String version;
    private VersionResult versionResult;
    private MainPicResult mainPics;
    private List<String> imgesUrl;
    private boolean isShowPic;
    private LeftPopupWindow leftPopupWindow;
    private CustomerEntity isDefaultCustomer;
    private boolean isCustomized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        initView();
//        setChioceFragment(0);
        loadNetData();
        isNeedUpdate();
        getAddress();
    }

    private void getAddress() {
        baseShowWatLoading();
        String lgUrl = AppURL.URL_GET_ADDRESS + "tokenKey=" + BaseApplication.getToken();
        L.e("netLogin" + lgUrl);
        VolleyRequestUtils.getInstance().getCookieRequest(this, lgUrl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    GetAddressResult getAddressResult = new Gson().fromJson(result, GetAddressResult.class);
                    if(getAddressResult.getData()==null){
                        return;
                    }
                    if (Global.ring == null) {
                        Global.ring = new Ring();
                    }
                    Global.ring.setAddressEntity(getAddressResult.getData().getAddress());
                    isDefaultCustomer = getAddressResult.getData().getCustomerEntity();
                    if (isDefaultCustomer != null) {
                        Global.ring.setCustomerEntity(isDefaultCustomer);
                    }
                } else if (error.equals("2")) {
                    baseHideWatLoading();
                } else {
                    baseHideWatLoading();
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

    @Override
    public void loadNetData() {
        baseShowWatLoading();
        String lgUrl = AppURL.URL_GET_HOME_PIC + "tokenKey=" + BaseApplication.getToken();
        L.e("netLogin" + lgUrl);
        VolleyRequestUtils.getInstance().getCookieRequest(this, lgUrl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    baseHideWatLoading();
                    mainPics = new Gson().fromJson(result, MainPicResult.class);
                    if (mainPics.getData() == null) {
                        ToastManager.showToastReal("获取数据失败！");
                        return;
                    }
                    initView();

                } else if (error.equals("2")) {
                    baseHideWatLoading();
                } else {
                    baseHideWatLoading();
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


    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initView();
    }

    private void isNeedUpdate() {
        String lgUrl = AppURL.URL_CODE_VERSION + "device=" + "android";
        L.e("netLogin" + lgUrl);
        VolleyRequestUtils.getInstance().getCookieRequest(this, lgUrl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    versionResult = new Gson().fromJson(result, VersionResult.class);
                    if (versionResult.getData() == null) {
                        ToastManager.showToastReal("获取数据失败！");
                        return;
                    }
                    version = versionResult.getData().getVersion();
                    Double versionDouble = Double.parseDouble(version);
                    Double currentDouble = Double.parseDouble(getString(R.string.app_version));
                    if (versionDouble > currentDouble) {
                        showNoticeDialog();
                    }
                } else if (error.equals("2")) {

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

    /**
     * 显示软件更新对话框
     */
    private void showNoticeDialog() {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.soft_update_title);
        builder.setMessage(R.string.soft_update_info);
        // 更新
        builder.setPositiveButton(R.string.soft_update_updatebtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(versionResult.getData().getUrl()));
                startActivity(intent);
            }
        });

        Dialog noticeDialog = builder.create();
        noticeDialog.setCanceledOnTouchOutside(false);
        noticeDialog.show();
    }

    public int getVerCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(
                    "com.qx.mstarstoreapp", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("tage", e.getMessage());
        }
        return verCode;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isCustomized = SpUtils.getInstace(this).getBoolean("isCustomized", true);
        if (Global.isShowPopup != 0) {
            if (leftPopupWindow != null) {
                leftPopupWindow.initPopupView();
            }
        }
    }


    private void initView() {

        if (UIUtils.isScreenChange(this)) {
            imgesUrl = mainPics.getData().getHorizontal();
        } else {
            imgesUrl = mainPics.getData().getVertical();
        }

        flyMain.setImagesUrl(imgesUrl);
        ivHome.setOnClickListener(this);
        ivMine.setOnClickListener(this);
        ivProduct.setOnClickListener(this);
        ivStone.setOnClickListener(this);
        ivRingStone.setOnClickListener(this);
        ivStoneRing.setOnClickListener(this);
        llShowLess.setOnClickListener(this);
    }

    public static BadgeView badge1;

    public static void setOnInformationCountClick(OnInformationCountClick onInformationCountClick) {
        int count = onInformationCountClick.getCount();
        if (count == 0) {
            return;
        } else {
//            remind(count, badge1, true);
        }

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home:
                if(!isCustomized){
                    ToastManager.showToastReal("高级定制无快速定制，请在个人中心修改");
                }else {
                    if (Global.isShowPopup != 0) {
                        Global.isShowPopup = 0;
                        if (leftPopupWindow != null) {
                            leftPopupWindow.closePopupWindow();
                            llShowLess.setVisibility(View.GONE);
                        }
                    } else {
                        Global.isShowPopup = 2;
                        showMakingRing();
                    }
                }

                break;
            case R.id.iv_stone:
                cancleMaking();
                openActivity(StoneSearchInfoActivity.class, null);
                break;
            case R.id.iv_product:
                cancleMaking();
                openActivity(OrderActivity.class, null);
                break;
            case R.id.iv_mine:
                openActivity(SettingActivity.class, null);
                break;
            case R.id.iv_stone_ring:
                openActivity(StoneSearchInfoActivity.class, null);
                Global.isShowPopup = 2;
                break;
            case R.id.iv_ring_stone:
                openActivity(OrderActivity.class, null);
                Global.isShowPopup = 2;
                break;
            case R.id.ll_show_less:
                initPopwindow();
                break;
        }
    }

    private void cancleMaking() {
        Global.isShowPopup = 0;
        llShowLess.setVisibility(View.GONE);
    }

    private void initPopwindow() {
        if (leftPopupWindow == null) {
            leftPopupWindow = new LeftPopupWindow(this);
        }
        llShowLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftPopupWindow.showPop(flyMain);
            }
        });
    }

    private void showMakingRing() {
//        if (!isShowPic) {
//            llBanner.setAlpha(80);
//            llBanner.setVisibility(View.VISIBLE);
//            ivStoneRing.setVisibility(View.VISIBLE);
//            ivRingStone.setVisibility(View.VISIBLE);
//            isShowPic = true;
//        } else {
//
//            llBanner.setVisibility(View.GONE);
//            ivStoneRing.setVisibility(View.GONE);
//            ivRingStone.setVisibility(View.GONE);
//            isShowPic = false;
//        }

        llShowLess.setVisibility(View.VISIBLE);
        if (leftPopupWindow == null) {
            leftPopupWindow = new LeftPopupWindow(this);
        }
        leftPopupWindow.showPop(flyMain);
    }

    public interface OnInformationCountClick {
        int getCount();
    }


    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastManager.showToastReal("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                moveTaskToBack(true);
            }
        }
        return true;
    }
}
