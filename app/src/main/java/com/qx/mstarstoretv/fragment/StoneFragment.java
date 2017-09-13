package com.qx.mstarstoretv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.FinishTableLessActivity;
import com.qx.mstarstoretv.activity.StoneChooseMainActivity;
import com.qx.mstarstoretv.activity.StoneHistoryOrder;
import com.qx.mstarstoretv.activity.StoneSearchResultActivity;
import com.qx.mstarstoretv.adapter.BaseViewHolder;
import com.qx.mstarstoretv.adapter.CommonAdapter;
import com.qx.mstarstoretv.adapter.StoneOthersAdapter;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.BaseFragment;
import com.qx.mstarstoretv.base.Global;
import com.qx.mstarstoretv.json.KeyTitle;
import com.qx.mstarstoretv.json.ModelWeightRange;
import com.qx.mstarstoretv.json.StoneSearchInfo;
import com.qx.mstarstoretv.json.StoneSearchResult;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.CustomGridView;
import com.qx.mstarstoretv.viewutils.CustomLV;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/20 0020.
 */

public class StoneFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.tv_certificate_1)
    TextView tvCertificate1;
    @Bind(R.id.tv_certificate_2)
    TextView tvCertificate2;
    @Bind(R.id.ll_certificate_orgnization)
    LinearLayout llCertificateOrgnization;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.gv_weight)
    CustomGridView gvWeight;
    @Bind(R.id.et_weight_min)
    EditText etWeightMin;
    @Bind(R.id.et_weight_max)
    EditText etWeightMax;
    @Bind(R.id.gv_price)
    GridView gvPrice;
    @Bind(R.id.et_price_min)
    EditText etPriceMin;
    @Bind(R.id.et_price_max)
    EditText etPriceMax;
    @Bind(R.id.gv_color)
    GridView gvColor;
    @Bind(R.id.gv_quality)
    GridView gvQuality;
    @Bind(R.id.ll_shape)
    LinearLayout llShape;
    @Bind(R.id.gv_shape)
    CustomGridView gvShape;
    @Bind(R.id.lv_others)
    CustomLV lvOthers;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.tv_reset)
    TextView tvReset;
    private StoneSearchResult stoneSearchResult;
    private StoneSearchResult.DataBean.CertAuthBean certAuthBean;
    private StoneSearchResult.DataBean.ShapeBean shapeBean;
    private boolean[] certAuthBeanIsChooselist;
    private DecimalFormat df = new DecimalFormat("0.0");
    private DecimalFormat df1 = new DecimalFormat("0");
    private StoneSearchResult.DataBean.ColorBean colorBean;
    private StoneSearchResult.DataBean.PurityBean purityBean;
    private boolean[] colorChecks;
    private boolean[] shapeChecks;
    private boolean[] purityChecks;
    private StoneSearchResult.DataBean.WeightBean weightBean;
    private StoneSearchResult.DataBean.PriceBean priceBean;
    private String weightkey = "";
    private String pricekey = "";
    private boolean[] weightChecks;
    private boolean[] priceChecks;
    private StoneOthersAdapter stoneOthersAdapter;
    private StoneSearchInfo stoneSearchInfo;
    private int openType;//0 是正常进入，1是主石进入,2表示从StoneChooseMain进来
    private String itemId;//产品的id
    private ModelWeightRange modelWeightRange;

    public StoneFragment() {
    }

    public StoneFragment(int openType) {
        this.openType = openType;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_stone_storehouse1, null);
        ButterKnife.bind(this, view);
        baseShowWatLoading();
        loadNetData();
        return view;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (stoneSearchResult == null) {
            baseShowWatLoading();
            loadNetData();
        }
    }

    private void initView() {
        titleText.setText("裸石库");
        /**
         *
         */
        if (openType == 2) {
            idRelTitle.setVisibility(View.GONE);
        }
        idIgBack.setVisibility(View.GONE);
        idIgBack.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        tvReset.setOnClickListener(this);
         modelWeightRange =((StoneChooseMainActivity)getActivity()).getModelWeightRange();
        if(modelWeightRange!=null){
            String st = modelWeightRange.getValue();
            String[] sts = st.split(",");
            if (sts.length == 2) {
                etWeightMin.setText(sts[0]);
                etWeightMax.setText(sts[1]);
            }
        }

        initCertificate();
        initRandSeekBar();
        initShape();
        initColor();
        initPurity();
        initOthers();
    }

    private void initOthers() {
        stoneOthersAdapter = new StoneOthersAdapter(getActivity(), stoneSearchResult.getData());
        lvOthers.setAdapter(stoneOthersAdapter);
        UIUtils.setListViewHeightBasedOnChildren(lvOthers);
    }

    private void initPurity() {
        purityChecks = new boolean[purityBean.getValues().size()];
        final CommonAdapter commonAdapter = new CommonAdapter<String>(purityBean.getValues(), R.layout.item_gv_text) {
            @Override
            public void convert(int position, BaseViewHolder helper, String item) {
                if (purityChecks[position]) {
                    helper.setText(R.id.tv_item_text, item, R.drawable.corners_red_bg, getResources().getColor(R.color.white));
                } else {
                    helper.setText(R.id.tv_item_text, item, R.drawable.corners_white_bg, getResources().getColor(R.color.text_color));
                }
            }
        };
        gvQuality.setAdapter(commonAdapter);
        gvQuality.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                purityChecks[position] = !purityChecks[position];
                commonAdapter.notifyDataSetChanged();

            }
        });
        setListViewHeightBasedOnChildren(gvQuality, 5);
    }

    private void initColor() {
        List<String> colorList = colorBean.getValues();
        colorChecks = new boolean[colorList.size()];
        final CommonAdapter colorAdapter = new CommonAdapter<String>(colorList, R.layout.item_gv_text) {
            @Override
            public void convert(int position, BaseViewHolder helper, String item) {
                if (colorChecks[position]) {
                    helper.setText(R.id.tv_item_text, item, R.drawable.corners_red_bg, getResources().getColor(R.color.white));
                } else {
                    helper.setText(R.id.tv_item_text, item, R.drawable.corners_white_bg, getResources().getColor(R.color.text_color));
                }

            }
        };
        gvColor.setAdapter(colorAdapter);
        gvColor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                colorChecks[position] = !colorChecks[position];
                colorAdapter.notifyDataSetChanged();

            }
        });
        setListViewHeightBasedOnChildren(gvColor, 7);
    }

    private void initShape() {
        List<StoneSearchResult.DataBean.ShapeBean.ValuesBean> shapeList = shapeBean.getValues();
        shapeChecks = new boolean[shapeList.size()];
        final CommonAdapter shapeAdapter = new CommonAdapter<StoneSearchResult.DataBean.ShapeBean.ValuesBean>(shapeList, R.layout.item_gv_shape) {
            @Override
            public void convert(final int position, final BaseViewHolder helper, final StoneSearchResult.DataBean.ShapeBean.ValuesBean item) {
                helper.setImageBitmapHeight(R.id.iv_item_shape, 1.2);
                if (shapeChecks[position]) {
                    helper.setImageBitmap(R.id.iv_item_shape, item.getPic1());

                } else {
                    helper.setImageBitmap(R.id.iv_item_shape, item.getPic());
                }
                helper.setViewOnclick(R.id.iv_item_shape, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shapeChecks[position] = !shapeChecks[position];
                        if (shapeChecks[position]) {
                            helper.setImageBitmap(R.id.iv_item_shape, item.getPic1());
                        } else {
                            helper.setImageBitmap(R.id.iv_item_shape, item.getPic());
                        }
                    }
                });

            }
        };
        gvShape.setAdapter(shapeAdapter);
//        gvShape.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                shapeChecks[position] = !shapeChecks[position];
//                shapeAdapter.notifyDataSetChanged();
//            }
//        });
        if (UIUtils.isPad(getActivity())) {
            gvShape.setNumColumns(10);
            setListViewHeightBasedOnChildren(gvShape, 10);
        } else {
            if (UIUtils.isScreenChange(getActivity())) {
                gvShape.setNumColumns(8);
                setListViewHeightBasedOnChildren(gvShape, 8);
            } else {
                gvShape.setNumColumns(5);
                setListViewHeightBasedOnChildren(gvShape, 5);
            }
        }

    }

    private String getselectColor() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < colorChecks.length; i++) {
            if (colorChecks[i]) {
                sb.append(colorBean.getValues().get(i) + "  ");
            }
        }
        return sb.toString();
    }

    private String getselectPurity() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < purityChecks.length; i++) {
            if (purityChecks[i]) {
                sb.append(purityBean.getValues().get(i) + "  ");
            }
        }
        return sb.toString();
    }

    private void initRandSeekBar() {

        final List<KeyTitle> weightList = weightBean.getList();
        final List<KeyTitle> priceList = priceBean.getList();
        if (weightList == null) {
            return;
        }
        if (priceList == null) {
            return;
        }
        weightChecks = new boolean[weightList.size()];
        priceChecks = new boolean[priceList.size()];
        gvWeight.setNumColumns(weightList.size());
        final CommonAdapter weightAdapter = new CommonAdapter<KeyTitle>(weightList, R.layout.item_gv_text2) {
            @Override
            public void convert(int position, BaseViewHolder helper, KeyTitle item) {
                if (weightChecks[position]) {
                    helper.setText(R.id.tv_item_text, item.getTitle(), R.drawable.corners_red_bg, getResources().getColor(R.color.white));
                } else {
                    helper.setText(R.id.tv_item_text, item.getTitle(), R.drawable.corners_white_bg, getResources().getColor(R.color.text_color));
                }
            }
        };
        gvWeight.setAdapter(weightAdapter);
        gvWeight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = weightList.get(position).getKey();
                String[] values = value.split(",");
                etWeightMin.setText(values[0]);
                if (values[1].equals("0")) {
                    etWeightMax.setText("");
                } else {
                    etWeightMax.setText(values[1]);
                }

                if (weightChecks[position]) {
                    weightChecks[position] = !weightChecks[position];
                    weightkey = "";
                } else {
                    clearCheck(weightChecks);
                    weightChecks[position] = !weightChecks[position];
                    weightkey = weightList.get(position).getKey();
                }
                weightAdapter.notifyDataSetChanged();
            }
        });
        etWeightMin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    clearCheck(weightChecks);
                    weightAdapter.notifyDataSetChanged();
                    weightkey = "";
                }
                return false;
            }
        });
        etWeightMax.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    clearCheck(weightChecks);
                    weightAdapter.notifyDataSetChanged();
                    weightkey = "";
                }
                return false;
            }
        });


        gvPrice.setNumColumns(4);
        final CommonAdapter priceAdapter = new CommonAdapter<KeyTitle>(priceList, R.layout.item_gv_text2) {
            @Override
            public void convert(int position, BaseViewHolder helper, KeyTitle item) {
                if (priceChecks[position]) {
                    helper.setText(R.id.tv_item_text, item.getTitle(), R.drawable.corners_red_bg, getResources().getColor(R.color.white));
                } else {
                    helper.setText(R.id.tv_item_text, item.getTitle(), R.drawable.corners_white_bg, getResources().getColor(R.color.text_color));
                }
            }

        };
        gvPrice.setAdapter(priceAdapter);
        gvPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String st = priceList.get(position).getKey();
                String[] values = st.split(",");
                etPriceMin.setText(values[0]);
                if (values[1].equals("0")) {
                    etPriceMax.setText("");
                } else {
                    etPriceMax.setText(values[1]);
                }

                if (priceChecks[position]) {
                    priceChecks[position] = !priceChecks[position];
                    pricekey = "";
                } else {
                    clearCheck(priceChecks);
                    priceChecks[position] = !priceChecks[position];
                    pricekey = priceList.get(position).getKey();
                }
                priceAdapter.notifyDataSetChanged();
            }
        });
        etPriceMax.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    clearCheck(priceChecks);
                    priceAdapter.notifyDataSetChanged();
                    pricekey = "";
                }
                return false;
            }
        });
        etPriceMin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    clearCheck(priceChecks);
                    priceAdapter.notifyDataSetChanged();
                    pricekey = "";
                }
                return false;
            }
        });


    }

    private void clearCheck(boolean[] Checks) {
        for (int i = 0; i < Checks.length; i++) {
            Checks[i] = false;
        }
    }


    private void initCertificate() {
        tvCertificate1.setText(certAuthBean.getValues().get(0));
        tvCertificate2.setText(certAuthBean.getValues().get(1));
        tvCertificate1.setOnClickListener(this);
        tvCertificate2.setOnClickListener(this);
        certAuthBeanIsChooselist = new boolean[2];

    }


    public void loadNetData() {
        String url = "";
        url = AppURL.URL_STONE_SEARCHINFO + "tokenKey=" + BaseApplication.getToken();
        if (StringUtils.isEmpty(url)) {
            return;
        }
        L.e("获取地址" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(getActivity(), url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                baseHideWatLoading();
                L.e("result" + result);
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    stoneSearchResult = new Gson().fromJson(result, StoneSearchResult.class);
                    if (stoneSearchResult.getData() == null) {
                        return;
                    }
                    certAuthBean = stoneSearchResult.getData().getCertAuth();
                    shapeBean = stoneSearchResult.getData().getShape();
                    colorBean = stoneSearchResult.getData().getColor();
                    purityBean = stoneSearchResult.getData().getPurity();
                    weightBean = stoneSearchResult.getData().getWeight();
                    priceBean = stoneSearchResult.getData().getPrice();
                    initView();
                } else if (error.equals("2")) {
                    loginToServer(FinishTableLessActivity.class);
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal("数据加载错误！");
                }
            }

            @Override
            public void onFail(String fail) {
                ToastManager.showToastReal("数据获取失败");
                baseHideWatLoading();
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_certificate_1:
                isChoose(0);
                break;
            case R.id.tv_certificate_2:
                isChoose(1);
                break;
            case R.id.id_ig_back:

                break;
            case R.id.tv_search:
                if (searchStone()) {
                    gotoResult();
                }
                break;
            case R.id.tv_reset:
                reset();
                baseShowWatLoading();
                loadNetData();
                break;
            case R.id.tv_right:
                Intent intent = new Intent(getActivity(), StoneHistoryOrder.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
        }
    }

    private boolean searchStone() {
        stoneSearchInfo = new StoneSearchInfo();
        stoneSearchInfo.setCerAuth(getCerAuth());
        if (weightkey.equals("")) {
            String weightMin, weightMax;
            weightMin = etWeightMin.getText().toString();
            weightMax = etWeightMax.getText().toString();
            if (!weightMin.isEmpty() && !weightMax.isEmpty() && Double.parseDouble(weightMin) > Double.parseDouble(weightMax)) {
                ToastManager.showToastReal("克拉搜索输入有误！");
                return false;
            }
            if (weightMax.isEmpty()) {
                weightMax = "0";
            }
            if (weightMin.isEmpty()) {
                weightMin = "0";
            }

            stoneSearchInfo.setWeight(weightMin + "," + weightMax);
        } else {
            stoneSearchInfo.setWeight(weightkey);
        }
        if (pricekey.equals("")) {
            String priceMin, priceMax;
            priceMin = etPriceMin.getText().toString();
            priceMax = etPriceMax.getText().toString();
            if ((!priceMax.isEmpty() && !priceMin.isEmpty()) && Double.parseDouble(priceMin) > Double.parseDouble(priceMax)) {
                ToastManager.showToastReal("价格搜索输入有误！");
                return false;
            }
            if (priceMax.isEmpty()) {
                priceMax = "0";
            }
            if (priceMin.isEmpty()) {
                priceMin = "0";
            }
            stoneSearchInfo.setPrice(priceMin + "," + priceMax);
        } else {
            stoneSearchInfo.setPrice(pricekey);
        }

        stoneSearchInfo.setShape(getShape());
        stoneSearchInfo.setColor(getStoneColor());
        stoneSearchInfo.setPurity(getPurity());
        stoneSearchInfo.setCut(stoneOthersAdapter.getChooseResult(0));
        stoneSearchInfo.setPolishing(stoneOthersAdapter.getChooseResult(1));
        stoneSearchInfo.setSymmetric(stoneOthersAdapter.getChooseResult(2));
        stoneSearchInfo.setFluorescence(stoneOthersAdapter.getChooseResult(3));
        return true;
    }

    private void reset() {

        pricekey = "";
        weightkey = "";
        etPriceMax.setText("");
        etPriceMin.setText("");
        etWeightMax.setText("");
        etWeightMin.setText("");
        certAuthBeanIsChooselist[0] = false;
        tvCertificate1.setTextColor(getResources().getColor(R.color.text_color));
        tvCertificate1.setBackgroundResource(R.drawable.corners_white_bg);
        certAuthBeanIsChooselist[1] = false;
        tvCertificate2.setTextColor(getResources().getColor(R.color.text_color));
        tvCertificate2.setBackgroundResource(R.drawable.corners_white_bg);
    }

    private String getPurity() {
        List<String> puritys = purityBean.getValues();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < puritys.size(); i++) {
            if (purityChecks[i]) {
                if (sb.toString().equals("")) {
                    sb.append(puritys.get(i));
                } else {
                    sb.append("," + puritys.get(i));
                }
            }
        }
        return sb.toString();
    }

    private String getShape() {
        List<StoneSearchResult.DataBean.ShapeBean.ValuesBean> shapeList = shapeBean.getValues();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shapeList.size(); i++) {
            if (shapeChecks[i]) {
                if (sb.toString().equals("")) {
                    sb.append(shapeList.get(i).getName());
                } else {
                    sb.append("," + shapeList.get(i).getName());
                }
            }
        }
        return sb.toString();

    }

    private String getStoneColor() {
        List<String> colorList = colorBean.getValues();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < colorList.size(); i++) {
            if (colorChecks[i]) {
                if (sb.toString().equals("")) {
                    sb.append(colorList.get(i));
                } else {
                    sb.append("," + colorList.get(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 获得选中的证书
     *
     * @return
     */
    private String getCerAuth() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < certAuthBeanIsChooselist.length; i++) {
            if (certAuthBeanIsChooselist[i]) {
                sb.append(certAuthBean.getValues().get(0));
                if (i != certAuthBeanIsChooselist.length - 1) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

    private void gotoResult() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("searchStoneInfo", stoneSearchInfo);
        Intent intent = new Intent(getActivity(), StoneSearchResultActivity.class);
        intent.putExtra("type", ((StoneChooseMainActivity) getActivity()).getType());
        intent.putExtra("openType", ((StoneChooseMainActivity) getActivity()).getOpenType());
        intent.putExtra("itemId", ((StoneChooseMainActivity) getActivity()).getItemId());
        intent.putExtra("orderId", ((StoneChooseMainActivity) getActivity()).getOrderId());
        intent.putExtra("stoneInfo", bundle);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }


    private void isChoose(int i) {
        if (i == 0) {
            if (!certAuthBeanIsChooselist[0]) {
                tvCertificate1.setTextColor(getResources().getColor(R.color.white));
                tvCertificate1.setBackgroundResource(R.drawable.corners_red_bg);
                certAuthBeanIsChooselist[0] = true;
            } else {
                tvCertificate1.setTextColor(getResources().getColor(R.color.text_color));
                tvCertificate1.setBackgroundResource(R.drawable.corners_white_bg);
                certAuthBeanIsChooselist[0] = false;
            }
        } else {
            if (!certAuthBeanIsChooselist[1]) {
                tvCertificate2.setTextColor(getResources().getColor(R.color.white));
                tvCertificate2.setBackgroundResource(R.drawable.corners_red_bg);
                certAuthBeanIsChooselist[1] = true;
            } else {
                tvCertificate2.setTextColor(getResources().getColor(R.color.text_color));
                tvCertificate2.setBackgroundResource(R.drawable.corners_white_bg);
                certAuthBeanIsChooselist[1] = false;
            }
        }
    }

    // 动态加载GridView 高度
    public static void setListViewHeightBasedOnChildren(GridView myGridView, int num) {
        ListAdapter listAdapter = myGridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int col = num;
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            View listItem = listAdapter.getView(i, null, myGridView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = myGridView.getLayoutParams();
        params.height = totalHeight + 20;
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        myGridView.setLayoutParams(params);
    }

    // 动态加载GridView 高度
    public static void setGridViewWidthBasedOnChildren(GridView myGridView) {
        ListAdapter listAdapter = myGridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalWidth = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, myGridView);
            listItem.measure(0, 0);
            totalWidth += listItem.getMeasuredWidth();
        }
        ViewGroup.LayoutParams params = myGridView.getLayoutParams();
        params.height = totalWidth + 20;
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        myGridView.setLayoutParams(params);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
