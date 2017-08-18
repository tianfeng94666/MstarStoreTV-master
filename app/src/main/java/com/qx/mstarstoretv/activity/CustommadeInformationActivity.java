package com.qx.mstarstoretv.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
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
import com.qx.mstarstoretv.bean.Type;
import com.qx.mstarstoretv.json.ModelDetailResult;
import com.qx.mstarstoretv.json.StoneEntity;
import com.qx.mstarstoretv.net.ImageLoadOptions;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.viewutils.CustomSelectButton;
import com.qx.mstarstoretv.viewutils.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * 创建人：Yangshao
 * 创建时间：2016/9/23 15:50
 * @version     款号信息
 *
 */
public class CustommadeInformationActivity extends BaseActivity {


    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    TextView tvCurOrder;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.id_store_type)
    CustomSelectButton idStoreType;
    @Bind(R.id.id_store_norm)
    CustomSelectButton idStoreNorm;
    @Bind(R.id.id_store_shape)
    CustomSelectButton idStoreShape;
    @Bind(R.id.id_store_color)
    CustomSelectButton idStoreColor;
    @Bind(R.id.id_store_cut)
    CustomSelectButton idStoreCut;
    @Bind(R.id.id_store_price)
    CustomSelectButton idStorePrice;
    @Bind(R.id.id_storea_type)
    CustomSelectButton idStoreaType;
    @Bind(R.id.id_storea_norm)
    CustomSelectButton idStoreaNorm;
    @Bind(R.id.id_storea_shape)
    CustomSelectButton idStoreaShape;
    @Bind(R.id.id_storea_color)
    CustomSelectButton idStoreaColor;
    @Bind(R.id.id_storea_cut)
    CustomSelectButton idStoreaCut;
    @Bind(R.id.id_storea_price)
    CustomSelectButton idStoreaPrice;
    @Bind(R.id.id_storeb_type)
    CustomSelectButton idStorebType;
    @Bind(R.id.id_storeb_norm)
    CustomSelectButton idStorebNorm;
    @Bind(R.id.id_storeb_shape)
    CustomSelectButton idStorebShape;
    @Bind(R.id.id_storeb_color)
    CustomSelectButton idStorebColor;
    @Bind(R.id.id_storeb_cut)
    CustomSelectButton idStorebCut;
    @Bind(R.id.id_storeb_price)
    CustomSelectButton idStorebPrice;
    @Bind(R.id.id_storec_type)
    CustomSelectButton idStorecType;
    @Bind(R.id.id_storec_norm)
    CustomSelectButton idStorecNorm;
    @Bind(R.id.id_storec_shape)
    CustomSelectButton idStorecShape;
    @Bind(R.id.id_storec_color)
    CustomSelectButton idStorecColor;
    @Bind(R.id.id_storec_cut)
    CustomSelectButton idStorecCut;
    @Bind(R.id.id_storec_price)
    CustomSelectButton idStorecPrice;
    @Bind(R.id.tv_reset)
    TextView idTvCurorder;
   StoneEntity stone;
    ModelDetailResult.DataEntity.ModelEntity.StoneAEntity stoneA;
    ModelDetailResult.DataEntity.ModelEntity.StoneBEntity stoneB;
    ModelDetailResult.DataEntity.ModelEntity.StoneCEntity stoneC;

    List<ModelDetailResult.DataEntity.StoneTypeEntity> stoneTypeItme; //类型

    List<ModelDetailResult.DataEntity.StoneColorEntity> stoneColorItme;  //颜色

    List<ModelDetailResult.DataEntity.StonePurityEntity> stonePurityItme; //净度

    List<ModelDetailResult.DataEntity.StoneSpecEntity> stoneSpecItme;  //规格

    List<ModelDetailResult.DataEntity.StoneShapeEntity> stoneShapeItem;  //形状

    List<ModelDetailResult.DataEntity.ModelEntity.PicsEntity> pics;
    String id;
    @Bind(R.id.id_menus)
    LinearLayout idMenus;
    @Bind(R.id.id_gv_image)
    MyGridView idGvImage;

    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_custom_information);
        rootView = View.inflate(this,R.layout.activity_custom_information,null);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        id = extras.getString("itemId");
        initView();
        loadNetData();
    }

    @Override
    public void loadNetData() {
        String url = AppURL.URL_MODEL_DETAIL + "tokenKey=" + BaseApplication.getToken() + "&itemId=" + id;
        L.e("获取款号" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack(){
            @Override
            public void onSuccess(String result) {
                L.e(result);
                ModelDetailResult modelDetail = new Gson().fromJson(result, ModelDetailResult.class);
                String error=String.valueOf(modelDetail.getError());
                if (error.equals("1")){
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);
                }
                if (error.equals("2")){
                    loginToServer(CustommadeInformationActivity.class);
                }if (error.equals("0")){
                    ModelDetailResult.DataEntity dataEntity = modelDetail.getData();
                    if(dataEntity!=null){
                        List<ModelDetailResult.DataEntity.GoldenPriceEntity> goldenPrice = dataEntity.getGoldenPrice();
                        StringBuffer sb = new StringBuffer();
                        for (int i = 0; i < goldenPrice.size(); i++) {
                            sb.append(goldenPrice.get(i).getTitle() + " " + goldenPrice.get(i).getPrice());
                        }


                        pics = dataEntity.getModel().getPics();
                        stone = dataEntity.getModel().getStone();
                        stoneA = dataEntity.getModel().getStoneA();
                        stoneB = dataEntity.getModel().getStoneB();
                        stoneC = dataEntity.getModel().getStoneC();


                        stoneTypeItme = dataEntity.getStoneType();
                        stoneColorItme = dataEntity.getStoneColor();
                        stonePurityItme = dataEntity.getStonePurity();
                        stoneSpecItme = dataEntity.getStoneSpec();
                        stoneShapeItem = dataEntity.getStoneShape();
                        initParameter();
                    }

                }


            }


            @Override
            public void onFail(String fail) {

            }
        });

    }


    public void loadStonePrice(final StoneEntity stoneEntity) {
        String url = AppURL.URL_STONE_PRICE + "tokenKey=" + BaseApplication.getToken() + "&colorId=" + stoneEntity.getColorId() +
                "&categoryId=" + stoneEntity.getTypeId() + "&specId=" + stoneEntity.getSpecId() + "&purityId=" + stoneEntity.getPurityId();
        L.e("查询价格:    " + url);

        VolleyRequestUtils.getInstance().getCookieRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e(result);
                Gson gson = new Gson();
                String error = gson.fromJson(result, JsonObject.class).get("error").getAsString();
                if (error.equals("0")) {
                    //gson.fromJson(result, BaseResult.class);
                    String price = gson.fromJson(result, JsonObject.class).getAsJsonObject("data").get("price").getAsString();
                    stoneEntity.setPrice(price);
                    initParameter();
                }else if (error.equals("2")){
                    loginToServer(CustommadeInformationActivity.class);
                }else {
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

//    List<LinearLayout> linearLayouts = new ArrayList<>();
//
//    private void layoutBtns() {
//        for (int i = 0; i < pics.size(); i++) {
//            ImageView img = new ImageView(this);
//            img.setBackgroundResource(R.drawable.slecet_while_item);
//            if (i == 0) {
//                img.setBackgroundResource(R.drawable.selector_gridview_item);
//            }
//            ImageLoader.getInstance().displayImage(pics.get(i).getPic(), img, ImageLoadOptions.getOptions());
//            final LinearLayout ll = new LinearLayout(this);
//            ll.setGravity(Gravity.CENTER);
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            ll.addView(img, lp);
//            ll.setTag(i);
//            linearLayouts.add(ll);
////            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(172, 190);
//            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(250, 250);
//            lp1.setMargins(10, 0, 0, 0);
//            ll.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    LinearLayout linearLayout = (LinearLayout) view;
//                }
//            });
//            lymenus.addView(ll, lp1);
//        }
//    }

    private void initParameter() {
        initStone();
        initStoneA();
        initStoneB();
        initStoneC();
        //layoutBtns();
        //initGvImage();
    }

    private void initGvImage() {

        idGvImage.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Object getItem(int i) {
                return i;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                ViewHolder viewHolder = null;
                if (view == null) {
                    viewHolder=new ViewHolder();
                    view = LayoutInflater.from(context).inflate(R.layout.item_gv_image, null);
                    viewHolder.ig = (ImageView) view.findViewById(R.id.btMenu);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }
                ImageLoader.getInstance().displayImage(pics.get(i).getPic(), viewHolder.ig, ImageLoadOptions.getOptions());
                return view;
            }
        });
    }


    public class ViewHolder{
        ImageView ig;
    }

    private void initStoneA() {
        idStoreaType.setTextName(stoneA.getTypeTitle());
        idStoreaType.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择类型 " + id);
                stoneA.setTypeId(id);
                setStorePrice(stoneA);
            }

            @Override
            public String getTitle() {
                return "请选择类型";
            }
        });
        idStoreaColor.setTextName(stoneA.getColorTitle());
        idStoreaColor.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择颜色 " + id);
                stoneA.setColorId(id);
                setStorePrice(stoneA);

            }

            @Override
            public String getTitle() {
                return "请选择颜色";
            }
        });
        idStoreaCut.setTextName(stoneA.getPurityTitle());  //净度
        idStoreaCut.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择净度 " + id);
                stoneA.setPurityId(id);
                setStorePrice(stoneA);
            }

            @Override
            public String getTitle() {
                return "请选择净度";
            }
        });

        idStoreaNorm.setTextName(stoneA.getSpecTitle());  //规格
        idStoreaNorm.setOnSelectData(new CustomSelectButton.OnselectData() {
            @Override
            public List<Type> getData() {
                List<Type> list = new ArrayList<Type>();
                for (int i = 0; i < stoneSpecItme.size(); i++) {
                    Type type = new Type();
                    type.setId(stoneSpecItme.get(i).getId());
                    type.setTypeName(stoneSpecItme.get(i).getTitle());
                    list.add(type);
                }
                return list;
            }
            @Override
            public View getRootView() {
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择规格 " + id);
                stoneA.setSpecId(id);
                setStorePrice(stoneA);
            }

            @Override
            public String getTitle() {
                return "请选择规格";
            }
        });
        idStoreaShape.setTextName(stoneA.getShapeTitle());
        idStoreaShape.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择形状 " + id);
                stoneA.setShapeId(id);
            }

            @Override
            public String getTitle() {
                return "请选择形状";
            }
        });
        idStoreaPrice.setTextName(stoneA.getPrice());

    }

    public void setStorePrice(StoneEntity stoneEntity) {
        if (!(StringUtils.isEmpty(stoneEntity.getColorId()) && StringUtils.isEmpty(stoneEntity.getPurityId())
                && StringUtils.isEmpty(stoneEntity.getSpecId()) && StringUtils.isEmpty(stoneEntity.getTypeId()))) {
            loadStonePrice(stoneEntity);
        }
    }

    private void initStoneB() {

        idStorebType.setTextName(stoneB.getTypeTitle());
        idStorebType.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择类型 " + id);
                stoneB.setTypeId(id);
                setStorePrice(stoneB);
            }

            @Override
            public String getTitle() {
                return "请选择类型";
            }
        });
        idStorebColor.setTextName(stoneB.getColorTitle());
        idStorebColor.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择颜色 " + id);
                stoneB.setColorId(id);
                setStorePrice(stoneB);
            }

            @Override
            public String getTitle() {
                return "请选择颜色";
            }
        });
        idStorebCut.setTextName(stoneB.getPurityTitle());  //净度
        idStorebCut.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择净度 " + id);
                stoneB.setPurityId(id);
                setStorePrice(stoneB);
            }

            @Override
            public String getTitle() {
                return "请选择净度";
            }
        });
        idStorebNorm.setTextName(stoneB.getSpecTitle());  //规格
        idStorebNorm.setOnSelectData(new CustomSelectButton.OnselectData() {
            @Override
            public List<Type> getData() {
                List<Type> list = new ArrayList<Type>();
                for (int i = 0; i < stoneSpecItme.size(); i++) {
                    Type type = new Type();
                    type.setId(stoneSpecItme.get(i).getId());
                    type.setTypeName(stoneSpecItme.get(i).getTitle());
                    list.add(type);
                }
                return list;
            }
            @Override
            public View getRootView() {
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择规格 " + id);
                stoneB.setSpecId(id);
                setStorePrice(stoneB);
            }

            @Override
            public String getTitle() {
                return "请选择规格";
            }
        });
        idStorebShape.setTextName(stoneB.getShapeTitle());
        idStorebShape.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择形状 " + id);
                stoneB.setShapeId(id);
            }

            @Override
            public String getTitle() {
                return "请选择形状";
            }
        });
        idStorebPrice.setTextName(stoneB.getPrice());

    }

    private void initStoneC() {
        idStorecType.setTextName(stoneC.getTypeTitle());
        idStorecType.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择类型 " + id);
                stoneC.setTypeId(id);
            }

            @Override
            public String getTitle() {
                return "请选择类型";
            }
        });
        idStorecColor.setTextName(stoneC.getColorTitle());
        idStorecColor.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择颜色 " + id);
                stoneC.setColorId(id);
                setStorePrice(stoneC);
            }

            @Override
            public String getTitle() {
                return "请选择颜色";
            }
        });
        idStorecCut.setTextName(stoneC.getPurityTitle());  //净度
        idStorecCut.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择净度 " + id);
                stoneC.setPurityId(id);
                setStorePrice(stoneC);
            }

            @Override
            public String getTitle() {
                return "请选择净度";
            }
        });
        idStorecNorm.setTextName(stoneC.getSpecTitle());  //规格
        idStorecNorm.setOnSelectData(new CustomSelectButton.OnselectData() {
            @Override
            public List<Type> getData() {
                List<Type> list = new ArrayList<Type>();
                for (int i = 0; i < stoneSpecItme.size(); i++) {
                    Type type = new Type();
                    type.setId(stoneSpecItme.get(i).getId());
                    type.setTypeName(stoneSpecItme.get(i).getTitle());
                    list.add(type);
                }
                return list;
            }
            @Override
            public View getRootView() {
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择规格 " + id);
                stoneC.setSpecId(id);
                setStorePrice(stoneC);
            }

            @Override
            public String getTitle() {
                return "请选择规格";
            }
        });
        idStorecShape.setTextName(stoneC.getShapeTitle());
        idStorecShape.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择形状 " + id);
                stoneC.setShapeId(id);
            }

            @Override
            public String getTitle() {
                return "请选择形状";
            }
        });
        idStorecPrice.setTextName(stoneC.getPrice());
    }

    /**
     * 初始化主石
     */
    private void initStone() {
        idStoreType.setTextName(stone.getTypeTitle());
        idStoreType.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e(getString(R.string.please_select_type)+ id);
                stone.setTypeId(id);
                setStorePrice(stone);
            }

            @Override
            public String getTitle() {
                return getString(R.string.please_select_type);
            }
        });
        idStoreColor.setTextName(stone.getColorTitle());
        idStoreColor.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e(getString(R.string.please_select_color) + id);
                stone.setColorId(id);
                setStorePrice(stone);
            }

            @Override
            public String getTitle() {
                return getString(R.string.please_select_color);
            }
        });
        idStoreCut.setTextName(stone.getPurityTitle());  //净度
        idStoreCut.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e("请选择净度 " + id);
                stone.setPurityId(id);
                setStorePrice(stone);
            }

            @Override
            public String getTitle() {
                return getString(R.string.please_select_cut);
            }
        });
        idStoreNorm.setTextName(stone.getSpecTitle());  //规格
        idStoreNorm.setOnSelectData(new CustomSelectButton.OnselectData() {
            @Override
            public List<Type> getData() {
                List<Type> list = new ArrayList<Type>();
                for (int i = 0; i < stoneSpecItme.size(); i++) {
                    Type type = new Type();
                    type.setId(stoneSpecItme.get(i).getId());
                    type.setTypeName(stoneSpecItme.get(i).getTitle());
                    list.add(type);
                }
                return list;
            }
            @Override
            public View getRootView() {
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e(getString(R.string.please_select_norm) + id);
                stone.setSpecId(id);
                setStorePrice(stone);
            }

            @Override
            public String getTitle() {
                return getString(R.string.please_select_norm);
            }
        });
        idStoreShape.setTextName(stone.getShapeTitle());
        idStoreShape.setOnSelectData(new CustomSelectButton.OnselectData() {
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
                return rootView;
            }
            @Override
            public void getSelectId(Type type) {
                L.e(getString(R.string.please_select_shape)+ id);
                stone.setShapeId(id);
            }

            @Override
            public String getTitle() {
                return getString(R.string.please_select_shape);
            }
        });
        idStorePrice.setTextName(stone.getPrice());
    }


    public void onBack(View view) {
        finish();
    }

    LinearLayout lymenus;

    protected void initView() {
        titleText.setText("款号信息");
        tvCurOrder = (TextView) findViewById(R.id.tv_reset);
        tvCurOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ConfirmOrderActivity.class, null);
            }
        });
        idStoreType.setText("款号");
        lymenus = (LinearLayout) findViewById(R.id.id_menus);
    }


}
