package com.qx.mstarstoretv.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.qx.mstarstoretv.base.Global;
import com.qx.mstarstoretv.bean.Type;
import com.qx.mstarstoretv.inter.ConfirmOrderOnUpdate;
import com.qx.mstarstoretv.json.JewelStone;
import com.qx.mstarstoretv.json.ModelDetailResult;
import com.qx.mstarstoretv.json.StoneDetail;
import com.qx.mstarstoretv.json.StoneEntity;
import com.qx.mstarstoretv.json.StoneSearchInfoResult;
import com.qx.mstarstoretv.net.OKHttpRequestUtils;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.BadgeView;
import com.qx.mstarstoretv.viewutils.CustomLV;
import com.qx.mstarstoretv.viewutils.CustomSelectButton;
import com.qx.mstarstoretv.viewutils.CustomselectStringButton;
import com.qx.mstarstoretv.viewutils.FlyBanner;
import com.qx.mstarstoretv.viewutils.LeftPopupWindow;
import com.wx.wheelview.widget.WheelView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import q.rorbin.badgeview.QBadgeView;


/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class SimpleStyleInfromationActivity extends BaseActivity implements View.OnClickListener {

    List<StoneEntity> stoneEntities = new ArrayList<>();
    List<StoneEntity> stoneEntitiesTemp = new ArrayList<>();
    StoneEntity stone, stoneTemp, stoneATemp, stoneBTemp, stoneCTemp;
    ModelDetailResult.DataEntity.ModelEntity.StoneAEntity stoneA;
    ModelDetailResult.DataEntity.ModelEntity.StoneBEntity stoneB;
    ModelDetailResult.DataEntity.ModelEntity.StoneCEntity stoneC;

    List<ModelDetailResult.DataEntity.StoneTypeEntity> stoneTypeItme; //类型
    List<ModelDetailResult.DataEntity.StoneColorEntity> stoneColorItme;  //颜色
    List<ModelDetailResult.DataEntity.StonePurityEntity> stonePurityItme; //净度
    List<ModelDetailResult.DataEntity.StoneSpecEntity> stoneSpecItme;  //规格
    List<ModelDetailResult.DataEntity.StoneShapeEntity> stoneShapeItem;  //形状
    StoneDetail stoneDetail;
    List<ModelDetailResult.DataEntity.ModelEntity.PicsEntity> pics;
    ModelDetailResult.DataEntity.ModelEntity modelEntity;
    List<ModelDetailResult.DataEntity.RemarksEntity> remarksEntity;//备注
    Type remar = new Type();  //提交的
    String categoryTitle, storeNumber, storeSize, categoryId;
    @Bind(R.id.flybanner)
    FlyBanner flybanner;
    @Bind(R.id.iv_preview)
    ImageView ivPreview;
    @Bind(R.id.tv_preview_title)
    TextView tvPreviewTitle;
    @Bind(R.id.ll_banner)
    LinearLayout llBanner;
    @Bind(R.id.id_store_title)
    TextView idStoreTitle;
    @Bind(R.id.tv_weight)
    TextView tvWeight;
    @Bind(R.id.rl_top)
    RelativeLayout rlTop;
    @Bind(R.id.id_store_title2)
    TextView idStoreTitle2;
    @Bind(R.id.tv_weight2)
    TextView tvWeight2;
    @Bind(R.id.rl_top2)
    RelativeLayout rlTop2;
    @Bind(R.id.tv_product_color)
    CustomSelectButton tvProductColor;
    @Bind(R.id.ll_color)
    LinearLayout llColor;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.ll_type)
    LinearLayout llType;
    @Bind(R.id.tv_amount_title)
    TextView tvAmountTitle;
    @Bind(R.id.iv_reduce)
    ImageView ivReduce;
    @Bind(R.id.et_weight)
    EditText idCusStoreNumber;
    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.ll_amount)
    LinearLayout llAmount;
    @Bind(R.id.tv_certcode)
    TextView tvCertcode;
    @Bind(R.id.ll_certcode)
    LinearLayout llCertcode;
    @Bind(R.id.id_cus_store_size)
    CustomselectStringButton idCusStoreSize;
    @Bind(R.id.id_list)
    CustomLV listView;
    @Bind(R.id.tv_reset)
    TextView tvReset;
    @Bind(R.id.tv_del)
    TextView tvDel;
    @Bind(R.id.et_making)
    EditText etMaking;
    @Bind(R.id.iv_heart)
    ImageView ivHeart;
    @Bind(R.id.iv_yu)
    ImageView ivYu;
    @Bind(R.id.tv_preview)
    TextView tvPreview;
    @Bind(R.id.ll_making)
    LinearLayout llMaking;
    @Bind(R.id.id_cus_store_remarkid)
    CustomSelectButton idCusStoreRemarkid;
    @Bind(R.id.id_tv_store_remarks)
    EditText idTvStoreRemarks;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.space_view)
    View spaceView;
    @Bind(R.id.id_tv_curorder)
    TextView idTvCurorder;
    @Bind(R.id.ll_curorder)
    LinearLayout llCurorder;
    @Bind(R.id.id_fr)
    FrameLayout idFr;
    @Bind(R.id.tv_price_title)
    TextView tvPriceTitle;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.ll_show_less)
    LinearLayout llShowLess;
    @Bind(R.id.tips_loading_msg)
    TextView tipsLoadingMsg;
    @Bind(R.id.lny_loading_layout)
    LinearLayout lnyLoadingLayout;
    @Bind(R.id.line_making)
    View lineMaking;

    private View rootView;
    private ModelDetailResult.DataEntity dataEntity;
    private String certCode = "";//石头证书号
    private String stoneprice;//石头价格
    private String openType = "0"; //1是从石头搜索回来,2是从主石选择回来
    private String MainStone = "主    石";
    private String weight;
    private StoneSearchInfoResult.DataBean.StoneBean.ListBean selectedStone;
    private JewelStone jewelStone;
    private ListAdapter adapter;
    private StoneEntity selectedStoneEnity;
    private ModelDetailResult modelDetail;
    private ArrayList<String> getPics;
    private Boolean isShowPrice;
    WheelView mainWheelView;
    int type = 0;//0正常，2编辑
    String orderId;
    int waitOrderCount;
    String itemId;
    static ConfirmOrderOnUpdate confirmOrderOnUpdate;
    private LeftPopupWindow leftPopupWindow;
    private List<ModelDetailResult.DataEntity.ModelPuritys> modelPuritys;
    private boolean isnumberCanChange = true;
    DecimalFormat df = new DecimalFormat("######0.00");

    public static void setConfirmOrderOnUpdate(ConfirmOrderOnUpdate confirmOrderOnUpdate) {
        SimpleStyleInfromationActivity.confirmOrderOnUpdate = confirmOrderOnUpdate;
    }

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initWindwoState();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_style_simple_information);
        ButterKnife.bind(this);
        rootView = View.inflate(this, R.layout.activity_style_simple_information, null);
        isShowPrice = SpUtils.getInstace(this).getBoolean("isShowPrice", true);
        getIntentData(getIntent());
        loadNetData();
    }


    /**
     * 动态的设置状态栏  实现沉浸式状态栏
     */
    private void initWindwoState() {
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getIntentData(intent);
        if (Global.isShowPopup != 0 && leftPopupWindow != null) {
            leftPopupWindow.initPopupView();
        }
        if ((type == 2 || type == 1)&&!openType.equals("2")) {
            loadNetData();
        }

        initView();
    }

    private void getIntentData(Intent intent) {
        Bundle extras = intent.getExtras();
        itemId = extras.getString("itemId");
        type = extras.getInt("type");
        orderId = extras.getString("orderId");
        waitOrderCount = extras.getInt("waitOrderCount", 0);
        openType = extras.getString("openType");
        selectedStoneEnity = (StoneEntity) extras.getSerializable("stoneResult");
        if (selectedStoneEnity != null && stoneEntities.size() > 0) {
            stoneEntities.get(0).changetoStone(selectedStoneEnity);
            stoneprice = selectedStoneEnity.getPrice();

        }
        selectedStone = (StoneSearchInfoResult.DataBean.StoneBean.ListBean) extras.getSerializable("stone");
        if (selectedStone != null) {
            weight = selectedStone.getWeight();
            certCode = selectedStone.getCertCode();
            stoneprice = selectedStone.getPrice();
        } else {
            selectedStone = new StoneSearchInfoResult.DataBean.StoneBean.ListBean();
        }

        if (openType == null) {
            openType = "0";
        }

        if (adapter != null) {

            tvPrice.setText("¥" + UIUtils.stringChangeToInt(Double.parseDouble(toEmpty(modelEntity.getPrice())) * Double.parseDouble(toEmpty(modelEntity.getNumber())) + Double.parseDouble(toEmpty(stoneprice)) + ""));

            listView.setAdapter(adapter);
        }
        numberIsChange();
    }

    public String toEmpty(String st) {
        return Double.parseDouble(StringUtils.isEmpty(st) ? "0" : st) + "";
    }

    public void numberIsChange() {

        if (openType.equals("1")) {
            isnumberCanChange = false;
            idCusStoreNumber.setText("1");
            idCusStoreNumber.setEnabled(false);
        } else if (openType.equals("2")) {
            isnumberCanChange = true;
            idCusStoreNumber.setEnabled(true);
        } else {
            if (!certCode.isEmpty()) {
                isnumberCanChange = false;
                idCusStoreNumber.setText("1");
                idCusStoreNumber.setEnabled(false);
            } else {
                isnumberCanChange = true;
                idCusStoreNumber.setEnabled(true);
            }
        }
        if(Global.isShowPopup!=0){
            isnumberCanChange = false;
            idCusStoreNumber.setText("1");
            idCusStoreNumber.setEnabled(false);
        }
    }

    private void initView() {
        if (isShowPrice) {
            tvPrice.setVisibility(View.VISIBLE);
            tvPriceTitle.setVisibility(View.VISIBLE);
        } else {
            tvPrice.setVisibility(View.GONE);
            tvPriceTitle.setVisibility(View.GONE);
        }
        // titleText.setText("款式信息");
        tvSearch.setText(R.string.add_curent_order);
        idTvCurorder.setText(R.string.look_current_order);
//        idCusStoreSize.setBackgroundId(R.color.white);

        if (type == 1 || type == 2) {
            tvSearch.setText(R.string.confirm_update);
            idTvCurorder.setText(R.string.cancle_update);
            if (badge != null) {
                badge.setVisibility(View.GONE);
            }
        }
        numberIsChange();

        tvSearch.setOnClickListener(this);
        badge = new QBadgeView(this);// 创建一个BadgeView对象，view为你需要显示提醒的控件
        badge.bindTarget(idTvCurorder);
        remind(waitOrderCount);
        adapter = new ListAdapter(stoneEntities);
        listView.setAdapter(adapter);
        idIgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("waitOrderCount", waitOrderCount);
                setResult(10, intent);
                finish();
            }
        });

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        ivReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reduce();
            }
        });
        tvDel.setOnClickListener(this);
        tvReset.setOnClickListener(this);
           /*    查看当前订单*/
        idTvCurorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addCurentOrder();
                //  openActivity(ConfirmOrderActivity.class,null);
                /*确定页面的取消修改*/
                System.out.println("type=" + type);
                if (type == 1 || type == 2) {
                    finish();
                } else {
                    /*查看当前订单*/
                    Intent intent = new Intent(SimpleStyleInfromationActivity.this, ConfirmOrderActivity.class);
                    intent.putExtra("waitOrderCount", waitOrderCount);
                    startActivityForResult(intent, 12);
                    //设置切换动画，从右边进入，左边退出
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }
            }
        });

        initCustomselect();
        //快速定制
        if (type == 0) {        //避免修改时进来
            if (Global.isShowPopup != 0) {
                llShowLess.setVisibility(View.VISIBLE);
                llColor.setVisibility(View.VISIBLE);
                llMaking.setVisibility(View.VISIBLE);
                rlTop2.setVisibility(View.VISIBLE);
                lineMaking.setVisibility(View.VISIBLE);
                rlTop.setVisibility(View.GONE);
                llType.setVisibility(View.GONE);
                idFr.setVisibility(View.GONE);
                initPopwindow();
                tvSearch.setText(R.string.confirm_making_order);
                if (Global.ring.getRingPurityId() != null) {
                    tvProductColor.setTextName(Global.ring.getRingPurity());
                }
                if (Global.ring.getHandSize() != null) {
                    idCusStoreSize.setTextName(Global.ring.getHandSize());
                }
                if (Global.ring.getWord() != null) {
                    UIUtils.setEdittextToEnd(etMaking, Global.ring.getWord());
                }

                Global.ring.setRingPrice(dataEntity.getModel().getPrice());
            } else {
                llShowLess.setVisibility(View.GONE);
                llColor.setVisibility(View.GONE);
                llMaking.setVisibility(View.GONE);
                rlTop2.setVisibility(View.GONE);
                lineMaking.setVisibility(View.GONE);
                rlTop.setVisibility(View.VISIBLE);
                llType.setVisibility(View.VISIBLE);
                idFr.setVisibility(View.VISIBLE);
                tvSearch.setText(R.string.add_curent_order);
            }
        } else {
            if (Global.isShowPopup == 0 && leftPopupWindow != null) {
                leftPopupWindow.closePopupWindow();
                llShowLess.setVisibility(View.GONE);
            }
            llShowLess.setVisibility(View.GONE);
            llColor.setVisibility(View.GONE);
            llMaking.setVisibility(View.GONE);
            rlTop2.setVisibility(View.GONE);
            lineMaking.setVisibility(View.GONE);
            rlTop.setVisibility(View.VISIBLE);
            llType.setVisibility(View.VISIBLE);
            idFr.setVisibility(View.VISIBLE);
        }


        tvPreview.setOnClickListener(this);
        ivHeart.setOnClickListener(this);
        ivYu.setOnClickListener(this);

        idCusStoreNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvPrice.setText("¥" + UIUtils.stringChangeToInt(Double.parseDouble(toEmpty(modelEntity.getPrice())) * Double.parseDouble(toEmpty(idCusStoreNumber.getText().toString()))
                        + Double.parseDouble(toEmpty(stoneprice)) + ""));
            }
        });
    }

    private void initCustomselect() {
        idCusStoreSize.setDefaultText("手寸");
        idCusStoreSize.setOnSelectData(new CustomselectStringButton.OnselectData() {
            @Override
            public List<String> getData() {
                return Arrays.asList(dataEntity.getHandSizeData());
            }

            @Override
            public void getSelectId(String type) {
                idCusStoreSize.setTextName(type);
                //快速设置设置手寸大小
                if (Global.isShowPopup != 0) {
                    Global.ring.setHandSize(type);
                }
            }

            @Override
            public int defaultPosition() {
                return 22;
            }

            @Override
            public String getTitle() {
                return "手寸";
            }

            @Override
            public View getRootView() {
                return idCusStoreSize;
            }
        });

        idCusStoreRemarkid.setOnSelectData(new CustomSelectButton.OnselectData() {
            @Override
            public List<Type> getData() {
                List<Type> list = new ArrayList<>();
                if (remarksEntity.size() > 0) {
                    for (int i = 0; i < remarksEntity.size(); i++) {
                        Type type = new Type();
                        type.setId(remarksEntity.get(i).getId());
                        type.setTypeName(remarksEntity.get(i).getTitle());
                        type.setContent(remarksEntity.get(i).getContent());
                        list.add(type);
                    }
                }

                return list;
            }

            @Override
            public View getRootView() {
                return idCusStoreRemarkid;
            }


            @Override
            public void getSelectId(Type type) {
                remar = type;
                String curentRemar = idTvStoreRemarks.getText().toString();
                idTvStoreRemarks.setText(curentRemar + type.getContent());
                Global.ring.setRemarks(type.getContent());

            }

            @Override
            public String getTitle() {
                return "选择备注";
            }
        });

        tvProductColor.setOnSelectData(new CustomSelectButton.OnselectData() {
            @Override
            public List<Type> getData() {
                List<Type> list = new ArrayList<>();
                if (modelPuritys != null) {
                    for (int i = 0; i < modelPuritys.size(); i++) {
                        Type type = new Type();
                        type.setId(modelPuritys.get(i).getId());
                        type.setTypeName(modelPuritys.get(i).getTitle());
                        list.add(type);
                    }
                }
                return list;
            }

            @Override
            public View getRootView() {
                return tvProductColor;
            }


            @Override
            public void getSelectId(Type type) {
                tvProductColor.setText(type.getTypeName());
                Global.ring.setRingPurity(type.getTypeName());
                Global.ring.setRingPurityId(type.getId());
            }

            @Override
            public String getTitle() {
                return "选择成色";
            }
        });
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

    private void selectStone() {
        Intent intent = new Intent(SimpleStyleInfromationActivity.this, StoneChooseMainActivity.class);
//        Intent intent = new Intent(SimpleStyleInfromationActivity.this, StoneSearchInfoActivity.class);
        intent.putExtra("openType", 1);
        intent.putExtra("type", type);
        intent.putExtra("itemId", itemId);
        intent.putExtra("orderId", orderId);
        intent.putExtra("isCanSelectStone", modelDetail.getData().getIsCanSelectStone());
        intent.putExtra("stoneDetail", stoneDetail);
        intent.putExtra("stone", stone);
        intent.putExtra("stoneRange", modelEntity.getRingWeightRange());
        startActivity(intent);
        //设置切换动画，从右边进入，左边退出
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    private void reduce() {
        if (!isnumberCanChange) {
            ToastManager.showToastReal("数量唯一，不能修改！");
            return;
        }
        String st = idCusStoreNumber.getText().toString();
        double amount;
        if (st.equals("")) {
            amount = 0;
        } else {
            amount = Double.parseDouble(st);
        }

        if (amount > 0 && amount == 0.5) {
            idCusStoreNumber.setText(amount - 0.5 + "");
        } else if (amount == 1) {
            idCusStoreNumber.setText("");
        } else if (amount > 1) {
            if (amount % 1 == 0.5) {
                idCusStoreNumber.setText(amount - 1 + "");
            } else {
                idCusStoreNumber.setText((int) Math.floor(amount - 1) + "");
            }
        }
        tvPrice.setText("¥" + UIUtils.stringChangeToInt(Double.parseDouble(toEmpty(modelEntity.getPrice())) * Double.parseDouble(toEmpty(idCusStoreNumber.getText().toString()))
                + Double.parseDouble(toEmpty(stoneprice)) + ""));
    }

    private void add() {
        if (!isnumberCanChange) {
            ToastManager.showToastReal("数量唯一，不能修改！");
            return;
        }
        String st = idCusStoreNumber.getText().toString();
        double amount;
        if (st.equals("") || st.equals("0.0")) {
            amount = 0;
        } else {
            amount = Double.parseDouble(st);
        }

        if (amount >= 0 && amount < 0.5) {
            idCusStoreNumber.setText((int) (amount + 1) + "");
        } else if (amount == 0.5) {
            idCusStoreNumber.setText(amount + 1 + "");
        } else if (amount >= 1) {
            if (amount % 1 == 0.5) {
                idCusStoreNumber.setText(amount + 1 + "");
            } else {
                idCusStoreNumber.setText((int) Math.floor(amount + 1) + "");
            }

        }
        tvPrice.setText("¥" + UIUtils.stringChangeToInt(Double.parseDouble(toEmpty(modelEntity.getPrice())) * Double.parseDouble(toEmpty(idCusStoreNumber.getText().toString()))
                + Double.parseDouble(toEmpty(stoneprice)) + ""));
    }

    QBadgeView badge;


    private void remind(int count) {
        boolean isVisible = false;
        if (count != 0) {
            isVisible = true;
        }

        //BadgeView的具体使用
        badge.setBadgeText(" "+count + " "); // 需要显示的提醒类容
        badge.setBadgeGravity(Gravity.END|Gravity.TOP);// 显示的位置.右上角,BadgeView.POSITION_BOTTOM_LEFT,下左，还有其他几个属性
        badge.setBadgeTextColor(Color.WHITE); // 文本颜色
        int hint = Color.rgb(200, 39, 73);
        badge.setBadgeBackgroundColor(hint); // 提醒信息的背景颜色，自己设置
        badge.setBadgeTextSize(12,true); // 文本大小
        badge.setBadgePadding(3,true); // 水平和竖直方向的间距+-
        if (isVisible) {
            badge.setVisibility(View.VISIBLE);  // 只有显示
        } else {
            badge.setVisibility(View.GONE);//影藏显示
        }
    }

    @Override
    public void loadNetData() {
        lnyLoadingLayout.setVisibility(View.VISIBLE);
        String url;
        if (type == 1) {
            url = AppURL.URL_MODEL_UPDATE + "tokenKey=" + BaseApplication.getToken() + "&itemId=" + itemId;
        } else if (type == 2) {
            url = AppURL.URL_ORDER_MODEL_UPDATE + "tokenKey=" + BaseApplication.getToken() + "&itemId=" + itemId;
        } else {
            url = AppURL.URL_MODEL_DETAIL + "tokenKey=" + BaseApplication.getToken() + "&id=" + itemId;
            //款号页面
        }
        L.e("获取款号" + url + "    Type=   " + type);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("success", result);
                stoneEntities.clear();
                lnyLoadingLayout.setVisibility(View.GONE);
                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    modelDetail = new Gson().fromJson(result, ModelDetailResult.class);
                    dataEntity = modelDetail.getData();
                    if (dataEntity == null) {
                        return;
                    }

                    List<ModelDetailResult.DataEntity.GoldenPriceEntity> goldenPrice = dataEntity.getGoldenPrice();
                    remarksEntity = dataEntity.getRemarks();
                    modelEntity = dataEntity.getModel();
                    modelPuritys = dataEntity.getModelPuritys();
                    categoryTitle = modelEntity.getCategoryTitle();
                    categoryId = modelEntity.getCategoryId();
                    /*是否自带主石*/

                    //L.e("服务器返回是否自带主石头"+isSelfStone);
                    /*得到数量*/
                    if (modelEntity.getNumber() == null) {
                        modelEntity.setNumber("1");
                    }
                    String number = modelEntity.getNumber();
                    String remark = modelEntity.getRemark();
                    String handSize = modelEntity.getHandSize();

                    pics = modelEntity.getPics();
                    stone = modelEntity.getStone();
                    stoneA = modelEntity.getStoneA();
                    stoneB = modelEntity.getStoneB();
                    stoneC = modelEntity.getStoneC();
                    jewelStone = dataEntity.getJewelStone();

                    stoneTypeItme = dataEntity.getStoneType();
                    stoneColorItme = dataEntity.getStoneColor();
                    stonePurityItme = dataEntity.getStonePurity();
                    stoneSpecItme = dataEntity.getStoneSpec();
                    stoneShapeItem = dataEntity.getStoneShape();

                    stoneDetail = new StoneDetail(stoneTypeItme, stoneColorItme, stonePurityItme, stoneSpecItme, stoneShapeItem);

                    if (type == 2 && selectedStone.getCertCode() != null) {
                        if(jewelStone!=null){
                            jewelStone.setJewelStoneId(selectedStone.getId());
                        }
                        stoneprice = selectedStone.getPrice();
                    } else if (selectedStone.getCertCode() == null && jewelStone != null) {
                        stoneprice = jewelStone.getJewelStonePrice();
                        selectedStone.setId(jewelStone.getJewelStoneId());
                    }
                    if (jewelStone != null) {
                        tvCertcode.setText(jewelStone.getJewelStoneCode());
                        certCode = jewelStone.getJewelStoneCode();
                        idCusStoreNumber.setText("1");
                        stone.setColorTitle(jewelStone.getJewelStoneColor());
                        stone.setPurityTitle(jewelStone.getJewelStonePurity());
                        stone.setPrice(jewelStone.getJewelStonePrice());
                        stone.setSpecTitle(jewelStone.getJewelStoneWeight());
                        stone.setTypeId(stoneTypeItme.get(0).getId());
                        stone.setTypeTitle(stoneTypeItme.get(0).getTitle());
                        stone.setShapeId(stoneShapeItem.get(0).getId());
                        stone.setShapeTitle(stoneShapeItem.get(0).getTitle());
                        stone.setNumber("1");
                        stone.setSpecTitle("1");
                        stone.setColorId(stoneColorItme.get(0).getId());
                        stone.setPurityId(stonePurityItme.get(0).getId());
                        stone.setIsNotEmpty(1);
                        stone.setStoneCode(jewelStone.getJewelStoneCode());

                    } else {
                        tvAmountTitle.setText("件    数");
                        llAmount.setVisibility(View.VISIBLE);
                        llCertcode.setVisibility(View.GONE);
                    }
                    stone.setIsSelfStone(modelEntity.getIsSelfStone());
                    if (modelEntity.getIsSelfStone() == 1) {
                        stone.setChecked(true);
                    }
                    if (stoneA.getStoneOut().equals("1")) {
                        stoneA.setChecked(true);
                        stoneA.setIsSelfStone(modelEntity.getIsSelfStone());
                    }
                    if (stoneB.getStoneOut().equals("1")) {
                        stoneB.setChecked(true);
                        stoneB.setIsSelfStone(modelEntity.getIsSelfStone());
                    }
                    if (stoneC.getStoneOut().equals("1")) {
                        stoneC.setChecked(true);
                        stoneC.setIsSelfStone(modelEntity.getIsSelfStone());
                    }
                    if (openType.equals("2")) {
                        stone.setIsNotEmpty(1);
                    }
                    addEntities(stone, stoneA, stoneB, stoneC);


                    setStorePrice(stone);
                    setStorePrice(stoneA);
                    setStorePrice(stoneB);
                    setStorePrice(stoneC);

                    L.e("categoryTitle" + categoryTitle);
                    idStoreTitle.setText(modelEntity.getTitle());
                    tvWeight.setText(modelEntity.getWeight());
                    tvType.setText(categoryTitle);
                    //快速定制
                    if (!modelEntity.getCategoryTitle().contains("戒")) {
                        tvPreview.setVisibility(View.GONE);
                    } else {
                        tvPreview.setVisibility(View.VISIBLE);
                    }
                    idStoreTitle2.setText(modelEntity.getTitle() + " " + categoryTitle);
                    tvWeight2.setText(modelEntity.getWeight());
                    if (!StringUtils.isEmpty(number)) {
                        idCusStoreNumber.setText("" + number);
                    }
                    if (!StringUtils.isEmpty(remark)) {
                        idTvStoreRemarks.setText("" + remark);
                    }
                    idCusStoreSize.setTextName(handSize);
                    tvPrice.setText("¥" + UIUtils.stringChangeToInt(Double.parseDouble(toEmpty(modelEntity.getPrice())) * Double.parseDouble(toEmpty(modelEntity.getNumber())) + Double.parseDouble(toEmpty(stoneprice)) + ""));


                    initViewPager();
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < goldenPrice.size(); i++) {
                        sb.append(goldenPrice.get(i).getTitle() + " " + goldenPrice.get(i).getPrice() + "      ");
                    }

                    initView();
                    adapter.notifyDataSetChanged();
                } else if (error == 2) {
                    ToastManager.showToastReal(getString(R.string.data_error));
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal("数据加载错误:+" + message);
                }

            }

            @Override
            public void onFail(String fail) {
                lnyLoadingLayout.setVisibility(View.GONE);
                showToastReal(fail);
                showToastReal("数据获取失败");
            }

        });


    }

    /**
     * 备份stone数据，判断是否显示，添加到list 中
     *
     * @param stone
     * @param stoneA
     * @param stoneB
     * @param stoneC
     */
    private void addEntities(StoneEntity stone, ModelDetailResult.DataEntity.ModelEntity.StoneAEntity stoneA, ModelDetailResult.DataEntity.ModelEntity.StoneBEntity stoneB, ModelDetailResult.DataEntity.ModelEntity.StoneCEntity stoneC) {
        stoneTemp = copyStone(stone);
        stoneEntitiesTemp.add(stoneTemp);
        stoneATemp = copyStone(stoneA);
        stoneEntitiesTemp.add(stoneATemp);
        stoneBTemp = copyStone(stoneB);
        stoneEntitiesTemp.add(stoneBTemp);
        stoneCTemp = copyStone(stoneC);
        stoneEntitiesTemp.add(stoneCTemp);


        stoneEntities.add(stone);

        if (stoneA.getIsNotEmpty() == 1) {
            stoneEntities.add(stoneA);
        }
        if (stoneB.getIsNotEmpty() == 1) {
            stoneEntities.add(stoneB);
        }
        if (stoneC.getIsNotEmpty() == 1) {
            stoneEntities.add(stoneC);
        }

    }

    public boolean isEmptyStone(StoneEntity stoEntity) {
        boolean isEmpty = false;
        if (StringUtils.isEmpty(stoEntity.getNumber()) &&
                StringUtils.isEmpty(stoEntity.getShapeId()) && StringUtils.isEmpty(stoEntity.getPurityTitle())
                && StringUtils.isEmpty(stoEntity.getColorId()) && StringUtils.isEmpty(stoEntity.getTypeId())) {
            isEmpty = true;
        }
        return isEmpty;
    }


    public void initViewPager() {
        if (!UIUtils.isScreenChange(this)) {
            ViewGroup.LayoutParams lp = flybanner.getLayoutParams();
            int screenWidth = UIUtils.getWindowWidth();
            lp.height = (int) (screenWidth);
            flybanner.setLayoutParams(lp);
        } else {
            ViewGroup.LayoutParams lp = llBanner.getLayoutParams();
            int screenWidth = UIUtils.getWindowWidth();
            int screenHeight = UIUtils.getWindowHight();
            lp.height = Math.min(screenWidth, screenHeight);
            lp.width = Math.min(screenWidth, screenHeight);
            llBanner.setLayoutParams(lp);

            ViewGroup.LayoutParams lp2 = spaceView.getLayoutParams();
            lp2.width = screenHeight;
            spaceView.setLayoutParams(lp2);
        }
        /**
         * 创建多个item （每一条viewPager都是一个item） 从服务器获取完数据（图片url地址） 后，再设置适配器
         */
        getPics = new ArrayList<>();
        for (int i = 0; i < pics.size(); i++) {
            getPics.add(pics.get(i).getPicb());
        }
        flybanner.setImagesUrl(getPics);
        flybanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (pics.size() == 0 && StringUtils.isEmpty(pics.get(0).getPicb())) {
                    return;
                }
                //主页图片
                Intent intent = new Intent(SimpleStyleInfromationActivity.this,
                        ImageBrowserActivity.class);
                intent.putExtra("photos", getPics);
                intent.putExtra("position", position);
                startActivity(intent);
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                if (Global.isShowPopup != 0) {
                    Global.ring.setNumber(idCusStoreNumber.getText().toString());
                    Global.ring.setWord(etMaking.getText().toString());
                    isFinishChooseRing();
                } else {
                    addCurentOrder();
                }
                break;
            case R.id.tv_reset:
                reset();
                break;
            case R.id.tv_del:
                delStone();
                break;
            case R.id.tv_preview:
                showPreview();
                break;
            case R.id.iv_heart:
                UIUtils.setEdittextToEnd(etMaking, etMaking.getText().toString() + "❤");
                break;
            case R.id.iv_yu:
                UIUtils.setEdittextToEnd(etMaking, etMaking.getText().toString() + "&");
                break;
        }
    }

    private void showPreview() {
        tvPreviewTitle.setText(etMaking.getText().toString());
        tvPreviewTitle.setVisibility(View.VISIBLE);
        ivPreview.setVisibility(View.VISIBLE);
    }

    private void isFinishChooseRing() {
        if (Global.ring.getRingPurity() == null) {
            showToastReal("请选择成色");
            return;
        }
        if (leftPopupWindow != null) {
            leftPopupWindow.showPop(rootView);
        }
        if (Global.ring.getStoneEntity() == null) {
            showToastReal("请选择裸石");
            return;
        }
        if (Global.ring.getAddressEntity() == null || Global.ring.getCustomerEntity() == null) {
            showToastReal("请选择信息");
            return;
        }
        showToastReal("请确认定制");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (leftPopupWindow != null) {
            leftPopupWindow.closePopupWindow();
        }
    }

    public StoneEntity copyStone(StoneEntity stoneEntity) {
        StoneEntity stoneCopy = new StoneEntity();
        stoneCopy.setChecked(stoneEntity.isChecked());
        stoneCopy.setStroneName(stoneEntity.getStroneName());
        stoneCopy.setPrice(stoneEntity.getPrice());
        stoneCopy.setShapeId(stoneEntity.getSpecId());
        stoneCopy.setNumber(stoneEntity.getNumber());
        stoneCopy.setShapeId(stoneEntity.getShapeId());
        stoneCopy.setPurityTitle(stoneEntity.getPurityTitle());
        stoneCopy.setShapeTitle(stoneEntity.getShapeTitle());
        stoneCopy.setColorId(stoneEntity.getColorId());
        stoneCopy.setTypeId(stoneEntity.getTypeId());
        stoneCopy.setSpecTitle(stoneEntity.getSpecTitle());
        stoneCopy.setColorTitle(stoneEntity.getColorTitle());
        stoneCopy.setTypeTitle(stoneEntity.getTypeTitle());
        stoneCopy.setPurityId(stoneEntity.getPurityId());
        stoneCopy.setSpecSelectTitle(stoneEntity.getSpecSelectTitle());
        stoneCopy.setIsSelfStone(stoneEntity.getIsSelfStone());
        stoneCopy.setIsNotEmpty(stoneEntity.getIsNotEmpty());
        stoneCopy.setStoneOut(stoneEntity.getStoneOut());
        return stoneCopy;
    }


    /**
     * 判断添加对应的stone
     */
    private void addStone() {
        switch (stoneEntities.size()) {
            case 0:
                stoneEntities.add(copyStone(stoneTemp));
                break;
            case 1:
                stoneEntities.add(copyStone(stoneATemp));
                break;
            case 2:
                stoneEntities.add(copyStone(stoneBTemp));
                break;
            case 3:
                stoneEntities.add(copyStone(stoneCTemp));
                break;
            case 4:
                ToastManager.showToastReal("不能再添加了！");
                break;
        }
    }

    /**
     * 判断删除对应的stone
     */
    private void delStone() {
        switch (stoneEntities.size()) {
            case 1:
                stoneEntities.remove(0);
                break;
            case 2:
                stoneEntities.remove(1);
                break;
            case 3:
                stoneEntities.remove(2);
                break;
            case 4:
                stoneEntities.remove(3);
                break;
        }

    }

    /**
     * 判断重置对应的stone
     */
    private void reset() {
        switch (stoneEntities.size()) {
            case 1:
                stoneEntities.remove(0);
                stoneEntities.add(copyStone(stoneTemp));
                tvAmountTitle.setText("件    数");
                openType = "0";
                selectedStone.setId("");
                llAmount.setVisibility(View.VISIBLE);
                llCertcode.setVisibility(View.GONE);
                break;
            case 2:
                stoneEntities.remove(1);
                stoneEntities.add(copyStone(stoneATemp));
                break;
            case 3:
                stoneEntities.remove(2);
                stoneEntities.add(copyStone(stoneBTemp));
                break;
            case 4:
                stoneEntities.remove(3);
                stoneEntities.add(copyStone(stoneCTemp));
                break;
        }
    }


    /**
     * 提交订单
     **/
    private void addCurentOrder() {
        // OrderDoCurrentModelItemDo?productId=1&categoryId=8&number=2&handSize=3&stone=1|3|2|3|4|5&stoneA=1|2|2|3|4|5&
        // stoneB=1|2|3|3|4|5&stoneC=1|2|3|3|4|6&tokenKey=10b588002228fa805231a59bb7976bf4
        String url = "";
        StoneEntity stone = null, stoneA = null, stoneB = null, stoneC = null;
        String remark = idTvStoreRemarks.getText().toString();
        storeNumber = idCusStoreNumber.getText().toString();
        if (!isNumber(storeNumber)) {
            ToastManager.showToastReal("件数只许整形或者0.5");
            return;
        }
        storeSize = idCusStoreSize.getTextName().toString();
        if (stoneEntities.size() > 0) {
            stone = stoneEntities.get(0);
        } else {
            stone = stoneATemp;
        }

        if (stoneEntities.size() > 1) {
            stoneA = stoneEntities.get(1);
        } else {
            stoneA = stoneATemp;
        }
        if (stoneEntities.size() > 2) {
            stoneB = stoneEntities.get(2);
        } else {
            stoneB = stoneBTemp;
        }

        if (stoneEntities.size() > 3) {
            stoneC = stoneEntities.get(3);
        } else {
            stoneC = stoneCTemp;
        }
        if (selectedStone == null) {
            selectedStone = new StoneSearchInfoResult.DataBean.StoneBean.ListBean();
        }
        String urlStroe = objectisEmptyAndtoJson("stone", stone);
        String urlStroeA = objectisEmptyAndtoJson("stoneA", stoneA);
        String urlStroeB = objectisEmptyAndtoJson("stoneB", stoneB);
        String urlStroeC = objectisEmptyAndtoJson("stoneC", stoneC);
        if (type == 1) {
            url = AppURL.URL_CURRENT_EDIT_ORDER1 + "tokenKey=" + BaseApplication.getToken() + "&itemId=" + itemId + "&number=" + storeNumber
                    + "&handSize=" + storeSize + urlStroe + urlStroeA + urlStroeB + urlStroeC + "&isSelfStone=" + stone.getIsSelfStone() + "" + "&remarks=" + remark + "&jewelStoneId=" + selectedStone.getId();
        } else if (type == 2) {
            url = AppURL.URL_UPDATE_ORDER_WATET1 + "tokenKey=" + BaseApplication.getToken() + "&itemId=" + itemId + "&number=" + storeNumber
                    + "&handSize=" + storeSize + urlStroe + urlStroeA + urlStroeB + urlStroeC + "&isSelfStone=" + stone.getIsSelfStone() + "" + "&remarks=" + remark + "&jewelStoneId=" + selectedStone.getId();
        } else {
            url = AppURL.URL_CURRENT_ORDER1 + "tokenKey=" + BaseApplication.getToken() + "&productId=" + itemId + "&categoryId=" + categoryId +
                    "&number=" + storeNumber + "&handSize=" + storeSize + urlStroe + urlStroeA + urlStroeB + urlStroeC + "&isSelfStone=" + stone.getIsSelfStone() + "" + "&remarks=" + remark + "&jewelStoneId=" + selectedStone.getId();
        }
        L.e("提交订单" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("提交订单 result" + result);
                if (new Gson().fromJson(result, JsonObject.class).get("error").getAsString().equals("0")) {
                    tvSearch.setEnabled(true);
                    if (type == 1 || type == 2) {
                        ToastManager.showToastReal("修改成功");
                        if (confirmOrderOnUpdate != null) {
                            confirmOrderOnUpdate.onUpdate();
                            Bundle bundle = new Bundle();
                            bundle.putInt("type", type);
                            bundle.putString("itemId", orderId);
                            openActivity(ConfirmOrderActivity.class, bundle);
                        }
                    } else {
                        String strwaitOrderCount = new Gson().fromJson(result, JsonObject.class).get("data").getAsJsonObject().get("waitOrderCount").getAsString();
                        waitOrderCount = Integer.valueOf(strwaitOrderCount);
                        L.e("waitOrderCount" + waitOrderCount);
                        ToastManager.showToastReal("添加成功");
                        remind(waitOrderCount);
                        tvSearch.setEnabled(true);
                        return;
                    }
                    //finish();
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);
                    tvSearch.setEnabled(true);
                }
            }

            @Override
            public void onFail(String fail) {
                showToastReal("数据获取失败");
            }

        });
    }


    /*验证数字是否是整形和0.5*/
    public boolean isNumber(String storeNumber) {
        if (!StringUtils.isEmpty(storeNumber)) {
            double d = Double.parseDouble(storeNumber);
            if (d % 1 == 0 || (d % 1 == 0.5)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将选择的数据转化为JSON字符串
     * *
     */
    public String objectisEmptyAndtoJson(String key, StoneEntity stoEntity) {
        List list = new ArrayList();
        //如果是主石
        if (stoEntity.isChecked() && !stoEntity.getStroneName().equals(MainStone)) {
            for (int i = 0; i < 6; i++) {
                list.add("");
                if (i == 5) {
                    list.add(stoEntity.getIsSelfStone() + "");
                }
            }
        } else if (stoEntity.isChecked() && stoEntity.getStroneName().equals(MainStone)) {
            list.add(stoEntity.getTypeId());
            list.add(stoEntity.getSpecTitle());
            list.add(stoEntity.getShapeId());
            list.add(stoEntity.getColorId());
            list.add(stoEntity.getPurityId());
            list.add(stoEntity.getNumber());
            stoEntity.setIsSelfStone(0);//简版设为不是自带主石

        } else {
            if (stoEntity.getStroneName() != null) {
                list.add(!StringUtils.isEmpty(stoEntity.getTypeId()) ? stoEntity.getTypeId() : "");
                list.add(!StringUtils.isEmpty(stoEntity.getSpecTitle()) ? stoEntity.getSpecTitle() : "");
                list.add(!StringUtils.isEmpty(stoEntity.getShapeId()) ? stoEntity.getShapeId() : "");
                list.add(!StringUtils.isEmpty(stoEntity.getColorId()) ? stoEntity.getColorId() : "");
                list.add(!StringUtils.isEmpty(stoEntity.getPurityId()) ? stoEntity.getPurityId() : "");
                list.add(!StringUtils.isEmpty(stoEntity.getNumber()) ? stoEntity.getNumber() : "");
                if (!stoEntity.getStroneName().equals(MainStone)) {
                    list.add(0 + "");//是否自带主石
                }
            }


        }
        return StringUtils.purUrlCut(key, list).toString();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        L.e("requestCode" + requestCode);
        if (requestCode == 11) {
            if (data != null) {
                String tempid = data.getExtras().getString("itemId");
                type = 1;
                if (!StringUtils.isEmpty(tempid)) {
                    itemId = tempid;
                    loadNetData();
                }
            }
        }
        if (requestCode == 12) {
            if (data == null) {
                return;
            }
            waitOrderCount = data.getExtras().getInt("waitOrderCount");
            remind(waitOrderCount);
        }
    }

    public void setStorePrice(StoneEntity stoneEntity) {
        if (!(StringUtils.isEmpty(stoneEntity.getColorId()) && StringUtils.isEmpty(stoneEntity.getPurityId())
                && StringUtils.isEmpty(stoneEntity.getSpecTitle()) && StringUtils.isEmpty(stoneEntity.getTypeId()))) {
            loadStonePrice(stoneEntity);
        }
    }

    /***
     *
     * 获取价格
     *
     * */
    public void loadStonePrice(final StoneEntity stoneEntity) {
        String url = AppURL.URL_STONE_PRICE + "tokenKey=" + BaseApplication.getToken() + "&colorId=" + stoneEntity.getColorId() +
                "&categoryId=" + stoneEntity.getTypeId() + "&specValue=" + stoneEntity.getSpecTitle() + "&purityId=" + stoneEntity.getPurityId();
        L.e("查询价格:    " + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    String price = new Gson().fromJson(result, JsonObject.class).getAsJsonObject("data").get("price").getAsString();
                    stoneEntity.setPrice(price);
                } else if (error == 2) {
                    loginToServer(SimpleStyleInfromationActivity.class);
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
//                    ToastManager.showToastReal(message);
                }
            }

            @Override
            public void onFail(String fail) {
                showToastReal("数据获取失败");
            }
        });
    }


    public class ListAdapter extends BaseAdapter {
        List<StoneEntity> stoneEntities;

        public ListAdapter(List<StoneEntity> stoneEntities) {
            this.stoneEntities = stoneEntities;
        }


        @Override
        public int getCount() {
            return stoneEntities.size();
        }

        @Override
        public StoneEntity getItem(int i) {
            return stoneEntities.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(SimpleStyleInfromationActivity.this).inflate(R.layout.item_simple_styleinfromation, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final StoneEntity stoneEntity = getItem(i);

            switch (i) {
                case 0:
                    if (isEmptyStone(stoneEntity)) {
                        if (Global.isShowPopup == 0) {
                            viewHolder.tvMainStoneDate.setText("+ 添加主石");
                            viewHolder.tvMainStoneDate.setTextColor(getResources().getColor(R.color.theme_red));
                            viewHolder.tvMainStoneDate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    selectStone();
                                }
                            });
                        } else {
                            viewHolder.tvMainStoneDate.setText("+ 请在悬浮框中添加主石");
                            viewHolder.tvMainStoneDate.setTextColor(getResources().getColor(R.color.text_color));
                        }
                        if (openType.equals("1")) {
                            viewHolder.tvMainStoneDate.setTextColor(getResources().getColor(R.color.text_color));
                            viewHolder.tvMainStoneDate.setText(changeSelectedStoneToString());
                        }
                        viewHolder.ivSelectMainStone.setVisibility(View.GONE);
                    } else {
                        if (Global.isShowPopup == 0) {
                            //从石头搜索回来，不显示浮框
                            viewHolder.tvMainStoneDate.setTextColor(getResources().getColor(R.color.text_color));
                            if (openType.equals("1")) {
                                viewHolder.tvMainStoneDate.setText(changeSelectedStoneToString());
                            } else {
                                viewHolder.tvMainStoneDate.setText(changeStoneEntityToString(stoneEntity));
                            }

                            viewHolder.ivSelectMainStone.setVisibility(View.VISIBLE);
                            viewHolder.tvMainStoneDate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    selectStone();
                                }
                            });
                            viewHolder.ivSelectMainStone.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    selectStone();
                                }
                            });
                        } else {
                            viewHolder.ivSelectMainStone.setVisibility(View.VISIBLE);
                            viewHolder.tvMainStoneDate.setText(changeStoneEntityToString(stoneEntity));
                            viewHolder.tvMainStoneDate.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                            viewHolder.tvMainStoneDate.setTextColor(getResources().getColor(R.color.text_color));
                            if (Global.isShowPopup != 0) {
                                viewHolder.ivSelectMainStone.setVisibility(View.GONE);
                            }
                        }

                    }


                    stoneEntity.setStroneName(MainStone);
                    viewHolder.tvTitle.setText(MainStone);
                    viewHolder.idIsCheck.setVisibility(View.GONE);
                    break;
                case 1:
                    stoneEntity.setStroneName("副石A");
                    viewHolder.tvTitle.setText("副石A ");
                    if (isEmptyStone(stoneEntity)) {
                        viewHolder.tvMainStoneDate.setText("");
                        viewHolder.tvMainStoneDate.setTextColor(getResources().getColor(R.color.theme_red));
                        viewHolder.ivSelectMainStone.setVisibility(View.GONE);
                    } else {
                        viewHolder.tvMainStoneDate.setText(changeStoneEntityToString(stoneEntity));
                        viewHolder.tvMainStoneDate.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                        viewHolder.tvMainStoneDate.setTextColor(getResources().getColor(R.color.text_color));
                    }
                    viewHolder.idIsCheck.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    stoneEntity.setStroneName("副石B");
                    viewHolder.tvTitle.setText("副石B ");
                    if (isEmptyStone(stoneEntity)) {
                        viewHolder.tvMainStoneDate.setText("");
                        viewHolder.tvMainStoneDate.setTextColor(getResources().getColor(R.color.theme_red));
                        viewHolder.ivSelectMainStone.setVisibility(View.GONE);
                    } else {
                        viewHolder.tvMainStoneDate.setText(changeStoneEntityToString(stoneEntity));
                        viewHolder.tvMainStoneDate.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                        viewHolder.tvMainStoneDate.setTextColor(getResources().getColor(R.color.text_color));
                    }
                    viewHolder.idIsCheck.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    stoneEntity.setStroneName("副石C");
                    viewHolder.tvTitle.setText("副石C ");
                    if (isEmptyStone(stoneEntity)) {
                        viewHolder.tvMainStoneDate.setText("");
                        viewHolder.tvMainStoneDate.setTextColor(getResources().getColor(R.color.theme_red));
                        viewHolder.ivSelectMainStone.setVisibility(View.GONE);
                    } else {
                        viewHolder.tvMainStoneDate.setText(changeStoneEntityToString(stoneEntity));
                        viewHolder.tvMainStoneDate.setTextColor(getResources().getColor(R.color.text_color));
                    }
                    viewHolder.idIsCheck.setVisibility(View.VISIBLE);

                    break;
            }


            viewHolder.idIsCheck.setChecked(stoneEntity.isChecked());
            viewHolder.idIsCheck.setTag(i);
            viewHolder.idIsCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!openType.equals("1") || !stoneEntity.getStroneName().equals(MainStone)) {
                        stoneEntity.setChecked(!stoneEntity.isChecked());
                        if (stoneEntity.isChecked()) {
                            stoneEntity.setIsSelfStone(1);
                        } else {
                            stoneEntity.setIsSelfStone(0);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        showToastReal("裸石库的石头不能编辑！");
                        stoneEntity.setChecked(false);
                        viewHolder.idIsCheck.setChecked(false);
                    }

                }
            });


            return view;
        }


        class ViewHolder {
            @Bind(R.id.tv_title)
            TextView tvTitle;
            @Bind(R.id.tv_main_stone_date)
            TextView tvMainStoneDate;
            @Bind(R.id.iv_select_main_stone)
            ImageView ivSelectMainStone;
            @Bind(R.id.id_is_check)
            CheckBox idIsCheck;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    private boolean isAllEmptyStone(StoneEntity stoEntity) {
        boolean isEmpty = false;
        if (StringUtils.isEmpty(stoEntity.getNumber()) ||
                StringUtils.isEmpty(stoEntity.getShapeId()) || StringUtils.isEmpty(stoEntity.getPurityTitle())
                || StringUtils.isEmpty(stoEntity.getColorId()) || StringUtils.isEmpty(stoEntity.getTypeId())) {
            isEmpty = true;
        }
        return isEmpty;
    }
    public String toEmpty2(String st) {
        return StringUtils.isEmpty(st) ? "" : st ;
    }

    private String changeSelectedStoneToString() {

        return "规格：" + toEmpty2(selectedStone.getWeight()) + ";形状：" + toEmpty2(selectedStone.getShape()) + ";颜色："
                + toEmpty2(selectedStone.getColor()) + ";净度：" + toEmpty2(selectedStone.getPurity()) + ";证书号：" + toEmpty2(selectedStone.getCertCode());
    }

    private String changeStoneEntityToString(StoneEntity stoneEntity) {

        return "类别：" + toEmpty2(stoneEntity.getTypeTitle()) + "; 规格：" + toEmpty2(stoneEntity.getSpecTitle()) + ";形状：" + toEmpty2(stoneEntity.getShapeTitle()) +
                ";颜色：" + toEmpty2(stoneEntity.getColorTitle()) + ";净度：" + toEmpty2(stoneEntity.getPurityTitle()) + ";数量：" + toEmpty2(stoneEntity.getNumber()) + (stoneEntity.getStoneCode() != null && (!openType.equals("2")) ? ";证书号：" + stoneEntity.getStoneCode() : "");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            intent.putExtra("waitOrderCount", waitOrderCount);
            setResult(10, intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
