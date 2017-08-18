package com.qx.mstarstoretv.viewutils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.RelativeLayout;

/*
 * 创建人：Yangshao
 * 创建时间：2016/10/17 10:01
 * @version    
 *    
 */
public class CheckableRelativeLayou extends RelativeLayout implements Checkable {

    private boolean mChecked;
    public CheckableRelativeLayou(Context context, AttributeSet set) {
        super(context,set);
    }

    @Override
    public void setChecked(boolean checked) {
        if (mChecked!=checked){
            mChecked=checked;
            refreshDrawableState();
            int len=getChildCount();
            for (int i=0;i<len;i++){
                View child=getChildAt(i);
                if (child instanceof  Checkable) {
                    ((Checkable) child).setChecked(checked);
                }
            }
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }
}
