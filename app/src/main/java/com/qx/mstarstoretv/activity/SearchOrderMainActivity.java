package com.qx.mstarstoretv.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.fragment.DeliveryFragment;
import com.qx.mstarstoretv.fragment.FinishFragment;
import com.qx.mstarstoretv.fragment.ProductingFragment;
import com.qx.mstarstoretv.json.SearchOrderMainResult;
import com.qx.mstarstoretv.net.OKHttpRequestUtils;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.viewutils.BadgeView;
import com.qx.mstarstoretv.viewutils.IndicatorView;
import com.qx.mstarstoretv.viewutils.LoadingWaitDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class SearchOrderMainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    IndicatorView indicatorView;
    ViewPager viewPager;
    public List<Fragment> fragmentList = new ArrayList<>();
    private static int SCREENWIDTH;
    List<TextView> tabTextViews = new ArrayList<>();

    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView ivRight;
    @Bind(R.id.ll_waiting_check)
    LinearLayout llWaitingCheck;
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tab1)
    TextView tab1;
    @Bind(R.id.id_fr1)
    FrameLayout idFr1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tab2)
    TextView tab2;
    @Bind(R.id.id_fr2)
    FrameLayout idFr2;
    @Bind(R.id.tv3)
    TextView tv3;
    @Bind(R.id.tab3)
    TextView tab3;
    @Bind(R.id.id_fr3)
    FrameLayout idFr3;
    @Bind(R.id.id_indicatorview)
    IndicatorView idIndicatorview;
    @Bind(R.id.order_viewpager)
    ViewPager orderViewpager;
    private ProductingFragment productingFragment;//生产中
    private DeliveryFragment sendingFragment;//已发货
    private FinishFragment finishedFragment;//已完成
    String orderNum;
    private SearchOrderMainResult.DataBean.OrderProduceBean orderProduceBean;
    private SearchOrderMainResult.DataBean.OrderSendedBean orderSendedBean;
    public BadgeView badge1, badge2, badge3, badge4;
    private LoadingWaitDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search_main);
        ButterKnife.bind(this);
        llWaitingCheck.setVisibility(View.GONE);
        getData();
        loadNetData();

    }

    private void getData() {
        Intent intent = getIntent();
        orderNum = intent.getStringExtra("orderNum");
    }

    public void showWatiNetDialog() {
        dialog = new LoadingWaitDialog(this);
        dialog.show();
    }

    public void dismissWatiNetDialog() {
        if (dialog != null) {
            dialog.cancel();
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void loadNetData() {
        showWatiNetDialog();
        String url = AppURL.URL_CODE_ORDER_SEARCH_DETAIL + "tokenKey=" + BaseApplication.getToken() + "&orderNum=" + orderNum;
//        String url ="http://appapi1.fanerweb.com/api/aproxy/ModelOrderSearchDetail?tokenKey=7cdcf3a6a47904dbff1e7da86b8ef225&orderNum=AP170310360";
        L.e("url" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                dismissWatiNetDialog();
                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    SearchOrderMainResult searchOrderMainResult = new Gson().fromJson(result, SearchOrderMainResult.class);
                    if (searchOrderMainResult.getData() != null) {
                        orderProduceBean = searchOrderMainResult.getData().getOrderProduce();
                        orderSendedBean = searchOrderMainResult.getData().getOrderSended();
                        if(orderSendedBean==null&&orderProduceBean==null){
                            AlertDialog.Builder builder = new AlertDialog.Builder(SearchOrderMainActivity.this)
                                    .setTitle("提示")
                                    .setMessage("后台没有数据！")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            SearchOrderMainActivity.this.finish();

                                        }
                                    });

                            builder.setCancelable(false);//点击外部不会消失
                            builder.show();
                        }else {
                            initView();
                        }
                    }

                } else if (error == 2) {
                    loginToServer(SearchResultActivity.class);
                } else {
                    ToastManager.showToastReal(OKHttpRequestUtils.getmInstance().getErrorMsg(result));
                }
            }

            @Override
            public void onFail(String fail) {
                dismissWatiNetDialog();
            }
        });
    }

    public void onBack(View view) {
        finish();
    }


    static List<BadgeView> list = new ArrayList<>();

    protected void initView() {
        titleText.setText("搜索结果");

        productingFragment = new ProductingFragment();
        fragmentList.add(productingFragment);
        sendingFragment = new DeliveryFragment();
        fragmentList.add(sendingFragment);
        finishedFragment = new FinishFragment();
        fragmentList.add(finishedFragment);


        SCREENWIDTH = getScreenWidth();
        indicatorView = (IndicatorView) findViewById(R.id.id_indicatorview);
        indicatorView.setNum(3);
        viewPager = (ViewPager) findViewById(R.id.order_viewpager);

        TextView tab1 = (TextView) findViewById(R.id.tab1);
        TextView tab2 = (TextView) findViewById(R.id.tab2);
        TextView tab3 = (TextView) findViewById(R.id.tab3);

        TextView tv1 = (TextView) findViewById(R.id.tv1);
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        TextView tv3 = (TextView) findViewById(R.id.tv3);

        FrameLayout id_fl_tab = (FrameLayout) findViewById(R.id.id_fr);
        id_fl_tab.setVisibility(View.GONE);
        FrameLayout id_fl_tab1 = (FrameLayout) findViewById(R.id.id_fr1);
        FrameLayout id_fl_tab2 = (FrameLayout) findViewById(R.id.id_fr2);
        FrameLayout id_fl_tab3 = (FrameLayout) findViewById(R.id.id_fr3);


        badge2 = new BadgeView(SearchOrderMainActivity.this, tab1);// 创建一个BadgeView对象，view为你需要显示提醒的控件
        badge3 = new BadgeView(SearchOrderMainActivity.this, tab2);// 创建一个BadgeView对象，view为你需要显示提醒的控件
        badge4 = new BadgeView(SearchOrderMainActivity.this, tab3);// 创建一个BadgeView对象，view为你需要显示提醒的控件


        tabTextViews.add(tv1);
        tabTextViews.add(tv2);
        tabTextViews.add(tv3);

        id_fl_tab1.setOnClickListener(this);
        id_fl_tab2.setOnClickListener(this);
        id_fl_tab3.setOnClickListener(this);
        CommentListPagerAdapter adapter = new CommentListPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(1);
        int pagerNumber;
        //显示第几个Fragment
        if (orderProduceBean.getModelList() != null) {
            pagerNumber = getIntent().getIntExtra("pageNumber", 0);
        } else if (orderProduceBean.getModelList() == null && orderSendedBean != null) {
            pagerNumber = getIntent().getIntExtra("pageNumber", 1);
        } else {
            pagerNumber = getIntent().getIntExtra("pageNumber", 0);
        }
        viewPager.setCurrentItem(pagerNumber);
//        ivRight.setVisibility(View.VISIBLE);
//        ivRight.setOnClickListener(this);
    }


    private static void remind(int count, BadgeView badge, boolean isVisible) {
        //BadgeView的具体使用
        System.out.println("count=" + count + ",badge=" + badge);
        badge.setText(count + ""); // 需要显示的提醒类容
        badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);// 显示的位置.右上角,BadgeView.POSITION_BOTTOM_LEFT,下左，还有其他几个属性
        badge.setTextColor(Color.WHITE); // 文本颜色
        int hint = Color.rgb(200, 39, 73);
        badge.setBadgeBackgroundColor(hint); // 提醒信息的背景颜色，自己设置
        badge.setTextSize(15); // 文本大小
        badge.setBadgeMargin(3, 3); // 水平和竖直方向的间距
        badge.setBadgeMargin(5); //各边间隔
        if (isVisible) {
            badge.show();// 只有显示
        } else {
            badge.hide();//影藏显示
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        indicatorView.setX(SCREENWIDTH / 3 * (position + positionOffset));
    }

    @Override
    public void onPageSelected(int position) {
        indicatorView.setX(SCREENWIDTH / 3 * position);
        setTxtColor(tabTextViews.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setTxtColor(TextView textView) {
        for (int i = 0; i < tabTextViews.size(); i++) {
            tabTextViews.get(i).setTextColor(getResources().getColor(R.color.text_color2));
        }
        textView.setTextColor(getResources().getColor(R.color.theme_red));
    }

    private int getScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.id_fr1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.id_fr2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.id_fr3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.tv_right:
                search();
                break;
        }
    }

    private void search() {
        openActivity(SearchOrderActivity.class, null);
    }


    public void setBadge(int payNum, BadgeView badge) {
        if (payNum != 0) {
            remind(Integer.valueOf(payNum), badge, true);
        } else {
            remind(Integer.valueOf(payNum), badge, false);
        }
    }



    public class CommentListPagerAdapter extends FragmentPagerAdapter {
        private List<?> fragments;

        public CommentListPagerAdapter(FragmentManager fm, List<?> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return (Fragment) fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public SearchOrderMainResult.DataBean.OrderProduceBean getOrderProduceBean() {
        return orderProduceBean;
    }

    public void setOrderProduceBean(SearchOrderMainResult.DataBean.OrderProduceBean orderProduceBean) {
        this.orderProduceBean = orderProduceBean;
    }

    public SearchOrderMainResult.DataBean.OrderSendedBean getOrderSendedBean() {
        return orderSendedBean;
    }

    public void setOrderSendedBean(SearchOrderMainResult.DataBean.OrderSendedBean orderSendedBean) {
        this.orderSendedBean = orderSendedBean;
    }
}
