package com.qx.mstarstoretv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.Scrollable.CanScrollVerticallyDelegate;
import com.qx.mstarstoretv.Scrollable.OnFlingOverListener;
import com.qx.mstarstoretv.Scrollable.TabsLayout;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.bean.Type;
import com.qx.mstarstoretv.inter.AdapterCallBack;
import com.qx.mstarstoretv.inter.ConfirmOrderOnUpdate;
import com.qx.mstarstoretv.json.AddressEntity;
import com.qx.mstarstoretv.json.ConfirmOrderResult;
import com.qx.mstarstoretv.json.CustomerEntity;
import com.qx.mstarstoretv.json.DefaultValue;
import com.qx.mstarstoretv.json.IsHaveCustomerResult;
import com.qx.mstarstoretv.json.OrderListResult;
import com.qx.mstarstoretv.json.PriceResult;
import com.qx.mstarstoretv.net.ImageLoadOptions;
import com.qx.mstarstoretv.net.OKHttpRequestUtils;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.CustomSelectButton;
import com.qx.mstarstoretv.viewutils.GridViewWithHeaderAndFooter;
import com.qx.mstarstoretv.viewutils.PullToRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * 创建人：Yangshao
 * 创建时间：2016/9/23 15:35
 * @version     确认订单页面
 *
 */
public class ConfirmOrderActivity extends BaseActivity implements PullToRefreshView.OnFooterRefreshListener,
        PullToRefreshView.OnHeaderRefreshListener, CanScrollVerticallyDelegate,
        OnFlingOverListener, AdapterCallBack, ConfirmOrderOnUpdate {

    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.id_lay_address)
    RelativeLayout layAddress;
    @Bind(R.id.ck_checkall)
    CheckBox ckCheckall;
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
    @Bind(R.id.id_cs_color)
    CustomSelectButton idCsColor;
    @Bind(R.id.id_receipt)
    Button idReceipt;
    @Bind(R.id.id_cs_mass)
    CustomSelectButton idCsMass;
    @Bind(R.id.pull_refresh_view)
    PullToRefreshView pullRefreshView;
    @Bind(R.id.rel_shopping_car_bottom_action)
    RelativeLayout relShoppingCarBottomAction;
    /*选择成色的ItME*/
    List<OrderListResult.DataEntity.ModelColorEntity> modelColorItme;
    /*选择质量等级的Item*/
    List<OrderListResult.DataEntity.ModelQualityEntity> modelQualityItem;
    List<OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity> listData;
    @Bind(R.id.id_lay_order_detail)
    LinearLayout idLayOrderDetail;
    @Bind(R.id.id_et_seach)
    EditText idEtSeach;
    @Bind(R.id.id_view_line)
    View idViewLine;
    @Bind(R.id.ig_btn_seach)
    ImageView igBtnSeach;
    @Bind(R.id.id_rl1)
    RelativeLayout idRl1;
    @Bind(R.id.phone_tv)
    TextView phoneTv;
    @Bind(R.id.lny_loading_layout)
    LinearLayout lnyLoadingLayout;
    AddressEntity isDefaultAddress;
    CustomerEntity isDefaultCustomer;
    @Bind(R.id.id_ed_word)
    EditText idEdWord;
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
    @Bind(R.id.tv_totalPrice_title)
    TextView tvTotalPriceTitle;
    @Bind(R.id.product_gridview)
    GridViewWithHeaderAndFooter productGridview;
    @Bind(R.id.id_order_num)
    TextView idOrderNum;
    @Bind(R.id.id_order_date)
    TextView idOrderDate;
    @Bind(R.id.id_order_status)
    TextView idOrderStatus;
    @Bind(R.id.id_order_goodprice)
    TextView idOrderGoodprice;
    @Bind(R.id.ll_order_header)
    LinearLayout llOrderHeader;

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
    private boolean isShowPrice;
    private boolean ischooseEmpty;//是否选择了产品
    private boolean isCustomized;//是否是用户定制
    private DefaultValue defaultValue;
    Map<String, OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity> mchecked = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_confirmorder);
        ButterKnife.bind(this);
        getActivityType(getIntent());
        initView();
        initScroll();
        loadNetData();
        isFirst = true;

    }

    public int getActivityType(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return 0;
        }
        type = bundle.getInt("type");
        orderId = bundle.getString("itemId");
        waitOrderCount = getIntent().getIntExtra("waitOrderCount", 0);
        return type;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getActivityType(intent);
        initView();
        initScroll();
        loadNetData();
    }

    @Override
    public void loadNetData() {
        String url;
        if (type == 2) {
            url = AppURL.URL_ORDER_DETAIL + "tokenKey=" + BaseApplication.getToken() + "&orderId=" + orderId + "&pageNum=24";
        } else {
            url = AppURL.URL_ORDER_LIST + "tokenKey=" + BaseApplication.getToken() + "&purityId=" + purityId +
                    "&qualityId=" + qualityId + "&cpage=" + curpage + "&pageNum=24";
        }
        L.e("获取订单信息" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                lnyLoadingLayout.setVisibility(View.GONE);
                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    OrderListResult orderListResult = new Gson().fromJson(result, OrderListResult.class);
                    OrderListResult.DataEntity dataEntity = orderListResult.getData();
                    if (dataEntity != null) {
                        OrderListResult.DataEntity.CurrentOrderlListEntity currentOrderlList = dataEntity.getCurrentOrderlList();
                        List<OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity> list = currentOrderlList.getList();
                        if (isDefaultAddress == null) {
                            isDefaultAddress = dataEntity.getAddress();
                        }
                        if (isDefaultCustomer == null) {
                            isDefaultCustomer = dataEntity.getCustomer();
                        }
                        if (pullState != PULL_LOAD) {
                            confirOrderAdapter.cancelAll();
                            listData.clear();
                        }
                        list_count = Integer.valueOf(currentOrderlList.getList_count());
                        if (curpage == 1) {
                            modelColorItme = dataEntity.getModelColor();
                            modelQualityItem = dataEntity.getModelQuality();
                        }
                        if (list_count == 0) {
                            showToastReal("没有数据");
                        } else {
                            listData.addAll(list);
                        }
                        if (isDefaultAddress != null) {
                            idTvName.setText(isDefaultAddress.getName());
                            idTvAddress.setText(isDefaultAddress.getAddr());
                            phoneTv.setText(isDefaultAddress.getPhone());
                        }
                        OrderListResult.DataEntity.OrderInfoEntity orderInfo = dataEntity.getOrderInfo();
                        if (type == 2) {
                            Double totalPrice = dataEntity.getTotalPrice();
                            Double totalNeedPayPrice = dataEntity.getTotalNeedPayPrice();
                            L.e("orderInfo" + orderInfo.toString() + "totalPrice:" + totalPrice + "totalNeedPayPrice:" + totalNeedPayPrice);
                            idCsColor.setTextName(orderInfo.getPurityName());
                            idCsMass.setTextName(orderInfo.getQualityName());
                            idEdWord.setText(orderInfo.getWord());
                            idEdRemarks.setText(orderInfo.getOrderNote());
                            idEtSeach.setText(orderInfo.getCustomerName());
                            tvTotalPrice.setText(UIUtils.stringChangeToInt(totalNeedPayPrice+"") + "--定金   " + StringUtils.formatedPrice(totalPrice) + "(合计)");
                            idTvNeedPrice.setText("定金 :" + StringUtils.formatedPrice(totalNeedPayPrice));
                            idTvTotalPrice.setText("合计 :" + StringUtils.formatedPrice(totalPrice));
                        }

                        if (isFirst) {
                            if (type == 2) {
                                setListHeadView(orderInfo);
                                invTitle = orderInfo.getInvoiceTitle();
                                invType = orderInfo.getInvoiceType();
                                if (!StringUtils.isEmpty(orderInfo.getInvoiceTitle()) && !StringUtils.isEmpty(invType)) {
                                    idReceipt.setText("类型：" + invType + "     抬头：" + invTitle);
                                    L.e("类型：" + invTitle + "     抬头：" + invTitle);
                                }

                            } else {
                                defaultValue = dataEntity.getDefaultValue();
                                if (defaultValue.getModelColor() != null) {
                                    if (!defaultValue.getModelColor().getId().isEmpty()) {
                                        idCsColor.setTextName(defaultValue.getModelColor().getTitle());
                                        purityId = defaultValue.getModelColor().getId();
                                    }
                                }
                                if (defaultValue.getModelQuality() != null) {
                                    if (!defaultValue.getModelQuality().getId().isEmpty()) {
                                        idCsMass.setTextName(defaultValue.getModelQuality().getTitle());
                                        qualityId = defaultValue.getModelQuality().getId() + "";
                                    }
                                }

                                isDefaultCustomer = dataEntity.getCustomer();
                                if (isDefaultCustomer != null) {
                                    idEtSeach.setText(isDefaultCustomer.getCustomerName());
                                }
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
                        if (type == 2) {
                            btGoPay.setVisibility(View.GONE);
                            idLayOrderDetail.setVisibility(View.VISIBLE);
                            idLayPrice1.setVisibility(View.GONE);
                            idLayPrice2.setVisibility(View.VISIBLE);
                        } else {
                            btGoPay.setVisibility(View.VISIBLE);
                            idLayOrderDetail.setVisibility(View.GONE);
                            idLayPrice1.setVisibility(View.VISIBLE);
                            idLayPrice2.setVisibility(View.GONE);
                        }
                        /**
                         * 是否显示价格处理
                         */
                        isShowPrice = SpUtils.getInstace(ConfirmOrderActivity.this).getBoolean("isShowPrice", true);
                        if (isShowPrice) {
                            tvTotalPriceTitle.setVisibility(View.VISIBLE);
                            tvTotalPrice.setVisibility(View.VISIBLE);
                        } else {
                            tvTotalPriceTitle.setVisibility(View.GONE);
                            tvTotalPrice.setVisibility(View.GONE);
                        }
                        L.e("解析成功");
                        //清空是否选中
                        if(type!=2){
                            confirOrderAdapter.clearCheckedState();
                            mchecked.clear();
                        }
                        setAdapter();
                        endNetRequest();
                        initListener();
                        L.e("mchecked" + mchecked.size());
                        changeState(mchecked);
                    }

                }
                if (error == 2) {
                    if (isCustomized) {
                        loginToServer(SimpleStyleInfromationActivity.class);
                    } else {
                        loginToServer(StyleInfromationActivity.class);
                    }
                } else {
                    endNetRequest();
                }

            }

            @Override
            public void onFail(String fail) {
                endNetRequest();
            }

        });
    }


    public void setListHeadView(OrderListResult.DataEntity.OrderInfoEntity orderInfo) {
        llOrderHeader.setVisibility(View.VISIBLE);
        idOrderNum.setText(orderInfo.getOrderNum());
        idOrderDate.setText("" + orderInfo.getOrderDate());
        idOrderStatus.setText("" + orderInfo.getOrderStatus());
        idOrderGoodprice.setText("" + orderInfo.getGoldPrice());
    }


    private void initListener() {
        if (idCsColor != null)
            idCsColor.setOnSelectData(new CustomSelectButton.OnselectData() {
                @Override
                public List<Type> getData() {
                    List<Type> list = new ArrayList<>();
                    for (int i = 0; i < modelColorItme.size(); i++) {
                        Type type = new Type();
                        type.setId(modelColorItme.get(i).getId());
                        type.setTypeName(modelColorItme.get(i).getTitle() + "  " + modelColorItme.get(i).getPrice());
                        list.add(type);
                    }
                    return list;
                }

                @Override
                public void getSelectId(Type type) {
                    idCsColor.setText(type.getTypeName());
                    purityId = type.getId();
//                    queryPricefoServer(qualityId, purityId);
                }

                @Override
                public String getTitle() {
                    return "选择质量等级";
                }

                @Override
                public View getRootView() {
                    return idCsColor;
                }
            });

        idReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmOrderActivity.this, ReceiptActivity.class);
                if (!StringUtils.isEmpty(invTitle)) {
                    intent.putExtra("invTitle", invTitle);
                }
                intent.putExtra("type", type);
                intent.putExtra("invTitle", invType);
                startActivityForResult(intent, 13);
            }
        });

        if (idCsMass != null)
            idCsMass.setOnSelectData(new CustomSelectButton.OnselectData() {
                @Override
                public List<Type> getData() {
                    List<Type> list = new ArrayList<>();
                    for (int i = 0; i < modelQualityItem.size(); i++) {
                        Type type = new Type();
                        type.setId(modelQualityItem.get(i).getId());
                        type.setTypeName(modelQualityItem.get(i).getTitle());
                        list.add(type);
                    }
                    return list;
                }

                @Override
                public View getRootView() {
                    return idCsMass;
                }

                @Override
                public void getSelectId(Type type) {
                    idCsMass.setText(type.getTypeName());
                    qualityId = type.getId();
//                    queryPricefoServer(qualityId, purityId);
                }

                @Override
                public String getTitle() {
                    return "选择成色";
                }
            });


        ckCheckall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ckCheckall.isChecked()) {
                    confirOrderAdapter.selectAll();
                } else {
                    confirOrderAdapter.cancelAll();
                }
                confirOrderAdapter.notifyDataSetChanged();
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
                    String st = idEtSeach.getText().toString();
                    if (st.isEmpty()) {
                        Intent intent = new Intent(ConfirmOrderActivity.this, CustomersListActivity.class);
                        intent.putExtra("keyWord", st);
                        startActivityForResult(intent, 11);
                    } else {
                        seachCustom(idEtSeach.getText().toString());
                    }
                }
            }
        });

        idEdWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    updateCustomorWord(idEtSeach.getText().toString(), idEdWord.getText().toString());
                }
            }
        });
         /*确定支付*/
        if (isShowPrice) {
            btGoPay.setEnabled(true);
            btGoPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // openActivity(ModeOfPaymentActivity.class, null);
                    btGoPay.setTextColor(getResources().getColor(R.color.white));
                    if (ischooseEmpty) {
                        submitOrder();
                    } else {
                        showToastReal("请选择商品！");
                    }
                }
            });
        } else {
            btGoPay.setTextColor(getResources().getColor(R.color.text_color3));
            btGoPay.setEnabled(false);
        }


        /*选择地址*/
        layAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openActivity(ShopingAddressActivity.class,null);
                // Intent intent = new Intent(ConfirmOrderActivity.this, ShopingAddressActivity.class);
                // startActivityForResult(intent, 12);
                Intent intent = new Intent(ConfirmOrderActivity.this, AddressListActivity.class);
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
                intent = new Intent(ConfirmOrderActivity.this, ModeOfPaymentActivity.class);
                if (orderId != null && !orderId.equals("")) {
                    intent.putExtra("id", orderId);
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
                                    listData.get(j).setNeedPayPrice(priceList.get(i).getNeedPayPrice() + "");
                                }
                            }
                        }
                    }
                    endNetRequest();
                    if (type == 2) {
                        Double total = .0;
                        Double needPrice = .0;
                        for (OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity cartItem : listData) {
                            total += Double.valueOf(cartItem.getPrice());
                            needPrice += Double.valueOf(cartItem.getNeedPayPrice());
                            L.e("needPrice" + needPrice);
                        }
                        //价格
                        tvTotalPrice.setText(StringUtils.formatedPrice(total));
                        idLayPrice1.setVisibility(View.INVISIBLE);
                        idLayPrice2.setVisibility(View.VISIBLE);
                        idTvNeedPrice.setText("定金 :" + StringUtils.formatedPrice(needPrice));
                        idTvTotalPrice.setText("合计 :" + StringUtils.formatedPrice(total));
                    } else {
                        confirOrderAdapter.updatePrice();
                    }
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
            intent.setClass(this, CustomMadeActivity.class);
            startActivity(intent);
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
        isCustomized = SpUtils.getInstace(this).getBoolean("isCustomized", true);
        lnyLoadingLayout.setVisibility(View.VISIBLE);
        if (listData == null) {
            listData = new ArrayList<>();
        }


        confirOrderAdapter = new ConfirOrderAdapter();
        confirOrderAdapter.setOnAdapterCallBack(this);


          /*发票*/
        idReceipt.setText(R.string.write_a_receip);


        if (type == 2) {
            ckCheckall.setVisibility(View.GONE);
            titleText.setText(R.string.order_detail);
            idCancleOrder.setText(R.string.cancle_order);
        } else {
            titleText.setText(R.string.confirm_order);
        }


        if (isCustomized) {
            SimpleStyleInfromationActivity.setConfirmOrderOnUpdate(this);
        } else {
            StyleInfromationActivity.setConfirmOrderOnUpdate(this);
        }

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
            }
        });
    }

    private void setAdapter() {
        if (UIUtils.isPad(this)) {
            productGridview.setNumColumns(2);
        } else {
            productGridview.setNumColumns(1);
        }
        productGridview.setEmptyView(findViewById(R.id.lny_no_result));
        productGridview.setAdapter(confirOrderAdapter);
        pullRefreshView.setOnFooterRefreshListener(this);
        pullRefreshView.setOnHeaderRefreshListener(this);
    }

    //OrderCurrentSubmitDo?itemId=1|2|3&addressId=1&purityId=3&qualityId=2&tokenKey=944df2f27ffce557042887589986c193
    String purityId, qualityId;
    List<String> mcheckId;


    String invTitle, invType;

    /*
     * @version  提交订单
     */
    public void submitOrder() {
        String url = AppURL.URL_ORDER_SUBMIT + "&tokenKey=" + BaseApplication.getToken();
        String word = idEdWord.getText().toString();
        String remarks = idEdRemarks.getText().toString();
        if (isDefaultAddress == null) {
            ToastManager.showToastReal(getString(R.string.please_write_address));
            return;
        }
        if (isDefaultCustomer == null) {
            ToastManager.showToastReal(getString(R.string.please_write_customer));
            return;
        }

        String addressId = isDefaultAddress.getId();
        String customerID = isDefaultCustomer.getCustomerID() + "";
        String itemurl = "";
        if (mcheckId == null || mcheckId.size() == 0) {
            showToastReal(getString(R.string.please_select_order));
        } else {
            itemurl = StringUtils.purUrlCut("itemId", mcheckId).toString();
        }
        L.e("word" + word + "   addressId" + addressId + "");
        if (customerID.equals("-1")) {
            showToastReal("请填写客户资料");
            return;
        }
        if (StringUtils.isEmpty(word) && StringUtils.isEmpty(addressId) && StringUtils.isEmpty(customerID) && StringUtils.isEmpty(itemurl)) {
            showToastReal("请填写完整资料");
            return;
        }
        url += itemurl + "&word=" + word + "&purityId=" + purityId + "&addressId=" + addressId + "&qualityId=" + qualityId + "&customerID=" + customerID +
                "&invTitle=" + invTitle + "&invType=" + invType + "&orderNote=" + remarks;
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
                    String id = jsonObject.get("id").getAsString();
                    waitOrderCount = Integer.valueOf(jsonObject.get("waitOrderCount").getAsString());
                    //是否需要付款  0不需要   1需要
                    int isNeedPay = jsonObject.get("isNeetPay").getAsInt();
                    //是都已经上传到ERP订单  //1 跳转到生产详情页    0跳转到审核页面
                    int isErpOrder = jsonObject.get("isErpOrder").getAsInt();
                    //intent = new Intent(getActivity(), CustomMadeActivity.class);
                    // startActivity(intent);

                    Intent intent;


                    if (isErpOrder == 1) {

                        String orderNum = jsonObject.get("orderNum").getAsString();
                        ProgressDialog progressDialog = new ProgressDialog(ConfirmOrderActivity.this, orderNum, 1);
                        progressDialog.showAsDropDown(rootView);
                        // intent.putExtra("pageNumber",1);
                    } else {
                        // intent.putExtra("pageNumber",0);  //待审核
                        Bundle bundle = new Bundle();
                        bundle.putInt("type", 2);
                        bundle.putString("itemId", id);
                        openActivity(ConfirmOrderActivity.class, bundle);
                    }

                } else if (error == 2) {
                    if (isCustomized) {
                        loginToServer(SimpleStyleInfromationActivity.class);
                    } else {
                        loginToServer(StyleInfromationActivity.class);
                    }
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
                            updateCustomorWord(isDefaultCustomer.getCustomerID() + "", idEdWord.getText().toString());
                        } else {
                            idEtSeach.setText(isDefaultCustomer.getCustomerName());
                        }

                    }
                    if (state == 2) {
                        Intent intent = new Intent(ConfirmOrderActivity.this, CustomersListActivity.class);
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
                "&customerId=" + cus + "&word=" + word;
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                L.e(result);
                if (error == 0) {
                    showToastReal("更新成功");
                }
                if (error == 2) {
                    if (isCustomized) {
                        loginToServer(SimpleStyleInfromationActivity.class);
                    } else {
                        loginToServer(StyleInfromationActivity.class);
                    }
                }
            }

            @Override
            public void onFail(String fail) {

            }
        });
    }


    @Override
    public void changeState(Map<String, OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity> checked) {
        List<OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity> checkedGoods = new ArrayList<>();
        if (confirOrderAdapter.isAllSelected()) {
            ckCheckall.setChecked(true);
        } else {
            ckCheckall.setChecked(false);
        }
        mchecked.putAll(checked);
        Set<Map.Entry<String, OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity>> entries = checked.entrySet();
        for (Map.Entry<String, OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity> entry : entries) {
            OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity entity = entry.getValue();
            checkedGoods.add(entity);
        }
        Double total = .0;
        for (OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity cartItem : checkedGoods) {
            total += Double.valueOf(cartItem.getPrice());
        }
        //价格
        tvTotalPrice.setText(StringUtils.formatedPrice(total));
        if (checkedGoods.size() == 0) {
            ischooseEmpty = false;
        } else {
            ischooseEmpty = true;
        }
        if (checkedGoods.size() != 0) {
            mcheckId = new ArrayList<>();
            for (int i = 0; i < checkedGoods.size(); i++) {
                mcheckId.add(checkedGoods.get(i).getId());
            }
        }
    }


    private void endNetRequest() {
        ckCheckall.setChecked(confirOrderAdapter.isAllSelected());
        //changeState(mchecked);
        confirOrderAdapter.notifyDataSetChanged();
        tempCurpage = curpage;
        if (pullState == PULL_LOAD) {
            pullRefreshView.onFooterRefreshComplete();
        } else if (pullState == PULL_REFRESH) {
            pullRefreshView.onHeaderRefreshComplete();
        }
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
            updateCustomorWord(customerID + "", idEdWord.getText().toString());
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
            if (type == 2) {
                updatAddress();
            }
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

    private void updatAddress() {
        String url = AppURL.URL_ORDER_ADRESS_CHANGE + "tokenKey=" + BaseApplication.getToken() + "&addressId=" + isDefaultAddress.getId() +
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
                    if (isCustomized) {
                        loginToServer(SimpleStyleInfromationActivity.class);
                    } else {
                        loginToServer(StyleInfromationActivity.class);
                    }
                }
            }

            @Override
            public void onFail(String fail) {

            }
        });
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
                    if (isCustomized) {
                        loginToServer(SimpleStyleInfromationActivity.class);
                    } else {
                        loginToServer(StyleInfromationActivity.class);
                    }
                }
            }

            @Override
            public void onFail(String fail) {

            }
        });
    }

    private static final String ARG_LAST_SCROLL_Y = "arg.LastScrollY";

    private TabsLayout tabs;

    public void initScroll() {
        final View header = findViewById(R.id.header);
        tabs = (TabsLayout) findViewById(R.id.tabs);
        tabs.setViewPager();

    }

    @Override
    public boolean canScrollVertically(int direction) {
        boolean isSroll = (productGridview != null && productGridview.canScrollVertically(direction));
        if (isSroll) {
            tabs.setImageView(R.drawable.icon_downp2x);
        } else {
            tabs.setImageView(R.drawable.icon_up);
        }
        return isSroll;
    }

    @Override
    public void onFlingOver(int y, long duration) {
        if (productGridview != null) {
            productGridview.smoothScrollBy(y, (int) duration);
        }
    }


    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        if (listData.size() < list_count) {
            tempCurpage = curpage;
            curpage++;
            pullState = PULL_LOAD;
            loadNetData();
        } else {
            showToastReal("没有更多数据");
            view.onFooterRefreshComplete();
        }
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        tempCurpage = curpage;
        curpage = 1;
        pullState = PULL_REFRESH;
        loadNetData();
    }

    @Override
    public void onUpdate() {
        loadNetData();
    }


    public class ConfirOrderAdapter extends BaseAdapter {
        private Map<String, OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity> checkedState;

        public ConfirOrderAdapter() {
            checkedState = new HashMap<>();
            isShowPrice = SpUtils.getInstace(ConfirmOrderActivity.this).getBoolean("isShowPrice", true);
            // L.e("ConfirOrderAdapter");
        }

        public void clearCheckedState(){
            checkedState.clear();
        }

        public void selectAll() {
            for (OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity t : listData) {
                checkedState.put(t.getId(), t);
            }
            if (callBack != null)
                callBack.changeState(checkedState);
        }

        public void cancelAll() {
            checkedState.clear();
            if (callBack != null)
                callBack.changeState(checkedState);
        }

        public void updatePrice() {
            if (callBack != null)
                callBack.changeState(checkedState);
        }


        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity getItem(int i) {
            return listData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup viewGroup) {
            final ViewHolder vh;
            if (convertView == null) {
                convertView = LayoutInflater.from(ConfirmOrderActivity.this).inflate(R.layout.layout_order, null);
                vh = new ViewHolder(convertView);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }

            if (type == 2) {
                vh.ckCheckone.setVisibility(View.GONE);
            }

            OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity listEntity = listData.get(position);
            if (checkedState.get(getItem(position).getId()) != null) {
                vh.ckCheckone.setChecked(true);
            } else {
                vh.ckCheckone.setChecked(false);
            }
            vh.ckCheckone.setTag(position);
            vh.ckCheckone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkedState.get(getItem(position).getId()) != null) {
                        checkedState.remove(getItem(position).getId());
                    } else {
                        checkedState.put(getItem(position).getId(), getItem(position));
                    }
                    if (callBack != null)
                        callBack.changeState(checkedState);
                }
            });
            vh.productName.setText(listEntity.getTitle());
            if (isShowPrice) {
                vh.productPrice.setText(UIUtils.stringChangeToTwoBitDouble(listEntity.getPrice()));
            } else {
                vh.productPrice.setText("");
            }
            vh.idTvInformation.setText(listEntity.getInfo());
            vh.productNorms.setText(listEntity.getBaseInfo());
            vh.productNumber.setText(listEntity.getNumber());
            vh.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent();
//                    intent.putExtra("itemId", listData.get(position).getId());
//                    setResult(11, intent);
//                    finish();
                    Bundle bundle = new Bundle();
                    bundle.putString("itemId", listData.get(position).getId());
                    if (type == 2) {
                        // ModelOrderWaitCheckDetailModifyInfoDo?orderId=10&customerId=2990&tokenKey=10b588002228fa805231a59bb7976bf4
                        bundle.putInt("type", 2);
                        bundle.putString("orderId", orderId);
                    } else {
                        bundle.putInt("type", 1);
                    }
                    if (isCustomized) {
                        openActivity(SimpleStyleInfromationActivity.class, bundle);
                    } else {
                        openActivity(StyleInfromationActivity.class, bundle);
                    }

                }
            });
            ImageLoader.getInstance().displayImage(listData.get(position).getPic(), vh.productImg, ImageLoadOptions.getOptions());
            vh.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteOrder(listData.get(position).getId());
                }
            });
            return convertView;
        }

        private AdapterCallBack callBack;

        public void setOnAdapterCallBack(AdapterCallBack callBack) {
            this.callBack = callBack;
        }

        public boolean isAllSelected() {
            return checkedState.size() == listData.size() && listData.size() != 0;
        }

        class ViewHolder {
            @Bind(R.id.ck_checkone)
            CheckBox ckCheckone;
            @Bind(R.id.btn_edit)
            TextView btnEdit;
            @Bind(R.id.btn_delete)
            TextView btnDelete;
            @Bind(R.id.item_button_layout)
            RelativeLayout itemButtonLayout;
            @Bind(R.id.line)
            View line;
            @Bind(R.id.product_img)
            ImageView productImg;
            @Bind(R.id.product_name)
            TextView productName;
            @Bind(R.id.product_norms)
            TextView productNorms;
            @Bind(R.id.product_price)
            TextView productPrice;
            @Bind(R.id.product_number)
            TextView productNumber;
            @Bind(R.id.id_oder_type)
            TextView idOderType;
            @Bind(R.id.id_lay1)
            LinearLayout idLay1;
            @Bind(R.id.id_view_line)
            View idViewLine;
            @Bind(R.id.id_tv_information)
            TextView idTvInformation;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }


        /*
         * @version    删除订单
         *
         */
        public void deleteOrder(String id) {
            String url;
            if (type == 2) {
                if (listData.size() <= 1) {
                    showToastReal("请点击取消订单");
                    return;
                }
                url = AppURL.URL_ORDER_WAIT_DELETE + "&tokenKey=" + BaseApplication.getToken() + "&itemId=" + id;
            } else {
                url = AppURL.URL_ORDER_DELETE + "&tokenKey=" + BaseApplication.getToken() + "&itemId=" + id;
            }
            L.e("删除订单" + url);
            VolleyRequestUtils.getInstance().getCookieRequest(ConfirmOrderActivity.this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
                @Override
                public void onSuccess(String result) {
                    L.e(result);
                    int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                    if (error == 0) {
                        ToastManager.showToastReal("删除成功");
                        String strwaitOrderCount = new Gson().fromJson(result, JsonObject.class).get("data").getAsJsonObject().get("waitOrderCount").getAsString();
                        waitOrderCount = Integer.valueOf(strwaitOrderCount);
                        loadNetData();
                    }
                    if (error == 2) {
                        loginToServer(OrderActivity.class);
                    }
                    if (error == 1) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.requestQueue.cancelAll(this);
    }
}
