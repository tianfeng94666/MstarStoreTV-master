package com.qx.mstarstoretv.viewutils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.utils.UIUtils;



public class SelectDotView extends View {

    private static final int DEFAULT_SELECTED_DOT = 0;
    private static final float RADIUS = 4f;
    private static final float DISTANCE = 12f;
    private Context mContext;
    private int dotNum;
    private int selectDotNum;
    private Paint p;
    private Paint p1;

    public SelectDotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public SelectDotView(Context context) {
        super(context);
        init(context);
    }

    public SelectDotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context mContext) {
        this.mContext = mContext;
        selectDotNum = DEFAULT_SELECTED_DOT;
        p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);


        p1 = new Paint();
        p1.setAntiAlias(true);
        p1.setStyle(Paint.Style.FILL);
    }

    public void setDotNum(int dotNum) {
        this.dotNum = dotNum;
    }

    public void setSelectedDot(int selectDotNum) {
        this.selectDotNum = selectDotNum;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = UIUtils.dip2px(RADIUS * 2 + DISTANCE * (dotNum - 1));
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = UIUtils.dip2px(RADIUS * 2);
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < dotNum; i++) {
            if (i == selectDotNum) {
                p1.setColor(mContext.getResources().getColor(R.color.theme_color));
                canvas.drawCircle(UIUtils.dip2px(RADIUS + i * DISTANCE), UIUtils.dip2px(RADIUS), UIUtils.dip2px(RADIUS), p1);
            } else {
                p.setColor(mContext.getResources().getColor(R.color.color_black_st));
                canvas.drawCircle(UIUtils.dip2px(RADIUS + i * DISTANCE), UIUtils.dip2px(RADIUS), UIUtils.dip2px(RADIUS), p);
            }
        }
    }
}
