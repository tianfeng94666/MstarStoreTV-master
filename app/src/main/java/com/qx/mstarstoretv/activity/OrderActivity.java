package com.qx.mstarstoretv.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.Global;
import com.qx.mstarstoretv.base.MyAction;
import com.qx.mstarstoretv.bean.Ring;
import com.qx.mstarstoretv.dialog.GridMenuDialog;
import com.qx.mstarstoretv.inter.ClassifyOnSeachListener;
import com.qx.mstarstoretv.json.ClassTypeFilerEntity;
import com.qx.mstarstoretv.json.ModeListResult;
import com.qx.mstarstoretv.json.SearchValue;
import com.qx.mstarstoretv.json.StoneSearchInfoResult;
import com.qx.mstarstoretv.json.TypeFiler;
import com.qx.mstarstoretv.net.ImageLoadOptions;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.BadgeView;
import com.qx.mstarstoretv.viewutils.GridViewWithHeaderAndFooter;
import com.qx.mstarstoretv.viewutils.LeftPopupWindow;
import com.qx.mstarstoretv.viewutils.ListMenuDialog;
import com.qx.mstarstoretv.viewutils.LoadingWaitDialog;
import com.qx.mstarstoretv.viewutils.PullToRefreshView;
import com.qx.mstarstoretv.viewutils.SideFilterDialog;
import com.qx.mstarstoretv.viewutils.SquareImageView;
import com.qx.mstarstoretv.viewutils.xListView.XListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import zxing.activity.CaptureActivity;

/*
 * 创建人：Yangshao
 * 创建时间：2016/10/13 11:04
 * @version    
 *    
 */
public class OrderActivity extends BaseActivity implements PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {


    @Bind(R.id.tv_pager_amount)
    TextView tvPagerAmount;
    @Bind(R.id.root_view)
    RelativeLayout rootView;
    @Bind(R.id.ll_show_less)
    LinearLayout llShowLess;
    private LinearLayout idLyAll, idLyFilter, idRel2;
    private GridViewWithHeaderAndFooter idGvMenu;
    private TextView idCurOrder, idTvSelect;
    private Context context;
    private ImageView idIgNor, idIgNor1;
    private SideFilterDialog filterDialog;
    private ListMenuDialog listMenuDialog;
    private PullToRefreshView pullRefreshView;
    private GridMenuDialog gridMenuDialog;
    private List<ModeListResult.DataEntity.ModelEntity.ModelListEntity> data = new ArrayList<>();
    /*推荐款  最新款*/


    private static final int PULL_REFRESH = 1;
    private static final int PULL_LOAD = 2;
    private int tempCurpage = 1;
    private int pullState = 1;
    private int curpage = 1;
    private int list_count;
    @Bind(R.id.id_et_seach)
    EditText idEtSeach;
    @Bind(R.id.ig_btn_seach)
    ImageView idIgSeach;
    @Bind(R.id.id_tv_filter)
    TextView idTvFilter;
    @Bind(R.id.id_his_order)
    TextView idTvHisOrder;
    @Bind(R.id.id_classify)
    TextView idTvClassify;
    @Bind(R.id.tv_reset)
    TextView idTvCurOrder;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;
    /*搜索过的多选历史记录*/
    public static List<TypeFiler> multiselectKey = new ArrayList<>();
    /*搜索过的单选历史记录*/
    public static List<SearchValue> singleKey = new ArrayList<>();

    private int waitOrderCount;
    private ModeListResult modeListResult;
    private boolean isShowPrice;
    private boolean isCustomized;//是否是用户定制
    private StoneSearchInfoResult.DataBean.StoneBean.ListBean selectStone;
    private String openType;
    private int totalAmount;
    BadgeView badge;

    String mcategory = "";   /*下啦筛选关键字*/
    String myAction = "";   /*判断是哪个页面的action*/
    private LeftPopupWindow leftPopupWindow;
    private int numColumns=3;
    private static final int WHAT_AUTO_PLAY = 1000;
    //自动播放时间
    private int mAutoPalyTime = 4000;
    private int mCurrentPositon=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        isCustomized = SpUtils.getInstace(this).getBoolean("isCustomized", true);
        context = this;
        getDate();
        addStoneRang();
        initView();
        initListener();
        loadNetData(getInitUrl());
    }

    /**
     * 设置自动滑动
     */
    private Handler mAutoPlayHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

//            mCurrentPositon = mViewPager.getCurrentItem();
            mCurrentPositon=mCurrentPositon+Global.divideAmount;
            if(mCurrentPositon%data.size()==0){
                idGvMenu.setSelection(mCurrentPositon%data.size());
            }else {

                idGvMenu.smoothScrollToPosition(mCurrentPositon%data.size());
            }


            mAutoPlayHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, mAutoPalyTime);

        }
    };


    /**
     * 开始播放
     */
    public void startAutoPlay() {
        mCurrentPositon=0;
        mAutoPlayHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, mAutoPalyTime);
    }

    /**
     * 停止播放
     */
    public void stopAutoPlay() {
        mAutoPlayHandler.removeMessages(WHAT_AUTO_PLAY);
    }
    private void getDate() {
        selectStone = (StoneSearchInfoResult.DataBean.StoneBean.ListBean) getIntent().getSerializableExtra("stone");
        openType = getIntent().getStringExtra("openType");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Global.divideAmount = 6;
    }


    private void remind(int count) {
        boolean isVisible = false;
        if (count != 0) {
            isVisible = true;
        }
        //BadgeView的具体使用
        badge.setText(count + ""); // 需要显示的提醒类容
        badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);// 显示的位置.右上角,BadgeView.POSITION_BOTTOM_LEFT,下左，还有其他几个属性
        badge.setTextColor(Color.WHITE); // 文本颜色
        int hint = Color.rgb(200, 39, 73);
        badge.setBadgeBackgroundColor(hint); // 提醒信息的背景颜色，自己设置
        badge.setTextSize(15); // 文本大小
        badge.setBadgeMargin(3, 3); // 水平和竖直方向的间距
        badge.setBadgeMargin(5); //各边间隔
        if (isVisible) {
            badge.show();// 只有显示
        } else {
            badge.hide();//影藏显示
        }
    }

    public void onBack(View view) {
        finish();
    }


    private String getInitUrl() {
        String url = AppURL.URL_MODE_LIST + "tokenKey=" + BaseApplication.getToken() + "&cpage=" + curpage + getCheckBoxUrl() + getRadioGroupUrl()+ "&pageNum=24";

        return url;
    }

    private void initListMenuDialog(List<ModeListResult.DataEntity.CustomList> customList) {
        listMenuDialog = new ListMenuDialog(OrderActivity.this, customList);
        listMenuDialog.setOnListMenuSelectCloseClick(new ListMenuDialog.OnListMenuSelectCloseClick() {
            @Override
            public void onClose() {
                L.e("onClose");
                backgroundAlpha(1f);
                idIgNor.setImageResource(R.drawable.icon_list_nor);
            }

            @Override
            public void onSelect(final ModeListResult.DataEntity.CustomList select) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        multiselectKey.clear();
                        idTvSelect.setText(StringUtils.idgui(select.getTitle()));
                        TypeFiler typeFiler = new TypeFiler();
                        typeFiler.setCheck(true);
                        typeFiler.setGroupKey("custom");
                        typeFiler.setName(select.getTitle());
                        typeFiler.setValue(select.getId());
                        typeFiler.setId(select.getId());
                        multiselectKey.add(typeFiler);
                        curpage =1;
                        String url = getInitUrl();
                        loadNetData(url);
                    }
                });
            }
        });
    }


    private void initFilterDialog(List<ClassTypeFilerEntity> typeList) {
        filterDialog = new SideFilterDialog(context, typeList, MyAction.filterDialogAction, getStatusBarHeight());
         /*筛选界面      关闭事件*/
        filterDialog.setOnListMenuSelectCloseClick(new SideFilterDialog.OnListMenuSelectCloseClick() {
            @Override
            public void onClose() {
                L.e("关闭  filterDialog");
                backgroundAlpha(1f);
            }
        });

        /*筛选界面      确认搜索事件*/
        filterDialog.setOnSeachListener(new ClassifyOnSeachListener() {
            @Override
            public void onSeach(String action) {
                myAction = action;
                curpage = 1;
                String url = getInitUrl();
//                url += getCheckBoxUrl();
//                url += getRadioGroupUrl();
                loadNetData(url);
            }
        });

    }

    LoadingWaitDialog dialog;

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

    }

    private void loadNetData(String url) {
        isShowPrice = SpUtils.getInstace(this).getBoolean("isShowPrice", true);
        showWatiNetDialog();
        L.e("开启搜索" + url);
        // 进行登录请求
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                dismissWatiNetDialog();
                L.e("loadNetData  " + result);
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    modeListResult = new Gson().fromJson(result, ModeListResult.class);
                    ModeListResult.DataEntity dataEntity = modeListResult.getData();
                    if (dataEntity == null) {
                        return;
                    }
                    if (isShowPrice) {
                        idTvHisOrder.setTextColor(getResources().getColor(R.color.text_color));
                    } else {
                        idTvHisOrder.setTextColor(getResources().getColor(R.color.text_color3));
                    }
                    totalAmount = Integer.parseInt(modeListResult.getData().getModel().getList_count());
                    ModeListResult.DataEntity.ModelEntity modeEntity = dataEntity.getMode();
                    if (curpage == 1) {
                          /*搜索过的单选历史记录*/
                        ClassifyActivity.singleKey = dataEntity.getSearchKeyword();
                        singleKey = dataEntity.getSearchKeyword();
                        /*搜索过的多选历史记录*/
                        multiselectKey = dataEntity.getCategoryFiler();
                        waitOrderCount = Integer.valueOf(dataEntity.getWaitOrderCount());
                        if (waitOrderCount != 0) {
                            remind(waitOrderCount);
                        }
                    }
                    //下啦分类筛选
                    List<ModeListResult.DataEntity.CustomList> customList = dataEntity.getCustomList();
                    if (customList != null && customList.size() != 0) {
                        initListMenuDialog(customList);
                    }

                    List<ClassTypeFilerEntity> typeList = dataEntity.getTypeList();
                    if (typeList != null && typeList.size() != 0) {
                        initFilterDialog(typeList);
                    }
                    if (pullState != PULL_LOAD) {
                        data.clear();
                    }
                    list_count = Integer.valueOf(modeEntity.getList_count());

                        List<ModeListResult.DataEntity.ModelEntity.ModelListEntity> modelList = modeEntity.getModelList();
                        data.addAll(modelList);
                    endNetRequest();
                } else if (error.equals("2")) {
                    L.e("重新登录");
                    ToastManager.showToastReal(jsonResult.get("message").getAsString());
                    loginToServer(OrderActivity.class);
                } else {
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

    @Override
    protected void onResume() {
        super.onResume();
        Global.divideAmount = 6;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Global.isShowPopup != 0 && leftPopupWindow != null) {
            leftPopupWindow.initPopupView();
        }
        addStoneRang();

    }

    private void addStoneRang() {
        if(Global.isShowPopup!=0&&Global.ring.getStoneEntity()!=null){
            for(int i =0 ;i<singleKey.size();i++){
                if(singleKey.get(i).getName().equals(Global.ring.getStoneEntity().getModelWeightRange().getKey())){
                    singleKey.get(i).setValue(Global.ring.getStoneEntity().getModelWeightRange().getValue());
                }
            }
        }
    }

    private void endNetRequest() {
        mGvAdapter.notifyDataSetChanged();
        tempCurpage = curpage;
        if (pullState == PULL_LOAD) {
            pullRefreshView.onFooterRefreshComplete();
        } else if (pullState == PULL_REFRESH) {
            pullRefreshView.onHeaderRefreshComplete();
        }
        pullState = 0;
    }

    View loadStateView;
    TextView hint_txt;

    public void initView() {
        pullRefreshView = (PullToRefreshView) findViewById(R.id.pull_refresh_view);
        pullRefreshView.setOnHeaderRefreshListener(this);
        pullRefreshView.setOnFooterRefreshListener(this);
        pullRefreshView.setVisibility(View.VISIBLE);
        idIgNor = (ImageView) findViewById(R.id.id_ig_nor);
        idIgNor1 = (ImageView) findViewById(R.id.id_ig_nor1);
        idLyAll = (LinearLayout) findViewById(R.id.id_ly_all);
        idTvSelect = (TextView) findViewById(R.id.id_tv_select);
        idRel2 = (LinearLayout) findViewById(R.id.id_rel2);
        //筛选
        idLyFilter = (LinearLayout) findViewById(R.id.id_ly_filter);
        idCurOrder = (TextView) findViewById(R.id.id_cur_order);
        idGvMenu = (GridViewWithHeaderAndFooter) findViewById(R.id.id_gv_menu);
        loadStateView = View.inflate(this, R.layout.grid_food_layout, null);
        hint_txt = (TextView) loadStateView.findViewById(R.id.tv_hint_txt);
        //idGvMenu.addFooterView(loadStateView);
        //没有数据显示
        idGvMenu.setEmptyView(findViewById(R.id.lny_no_result));
        tvPagerAmount.getBackground().setAlpha(100);
        idGvMenu.setOnScrollListener(new XListView.OnXScrollListener() {
            @Override
            public void onXScrolling(View view) {

            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(Global.divideAmount==3){
                    stopAutoPlay();
                    mAutoPlayHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, mAutoPalyTime);
                }

                System.out.println("firstVisibleItem=" + firstVisibleItem);
                if (firstVisibleItem == 0) {
                    firstVisibleItem = 1;
                }
                mCurrentPositon=firstVisibleItem;

                tvPagerAmount.setText((int) (Math.ceil(firstVisibleItem / 24.0)) + "/" + (int) Math.ceil(totalAmount / 24.0));

            }
        });

        idGvMenu.setNumColumns(6);

        idGvMenu.setAdapter(mGvAdapter);

        badge = new BadgeView(OrderActivity.this, idTvCurOrder);// 创建一个BadgeView对象，view为你需要显示提醒的控件
        //remind(1,badge1,true);

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idEtSeach.setText("");
            }
        });
        idEtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                // TODO Auto-generated method stub
                if (arg1 == EditorInfo.IME_ACTION_SEARCH) {
                    curpage = 1;
                    String url = getInitUrl();
                    url += getkeyWordUrl();
                    loadNetData(url);
                    // search pressed and perform your functionality.
                }
                return false;
            }

        });

        if (Global.isShowPopup != 0) {
            llShowLess.setVisibility(View.VISIBLE);
            initPopwindow();
        } else {
            llShowLess.setVisibility(View.GONE);
        }
    }
    private void initPopwindow() {
        leftPopupWindow = new LeftPopupWindow(this);
        llShowLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftPopupWindow.showPop(rootView);
            }
        });
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // 如果是橫屏時候
        try {
            // Checks the orientation of the screen
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                idGvMenu.setNumColumns(6);
                Global.divideAmount = 6;
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                idGvMenu.setNumColumns(2);
                Global.divideAmount = 2;
            }
        } catch (Exception ex) {

        }

    }


    public String getCheckBoxUrl() {
        List<TypeFiler> filterList;
        if (myAction.equals(MyAction.classifyActivityAction)) {
            filterList = ClassifyActivity.hisCategoryFilterList;
        } else {
            filterList = OrderActivity.multiselectKey;
        }
        List<String> list = new ArrayList<>();
        int count = filterList.size();
        for (int i = 0; i < count; i++) {
            TypeFiler typeFiler = filterList.get(i);
            list.add(typeFiler.getGroupKey());
            // L.e(""+typeFiler.toString());
        }
        HashSet<String> hs = new HashSet<>(list); //此时已经去掉重复的数据保存在hashset中
        Iterator<String> iterator = hs.iterator();
        StringBuffer sbbuf = new StringBuffer();
        while (iterator.hasNext()) {
            StringBuffer sb = new StringBuffer();
            String tag = iterator.next();
            sb.append("&" + tag + "=");
            for (int i = 0; i < count; i++) {
                TypeFiler typeFiler = filterList.get(i);
                if (typeFiler.getGroupKey().equals(tag)) {
                    sb.append(typeFiler.getValue() + "|");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sbbuf.append(sb.toString());
        }
        L.e("typeFiler  check" + sbbuf.toString());
        return sbbuf.toString();
    }

    public String getRadioGroupUrl() {
        List<SearchValue> keywordList;
        if (myAction.equals(MyAction.classifyActivityAction)) {
            keywordList = ClassifyActivity.singleKey;
        } else {
            keywordList = singleKey;
        }
        String low, hig;
        StringBuilder sbPrice = new StringBuilder();
        if (keywordList != null && keywordList.size() != 0) {
            for (SearchValue hisKey : keywordList) {
                if (!StringUtils.isEmpty(hisKey.getValue())) {
                    sbPrice.append("&" + hisKey.getName() + "=" + hisKey.getValue());
                } else {
                    low = hisKey.getLow();
                    hig = hisKey.getHig();
                    if (!StringUtils.isEmpty(low) && !StringUtils.isEmpty(hig)) {
                        sbPrice.append("&" + hisKey.getName() + "=" + low + "|" + hig);
                    } else if (!StringUtils.isEmpty(low) && StringUtils.isEmpty(hig)) {
                        sbPrice.append("&" + hisKey.getName() + "=" + low + "|");
                    } else if (StringUtils.isEmpty(low) && !StringUtils.isEmpty(hig)) {
                        sbPrice.append("&" + hisKey.getName() + "=" + "|" + hig);
                    }
                }
            }
            L.e(sbPrice.toString());
        }
        L.e("typeFiler  radio" + sbPrice.toString());
        return sbPrice.toString();
    }

    /**
     * 关键字
     *
     * @return
     */
    public String getkeyWordUrl() {
        String url = "";
        String keyWord = idEtSeach.getText().toString();
        if (!StringUtils.isEmpty(keyWord)) {
            keyWord = StringUtils.removeSpacesUrl(keyWord);
            idTvFilter.setVisibility(View.VISIBLE);
            idTvFilter.setText(keyWord);
        }
        url = "&keyword=" + keyWord;
        return url;
    }


    private void initListener() {
        /*分类界面的确认搜索*/
        ClassifyActivity.setOnClassifySeachListener(new ClassifyOnSeachListener() {
            @Override
            public void onSeach(String action) {
                myAction = action;
                curpage = 1;
                String url = getInitUrl();
//                url += getCheckBoxUrl();
//                url += getRadioGroupUrl();
                loadNetData(url);
            }
        });

        /*弹出筛选页面dialog*/
        idLyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filterDialog != null) {
                    backgroundAlpha(0.7f);
                    filterDialog.showAsDropDown(rootView, getStatusBarHeight());
                }

            }
        });


        /*弹出下拉别表搜索事件*/
        idLyAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listMenuDialog != null) {
                    backgroundAlpha(0.7f);
                    listMenuDialog.showAsDropDown(idRel2);
                    idIgNor.setImageResource(R.drawable.icon_list);
                }
            }
        });


        idGvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setPopupWindowDate(data.get(position));
                data.get(position).getId();
                Intent intent;
                if (isCustomized) {
                    intent = new Intent(OrderActivity.this, SimpleStyleInfromationActivity.class);
                } else {
                    intent = new Intent(OrderActivity.this, StyleInfromationActivity.class);
                }
                Bundle pBundle = new Bundle();
                L.e("itemId" + data.get(position).getId());
                pBundle.putString("itemId", data.get(position).getId());
                pBundle.putInt("type", 0);
                pBundle.putString("openType", openType + "");
                pBundle.putInt("waitOrderCount", waitOrderCount);
                if (selectStone != null) {
                    pBundle.putSerializable("stone", selectStone);
                }
                intent.putExtras(pBundle);
                // openActivity(StyleInfromationActivity.class, pBundle);
                startActivityForResult(intent, 10);
            }
        });


        idCurOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // openActivity(CustommadeInformationActivity.class, null);
            }
        });



        /*开始搜索事件*/
        idIgSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idEtSeach.getText().toString().equals("")) {
                    ToastManager.showToastReal("搜索内容为空！");
                    return;
                }
                curpage = 1;
                String url = getInitUrl();
                url += getkeyWordUrl();
                loadNetData(url);
            }
        });

        /*关键字标签清空*/
        idTvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTvFilter.setVisibility(View.GONE);
                idTvFilter.setText("");
                idEtSeach.setText("");
                loadNetData(getInitUrl());
            }
        });



        /*历史订单*/
        idTvHisOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowPrice) {
                    openActivity(CustomMadeActivity.class, null);
                }
            }
        });


        idTvCurOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, ConfirmOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("waitOrderCount", waitOrderCount);
                intent.putExtras(bundle);
                startActivityForResult(intent, 10);
                //openActivity(ConfirmOrderActivity.class,null);
            }
        });
        idTvClassify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++numColumns;
                idGvMenu.setNumColumns(numColumns % 4 + 3);
                Global.divideAmount = numColumns % 4 + 3;
                if(Global.divideAmount==3){
                    showToastReal("每行为3个时开启轮播");
                    startAutoPlay();
                }else {
                    stopAutoPlay();
                }
                idGvMenu.setAdapter(mGvAdapter);
            }
        });


    }
    private void setPopupWindowDate(ModeListResult.DataEntity.ModelEntity.ModelListEntity modelListEntity) {
        if( Global.ring==null){
            Global.ring= new Ring();
        }
        Global.ring.setImageUrl(modelListEntity.getPic());
        Global.ring.setItemId(modelListEntity.getId());
        Global.ring.setRingNumber(modelListEntity.getTitle());
         Global.ring.setRingWeightRange(modelListEntity.getWeightRange());
    }

    private BaseAdapter mGvAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(OrderActivity.this).inflate(R.layout.adapter_goods_list, parent, false);
                holder.lay = (LinearLayout) convertView.findViewById(R.id.img_container);
                holder.tv = (TextView) convertView.findViewById(R.id.name);
                holder.llPrice = (LinearLayout) convertView.findViewById(R.id.ll_price);
                holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_sum_price);
                holder.ig = (ImageView) convertView.findViewById(R.id.product_img);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            // holder.ig.setImageResource(R.drawable.no_image);
            ViewGroup.LayoutParams layoutParams = holder.ig.getLayoutParams();
            layoutParams.width=UIUtils.getWindowWidth()/Global.divideAmount;
            layoutParams.height = UIUtils.getWindowWidth() / Global.divideAmount;
            holder.ig.setLayoutParams(layoutParams);
            holder.tv.setText(data.get(position).getTitle());
            holder.tvPrice.setText(UIUtils.stringChangeToTwoBitDouble(data.get(position).getPrice()));
            if (data.get(position).getPic() == null || !data.get(position).getPic().equals(holder.ig.getTag())) {
                ImageLoader.getInstance().displayImage(data.get(position).getPicm(), holder.ig, ImageLoadOptions.getOptions());
                holder.ig.setTag(data.get(position).getPic());
            }
            if (curpage == 1) {
                if (isShowPrice) {
                    holder.llPrice.setVisibility(View.VISIBLE);
                } else {
                    holder.llPrice.setVisibility(View.GONE);
                }
            }

            return convertView;
        }

        class ViewHolder {
            LinearLayout lay;
           ImageView ig;
            TextView tv;
            TextView tvPrice;
            LinearLayout llPrice;
        }
    };

    public boolean isScreenChange() {

        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
//横屏
            return true;
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {

//竖屏
            return false;
        }
        return false;
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        if (data.size() < list_count) {
            tempCurpage = curpage;
            curpage++;
            pullState = PULL_LOAD;
            loadNetData(getInitUrl());
        } else {
            hint_txt.setText("已加载全部数据喲");
            ToastManager.showToastReal("没有更多数据");
            view.onFooterRefreshComplete();
        }
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        tempCurpage = curpage;
        curpage = 1;
        pullState = PULL_REFRESH;
        hint_txt.setText("上拉加载更多");
        loadNetData(getInitUrl());
    }


    /*扫描二维码页面*/
    public void scan(View view) {
        Intent inten = new Intent(OrderActivity.this, CaptureActivity.class);
        startActivityForResult(inten, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        L.e(requestCode + "");
        if (requestCode == 0 && data != null) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            idEtSeach.setText(result);
            String url = getInitUrl();
            url += getkeyWordUrl();
            loadNetData(url);
            L.e("onActivityResult result" + result);
        }

        if (requestCode == 10 && data != null) {
            Bundle bundle = data.getExtras();
            waitOrderCount = bundle.getInt("waitOrderCount");
            remind(waitOrderCount);
            L.e("waitOrderCount");
        }
    }

}
