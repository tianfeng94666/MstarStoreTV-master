package com.qx.mstarstoretv.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.bean.Type;
import com.qx.mstarstoretv.inter.ConfirmOrderOnUpdate;
import com.qx.mstarstoretv.json.ModelDetailResult;
import com.qx.mstarstoretv.json.StoneEntity;
import com.qx.mstarstoretv.net.OKHttpRequestUtils;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.BadgeView;
import com.qx.mstarstoretv.viewutils.CustomLV;
import com.qx.mstarstoretv.viewutils.CustomSelectButton;
import com.qx.mstarstoretv.viewutils.CustomSelectInput;
import com.qx.mstarstoretv.viewutils.CustomselectStringButton;
import com.recker.flybanner.FlyBanner;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * 创建人：Yangshao
 * 创建时间：2016/10/19 9:37
 * @version  款式信息
 *
 */
public class StyleInfromationActivity extends BaseActivity implements View.OnClickListener {

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
    List<ModelDetailResult.DataEntity.ModelEntity.PicsEntity> pics;
    ModelDetailResult.DataEntity.ModelEntity modelEntity;
    List<ModelDetailResult.DataEntity.RemarksEntity> remarksEntity;//备注
    Type remar = new Type();  //提交的

    @Bind(R.id.id_list)
    CustomLV listView;
    ListAdapter adapter;
    LinearLayout lymenus;
    @Bind(R.id.tv_weight)
    TextView tvWeight;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.id_cus_store_type)
    CustomSelectButton idCusStoreType;
    @Bind(R.id.tv_reset)
    TextView tvReset;
    @Bind(R.id.tv_search)
    TextView idTvAddOrder;
    @Bind(R.id.et_spot)
    EditText idCusStoreNumber;
    String categoryTitle, storeNumber, storeSize, categoryId;
    @Bind(R.id.id_cus_store_remarkid)
    CustomSelectButton idCusStoreRemarkid;
    @Bind(R.id.id_tv_store_remarks)
    EditText idTvStoreRemarks;
    @Bind(R.id.lny_loading_layout)
    LinearLayout lnyLoadingLayout;
    @Bind(R.id.iv_reduce)
    ImageView ivReduce;
    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.id_cus_store_size)
    CustomselectStringButton idCusStoreSize;
    @Bind(R.id.tv_del)
    TextView tvDel;
    @Bind(R.id.tv_add)
    TextView tvAdd;
    @Bind(R.id.id_tv_curorder)
    TextView idTvCurorder;
    WheelView mainWheelView;
    int type = 0;
    String orderId;
    int waitOrderCount;
    String itemId;
    static ConfirmOrderOnUpdate confirmOrderOnUpdate;
    @Bind(R.id.ll_add)
    LinearLayout llAdd;
    @Bind(R.id.flybanner)
    FlyBanner flybanner;
    @Bind(R.id.ll_banner)
    LinearLayout llBanner;
    @Bind(R.id.space_view)
    View spaceView;
    @Bind(R.id.id_store_title)
    TextView idStoreTitle;

    private View rootView;
    private ModelDetailResult.DataEntity dataEntity;
    private ArrayList<String> getPics;

    public static void setConfirmOrderOnUpdate(ConfirmOrderOnUpdate confirmOrderOnUpdate) {
        StyleInfromationActivity.confirmOrderOnUpdate = confirmOrderOnUpdate;
    }

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initWindwoState();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_style_information);
        rootView = View.inflate(this, R.layout.activity_style_information, null);
        ButterKnife.bind(this);
        getIntentData();
        initView();
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

    private void getIntentData() {
        Bundle extras = getIntent().getExtras();
        itemId = extras.getString("itemId");
        type = extras.getInt("type");
        orderId = extras.getString("orderId");
        waitOrderCount = extras.getInt("waitOrderCount", 0);

    }


    private void initView() {
        // titleText.setText("款式信息");
        lnyLoadingLayout.setVisibility(View.VISIBLE);
        idTvAddOrder.setText(R.string.add_curent_order);
        idTvCurorder.setText(R.string.look_current_order);

        if (type == 1 || type == 2) {
            idTvAddOrder.setText(R.string.confirm_update);
            idTvCurorder.setText(R.string.cancle_update);
        }

        idTvAddOrder.setOnClickListener(this);
        adapter = new ListAdapter(stoneEntities);
        listView.setAdapter(adapter);
        badge = new BadgeView(StyleInfromationActivity.this, idTvCurorder);// 创建一个BadgeView对象，view为你需要显示提醒的控件
        remind(waitOrderCount);

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
        llAdd.setOnClickListener(this);
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
                    Intent intent = new Intent(StyleInfromationActivity.this, ConfirmOrderActivity.class);
                    intent.putExtra("waitOrderCount", waitOrderCount);
                    startActivityForResult(intent, 12);
                }
            }
        });
    }

    private void reduce() {
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
    }

    private void add() {
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
    }


    BadgeView badge;

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
        badge.setBadgeMargin(0, 15); // 水平和竖直方向的间距+-
        if (isVisible) {
            badge.show();// 只有显示
        } else {
            badge.hide();//影藏显示
        }
    }

    @Override
    public void loadNetData() {
        String url;
        if (type == 1) {
            url = AppURL.URL_MODEL_UPDATE + "tokenKey=" + BaseApplication.getToken() + "&itemId=" + itemId;
        } else if (type == 2) {
            url = AppURL.URL_ORDER_MODEL_UPDATE + "tokenKey=" + BaseApplication.getToken() + "&itemId=" + itemId;
        } else {
            url = AppURL.URL_MODEL_DETAIL + "tokenKey=" + BaseApplication.getToken() + "&id=" + itemId;
            //款号页面
        }
        L.e("获取款号" + url + "Type   " + type);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                stoneEntities.clear();
                lnyLoadingLayout.setVisibility(View.GONE);
                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    ModelDetailResult modelDetail = new Gson().fromJson(result, ModelDetailResult.class);
                    dataEntity = modelDetail.getData();
                    if (dataEntity == null) {
                        return;
                    }
                    initCustomselectString();
                    List<ModelDetailResult.DataEntity.GoldenPriceEntity> goldenPrice = dataEntity.getGoldenPrice();
                    remarksEntity = dataEntity.getRemarks();
                    modelEntity = dataEntity.getModel();
                    categoryTitle = modelEntity.getCategoryTitle();
                    categoryId = modelEntity.getCategoryId();
                    /*是否自带主石*/

                    //L.e("服务器返回是否自带主石头"+isSelfStone);
                    /*得到数量*/
                    String number = modelEntity.getNumber();
                    String remark = modelEntity.getRemark();
                    String handSize = modelEntity.getHandSize();

                    pics = modelEntity.getPics();
                    stone = modelEntity.getStone();
                    stoneA = modelEntity.getStoneA();
                    stoneB = modelEntity.getStoneB();
                    stoneC = modelEntity.getStoneC();
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
                    addEntities(stone, stoneA, stoneB, stoneC);

                    stoneTypeItme = dataEntity.getStoneType();
                    stoneColorItme = dataEntity.getStoneColor();
                    stonePurityItme = dataEntity.getStonePurity();
                    stoneSpecItme = dataEntity.getStoneSpec();
                    stoneShapeItem = dataEntity.getStoneShape();

                    setStorePrice(stone);
                    setStorePrice(stoneA);
                    setStorePrice(stoneB);
                    setStorePrice(stoneC);
                    L.e("categoryTitle" + categoryTitle);
                    idStoreTitle.setText(modelEntity.getTitle());
                    tvWeight.setText(modelEntity.getWeight());
                    idCusStoreType.setTextName(categoryTitle);
                    if (!StringUtils.isEmpty(number)) {
                        idCusStoreNumber.setText("" + number);
                    }
                    if (!StringUtils.isEmpty(remark)) {
                        idTvStoreRemarks.setText("" + remark);
                    }
                    idCusStoreSize.setTextName(handSize);
                    // initView();
                    //layoutBtns();
                    //initGvImage();
                    initViewPager();
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < goldenPrice.size(); i++) {
                        sb.append(goldenPrice.get(i).getTitle() + " " + goldenPrice.get(i).getPrice() + "      ");
                    }
//                    idStoreInformation.setText(sb.toString());
                    idCusStoreRemarkid.setOnSelectData(new CustomSelectButton.OnselectData() {
                        @Override
                        public List<Type> getData() {
                            List<Type> list = new ArrayList<>();
                            for (int i = 0; i < remarksEntity.size(); i++) {
                                Type type = new Type();
                                type.setId(remarksEntity.get(i).getId());
                                type.setTypeName(remarksEntity.get(i).getTitle());
                                type.setContent(remarksEntity.get(i).getContent());
                                list.add(type);
                            }
                            return list;
                        }

                        @Override
                        public View getRootView() {
                            return   idCusStoreRemarkid;
                        }


                        @Override
                        public void getSelectId(Type type) {
                            remar = type;
                            String curentRemar = idTvStoreRemarks.getText().toString();
                            idTvStoreRemarks.setText(curentRemar + type.getContent());
                        }

                        @Override
                        public String getTitle() {
                            return "选择备注";
                        }
                    });
                    adapter.notifyDataSetChanged();
                } else if (error == 2) {
                    ToastManager.showToastReal(getString(R.string.data_error));
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

        if (stone.getIsNotEmpty() == 1) {
            stoneEntities.add(stone);
        }
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

    private void initCustomselectString() {
        idCusStoreSize.setDefaultText("手寸");
        idCusStoreSize.setOnSelectData(new CustomselectStringButton.OnselectData() {
            @Override
            public List<String> getData() {
                return Arrays.asList(dataEntity.getHandSizeData());
            }

            @Override
            public void getSelectId(String type) {
                idCusStoreSize.setTextName(type);
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
                Intent intent = new Intent(StyleInfromationActivity.this,
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
                if (!UIUtils.isFastDoubleClick()) {
                    chkeckConfirmOrder();
                }
                break;
            case R.id.tv_reset:
                reset();
                break;
            case R.id.ll_add:
                addStone();
                break;
            case R.id.tv_del:
                delStone();
                break;
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
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
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
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    /**
     * 判断重置对应的stone
     */
    private void reset() {
        switch (stoneEntities.size()) {
            case 1:
                stoneEntities.remove(0);
                stoneEntities.add(copyStone(stoneTemp));
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
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    /*提交订单之前检查是否允许提交*/
    private void chkeckConfirmOrder() {
        storeNumber = idCusStoreNumber.getText().toString();
        storeSize = idCusStoreSize.getTextName().toString();
        L.e("storeNumber" + storeNumber + "storeSize" + storeSize + "categoryTitle" + categoryTitle);
        boolean isConfir = false;
        int j = 0;
        if (isNumber(storeNumber)) {
            for (int i = 0; i < stoneEntities.size(); i++) {
                StoneEntity stone = stoneEntities.get(i);
                  /*自带主石头*/
                if (!stone.isChecked() && !checkUpPass(stone) || stone.isChecked()) {
                    j++;
                }
            }
            if (j == stoneEntities.size()) {
                isConfir = true;
            }

            /*是否可以提交*/
            if (isConfir) {
                addCurentOrder();
                idTvAddOrder.setEnabled(false);
            } else {
                ToastManager.showToastReal(getString(R.string.please_fill_data));
            }

        } else {
            ToastManager.showToastReal("件数只许整形或者0.5");
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
        String urlStroe = objectisEmptyAndtoJson("stone", stone);
        String urlStroeA = objectisEmptyAndtoJson("stoneA", stoneA);
        String urlStroeB = objectisEmptyAndtoJson("stoneB", stoneB);
        String urlStroeC = objectisEmptyAndtoJson("stoneC", stoneC);
        if (type == 1) {
            url = AppURL.URL_CURRENT_EDIT_ORDER + "tokenKey=" + BaseApplication.getToken() + "&itemId=" + itemId + "&number=" + storeNumber
                    + "&handSize=" + storeSize + urlStroe + urlStroeA + urlStroeB + urlStroeC + "&isSelfStone=" + stone.getIsSelfStone() + "" + "&remarks=" + remark;
        } else if (type == 2) {
            url = AppURL.URL_UPDATE_ORDER_WATET + "tokenKey=" + BaseApplication.getToken() + "&itemId=" + itemId + "&number=" + storeNumber
                    + "&handSize=" + storeSize + urlStroe + urlStroeA + urlStroeB + urlStroeC + "&isSelfStone=" + stone.getIsSelfStone() + "" + "&remarks=" + remark;
        } else {
            url = AppURL.URL_CURRENT_ORDER + "tokenKey=" + BaseApplication.getToken() + "&productId=" + itemId + "&categoryId=" + categoryId +
                    "&number=" + storeNumber + "&handSize=" + storeSize + urlStroe + urlStroeA + urlStroeB + urlStroeC + "&isSelfStone=" + stone.getIsSelfStone() + "" + "&remarks=" + remark;
        }
        L.e("提交订单" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("提交订单 result" + result);
                if (new Gson().fromJson(result, JsonObject.class).get("error").getAsString().equals("0")) {
                    idTvAddOrder.setEnabled(true);
                    if (type == 1 || type == 2) {
                        ToastManager.showToastReal("修改成功");
                        if (confirmOrderOnUpdate != null) {
                            confirmOrderOnUpdate.onUpdate();
                            finish();
                        }
                    } else {
                        String strwaitOrderCount = new Gson().fromJson(result, JsonObject.class).get("data").getAsJsonObject().get("waitOrderCount").getAsString();
                        waitOrderCount = Integer.valueOf(strwaitOrderCount);
                        L.e("waitOrderCount" + waitOrderCount);
                        ToastManager.showToastReal("添加成功");
                        remind(waitOrderCount);
                        idTvAddOrder.setEnabled(true);
                        return;
                    }
                    //finish();
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);
                    idTvAddOrder.setEnabled(true);
                }
            }

            @Override
            public void onFail(String fail) {

            }

        });
    }


    /**
     * 验证输入 不通过 返回true 要么全空要么全不为空
     */
    public boolean checkUpPass(StoneEntity stoEntity) {
        boolean isUpPass = true;
        //全部为空
        if (StringUtils.isEmpty(stoEntity.getNumber()) &&
                StringUtils.isEmpty(stoEntity.getShapeId()) && StringUtils.isEmpty(stoEntity.getPurityTitle())
                && StringUtils.isEmpty(stoEntity.getColorId()) && StringUtils.isEmpty(stoEntity.getTypeId())
                && StringUtils.isEmpty(stoEntity.getSpecTitle())) {
            isUpPass = false;
        }
        if (!StringUtils.isEmpty(stoEntity.getNumber()) &&
                !StringUtils.isEmpty(stoEntity.getShapeId()) && !StringUtils.isEmpty(stoEntity.getPurityTitle())
                && !StringUtils.isEmpty(stoEntity.getColorId()) && !StringUtils.isEmpty(stoEntity.getTypeId())
                && !StringUtils.isEmpty(stoEntity.getSpecTitle())) {
            isUpPass = false;
        }

        if (!isUpPass) {
            L.e(stoEntity.toString() + "通过验证");
        } else {
            L.e(stoEntity.toString() + "不通过验证");
        }

        return isUpPass;
    }


    /*验证数字是否是整形和0.5*/
    public boolean isNumber(String storeNumber) {
        if (!StringUtils.isEmpty(storeNumber)) {
            double d = Double.parseDouble(storeNumber);
            if (!storeNumber.equals("0.5") && !(d % 1 != 0.5)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将选择的数据转化为JSON字符串
     * *
     */
    public String objectisEmptyAndtoJson(String key, StoneEntity stoEntity) {
        List list = new ArrayList();
        //如果是主石
        if (stoEntity.isChecked() && !stoEntity.getStroneName().equals("主 石")) {
            for (int i = 0; i < 6; i++) {
                list.add("");
                if (i == 5) {
                    list.add(stoEntity.getIsSelfStone() + "");
                }
            }
        } else if (stoEntity.isChecked() && stoEntity.getStroneName().equals("主 石")) {
            list.add(stoEntity.getTypeId());
            list.add(stoEntity.getSpecTitle());
            list.add(stoEntity.getShapeId());
            list.add(stoEntity.getColorId());
            list.add(stoEntity.getPurityId());
            list.add(stoEntity.getNumber());
        } else {
            if (!StringUtils.isEmpty(stoEntity.getNumber()) &&
                    !StringUtils.isEmpty(stoEntity.getPurityId()) && !StringUtils.isEmpty(stoEntity.getColorId())
                    && !StringUtils.isEmpty(stoEntity.getTypeId()) && !StringUtils.isEmpty(stoEntity.getSpecTitle())
                    && !StringUtils.isEmpty(stoEntity.getShapeId())) {
                list.add(stoEntity.getTypeId());
                list.add(stoEntity.getSpecTitle());
                list.add(stoEntity.getShapeId());
                list.add(stoEntity.getColorId());
                list.add(stoEntity.getPurityId());
                list.add(stoEntity.getNumber());
                if (!stoEntity.getStroneName().equals("主 石")) {
                    list.add(stoEntity.getIsSelfStone() + "");
                }

            }
        }
        return StringUtils.purUrlCut(key, list).toString();
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
                view = LayoutInflater.from(StyleInfromationActivity.this).inflate(R.layout.item_styleinfromation, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.checkLayoutVisible.setVisibility(View.VISIBLE);
            final StoneEntity stoneEntity = getItem(i);
            switch (i) {
                case 0:
                    stoneEntity.setStroneName("主 石");
                    viewHolder.idTvTitle.setText("主 石");
                    viewHolder.tvHint.setText("自带");
                    break;
                case 1:
                    stoneEntity.setStroneName("副石A");
                    viewHolder.idTvTitle.setText("副石A");
                    viewHolder.tvHint.setText("封石");
                    break;
                case 2:
                    stoneEntity.setStroneName("副石B");
                    viewHolder.idTvTitle.setText("副石B");
                    viewHolder.tvHint.setText("封石");
                    break;
                case 3:
                    stoneEntity.setStroneName("副石C");
                    viewHolder.idTvTitle.setText("副石C");
                    viewHolder.tvHint.setText("封石");

                    break;
            }

            // viewHolder.idStoreNumber.addTextChangedListener(new TextChange(viewHolder,stoneEntity));


            if (stoneEntity.isChecked()) {
                redHint(viewHolder, false);
//                checkedState.put(stoneEntity.getStroneName(), stoneEntity);
            } else {
                redHint(viewHolder, checkUpPass(stoneEntity));
            }
            viewHolder.idIsCheck.setChecked(stoneEntity.isChecked());

            viewHolder.idIsCheck.setTag(i);
            viewHolder.idIsCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stoneEntity.setChecked(!stoneEntity.isChecked());
                    if (stoneEntity.isChecked()) {
                        stoneEntity.setIsSelfStone(1);
                    } else {
                        stoneEntity.setIsSelfStone(0);
                    }

//                    if (checkedState.get(stoneEntity.getStroneName()) != null) {
////                        checkedState.remove(stoneEntity.getStroneName());
//                        stoneEntity.setChecked(false);
//                        stoneEntity.setIsSelfStone(0);
//                    }
////                    else {
////                        checkedState.put(stoneEntity.getStroneName(), stoneEntity);
////                    }
//                    if (checkedState.size() > 0) {
//                        for (String key : checkedState.keySet()) {
//                            StoneEntity stone = checkedState.get(key);//得到每个key多对用value的值
//                            stone.setChecked(true);
//                            stoneEntity.setIsSelfStone(1);
//                        }
//                    }
                    adapter.notifyDataSetChanged();
                }
            });
            initDataAndListener(viewHolder, stoneEntity);
            return view;
        }


        /**
         * true红色提示  表示不通过验证
         **/
        public void redHint(ViewHolder viewHolder, boolean isRed) {
            if (isRed) {
                viewHolder.idStoreCut.getTv().setBackgroundResource(R.drawable.check_red_border);
                viewHolder.idStoreType.getTv().setBackgroundResource(R.drawable.check_red_border);
                viewHolder.idStoreColor.getTv().setBackgroundResource(R.drawable.check_red_border);
                viewHolder.idStoreNorm.getTv().setBackgroundResource(R.drawable.check_red_border);
                viewHolder.idStoreShape.getTv().setBackgroundResource(R.drawable.check_red_border);
                viewHolder.idStoreNumber.getTv().setBackgroundResource(R.drawable.check_red_border);
            } else {
                viewHolder.idStoreCut.getTv().setBackgroundResource(R.drawable.btn_bg_while);
                viewHolder.idStoreType.getTv().setBackgroundResource(R.drawable.btn_bg_while);
                viewHolder.idStoreColor.getTv().setBackgroundResource(R.drawable.btn_bg_while);
                viewHolder.idStoreNorm.getTv().setBackgroundResource(R.drawable.btn_bg_while);
                viewHolder.idStoreShape.getTv().setBackgroundResource(R.drawable.btn_bg_while);
                viewHolder.idStoreNumber.setBackgroundResource(R.drawable.btn_bg_while);
                viewHolder.idStoreNumber.getTv().setBackgroundResource(R.drawable.btn_bg_while);
            }
        }


    }

    /*选中显示*/
    public void isCheckVisable(final ViewHolder viewHolder, final StoneEntity stoneEntity) {
        if (stoneEntity.isChecked() && !stoneEntity.getStroneName().equals("主 石")) {
            viewHolder.idStoreType.setDefaultText("类型");
            viewHolder.idStoreNorm.setDefaultText("规格");
            viewHolder.idStoreShape.setDefaultText("形状");
            viewHolder.idStoreCut.setDefaultText("净度");
            viewHolder.idStoreColor.setDefaultText("颜色");
            viewHolder.idStoreNumber.setDefaultText("数量");
        } else {
            if (!StringUtils.isEmpty(stoneEntity.getTypeTitle())) {
                viewHolder.idStoreType.setTextName(stoneEntity.getTypeTitle());
            } else {
                viewHolder.idStoreType.setDefaultText("类型");
            }
            if (!StringUtils.isEmpty(stoneEntity.getSpecTitle())) {
                viewHolder.idStoreNorm.setTextName(stoneEntity.getSpecTitle());
            } else {
                viewHolder.idStoreNorm.setDefaultText("规格");
            }
            if (!StringUtils.isEmpty(stoneEntity.getShapeTitle())) {
                viewHolder.idStoreShape.setTextName(stoneEntity.getShapeTitle());
            } else {
                viewHolder.idStoreShape.setDefaultText("形状");
            }
            if (!StringUtils.isEmpty(stoneEntity.getPurityTitle())) {
                viewHolder.idStoreCut.setTextName(stoneEntity.getPurityTitle());
            } else {
                viewHolder.idStoreCut.setDefaultText("净度");
            }
            if (!StringUtils.isEmpty(stoneEntity.getColorTitle())) {
                viewHolder.idStoreColor.setTextName(stoneEntity.getColorTitle());
            } else {
                viewHolder.idStoreColor.setDefaultText("颜色");
            }
            if (!StringUtils.isEmpty(stoneEntity.getNumber())) {
                viewHolder.idStoreNumber.setTextName(stoneEntity.getNumber());
            } else {
                viewHolder.idStoreNumber.setDefaultText("数量");
            }


        }

    }


    private void initDataAndListener(final ViewHolder viewHolder, final StoneEntity stoneEntity) {

        isCheckVisable(viewHolder, stoneEntity);

        viewHolder.idStoreNumber.setOnSelectData(new CustomselectStringButton.OnselectData() {
            @Override
            public List<String> getData() {
                List<String> list = new ArrayList<String>();
                for (int i = 1; i < 100; i++) {
                    list.add(i + "");
                }
                return list;
            }

            @Override
            public void getSelectId(String type) {
                stoneEntity.setNumber(type);
                viewHolder.idStoreNumber.setText(stoneEntity.getNumber());
                if (!StringUtils.isEmpty(stoneEntity.getNumber())) {
                    viewHolder.idStoreNumber.setBackgroundResource(R.drawable.btn_bg_while);
                } else {
                    viewHolder.idStoreNumber.setBackgroundResource(R.drawable.check_red_border);
                }
                setStorePrice(stoneEntity);
                // adapter.setTag(viewHolder,position);
                isSetNOcheck(stoneEntity);
            }

            @Override
            public int defaultPosition() {
                return 0;
            }

            @Override
            public String getTitle() {
                return "数量";
            }

            @Override
            public View getRootView() {
                return viewHolder.idStoreNumber;
            }
        });


        viewHolder.idStoreType.setOnSelectData(new CustomSelectButton.OnselectData() {
            @Override
            public List<Type> getData() {
                List<Type> list = new ArrayList<Type>();
                for (int i = 0; i < stoneTypeItme.size(); i++) {
                    Type type = new Type();
                    type.setId(stoneTypeItme.get(i).getId());
                    type.setTypeName(stoneTypeItme.get(i).getTitle());
                    list.add(type);
                }
                return list;
            }

            @Override
            public View getRootView() {
                return  viewHolder.idStoreType;
            }

            @Override
            public void getSelectId(Type type) {
                stoneEntity.setTypeId(type.getId());
                stoneEntity.setTypeTitle(type.getTypeName());
                setStorePrice(stoneEntity);
                // adapter.setTag(viewHolder,position);
                isSetNOcheck(stoneEntity);
            }

            @Override
            public String getTitle() {
                return "类型";
            }
        });
        viewHolder.idStoreColor.setOnSelectData(new CustomSelectButton.OnselectData() {
            @Override
            public List<Type> getData() {
                List<Type> list = new ArrayList<Type>();
                for (int i = 0; i < stoneColorItme.size(); i++) {
                    Type type = new Type();
                    type.setId(stoneColorItme.get(i).getId());
                    type.setTypeName(stoneColorItme.get(i).getTitle());
                    list.add(type);
                }
                return list;
            }

            @Override
            public View getRootView() {
                return  viewHolder.idStoreColor;
            }

            @Override
            public void getSelectId(Type type) {
                stoneEntity.setColorId(type.getId());
                stoneEntity.setColorTitle(type.getTypeName());
                setStorePrice(stoneEntity);
                // adapter.setTag(viewHolder,position);
                isSetNOcheck(stoneEntity);
            }

            @Override
            public String getTitle() {
                return "颜色";
            }
        });
        viewHolder.idStoreCut.setOnSelectData(new CustomSelectButton.OnselectData() {
            @Override
            public List<Type> getData() {
                List<Type> list = new ArrayList<Type>();
                for (int i = 0; i < stonePurityItme.size(); i++) {
                    Type type = new Type();
                    type.setId(stonePurityItme.get(i).getId());
                    type.setTypeName(stonePurityItme.get(i).getTitle());
                    list.add(type);
                }
                return list;
            }

            @Override
            public View getRootView() {
                return    viewHolder.idStoreCut;
            }

            @Override
            public void getSelectId(Type type) {
                stoneEntity.setPurityId(type.getId());
                stoneEntity.setPurityTitle(type.getTypeName());
                setStorePrice(stoneEntity);
                // adapter.setTag(viewHolder,position);
                isSetNOcheck(stoneEntity);
            }

            @Override
            public String getTitle() {
                return "净度";
            }
        });


        viewHolder.idStoreNorm.setOnSelectData(new CustomSelectInput.OnselectData() {
            @Override
            public void getSelectId(String key) {
                if (!key.equals("规格")) {
                    stoneEntity.setSpecTitle(key);
                }
                setStorePrice(stoneEntity);

                //  adapter.setTag(viewHolder,position);
                isSetNOcheck(stoneEntity);
            }

            @Override
            public String getTitle() {
                if (!StringUtils.isEmpty(stoneEntity.getSpecSelectTitle())) {
                    return stoneEntity.getSpecSelectTitle();
                } else {
                    return "请输入合法规格";
                }

            }
        });

        viewHolder.idStoreShape.setOnSelectData(new CustomSelectButton.OnselectData() {
            @Override
            public List<Type> getData() {
                List<Type> list = new ArrayList<Type>();
                for (int i = 0; i < stoneShapeItem.size(); i++) {
                    Type type = new Type();
                    type.setId(stoneShapeItem.get(i).getId());
                    type.setTypeName(stoneShapeItem.get(i).getTitle());
                    list.add(type);
                }
                return list;
            }

            @Override
            public View getRootView() {
                return  viewHolder.idStoreShape;
            }

            @Override
            public void getSelectId(Type type) {
                stoneEntity.setShapeId(type.getId());
                stoneEntity.setShapeTitle(type.getTypeName());
                isSetNOcheck(stoneEntity);
                //adapter.setTag(viewHolder,position);
            }

            @Override
            public String getTitle() {
                return "形状";
            }
        });


    }


    public void isSetNOcheck(StoneEntity stoneEntity) {
        if (stoneEntity.isChecked() && !stoneEntity.getStroneName().equals("主 石")) {
            stoneEntity.setChecked(false);
        }
        adapter.notifyDataSetChanged();
    }

    public void firstClick(StoneEntity stoneEntity) {
        //全部为空
        boolean isUpPass = false;
        if (StringUtils.isEmpty(stoneEntity.getNumber()) &&
                StringUtils.isEmpty(stoneEntity.getShapeId()) && StringUtils.isEmpty(stoneEntity.getPurityTitle())
                && StringUtils.isEmpty(stoneEntity.getColorId()) && StringUtils.isEmpty(stoneEntity.getTypeId())
                && StringUtils.isEmpty(stoneEntity.getSpecTitle())) {
            isUpPass = true;
        }
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
                    adapter.notifyDataSetChanged();
                } else if (error == 2) {
                    loginToServer(StyleInfromationActivity.class);
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
//                    ToastManager.showToastReal(message);
                }
            }

            @Override
            public void onFail(String fail) {

            }
        });
    }

    public class ViewHolder {
        @Bind(R.id.id_store_type)
        CustomSelectButton idStoreType;
        @Bind(R.id.id_store_norm)
        CustomSelectInput idStoreNorm;
        @Bind(R.id.id_store_shape)
        CustomSelectButton idStoreShape;
        @Bind(R.id.id_store_color)
        CustomSelectButton idStoreColor;
        @Bind(R.id.id_store_cut)
        CustomSelectButton idStoreCut;
        @Bind(R.id.id_store_price)
        CustomSelectButton idStorePrice;
        @Bind(R.id.id_tv_title)
        TextView idTvTitle;
        @Bind(R.id.id_ed_number)
        CustomselectStringButton idStoreNumber;
        @Bind(R.id.id_check_Layout_visiable)
        LinearLayout checkLayoutVisible;

        @Bind(R.id.id_is_check)
        CheckBox idIsCheck;

        @Bind(R.id.tv_hint)
        TextView tvHint;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
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
