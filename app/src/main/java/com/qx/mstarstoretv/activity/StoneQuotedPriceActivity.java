package com.qx.mstarstoretv.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.adapter.BaseViewHolder;
import com.qx.mstarstoretv.adapter.CommonAdapter;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.json.QuotedPriceResult;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class StoneQuotedPriceActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.lv_quoted_price)
    ListView lvQuotedPrice;
    String stoneIds;
    private List<QuotedPriceResult.DataBean.ListBean> list;
    private String percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stone_quoted_price);
        ButterKnife.bind(this);
        getDate();
        init();
    }

    private void init() {
        titleText.setText("裸石报价");
        idIgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDate() {
        Intent intent = getIntent();
        stoneIds = intent.getStringExtra("stoneIds");
        percent = intent.getStringExtra("percent");
        loadNetData();
    }

    @Override
    public void loadNetData() {
        baseShowWatLoading();
        String url = "";
        url = AppURL.URL_STONE_QUOTED_PRICE + "tokenKey=" + BaseApplication.getToken() + "&id="+stoneIds+"&percent= "+percent;

        if (StringUtils.isEmpty(url)) {
            return;
        }
        L.e("获取地址" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                baseHideWatLoading();
                L.e("result" + result);
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    QuotedPriceResult quotedPriceResult = new Gson().fromJson(result,QuotedPriceResult.class);
                    if(quotedPriceResult.getData()== null){
                        ToastManager.showToastReal("数据为空！");
                        return;
                    }
                     list = quotedPriceResult.getData().getList();
                    lvQuotedPrice.setAdapter(new CommonAdapter<QuotedPriceResult.DataBean.ListBean>(list,R.layout.item_quoted_price) {
                        @Override
                        public void convert(int position, BaseViewHolder helper, QuotedPriceResult.DataBean.ListBean item) {
                            final QuotedPriceResult.DataBean.ListBean bean = list.get(position);
                                helper.setText(R.id.tv_itme_quoted_price_iscertauth,bean.getTitle());
                            helper.setText(R.id.tv_itme_content,bean.getContent());
                            helper.setViewOnclick(R.id.tv_itme_copy, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // 从API11开始android推荐使用android.content.ClipboardManager
                                    // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    // 将文本内容放到系统剪贴板里。
                                    cm.setText(bean.getContent());
                                    ToastManager.showToastReal("复制成功！");
                                }
                            });
                        }
                    });
                } else if (error.equals("2")) {
                    loginToServer(FinishTableLessActivity.class);
                } else {
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
