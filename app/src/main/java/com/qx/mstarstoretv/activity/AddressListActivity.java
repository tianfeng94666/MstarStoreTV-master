package com.qx.mstarstoretv.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.json.AddressListEntity;
import com.qx.mstarstoretv.json.AddressListResult;
import com.qx.mstarstoretv.net.OKHttpRequestUtils;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.GridViewWithHeaderAndFooter;
import com.qx.mstarstoretv.viewutils.LoadingWaitDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * 创建人：Yangshao
 * 创建时间：2016/9/29 16:04
 * @version    地址列表
 *    
 */
public class AddressListActivity extends BaseActivity {



    ListViewAdapter adapter;
    OrderSelectAdressAdapter orderSelectAdressAdapter;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.gv_adress)
    GridViewWithHeaderAndFooter gridview;
    List<AddressListEntity> addressList = new ArrayList<>();
    int type;
    private final int ADDRESS_MANAGER = 1;
    private final int ADDRESS_ORDER = 2;

    AddressListEntity idDefaultAddressListEntity = new AddressListEntity();
    private LoadingWaitDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_adress);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", 1);
        initView();
        loadNetData();
    }

    public void showWatiNetDialog() {
        dialog = new LoadingWaitDialog(this);
        dialog.show();
    }

    public void dismissWatiNetDialog() {
        if (dialog != null) {
            dialog.cancel();
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void loadNetData() {
        showWatiNetDialog();
        String url = "";
        if (type == ADDRESS_MANAGER) {
            url = AppURL.URL_ADDRESS_MANAGER + "tokenKey=" + BaseApplication.getToken();
        }
        if (type == ADDRESS_ORDER) {
            url = AppURL.URL_ADDRESS_SELECT + "tokenKey=" + BaseApplication.getToken();
        }

        L.e("获取地址" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                dismissWatiNetDialog();
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    AddressListResult address = new Gson().fromJson(result, AddressListResult.class);
                    if (address.getData() != null) {
                        addressList.clear();
                        addressList.addAll(address.getData().getAddressList());
                        if (type == ADDRESS_MANAGER) {
                            adapter.notifyDataSetChanged();
                        }
                        if (type == ADDRESS_ORDER) {
                            orderSelectAdressAdapter.notifyDataSetChanged();
                        }
                        for (int i = 0; i < addressList.size(); i++) {
                            if (addressList.get(i).getIsDefault().equals("1")) {
                                L.e("手动设置默认地址");
                                idDefaultAddressListEntity.setIsDefault("1");
                                idDefaultAddressListEntity.setAddr(addressList.get(i).getAddr());
                                idDefaultAddressListEntity.setId(addressList.get(i).getId());
                                idDefaultAddressListEntity.setName(addressList.get(i).getName());
                                idDefaultAddressListEntity.setPhone(addressList.get(i).getPhone());
                            }
                        }
                    }
                } else if (error == 2) {
                    loginToServer(AddressListActivity.class);
                } else if (error == 1) {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);
                }
            }

            @Override
            public void onFail(String fail) {
                dismissWatiNetDialog();
            }
        });


    }

    protected void initView() {
        titleText.setText(R.string.shop_address_manager);
        if(UIUtils.isPad(this)){
            gridview.setNumColumns(3);
        }else {
            gridview.setNumColumns(1);
        }
        LinearLayout addaderss = (LinearLayout) findViewById(R.id.id_lay_add);
        addaderss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddressListActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });
        View view = View.inflate(this, R.layout.lv_below_add, null);
        view.findViewById(R.id.id_lay_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressListActivity.this, AddAddressActivity.class);
                startActivityForResult(intent, 11);
            }
        });
        gridview.addFooterView(view);// 为listview添加footview

        if (type == ADDRESS_MANAGER) {
            adapter = new ListViewAdapter();
            gridview.setAdapter(adapter);
        }
        if (type == ADDRESS_ORDER) {
            orderSelectAdressAdapter = new OrderSelectAdressAdapter();
            gridview.setAdapter(orderSelectAdressAdapter);
        }
        AddAddressActivity.setOnRefurbishListener(new AddAddressActivity.OnRefurbishListener() {
            @Override
            public void onRefush() {
                loadNetData();
            }
        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                netSetDefaultAddress(addressList.get(position));
                if (type == ADDRESS_MANAGER) {
                    return;
                }
                if (addressList.size() == 0) {
                    finish();
                    return;
                }
                if (type == ADDRESS_ORDER) {
                    netSetDefaultAddress(addressList.get(position));
                }
                idDefaultAddressListEntity = addressList.get(position);
                Intent intent = new Intent();
                intent.putExtra("phoneNumber", idDefaultAddressListEntity.getPhone());
                intent.putExtra("name", idDefaultAddressListEntity.getName());
                intent.putExtra("address", idDefaultAddressListEntity.getAddr());
                intent.putExtra("addressId", idDefaultAddressListEntity.getId());
                setResult(12, intent);
                finish();
            }
        });

    }

    public class OrderSelectAdressAdapter extends BaseAdapter {
        @Override

        public int getCount() {
            return addressList.size();
        }

        @Override
        public Object getItem(int i) {
            return addressList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(AddressListActivity.this).inflate(R.layout.adapter_order_adress_item, null);
                viewHolder.idTvName = (TextView) view.findViewById(R.id.id_tv_name);
                viewHolder.idTvAddress = (TextView) view.findViewById(R.id.id_tv_address);
                viewHolder.idLyEdit = (LinearLayout) view.findViewById(R.id.id_ly_edit);
                viewHolder.idLyDel = (LinearLayout) view.findViewById(R.id.id_ly_del);
                viewHolder.igCheck = (ImageView) view.findViewById(R.id.id_check);
                viewHolder.phone_tv = (TextView) view.findViewById(R.id.phone_tv);

                // viewHolder.addressListEntity=addressList.get(i);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }


            AddressListEntity addressListEntity = addressList.get(i);
            // viewHolder.id_lay_bg.setBackgroundColor(getResources().getColor(R.color.theme_bg));
            if (addressListEntity.getId().equals("0")) {
                viewHolder.idLyEdit.setVisibility(View.GONE);
                viewHolder.idLyDel.setVisibility(View.GONE);
            } else {
                viewHolder.idLyEdit.setVisibility(View.VISIBLE);
                viewHolder.idLyDel.setVisibility(View.VISIBLE);
            }
            viewHolder.idTvName.setText(addressListEntity.getName());
            viewHolder.idTvAddress.setText(addressListEntity.getAddr());
            viewHolder.phone_tv.setText(addressListEntity.getPhone());
            if (addressListEntity.getIsDefault().equals("1")) {
                viewHolder.igCheck.setImageResource(R.drawable.icon_gou);
            } else {
                viewHolder.igCheck.setImageResource(0);
            }
            viewHolder.idLyDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (addressList.size() > 1) {
                        if (addressList.get(i).getIsDefault().equals("1")) {
                            netDelAddress(addressList.get(i), true);
                        }
                    } else {
                        ToastManager.showToastReal(getString(R.string.of_one_adress));
                    }
                }
            });

            /*编辑*/
            viewHolder.idLyEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle pBundle = new Bundle();
                    pBundle.putBoolean("IS_MODIFY_ADDRESS", true);
                    pBundle.putString("ID", addressList.get(i).getId());
                    openActivity(AddAddressActivity.class, pBundle);
                }
            });
            return view;
        }
    }


    public class ListViewAdapter extends BaseAdapter {
        int temp;

        @Override
        public int getCount() {
            return addressList.size();
        }

        @Override
        public Object getItem(int i) {
            return addressList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(AddressListActivity.this).inflate(R.layout.adapter_adress_item, null);
                viewHolder.idTvName = (TextView) view.findViewById(R.id.id_tv_name);
                viewHolder.idTvAddress = (TextView) view.findViewById(R.id.id_tv_address);
                viewHolder.idCheck = (TextView) view.findViewById(R.id.id_check);
                viewHolder.idLyEdit = (LinearLayout) view.findViewById(R.id.id_ly_edit);
                viewHolder.idLyDel = (LinearLayout) view.findViewById(R.id.id_ly_del);
                viewHolder.id_lay_bg = (RelativeLayout) view.findViewById(R.id.id_lay_bg);
                // viewHolder.addressListEntity=addressList.get(i);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            AddressListEntity addressListEntity = addressList.get(i);
            // viewHolder.id_lay_bg.setBackgroundColor(getResources().getColor(R.color.theme_bg));
            viewHolder.idTvName.setText(addressListEntity.getName());
            viewHolder.idTvAddress.setText(addressListEntity.getAddr());
            Drawable drawable = getResources().getDrawable(R.drawable.iocn_lsel_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
            Drawable drawable1 = getResources().getDrawable(R.drawable.iocn_lsel_nor);
            drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight()); //设置边界
            if (addressListEntity.getIsDefault().equals("1")) {
                viewHolder.idCheck.setCompoundDrawables(null, null, drawable, null);//画在右边
            } else {
                viewHolder.idCheck.setCompoundDrawables(null, null, drawable1, null);//画在右边
            }
            viewHolder.idCheck.setId(i);
            viewHolder.idLyDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    netDelAddress(addressList.get(i), true);
                }
            });

            /*编辑*/
            viewHolder.idLyEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle pBundle = new Bundle();
                    pBundle.putBoolean("IS_MODIFY_ADDRESS", true);
                    pBundle.putString("ID", addressList.get(i).getId());
                    openActivity(AddAddressActivity.class, pBundle);
                }
            });
            /*viewHolder.idCheck.setOnCheckedChangeListener()替换为   避免多次调用*/
            viewHolder.idCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    netSetDefaultAddress(addressList.get(i));
                }
            });
            return view;
        }


    }


    public void netSetDefaultAddress(final AddressListEntity addressListEntity) {
        String url = AppURL.URL_DEFAULT_ADDRESS + "tokenKey=" + BaseApplication.getToken() + "&id=" + addressListEntity.getId();
        L.e(url);
        VolleyRequestUtils.getInstance().getCookieRequest(AddressListActivity.this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    //通知适配器更改
                    L.e(getString(R.string.set_default_adress_success));
                    loadNetData();
                } else if (error == 2) {
                    loginToServer(AddressListActivity.class);
                } else if (error == 1) {
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

    public void netDelAddress(AddressListEntity addressListEntity, boolean isdefault) {
        String url = AppURL.URL_DEL_ADDRESS + "tokenKey=" + BaseApplication.getToken() + "&id=" + addressListEntity.getId();
        L.e(url);
        VolleyRequestUtils.getInstance().getCookieRequest(AddressListActivity.this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    loadNetData();
                } else if (error == 2) {
                    loginToServer(AddressListActivity.class);
                } else if (error == 1) {
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

    public class ViewHolder {
        TextView idTvName;
        TextView idTvAddress;
        TextView idCheck;
        LinearLayout idLyEdit;
        LinearLayout idLyDel;
        RelativeLayout id_lay_bg;
        ImageView igCheck;
        TextView phone_tv;
    }

    public void onBack(View view) {
        onsetResult();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onsetResult();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onsetResult() {
        Intent intent = new Intent();
        intent.putExtra("phoneNumber", idDefaultAddressListEntity.getPhone());
        intent.putExtra("name", idDefaultAddressListEntity.getName());
        intent.putExtra("address", idDefaultAddressListEntity.getAddr());
        intent.putExtra("addressId", idDefaultAddressListEntity.getId());
        L.e("onBack");
        setResult(12, intent);
        finish();
    }
}
