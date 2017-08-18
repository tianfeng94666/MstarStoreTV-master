package com.qx.mstarstoretv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.json.AddressListEntity;
import com.qx.mstarstoretv.json.AddressListResult;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.ToastManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 收货地址
 *
 * @author wenjundu 2015-7-23
 */
public class ShopingAddressActivity extends BaseActivity implements OnClickListener {


    private final String TAG = "ShippingAddressActivity";
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    private ListView listView;
    private Button btnManageAddress;
    private ReceiverAddressAdapter adapter;
    private String token;

    List<AddressListEntity> mdata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_shipping_address);
        ButterKnife.bind(this);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNetData();
    }

    @Override
    public void loadNetData() {
        String url = AppURL.URL_ADDRESS_MANAGER + "tokenKey=" + BaseApplication.getToken();
        L.e("获取地址" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack(){
            @Override
            public void onSuccess(String result) {
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    AddressListResult address = new Gson().fromJson(result, AddressListResult.class);
                    if(address.getData()==null){
                        return;
                    }
                    mdata = address.getData().getAddressList();
                    adapter.notifyDataSetChanged();
                }
               else if (error.equals("2")) {
                    L.e("重新登录");
                    ToastManager.showToastReal(jsonResult.get("message").getAsString());
                    loginToServer(ShopingAddressActivity.class);
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

    public static   OnRefreshOrderListener monRefreshOrderListener;
    public interface OnRefreshOrderListener{
        void  onrefresh();
    }

    public static void setOnRefreshOrderListener(OnRefreshOrderListener onRefreshOrderListener) {
        monRefreshOrderListener = onRefreshOrderListener;
    }



    private void initView() {

        titleText.setText("收货地址管理");
        listView = (ListView) findViewById(R.id.address_list);

        btnManageAddress = (Button) findViewById(R.id.btn_manage_address);
        btnManageAddress.setOnClickListener(this);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // mdata.get(position).getId();
                AddressListEntity addressListEntity = mdata.get(position);
                Intent intent = new Intent();
                intent.putExtra("phoneNumber", addressListEntity.getPhone());
                intent.putExtra("name", addressListEntity.getName());
                intent.putExtra("address", addressListEntity.getAddr());
                intent.putExtra("addressId", addressListEntity.getId());
                setResult(12, intent);
                finish();
            }
        });

        token = BaseApplication.getToken();
        if (token.equals("")) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, 1);
        }
        adapter = new ReceiverAddressAdapter();
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//开启单选模式
        //获取收货地址
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_manage_address://管理收货地址
                Intent intent = new Intent(this, AddressListActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void onBack(View v) {
        finish();
    }

    @Override
    protected void onDestroy() {
        BaseApplication.requestQueue.cancelAll(this);
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 2) {
            if (data != null) {
                token = data.getExtras().getString("token");
                //getReceiverAddressList();
            }
        }
    }


    public class ReceiverAddressAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mdata.size();
        }

        @Override
        public Object getItem(int position) {
            return mdata.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_receiver_address, null);
                viewHolder.isDefaultTV = (TextView) convertView.findViewById(R.id.default_tv);
                viewHolder.NameTV = (TextView) convertView.findViewById(R.id.name_tv);
                viewHolder.phoneTV = (TextView) convertView.findViewById(R.id.phone_tv);
                viewHolder.addressTV = (TextView) convertView.findViewById(R.id.address_tv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            AddressListEntity addressListEntity = mdata.get(position);
            if (addressListEntity.getIsDefault().equals("1")) {//是否默认地址,是显示默认
                viewHolder.isDefaultTV.setVisibility(View.VISIBLE);
            } else {
                viewHolder.isDefaultTV.setVisibility(View.GONE);
            }
            viewHolder.NameTV.setText(addressListEntity.getName());
            viewHolder.phoneTV.setText(addressListEntity.getPhone());
            viewHolder.addressTV.setText(addressListEntity.getAddr());
            return convertView;
        }

        class ViewHolder {
            TextView isDefaultTV, NameTV, phoneTV, addressTV;
        }
    }

}
