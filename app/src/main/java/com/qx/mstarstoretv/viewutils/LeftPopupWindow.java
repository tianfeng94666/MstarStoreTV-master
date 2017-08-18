package com.qx.mstarstoretv.viewutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.ChooseCustomerAndAddressActivity;
import com.qx.mstarstoretv.activity.ConfirmOrderActivity;
import com.qx.mstarstoretv.activity.OrderActivity;
import com.qx.mstarstoretv.activity.SimpleStyleInfromationActivity;
import com.qx.mstarstoretv.activity.StoneSearchInfoActivity;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.Global;
import com.qx.mstarstoretv.bean.Ring;
import com.qx.mstarstoretv.json.ConfirmOrderResult;
import com.qx.mstarstoretv.net.ImageLoadOptions;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.qx.mstarstoretv.utils.UIUtils.dip2px;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class LeftPopupWindow implements View.OnClickListener {


    @Bind(R.id.iv_ring)
    ImageView ivRing;
    @Bind(R.id.tv_hand_size)
    TextView tvHandSize;
    @Bind(R.id.tv_ring_detail)
    TextView tvRingDetail;
    @Bind(R.id.tv_choose_ring)
    TextView tvChooseRing;
    @Bind(R.id.tv_stone_detail)
    TextView tvStoneDetail;
    @Bind(R.id.tv_choose_stone)
    TextView tvChooseStone;
    @Bind(R.id.tv_total_price)
    TextView tvTotalPrice;
    @Bind(R.id.tv_commit_order)
    Button tvCommitOrder;
    @Bind(R.id.ll_pp)
    LinearLayout llPp;
    @Bind(R.id.ll_show_less)
    LinearLayout llShowLess;
    @Bind(R.id.ll_more)
    LinearLayout llMore;
    @Bind(R.id.tv_ring_number)
    TextView tvRingNumber;
    @Bind(R.id.tv_ring_purity)
    TextView tvRingPurity;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_customer)
    TextView tvCustomer;
    @Bind(R.id.tv_choose_customer)
    TextView tvChooseCustomer;
    @Bind(R.id.tv_reset)
    Button tvReset;
    private PopupWindow popupWindow;
    Context context;
    private View view;
    private Ring ring;
    private int mTouchDownX;
    private int mLastTouchX;
    private int mLastTouchY;
//    private LinearLayout llIsshow;

    public LeftPopupWindow(Context context) {
        this.context = context;
        initView();
        if (Global.ring == null) {
            Global.ring = new Ring();
        }
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.left_popup_window, null);
        ButterKnife.bind(this, view);
        popupWindow = new PopupWindow(view, dip2px(600), dip2px(840));
//        llIsshow = (LinearLayout) view.findViewById(R.id.ll_isshow);
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
        popupWindow.setAnimationStyle(R.style.MyPopupWindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());     //点击外部消失这句很重要
        initOnclic();
    }

    private void initOnclic() {
        llShowLess.setOnClickListener(this);
        tvChooseRing.setOnClickListener(this);
        tvChooseStone.setOnClickListener(this);
        tvChooseCustomer.setOnClickListener(this);
        tvCommitOrder.setOnClickListener(this);
        tvReset.setOnClickListener(this);
        ivRing.setOnClickListener(this);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mTouchDownX = x;
                        mLastTouchX = x;
                        mLastTouchY = y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int deltaX = mLastTouchX - x;
                        if (deltaX > view.getWidth() / 4) {
                            popupWindow.dismiss();
                        }
                        break;

                }
                return true;

            }
        });
    }

    public void showPop(View view) {
        popupWindow.showAtLocation(view, Gravity.LEFT, 0, 0);
        initPopupView();
    }

    public void closePopupWindow() {
        popupWindow.dismiss();
    }

    public void initPopupView() {
        ring = Global.ring;
        int ringPrice, stonePrice;
        if (ring != null) {
            ImageLoader.getInstance().displayImage(ring.getImageUrl(), ivRing, ImageLoadOptions.getOptionsHight());
            tvRingNumber.setText(ring.getRingNumber() == null ? "" : ring.getRingNumber());
            tvHandSize.setText(ring.getHandSize() != null ?"手寸："+ ring.getHandSize() : "");
            tvRingDetail.setText(ring.getItemId() == null ? "" : "质量等级：精品");
            tvRingPurity.setText(ring.getRingPurity() == null ? "" : "成色：" + ring.getRingPurity());
            tvStoneDetail.setText(ring.getStoneDetail());
            tvTotalPrice.setText(ring.getTotalPrice() == null ? "" : ring.getTotalPrice());
            ringPrice = ring.getRingPrice() != null ? UIUtils.stringChangeToInt(ring.getRingPrice()) : 0;
            stonePrice = ring.getStoneEntity() != null ? UIUtils.stringChangeToInt(ring.getStoneEntity().getPrice()) : 0;
            tvTotalPrice.setText((ringPrice + stonePrice) + "");
            tvAddress.setText(ring.getAddressEntity() != null ? ring.getAddressEntity().getAddr() : "");
            tvCustomer.setText(ring.getCustomerEntity() != null ? ring.getCustomerEntity().getCustomerName() : "");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_choose_ring:
                openActivity(OrderActivity.class, null);
                Global.isShowPopup = 2;
                break;
            case R.id.tv_choose_stone:
                openActivity(StoneSearchInfoActivity.class, null);
                Global.isShowPopup = 2;
                break;
            case R.id.tv_commit_order:
                commitOrder();
                break;
            case R.id.tv_total_price:
                break;
            case R.id.ll_show_less:
                popupWindow.dismiss();
                break;
            case R.id.tv_choose_customer:
                openActivity(ChooseCustomerAndAddressActivity.class, null);
                break;
            case R.id.tv_reset:
                resetStoneAndRing();
                break;
            case R.id.iv_ring:
                if (ring.getItemId() != null) {
                    Bundle pBundle = new Bundle();
                    pBundle.putString("itemId", ring.getItemId());
                    pBundle.putInt("type", 0);
                    openActivity(SimpleStyleInfromationActivity.class, pBundle);
                }
                break;
        }
    }

    private void commitOrder() {
        if (canCommit()) {
            baseShowWatLoading();
            String lgUrl = AppURL.URL_QUICK_MAKING + "tokenKey=" + BaseApplication.getToken() + "&productId=" + ring.getItemId() + "&modelPurityId=" + ring.getRingPurityId()
                    + "&modelQualityId=1" + "&number=" + ring.getNumber() + "&jewelStoneId=" + ring.getStoneEntity().getId() + "&word=" + ring.getWord() + "&customerID="
                    + ring.getCustomerEntity().getCustomerID();
            L.e("netLogin" + lgUrl);
            VolleyRequestUtils.getInstance().getCookieRequest(context, lgUrl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
                @Override
                public void onSuccess(String result) {
                    JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                    String error = jsonResult.get("error").getAsString();
                    if (error.equals("0")) {
                        ConfirmOrderResult orderListResult = new Gson().fromJson(result, ConfirmOrderResult.class);
                        ToastManager.showToastReal("定制完成！");
                        if (orderListResult.getData() == null) {
                            return;
                        }
                        resetStoneAndRing();
                        Bundle bundle = new Bundle();
                        bundle.putInt("type", 2);
                        bundle.putString("itemId", orderListResult.getData().getId());
                        openActivity(ConfirmOrderActivity.class, bundle);

                    } else if (error.equals("2")) {
                        baseHideWatLoading();
                    } else {
                        baseHideWatLoading();
                        String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                        ToastManager.showToastWhendebug(message);
                        L.e(message);
                    }
                }

                @Override
                public void onFail(String fail) {
                    baseHideWatLoading();
                }


            });
        }
    }

    private void resetStoneAndRing() {
        Global.ring.setNumber(null);
        Global.ring.setHandSize(null);
        Global.ring.setImageUrl(null);
        Global.ring.setItemId(null);
        Global.ring.setRingPrice(null);
        Global.ring.setRingNumber(null);
        Global.ring.setRingPurity(null);
        Global.ring.setRingPurityId(null);
        Global.ring.setStoneEntity(null);
        Global.ring.setRingWeightRange(null);
        initPopupView();
    }

    private LoadingWaitDialog loadingDialog;

    protected void baseShowWatLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingWaitDialog(context, "加载中");
            loadingDialog.show();
        }
        //SystemClock.sleep(1000);
    }

    public void baseHideWatLoading() {
        if (loadingDialog == null) return;
        if (loadingDialog != null || loadingDialog.isShowing()) {
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }

    private boolean canCommit() {
        if (ring.getStoneEntity() == null) {
            ToastManager.showToastReal("请选择主石！");
            return false;
        }
        if (ring.getItemId() == null) {
            ToastManager.showToastReal("请选择戒托！");
            return false;
        }
        if (ring.getRingPurityId() == null) {
            ToastManager.showToastReal("请选择戒托成色！");
            return false;
        }
        if (ring.getAddressEntity() == null) {
            ToastManager.showToastReal("请选择地址！");
            return false;
        }
        if (ring.getCustomerEntity() == null) {
            ToastManager.showToastReal("请选择客户！");
            return false;
        }
        return true;
    }

    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(context, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }
}
