package com.qx.mstarstoretv.dialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.SystemClock;

import com.qx.mstarstoretv.inter.OnResultListener;

/**
 * 管理dialog
 * 
 * @author lzy_torah
 *
 */
public class DialogUtil {
	private static ProgressDialog pDialog;

	/**
	 * 显示等待中的dialog
	 * 
	 * @param activity
	 * @param msg
	 */
	public static void showProgressDialog(Activity activity, final DialogMsg msg) {
		pDialog = new ProgressDialog(activity);
		pDialog.setMessage(msg.getMessage());
		pDialog.setProgressStyle(40);
		pDialog.setCanceledOnTouchOutside(false);
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
		if (msg.getTimeMills() > 0) {
			new Thread() {
				public void run() {
					SystemClock.sleep(msg.getTimeMills());
					dismissProgressDialog();
					if(msg.getListener()!=null){
						msg.getListener().onResult(true, null);
					}
				};
			}.start();
		}
	}

	/**
	 * 进度框消失
	 */
	public static void dismissProgressDialog() {
		if (pDialog != null && pDialog.isShowing()) {
			pDialog.dismiss();
		}
	}


	public class DialogMsg {
		/**
		 * 要显示的信息
		 */
		private String message;
		/**
		 * 秒
		 */
		private int timeMills;
		/**
		 * 对话框消失后的
		 */
		private OnResultListener<Object> listener;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getTimeMills() {
			return timeMills;
		}

		public void setTimeMills(int timeMills) {
			this.timeMills = timeMills;
		}

		public OnResultListener<Object> getListener() {
			return listener;
		}

		public void setListener(OnResultListener<Object> listener) {
			this.listener = listener;
		}
	}
}
