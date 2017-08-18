package com.qx.mstarstoretv.viewutils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.qx.mstarstoretv.R;

public class IndicatorView extends View {

    private Context mContext;
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        invalidate();
    }

    public IndicatorView(Context context) {
        super(context);
        init(context);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);
       num= typedArray.getInt(R.styleable.IndicatorView_indicatorView_amount,4);
       typedArray.recycle();
        init(context);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);
        num= typedArray.getInt(R.styleable.IndicatorView_indicatorView_amount,4);
        typedArray.recycle();
        init(context);
    }

    private void init(Context mContext){
        this.mContext = mContext;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getScreenWidth() / num, dp2px(4));
    }

    private int getScreenWidth(){
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    private int dp2px(int dp){
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }
}
