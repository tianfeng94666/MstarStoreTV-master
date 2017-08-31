package com.qx.mstarstoretv.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.qx.mstarstoretv.base.BaseApplication;

import java.text.DecimalFormat;


public class UIUtils {
	
	public static Context getContext() {
		return BaseApplication.getApplication();
	}

	public static Thread getMainThread() {
		return BaseApplication.getMainThread();
	}

	public static long getMainThreadId() {
		return BaseApplication.getMainThreadId();
	}


	public static int dp(Context context, int dp) {
		return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dp, context.getResources().getDisplayMetrics()) + 0.5F);
	}


	public static View infalte(Context context, @LayoutRes int layoutId, ViewGroup parent) {
		return LayoutInflater.from(context).inflate(layoutId, parent, false);

	}
	public static Display getWindowManager(){
		WindowManager wm = (WindowManager) getContext()
				.getSystemService(Context.WINDOW_SERVICE);
		Display defaultDisplay = wm.getDefaultDisplay();
		return defaultDisplay;

	}

	public static int getWindowHight(){
		DisplayMetrics metric = new DisplayMetrics();
		UIUtils.getWindowManager().getMetrics(metric);
		int width = metric.widthPixels;     // 屏幕宽度（像素）
		int height = metric.heightPixels;   // 屏幕高度（像素）
		float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
		int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 2
		return height;
	}

	public static int getWindowWidth(){
		DisplayMetrics metric = new DisplayMetrics();
		UIUtils.getWindowManager().getMetrics(metric);
		int width = metric.widthPixels;     // 屏幕宽度（像素）
		int height = metric.heightPixels;   // 屏幕高度（像素）
		float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
		int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 2
		return width;
	}





	public static int convertPxtoDip(int pixel) {
		float scale = getContext().getResources().getDisplayMetrics().density;
		int dips = (int) ((pixel * scale) + 0.5f);
		return dips;
	}

	public static int convertDiptoPx(int dips, Context context) {

		float scale = context.getResources().getDisplayMetrics().density;
		int pixel = (int) ((dips - 0.5f) / scale);
		return pixel;
	}

	/** dip转换px */
	public static int dip2px(float dip) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f);
	}

	/** pxz转换dip */
	public static int px2dip(float px) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}
	/**
	 * 将px值转换为sp值，保证文字大小不变
	 *
	 * @param pxValue

	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp( float pxValue) {
		final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}
	/**
	 * 将sp值转换为px值，保证文字大小不变
	 *
	 * @param spValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */

	public static int sp2px( float spValue) {
		final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}
	/** 获取主线程的handler */
	public static Handler getHandler() {
		return BaseApplication.getMainThreadHandler();
	}

	/** 延时在主线程执行runnable */
	public static boolean postDelayed(Runnable runnable, long delayMillis) {
		return getHandler().postDelayed(runnable, delayMillis);
	}

	/** 在主线程执行runnable */
	public static boolean post(Runnable runnable) {
		return getHandler().post(runnable);
	}

	/** 从主线程looper里面移除runnable */
	public static void removeCallbacks(Runnable runnable) {
		getHandler().removeCallbacks(runnable);
	}

	public static View inflate(int resId){
		return LayoutInflater.from(getContext()).inflate(resId,null);
	}

	/** 获取资源 */
	public static Resources getResources() {
		return getContext().getResources();
	}

	/** 获取文字 */
	public static String getString(int resId) {
		return getResources().getString(resId);
	}

	/** 获取文字数组 */
	public static String[] getStringArray(int resId) {
		return getResources().getStringArray(resId);
	}

	/** 获取dimen */
	public static int getDimens(int resId) {
		return getResources().getDimensionPixelSize(resId);
	}

	/** 获取drawable */
	public static Drawable getDrawable(int resId) {
		return getResources().getDrawable(resId);
	}

	/** 获取颜色 */
	public static int getColor(int resId) {
		return getResources().getColor(resId);
	}

	/** 获取颜色选择 */
	public static ColorStateList getColorStateList(int resId) {
		return getResources().getColorStateList(resId);
	}

	private static long lastClickTime;

	/**
	 * 避免按键连续点击
	 * @return
	 */
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if ( 0 < timeD && timeD < 1000) {
			return true;
		}
		lastClickTime = time;
		return false;
	}
	//判断当前的线程是不是在主线程 
	public static boolean isRunInMainThread() {
		return android.os.Process.myTid() == getMainThreadId();
	}


	public static void runInMainThread(Runnable runnable) {
		if (isRunInMainThread()) {
			runnable.run();
		} else {
			post(runnable);
		}
	}

	/**
	 * 判断是否平板设备
	 * @param context
	 * @return true:平板,false:手机
	 */
	public static boolean isTabletDevice(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
				Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	public static void setBackgroundAlpha(Context context, float bgAlpha) {
		WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
		lp.alpha = bgAlpha;
		((Activity)context).getWindow().setAttributes(lp);
	}

	// 动态加载GridView 高度
	public static void setGridViewHeightBasedOnChildren(GridView myGridView, int num) {
		ListAdapter listAdapter = myGridView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int col = num;
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i += col) {
			View listItem = listAdapter.getView(i, null, myGridView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = myGridView.getLayoutParams();
		params.height = totalHeight+20;
		((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
		myGridView.setLayoutParams(params);
	}
	// 动态加载GridView 高度
	public static void setListViewHeightBasedOnChildren(ListView myGridView) {
		ListAdapter listAdapter = myGridView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i ++) {
			View listItem = listAdapter.getView(i, null, myGridView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = myGridView.getLayoutParams();
		params.height = totalHeight;
		((ViewGroup.MarginLayoutParams) params).setMargins(0, 10, 0, 10);
		myGridView.setLayoutParams(params);
	}

	/**
	 * 将string转成int
	 * @param st
	 * @return
	 */
	public static  int stringChangeToInt(String st){
		int result = 0;
		try {
			double a = Double.parseDouble(st);
			result = (int) a;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static  String stringChangeToTwoBitDouble(String st){
		String string;
		DecimalFormat df = new DecimalFormat("######0.00");
		string = df.format(Double.parseDouble(st));
		return string;
	}

	public static boolean isScreenChange(Context context) {

		Configuration mConfiguration = context.getResources().getConfiguration(); //获取设置的配置信息
		int ori = mConfiguration.orientation; //获取屏幕方向
		if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
//横屏
			return true;
		} else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {

//竖屏
			return false;
		}
		return false;
	}

	/**
	 * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
	 *
	 * @param context
	 * @return 平板返回 True，手机返回 False
	 */
	public static boolean isPad(Context context) {
		return (context.getResources().getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK)
				>= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	public static void  setEdittextToEnd(EditText editText,String string){
		editText.setText(string);
		editText.setSelection(editText.getText().toString().length());
	}
}


