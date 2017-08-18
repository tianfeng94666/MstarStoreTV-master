package com.qx.mstarstoretv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.AddressListActivity;
import com.qx.mstarstoretv.activity.ProgressDialog;
import com.qx.mstarstoretv.activity.SearchOrderMainActivity;
import com.qx.mstarstoretv.adapter.BaseViewHolder;
import com.qx.mstarstoretv.adapter.CommonAdapter;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.BaseFragment;
import com.qx.mstarstoretv.json.ModelListEntity;
import com.qx.mstarstoretv.json.OrderInfoEntity;
import com.qx.mstarstoretv.json.ProductListResult;
import com.qx.mstarstoretv.json.SearchOrderMainResult;
import com.qx.mstarstoretv.net.ImageLoadOptions;
import com.qx.mstarstoretv.net.OKHttpRequestUtils;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.viewutils.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class ProductingFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView ivRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.id_order_num)
    TextView idOrderNum;
    @Bind(R.id.id_order_date)
    TextView idOrderDate;
    @Bind(R.id.id_update_date)
    TextView idUpdateDate;
    @Bind(R.id.id_tv_invo)
    TextView idTvInvo;
    @Bind(R.id.id_tv_detail)
    TextView idTvDetail;
    @Bind(R.id.tv_remark)
    TextView tvRemark;
    @Bind(R.id.id_pd_lv)
    ListView idPdLv;

    @Bind(R.id.id_tv_confirfilterr)
    TextView idTvConfirfilterr;
    @Bind(R.id.id_tv_showdialog)
    TextView idTvShowdialog;
    @Bind(R.id.tips_loading_msg)
    TextView tipsLoadingMsg;
    @Bind(R.id.lny_loading_layout)
    LinearLayout lnyLoadingLayout;
    @Bind(R.id.root_view)
    RelativeLayout rootView;
    @Bind(R.id.pull_refresh_view)
    PullToRefreshView pullRefreshView;
    private SearchOrderMainResult.DataBean.OrderProduceBean bean;
    private OrderInfoEntity orderInfoBean;
    private String orderNum;
    private List<ModelListEntity> modelListBeen;
    private List<ModelListEntity> orderlList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_production, null);
        ButterKnife.bind(this, view);
        idRelTitle.setVisibility(View.GONE);

        getIntentData();
        initView();
        getData();

        return view;
    }


    public void getIntentData() {
        Bundle extras = getActivity().getIntent().getExtras();
        orderNum = extras.getString("orderNum");
    }


    public void getData() {
        bean = ((SearchOrderMainActivity) getActivity()).getOrderProduceBean();
        if (bean != null) {
            modelListBeen = bean.getModelList();
            orderInfoBean = bean.getOrderInfo();
            if (orderInfoBean != null) {
                initViewData(modelListBeen, orderInfoBean);
            }
        }

    }

    public String isEmpty(String st) {

        if (st == null || st.equals("") || st.equals("null")) {
            return "暂无数据";
        } else {
            return st;
        }
    }

    private void initViewData(List<ModelListEntity> modelList, OrderInfoEntity orderInfoBean) {
        idOrderNum.setText("订单编号：" + isEmpty(orderInfoBean.getOrderNum() + ""));
        idOrderDate.setText("下单日期：" + isEmpty(orderInfoBean.getOrderDate()));
        idUpdateDate.setText("审核日期：" + isEmpty(orderInfoBean.getConfirmDate()));
        idTvInvo.setText("发票： " + "类型：" + isEmpty(orderInfoBean.getInvoiceType() + "") + " 抬头：" + isEmpty(orderInfoBean.getInvoiceTitle() + ""));
        tvRemark.setText("备注：" + isEmpty(orderInfoBean.getOrderNote()));
        idTvDetail.setText(isEmpty(orderInfoBean.getOtherInfo()));
        if (pullStauts != PULL_LOAD) {
            orderlList.clear();
        }
        orderlList.addAll(modelList);
        adapter.setListData(orderlList);
        endNetRequse();
    }

    ProductionAdapter adapter;

    private void initView() {
        titleText.setText("生产中");
        pullRefreshView.setOnFooterRefreshListener(this);
        pullRefreshView.setOnHeaderRefreshListener(this);
        orderlList = new ArrayList<>();
        adapter = new ProductionAdapter(orderlList, R.layout.layout_order);
        idPdLv.setAdapter(adapter);
        idTvShowdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(getActivity(), orderInfoBean.getOrderNum(), 2);
                progressDialog.showAsDropDown(rootView);
            }
        });
        idTvConfirfilterr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNetData();
            }
        });
    }

    public void loadNetData() {
        String url = AppURL.URL_PD_ORDER_DETAIL2 + "orderNum=" + orderNum + "&tokenKey=" + BaseApplication.getToken();
        L.e("ProductionListActivity" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(getActivity(), url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    lnyLoadingLayout.setVisibility(View.GONE);
                    ProductListResult productListResult = new Gson().fromJson(result, ProductListResult.class);
                    if (productListResult.getData() == null) {
                        endNetRequse();
                        return;
                    }
                    ProductListResult.DataEntity productListResultData = productListResult.getData();
                    List<ModelListEntity> modelList = productListResultData.getModelList();
                    OrderInfoEntity orderInfo = productListResultData.getOrderInfo();
                    initViewData(modelList, orderInfo);
                }
                if (error == 1) {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);
                }
                if (error == 2) {
                    loginToServer(AddressListActivity.class);
                }

            }

            @Override
            public void onFail(String fail) {
                endNetRequse();
            }
        });
    }


    public void endNetRequse() {
        tempCurpage = cupage;
        if (pullStauts == PULL_LOAD) {
            pullRefreshView.onFooterRefreshComplete();
        }
        if (pullStauts == PULL_REFRESH) {
            pullRefreshView.onHeaderRefreshComplete();
        }
        pullStauts = 0;
    }

    public static final int PULL_LOAD = 1;
    public static final int PULL_REFRESH = 2;
    public int pullStauts;
    public int cupage;
    public int tempCurpage;
    private int listCount = 0;

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        if (listCount > adapter.getCount()) {
            tempCurpage = cupage;
            cupage++;
            pullStauts = PULL_LOAD;
            loadNetData();
        } else {
            ToastManager.showToastReal("没有更多数据");
            pullRefreshView.onFooterRefreshComplete();
        }

    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        tempCurpage = cupage;
        cupage = 1;
        pullStauts = PULL_REFRESH;
        loadNetData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public class ProductionAdapter extends CommonAdapter<ModelListEntity> {
        public ProductionAdapter(List<ModelListEntity> mDatas, int itemLayoutId) {
            super(mDatas, itemLayoutId);
            L.e("ProductionAdapter");
        }

        @Override
        public void convert(int position, BaseViewHolder helper, ModelListEntity item) {
            RelativeLayout reView = helper.getView(R.id.item_button_layout);
            ImageView productImg = helper.getView(R.id.product_img);
            reView.setVisibility(View.GONE);
            helper.setText(R.id.product_name, item.getTitle());
            helper.setText(R.id.product_price, item.getPrice());
            helper.setText(R.id.product_norms, item.getBaseInfo());
            helper.setText(R.id.product_number, item.getNumber() + "");
            helper.setText(R.id.id_tv_information, item.getInfo());
            ImageLoader.getInstance().displayImage(item.getPic(), productImg, ImageLoadOptions.getOptions());
        }

    }
}
