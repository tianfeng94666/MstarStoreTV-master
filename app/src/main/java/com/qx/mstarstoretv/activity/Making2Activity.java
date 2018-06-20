package com.qx.mstarstoretv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qx.mstarstoretv.ItemDecoration.SpaceItemDecoration;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.adapter.RecycleViewPartListAdapter;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.json.GetPartListResult;
import com.qx.mstarstoretv.json.GetRingPartResult;
import com.qx.mstarstoretv.json.JewelStone;
import com.qx.mstarstoretv.json.ModelPartsBean;
import com.qx.mstarstoretv.json.ModelPicBean;
import com.qx.mstarstoretv.json.SelectProItemBean;
import com.qx.mstarstoretv.json.StoneSearchInfoResult;
import com.qx.mstarstoretv.manager.FitGridLayoutManager;
import com.qx.mstarstoretv.net.ImageLoadOptions;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.CustomselectStringButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class Making2Activity extends BaseActivity {


    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_product)
    CircleImageView ivProduct;
    @Bind(R.id.iv_ring_head)
    ImageView ivRingHead;
    @Bind(R.id.tv_ring_head)
    TextView tvRingHead;
    @Bind(R.id.rl_ring_head)
    RelativeLayout rlRingHead;
    @Bind(R.id.iv_ring_interface)
    ImageView ivRingInterface;
    @Bind(R.id.tv_ring_interface)
    TextView tvRingInterface;
    @Bind(R.id.rl_ring_interface)
    RelativeLayout rlRingInterface;
    @Bind(R.id.iv_ring_body)
    ImageView ivRingBody;
    @Bind(R.id.tv_ring_body)
    TextView tvRingBody;
    @Bind(R.id.rl_ring_body)
    RelativeLayout rlRingBody;
    @Bind(R.id.tv_ring_stone)
    TextView tvRingStone;
    @Bind(R.id.rl_ring_stone)
    RelativeLayout rlRingStone;
    @Bind(R.id.rl_main)
    RelativeLayout rlMain;
    @Bind(R.id.tv_reset)
    TextView tvReset;
    @Bind(R.id.tv_finish)
    TextView tvFinish;
    @Bind(R.id.rv_part)
    RecyclerView rvPart;
    @Bind(R.id.rl_choose)
    RelativeLayout rlChoose;
    @Bind(R.id.rl_main_commit)
    RelativeLayout rlMainCommit;
    @Bind(R.id.tv_cancle)
    TextView tvCancle;
    @Bind(R.id.tv_comfirm)
    TextView tvComfirm;
    @Bind(R.id.rl_choose_commit)
    RelativeLayout rlChooseCommit;
    @Bind(R.id.tv_choose_handsize)
    CustomselectStringButton tvChooseHandsize;
    @Bind(R.id.tv_buy)
    TextView tvBuy;
    @Bind(R.id.tv_delete)
    TextView tvDelete;
    @Bind(R.id.iv_logo)
    ImageView ivLogo;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    private FitGridLayoutManager mLayoutManager;
    private GetRingPartResult getRingPartResult;
    private List<ModelPicBean> modelPicBeans;
    private List<ModelPartsBean> partsList;
    private List<String> countList;
    private GetPartListResult getPartListResult;
    private List<ModelPartsBean> partList;//弹窗中的组件list
    private String[] selectPids;

    private boolean isScreenLand;
    private String makeProductId;
    private String handsize;
    private String itemId;//编辑的订单Id
    private int type = 3;//打开类型 3直接打开 4编辑过来的 5待审核过来编辑
    private Bundle extras;//记录打开类型
    private SelectProItemBean selectProItem; //选中后的产品
    private StoneSearchInfoResult.DataBean.StoneBean.ListBean selectedStoneEnity;
    private int partsAount;//记录parts个数 方便添加stone选择
    private JewelStone jewelStone;

    private int mainPostion = -1;//主界面选中的哪一项（0头1是接口2是圈）
    private RecycleViewPartListAdapter recycleViewPartAdapter;


    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_making2);
        ButterKnife.bind(this);

        getData(getIntent());

    }


    private void getStone() {
        if (StringUtils.isEmpty(makeProductId)) {
            getLostMessage();
        } else {
            Intent intent = new Intent(Making2Activity.this, StoneSearchInfoActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("itemId", itemId);
            if (selectProItem != null) {
                intent.putExtra("stoneValueRange", selectProItem.getStoneWeightRange());
            }
            startActivity(intent);
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        }
    }

    private void getData(Intent intent) {

        extras = intent.getExtras();
        if (extras != null) {
            selectedStoneEnity = (StoneSearchInfoResult.DataBean.StoneBean.ListBean) extras.getSerializable("stone");
            itemId = extras.getString("itemId");
            type = extras.getInt("type");
        }

        if (selectedStoneEnity != null) {//选完石头回来
            initView();
        } else {
            loadNetData();
        }

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getData(intent);
    }

    private void initView() {


        isScreenLand = UIUtils.isScreenChange(this);


        if (getRingPartResult != null) {
            partsList = getRingPartResult.getData().getModelParts();
            countList = getRingPartResult.getData().getModelpartCount();
            modelPicBeans = getRingPartResult.getData().getModelItem().getModelPic();
            selectPids = new String[countList.size()];
            //产品图片
            ImageLoader.getInstance().displayImage(modelPicBeans.get(0).getPicb(), ivProduct, ImageLoadOptions.getOptionsHight());
            //设置戒指部分
            setParts();


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

                }
            }
            tvRingStone.setText(stone.getTitle());
        }


        if (partsList != null) {

//            选择手寸
            if (type != 3) {
                tvChooseHandsize.setDefaultText(getRingPartResult.getData().getModelItem().getHandSize());
                handsize = getRingPartResult.getData().getModelItem().getHandSize();
            } else {
                tvChooseHandsize.setDefaultText(StringUtils.isEmpty(handsize) ? "选择手寸" : handsize);
            }
            tvChooseHandsize.setOnSelectData(new CustomselectStringButton.OnselectData() {
                @Override
                public List<String> getData() {
                    return getRingPartResult.getData().getHandSizeData();
                }

                @Override
                public void getSelectId(String type) {
                    handsize = type;
                    if (StringUtils.isEmpty(makeProductId) || selectedStoneEnity == null || jewelStone == null) {
                        if (StringUtils.isEmpty(makeProductId)) {
                            getLostMessage();
                        } else if (selectedStoneEnity == null || jewelStone == null) {
                            showToastReal("请选择裸石");
                        }

                    } else {
                        setTotalMoney();

                    }

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

        setTotalMoney();
    }

    private void setParts() {
        setPartsData(ivRingHead, tvRingHead, 0);
        setPartsData(ivRingInterface, tvRingInterface, 1);
        setPartsData(ivRingBody, tvRingBody, 2);
    }

    private void setPartsData(ImageView iv, TextView tv, int i) {
        ModelPartsBean model = partsList.get(i);
        ImageLoader.getInstance().displayImage(model.getPicm(), iv, ImageLoadOptions.getOptionsHight());

        if (StringUtils.isEmpty(countList.get(i))) {
            tv.setText(model.getTitle());
        } else {
            tv.setText(model.getTitle() + "/" + countList.get(i));
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


    private void getItemSource() {
        hideRlMain();
        baseShowWatLoading();
        String lgUrl = AppURL.URL_GET_PART_SOURCE + "tokenKey=" + BaseApplication.getToken() + "&partSort=" + partsList.get(mainPostion).getPartSort() + "&selectPids=" + getSelectPids();
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
                    mLayoutManager = new FitGridLayoutManager(context, rvPart, 3, GridLayoutManager.VERTICAL, false);
                    rvPart.setLayoutManager(mLayoutManager);

                    if (recycleViewPartAdapter == null) {

                        //设置item间距，30dp
                        rvPart.addItemDecoration(new SpaceItemDecoration(60, 60, 0, 30));
                    }
                    recycleViewPartAdapter = new RecycleViewPartListAdapter(context, partList, new RecycleViewPartListAdapter.PartListItemClickListener() {
                        @Override
                        public void onItemClick(View view, int postion) {
                            recycleViewPartAdapter.setChoose(postion);
                        }
                    });
                    rvPart.setAdapter(recycleViewPartAdapter);


                } else if (error.equals("2")) {
                    baseHideWatLoading();
                    loginToServer(Making2Activity.class);
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

    private void hideRlMain() {
        rlMain.setVisibility(View.GONE);
        rlMainCommit.setVisibility(View.GONE);
        ivLogo.setVisibility(View.GONE);

        rlChoose.setVisibility(View.VISIBLE);
        rlChooseCommit.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
    }

    private void showMainRl() {
        rlMain.setVisibility(View.VISIBLE);
        rlMainCommit.setVisibility(View.VISIBLE);
        ivLogo.setVisibility(View.VISIBLE);

        rlChoose.setVisibility(View.GONE);
        rlChooseCommit.setVisibility(View.GONE);
        tvTitle.setVisibility(View.GONE);
    }

    //
    private String getSelectPids() {

        //减去添加的裸石数量
        for (int i = 0; i < partsList.size() - 1; i++) {
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
                    initView();
                } else if (error.equals("2")) {
                    loginToServer(Making2Activity.class);

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

    @OnClick({R.id.rl_ring_head, R.id.rl_ring_interface, R.id.rl_ring_body, R.id.rl_ring_stone, R.id.tv_reset, R.id.tv_finish, R.id.tv_cancle, R.id.tv_comfirm, R.id.tv_delete,R.id.id_ig_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_ring_head:
                mainPostion = 0;
                getItemSource();
                tvTitle.setText("选 择 头");
                break;
            case R.id.rl_ring_interface:
                mainPostion = 1;
                getItemSource();
                tvTitle.setText("选 择 接 口");
                break;
            case R.id.rl_ring_body:
                mainPostion = 2;
                tvTitle.setText("选 择 圆 圈");
                getItemSource();
                break;
            case R.id.rl_ring_stone:
                getStone();
                break;
            case R.id.tv_reset:
                jewelStone = null;
                loadNetData();
                break;
            case R.id.tv_finish:
                addMakingOrder();
                break;
            case R.id.tv_cancle:
                showMainRl();
                break;
            case R.id.tv_comfirm:
                chooseProduct();
                break;
            case R.id.tv_delete:
                partsList.get(mainPostion).setPid("");
                deleteSingle(mainPostion);
                break;
            case R.id.id_ig_back:
                finish();
                break;
        }
    }


    private void chooseProduct() {
        //判断是否选中
        int selectPosition = recycleViewPartAdapter.getChoose();
        if (selectPosition == -1) {
            showToastReal("请选择后在确定");
            return;
        }
        rePartsView(selectPosition);

    }

    private void rePartsView(int selectPosition) {
        selectPids[mainPostion] = partList.get(selectPosition).getPid();
        partsList.set(mainPostion, partList.get(selectPosition));
        selectProItem = partList.get(selectPosition).getSelectProItem();
        makeProductId = "";
        if (selectProItem != null) {
            modelPicBeans = selectProItem.getModelPic();
            startAnimaltion();
            getRingPartResult.getData().setModelItem(selectProItem);
            ImageLoader.getInstance().displayImage(selectProItem.getModelPic().get(0).getPicb(), ivProduct, ImageLoadOptions.getOptionsHight());
            makeProductId = selectProItem.getId();
            setTotalMoney();

        }

        countList.clear();
        countList.addAll(partList.get(selectPosition).getModelPartCount());
        recycleViewPartAdapter.notifyDataSetChanged();
        setParts();
        showMainRl();
    }

    private void startAnimaltion() {
        UIUtils.animToTagOnWindows(this, ivRingHead, ivProduct, 0);
        UIUtils.animToTagOnWindows(this, ivRingBody, ivProduct, 0);
        UIUtils.animToTagOnWindows(this, ivRingInterface, ivProduct, 0);
    }


    private void setTotalMoney() {
        double ringPrice;
        double stonePrice;
        if (selectProItem != null) {
            ringPrice = Double.parseDouble(StringUtils.isEmptyReturnString(selectProItem.getPrice()));
        } else {
            ringPrice = 0;
        }
        if (jewelStone != null) {
            stonePrice = Double.parseDouble(StringUtils.isEmptyReturnString(jewelStone.getJewelStonePrice()));
        } else {
            stonePrice = 0;
        }

        String totalMoney = UIUtils.stringChangeToTwoBitDouble(ringPrice + stonePrice + "");
        if (type == 4 || type == 5) {
            tvBuy.setText("¥" + totalMoney);
        } else if (type == 3) {
            tvBuy.setText("¥" + totalMoney);
        }

    }

    private void addMakingOrder() {
        if (StringUtils.isEmpty(makeProductId) || jewelStone == null) {
            if (StringUtils.isEmpty(makeProductId)) {
                getLostMessage();
            } else if (jewelStone == null) {
                showToastReal("请选择裸石");
            }
            return;

        }
        if (StringUtils.isEmpty(handsize)) {
            showToastReal("手寸选择错误");
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
                    showMainRl();
                } else if (error.equals("2")) {
                    loginToServer(Making2Activity.class);
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
