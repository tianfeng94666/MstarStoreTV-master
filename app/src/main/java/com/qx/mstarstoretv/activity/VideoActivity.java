package com.qx.mstarstoretv.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.utils.SpUtils;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class VideoActivity extends BaseActivity {

    @Bind(R.id.sv_ad)
    SurfaceView vv;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    private MediaPlayer mPlayer;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        verifyStoragePermissions(this);
        init();
//        init2();
    }

    private void init2() {
        String path = SpUtils.getInstace(VideoActivity.this).getString("videoPath");
        Uri uri = Uri.parse("file://" + path);
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void init() {
        idIgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        vv.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mPlayer != null) {
                    mPlayer.stop();
                    mPlayer.release();
                    mPlayer = null;
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                path = SpUtils.getInstace(VideoActivity.this).getString("videoPath");

                try {
                    if (mPlayer == null) {
                        mPlayer = MediaPlayer.create(VideoActivity.this, Uri.parse("file://" + path));
                    }
                    if(mPlayer==null){
                        showToastReal("请在个人中心中选择正确的视频");
                    }
                    mPlayer.setDisplay(holder);//将SurfaceHolder关联mediaplayer
                    mPlayer.setLooping(true);
                    mPlayer.start();

                    mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                        @Override
                        public boolean onError(MediaPlayer mp, int what,
                                               int extra) {
                            // TODO Auto-generated method stub
                            return false;
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {

            }
        });
    }

    public void onBack(View view) {
        finish();
    }

    @Override
    public void loadNetData() {

    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    /**
     * 检查应用程序是否允许写入存储设备
     * <p>
     * <p>
     * <p>
     * 如果应用程序不允许那么会提示用户授予权限
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);


        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
