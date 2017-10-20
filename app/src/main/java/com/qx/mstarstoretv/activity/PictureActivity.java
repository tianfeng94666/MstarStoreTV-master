package com.qx.mstarstoretv.activity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.viewutils.FlyBanner;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class PictureActivity extends BaseActivity {
    @Bind(R.id.flybanner)
    FlyBanner flybanner;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    private String picPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);
        init();
        getDate();
    }

    private void init() {
        idIgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDate() {
        picPath = SpUtils.getInstace(this).getString("picPath");
        File file = new File(picPath);
        String parentPath =file.getParent();
        if(parentPath==null){
            showToastReal("请在个人中心选择正确的图片路径");
            return;
        }
        File parentFile = new File(parentPath);
        File[] files = parentFile.listFiles();
        ArrayList<String> list = new ArrayList<>();
        if(files==null){
            showToastReal("请在个人中心选择正确的图片路径");
            return;
        }
        for(int i =0;i<files.length;i++){
            File item = files[i];
            list.add("file://"+item.getAbsolutePath());
        }
        flybanner.setImagesUrl(list);

    }

    @Override
    public void loadNetData() {

    }
}
