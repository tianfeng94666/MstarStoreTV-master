package com.qx.mstarstoretv.viewutils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.qx.mstarstoretv.R;


/**
 * 加载中Dialog
 */
public class LoadingWaitDialog extends Dialog {

    private TextView tips_loading_msg;

    private String message = null;

    public LoadingWaitDialog(Context context) {
        super(context);
        message = getContext().getResources().getString(R.string.pull_to_refresh_footer_refreshing_label);
    }

    public LoadingWaitDialog(Context context, String message) {
        super(context);
        this.message = message;
        this.setCancelable(false);
    }

    public LoadingWaitDialog(Context context, int theme, String message) {
        super(context, theme);
        this.message = message;
        this.setCancelable(false);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.layout_loading);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setCanceledOnTouchOutside(true);
        tips_loading_msg = (TextView) findViewById(R.id.tips_loading_msg);
        tips_loading_msg.setText(this.message);
    }

    public void setText(String message) {
        this.message = message;
        tips_loading_msg.setText(this.message);
    }

    public void setText(int resId) {
        setText(getContext().getResources().getString(resId));
    }

}
