package com.qx.mstarstoretv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.Scrollable.CanScrollVerticallyDelegate;
import com.qx.mstarstoretv.Scrollable.OnFlingOverListener;
import com.qx.mstarstoretv.Scrollable.OnScrollChangedListener;
import com.qx.mstarstoretv.Scrollable.ScrollableLayout;
import com.qx.mstarstoretv.Scrollable.TabsLayout;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.inter.AdapterStoneCallBack;
import com.qx.mstarstoretv.inter.ConfirmOrderOnUpdate;
import com.qx.mstarstoretv.json.AddressEntity;
import com.qx.mstarstoretv.json.ConfirmOrderResult;
import com.qx.mstarstoretv.json.CustomerEntity;
import com.qx.mstarstoretv.json.IsHaveCustomerResult;
import com.qx.mstarstoretv.json.OrderListResult;
import com.qx.mstarstoretv.json.PriceResult;
import com.qx.mstarstoretv.json.StoneBean;
import com.qx.mstarstoretv.json.StoneOrderDetailResult;
import com.qx.mstarstoretv.json.StoneOrderResult;
import com.qx.mstarstoretv.net.OKHttpRequestUtils;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.CustomGridView;
import com.qx.mstarstoretv.viewutils.GridViewWithHeaderAndFooter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class ConfirmStoneOrderActivity extends BaseActivity implements CanScrollVerticallyDelegate,
        OnFlingOverListener, AdapterStoneCallBack, ConfirmOrderOnUpdate {

    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.id_lay_address)
    RelativeLayout layAddress;
    @Bind(R.id.tv_totalPrice)
    TextView tvTotalPrice;
    @Bind(R.id.bt_go_pay)
    Button btGoPay;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.id_tv1)
    TextView idTv1;
    @Bind(R.id.id_tv_name)
    TextView idTvName;
    @Bind(R.id.id_tv_address)
    TextView idTvAddress;
    @Bind(R.id.id_receipt)
    TextView idReceipt;


    @Bind(R.id.rel_shopping_car_bottom_action)
    RelativeLayout relShoppingCarBottomAction;
    @Bind(R.id.id_lay_order_detail)
    LinearLayout idLayOrderDetail;
    @Bind(R.id.id_et_seach)
    EditText idEtSeach;
    @Bind(R.id.ig_btn_seach)
    ImageView igBtnSeach;
    @Bind(R.id.id_rl1)
    RelativeLayout idRl1;
    @Bind(R.id.phone_tv)
    TextView phoneTv;


    @Bind(R.id.id_ed_remarks)
    EditText idEdRemarks;
    @Bind(R.id.id_lay_price1)
    LinearLayout idLayPrice1;
    @Bind(R.id.id_tv_need_price)
    TextView idTvNeedPrice;
    @Bind(R.id.id_tv_total_price)
    TextView idTvTotalPrice;
    @Bind(R.id.id_lay_price2)
    LinearLayout idLayPrice2;
    @Bind(R.id.id_cancle_order)
    Button idCancleOrder;
    @Bind(R.id.root_view)
    RelativeLayout rootView;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;
    @Bind(R.id.tv_pay)
    Button tvPay;

    protected GridViewWithHeaderAndFooter lv_list;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_no_adress)
    TextView idNoAdress;
    @Bind(R.id.id_view_line)
    View idViewLine;
    @Bind(R.id.ll_stone_confirm_order)
    LinearLayout llStoneConfirmOrder;
    @Bind(R.id.tv_post_name)
    TextView tvPostName;
    @Bind(R.id.tv_post_phone)
    TextView tvPostPhone;
    @Bind(R.id.tv_post_address)
    TextView tvPostAddress;
    @Bind(R.id.tv_sum_price)
    TextView tvSumPrice;
    @Bind(R.id.tv_amount)
    TextView tvAmount;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_remark)
    TextView tvRemark;
    @Bind(R.id.ll_stone_order_detail)
    LinearLayout llStoneOrderDetail;
    @Bind(R.id.header)
    LinearLayout header;
    @Bind(R.id.id_rl)
    LinearLayout idRl;
    @Bind(R.id.scrollable_layout)
    ScrollableLayout scrollableLayout;

    @Bind(R.id.tv_order_state)
    TextView tvOrderState;
    @Bind(R.id.tv_customer_name)
    TextView tvCustomerName;
    @Bind(R.id.tv_order_number)
    TextView tvOrderNumber;
    @Bind(R.id.ll_order_detail_state)
    LinearLayout llOrderDetailState;


    private int tempCurpage = 1;
    private int pullState = 1;
    private int curpage = 1;
    private ConfirOrderAdapter confirOrderAdapter;
    private int type = 0;   //type=2表示审核订单中过来的页面
    private String orderId;
    private boolean isFirst; //记录 是否第一次进入页面
    private int list_count = -1;
    private static final int PULL_REFRESH = 1;
    private static final int PULL_LOAD = 2;
    int waitOrderCount;
    private ConfirmOrderResult confirmOrderResult;
    /*选择成色的ItME*/
    List<OrderListResult.DataEntity.ModelColorEntity> modelColorItme;
    /*选择质量等级的Item*/
    List<OrderListResult.DataEntity.ModelQualityEntity> modelQualityItem;
    List<StoneBean> listData;
    AddressEntity isDefaultAddress;
    CustomerEntity isDefaultCustomer;
    private String percent;
    DecimalFormat df = new DecimalFormat("######0.00");
    private int isFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_confirm_stone_order);
        ButterKnife.bind(this);
        getDate();
        initView();
        initScroll();
        loadNetData();
        isFirst = true;
        new Thread(run2).start();
    }

    private void getDate() {
        Intent intent = getIntent();
        orderId = intent.getStringExtra("itemId");
        System.out.println("itemId=" + orderId);
        percent = intent.getStringExtra("percent");
        type = intent.getIntExtra("type", 0);
        isFinish = intent.getIntExtra("isFinish", 0);

    }

    Runnable run2 = new Runnable() {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                View rootview = ConfirmStoneOrderActivity.this.getWindow().getDecorView();
                View aaa = rootview.findFocus();
                Log.i("tag", aaa.toString());
            }

        }
    };

    @Override
    public void loadNetData() {
        String url;
        if (type == 2) {
            url = AppURL.URL_STONE_ORDER_DETAIL + "tokenKey=" + BaseApplication.getToken() + "&orderId=" + orderId+"&pageNum=24";
        } else {
            url = AppURL.URL_STONE_PLACE_ORDER + "tokenKey=" + BaseApplication.getToken() + "&id=" + orderId + "&percent=" + percent+"&pageNum=24";
        }
        L.e("获取订单信息" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {

                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    List<StoneBean> currentOrderlList = null;
                    if (type == 2) {
                        btGoPay.setVisibility(View.GONE);
                        idLayOrderDetail.setVisibility(View.VISIBLE);
                        idLayPrice1.setVisibility(View.GONE);
                        idLayPrice2.setVisibility(View.GONE);
                        llStoneOrderDetail.setVisibility(View.VISIBLE);
                        llStoneConfirmOrder.setVisibility(View.GONE);
                        layAddress.setVisibility(View.GONE);
                        llOrderDetailState.setVisibility(View.VISIBLE);


                        StoneOrderDetailResult stoneOrderDetailResult = new Gson().fromJson(result, StoneOrderDetailResult.class);
                        StoneOrderDetailResult.DataBean dataBean = stoneOrderDetailResult.getData();
                        currentOrderlList = dataBean.getList();
                        tvOrderState.setText(dataBean.getOrderStatusTitle());
                        tvCustomerName.setText(dataBean.getCustomerName());
                        tvOrderNumber.setText(dataBean.getOrderNum());
                        tvPostName.setText(dataBean.getPostName());
                        tvPostPhone.setText(dataBean.getPostTel());
                        tvPostAddress.setText(dataBean.getPostAddress());
                        tvSumPrice.setText(dataBean.getTotelPrice());
                        tvAmount.setText(dataBean.getOrderNumber() + "");
                        tvDate.setText(dataBean.getOrderDate());
                        tvRemark.setText(dataBean.getRemark());

                        if (dataBean.getIsNeetPay() == 1) {
                            tvPay.setVisibility(View.VISIBLE);
                        } else {
                            tvPay.setVisibility(View.GONE);
                        }
                    } else {
                        btGoPay.setVisibility(View.VISIBLE);
                        idLayOrderDetail.setVisibility(View.GONE);
                        idLayPrice1.setVisibility(View.VISIBLE);
                        idLayPrice2.setVisibility(View.GONE);
                        llStoneOrderDetail.setVisibility(View.GONE);
                        llStoneConfirmOrder.setVisibility(View.VISIBLE);
                        layAddress.setVisibility(View.VISIBLE);
                        llOrderDetailState.setVisibility(View.GONE);
                        idLayPrice1.setVisibility(View.VISIBLE);

                        StoneOrderResult stoneOrderResult = new Gson().fromJson(result, StoneOrderResult.class);
                        if (stoneOrderResult.getData() == null) {
                            showToastReal("数据为空");
                            return;
                        }
                        StoneOrderResult.DataBean dataBean = stoneOrderResult.getData();
                        isDefaultAddress = dataBean.getAddress();
                        isDefaultCustomer = dataBean.getCustomer();
                        if (isDefaultCustomer != null) {
                            idEtSeach.setText(isDefaultCustomer.getCustomerName());
                        }
                        currentOrderlList = dataBean.getList();
                        if (isDefaultAddress != null) {
                            idTvName.setText(isDefaultAddress.getName());
                            idTvAddress.setText(isDefaultAddress.getAddr());
                            phoneTv.setText(isDefaultAddress.getPhone());
                        }

                    }

                    if (pullState != PULL_LOAD) {

                        listData.clear();
                    }
                    list_count = currentOrderlList.size();

                    if (list_count == 0) {
                        showToastReal("没有数据");
                    } else {
                        listData.addAll(currentOrderlList);
                    }

                    if (isFirst) {
                        if (isDefaultCustomer != null) {
                            idEtSeach.setText(isDefaultCustomer.getCustomerName());
                        }

                        isFirst = false;
                    }
                    if (priceList != null && priceList.size() != 0) {
                        for (int i = 0; i < priceList.size(); i++) {
                            for (int j = 0; j < listData.size(); j++) {
                                if (listData.get(j).getId().equals(priceList.get(i).getId())) {
                                    listData.get(j).setPrice(priceList.get(i).getPrice() + "");
                                }
                            }
                        }
                    }
                    L.e("解析成功");
                    tvTotalPrice.setText(confirOrderAdapter.getTotleMoney());
                    endNetRequest();
                    initListener();
                    L.e("mchecked" + mchecked.size());
                    Set<Map.Entry<String, StoneBean>> entries = mchecked.entrySet();
                }
                if (error == 2) {
                    loginToServer(OrderActivity.class);
                }

            }

            @Override
            public void onFail(String fail) {

            }

        });
    }


    public void setListHeadView(OrderListResult.DataEntity.OrderInfoEntity orderInfo) {
        listHeadView = LayoutInflater.from(this).inflate(R.layout.order_header_view, null);
        TextView orderNum = (TextView) listHeadView.findViewById(R.id.id_order_num);
        TextView orderDate = (TextView) listHeadView.findViewById(R.id.id_order_date);
        TextView orderStatus = (TextView) listHeadView.findViewById(R.id.id_order_status);
        TextView goodprice = (TextView) listHeadView.findViewById(R.id.id_order_goodprice);
        orderNum.setText("订单编号：" + orderInfo.getOrderNum());
        orderDate.setText("下单日期：" + orderInfo.getOrderDate());
        orderStatus.setText("状态：" + orderInfo.getOrderStatus());
        goodprice.setText("金价：" + orderInfo.getGoldPrice());
        lv_list.addHeaderView(listHeadView);
    }


    private void initListener() {


        idReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmStoneOrderActivity.this, ReceiptActivity.class);
                if (!StringUtils.isEmpty(invTitle)) {
                    intent.putExtra("invTitle", invTitle);
                }
                intent.putExtra("isStone", "2");//是否是石头
                intent.putExtra("type", type);
                intent.putExtra("invTitle", invType);
                startActivityForResult(intent, 13);
            }
        });






        /*搜索用户时间  失去焦点*/
        idEtSeach.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if (!StringUtils.isEmpty(idEtSeach.getText().toString()))
                        seachCustom(idEtSeach.getText().toString());
                }
            }
        });


        igBtnSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isFast = UIUtils.isFastDoubleClick();
                if (!isFast) {
                    seachCustom(idEtSeach.getText().toString());
                }
            }
        });

        ;
        /*确定支付*/
        btGoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // openActivity(ModeOfPaymentActivity.class, null);
                submitOrder();
            }
        });


        /*选择地址*/
        layAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openActivity(ShopingAddressActivity.class,null);
                // Intent intent = new Intent(ConfirmOrderActivity.this, ShopingAddressActivity.class);
                // startActivityForResult(intent, 12);
                Intent intent = new Intent(ConfirmStoneOrderActivity.this, AddressListActivity.class);
                intent.putExtra("type", 2);
                startActivityForResult(intent, 12);
            }
        });

        /*取消订单*/
        idCancleOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancalOrder();
            }
        });
        tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(ConfirmStoneOrderActivity.this, ModeOfPaymentActivity.class);
                if (!orderId.equals("")) {
                    intent.putExtra("id", orderId);
                    intent.putExtra("type", 2);
                }
                startActivity(intent);
            }
        });

    }

    /*
    * @version  取消订单
    */
    public void cancalOrder() {
        // ModelOrderWaitCheckCancelDo?orderId=10&tokenKey=10b588002228fa805231a59bb7976bf4
        String url = AppURL.URL_ORDER_WAIT_CANCEL + "tokenKey=" + BaseApplication.getToken() + "&orderId=" + orderId;
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    showToastReal("提交成功");
                    setResult(11);
                    finish();
                }
                if (error == 2) {
                    loginToServer(OrderActivity.class);
                }
            }

            @Override
            public void onFail(String fail) {

            }
        });
    }


    /*价格查询  等级和 成色的变化都会影响价格*/
    List<PriceResult.DataEntity.PriceListEntity> priceList;

    public void queryPricefoServer(String qualityId, String purityId) {
        String url;
        if (type == 2) {
            //ModelOrderWaitCheckGetOrderPricePageListDo?purityId=1&qualityId=1&orderId=13&tokenKey=10b588002228fa805231a59bb7976bf4
            url = AppURL.URL_WATI_ORDER_PRICE + "tokenKey=" + BaseApplication.getToken() + "&purityId=" + purityId +
                    "&qualityId=" + qualityId + "&orderId=" + orderId;
        } else {
            url = AppURL.URL_ORDER_PRICE + "tokenKey=" + BaseApplication.getToken() + "&purityId=" + purityId +
                    "&qualityId=" + qualityId + "&cpage=" + curpage;
        }
        L.e("path");
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    PriceResult priceResult = new Gson().fromJson(result, PriceResult.class);
                    priceList = priceResult.getData().getPriceList();
                    if (priceList != null && priceList.size() != 0) {
                        for (int i = 0; i < priceList.size(); i++) {
                            for (int j = 0; j < listData.size(); j++) {
                                if (listData.get(j).getId().equals(priceList.get(i).getId())) {
                                    listData.get(j).setPrice(priceList.get(i).getPrice() + "");
                                }
                            }
                        }
                    }
                    endNetRequest();


                }
                if (error == 2) {
                    loginToServer(StyleInfromationActivity.class);
                }
            }

            @Override
            public void onFail(String fail) {

            }
        });
    }


    public void onBack(View view) {
        L.e("删除后" + waitOrderCount);
        Intent intent = new Intent();
        intent.putExtra("waitOrderCount", waitOrderCount);
        if (type == 2) {
            if (isFinish == 0) {
                intent.setClass(this, StoneHistoryOrder.class);
                startActivity(intent);
            }

        } else {
            setResult(12, intent);
        }

        finish();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            intent.putExtra("waitOrderCount", waitOrderCount);
            setResult(12, intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    View listHeadView;

    protected void initView() {

        listData = new ArrayList<>();
        lv_list = (GridViewWithHeaderAndFooter) findViewById(R.id.gridview);
        confirOrderAdapter = new ConfirOrderAdapter();
        confirOrderAdapter.setOnAdapterCallBack(this);
        lv_list.setEmptyView(findViewById(R.id.lny_no_result));
        lv_list.setAdapter(confirOrderAdapter);


          /*发票*/
        idReceipt.setText(R.string.write_a_receip);


        if (type == 2) {
            titleText.setText(R.string.order_detail);
            idCancleOrder.setText(R.string.cancle_order);
        } else {
            titleText.setText(R.string.confirm_order);
        }


        StyleInfromationActivity.setConfirmOrderOnUpdate(this);

        ShopingAddressActivity.setOnRefreshOrderListener(new ShopingAddressActivity.OnRefreshOrderListener() {
            @Override
            public void onrefresh() {
                loadNetData();
            }
        });
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idEtSeach.setText("");
                isDefaultCustomer = null;
            }
        });
    }

    //OrderCurrentSubmitDo?itemId=1|2|3&addressId=1&purityId=3&qualityId=2&tokenKey=944df2f27ffce557042887589986c193
    String purityId, qualityId;
    List<String> mcheckId;


    String invTitle, invType;

    /*
     * @version  提交订单
     */
    public void submitOrder() {
        String url = AppURL.URL_STONE_COMMIT_ORDER + "&tokenKey=" + BaseApplication.getToken();

        String remarks = idEdRemarks.getText().toString();
        if (isDefaultAddress == null) {
            showToastReal(getString(R.string.please_write_address));
            return;
        }
        if (isDefaultCustomer == null) {
            showToastReal(getString(R.string.please_write_customer));
            return;
        }

        String addressId = isDefaultAddress.getId();
        String customerID = isDefaultCustomer.getCustomerID() + "";

        if (customerID.equals("-1")) {
            showToastReal("请填写客户资料");
            return;
        }
        if (StringUtils.isEmpty(addressId) && StringUtils.isEmpty(customerID)) {
            showToastReal("请填写完整资料");
            return;
        }
        String id = confirOrderAdapter.getAllIdAndNumber();
        if (StringUtils.isEmpty(id)) {
            showToastReal("没有产品！");
            return;
        }
        url += "&id=" + id + "&addressId=" + addressId + "&customerId=" + customerID +
                "&invTitle=" + invTitle + "&invType=" + invType + "&remark=" + remarks + "&percent=" + percent;
        L.e("submitOrder" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("确定订单" + result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    ToastManager.showToastReal("提交成功");
//                    onUpdate();
                    JsonObject jsonObject = new Gson().fromJson(result, JsonObject.class).get("data").getAsJsonObject();
                    confirmOrderResult = new Gson().fromJson(result, ConfirmOrderResult.class);
                    if (confirmOrderResult.getData() == null) {
                        return;
                    }
                    String id = confirmOrderResult.getData().getOrderId();

                    Intent intent = new Intent(ConfirmStoneOrderActivity.this, ConfirmStoneOrderActivity.class);
                    intent.putExtra("itemId", id);
                    intent.putExtra("type", 2);
                    startActivity(intent);
                    finish();

                } else if (error == 2) {
                    loginToServer(OrderActivity.class);
                } else {
                    showToastReal(OKHttpRequestUtils.getmInstance().getErrorMsg(result));
                }
            }

            @Override
            public void onFail(String fail) {

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
                        if (type == 2) {
                            updateCustomorWord(isDefaultCustomer.getCustomerID() + "", keyWord);
                        } else {
                            idEtSeach.setText(isDefaultCustomer.getCustomerName());
                        }

                    }
                    if (state == 2) {
                        Intent intent = new Intent(ConfirmStoneOrderActivity.this, CustomersListActivity.class);
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

    /*
     * @version  修改客户和自印
     */
    public void updateCustomorWord(String cus, String word) {
        // ModelOrderWaitCheckDetailModifyInfoDo?orderId=10&customerId=2990&tokenKey=10b588002228fa805231a59bb7976bf4
        String url = "";
        if (type != 2) {
            return;
        }
        url = AppURL.URL_WATI_ORDER_MODIINFO + "tokenKey=" + BaseApplication.getToken() + "&orderId=" + orderId +
                "&customerId=" + cus;
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                L.e(result);
                if (error == 0) {
                    showToastReal("更新成功");
                }
                if (error == 2) {
                    loginToServer(StyleInfromationActivity.class);
                }
            }

            @Override
            public void onFail(String fail) {

            }
        });
    }


    Map<String, StoneBean> mchecked = new HashMap<>();


    private void endNetRequest() {

        confirOrderAdapter.notifyDataSetChanged();
        tempCurpage = curpage;

        pullState = 0;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            updateCustomorWord(customerID + "", idEtSeach.getText().toString());
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
            isDefaultAddress.setAddr(address);
            isDefaultAddress.setName(name);
            isDefaultAddress.setPhone(phoneNumber);

            idTvName.setText(name);
            idTvAddress.setText(address);
            phoneTv.setText(phoneNumber);
        }

        if (requestCode == 13) {
            invTitle = data.getStringExtra("invTitle");
            invType = data.getStringExtra("invTypeId");
            int type = data.getIntExtra("type", 1);
            String invTypeTitle = data.getStringExtra("invTypeTitle");
            if (!StringUtils.isEmpty(invTitle) && !StringUtils.isEmpty(invTypeTitle)) {
                idReceipt.setText("类型：" + invTypeTitle + "     抬头：" + invTitle);
            } else {
                invTitle = "";
                invType = "";
                idReceipt.setText("暂不开票");
            }
            if (type == 2) {
                updateInv();
            }
        }
    }

    public void updateInv() {
        String url = AppURL.URL_WATI_ORDER_MODIINFO + "tokenKey=" + BaseApplication.getToken() + "&invTitle=" + invTitle +
                "&invType=" + invType +
                "&orderId=" + orderId;
        L.e("updateInv" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                L.e(result);
                if (error == 0) {
                    ToastManager.showToastReal("更新成功");
                }
                if (error == 2) {
                    loginToServer(StyleInfromationActivity.class);
                }
            }

            @Override
            public void onFail(String fail) {

            }
        });
    }

    private static final String ARG_LAST_SCROLL_Y = "arg.LastScrollY";
    private ScrollableLayout mScrollableLayout;
    private TabsLayout tabs;

    public void initScroll() {
        final View header = findViewById(R.id.header);
        tabs = (TabsLayout) findViewById(R.id.tabs);
        tabs.setViewPager();
        mScrollableLayout = (ScrollableLayout) findViewById(R.id.scrollable_layout);
        mScrollableLayout.setDraggableView(tabs);
        mScrollableLayout.setCanScrollVerticallyDelegate(this);
        mScrollableLayout.setOnScrollChangedListener(new OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int y, int oldY, int maxY) {

                final float tabsTranslationY;
                if (y < maxY) {
                    tabsTranslationY = .0F;
                } else {
                    tabsTranslationY = y - maxY;
                }
                tabs.setTranslationY(tabsTranslationY);
                header.setTranslationY(y / 2);
            }
        });
    }

    @Override
    public boolean canScrollVertically(int direction) {
        boolean isSroll = (lv_list != null && lv_list.canScrollVertically(direction));
        if (isSroll) {
            tabs.setImageView(R.drawable.icon_downp2x);
        } else {
            tabs.setImageView(R.drawable.icon_up);
        }
        return isSroll;
    }

    @Override
    public void onFlingOver(int y, long duration) {
        if (lv_list != null) {
            lv_list.smoothScrollBy(y, (int) duration);
        }
    }


    @Override
    public void onUpdate() {
        loadNetData();
    }


    @Override
    public void changeStoneTotleMoney(String st) {
        tvTotalPrice.setText(st);
    }


    public class ConfirOrderAdapter extends BaseAdapter {
        private Map<String, StoneBean> checkedState;
        int index;

        public ConfirOrderAdapter() {
            checkedState = new HashMap<>();
            // L.e("ConfirOrderAdapter");
        }


        public String getAllIdAndNumber() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < listData.size(); i++) {
                StoneBean listEntity = listData.get(i);
                if (i == (listData.size() - 1)) {
                    sb.append(listEntity.getId() + "," + listEntity.getNumber());
                } else {
                    sb.append(listEntity.getId() + "," + listEntity.getNumber() + "|");
                }
            }
            return sb.toString();
        }

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public StoneBean getItem(int i) {
            return listData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public String getTotleMoney() {
            double sum = 0;
            for (int i = 0; i < listData.size(); i++) {
                StoneBean listEntity = listData.get(i);
                sum = sum + Double.valueOf(listEntity.getPrice()) * Double.valueOf(listEntity.getNumber() + "");
            }
            DecimalFormat df = new DecimalFormat("######0.00");
            return df.format(sum) + "";
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup viewGroup) {
            final ViewHolder vh;
            if (convertView == null) {
                convertView = LayoutInflater.from(ConfirmStoneOrderActivity.this).inflate(R.layout.layout_stone_order, null);
                vh = new ViewHolder(convertView);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            final StoneBean listEntity = listData.get(position);
//            if (type == 2) {
                vh.ivAdd.setVisibility(View.GONE);
                vh.ivReduce.setVisibility(View.GONE);
                vh.btnDelete.setVisibility(View.GONE);
                vh.productNumber.setText("×" + listEntity.getNumber() + "");
                vh.productNumber.setEnabled(false);
//            }
//            else {
//                vh.ivAdd.setVisibility(View.VISIBLE);
//                vh.ivReduce.setVisibility(View.VISIBLE);
//                vh.btnDelete.setVisibility(View.VISIBLE);
//                vh.productNumber.setText(listEntity.getNumber() + "");
//                vh.productNumber.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        if (event.getAction() == MotionEvent.ACTION_UP) {
//                            index = position;
//                        }
//                        return false;
//                    }
//                });
//
//                vh.productNumber.clearFocus();
//                if (index != -1 && index == position) {
//                    // 如果当前的行下标和点击事件中保存的index一致，手动为EditText设置焦点。
//                    vh.productNumber.requestFocus();
//                }
//                vh.productNumber.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//                        listEntity.setNumber(Integer.parseInt(vh.productNumber.getText().toString()));
//                    }
//                });
//            }


            vh.ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = listEntity.getNumber();
                    vh.productNumber.setText(++number + "");
                    listEntity.setNumber(number);
                    vh.productPrice.setText(df.format(Double.valueOf(listEntity.getPrice()) * Double.valueOf(listEntity.getNumber() + "")));
                    callBack.changeStoneTotleMoney(getTotleMoney());
                }
            });
            vh.ivReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = listEntity.getNumber();
                    if (number == 1) {
                        showToastReal("数量不能少于1！");
                    } else {
                        vh.productNumber.setText(--number + "");
                        listEntity.setNumber(number);
                        vh.productPrice.setText(df.format(Double.valueOf(listEntity.getPrice()) * Double.valueOf(listEntity.getNumber() + "")));
                    }
                    callBack.changeStoneTotleMoney(getTotleMoney());
                }
            });
            vh.productName.setText(listEntity.getInfo());
            vh.productPrice.setText(listEntity.getPrice());


            vh.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listData.remove(listEntity)) {
                        showToastReal("删除成功！");
                    } else {
                        showToastReal("删除失败！");
                    }
                    notifyDataSetChanged();
                    callBack.changeStoneTotleMoney(getTotleMoney());
                }
            });
            return convertView;
        }

        private AdapterStoneCallBack callBack;

        public void setOnAdapterCallBack(AdapterStoneCallBack callBack) {
            this.callBack = callBack;
        }

        public boolean isAllSelected() {
            return checkedState.size() == listData.size() && listData.size() != 0;
        }


        class ViewHolder {
            @Bind(R.id.btn_delete)
            TextView btnDelete;
            @Bind(R.id.item_button_layout)
            RelativeLayout itemButtonLayout;
            @Bind(R.id.line)
            View line;
            @Bind(R.id.product_name)
            TextView productName;
            @Bind(R.id.product_price)
            TextView productPrice;
            @Bind(R.id.iv_reduce)
            ImageView ivReduce;
            @Bind(R.id.product_number)
            EditText productNumber;
            @Bind(R.id.iv_add)
            ImageView ivAdd;
            @Bind(R.id.id_oder_type)
            TextView idOderType;
            @Bind(R.id.id_lay1)
            LinearLayout idLay1;
            @Bind(R.id.id_view_line)
            View idViewLine;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.requestQueue.cancelAll(this);
    }
}
