package com.qx.mstarstoretv.viewutils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.adapter.RecycleViewPartListAdapter;
import com.qx.mstarstoretv.json.ModelPartsBean;
import com.qx.mstarstoretv.manager.FitGridLayoutManager;
import com.qx.mstarstoretv.utils.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/11 0011.
 */

public class BottmPartPopupWindow {
    @Bind(R.id.rv_part)
    RecyclerView rvPart;
    @Bind(R.id.iv_cancle)
    ImageView ivCancle;
    @Bind(R.id.iv_comfirm)
    ImageView ivComfirm;
    @Bind(R.id.header)
    View header;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;
    private Context context;
    private View view;
    private PopupWindow popupWindow;
    private FitGridLayoutManager mLayoutManager;
    List<ModelPartsBean> partList;
    private int choosePosition = -1;
    public RecycleViewPartListAdapter recycleViewPartAdapter;
    RecycleViewPartListAdapter.PartListItemClickListener itemClickListener;
    private View.OnClickListener comfirmClickListerner;
    private View rootView;
    private View.OnClickListener deleteClickListerner;

    public BottmPartPopupWindow(Context context) {
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.bottom_popup_window, null);
        ButterKnife.bind(this, view);
        if (UIUtils.isScreenChange(context)) {
            rvPart.setPadding(0, 0, 0, 0);
            header.setVisibility(View.GONE);
            int width = UIUtils.getWindowWidth();


            popupWindow = new PopupWindow(view, width / 2, UIUtils.getWindowHight());
        } else {
            rvPart.setPadding(0, UIUtils.dip2px(20), 0, 0);
            header.setVisibility(View.VISIBLE);
            int width = UIUtils.getWindowWidth();
            popupWindow = new PopupWindow(view, width, width+UIUtils.dip2px(60));
        }
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE && !popupWindow.isFocusable()) {
                    //如果焦点不在popupWindow上，且点击了外面，不再往下dispatch事件：
                    //不做任何响应,不 dismiss popupWindow
                    return true;
                }
                //否则default，往下dispatch事件:关掉popupWindow，
                return false;
            }
        });
        popupWindow.setAnimationStyle(R.style.MyPopupWindow_bottom_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());     //点击外部消失这句很重要

    }

    public void setData(List<ModelPartsBean> partList, RecycleViewPartListAdapter.PartListItemClickListener listItemClickListener, View.OnClickListener comfimrClickListener,View.OnClickListener deleteClickListener, View view) {
        this.partList = partList;
        this.itemClickListener = listItemClickListener;
        this.comfirmClickListerner = comfimrClickListener;
        this.deleteClickListerner = deleteClickListener;
        showPop(view);
    }

    public void showPop(View view) {
        rootView = view;
        choosePosition = -1;//恢复到初始化

        if (UIUtils.isScreenChange(context)) {
            mLayoutManager = new FitGridLayoutManager(context, rvPart, 2, GridLayoutManager.VERTICAL, false);
            rvPart.setLayoutManager(mLayoutManager);
            recycleViewPartAdapter = new RecycleViewPartListAdapter(context, partList, itemClickListener);
            //设置item间距，30dp
//            rvPart.addItemDecoration(new SpaceItemDecoration(4));
            rvPart.setAdapter(recycleViewPartAdapter);
            popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
        } else {
            mLayoutManager = new FitGridLayoutManager(context, rvPart, 2, GridLayoutManager.HORIZONTAL, false);
            rvPart.setLayoutManager(mLayoutManager);
            recycleViewPartAdapter = new RecycleViewPartListAdapter(context, partList, itemClickListener);
            //设置item间距，30dp
//            rvPart.addItemDecoration(new SpaceItemDecoration(4));
            rvPart.setAdapter(recycleViewPartAdapter);
            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }

    }

    public void closePopupWindow() {
        popupWindow.dismiss();
    }

    @OnClick({R.id.iv_cancle, R.id.iv_comfirm,R.id.iv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cancle:
                closePopupWindow();
                break;
            case R.id.iv_comfirm:
                comfirmClickListerner.onClick(view);
                break;
            case R.id.iv_delete:
                deleteClickListerner.onClick(view);
                closePopupWindow();
                break;
        }
    }

    public int getChoosePosition() {
        return choosePosition;
    }

    public void setChoosePosition(int choosePosition) {
        this.choosePosition = choosePosition;
        recycleViewPartAdapter.setChoose(choosePosition);
    }
}
