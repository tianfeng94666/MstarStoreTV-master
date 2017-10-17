package com.qx.mstarstoretv.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.utils.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class GoldSettingActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.et_full_gold)
    EditText etFullGold;
    @Bind(R.id.et_gold)
    EditText etGold;
    @Bind(R.id.et_gold_pt)
    EditText etGoldPt;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gold_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGold();
            }
        });
    }

    private void setGold() {
        String fullGold = etFullGold.getText().toString();
        String gold = etGold.getText().toString();
        String goldPt = etGoldPt.getText().toString();
        if(StringUtils.isEmpty(fullGold)){
            showToastReal("足金不能为空");
            return;
        }
        if(StringUtils.isEmpty(gold)){
            showToastReal("金条不能为空");
            return;
        }
        if(StringUtils.isEmpty(goldPt)){
            showToastReal("Pt950不能为空");
            return;
        }
        SpUtils.getInstace(this).saveString("fullGold",fullGold);
        SpUtils.getInstace(this).saveString("gold",gold);
        SpUtils.getInstace(this).saveString("goldPt",goldPt);
        finish();
    }

    @Override
    public void loadNetData() {

    }

    public void onBack(View view) {
        finish();
    }
}
