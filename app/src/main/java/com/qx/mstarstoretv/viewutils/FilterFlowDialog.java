package com.qx.mstarstoretv.viewutils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.qx.mstarstoretv.R;

/**
 * Created by Administrator on 2016/9/20.
 *
 *  显示隐藏的二级菜单
 */
public class FilterFlowDialog extends PopupWindow {
    private View mPopView;
    Context mContext;
    ListView listView;
    public FilterFlowDialog(Context context, int height){
        super(context);
        this.mContext=context;
        init(context,height);
    }
    //new Screen(mctontext)).getHeight() - getStatusBarHeight()
    private void init(Context context,int height) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPopView= inflater.inflate(R.layout.dialog_filter_flow,null);
        listView = (ListView) mPopView.findViewById(R.id.gridview);
        this.setContentView(mPopView);
        this.setWidth(getWindowWidth()-150);
        this.setHeight(height);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 点击外面的控件也可以使得PopUpWindow dimiss
        this.setOutsideTouchable(false);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.Fiter_PopupAnimation);
        ColorDrawable dw = new ColorDrawable(0xffffffff);//0xb0000000
        // ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);//半透明颜色
    }

    public int getWindowWidth(){
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }




}
