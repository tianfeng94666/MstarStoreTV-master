package com.qx.mstarstoretv.viewutils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.qx.mstarstoretv.utils.UIUtils;


/**
 * Created by Shinelon on 2016/1/11.
 */
public class MyLinearLayout extends LinearLayout {

    public static int HORIZONTAL_SPACE;

    private int horizontalSpace;

    private LinearLayout ll;
    private boolean isflag = false;

    public MyLinearLayout(Context context) {
        super(context);
        init();
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        ll = new LinearLayout(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        ll.setLayoutParams(lp);
        ll.setOrientation(HORIZONTAL);
        addView(ll);
        measure(lp.width, lp.height);
        HORIZONTAL_SPACE = getMeasuredWidth();
        horizontalSpace = getMeasuredWidth();
    }

    public void addNewView(View view) {
        if (getChildCount() == 1){
            int padding = UIUtils.dip2px(16);
            setPadding(padding, 0, padding, padding);
        }
        if (!isflag) {
            isflag = true;
            horizontalSpace = HORIZONTAL_SPACE;
        }
        MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
        view.measure(lp.width, lp.height);
        int viewWidth = lp.leftMargin + lp.rightMargin + view.getMeasuredWidth();
        Log.e("wcl", "viewWidth:" + viewWidth + " horizontalSpace:" + horizontalSpace);
        if (ll.getChildCount() >= 1 && viewWidth > horizontalSpace) {
            ll = new LinearLayout(getContext());
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(-1, -2);
            ll.setLayoutParams(lp2);
            ll.setOrientation(HORIZONTAL);
            addView(ll);
            horizontalSpace = HORIZONTAL_SPACE;
        }
        ll.addView(view, lp);
        horizontalSpace = horizontalSpace - viewWidth;
    }

}
