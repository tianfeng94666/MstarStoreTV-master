package com.qx.mstarstoretv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.json.GoldProductResult;
import com.qx.mstarstoretv.json.VersionResult;
import com.qx.mstarstoretv.net.ImageLoadOptions;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.viewutils.XGallery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class ShowGoldActivity extends BaseActivity {
    @Bind(R.id.tv_full_gold)
    TextView tvFullGold;
    @Bind(R.id.tv_gold)
    TextView tvGold;
    @Bind(R.id.tv_gold_pt)
    TextView tvGoldPt;
    @Bind(R.id.ll_gold)
    LinearLayout llGold;
    @Bind(R.id.xg_product)
    XGallery xgProduct;
    @Bind(R.id.ll_rootview)
    RelativeLayout llRootview;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    private GoldProductResult goldProductResult;
    List<GoldProductResult.DataBean.ModelListBean> listBeen;
    private boolean isCustomized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_gold);
        isCustomized = SpUtils.getInstace(this).getBoolean("isCustomized", true);
        ButterKnife.bind(this);
        loadNetData();

    }

    private void initView() {
        String fullGold = SpUtils.getInstace(this).getString("fullGold");
        String gold = SpUtils.getInstace(this).getString("gold");
        String goldPt = SpUtils.getInstace(this).getString("goldPt");
        tvFullGold.setText(fullGold + "元/克");
        tvGold.setText(gold + "元/克");
        tvGoldPt.setText(goldPt + "元/克");
        idIgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(MainActivity.class, null);
                finish();
            }
        });

        SampleAdapter adapter = new SampleAdapter();
        xgProduct.setAdapter(adapter);

        xgProduct.setOnGalleryPageSelectListener(new XGallery.OnGalleryPageSelectListener() {
            @Override
            public void onGalleryPageSelected(int position) {
                Bundle pBundle = new Bundle();
                pBundle.putString("itemId", listBeen.get(position % listBeen.size()).getId());
//                showToastReal(listBeen.get(position % listBeen.size()).getTitle());
                pBundle.putInt("type", 0);
                if (isCustomized) {
                    openActivity(SimpleStyleInfromationActivity.class, pBundle);
                } else {
                    openActivity(StyleInfromationActivity.class, pBundle);
                }
            }
        });


    }

    @Override
    public void loadNetData() {
        String lgUrl = AppURL.URL_GOLD_PRODUCT + "tokenKey=" + BaseApplication.getToken();
        L.e("netLogin" + lgUrl);
        VolleyRequestUtils.getInstance().getCookieRequest(this, lgUrl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    goldProductResult = new Gson().fromJson(result, GoldProductResult.class);
                    if (goldProductResult.getData() == null) {
                        showToastReal("后台数据为空");
                        return;
                    }
                    listBeen = goldProductResult.getData().getModelList();
                    if (listBeen != null && listBeen.size() > 0) {
                        initView();
                    }

                } else if (error.equals("2")) {

                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    ToastManager.showToastWhendebug(message);
                    L.e(message);
                }
            }

            @Override
            public void onFail(String fail) {

            }


        });
    }


    class SampleAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            L.e("adapterPosition=", position + "");


            final View view = View.inflate(container.getContext(), R.layout.adapter_sample, null);
            ImageView iv = (ImageView) view.findViewById(R.id.iv);
            TextView tv = (TextView) view.findViewById(R.id.tv_position);
            tv.setText(listBeen.get(position % listBeen.size())+"");
            ImageLoader.getInstance().displayImage(listBeen.get(position % listBeen.size()).getPicm(), iv, ImageLoadOptions.getOptions());
            container.addView(view);
            view.setTag(position % listBeen.size());
//            iv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
            view.setTag(position % listBeen.size());
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

            L.e("destroyitme= ", position + "");
        }


    }
}
