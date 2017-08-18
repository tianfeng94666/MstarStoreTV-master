package com.qx.mstarstoretv.viewutils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class CustomHorizontalScrollView extends HorizontalScrollView {

    public CustomHorizontalScrollView(Context context) {
        super(context);

        setFadingEdgeLength(0);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setFadingEdgeLength(0);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setFadingEdgeLength(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        double xDistance;
//        double yDistance;
//        double xStart = 0;
//        double yStart = 0;
//        double xEnd = 0;
//        double yEnd = 0;
//        switch (ev.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//
//                xDistance = yDistance = 0f;
//
//                xStart = ev.getX();
//
//                yStart = ev.getY();
//
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//
//                xEnd = ev.getX();
//
//                yEnd = ev.getY();
//
//                break;
//
//            default:
//
//                break;
//
//        }
//
//        xDistance = Math.abs(xEnd-xStart);
//
//        yDistance = Math.abs(yEnd-yStart);
//
//        if(xDistance>yDistance)
//
//            return true;  //拦截事件向下分发

        return super.onInterceptTouchEvent(ev);//默认值为false
    }

}
