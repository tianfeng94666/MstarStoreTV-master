package com.qx.mstarstoretv.viewutils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class CustomLV extends ListView {
    public CustomLV(Context context) {
        super(context);
    }

    public CustomLV(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLV(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
