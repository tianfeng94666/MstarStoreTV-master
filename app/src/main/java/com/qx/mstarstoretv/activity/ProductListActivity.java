package com.qx.mstarstoretv.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.json.GoldProductResult;
import com.qx.mstarstoretv.net.ImageLoadOptions;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.XGallery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class ProductListActivity extends BaseActivity {


    @Bind(R.id.iv_product)
    ImageView ivProduct;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.xg_product)
    XGallery xgProduct;
    @Bind(R.id.tv_name)
    TextView tvName;
    private GoldProductResult goldProductResult;
    List<GoldProductResult.DataBean.ModelListBean> listBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        loadNetData();
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

    private void initView() {
        ViewGroup.LayoutParams layoutParams = ivProduct.getLayoutParams();
        layoutParams.width = UIUtils.getWindowHight() - UIUtils.dip2px(150);
        layoutParams.height = UIUtils.getWindowHight() - UIUtils.dip2px(150);
        ivProduct.setLayoutParams(layoutParams);
        idIgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        ArrayList<String> imagesList = new ArrayList<>();
        for (GoldProductResult.DataBean.ModelListBean bean : listBeen) {
            imagesList.add(bean.getPicb());
        }
        ivProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle pBundle = new Bundle();
                pBundle.putString("itemId", listBeen.get(xgProduct.getmCurrentPositon()%listBeen.size()).getId());
//                showToastReal(listBeen.get(position % listBeen.size()).getTitle());
                pBundle.putInt("type", 0);

                openActivity(SimpleStyleInfromationActivity.class, pBundle);
            }
        });

//        flybanner.setImagesUrl(imagesList);
//        flybanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Bundle pBundle = new Bundle();
//                pBundle.putString("itemId", listBeen.get(position % listBeen.size()).getId());
////                showToastReal(listBeen.get(position % listBeen.size()).getTitle());
//                pBundle.putInt("type", 0);
//
//                openActivity(SimpleStyleInfromationActivity.class, pBundle);
//            }
//        });
        SampleAdapter adapter = new SampleAdapter();
        xgProduct.setAdapter(adapter);
        xgProduct.setPageOffscreenLimit(10);
        xgProduct.setOnGalleryPageSelectListener(new XGallery.OnGalleryPageSelectListener() {
            @Override
            public void onGalleryPageSelected(int position) {
//                xgProduct.setmCurrentPositon(position%listBeen.size());
                tvName.setText(listBeen.get(position%listBeen.size()).getModelNum());
                ImageLoader.getInstance().displayImage(listBeen.get(position%listBeen.size()).getPicb(), ivProduct, ImageLoadOptions.getOptions());
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
            tv.setText(listBeen.get(position % listBeen.size()) + "");
            ImageLoader.getInstance().displayImage(listBeen.get(position % listBeen.size()).getPicm(), iv, ImageLoadOptions.getOptions());
            container.addView(view);
            view.setTag(position % listBeen.size());
            tvName.setText(listBeen.get(xgProduct.getmCurrentPositon() % listBeen.size()).getModelNum());
            ImageLoader.getInstance().displayImage(listBeen.get(xgProduct.getmCurrentPositon() % listBeen.size()).getPicb(), ivProduct, ImageLoadOptions.getOptions());
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
