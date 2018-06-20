package com.qx.mstarstoretv.viewutils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.utils.ToastManager;

/**
 * @author wuzhen
 * @since 2017-06-06
 */
public class XGallery extends FrameLayout implements View.OnTouchListener {

    private ViewPager mViewPager;
    private BasePageTransformer mPageTransformer;

    private int mTouchSlop;
    private int mTapTimeout;

    private int mViewPagerWidth;
    private long mStartDownTime = -1;
    private boolean isClickMode;
    private PointF mStartPoint = new PointF();
    private static final int WHAT_AUTO_PLAY = 1000;
    //自动播放时间
    private int mAutoPalyTime = 4000;
    //图片长度
    private int dataSize = 1;


    public int mCurrentPositon;
    private BottomScalePageTransformer bottomScalePageTransformer = new BottomScalePageTransformer();
    private ScaleTranstormer scaleTranstormer = new ScaleTranstormer();
    private int type;

    public XGallery(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public XGallery(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public XGallery(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    private void init(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.XGallery, defStyleAttr, 0);
        int itemWidth = a.getDimensionPixelOffset(R.styleable.XGallery_xGallery_itemWidth, LayoutParams.MATCH_PARENT);
        int itemHeight = a.getDimensionPixelOffset(R.styleable.XGallery_xGallery_itemHeight, LayoutParams.MATCH_PARENT);
        type = a.getInteger(R.styleable.XGallery_xGallery_type, 0);
        a.recycle();

        mViewPager = new ViewPager(context);
        mViewPager.setClipChildren(false);
        mViewPager.setOverScrollMode(OVER_SCROLL_NEVER);
        mViewPager.setHorizontalScrollBarEnabled(false);
        mViewPager.setOffscreenPageLimit(6);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPositon = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (type == 0) {
            setPageTransformer(bottomScalePageTransformer);
        } else {
            setPageTransformer(scaleTranstormer);
        }


        LayoutParams params = new LayoutParams(itemWidth, itemHeight);
        params.gravity = Gravity.CENTER;
        addView(mViewPager, params);

        setClipChildren(false);
        setOnTouchListener(this);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mTapTimeout = ViewConfiguration.getTapTimeout();
        mViewPagerWidth = itemWidth;
    }


    private Handler mAutoPlayHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

//            mCurrentPositon = mViewPager.getCurrentItem();
            mCurrentPositon++;
            mViewPager.setCurrentItem(mCurrentPositon % dataSize, true);

            mAutoPlayHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, mAutoPalyTime);

        }
    };


    /**
     * 开始播放
     */
    public void startAutoPlay() {
        mAutoPlayHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, mAutoPalyTime);
    }

    /**
     * 停止播放
     */
    public void stopAutoPlay() {
        mAutoPlayHandler.removeMessages(WHAT_AUTO_PLAY);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartDownTime = SystemClock.uptimeMillis();
                mStartPoint.set(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                stopTouch();
                break;

            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    public void stopTouch() {
        float centerDistance = mStartPoint.x - getWidth() / 2.f;
        boolean isRightSide = centerDistance >= 0;
        int space = (int) (Math.abs(centerDistance) + mViewPagerWidth / 2.f) / mViewPagerWidth;
        int position = mViewPager.getCurrentItem() + (isRightSide ? space : -space);
        int currentItem = mViewPager.getCurrentItem();
        if (currentItem == position) { // 没有改变
            if (mOnGalleryPageSelectListener != null) {
                mOnGalleryPageSelectListener.onGalleryPageSelected(position);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mViewPager.getAdapter() == null || mViewPager.getAdapter().getCount() == 0) {
            return true;
        }

        final float x = event.getX();
        final float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopAutoPlay();
                isClickMode = true;
                mStartDownTime = SystemClock.uptimeMillis();
                mStartPoint.set(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - mStartPoint.x) > mTouchSlop || Math.abs(y - mStartPoint.y) > mTouchSlop) {
                    isClickMode = false;
                }

                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                startAutoPlay();
                if (isClickMode && SystemClock.uptimeMillis() - mStartDownTime <= mTapTimeout) {
                    performClickItem(mStartPoint.x);
                }
                isClickMode = false;
                mStartDownTime = -1;
                break;

            default:
                break;
        }
        return mViewPager.dispatchTouchEvent(event);
    }


    // 处理Item的点击事件
    private void performClickItem(float x) {
        float centerDistance = x - getWidth() / 2.f;
        boolean isRightSide = centerDistance >= 0;
        int space = (int) (Math.abs(centerDistance) + mViewPagerWidth / 2.f) / mViewPagerWidth;
        int position = mViewPager.getCurrentItem() + (isRightSide ? space : -space);
        if (position < 0 || position >= mViewPager.getAdapter().getCount()) {
            return;
        }

        int currentItem = mViewPager.getCurrentItem();
        if (currentItem == position) { // 没有改变
            return;
        }

        mViewPager.setCurrentItem(position, true);
        if (mOnGalleryPageSelectListener != null) {
            mOnGalleryPageSelectListener.onGalleryPageSelected(position);
        }
    }

    /**
     * 设置ViewPager额外显示的最大页数。
     *
     * @param limit 页数
     */
    public void setPageOffscreenLimit(int limit) {
        mViewPager.setOffscreenPageLimit(limit);
    }

    /**
     * 设置Item的切换动画。
     *
     * @param transformer 切换动画
     */
    public void setPageTransformer(BasePageTransformer transformer) {
        mPageTransformer = transformer;
        mViewPager.setPageTransformer(true, transformer);
    }

    /**
     * 设置Adapter。
     *
     * @param adapter PagerAdapter
     */
    public void setAdapter(PagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        dataSize = adapter.getCount();
        startAutoPlay();
    }

    /**
     * 设置当前选中的Item。
     *
     * @param itemPosition Item的位置
     * @param smoothScroll 是否平缓滚动到指定的位置
     */
    public void setSelection(int itemPosition, boolean smoothScroll) {
        mViewPager.setCurrentItem(itemPosition, smoothScroll);
    }

    /**
     * 添加页面切换的监听事件。
     *
     * @param listener ViewPager页面切换的监听事件
     */
    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mViewPager.addOnPageChangeListener(listener);
    }

    private OnGalleryPageSelectListener mOnGalleryPageSelectListener;

    /**
     * 设置选中的监听事件。
     *
     * @param listener 选中的监听事件
     */
    public void setOnGalleryPageSelectListener(OnGalleryPageSelectListener listener) {
        bottomScalePageTransformer.setmOnGalleryPageSelectListener(listener);
        scaleTranstormer.setmOnGalleryPageSelectListener(listener);
        this.mOnGalleryPageSelectListener = listener;

    }

    public interface OnGalleryPageSelectListener {

        void onGalleryPageSelected(int position);
    }

    public int getmCurrentPositon() {
        return mCurrentPositon ;
    }

    public void setmCurrentPositon(int mCurrentPositon) {
        this.mCurrentPositon = mCurrentPositon;
    }

}
