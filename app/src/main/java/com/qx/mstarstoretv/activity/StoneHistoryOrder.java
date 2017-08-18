package com.qx.mstarstoretv.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.fragment.StoneOrderFragemnt;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class StoneHistoryOrder extends BaseActivity {
    @Bind(R.id.tab)
    TabLayout tab;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    private StoneOrderFragemnt waittingPayFrament; //待支付
    private StoneOrderFragemnt payedFragment;//支付
    private StoneOrderFragemnt sendingFragment;//已发货
    private StoneOrderFragemnt finishedFragment;//已完成
    public List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stone_stone_histoty_order);
        ButterKnife.bind(this);
        initView();
    }
    public void onBack() {
        openActivity(StoneSearchInfoActivity.class,null);
        finish();
    }
    private void initView() {
        waittingPayFrament = new StoneOrderFragemnt(1);
        fragmentList.add(waittingPayFrament);
        payedFragment = new StoneOrderFragemnt(2);
        fragmentList.add(payedFragment);
        sendingFragment = new StoneOrderFragemnt(3);
        fragmentList.add(sendingFragment);
        finishedFragment = new StoneOrderFragemnt(4);
        fragmentList.add(finishedFragment);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        tab.addTab(tab.newTab().setText("1"));
        tab.addTab(tab.newTab().setText("2"));
        tab.addTab(tab.newTab().setText("3"));
        tab.addTab(tab.newTab().setText("4"));
        //添加页卡标题
        mTitleList.add("待支付");
        mTitleList.add("已付款");
        mTitleList.add("已发货");
        mTitleList.add("已完成");
        tab.setupWithViewPager(viewPager);
        tab.setTabsFromPagerAdapter(pagerAdapter);
        idIgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
        titleText.setText("裸石订单");
    }

    @Override
    public void loadNetData() {

    }

    public class PagerAdapter extends FragmentPagerAdapter {
        private List<?> fragments;

        public PagerAdapter(FragmentManager fm, List<?> fragments) {
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

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);//页卡标题
        }
    }
}
