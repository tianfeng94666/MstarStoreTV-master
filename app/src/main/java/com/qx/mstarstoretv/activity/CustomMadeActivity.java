package com.qx.mstarstoretv.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.Global;
import com.qx.mstarstoretv.fragment.FragOrderListFragment;
import com.qx.mstarstoretv.json.OrderWaitResult;
import com.qx.mstarstoretv.viewutils.BadgeView;
import com.qx.mstarstoretv.viewutils.IndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * 创建人：Yangshao
 * 创建时间：2016/9/23 15:48
 * @version    成品订单
 *
 */
public class CustomMadeActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, FragOrderListFragment.OnOderNumberChange {

    IndicatorView indicatorView;
    ViewPager viewPager;
    public List<FragOrderListFragment> fragmentList = new ArrayList<>();
    private static int SCREENWIDTH;
    List<TextView> tabTextViews = new ArrayList<>();

    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView ivRight;
    private FragOrderListFragment checkingFrament; //待审核
    private FragOrderListFragment productingFragment;//生产中
    private FragOrderListFragment sendingFragment;//已发货
    private FragOrderListFragment finishedFragment;//已完成

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_custom_made);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void loadNetData() {

    }

    public void onBack(View view) {
        if(Global.GO_HOEM){
            Global.GO_HOEM= false;
            openActivity(MainActivity.class,null);
        }else {
            openActivity(OrderActivity.class,null);
        }

        finish();
    }

    public BadgeView badge1, badge2, badge3, badge4;


    static List<BadgeView> list = new ArrayList<>();

    protected void initView() {
        titleText.setText("成品订单");
        checkingFrament = new FragOrderListFragment(1);
        fragmentList.add(checkingFrament);
        productingFragment = new FragOrderListFragment(2);
        fragmentList.add(productingFragment);
        sendingFragment = new FragOrderListFragment(3);
        fragmentList.add(sendingFragment);
        finishedFragment = new FragOrderListFragment(4);
        fragmentList.add(finishedFragment);
        for (FragOrderListFragment f : fragmentList) {
            f.setOnOderNumberChange(this);
        }

        SCREENWIDTH = getScreenWidth();
        indicatorView = (IndicatorView) findViewById(R.id.id_indicatorview);
        viewPager = (ViewPager) findViewById(R.id.order_viewpager);
        TextView tab = (TextView) findViewById(R.id.tab);
        TextView tab1 = (TextView) findViewById(R.id.tab1);
        TextView tab2 = (TextView) findViewById(R.id.tab2);
        TextView tab3 = (TextView) findViewById(R.id.tab3);

        TextView tv = (TextView) findViewById(R.id.tv);
        TextView tv1 = (TextView) findViewById(R.id.tv1);
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        TextView tv3 = (TextView) findViewById(R.id.tv3);

        FrameLayout id_fl_tab = (FrameLayout) findViewById(R.id.id_fr);
        FrameLayout id_fl_tab1 = (FrameLayout) findViewById(R.id.id_fr1);
        FrameLayout id_fl_tab2 = (FrameLayout) findViewById(R.id.id_fr2);
        FrameLayout id_fl_tab3 = (FrameLayout) findViewById(R.id.id_fr3);

        badge1 = new BadgeView(CustomMadeActivity.this, tab);// 创建一个BadgeView对象，view为你需要显示提醒的控件
        badge2 = new BadgeView(CustomMadeActivity.this, tab1);// 创建一个BadgeView对象，view为你需要显示提醒的控件
        badge3 = new BadgeView(CustomMadeActivity.this, tab2);// 创建一个BadgeView对象，view为你需要显示提醒的控件
        badge4 = new BadgeView(CustomMadeActivity.this, tab3);// 创建一个BadgeView对象，view为你需要显示提醒的控件


        tabTextViews.add(tv);
        tabTextViews.add(tv1);
        tabTextViews.add(tv2);
        tabTextViews.add(tv3);
        id_fl_tab.setOnClickListener(this);
        id_fl_tab1.setOnClickListener(this);
        id_fl_tab2.setOnClickListener(this);
        id_fl_tab3.setOnClickListener(this);
        CommentListPagerAdapter adapter = new CommentListPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(3);

        //显示第几个Fragment
        int pagerNumber = getIntent().getIntExtra("pageNumber", 0);
        viewPager.setCurrentItem(pagerNumber);
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setOnClickListener(this);
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
        indicatorView.setX(SCREENWIDTH / 4 * (position + positionOffset));
    }

    @Override
    public void onPageSelected(int position) {
        indicatorView.setX(SCREENWIDTH / 4 * position);
        setTxtColor(tabTextViews.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setTxtColor(TextView textView) {
        for (int i = 0; i < tabTextViews.size(); i++) {
            tabTextViews.get(i).setTextColor(getResources().getColor(R.color.text_color2));
        }
        textView.setTextColor(getResources().getColor(R.color.theme_color));
    }

    private int getScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_fr:
                viewPager.setCurrentItem(0);
                break;
            case R.id.id_fr1:
                viewPager.setCurrentItem(1);
                break;
            case R.id.id_fr2:
                viewPager.setCurrentItem(2);
                break;
            case R.id.id_fr3:
                viewPager.setCurrentItem(3);
                break;
            case R.id.tv_right:
                search();
                break;
        }
    }

    private void search() {
        openActivity(SearchOrderActivity.class, null);
    }

    @Override
    public void onFragOrderCount(OrderWaitResult.DataBean.StatusCountBean statusCountBean) {
        setBadge(statusCountBean.getWaitForValidate(), badge1);
        setBadge(statusCountBean.getProduceding(), badge2);
        setBadge(statusCountBean.getWaitForSend(), badge3);
        setBadge(statusCountBean.getFinished(), badge4);
    }

    public void setBadge(int payNum, BadgeView badge) {
        if (payNum != 0) {
            remind(Integer.valueOf(payNum), badge, true);
        } else {
            remind(Integer.valueOf(payNum), badge, false);
        }
    }

    @Override
    public void onFragProduCount(int deliverNum) {

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


}
