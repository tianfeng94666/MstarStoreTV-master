package com.qx.mstarstoretv.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/12.
 */
public class UpdatePhoneNumber extends BaseActivity {


    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_phone);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void loadNetData() {

    }

    public void onBack(View view){
        finish();
    }
    protected void initView() {
        titleText.setText("修改手机号码");
    }

}
