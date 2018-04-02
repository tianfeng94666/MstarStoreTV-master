package com.qx.mstarstoretv.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.adapter.StoneSearchResultAdapter;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.Global;
import com.qx.mstarstoretv.bean.Ring;
import com.qx.mstarstoretv.json.StoneSearchInfo;
import com.qx.mstarstoretv.json.StoneSearchInfoResult;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.viewutils.LeftPopupWindow;
import com.qx.mstarstoretv.viewutils.LoadingWaitDialog;
import com.qx.mstarstoretv.viewutils.xListView.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class StoneSearchResultActivity extends Activity implements View.OnClickListener, XListView.IXListViewListener, StoneSearchResultAdapter.ChooseItemInterface {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.lv_stone)
    XListView lvStone;
    @Bind(R.id.tv_quoted_price_all)
    TextView tvQutedPriceAll;
    @Bind(R.id.tv_ischeck_stone)
    TextView tvIscheckStone;
    @Bind(R.id.tv_item_weight)
    TextView tvItemWeight;
    @Bind(R.id.tv_item_shape)
    TextView tvItemShape;
    @Bind(R.id.tv_item_color)
    TextView tvItemColor;
    @Bind(R.id.tv_item_purity)
    TextView tvItemPurity;
    @Bind(R.id.tv_item_cut)
    TextView tvItemCut;
    @Bind(R.id.tv_item_polish)
    TextView tvItemPolish;
    @Bind(R.id.tv_item_symmetric)
    TextView tvItemSymmetric;
    @Bind(R.id.tv_item_fluorescence)
    TextView tvItemFluorescence;
    @Bind(R.id.tv_item_certauth)
    TextView tvItemCertauth;
    @Bind(R.id.tv_item_quoted_price)
    TextView tvItemQuotedPrice;
    @Bind(R.id.ll_title)
    LinearLayout llTitle;
    @Bind(R.id.tv_search_target)
    TextView tvSearchTarget;
    @Bind(R.id.tv_item_price)
    TextView tvItemPrice;
    @Bind(R.id.tv_item_cerauth_number)
    TextView tvItemCerauthNumber;
    @Bind(R.id.tv_reset)
    TextView tvReset;
    @Bind(R.id.tv_place_order)
    TextView tvPlaceOrder;
    @Bind(R.id.tv_confirm_reback)
    TextView tvConfirmReback;
    @Bind(R.id.tv_empty)
    TextView tvEmpty;
    @Bind(R.id.ll_from_search)
    LinearLayout llFromSearch;
    @Bind(R.id.tv_choose_product)
    TextView tvChooseProduct;
    @Bind(R.id.tv_pager_amount)
    TextView tvPagerAmount;
    @Bind(R.id.tv_comfirm_stone)
    TextView tvComfirmStone;
    @Bind(R.id.ll_confirm_stone)
    RelativeLayout llConfirmStone;
    @Bind(R.id.ll_show_less)
    LinearLayout llShowLess;
    @Bind(R.id.rl_stone_search_result)
    RelativeLayout rlStoneSearchResult;
    @Bind(R.id.tv_reset2)
    TextView tvReset2;


    private boolean isLandscape;
    private StoneSearchInfo stoneSearchInfo;
    int page = 1;
    private StoneSearchInfoResult stoneSearchInfoResult;
    List<StoneSearchInfoResult.DataBean.StoneBean.ListBean> list = new ArrayList<>();
    private StoneSearchResultAdapter stoneSearchResultAdapter;
    private static final int PULL_REFRESH = 1;
    private static final int PULL_LOAD = 2;
    private int pullStatus;
    private int listCount;
    private List<String> listTitle;
    private String orderby = "";
    int orderByWeightTimes = 0;//重量排序次数
    int orderByPriceTiems = 0;//价钱排序次数
    private int openType;//0 是正常进入，1是主石进入
    private String itemId;//产品的id
    private int type;
    private boolean isShowPrice;
    private boolean isCustomized;
    private double totalAmount;
    private LeftPopupWindow leftPopupWindow;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stone_search_result);
        ButterKnife.bind(this);
        isShowPrice = SpUtils.getInstace(this).getBoolean("isShowStonePrice", true);
        isCustomized = SpUtils.getInstace(this).getBoolean("isCustomized", true);
        getDate();
        init();

    }


    private void getDate() {
        Intent intent = getIntent();
        openType = intent.getIntExtra("openType", 0);
        itemId = intent.getStringExtra("itemId");
        orderId = intent.getStringExtra("orderId");
        type = intent.getIntExtra("type", 0);
        Bundle stoneBundle = null;
        Bundle bundle = intent.getBundleExtra("stoneInfo");
        stoneSearchInfo = (StoneSearchInfo) bundle.getSerializable("searchStoneInfo");
        stoneSearchInfoResult = (StoneSearchInfoResult) getLastNonConfigurationInstance();
        if (stoneSearchInfoResult != null) {
            setXListview(stoneSearchInfoResult);
        } else {
            loadNetData();
        }
    }

    private void changeOrientation() {

        if (!isLandscape) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            isLandscape = true;
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            isLandscape = false;
        }
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        return stoneSearchInfoResult;
    }

    private void init() {
        //来自个人定制type=11
        if (openType == 1||type>2) {
            tvQutedPriceAll.setVisibility(View.GONE);
            tvPlaceOrder.setVisibility(View.GONE);
            tvConfirmReback.setVisibility(View.VISIBLE);
            tvChooseProduct.setVisibility(View.GONE);
        } else {
            tvQutedPriceAll.setVisibility(View.VISIBLE);
            tvPlaceOrder.setVisibility(View.VISIBLE);
            tvConfirmReback.setVisibility(View.GONE);
            tvChooseProduct.setVisibility(View.VISIBLE);
        }

        tvReset2.setOnClickListener(this);
        idIgBack.setOnClickListener(this);
        lvStone.setXListViewListener(this);
        lvStone.setAutoLoadEnable(false);
        lvStone.setPullRefreshEnable(false);
        lvStone.setPullLoadEnable(true);
        titleText.setText("搜索结果");
        tvItemWeight.setOnClickListener(this);
        tvItemPrice.setOnClickListener(this);
        tvReset.setOnClickListener(this);
        tvConfirmReback.setOnClickListener(this);
        tvChooseProduct.setOnClickListener(this);
        tvComfirmStone.setOnClickListener(this);
        if (isShowPrice) {
            tvPlaceOrder.setOnClickListener(this);
            tvQutedPriceAll.setOnClickListener(this);
            tvPlaceOrder.setTextColor(getResources().getColor(R.color.white));
            tvQutedPriceAll.setTextColor(getResources().getColor(R.color.white));
        } else {
            tvPlaceOrder.setTextColor(getResources().getColor(R.color.gray));
            tvQutedPriceAll.setTextColor(getResources().getColor(R.color.gray));
        }

        if (Global.isShowPopup != 0) {
            llShowLess.setVisibility(View.VISIBLE);
            llConfirmStone.setVisibility(View.VISIBLE);
            llFromSearch.setVisibility(View.GONE);
            initPopwindow();

        } else {
            llShowLess.setVisibility(View.GONE);
            llConfirmStone.setVisibility(View.GONE);
            llFromSearch.setVisibility(View.VISIBLE);
        }
    }

    private void initPopwindow() {
        leftPopupWindow = new LeftPopupWindow(this);
        llShowLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftPopupWindow.showPop(rlStoneSearchResult);
            }
        });
    }

    private void initTitleView() {
        StoneSearchInfoResult.DataBean dataBean = stoneSearchInfoResult.getData();
        tvRight.setOnClickListener(this);
        listTitle = dataBean.getStone().getHeadline();
        if (listTitle == null) {
            return;
        }
        tvIscheckStone.setText(listTitle.get(0));
        tvItemWeight.setText(listTitle.get(1));

        if (isShowPrice) {
            tvItemPrice.setVisibility(View.VISIBLE);
        } else {
            tvItemPrice.setVisibility(View.GONE);
        }

        tvItemPrice.setText(listTitle.get(2));
        tvItemShape.setText(listTitle.get(3));
        tvItemColor.setText(listTitle.get(4));
        tvItemPurity.setText(listTitle.get(5));
        tvItemCut.setText(listTitle.get(6));
        tvItemPolish.setText(listTitle.get(7));
        tvItemSymmetric.setText(listTitle.get(8));
        tvItemFluorescence.setText(listTitle.get(9));
        tvItemCertauth.setText(listTitle.get(10));
        tvItemCerauthNumber.setText(listTitle.get(11));
    }

    public void loadNetData() {
        baseShowWatLoading();
        String url = "";
        url = AppURL.URL_STONE_LIST + "tokenKey=" + BaseApplication.getToken() + "&cpage=" + page + "&certAuth=" + stoneSearchInfo.getCerAuth() + "&color=" + stoneSearchInfo.getColor() + "&shape=" + stoneSearchInfo.getShape()
                + "&purity=" + stoneSearchInfo.getPurity() + "&cut=" + stoneSearchInfo.getCut() + "&polishing=" + stoneSearchInfo.getPolishing() + "&symmetric=" + stoneSearchInfo.getSymmetric() + "&fluorescence=" + stoneSearchInfo.getFluorescence()
                + "&price=" + stoneSearchInfo.getPrice() + "&weight=" + stoneSearchInfo.getWeight() + "&orderby=" + orderby;
        if (StringUtils.isEmpty(url)) {
            return;
        }
        L.e("获取地址" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                baseHideWatLoading();
                L.e("result" + result);
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    stoneSearchInfoResult = new Gson().fromJson(result, StoneSearchInfoResult.class);
                    if (stoneSearchInfoResult.getData() == null) {
                        ToastManager.showToastReal("没有数据！");
                        return;
                    }
                    totalAmount = Integer.parseInt(stoneSearchInfoResult.getData().getStone().getList_count());
                    init();
                    setXListview(stoneSearchInfoResult);

                } else if (error.equals("2")) {

                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    showToastReal("数据加载错误:+" + message);
                }
            }

            @Override
            public void onFail(String fail) {
                showToastReal("数据获取失败");
                baseHideWatLoading();
            }

        });
    }

    private void setXListview(StoneSearchInfoResult stoneSearchInfoResult) {
        if (stoneSearchInfoResult.getData().getStone().getList() == null) {
            ToastManager.showToastReal("数据为空！");
            lvStone.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
            return;
        }
        if (stoneSearchInfoResult.getData().getStone().getList().size() == 0) {
            lvStone.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            lvStone.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
        }

        List<StoneSearchInfoResult.DataBean.StoneBean.ListBean> templist = stoneSearchInfoResult.getData().getStone().getList();
        listCount = Integer.parseInt(stoneSearchInfoResult.getData().getStone().getList_count());
        list.addAll(templist);
        if (listTitle == null) {
            initTitleView();
        }
        String st = stoneSearchInfoResult.getData().getStone().getSearchKey();
        if (!st.equals("")) {
            tvSearchTarget.setVisibility(View.VISIBLE);
            tvSearchTarget.setText(st);
        } else {
            tvSearchTarget.setVisibility(View.GONE);
        }


        if (pullStatus == PULL_LOAD) {
            stoneSearchResultAdapter.notifyDataSetChanged();
        } else {
            stoneSearchResultAdapter = new StoneSearchResultAdapter(list, StoneSearchResultActivity.this, isShowPrice);
            lvStone.setAdapter(stoneSearchResultAdapter);
        }
        lvStone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list.get(position - 1).setIscheck(!list.get(position - 1).ischeck());
                stoneSearchResultAdapter.notifyDataSetChanged();
            }
        });
        tvPagerAmount.getBackground().setAlpha(100);
        lvStone.setOnScrollListener(new XListView.OnXScrollListener() {
            @Override
            public void onXScrolling(View view) {

            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                System.out.println("firstVisibleItem=" + firstVisibleItem);
                if (firstVisibleItem == 0) {
                    firstVisibleItem = 1;
                }

                tvPagerAmount.setText((int) (Math.ceil(firstVisibleItem / 30.0)) + "/" + (int) Math.ceil(listCount / 30.0));

            }
        });
        lvStone.stopRefresh();
        lvStone.stopLoadMore();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right:
                changeOrientation();
                break;
            case R.id.id_ig_back:
                finish();
                break;
            case R.id.tv_quoted_price_all:
                if (list.size() != 0) {
                    quotedPrice(stoneSearchResultAdapter.getQuotedPriceId());
                }
                break;
            case R.id.tv_item_weight:
                setorderByWeight();
                break;
            case R.id.tv_item_price:
                setOrderByPrice();
                break;
            case R.id.tv_reset:
                stoneSearchInfoResult = null;
                list.clear();
                loadNetData();
                break;
            case R.id.tv_reset2:
                stoneSearchInfoResult = null;
                list.clear();
                loadNetData();
                break;
            case R.id.tv_place_order:
                stonePlaceOrder(stoneSearchResultAdapter.getQuotedPriceId());
                break;
            case R.id.tv_confirm_reback:
                rebackProductInfo();
                break;
            case R.id.tv_choose_product:
                chooseProduct();
                break;
            case R.id.tv_comfirm_stone:
                chooseStone();
                break;
        }
    }

    private void chooseStone() {
        int chooseAmount = 0;
        int seletPosition = 0;
        if (list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            StoneSearchInfoResult.DataBean.StoneBean.ListBean listBean = list.get(i);
            if (listBean.ischeck()) {
                chooseAmount++;
                seletPosition = i;
            }
        }
        if (list.get(seletPosition).getCertCode().isEmpty()) {
            showToastReal("不能选择没有证书的裸石！");
            return;
        }
        if (chooseAmount == 0) {
            showToastReal("您忘记了石头，请选择一个！");
        } else if (chooseAmount == 1) {
            StoneSearchInfoResult.DataBean.StoneBean.ListBean listBean = list.get(seletPosition);
            if (Global.ring == null) {
                Global.ring = new Ring();
            }
            Global.ring.setStoneEntity(listBean);
            if (leftPopupWindow != null) {
                leftPopupWindow.showPop(rlStoneSearchResult);
            }
            if (Global.ring.getItemId() == null) {
                showToastReal("请选择戒托");
                return;
            }
            if (Global.ring.getAddressEntity() == null || Global.ring.getCustomerEntity() == null) {
                showToastReal("请选择信息");
                return;
            }
            showToastReal("请确认定制");
        } else {
            showToastReal("主石只能有一个！");
        }

    }

    private void setOrderByPrice() {
        list.clear();
        page = 1;
        pullStatus = 0;
        orderByWeightTimes = 0;
        orderByPriceTiems++;
        resetOrderImage(tvItemWeight);
        Drawable drawable = null;
        switch (orderByPriceTiems % 3) {
            case 0:
                orderby = "";
                drawable = this.getResources().getDrawable(R.drawable.icon_sort);
                break;
            case 1:
                orderby = "price_asc";
                drawable = this.getResources().getDrawable(R.drawable.icon_sort_d);
                break;
            case 2:
                orderby = "price_desc";
                drawable = this.getResources().getDrawable(R.drawable.icon_sort_u);
                break;
        }
        loadNetData();
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        tvItemPrice.setCompoundDrawables(null, null, drawable, null);
    }

    private void resetOrderImage(TextView tvItemWeight) {
        Drawable drawable = null;
        drawable = this.getResources().getDrawable(R.drawable.icon_sort);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        tvItemWeight.setCompoundDrawables(null, null, drawable, null);
    }


    private void chooseProduct() {
        int chooseAmount = 0;
        int seletPosition = 0;
        if (list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            StoneSearchInfoResult.DataBean.StoneBean.ListBean listBean = list.get(i);
            if (listBean.ischeck()) {
                chooseAmount++;
                seletPosition = i;
            }
        }
        if (list.get(seletPosition).getCertCode().isEmpty()) {
            showToastReal("不能选择没有证书的裸石！");
            return;
        }
        if (chooseAmount == 0) {
            showToastReal("您忘记了石头，请选择一个！");
        } else if (chooseAmount == 1) {
            Intent intent;
            intent = new Intent(this, OrderActivity.class);
            Bundle pBundle = new Bundle();
            pBundle.putString("openType", "1");
            StoneSearchInfoResult.DataBean.StoneBean.ListBean listBean = list.get(seletPosition);
            pBundle.putSerializable("stone", listBean);
            intent.putExtras(pBundle);
            startActivity(intent);
            //设置切换动画，从右边进入，左边退出
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        } else {
            showToastReal("主石只能有一个！");
        }


    }

    private void rebackProductInfo() {
        int chooseAmount = 0;
        int seletPosition = 0;
        if (list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            StoneSearchInfoResult.DataBean.StoneBean.ListBean listBean = list.get(i);
            if (listBean.ischeck()) {
                chooseAmount++;
                seletPosition = i;
            }
        }
        if (list.get(seletPosition).getCertCode().isEmpty()) {
            showToastReal("不能选择没有证书的裸石！");
            return;
        }
        if (chooseAmount == 0) {
            showToastReal("您忘记了石头，请选择一个！");
        } else if (chooseAmount == 1) {
            Intent intent;
            //判断是否要去个人定制
            if(type >2){
                intent = new Intent(this, MakingActivity.class);
            }else {
                if (!isCustomized) {
                    intent = new Intent(this, StyleInfromationActivity.class);
                } else {
                    intent = new Intent(this, SimpleStyleInfromationActivity.class);
                }
            }

            Bundle pBundle = new Bundle();
            pBundle.putString("itemId", itemId);
            pBundle.putString("orderId", orderId);
            pBundle.putInt("type", type);
            pBundle.putString("openType", openType + "");
            StoneSearchInfoResult.DataBean.StoneBean.ListBean listBean = list.get(seletPosition);
            pBundle.putSerializable("stone", listBean);
            intent.putExtras(pBundle);
            startActivity(intent);
            //设置切换动画，从右边进入，左边退出
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        } else {
            showToastReal("主石只能有一个！");
        }

    }

    private Toast toast = null;

    public void showToastReal(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    private void stonePlaceOrder(String id) {
        if (id.equals("")) {
            showToastReal("您未选择产品！");
        } else {
            Intent intent = new Intent(this, ConfirmStoneOrderActivity.class);
            intent.putExtra("itemId", id);
            intent.putExtra("percent", stoneSearchInfo.getPercent());
            intent.putExtra("type", 0);
            startActivity(intent);
            //设置切换动画，从右边进入，左边退出
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        }
    }

    private void setorderByWeight() {
        list.clear();
        page = 1;
        pullStatus = 0;
        orderByPriceTiems = 0;
        orderByWeightTimes++;
        resetOrderImage(tvItemPrice);
        Drawable drawable = null;
        switch (orderByWeightTimes % 3) {
            case 0:
                orderby = "";
                drawable = this.getResources().getDrawable(R.drawable.icon_sort);
                break;
            case 1:
                orderby = "weight_asc";
                drawable = this.getResources().getDrawable(R.drawable.icon_sort_d);
                break;
            case 2:
                orderby = "weight_desc";
                drawable = this.getResources().getDrawable(R.drawable.icon_sort_u);
                break;
        }
        loadNetData();
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        tvItemWeight.setCompoundDrawables(null, null, drawable, null);
    }


    @Override
    public void onRefresh() {
        page = 1;
        list.clear();
        pullStatus = PULL_REFRESH;
        loadNetData();
        lvStone.stopRefresh();

    }

    @Override
    public void onLoadMore() {
        if (listCount > stoneSearchResultAdapter.getCount()) {
            page++;
            pullStatus = PULL_LOAD;
            loadNetData();
        } else {
            ToastManager.showToastReal("没有更多数据");
            lvStone.stopLoadMore();
            lvStone.stopRefresh();
        }
    }

    @Override
    public void quotedPrice(String id) {
        if (id.equals("")) {
            ToastManager.showToastReal("您未选择产品！");
        } else {
            Intent intent = new Intent(this, StoneQuotedPriceActivity.class);
            intent.putExtra("stoneIds", id);
            intent.putExtra("percent", stoneSearchInfo.getPercent());
            startActivity(intent);
            //设置切换动画，从右边进入，左边退出
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        }

    }

    private LoadingWaitDialog loadingDialog;

    protected void baseShowWatLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingWaitDialog(this, getString(R.string.pull_to_refresh_footer_refreshing_label));
            loadingDialog.show();
        }
        //SystemClock.sleep(1000);
    }

    public void baseHideWatLoading() {
        if (loadingDialog == null) return;
        if (loadingDialog != null || loadingDialog.isShowing()) {
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }

    protected void openActivity(Class<?> pClass, String key, int st) {
        Intent intent = new Intent(this, pClass);
        intent.putExtra(key, st);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }
}
