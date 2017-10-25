package com.qx.mstarstoretv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.Global;
import com.qx.mstarstoretv.json.AddressEntity;
import com.qx.mstarstoretv.json.CustomerEntity;
import com.qx.mstarstoretv.json.IsHaveCustomerResult;
import com.qx.mstarstoretv.json.OrderListResult;
import com.qx.mstarstoretv.net.OKHttpRequestUtils;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.LeftPopupWindow;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class ChooseCustomerAndAddressActivity extends BaseActivity {

    @Bind(R.id.id_tv1)
    TextView idTv1;
    @Bind(R.id.id_tv_name)
    TextView idTvName;
    @Bind(R.id.phone_tv)
    TextView phoneTv;
    @Bind(R.id.id_tv_address)
    TextView idTvAddress;
    @Bind(R.id.id_no_adress)
    TextView idNoAdress;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.id_lay_address)
    RelativeLayout idLayAddress;
    @Bind(R.id.id_et_seach)
    EditText idEtSeach;
    @Bind(R.id.id_view_line)
    View idViewLine;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;
    @Bind(R.id.ig_btn_seach)
    ImageView igBtnSeach;
    @Bind(R.id.id_rl1)
    RelativeLayout idRl1;
    @Bind(R.id.id_ed_remarks)
    EditText idEdRemarks;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.tv_comfirm)
    TextView tvComfirm;
    @Bind(R.id.ll_confirm_stone)
    RelativeLayout llConfirmStone;
    @Bind(R.id.ll_show_less)
    LinearLayout llShowLess;
    @Bind(R.id.rl_choose_cus_and_add)
    RelativeLayout rlChooseCusAndAdd;
    private AddressEntity isDefaultAddress;
    private CustomerEntity isDefaultCustomer;
    private LeftPopupWindow leftPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choose_cus_and_add);
        ButterKnife.bind(this);
        loadNetData();
    }

    public void onBack(View view) {
        finish();
    }

    @Override
    public void loadNetData() {
        String url;

        url = AppURL.URL_ORDER_LIST + "tokenKey=" + BaseApplication.getToken() + "&purityId=" +
                "&qualityId=" + "&cpage=" + 1;
        L.e("获取订单信息" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    OrderListResult orderListResult = new Gson().fromJson(result, OrderListResult.class);
                    OrderListResult.DataEntity dataEntity = orderListResult.getData();
                    L.e("result", result);
                    if (dataEntity == null) {
                        return;
                    }
                    isDefaultAddress = dataEntity.getAddress();

                    if (isDefaultAddress != null) {
                        idTvName.setText(isDefaultAddress.getName());
                        idTvAddress.setText(isDefaultAddress.getAddr());
                        phoneTv.setText(isDefaultAddress.getPhone());
                        if(Global.ring!=null){
                            Global.ring.setAddressEntity(isDefaultAddress);
                        }

                    }
                    isDefaultCustomer = dataEntity.getCustomer();
                    if (isDefaultCustomer != null) {
                        idEtSeach.setText(isDefaultCustomer.getCustomerName());
                        if(Global.ring!=null){
                            Global.ring.setCustomerEntity(isDefaultCustomer);
                        }
                    }
                    initview();
                }

            }

            @Override
            public void onFail(String fail) {

            }

        });
    }

    private void initPopwindow() {
        leftPopupWindow = new LeftPopupWindow(this);
        llShowLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftPopupWindow.showPop(rlChooseCusAndAdd);
            }
        });
    }

    private void initview() {
        if (Global.isShowPopup != 0) {
            llShowLess.setVisibility(View.VISIBLE);
            initPopwindow();
        } else {
            llShowLess.setVisibility(View.GONE);
        }
        igBtnSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isFast = UIUtils.isFastDoubleClick();
                if (!isFast) {
                    String st = idEtSeach.getText().toString();
                    if (st.isEmpty()) {
                        Intent intent = new Intent(ChooseCustomerAndAddressActivity.this, CustomersListActivity.class);
                        intent.putExtra("keyWord", st);
                        startActivityForResult(intent, 11);
                    } else {
                        seachCustom(idEtSeach.getText().toString());
                    }
                }
            }
        });

            /*选择地址*/
        idLayAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCustomerAndAddressActivity.this, AddressListActivity.class);
                startActivityForResult(intent, 12);
            }
        });

        tvComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Global.ring.getAddressEntity() == null) {
                    ToastManager.showToastReal("请选择地址！");
                    return;
                }
                if(Global.ring.getCustomerEntity()!=null){
                    if (Global.ring.getCustomerEntity().getCustomerName()==null) {
                        ToastManager.showToastReal("请选择客户！");
                        return;
                    }
                }

                leftPopupWindow.showPop(rlChooseCusAndAdd);
                if (Global.ring.getItemId() == null) {
                    showToastReal("请选择戒托");
                }
                if (Global.ring.getStoneEntity() == null) {
                    showToastReal("请选择裸石");
                    return;
                }

                showToastReal("请确认定制");
            }
        });
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idEtSeach.setText("");
            }
        });
    }

    /*
   * @version  搜索时候有客户
   */
    private void seachCustom(final String keyWord) {
        String url = AppURL.URL_HAVE_CUSTOMER + "tokenKey=" + BaseApplication.getToken() + "&keyword=" + keyWord;
        //keyword=广西|平果&tokenKey=944df2f27ffce557042887589986c193
        L.e("seachCustom" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                    IsHaveCustomerResult isHaveCustomerResult = new Gson().fromJson(result, IsHaveCustomerResult.class);
                    JsonObject jsonObject = jsonResult.get("data").getAsJsonObject();
                    int state = jsonObject.get("state").getAsInt();
                    if (state == 0) {
                        showToastReal("没有此客户");
                        isDefaultCustomer.setCustomerID(-1);
                    }
                    if (state == 1) {
                        showToastReal("有此客户");
                        isDefaultCustomer = isHaveCustomerResult.getData().getCustomer();

                        idEtSeach.setText(isDefaultCustomer.getCustomerName());
                    }
                    if (state == 2) {
                        Intent intent = new Intent(ChooseCustomerAndAddressActivity.this, CustomersListActivity.class);
                        intent.putExtra("keyWord", keyWord);
                        startActivityForResult(intent, 11);
                    }
                } else if (error == 2) {
                    loginToServer(OrderActivity.class);
                } else {
                    ToastManager.showToastReal(OKHttpRequestUtils.getmInstance().getErrorMsg(result));
                }
            }

            @Override
            public void onFail(String fail) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        L.e("onActivityResult" + requestCode);
        if (data == null) {
            return;
        }
        if (requestCode == 11) {
            String customerName = data.getStringExtra("CustomerName");
            int customerID = data.getIntExtra("CustomerID", -1);
            idEtSeach.setText(customerName);
            if (isDefaultCustomer == null) {
                isDefaultCustomer = new CustomerEntity();
            }
            isDefaultCustomer.setCustomerID(customerID);
            isDefaultCustomer.setCustomerName(customerName);
            Global.ring.setCustomerEntity(isDefaultCustomer);
        }
        if (requestCode == 12) {
            String phoneNumber = data.getStringExtra("phoneNumber");
            String name = data.getStringExtra("name");
            String address = data.getStringExtra("address");
            String addressId = data.getStringExtra("addressId");

            /*设置默认地址ID*/
            if (isDefaultAddress == null) {
                isDefaultAddress = new AddressEntity();
            }
            isDefaultAddress.setId(addressId);
            idTvName.setText(name);
            idTvAddress.setText(address);
            phoneTv.setText(phoneNumber);
            Global.ring.setAddressEntity(isDefaultAddress);
        }
    }
}
