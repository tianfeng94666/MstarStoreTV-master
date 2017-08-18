package com.qx.mstarstoretv.net;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.inter.MyBaseHttpRequestCallback;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.UIUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Administrator on 2016/10/31.
 */
public class OKHttpRequestUtils {

    private static final String TAG = "";
    private static OKHttpRequestUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler handler;
    private final int READ_TIMEOUT = 10;
    private final int WRITE_TIMEOUT = 10;
    private final int CONNECT_TIMEOUT = 1000 * 60;
    private static final byte[] LOCKER = new byte[0];
    private final String HTTP_CACHE_FILENAME= "HttpCache";
    //json请求
    public static final MediaType JSON = MediaType
            .parse("application/json; charset=utf-8");


    public static OKHttpRequestUtils getmInstance() {
        if (mInstance == null) {
            synchronized (OKHttpRequestUtils.class) {
                if (mInstance == null) {
                    mInstance = new OKHttpRequestUtils();
                }
            }
        }
        return mInstance;
    }

    public int getResultCode(String result){
        return new Gson().fromJson(result, JsonObject.class).get("error").getAsInt();
    }
    public String getErrorMsg(String result){
        return new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
    }

    private final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .removeHeader("Pragma").header("Cache-Control", String.format("max-age=%d", 60))
                    .build();
        }
    };

    private OKHttpRequestUtils() {
        Cache cache;
        File httpCacheDirectory = new File(UIUtils.getContext().getExternalCacheDir(), HTTP_CACHE_FILENAME);
        cache = new Cache(httpCacheDirectory, 10 * 1024);
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)//添加自定义缓存拦截器（后面讲解），注意这里需要使用.addNetworkInterceptor
//                .cookieJar(new CookieJar() {
//                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
//                    @Override
//                    public void saveFromResponse(HttpUrl path, List<Cookie> cookies) {
//                        cookieStore.put(path, cookies);
//                    }
//
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl path) {
//                        List<Cookie> cookies = cookieStore.get(path);
//                        return cookies != null ? cookies : new ArrayList<Cookie>();
//                    }
//                })
                /*.cache(cache)*/.build();
        handler = new Handler(Looper.getMainLooper());
    }

    public void postJson(String url, String json, final OKHttpCallBack okHttpCallBack) {
        RequestBody body = RequestBody.create(JSON, json);
        final Request request = new Request.Builder().url(url)
                .post(body).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onError(okHttpCallBack, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    onSuccess(okHttpCallBack, response.body().toString());
                } else {
                    onError(okHttpCallBack, response.message());
                }
            }
        });
    }


    public void postMap(String url, Map<String,String> map, final OKHttpCallBack callBack) {
        FormBody.Builder builder=new FormBody.Builder();
        if (map!=null){
            for (Map.Entry<String,String> entry:map.entrySet()){
                builder.add(entry.getKey(),entry.getValue().toString());
            }
        }
        RequestBody body=builder.build();
        Request request=new Request.Builder().url(url).post(body).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onError(callBack,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    onSuccess(callBack,response.body().toString());
                }else {
                    onError(callBack,response.message());
                }
            }
        });
    }

    public void canceltest() throws Exception {
        Request request = new Request.Builder()
                .url("http://httpbin.org/delay/2") // This URL is served with a 2 second delay.
                .build();
        final long startNanos = System.nanoTime();
        final Call call = mOkHttpClient.newCall(request);
        call.cancel();
    }

//    public void getJson(final String path, final OKHttpCallBack callBack){
//        L.e("URL    "+path);
//        final Request request=new Request.Builder().path(path).build();
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                onError(callBack,e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()){
//                    onSuccess(callBack,response.body().string());
//                   // response.body().close();
//                }else {
//                    onError(callBack,response.message());
//                }
//            }
//        });
//    }

    /**
     * okHttp post同步请求
     */
    public void requestPostBySyn(String url, final OKHttpCallBack callBack) {
        try {
            final Request request=new Request.Builder().url(url).build();
            //执行请求
            Response response = mOkHttpClient.newCall(request).execute();
            //请求执行成功
            if (response.isSuccessful()){
                onSuccess(callBack,response.body().string());
                //response.body().close();
            }else {
                onError(callBack,response.message());
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


    public void onError(final OKHttpCallBack callBack, final String msg) {
        if (callBack != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    L.e("onError  :"+msg);
                }
            });
        }
    }

    public void onSuccess(final OKHttpCallBack callBack, final String data) {
        if (callBack != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onSuccess(data);
                    L.e("onSuccess  :"+data);
                }
            });
        }
    }


    public static abstract class OKHttpCallBack {
        public abstract void onSuccess(String data);
    }


    public static abstract class OKHttpFinallCallBack {
        public abstract void onSuccess(String data);
        public abstract void onReLogin();
        public abstract void onErrorMsg(String msg);
    }

    public void requestData(final String url, HttpCycleContext httpCycleContext, final OKHttpFinallCallBack callBack) {
        RequestParams params = new RequestParams(httpCycleContext);
        HttpRequest.post(url, params, new MyBaseHttpRequestCallback<String>() {
            @Override
            public void onLogicSuccess(String result) {
                L.e("URL   "+"\n"+url);
                int code=getResultCode(result);
                if (callBack!=null&&code==0){
                    L.e("result   "+"\n"+result);
                    callBack.onSuccess(result);
                }if (callBack!=null&&code==2){
                    callBack.onReLogin();
                }if(callBack!=null&&code!=0&code!=1){
                    callBack.onErrorMsg(getErrorMsg(result));
                }
            }
            @Override
            public void onLogicFailure(String newGameResponse) {
                super.onLogicFailure(newGameResponse);
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
            }

            @Override public void onFinish() {
                super.onFinish();
            }
        });
    }

}
