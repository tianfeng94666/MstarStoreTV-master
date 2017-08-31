package com.qx.mstarstoretv.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.json.AddressResult;
import com.qx.mstarstoretv.json.CityResult;
import com.qx.mstarstoretv.json.ProvinceListEntity;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/28.
 */
public class SelectCityActivity extends BaseActivity {

    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.gridview)
    ListView list;
    SelectCityAdapter adapter;
    String url;

    String city, area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcity);
        ButterKnife.bind(this);
        initView();
        url = AppURL.URL_SHOP_ADDRESS + "tokenKey=" + BaseApplication.getToken();
        adapter = new SelectCityAdapter();
        list.setAdapter(adapter);
        list.setOnItemClickListener(itemClickListener);
        loadNetData();

    }

    private void endNetRequest() {
        state++;
        adapter.notifyDataSetChanged();
    }

    int state = 0;

    String provinceId, cityId, areaId;
    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (state == 1) {
                city = provinceList.get(i).getName();
                provinceId = provinceList.get(i).getId();
                titleText.setText("你选择了：" + city);
                String ADRESSURL_CITY = AppURL.URL_GET_CITY + "tokenKey=" + BaseApplication.getToken() + "&id=" + provinceList.get(i).getId();
                url = ADRESSURL_CITY;
                loadNetData();
            }
            if (state == 2) {
                area = provinceList.get(i).getName();
                cityId = provinceList.get(i).getId();
                titleText.setText("你选择了：" + city + "/" + area);
                String ADRESSURL_AREA = AppURL.URL_GET_AREA + "tokenKey=" + BaseApplication.getToken() + "&id=" + provinceList.get(i).getId();
                url = ADRESSURL_AREA;
                loadNetData();
            }
            if (state == 3) {
                titleText.setText("你选择了：" + city + "/" + area + "/" + provinceList.get(i).getName());
                areaId = provinceList.get(i).getId();
                Intent intent = new Intent();
                intent.putExtra("city", city);
                intent.putExtra("area", area);
                intent.putExtra("county", provinceList.get(i).getName());

                intent.putExtra("provinceId", provinceId);
                intent.putExtra("cityId", cityId);
                intent.putExtra("areaId", areaId);
                setResult(11, intent);
                finish();
            }

        }
    };
    List<ProvinceListEntity> provinceList = new ArrayList<>();

    public void loadNetData() {
        String ADRESSURL = url;
        L.e("URL:" + ADRESSURL);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack(){
            @Override
            public void onSuccess(String result) {
                provinceList.clear();
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    if (state == 0) {
                        AddressResult cityResult = new Gson().fromJson(result, AddressResult.class);
                        if(cityResult==null){
                            return;
                        }
                        provinceList = cityResult.getData().getProvinceList();
                    }
                    if (state == 1 || state == 2) {
                        L.e(result);
                        CityResult areaResult = new Gson().fromJson(result, CityResult.class);
                        provinceList = areaResult.getData();
                    }
                    endNetRequest();
                }
                else if (error.equals("2")) {
                    loginToServer(SelectCityActivity.class);
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);
                }


            }

            @Override
            public void onFail(String fail) {

            }

        });
    }

    public class SelectCityAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return provinceList.size();
        }

        @Override
        public ProvinceListEntity getItem(int i) {
            return provinceList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            TextView txt = new TextView(SelectCityActivity.this);
            txt.setBackgroundColor(Color.WHITE);
            int padding = UIUtils.dip2px(15);
            txt.setPadding(padding, padding, padding, padding);
            txt.setText(getItem(position).getName());
            return txt;
        }
    }

    protected void initView() {
        titleText.setText("请选择省份");
        idIgBack.setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
