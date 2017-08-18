package com.qx.mstarstoretv.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.adapter.DeliveryAdapter;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.json.DeliveryTableResult;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/6 0006.
 */

public class DeliveryTableActivity extends BaseActivity {

    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.tv_customer_name)
    TextView tvCustomerName;
    @Bind(R.id.tv_delivery_order_number)
    TextView tvDeliveryOrderNumber;
    @Bind(R.id.tv_delivery_gold_price)
    TextView tvDeliveryGoldPrice;
    @Bind(R.id.tv_delivery_number)
    TextView tvDeliveryNumber;
    @Bind(R.id.tv_delivery_amount)
    TextView tvDeliveryAmount;
    @Bind(R.id.tv_delivery_quality)
    TextView tvDeliveryQuality;
    @Bind(R.id.tv_delivery_sum_money)
    TextView tvDeliverySumMoney;
    @Bind(R.id.lv_products)
    ListView lvProducts;

    String momNumber;//出库单号
    List<DeliveryTableResult.DataBean.ModelListBean> list;//出货单详情列表
    DeliveryTableResult deliveryTableResult;
    DeliveryTableResult.DataBean.MoItemBean moItemBean;
    private DeliveryAdapter deliveryAdapter;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_delivery_table);
        ButterKnife.bind(this);
        init();
        getDate();
        loadNetData();
    }

    private void init() {
        titleText.setText("出货单");
        idIgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void getDate() {
        momNumber = getIntent().getStringExtra("momNumber");
        type = getIntent().getStringExtra("type");
    }

    private void initView() {
        tvCustomerName.setText("客户："+moItemBean.getCustomerName());
        tvDeliveryOrderNumber.setText("订单编号："+moItemBean.getOrderNum());
        tvDeliveryGoldPrice.setText("金价："+moItemBean.getGoldPrice());
        tvDeliveryNumber.setText("出库单号："+moItemBean.getMoNum());
        tvDeliveryAmount.setText("件数："+moItemBean.getNumber());
        tvDeliveryQuality.setText("成色："+moItemBean.getPurityName());
        tvDeliverySumMoney.setText(moItemBean.getTotalPrice());
        if(list!=null){
             deliveryAdapter = new DeliveryAdapter(this, list);
        }
        lvProducts.setAdapter(deliveryAdapter);
    }

    @Override
    public void loadNetData() {
        String url = "";
        if(type.equals("1")){
            url = AppURL.URL_CODE_SENDING_DETAIL + "tokenKey=" + BaseApplication.getToken() + "&moNum=" + momNumber;
        }else if(type.equals("2")){
            url = AppURL.URL_CODE_SEARCH_DELIVERY_DETAIL + "tokenKey=" + BaseApplication.getToken() + "&moNum=" + momNumber;
        }
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
                    deliveryTableResult = new Gson().fromJson(result, DeliveryTableResult.class);
                    if(deliveryTableResult.getData()!=null){
                        moItemBean = deliveryTableResult.getData().getMoItem();
                        list = deliveryTableResult.getData().getModelList();
                        if(moItemBean!=null){
                            initView();
                        }
                    }
                } else if (error.equals("2")) {
                    loginToServer(CustomMadeActivity.class);
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
