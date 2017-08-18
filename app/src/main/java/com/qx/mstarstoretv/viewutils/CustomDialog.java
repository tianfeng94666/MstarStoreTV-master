package com.qx.mstarstoretv.viewutils;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by Administrator on 2016/9/14.
 */
public class CustomDialog extends Dialog {

    Context mContext;
    public CustomDialog(Context context) {
        super(context);
        this.mContext=context;
    }
    public CustomDialog(Context context,int theme) {
        super(context,theme);
        this.mContext=context;
    }

}
