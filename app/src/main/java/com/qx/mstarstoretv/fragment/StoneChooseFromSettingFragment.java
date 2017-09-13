package com.qx.mstarstoretv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.SimpleStyleInfromationActivity;
import com.qx.mstarstoretv.activity.StoneChooseMainActivity;
import com.qx.mstarstoretv.activity.StyleInfromationActivity;
import com.qx.mstarstoretv.adapter.BaseViewHolder;
import com.qx.mstarstoretv.adapter.CommonAdapter;
import com.qx.mstarstoretv.base.BaseFragment;
import com.qx.mstarstoretv.json.ModelDetailResult;
import com.qx.mstarstoretv.json.StoneDetail;
import com.qx.mstarstoretv.json.StoneEntity;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.CustomGridView;

import static com.qx.mstarstoretv.fragment.StoneFragment.setListViewHeightBasedOnChildren;

/**
 * Created by Administrator on 2017/7/21 0021.
 */

public class StoneChooseFromSettingFragment extends BaseFragment implements View.OnClickListener {
    StoneDetail stoneDetail;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.ll_type)
    LinearLayout llType;
    @Bind(R.id.gv_type)
    GridView gvType;
    @Bind(R.id.tv_weight)
    TextView tvWeight;
    @Bind(R.id.iv_weight_reduce)
    ImageView ivWeightReduce;
    @Bind(R.id.et_weight)
    EditText etWeight;
    @Bind(R.id.iv_weight_add)
    ImageView ivWeightAdd;
    @Bind(R.id.tv_amount)
    TextView tvAmount;
    @Bind(R.id.iv_amount_reduce)
    ImageView ivAmountReduce;
    @Bind(R.id.et_amount)
    EditText etAmount;
    @Bind(R.id.iv_amount_add)
    ImageView ivAmountAdd;
    @Bind(R.id.ll_shape)
    LinearLayout llShape;
    @Bind(R.id.gv_shape)
    CustomGridView gvShape;
    @Bind(R.id.tv_color)
    TextView tvColor;
    @Bind(R.id.ll_color)
    LinearLayout llColor;
    @Bind(R.id.gv_color)
    GridView gvColor;
    @Bind(R.id.tv_quality)
    TextView tvQuality;
    @Bind(R.id.ll_quality)
    LinearLayout llQuality;
    @Bind(R.id.gv_quality)
    GridView gvQuality;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.tv_reset)
    TextView tvReset;
    @Bind(R.id.tv_isshow_more)
    TextView tvIsshowMore;
    private boolean[] colorChecks;
    private boolean[] shapeChecks;
    private boolean[] purityChecks;
    private boolean[] typeChecks;
    StoneEntity stoneEntity;
    boolean isShowMore = false;
    private boolean isCustomized;
    private StoneEntity stoneEntityold;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_stone_choose_setting, null);
        ButterKnife.bind(this, view);
        stoneDetail = ((StoneChooseMainActivity) getActivity()).getStoneDetail();
        stoneEntityold = ((StoneChooseMainActivity) getActivity()).getStoneEntity();
        isCustomized = SpUtils.getInstace(getActivity()).getBoolean("isCustomized", true);
        initView();
        return view;
    }

    private void initView() {
        stoneEntity = new StoneEntity(stoneEntityold);
        iniWeightAndAmount();
        initType();
        initPurity();
        initColor();
        initshape();
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private void reset() {
        tvType.setText("");
        etAmount.setText("0");
        etAmount.setText("0");
        tvColor.setText("");
        tvQuality.setText("");
        stoneEntity = new StoneEntity(stoneEntityold);
        initView();
    }

    private void goBack() {
        Intent intent;
        stoneEntity.setSpecTitle(etWeight.getText().toString());
        stoneEntity.setNumber(etAmount.getText().toString());
        if (!isCustomized) {
            intent = new Intent(getActivity(), StyleInfromationActivity.class);
        } else {
            intent = new Intent(getActivity(), SimpleStyleInfromationActivity.class);
        }
        Bundle pBundle = new Bundle();
        pBundle.putString("itemId", ((StoneChooseMainActivity) getActivity()).getItemId());
        pBundle.putInt("type", ((StoneChooseMainActivity) getActivity()).getType());
        pBundle.putString("orderId", ((StoneChooseMainActivity) getActivity()).getOrderId());
        pBundle.putString("openType", "2");
        pBundle.putSerializable("stoneResult", stoneEntity);
        intent.putExtras(pBundle);
        startActivity(intent);
        //设置切换动画，从右边进入，左边退出
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    private void iniWeightAndAmount() {
        if (stoneEntity.getNumber()!=null) {
            etAmount.setText(stoneEntity.getNumber() + "");
        }
        if (stoneEntity.getSpecTitle()!=null) {
            etWeight.setText(stoneEntity.getSpecTitle());
        }

        ivWeightAdd.setOnClickListener(this);
        ivWeightReduce.setOnClickListener(this);
        ivAmountAdd.setOnClickListener(this);
        ivAmountReduce.setOnClickListener(this);
    }

    private void initshape() {
        final List<ModelDetailResult.DataEntity.StoneShapeEntity> list = stoneDetail.getStoneShapeItem();
        shapeChecks = new boolean[list.size()];
        if(stoneEntity.getShapeTitle()!=null){
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTitle().equals(stoneEntity.getShapeTitle())) {
                    shapeChecks[i] = true;
                }
            }
        }

        final CommonAdapter commonAdapter = new CommonAdapter<ModelDetailResult.DataEntity.StoneShapeEntity>(stoneDetail.getStoneShapeItem(), R.layout.item_gv_shape) {
            @Override
            public void convert(final int position, final BaseViewHolder helper, final ModelDetailResult.DataEntity.StoneShapeEntity item) {
                helper.setImageBitmapHeight(R.id.iv_item_shape, 1.2);
                if (shapeChecks[position]) {
                    helper.setImageBitmap(R.id.iv_item_shape, item.getPic1());
                } else {
                    helper.setImageBitmap(R.id.iv_item_shape, item.getPic());
                }
            }


        };
        gvShape.setAdapter(commonAdapter);
        gvShape.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!shapeChecks[position]) {
                    clearCheck(shapeChecks);
                    stoneEntity.setShapeTitle(list.get(position).getTitle());
                    stoneEntity.setShapeId(list.get(position).getId());
                } else {
                    stoneEntity.setShapeId("");
                    stoneEntity.setShapeTitle("");
                }
                shapeChecks[position] = !shapeChecks[position];
                commonAdapter.notifyDataSetChanged();

            }
        });
        if(UIUtils.isPad(getActivity())){
            gvShape.setNumColumns(20);
            setListViewHeightBasedOnChildren(gvShape, 20);
        }else {
            if(UIUtils.isScreenChange(getActivity())){
                gvShape.setNumColumns(8);
                setListViewHeightBasedOnChildren(gvShape, 8);
            }else {
                gvShape.setNumColumns(6);
                setListViewHeightBasedOnChildren(gvShape,6);
            }
        }

    }

    private void initType() {
        final List<ModelDetailResult.DataEntity.StoneTypeEntity> listmore = stoneDetail.getStoneTypeItme();
        List<ModelDetailResult.DataEntity.StoneTypeEntity> listless = listmore.subList(0,20);
        typeChecks = new boolean[listmore.size()];
        if(stoneEntity.getTypeTitle()!=null){
            for (int i = 0; i < listmore.size(); i++) {
                if (listmore.get(i).getTitle().equals(stoneEntity.getTypeTitle())) {
                    typeChecks[i] = true;
                    tvType.setText(listmore.get(i).getTitle());
                }
            }
        }

        final CommonAdapter commonAdapter = new CommonAdapter<ModelDetailResult.DataEntity.StoneTypeEntity>(listmore, R.layout.item_gv_text) {
            @Override
            public void convert(int position, BaseViewHolder helper, ModelDetailResult.DataEntity.StoneTypeEntity item) {
                if (typeChecks[position]) {
                    helper.setText(R.id.tv_item_text, item.getTitle(), R.drawable.corners_red_bg, getResources().getColor(R.color.white));
                } else {
                    helper.setText(R.id.tv_item_text, item.getTitle(), R.drawable.corners_white_bg, getResources().getColor(R.color.text_color));
                }
            }
        };
        final CommonAdapter commonAdapter2 = new CommonAdapter<ModelDetailResult.DataEntity.StoneTypeEntity>(listless, R.layout.item_gv_text) {
            @Override
            public void convert(int position, BaseViewHolder helper, ModelDetailResult.DataEntity.StoneTypeEntity item) {
                if (typeChecks[position]) {
                    helper.setText(R.id.tv_item_text, item.getTitle(), R.drawable.corners_red_bg, getResources().getColor(R.color.white));
                } else {
                    helper.setText(R.id.tv_item_text, item.getTitle(), R.drawable.corners_white_bg, getResources().getColor(R.color.text_color));
                }
            }
        };
        gvType.setAdapter(commonAdapter2);
        gvType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!typeChecks[position]) {
                    clearCheck(typeChecks);
                    tvType.setText(stoneDetail.getStoneTypeItme().get(position).getTitle());
                    stoneEntity.setTypeId(listmore.get(position).getId());
                    stoneEntity.setTypeTitle(listmore.get(position).getTitle());

                } else {
                    tvType.setText("");
                    stoneEntity.setTypeId("");
                    stoneEntity.setTypeTitle("");
                }
                typeChecks[position] = !typeChecks[position];
                commonAdapter.notifyDataSetChanged();
                commonAdapter2.notifyDataSetChanged();

            }
        });
        setListViewHeightBasedOnChildren(gvType, 10);
        tvIsshowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowMore = !isShowMore;
                if (isShowMore) {
                    gvType.setAdapter(commonAdapter);
                    tvIsshowMore.setText("收起来");
                } else {
                    gvType.setAdapter(commonAdapter2);
                    tvIsshowMore.setText("显示更多");
                }
                setListViewHeightBasedOnChildren(gvType, 10);
            }
        });
    }

    private void clearCheck(boolean[] Checks) {
        for (int i = 0; i < Checks.length; i++) {
            Checks[i] = false;
        }
    }

    private void initPurity() {
        final List<ModelDetailResult.DataEntity.StonePurityEntity> list = stoneDetail.getStonePurityItme();
        purityChecks = new boolean[list.size()];
        if(stoneEntity.getPurityTitle()!=null){
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTitle().equals(stoneEntity.getPurityTitle())) {
                    purityChecks[i] = true;
                    tvQuality.setText(list.get(i).getTitle());
                }
            }
        }

        final CommonAdapter commonAdapter = new CommonAdapter<ModelDetailResult.DataEntity.StonePurityEntity>(stoneDetail.getStonePurityItme(), R.layout.item_gv_text) {
            @Override
            public void convert(int position, BaseViewHolder helper, ModelDetailResult.DataEntity.StonePurityEntity item) {
                if (purityChecks[position]) {
                    helper.setText(R.id.tv_item_text, item.getTitle(), R.drawable.corners_red_bg, getResources().getColor(R.color.white));
                } else {
                    helper.setText(R.id.tv_item_text, item.getTitle(), R.drawable.corners_white_bg, getResources().getColor(R.color.text_color));
                }
            }
        };
        gvQuality.setAdapter(commonAdapter);
        gvQuality.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!purityChecks[position]) {
                    clearCheck(purityChecks);
                    tvQuality.setText(list.get(position).getTitle());
                    stoneEntity.setPurityId(list.get(position).getId());
                    stoneEntity.setPurityTitle(list.get(position).getTitle());
                } else {
                    tvQuality.setText("");
                    stoneEntity.setPurityId("");
                    stoneEntity.setPurityTitle("");
                }
                purityChecks[position] = !purityChecks[position];
                commonAdapter.notifyDataSetChanged();

            }
        });
        setListViewHeightBasedOnChildren(gvQuality, 12);
    }


    private void initColor() {
        final List<ModelDetailResult.DataEntity.StoneColorEntity> colorList = stoneDetail.getStoneColorItme();
        colorChecks = new boolean[colorList.size()];
        if(stoneEntity.getColorTitle()!=null){
            for (int i = 0; i < colorList.size(); i++) {
                if (colorList.get(i).getTitle().equals(stoneEntity.getColorTitle())) {
                    colorChecks[i] = true;
                    tvColor.setText(colorList.get(i).getTitle());
                }
            }
        }

        final CommonAdapter colorAdapter = new CommonAdapter<ModelDetailResult.DataEntity.StoneColorEntity>(colorList, R.layout.item_gv_text) {
            @Override
            public void convert(int position, BaseViewHolder helper, ModelDetailResult.DataEntity.StoneColorEntity item) {
                if (colorChecks[position]) {
                    helper.setText(R.id.tv_item_text, item.getTitle(), R.drawable.corners_red_bg, getResources().getColor(R.color.white));
                } else {
                    helper.setText(R.id.tv_item_text, item.getTitle(), R.drawable.corners_white_bg, getResources().getColor(R.color.text_color));
                }

            }

        };
        gvColor.setAdapter(colorAdapter);
        gvColor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!colorChecks[position]) {
                    clearCheck(colorChecks);
                    tvColor.setText(colorList.get(position).getTitle());
                    stoneEntity.setColorId(colorList.get(position).getId());
                    stoneEntity.setColorTitle(colorList.get(position).getTitle());
                } else {
                    tvColor.setText("");
                    stoneEntity.setColorId("");
                    stoneEntity.setColorTitle("");
                }
                colorChecks[position] = !colorChecks[position];
                colorAdapter.notifyDataSetChanged();

            }
        });
        setListViewHeightBasedOnChildren(gvColor, 12);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        double weight = Double.parseDouble(etWeight.getText().toString().equals("") ? "0" : etWeight.getText().toString());
        int amount = Integer.parseInt(etAmount.getText().toString().equals("") ? "0" : etAmount.getText().toString());
        switch (v.getId()) {
            case R.id.iv_weight_add:
                ++weight;
                break;
            case R.id.iv_weight_reduce:
                if (weight > 1) {
                    --weight;
                }
                break;
            case R.id.iv_amount_add:
                ++amount;
                break;
            case R.id.iv_amount_reduce:
                if (amount > 1) {
                    --amount;
                }
                break;
        }
        DecimalFormat df = new DecimalFormat("######0.00");
        stoneEntity.setNumber(amount + "");
        stoneEntity.setSpecTitle(df.format(weight) + "");
        etWeight.setText(df.format(weight) + "");
        etAmount.setText(amount + "");
    }
}
