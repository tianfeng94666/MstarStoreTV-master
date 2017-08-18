package com.qx.mstarstoretv.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.SelectDotView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * 创建人：Yangshao
 * 创建时间：2016/9/20 15:43
 * @version     成品信息页面
 *
 */
public class EndProducActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.index_product_images_container)
    ViewPager prroductIgContainer;
    @Bind(R.id.index_product_images_indicator)
    LinearLayout newsDotsContainer;
    @Bind(R.id.banner_layout)
    FrameLayout bannerLayout;
    @Bind(R.id.tv_product_name)
    TextView tvProductName;
    @Bind(R.id.share_btn)
    TextView shareBtn;
    @Bind(R.id.tv_sum_price)
    TextView tvPrice;
    @Bind(R.id.tv_remark)
    TextView idTvPrice;
    @Bind(R.id.btn_commodity_minus)
    ImageView btnCommodityMinus;
    @Bind(R.id.btn_commodity_number_display)
    TextView btnCommodityNumberDisplay;
    @Bind(R.id.btn_commodity_plus)
    ImageView btnCommodityPlus;
    SelectDotView newsDots;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    public static final int SCALE = 300;
    private List<ImageView> images;
    private int currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end_produc);
        ButterKnife.bind(this);
        initView();
        initImages();
        initNewsViewParams();
    }

    @Override
    public void loadNetData() {

    }

    private void initNewsViewParams() {
        newsDots = new SelectDotView(this);
        newsDots.setDotNum(images.size());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        newsDotsContainer.removeAllViews();
        newsDotsContainer.addView(newsDots, lp);

        prroductIgContainer.setAdapter(pagerAdapter);
        currentTab = images.size() * SCALE / 2;
        prroductIgContainer.setCurrentItem(currentTab, false);
        prroductIgContainer.setOnPageChangeListener(pageChangeListener);

    }

    private void initImages() {
        images = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ImageView image = new ImageView(this) {
                @Override
                protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                    int widthSize = getResources().getDisplayMetrics().widthPixels;
                    int heightSize = UIUtils.dip2px(180);
                    setMeasuredDimension(widthSize, heightSize);
                }
            };
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            images.add(image);
        }
    }


    private PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public int getCount() {
            return images == null ? 0 : images.size() * SCALE;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(images.get(position % images.size()));
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            try {
                container.addView(images.get(position % images.size()));
            } catch (Exception e) {

            }
            images.get(position % images.size()).setImageResource(R.drawable.no_image);
            //ImageLoader.getInstance().displayImage(homePageItem.pic.get(position % images.size()), images.get(position % images.size()), ImageLoadOptions.getOptions());
            /**
             * 添加轮播点击事件
             */
            images.get(position % images.size()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ToastUtils.showToastReal(position % images.size());
                }
            });
            return images.get(position % images.size());
        }
    };

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            currentTab = i;
            newsDots.setSelectedDot(currentTab % images.size());
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public void onBack(View view) {
        L.e("成品信息");
        finish();
    }


    protected void initView() {
        titleText.setText("成品信息");
        btnCommodityMinus.setOnClickListener(this);
        btnCommodityPlus.setOnClickListener(this);
    }


    int stock;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_commodity_minus:
                if (stock>0){
                    stock--;
                }
                btnCommodityNumberDisplay.setText(stock+"");
                break;
            case R.id.btn_commodity_plus:
                if (stock<100){
                    stock++;
                }
                btnCommodityNumberDisplay.setText(stock+"");
                break;
        }
    }
}
