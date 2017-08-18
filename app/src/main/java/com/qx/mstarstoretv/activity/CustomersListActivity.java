package com.qx.mstarstoretv.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.json.CustomerListRestult;
import com.qx.mstarstoretv.net.OKHttpRequestUtils;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * 创建人：Yangshao
 * 创建时间：2016/10/25 15:44
 * @version  客户列表
 *
 */
public class CustomersListActivity extends BaseActivity {

    @Bind(R.id.id_et_seach)
    EditText idEtSeach;
    @Bind(R.id.id_view_line)
    View idViewLine;
    @Bind(R.id.ig_btn_seach)
    ImageView igBtnSeach;
    @Bind(R.id.id_lv_custom)
    ListView idLvCustom;
    String keyWord = "";
    CustomersListAdapter customersListAdapter;
    List<CustomerListRestult.DataEntity.ListEntity> madata;
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;
    private int page = 1;
    private int visibleLastIndex;
    private int listcount;
    private int adapterCount;
    private boolean isfirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_customer);
        ButterKnife.bind(this);
getDate();
        initView();
    }

    private void getDate() {
        keyWord = getIntent().getStringExtra("keyWord");
        idEtSeach.setText(keyWord);
        if (!StringUtils.isEmpty("keyWord")) {
            loadNetData(keyWord,2);
        }
    }

    private void initView() {

        madata = new ArrayList<>();
        customersListAdapter = new CustomersListAdapter();
        idLvCustom.setAdapter(customersListAdapter);
        idLvCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idEtSeach.setText(madata.get(i).getCustomerName());
                Intent intent = new Intent();
                intent.putExtra("CustomerName", madata.get(i).getCustomerName());
                intent.putExtra("CustomerID", madata.get(i).getCustomerID());
                setResult(11, intent);
                finish();
            }
        });

        idLvCustom.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {

                int lastIndex = customersListAdapter.getCount();        //数据集最后一项的索引
                if (visibleLastIndex == lastIndex && listcount > adapterCount) {
                    //如果是自动加载,可以在这里放置异步加载数据的代码
                    page++;
                    loadNetData(idEtSeach.getText().toString(),2);
                }
                if (visibleLastIndex == lastIndex && listcount == adapterCount && isfirst) {
                    isfirst = !isfirst;
                    ToastManager.showToastReal("数据已加载完！");
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                visibleLastIndex = firstVisibleItem + visibleItemCount;
                adapterCount = totalItemCount;
            }
        });
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idEtSeach.setText("");
            }
        });
        idEtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener(){



            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                // TODO Auto-generated method stub
                if(arg1 == EditorInfo.IME_ACTION_SEARCH)
                {
                   loadNetData(idEtSeach.getText().toString(),1);
                    // search pressed and perform your functionality.
                }
                return false;
            }

        });
    }

    @Override
    public void loadNetData() {

    }

    public void loadNetData(final String keyWord , final int type) {
        //GetCustomerList?keyword=湖南|益阳&cpage=1&tokenKey=944df2f27ffce557042887589986c193
        String url = AppURL.URL_CUSTOMER_LIST + "tokenKey=" + BaseApplication.getToken() + "&keyword=" + StringUtils.replaceBlank(keyWord) + "&cpage=" + page;
        L.e("CustomersListActivity" + url);

        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    CustomerListRestult customerListRestult = new Gson().fromJson(result, CustomerListRestult.class);
                    if (customerListRestult.getData() != null) {
                        List<CustomerListRestult.DataEntity.ListEntity> datas = customerListRestult.getData().getList();
                        listcount = customerListRestult.getData().getList_count();
                        if(type ==1){
                            page=1;
                            madata.clear();
                        }
                            madata.addAll(datas);
                            customersListAdapter.notifyDataSetChanged();


                    }

                }
                if (error == 2) {
                    loginToServer(CustomersListActivity.class);
                }
                if (error == 1) {
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


    public class CustomersListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return madata.size();
        }

        @Override
        public CustomerListRestult.DataEntity.ListEntity getItem(int i) {
            return madata.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            TextView txt = new TextView(CustomersListActivity.this);
            txt.setBackgroundColor(Color.WHITE);
            int padding = UIUtils.dip2px(15);
            txt.setPadding(padding, padding, padding, padding);
            txt.setText(getItem(position).getCustomerName());

            return txt;
        }
    }

}
