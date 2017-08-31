package com.qx.mstarstoretv.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.bean.OrderSearchBean;
import com.qx.mstarstoretv.json.CustomerEntity;
import com.qx.mstarstoretv.json.SearchOrderResult;
import com.qx.mstarstoretv.net.OKHttpRequestUtils;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class SearchOrderActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.tv_search_type)
    TextView tvSearchType;
    @Bind(R.id.et_search_key)
    EditText etSearchKey;
    @Bind(R.id.tv_start_date)
    TextView tvStartDate;
    @Bind(R.id.rl_start_date)
    RelativeLayout rlStartDate;
    @Bind(R.id.tv_end_date)
    TextView tvEndDate;
    @Bind(R.id.rl_end_date)
    RelativeLayout rlEndDate;
    @Bind(R.id.ll_search_type)
    LinearLayout llSearchType;
    @Bind(R.id.iv_search_type)
    ImageView ivSearchType;
    @Bind(R.id.id_et_seach)
    EditText idEtSeach;
    @Bind(R.id.ig_btn_seach)
    ImageView igBtnSeach;
    @Bind(R.id.id_rl1)
    RelativeLayout idRl1;
    @Bind(R.id.rg_orders)
    RadioGroup rgOrders;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;
    @Bind(R.id.iv_delete2)
    ImageView ivDelete2;
    @Bind(R.id.ll_date_type)
    LinearLayout llDateType;
    @Bind(R.id.bt_confirm)
    Button btConfirm;


    private DatePicker datePicker;
    private PopupWindow popupWindow;
    private String choosetype;
    CustomerEntity isDefaultCustomer;
    List<SearchOrderResult.DataBean.SearchKeywordBean> list;
    private SearchTypeAdapter simpleAdapter;
    private List<SearchOrderResult.DataBean.SearchScopeBean> searchScopeBeenlist;
    private OrderSearchBean orderSearchBean = new OrderSearchBean();//搜索数据类
    private List<SearchOrderResult.DataBean.SearchDateScopeBean> searchDateScopeBeen;
    private int year;
    private int month;
    private int day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search_order);
        ButterKnife.bind(this);
        initView();
        loadNetData();
    }

    private void initView() {
        rlStartDate.setOnClickListener(this);
        rlEndDate.setOnClickListener(this);
        ivLeft.setOnClickListener(this);
        btConfirm.setOnClickListener(this);
        llSearchType.setOnClickListener(this);
        igBtnSeach.setOnClickListener(this);
        //监听radiogroup
        rgOrders.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radiobutton = (RadioButton) SearchOrderActivity.this.findViewById(group.getCheckedRadioButtonId());
                if (radiobutton != null) {
                    orderSearchBean.setSscopeid(getSscopeId(radiobutton.getText().toString()));
                }
            }
        });
 /*搜索用户时间  失去焦点*/
        idEtSeach.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if (!StringUtils.isEmpty(idEtSeach.getText().toString()))
                        seachCustom(idEtSeach.getText().toString());
                }
            }
        });
        etSearchKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                // TODO Auto-generated method stub
                if (arg1 == EditorInfo.IME_ACTION_SEARCH) {
                    goToSearchResultActivity();

                }
                return false;
            }

        });
        ivDelete.setOnClickListener(this);
        ivDelete2.setOnClickListener(this);
    }

    /**
     * 跳转到搜索结果页面
     */
    private void goToSearchResultActivity() {
        String startDate = tvStartDate.getText().toString();
        String endDate = tvEndDate.getText().toString();
        orderSearchBean.setKeyword(etSearchKey.getText().toString());
        orderSearchBean.setSdate(startDate);
        orderSearchBean.setEdate(endDate);
        if (!dateIsRight(startDate, endDate)) {
            ToastManager.showToastReal("日期填写错误！");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("searchData", orderSearchBean);
        openActivity(SearchResultActivity.class, bundle);
    }

    /**
     * 判断时间是否正确
     * @param sDate
     * @param eDate
     * @return
     */
    private boolean dateIsRight(String sDate, String eDate) {
        Date startDate = dateFromString(sDate);
        Date endDate = dateFromString(eDate);
        if (endDate.before(startDate)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获得搜索范围id
     *
     * @param s
     * @return
     */
    private int getSscopeId(String s) {
        if (searchScopeBeenlist != null) {
            for (int i = 0; i < searchScopeBeenlist.size(); i++) {
                if (searchScopeBeenlist.get(i).getTitle().equals(s)) {
                    return searchScopeBeenlist.get(i).getId();
                }
            }
        }
        return searchScopeBeenlist.get(0).getId();
    }


    /**
     * 获取搜索界面最初信息
     */
    @Override
    public void loadNetData() {
        baseShowWatLoading();
        String url = AppURL.URL_CODE_ORDER_SEARCH + "tokenKey=" + BaseApplication.getToken();
        L.e("url" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                baseHideWatLoading();
                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    SearchOrderResult searchOrderResult = new Gson().fromJson(result, SearchOrderResult.class);
                    if (searchOrderResult.getData() == null) {
                        return;
                    }
                    searchDateScopeBeen = searchOrderResult.getData().getSearchDateScope();
                    tvStartDate.setText(searchOrderResult.getData().getStartDate());
                    tvEndDate.setText(searchOrderResult.getData().getEndDate());
                    list = searchOrderResult.getData().getSearchKeyword();
                    if (list != null) {
                        tvSearchType.setText(list.get(0).getTitle());
                        orderSearchBean.setSkeyid(list.get(0).getId());
                        orderSearchBean.setKeyword(tvSearchType.getText().toString());
                    }
                    searchScopeBeenlist = searchOrderResult.getData().getSearchScope();
                    setRadioGroup();
                    setLlDateType();
                    orderSearchBean.setCustomerID(-1);
                } else if (error == 2) {
                    loginToServer(SearchOrderActivity.class);
                } else {
                    ToastManager.showToastReal(OKHttpRequestUtils.getmInstance().getErrorMsg(result));
                }
            }

            @Override
            public void onFail(String fail) {
                baseHideWatLoading();
            }
        });
    }

    private void setLlDateType() {
        if (searchDateScopeBeen == null) {
            return;
        }
        for (int i = 0; i < searchDateScopeBeen.size(); i++) {
            initTextView(searchDateScopeBeen.get(i), i);
        }
    }

    private void initTextView(final SearchOrderResult.DataBean.SearchDateScopeBean searchDateScopeBean, final int i) {
        final TextView tv = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(UIUtils.dip2px(16), 0, 0, 0);
        params.gravity = Gravity.CENTER_VERTICAL;
        tv.setLayoutParams(params);
        int padding1 = UIUtils.dip2px(5);
        int padding2 = UIUtils.dip2px(10);
        tv.setPadding(padding2, padding1, padding2, padding1);
        tv.setText(searchDateScopeBean.getTitle());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvEndDate.setText(searchDateScopeBean.getEdate());
                tvStartDate.setText(searchDateScopeBean.getSdate());
                changeIsDefault(i, 1);
                clearLlDateType();
            }
        });
        if (searchDateScopeBean.getIsDefault() == 1) {
            tv.setTextColor(getResources().getColor(R.color.theme_red));
            tv.setBackgroundResource(R.drawable.board_red);
        } else {
            tv.setTextColor(getResources().getColor(R.color.text_color3));
            tv.setBackgroundResource(R.drawable.board_gray);
        }
        llDateType.addView(tv);

    }

    private void clearLlDateType() {
        llDateType.removeAllViews();
        setLlDateType();
    }

    private void changeIsDefault(int j, int type) {

        for (int i = 0; i < searchDateScopeBeen.size(); i++) {
            if (i == j) {
                searchDateScopeBeen.get(i).setIsDefault(type);
            } else {
                searchDateScopeBeen.get(i).setIsDefault(0);
            }
        }
    }

    /**
     * 动态生成radiobutton控件
     *
     * @param st
     * @param marginLeft
     * @param isChoose
     */
    private void initRadioGroup(String st, int marginLeft, boolean isChoose) {
        RadioButton rb = new RadioButton(this);
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(UIUtils.dip2px(marginLeft), 0, 0, 0);
        params.gravity = Gravity.CENTER_VERTICAL;
        rb.setLayoutParams(params);
        rb.setButtonDrawable(R.drawable.selector_radio);
        rb.setText(st);
        rb.setTextSize(UIUtils.sp2px(24));
        rb.setSelected(isChoose);
        rgOrders.addView(rb);
    }

    /**
     * 设置RadioGroup，动态添加RadioButton和默认第一个选中
     */
    private void setRadioGroup() {
        if (searchScopeBeenlist != null) {
            for (int i = 0; i < searchScopeBeenlist.size(); i++) {
                if (i == 0) {
                    initRadioGroup(searchScopeBeenlist.get(i).getTitle(), 0, true);
                } else {
                    initRadioGroup(searchScopeBeenlist.get(i).getTitle(), 32, false);
                }

            }
            RadioButton radioButton = (RadioButton) rgOrders.getChildAt(0);
            radioButton.setChecked(true);
            orderSearchBean.setSscopeid(searchScopeBeenlist.get(0).getId());
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_start_date:
                initData("start");
                break;
            case R.id.rl_end_date:
                initData("end");
                break;
            case R.id.iv_left:
                finish();
                break;
            case R.id.bt_confirm:
                goToSearchResultActivity();
                break;
            case R.id.ll_search_type:
                showPopWindow(view);
                break;
            case R.id.ig_btn_seach:
                boolean isFast = UIUtils.isFastDoubleClick();
                if (!isFast) {
                    seachCustom("");
                }
                break;
            case R.id.iv_delete:
                etSearchKey.setText("");
                break;
            case R.id.iv_delete2:
                idEtSeach.setText("");
                orderSearchBean.setCustomerID(-1);
                break;
        }
    }

    /*
        * @version  搜索时候有客户
        */
    private void seachCustom(final String keyWord) {
        String url = AppURL.URL_HAVE_CUSTOMER + "tokenKey=" + BaseApplication.getToken() + "&keyword=" + keyWord;
        //keyword=广西|平果&tokenKey=944df2f27ffce557042887589986c193
        L.e("seachCustom" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                    JsonObject jsonObject = jsonResult.get("data").getAsJsonObject();
                    int state = jsonObject.get("state").getAsInt();
                    if (state == 0) {
                        ToastManager.showToastReal("没有此客户");
                        if (isDefaultCustomer == null) {
                            isDefaultCustomer = new CustomerEntity();
                        }
                        isDefaultCustomer.setCustomerID(-1);
                        orderSearchBean.setCustomerID(-1);
                    }
                    if (state == 1) {
                        ToastManager.showToastReal("有此客户");
                        orderSearchBean.setCustomerID(isDefaultCustomer.getCustomerID());

                    }
                    if (state == 2) {
                        Intent intent = new Intent(SearchOrderActivity.this, CustomersListActivity.class);
                        intent.putExtra("keyWord", keyWord);
                        startActivityForResult(intent, 11);
                    }
                } else if (error == 2) {
                    loginToServer(OrderActivity.class);
                } else {
                    ToastManager.showToastReal(OKHttpRequestUtils.getmInstance().getErrorMsg(result));
                }
            }

            @Override
            public void onFail(String fail) {

            }
        });
    }

    private void showPopWindow(View view) {
        getPopupWindow();
        popupWindow.showAsDropDown(view);
    }


    /***
     * 获取PopupWindow实例
     */
    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopuptWindow();
        }
    }

    /**
     * 创建popuWindow
     */
    private void initPopuptWindow() {
        // TODO Auto-generated method stub
        // 获取自定义布局文件activity_popupwindow_left.xml的视图
        View popupWindowView = getLayoutInflater().inflate(R.layout.popupwindow_search_type, null,
                false);
        ListView listView = (ListView) popupWindowView.findViewById(R.id.lv_seach_type);
        if (list != null) {
            simpleAdapter = new SearchTypeAdapter(list);
        }
        listView.setAdapter(simpleAdapter);
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        popupWindow = new PopupWindow(popupWindowView, UIUtils.dip2px(100), UIUtils.dip2px(28 + list.size() * 35), true);
        // 点击其他地方消失
        popupWindowView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choosetype = list.get(position).getTitle();
                tvSearchType.setText(choosetype);
                popupWindow.dismiss();
                popupWindow = null;
            }
        });
    }


    /**
     * 获得格式2015-09-01类似的String
     *
     * @param mYear
     * @param mMonth
     * @param mDay
     * @return
     */
    public String getDate(int mYear, int mMonth, int mDay) {
        StringBuilder sb = new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                .append((mDay < 10) ? "0" + mDay : mDay);
        String st = sb.toString();
        return st;
    }

    /**
     * 将yyyy-MM-dd string转化为date
     *
     * @param st
     * @return
     */
    public Date dateFromString(String st) {
        Date date = null;
        // 设置传入的时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(st);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据类型设置对应的textview
     *
     * @param type
     */
    public void initData(final String type) {
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        if (type.equals("start")) {
            date = dateFromString(tvStartDate.getText().toString());
        } else {
            date = dateFromString(tvEndDate.getText().toString());
        }
        calendar.setTime(date);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                String s = getDate(mYear, mMonth, mDay);
                if (type.equals("start")) {
                    SearchOrderActivity.this.tvStartDate.setText(s);
                    orderSearchBean.setSdate(s);
                } else {
                    SearchOrderActivity.this.tvEndDate.setText(s);
                    orderSearchBean.setEdate(s);
                }
            }
        }, year, month, day);
        datePickerDialog.show();


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        L.e("onActivityResult" + requestCode);
        if (data == null) {
            return;
        }
        if (requestCode == 11) {
            String customerName = data.getStringExtra("CustomerName");
            int customerID = data.getIntExtra("CustomerID", -1);
            idEtSeach.setText(customerName);
            if (isDefaultCustomer == null) {
                isDefaultCustomer = new CustomerEntity();
            }
            isDefaultCustomer.setCustomerID(customerID);
            orderSearchBean.setCustomerID(customerID);
        }


    }

    class SearchTypeAdapter extends BaseAdapter {
        List<SearchOrderResult.DataBean.SearchKeywordBean> list = new ArrayList<>();

        public SearchTypeAdapter(List<SearchOrderResult.DataBean.SearchKeywordBean> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder viewHolder = null;

            if (view == null) {
                view = View.inflate(context, R.layout.item_popupwinow_type, null);
                System.out.println("height=" + view.getMeasuredHeightAndState());
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.tvItemType.setText(list.get(position).getTitle());

            return view;
        }

        class ViewHolder {
            @Bind(R.id.tv_item_type)
            TextView tvItemType;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
