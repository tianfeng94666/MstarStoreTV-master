package com.qx.mstarstoretv.activity;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/20 0020.
 */

public class OrderExamineActivity extends BaseActivity {
    @Bind(R.id.webview)
    WebView myWebView;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_examine);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        idIgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myWebView.canGoBack()) {
                    myWebView.goBack();
                }else {
                    finish();
                }
            }
        });
        myWebView.loadUrl("http://appapi1.fanerweb.com/htapp/index.html?"+ "tokenKey=" + BaseApplication.getToken());
        //加载本地中的html
        //myWebView.loadUrl("file:///android_asset/www/test2.html");
        //加上下面这段代码可以使网页中的链接不以浏览器的方式打开
        myWebView.setWebViewClient(new WebViewClient());
        //得到webview设置
        WebSettings webSettings = myWebView.getSettings();
        //允许使用javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //将WebAppInterface于javascript绑定
//        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");

        webSettings.setPluginState(WebSettings.PluginState.ON);
        myWebView.setWebChromeClient(new WebChromeClient());


        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            myWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    @Override
    public void loadNetData() {

    }

    @Override
    public void finish() {
        super.finish();
        myWebView.clearCache(true);
    }
}
