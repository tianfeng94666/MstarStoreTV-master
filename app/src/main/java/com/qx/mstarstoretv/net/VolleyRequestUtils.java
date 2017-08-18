package com.qx.mstarstoretv.net;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.qx.mstarstoretv.base.BaseApplication;

import org.json.JSONObject;


/**
 * volley get post请求
 * @author wenjundu 2015-07-03
 */
public class VolleyRequestUtils {
	public static void cleanCookie () {
		CookieStringtRequest.cookie = "";
		CookieStringtRequest.isTruncate = true;
	}

	private  VolleyRequestUtils(){

	}
	static VolleyRequestUtils mInstance;
	public static VolleyRequestUtils getInstance() {
		if (mInstance == null) {
			synchronized (OKHttpRequestUtils.class) {
				if (mInstance == null) {
					mInstance = new VolleyRequestUtils();
				}
			}
		}
		return mInstance;
	}


	//获取网页或者特殊数据的时候
	public static void GetCookieRequestPurePage (Context context, String url,final HttpStringRequsetCallBack callBack){
		CookieStringtRequest jsonObjectRequest=new CookieStringtRequest(Method.GET, url, null, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				if(callBack!=null)
					callBack.onSuccess(response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				if(callBack!=null){
					callBack.onFail(error.toString());
				}
			}
		});
		jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		jsonObjectRequest.setCookie();
		BaseApplication.requestQueue.add(jsonObjectRequest);
	}


	//！！！！！！！！get请求为了保证cookie一致  后来不要使用该方法！！！！！！！！！！
	public  void getCookieRequest(Context context,String url,final HttpStringRequsetCallBack callback){

		JsonObjectRequest jsonObjectRequest = new NormalPostRequest( Request.Method.GET, url, null,
				new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						if(callback!=null)
						callback.onSuccess(response.toString());
					}
				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				if(callback!=null)
					callback.onFail(error.toString());
			}

		});
		jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
				2,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		jsonObjectRequest.setTag(context);
		BaseApplication.requestQueue.add(jsonObjectRequest);
	}


	public  void getJsonRequest (Context context, String url,final HttpStringRequsetCallBack callBack){
		CookieStringtRequest jsonObjectRequest=new CookieStringtRequest(Method.POST, url, null, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				if(callBack!=null)
					callBack.onSuccess(android.text.Html.fromHtml(response).toString());
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				callBack.onFail(error.toString());

				//使用缓存数据
//				if(BaseApplication.requestQueue.getCache().get(path)!=null){
//					callBack.onSuccess(new String(BaseApplication.requestQueue.getCache().get(path).data).toString());
//				}else{
//					//callBack.onFail(error.toString());
//				}
//				callBack.onSuccess(new String(BaseApplication.requestQueue.getCache().get(path).data).toString());
			}
		});
		jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		jsonObjectRequest.setTag(context);
		jsonObjectRequest.setCookie();
		BaseApplication.requestQueue.add(jsonObjectRequest);
	}



	/**
	 * 以GET或者POST方式发送jsonObjectRequest
	 *
	 * @param context android应用上下文
	 * @param requestUrl 请求的Url
	 * @paramrequestParamete 请求的参数，如果为null，则使用GET方法调用，否则使用POST方法调用
	 */
	public  void sendJsonObjectRequest(Context context, String requestUrl,final HttpRequestCallBack callBack){
		JsonObjectRequest request = new JsonObjectRequest(requestUrl, null, new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject jsonObject) {
				callBack.onSuccess(jsonObject);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				callBack.onFailure(volleyError.toString());
			}
		});
		VolleySingleton.getInstance(context).addToRequestQueue(request);
	}


	/**
	 *
	 * @param context volley加载图片
	 * @param imageUrl
	 * @param view
	 * @return
	 */
	public  ImageLoader.ImageContainer loadImage(Context context, String imageUrl, ImageView view, int default_image, int error_image){
		ImageLoader.ImageListener imgListener = ImageLoader.getImageListener(view, default_image, error_image);
		return VolleySingleton.getInstance(context).getImageLoader().get(imageUrl, imgListener);
		//networkImageView.setDefaultImageResId(R.drawable.default_image);
		//networkImageView.setErrorImageResId(R.drawable.failed_image);
	}




	//新加接口 ，保证cookie一致的volley网络请求回调这个接口
	public  interface HttpStringRequsetCallBack{
		void onSuccess(String result);
		void onFail(String fail);
	}

	public interface  HttpRequestCallBack {
		 void onSuccess(JSONObject jsonObject);
		  void onFailure(String fail);
	}



}
