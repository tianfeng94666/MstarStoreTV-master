package com.qx.mstarstoretv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.adapter.RecycleViewPartAdapter;
import com.qx.mstarstoretv.adapter.RecycleViewPartListAdapter;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.json.GetPartListResult;
import com.qx.mstarstoretv.json.GetRingPartResult;
import com.qx.mstarstoretv.json.JewelStone;
import com.qx.mstarstoretv.json.ModelPartsBean;
import com.qx.mstarstoretv.json.ModelPicBean;
import com.qx.mstarstoretv.json.StoneSearchInfoResult;
import com.qx.mstarstoretv.manager.FitGridLayoutManager;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.BottmPartPopupWindow;
import com.qx.mstarstoretv.viewutils.CustomselectStringButton;
import com.qx.mstarstoretv.viewutils.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/7 0007.
 */

public class MakingActivity extends BaseActivity {


    @Bind(R.id.flybanner)
    FlyBanner flybanner;
    @Bind(R.id.rv_part)
    RecyclerView rvPart;
    @Bind(R.id.tv_finish)
    TextView tvFinish;
    @Bind(R.id.tv_choose_handsize)
    CustomselectStringButton tvChooseHandsize;
    @Bind(R.id.tv_buy)
    TextView tvBuy;
    @Bind(R.id.ll_after_finish)
    LinearLayout llAfterFinish;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.root_view)
    RelativeLayout rootView;
    @Bind(R.id.ll_headview)
    LinearLayout llHeadview;
    @Bind(R.id.ll_recyle)
    LinearLayout llRecyle;

    private FitGridLayoutManager mLayoutManager;
    private BottmPartPopupWindow bottmPartPopupWindow;
    private GetRingPartResult getRingPartResult;
    private List<ModelPicBean> modelPicBeans;
    private List<ModelPartsBean> partsList;
    private List<String> countList;
    private GetPartListResult getPartListResult;
    private List<ModelPartsBean> partList;//弹窗中的组件list
    private String[] selectPids;
    private RecycleViewPartAdapter recycleViewPartAdapter;
    private boolean isScreenLand;
    private String makeProductId;
    private TranslateAnimation mHiddenAction;
    private String handsize;
    private String itemId;//编辑的订单Id
    private int type = 3;//打开类型 3直接打开 4编辑过来的 5待审核过来编辑
    private Bundle extras;//记录打开类型
    private ModelPartsBean.SelectProItemBean selectProItem; //选中后的产品
    private StoneSearchInfoResult.DataBean.StoneBean.ListBean selectedStoneEnity;
    private int partsAount;//记录parts个数 方便添加stone选择
    private JewelStone jewelStone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_making);
        ButterKnife.bind(this);
        bottmPartPopupWindow = new BottmPartPopupWindow(this);
        getData(getIntent());

    }

    private void getData(Intent intent) {

        extras = intent.getExtras();
        if (extras != null) {
            selectedStoneEnity = (StoneSearchInfoResult.DataBean.StoneBean.ListBean) extras.getSerializable("stone");
            itemId = extras.getString("itemId");
            type = extras.getInt("type");
        }
        init();
        if (selectedStoneEnity != null) {//选完石头回来
            initView();
        } else {
            loadNetData();
        }

    }

    private void init() {
        if (type == 4 || type == 5) {
            tvBuy.setText("确定");
        } else if (type == 3) {
            tvBuy.setText("购买");
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getData(intent);
    }

    private void initView() {


        isScreenLand = UIUtils.isScreenChange(this);
        if (isScreenLand) {
            mLayoutManager = new FitGridLayoutManager(this, rvPart, 2, GridLayoutManager.VERTICAL, false);
            mLayoutManager.setAutoMeasureEnabled(true);
        } else {
            ViewGroup.LayoutParams layoutParams = llRecyle.getLayoutParams();
            layoutParams.height = (int) (UIUtils.getWindowWidth() * 0.95);
            llRecyle.setLayoutParams(layoutParams);
            ViewGroup.LayoutParams layoutParams1 = llHeadview.getLayoutParams();
            layoutParams1.height = (int) (UIUtils.getWindowHight() - UIUtils.getWindowWidth() * 0.95);
            llHeadview.setLayoutParams(layoutParams1);
            mLayoutManager = new FitGridLayoutManager(this, rvPart, 2, GridLayoutManager.HORIZONTAL, false);
            mLayoutManager.setAutoMeasureEnabled(true);
        }
        //头部大图
        setFlaybanner(modelPicBeans);
        //其他part
        rvPart.setLayoutManager(mLayoutManager);
        if(getRingPartResult!=null){
            partsList = getRingPartResult.getData().getModelParts();
            countList = getRingPartResult.getData().getModelpartCount();
        }

        selectPids = new String[countList.size()];
        /**
         * 判断是否添加一个裸石选择
         */
        jewelStone = getRingPartResult.getData().getJewelStone();
        ModelPartsBean stone = new ModelPartsBean();
        if (partsAount == partsList.size()) {
            if (jewelStone != null) {
                stone.setTitle(jewelStone.getTotalString());
            } else {
                stone.setTitle("选择裸石");
            }

            partsList.add(stone);
            String stoneAmount = "";
            countList.add(stoneAmount);
        } else {
            if (selectedStoneEnity != null) {
                if (jewelStone == null) {
                    jewelStone = new JewelStone();
                }
                jewelStone.setJewelStoneCode(selectedStoneEnity.getCertCode());
                jewelStone.setJewelStoneId(selectedStoneEnity.getId());
                jewelStone.setJewelStonePrice(selectedStoneEnity.getPrice());
                stone.setTitle(selectedStoneEnity.changeStoneEntityToString());
                partsList.set(partsAount, stone);
                recycleViewPartAdapter.notifyDataSetChanged();
            }
        }

//        if (recycleViewPartAdapter == null) {
//            //设置item间距，30dp
////            rvPart.addItemDecoration(new SpaceItemDecoration(4));
//        }
        if (partsList != null) {
            recycleViewPartAdapter = new RecycleViewPartAdapter(this, partsList, countList, new RecycleViewPartAdapter.MyItemClickListener() {
                @Override
                public void onItemClick(View view, int postion) {
//                    llAfterFinish.startAnimation(mHiddenAction);
                    if (postion == (partsList.size() - 1)) {
                        if (StringUtils.isEmpty(makeProductId)) {
                            getLostMessage();
                        } else {
                            Intent intent = new Intent(MakingActivity.this, StoneSearchInfoActivity.class);
                            intent.putExtra("type", type);
                            if (selectProItem != null) {
                                intent.putExtra("stoneValueRange", selectProItem.getStoneWeightRange());
                            }
                            startActivity(intent);
                        }

                    } else {
                        llAfterFinish.setVisibility(View.GONE);
                        tvFinish.setVisibility(View.VISIBLE);
                        getItemSource(postion);
                    }

                }
            });

            rvPart.setAdapter(recycleViewPartAdapter);
            //选择手寸
            if (type != 3) {
                tvChooseHandsize.setDefaultText(getRingPartResult.getData().getModelItem().getHandSize());
                handsize = getRingPartResult.getData().getModelItem().getHandSize();
            } else {
                tvChooseHandsize.setDefaultText("选择手寸");
            }
            tvChooseHandsize.setOnSelectData(new CustomselectStringButton.OnselectData() {
                @Override
                public List<String> getData() {
                    return getRingPartResult.getData().getHandSizeData();
                }

                @Override
                public void getSelectId(String type) {
                    handsize = type;
                }

                @Override
                public int defaultPosition() {
                    return 22;
                }

                @Override
                public String getTitle() {
                    return "选择手寸";
                }

                @Override
                public View getRootView() {
                    return tvChooseHandsize;
                }
            });
        }


    }

    private void getLostMessage() {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < partsList.size() - 1; i++) {
            if (StringUtils.isEmpty(partsList.get(i).getPid())) {
                arrayList.add(partsList.get(i).getTitle());
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (i != arrayList.size() - 1) {
                sb.append(arrayList.get(i) + ",");
            } else {
                sb.append(arrayList.get(i));
            }
        }
        showToastReal("请选择" + sb.toString());
    }

    private void setFlaybanner(List<ModelPicBean> modelPicBeans) {
        if (modelPicBeans != null) {
            List list = new ArrayList();
            for (int i = 0; i < modelPicBeans.size(); i++) {
                list.add(modelPicBeans.get(i).getPicb());
            }
            flybanner.setImagesUrl(list);
        }
    }

    private void getItemSource(final int postion) {
        baseShowWatLoading();
        String lgUrl = AppURL.URL_GET_PART_SOURCE + "tokenKey=" + BaseApplication.getToken() + "&partSort=" + partsList.get(postion).getPartSort() + "&selectPids=" + getSelectPids();
        L.e("netLogin" + lgUrl);
        VolleyRequestUtils.getInstance().getCookieRequest(this, lgUrl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                baseHideWatLoading();
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    getPartListResult = new Gson().fromJson(result, GetPartListResult.class);
                    if (getPartListResult.getData() == null) {
                        showToastReal("后台数据为空");
                        return;
                    }
                    partList = getPartListResult.getData().getList();
                    bottmPartPopupWindow.setData(partList, new RecycleViewPartListAdapter.PartListItemClickListener() {
                                @Override
                                public void onItemClick(View view, int postion) {
                                    bottmPartPopupWindow.setChoosePosition(postion);

                                }
                            },
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //判断是否选中
                                    int selectPosition = bottmPartPopupWindow.getChoosePosition();
                                    if (selectPosition == -1) {
                                        showToastReal("请选择后在确定");
                                        return;
                                    }
                                    selectPids[postion] = partList.get(selectPosition).getPid();
                                    partsList.set(postion, partList.get(selectPosition));
                                    selectProItem = partList.get(selectPosition).getSelectProItem();
                                    makeProductId = "";
                                    if (selectProItem != null) {
                                        modelPicBeans = selectProItem.getModelPic();
                                        setFlaybanner(selectProItem.getModelPic());
                                        makeProductId = selectProItem.getId();
                                    }

                                    bottmPartPopupWindow.closePopupWindow();
                                    countList.clear();
                                    countList.addAll(partList.get(selectPosition).getModelPartCount());
                                    recycleViewPartAdapter.notifyDataSetChanged();

                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    partsList.get(postion).setPid("");
                                    deleteSingle(postion);
                                }
                            },
                            isScreenLand ? rvPart : rootView);

                } else if (error.equals("2")) {
                    baseHideWatLoading();
                    loginToServer(MakingActivity.class);
                } else {
                    baseHideWatLoading();
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    ToastManager.showToastWhendebug(message);
                    L.e(message);
                }
            }

            @Override
            public void onFail(String fail) {
                baseHideWatLoading();
            }


        });
    }

    private String getSelectPids() {
        //减去添加的裸石数量
        for (int i = 0; i < countList.size() - 1; i++) {
            selectPids[i] = partsList.get(i).getPid();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < selectPids.length; i++) {
            if (i != (selectPids.length - 1)) {
                sb.append((selectPids[i] == null ? "" : selectPids[i]) + ",");
            } else {
                sb.append(selectPids[i] == null ? "" : selectPids[i]);
            }
        }
        return sb.toString();
    }

    @Override
    public void loadNetData() {
        baseShowWatLoading();
        String lgUrl = "";
        if (type == 3) {
            lgUrl = AppURL.URL_GET_PART + "tokenKey=" + BaseApplication.getToken();
        } else if (type == 4) {
            lgUrl = AppURL.URL_PERSON_ORDER_INFOMATION + "tokenKey=" + BaseApplication.getToken() + "&itemId=" + itemId;
        } else if (type == 5) {
            lgUrl = AppURL.URL_PERSON_ORDER_FROM_WAIT_TO_INFOMATION + "tokenKey=" + BaseApplication.getToken() + "&itemId=" + itemId;
        }


        L.e("netLogin" + lgUrl);
        VolleyRequestUtils.getInstance().getCookieRequest(this, lgUrl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                baseHideWatLoading();
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    getRingPartResult = new Gson().fromJson(result, GetRingPartResult.class);
                    if (getRingPartResult.getData() == null) {
                        showToastReal("后台数据为空");
                        return;
                    }
                    partsAount = getRingPartResult.getData().getModelpartCount().size();
                    makeProductId = getRingPartResult.getData().getModelItem().getId();
                    modelPicBeans = getRingPartResult.getData().getModelItem().getModelPic();
                    initView();
                } else if (error.equals("2")) {
                    loginToServer(MakingActivity.class);

                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    ToastManager.showToastWhendebug(message);
                    L.e(message);
                }
            }

            @Override
            public void onFail(String fail) {
                baseHideWatLoading();
            }


        });
    }


    @OnClick({R.id.tv_finish, R.id.tv_buy, R.id.id_ig_back, R.id.tv_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_finish:
                if (StringUtils.isEmpty(makeProductId) || selectedStoneEnity == null||jewelStone==null) {
                    if (StringUtils.isEmpty(makeProductId)) {
                        getLostMessage();
                    } else if (selectedStoneEnity == null||jewelStone==null) {
                        showToastReal("请选择裸石");
                    }

                } else {
//                    tvFinish.startAnimation(mHiddenAction);
                    setTotalMoney();
                    tvFinish.setVisibility(View.GONE);
                    llAfterFinish.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_buy:
                addMakingOrder();
                break;
            case R.id.id_ig_back:
                finish();
                break;
            case R.id.tv_reset:
                jewelStone = null;
                loadNetData();
                break;
        }
    }

    private void setTotalMoney() {
        double ringPrice = Double.parseDouble(StringUtils.isEmptyReturnString(selectProItem.getPrice()));
        double stonePrice = Double.parseDouble(StringUtils.isEmptyReturnString(selectedStoneEnity.getPrice()));
        String totalMoney = UIUtils.stringChangeToTwoBitDouble(ringPrice + stonePrice + "");
        if (type == 4 || type == 5) {
            tvBuy.setText("确定（总价：" + totalMoney + ")");
        } else if (type == 3) {
            tvBuy.setText("下单（总价：" + totalMoney + ")");
        }

    }

    private void addMakingOrder() {
        if (StringUtils.isEmpty(handsize)) {
            showToastReal("手寸选择错误");
            return;
        }
        if (jewelStone == null) {
            showToastReal("请选择裸石");
            return;
        }
        String url = "";
        if (type == 3) {
            url = AppURL.URL_PERSON_ADD_ORDER + "tokenKey=" + BaseApplication.getToken() + "&productId=" + makeProductId +
                    "&number=" + "1" + "&handSize=" + handsize + "&jewelStoneId=" + jewelStone.getJewelStoneId();
        } else if (type == 4) {
            //确认编辑
            url = AppURL.URL_PERSON_EDIT_ORDER + "tokenKey=" + BaseApplication.getToken() + "&itemId=" + itemId +
                    "&number=" + "1" + "&handSize=" + handsize + "&productId=" + makeProductId + "&jewelStoneId=" + jewelStone.getJewelStoneId();
        } else if (type == 5) {
            //确认编辑
            url = AppURL.URL_PERSON_ORDER_FROM_WAIT_TO_INFOMATION_EDIT + "tokenKey=" + BaseApplication.getToken() + "&itemId=" + itemId +
                    "&number=" + "1" + "&handSize=" + handsize + "&productId=" + makeProductId + "&jewelStoneId=" + jewelStone.getJewelStoneId();
        }
        L.e("url= " + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("提交订单 result" + result);
                if (new Gson().fromJson(result, JsonObject.class).get("error").getAsString().equals("0")) {
                    if (type == 3) {
                        ToastManager.showToastReal("添加成功");
                    } else if (type == 4) {

                    } else if (type == 5) {

                    }
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", type);
                    openActivity(ConfirmOrderActivity.class, bundle);
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);

                }

            }

            @Override
            public void onFail(String fail) {
                showToastReal("数据获取失败");

            }

        });
    }


    public void deleteSingle(int postion) {
        baseShowWatLoading();
        String lgUrl = AppURL.URL_PERSON_ORDER_SINGLE_DELETE + "tokenKey=" + BaseApplication.getToken() + "&selectPids=" + getSelectPids();
        L.e("url= " + lgUrl);
        VolleyRequestUtils.getInstance().getCookieRequest(this, lgUrl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                baseHideWatLoading();
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    getRingPartResult = new Gson().fromJson(result, GetRingPartResult.class);
                    if (getRingPartResult.getData() == null) {
                        showToastReal("后台数据为空");
                        return;
                    }
                    partsAount = getRingPartResult.getData().getModelpartCount().size();
                    makeProductId = getRingPartResult.getData().getModelItem().getId();
                    initView();
                } else if (error.equals("2")) {
                    loginToServer(MakingActivity.class);
                    baseHideWatLoading();
                } else {
                    baseHideWatLoading();
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    ToastManager.showToastWhendebug(message);
                    L.e(message);
                }
            }

            @Override
            public void onFail(String fail) {
                baseHideWatLoading();
            }


        });
    }
}
