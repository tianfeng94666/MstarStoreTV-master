package com.qx.mstarstoretv.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.Global;
import com.qx.mstarstoretv.json.SettingResult;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.utils.ToastManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class EncryptionSettingsActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.tv_is_customized)
    TextView tvIsCustomized;
    @Bind(R.id.bt_customized)
    ImageView btCustomized;
    @Bind(R.id.tv_is_show_price)
    TextView tvIsShowPrice;
    @Bind(R.id.iv_is_show_price)
    ImageView ivIsShowPrice;
    @Bind(R.id.tv_is_show_cost_price)
    TextView tvIsShowCostPrice;
    @Bind(R.id.iv_is_show_cost_price)
    ImageView ivIsShowCostPrice;
    @Bind(R.id.rl_is_show_cost_price)
    RelativeLayout rlIsShowCostPrice;
    @Bind(R.id.tv_is_product_point)
    TextView tvIsProductPoint;
    @Bind(R.id.iv_product_reduce)
    ImageView ivProductReduce;
    @Bind(R.id.et_product_spot)
    EditText etProductSpot;
    @Bind(R.id.iv_product_add)
    ImageView ivProductAdd;
    @Bind(R.id.rl_product_addtion)
    RelativeLayout rlProductAddtion;
    @Bind(R.id.tv_is_stone_point)
    TextView tvIsStonePoint;
    @Bind(R.id.iv_stone_reduce)
    ImageView ivStoneReduce;
    @Bind(R.id.et_stones_spot)
    EditText etStonesSpot;
    @Bind(R.id.iv_stone_add)
    ImageView ivStoneAdd;
    @Bind(R.id.rl_stone_addtion)
    RelativeLayout rlStoneAddtion;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.tv_is_show_stone_price)
    TextView tvIsShowStonePrice;
    @Bind(R.id.iv_is_show_stone_price)
    ImageView ivIsShowStonePrice;
    @Bind(R.id.tv_is_stone_point1)
    TextView tvIsStonePoint1;
    @Bind(R.id.iv_stone_reduce1)
    ImageView ivStoneReduce1;
    @Bind(R.id.et_stones_spot1)
    EditText etStonesSpot1;
    @Bind(R.id.iv_stone_add1)
    ImageView ivStoneAdd1;
    @Bind(R.id.rl_stone_addtion1)
    RelativeLayout rlStoneAddtion1;
    @Bind(R.id.tv_is_stone_point2)
    TextView tvIsStonePoint2;
    @Bind(R.id.iv_stone_reduce2)
    ImageView ivStoneReduce2;
    @Bind(R.id.et_stones_spot2)
    EditText etStonesSpot2;
    @Bind(R.id.iv_stone_add2)
    ImageView ivStoneAdd2;
    @Bind(R.id.rl_stone_addtion2)
    RelativeLayout rlStoneAddtion2;
    private boolean isShowStonePrice = SpUtils.getInstace(this).getBoolean("isShowStonePrice", true);
    private boolean isShowPrice = SpUtils.getInstace(this).getBoolean("isShowPrice", true);
    private boolean isCustomized = SpUtils.getInstace(this).getBoolean("isCustomized", true);
    private int isShowCostPrice;
    private int COST_PRICE_TYPE = 1;
    private SettingResult settingResult;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enctyption_settings);
        ButterKnife.bind(this);
        getDate();
        initView();
    }

    private void getDate() {
        settingResult = (SettingResult) getIntent().getSerializableExtra("settingResult");
    }


    private void initView() {
        if (isCustomized) {
            btCustomized.setImageResource(R.drawable.icon_switch_off);
        } else {
            btCustomized.setImageResource(R.drawable.icon_switch_on);
        }
        btCustomized.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCustomized = !isCustomized;
                if (isCustomized) {
                    btCustomized.setImageResource(R.drawable.icon_switch_off);
                } else {
                    btCustomized.setImageResource(R.drawable.icon_switch_on);
                }
                Global.STONE_POINT_CHANGE = 1;
                SpUtils.getInstace(EncryptionSettingsActivity.this).saveBoolean("isCustomized", isCustomized);
            }
        });

        if (!isShowPrice) {
            ivIsShowPrice.setImageResource(R.drawable.icon_switch_off);
        } else {
            ivIsShowPrice.setImageResource(R.drawable.icon_switch_on);
        }
        ivIsShowPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShowPrice = !isShowPrice;
                if (!isShowPrice) {
                    ivIsShowPrice.setImageResource(R.drawable.icon_switch_off);
                } else {
                    ivIsShowPrice.setImageResource(R.drawable.icon_switch_on);
                }
                Global.STONE_POINT_CHANGE = 1;
                SpUtils.getInstace(EncryptionSettingsActivity.this).saveBoolean("isShowPrice", isShowPrice);
            }
        });

        if (!isShowStonePrice) {
            ivIsShowStonePrice.setImageResource(R.drawable.icon_switch_off);
        } else {
            ivIsShowStonePrice.setImageResource(R.drawable.icon_switch_on);
        }
        ivIsShowStonePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShowStonePrice = !isShowStonePrice;
                if (!isShowStonePrice) {
                    ivIsShowStonePrice.setImageResource(R.drawable.icon_switch_off);
                } else {
                    ivIsShowStonePrice.setImageResource(R.drawable.icon_switch_on);
                }
                Global.STONE_POINT_CHANGE = 1;
                SpUtils.getInstace(EncryptionSettingsActivity.this).saveBoolean("isShowStonePrice", isShowStonePrice);
            }
        });

        isShowCostPrice = settingResult.getData().getIsShowOriginalPrice();
        if (isShowCostPrice == 1) {
            ivIsShowCostPrice.setImageResource(R.drawable.icon_switch_on);
        } else {
            ivIsShowCostPrice.setImageResource(R.drawable.icon_switch_off);
        }
        ivIsShowCostPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowCostPrice == 1) {
                    isShowCostPrice = 0;
                    ivIsShowCostPrice.setImageResource(R.drawable.icon_switch_off);
                } else {
                    isShowCostPrice = 1;
                    ivIsShowCostPrice.setImageResource(R.drawable.icon_switch_on);
                }
                commitIsShow(isShowCostPrice);
            }
        });
        if (settingResult.getData().getIsMasterAccount() == 1) {
            rlIsShowCostPrice.setVisibility(View.VISIBLE);
            rlProductAddtion.setVisibility(View.VISIBLE);
            rlStoneAddtion.setVisibility(View.VISIBLE);
            rlStoneAddtion1.setVisibility(View.VISIBLE);
            rlStoneAddtion2.setVisibility(View.VISIBLE);
        } else {
            rlIsShowCostPrice.setVisibility(View.GONE);
            rlProductAddtion.setVisibility(View.GONE);
            rlStoneAddtion.setVisibility(View.GONE);
            rlStoneAddtion1.setVisibility(View.GONE);
            rlStoneAddtion2.setVisibility(View.GONE);
        }

        etProductSpot.setText(settingResult.getData().getModelAddtion());
        etStonesSpot.setText(settingResult.getData().getStoneAddtion());
        etStonesSpot1.setText(settingResult.getData().getStoneAddtion1());
        etStonesSpot2.setText(settingResult.getData().getStoneAddtion2());
        initListener();
    }


    private void commitIsShow(int i) {
        Global.STONE_POINT_CHANGE = 1;
        String url;
        url = AppURL.URL_ISHOW_COST_PRICE + "tokenKey=" + BaseApplication.getToken() + "&isNoShow=" + i;

        L.e("获取个人信息" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {

                L.e("loadNetData  " + result);
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    ToastManager.showToastReal("修改成功");
                } else if (error.equals("2")) {
                    ToastManager.showToastReal("修改失败");
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);
                }
            }

            @Override
            public void onFail(String fail) {
                ToastManager.showToastReal("数据获取失败");
            }
        });

    }


    private void isCommitaddtion() {
        if (!etProductSpot.getText().toString().equals(settingResult.getData().getModelAddtion())) {
            String value = etProductSpot.getText().toString();
            commitAddtion(value, 0);
            Global.STONE_POINT_CHANGE = 1;
        }
        boolean spot2IsChange = !etStonesSpot1.getText().toString().equals(settingResult.getData().getStoneAddtion1());
        boolean spot3IsChange = !etStonesSpot2.getText().toString().equals(settingResult.getData().getStoneAddtion2());
        if (!etStonesSpot.getText().toString().equals(settingResult.getData().getStoneAddtion()) || spot2IsChange || spot3IsChange) {
            String value = etStonesSpot.getText().toString();
            commitAddtion(value, 1);
            Global.STONE_POINT_CHANGE = 1;
        }
    }

    public void onBack(View view) {
        isCommitaddtion();
        finish();
    }

    private void initListener() {
        ivProductAdd.setOnClickListener(this);
        ivProductReduce.setOnClickListener(this);
        ivStoneAdd.setOnClickListener(this);
        ivStoneReduce.setOnClickListener(this);
        ivStoneAdd1.setOnClickListener(this);
        ivStoneReduce1.setOnClickListener(this);
        ivStoneAdd2.setOnClickListener(this);
        ivStoneReduce2.setOnClickListener(this);
    }

    private void commitAddtion(String value, int i) {
        String url;
        if (i == 0) {
            url = AppURL.URL_MODIFY_ADDTION + "tokenKey=" + BaseApplication.getToken() + "&value=" + value;
        } else {
            url = AppURL.URL_MODIFY_STONE_ADDTION + "tokenKey=" + BaseApplication.getToken() + "&value=" + value+
                    "&value1="+etStonesSpot1.getText().toString()+"&value2="+etStonesSpot2.getText().toString();
        }

        L.e("获取个人信息" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {

                L.e("loadNetData  " + result);
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    ToastManager.showToastReal("修改成功");
                } else if (error.equals("2")) {
                    ToastManager.showToastReal("修改失败");
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);
                }
            }

            @Override
            public void onFail(String fail) {
                ToastManager.showToastReal("数据获取失败");
            }
        });
    }

    @Override
    public void onClick(View v) {
        int point = Integer.parseInt(etProductSpot.getText().toString());
        int stonePoint = Integer.parseInt(etStonesSpot.getText().toString());
        int stonePoint1 = Integer.parseInt(etStonesSpot1.getText().toString());
        int stonePoint2 = Integer.parseInt(etStonesSpot2.getText().toString());
        switch (v.getId()) {
            case R.id.iv_product_add:
                ++point;
                etProductSpot.setText(point + "");
                break;
            case R.id.iv_product_reduce:
                if (point > 1) {
                    --point;
                }
                etProductSpot.setText(point + "");
                break;
            case R.id.iv_stone_add:
                ++stonePoint;
                etStonesSpot.setText(stonePoint + "");
                break;
            case R.id.iv_stone_reduce:
                if (stonePoint > 1) {
                    --stonePoint;
                }
                etStonesSpot.setText(stonePoint + "");
                break;
            case R.id.iv_stone_add1:
                ++stonePoint1;
                etStonesSpot1.setText(stonePoint1 + "");
                break;
            case R.id.iv_stone_reduce1:
                if (stonePoint1 > 1) {
                    --stonePoint1;
                }
                etStonesSpot1.setText(stonePoint1 + "");
                break;
            case R.id.iv_stone_add2:
                ++stonePoint2;
                etStonesSpot2.setText(stonePoint2 + "");
                break;
            case R.id.iv_stone_reduce2:
                if (stonePoint2 > 1) {
                    --stonePoint2;
                }
                etStonesSpot2.setText(stonePoint2 + "");
                break;
        }
    }
}
