package com.qx.mstarstoretv.fragment;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.AddressListActivity;
import com.qx.mstarstoretv.activity.CustomMadeActivity;
import com.qx.mstarstoretv.activity.LoginActivity;
import com.qx.mstarstoretv.activity.SettingActivity;
import com.qx.mstarstoretv.activity.UpdatePassWordActivity;
import com.qx.mstarstoretv.activity.UpdatePhoneNumber;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.BaseFragment;
import com.qx.mstarstoretv.base.Global;
import com.qx.mstarstoretv.dialog.ImageInitiDialog;
import com.qx.mstarstoretv.net.ImageLoadOptions;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.BimpUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.BitmapUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2016/9/5.
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {

    private final String TAG = "MystoreActivity";
    @Bind(R.id.content)
    LinearLayout content;
    Context context;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.id_tv_exit)
    TextView tvExit;

    @Bind(R.id.splitbutton)
    ImageView splitbutton;
    @Bind(R.id.id_ig_userpic)
    ImageView idIgUserpic;
    @Bind(R.id.id_lay_root)
    LinearLayout idLayRoot;
    @Bind(R.id.tv_username)
    TextView mTvUsername;
    @Bind(R.id.id_update_icon)
    RelativeLayout update_icon;
    @Bind(R.id.iv_is_show_price)
    ToggleButton ivIsShowPrice;
    @Bind(R.id.rl_clear_memery)
    RelativeLayout rlClearMemery;
    @Bind(R.id.tv_share)
    TextView tvShare;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.iv_below_menu_ic)
    ImageView ivBelowMenuIc;
    @Bind(R.id.tv_menu_title)
    TextView tvMenuTitle;
    @Bind(R.id.iv_btn_expand_pressed)
    ImageView ivBtnExpandPressed;
    @Bind(R.id.ringagain)
    TextView ringagain;
    @Bind(R.id.tv_is_show_price)
    TextView tvIsShowPrice;
    @Bind(R.id.tv_is_clear)
    TextView tvIsClear;
    @Bind(R.id.rl_shared)
    RelativeLayout rlShared;
    @Bind(R.id.tv_is_customized)
    TextView tvIsCustomized;
    @Bind(R.id.bt_customized)
    ImageView btCustomized;

    private LayoutInflater inflater;
    private String[] titles;
    private String temp_img_dir;
    private Uri mImageCaptureUri;
    private boolean isShowPrice;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.activity_modifydata, null);
        ButterKnife.bind(this, view);
        loadNetData();
        initViews();
        titles = new String[]{getString(R.string.updatepwd), getString(R.string.update_phone), getString(R.string.adress_manager)};
        context = getActivity();
        return view;
    }


    private Boolean isCustomized = SpUtils.getInstace(getActivity()).getBoolean("isCustomized", true);

    private void initViews() {
        if (isCustomized) {
            btCustomized.setImageResource(R.drawable.icon_switch_off);
        } else {
            btCustomized.setImageResource(R.drawable.icon_switch_on);
        }
        btCustomized.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCustomized = !isCustomized;
                if (isCustomized) {
                    btCustomized.setImageResource(R.drawable.icon_switch_off);
                } else {
                    btCustomized.setImageResource(R.drawable.icon_switch_on);
                }
                Global.STONE_POINT_CHANGE = 1;
                SpUtils.getInstace(getActivity()).saveBoolean("isCustomized", isCustomized);
            }
        });
        ivIsShowPrice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SpUtils.getInstace(getActivity()).saveBoolean("isShowPrice", ivIsShowPrice.isChecked());
                Global.STONE_POINT_CHANGE = 1;
            }
        });

        rlClearMemery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoader.getInstance().clearDiscCache();
                ImageLoader.getInstance().clearMemoryCache();
                ToastManager.showToastReal("已经清空！");
            }
        });
    }

    private void commitIsShowPrice(int i) {
        String url = AppURL.URL_IS_SHOW_PRICE + "tokenKey=" + BaseApplication.getToken() + "&value=" + i;
        L.e("获取个人信息" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(getActivity(), url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {

                L.e("loadNetData  " + result);
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    ToastManager.showToastReal("修改成功");
                } else if (error.equals("2")) {
                    loginToServer(CustomMadeActivity.class);
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

    String userName, phone, headPic, address;


    public void loadNetData() {
        String url = AppURL.URL_USER_MODIFY + "tokenKey=" + BaseApplication.getToken();
        baseShowWatLoading();
        L.e("获取个人信息" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(getActivity(), url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                baseHideWatLoading();
                L.e("loadNetData  " + result);
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    try {
                        if (jsonResult.get("data") != null) {
                            JsonObject jsData = jsonResult.get("data").getAsJsonObject();
                            userName = jsData.get("userName").getAsString();
                            phone = jsData.get("phone").getAsString();
                            headPic = jsData.get("headPic").getAsString();
                            address = jsData.get("address").getAsString();
                            isShowPrice = SpUtils.getInstace(getActivity()).getBoolean("isShowPrice", true);
                            if (isShowPrice) {
                                ivIsShowPrice.setChecked(true);
                            } else {
                                ivIsShowPrice.setChecked(false);
                            }
                            L.e("userName:" + userName + "phone" + phone + "address:" + address);
                            initContent(userName, headPic, phone, address);
                        }

                    } catch (Exception e) {
                        initContent(userName, headPic, phone, address);
                    }

                    initListener();
                } else if (error.equals("2")) {
                    loginToServer(CustomMadeActivity.class);
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);
                }
            }

            @Override
            public void onFail(String fail) {
                baseHideWatLoading();
            }
        });


    }


    private Boolean isFlag = false;

    private void initListener() {
        splitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFlag) {
                    splitbutton.setImageResource(R.drawable.icon_switch_off);
                } else {
                    splitbutton.setImageResource(R.drawable.icon_switch_on);
                }
                isFlag = !isFlag;
            }
        });
    }


    private static int LIGHT_MARGIN = UIUtils.convertPxtoDip(1);

    private void initContent(String userName, String pic, String phone, String adress) {
        titleText.setText("个人中心");
        mTvUsername.setText("用户名：" + userName);
        if (Build.VERSION.SDK_INT <= 19) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.setMargins(0, 0, 0, 0);
            ivIsShowPrice.setLayoutParams(params);
        }
        if (isShowPrice) {
            ivIsShowPrice.setChecked(true);
        } else {
            ivIsShowPrice.setChecked(false);
        }

        temp_img_dir = Environment.getExternalStorageDirectory() + File.separator + "tempImage.jpg";
        ImageLoader.getInstance().displayImage(pic, idIgUserpic, ImageLoadOptions.getOptions());
        content.removeAllViews();
        for (int i = 0; i < titles.length; i++) {
            getItmeView();
            viewHoder.tv_title.setText(titles[i]);
            viewHoder.inf_rel.setTag(titles[i]);
            if (titles[i].equals(getString(R.string.update_phone))) {
                viewHoder.tv_openning_hint.setText(phone);
                viewHoder.tv_openning_hint.setVisibility(View.VISIBLE);
            }
            if (titles[i].equals(getString(R.string.adress_manager))) {
                if (!StringUtils.isEmpty(adress)) {
                    viewHoder.tv_openning_hint.setLines(2);
                    viewHoder.tv_openning_hint.setText(adress);
                    viewHoder.tv_openning_hint.setVisibility(View.VISIBLE);
                }
            }

            viewHoder.inf_rel.setOnClickListener(clickListener);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
            if (i != 0) {
                lp.topMargin = LIGHT_MARGIN;
            }
            content.addView(viewHoder.inf_rel, lp);
        }
        tvShare.setOnClickListener(this);
        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseApplication.spUtils.saveString(SpUtils.key_tokenKey, "");
                openActivity(LoginActivity.class, null);
            }
        });

        update_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCameraPermission();
                ImageInitiDialog imageInitiDialog = new ImageInitiDialog(getActivity());
                imageInitiDialog.showDialog(idLayRoot);
                imageInitiDialog.setOnImageSelectListener(new ImageInitiDialog.OnImageSelectListener() {
                    @Override
                    public void onCamera() {
                        /*拍    照*/
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        /*给拍的照片随机取名*/
                        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "tmp_avatar_"
                                + String.valueOf(System.currentTimeMillis())
                                + ".jpg"));
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                        intent.putExtra("return-data", true);
                        startActivityForResult(intent, PICK_FROM_CAMERA);
                    }

                    @Override
                    public void onPhotos() {
                        /*相册中选取*/
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, PICK_FROM_PHOTO);
                    }
                });
            }
        });
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getActivity());
    }

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_PHOTO = 2;
    private static final int CROP_PHOTO = 3;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {
            case PICK_FROM_CAMERA:
                doCrop();
                break;
            case PICK_FROM_PHOTO:
                mImageCaptureUri = data.getData();
                doCrop();
                break;
            case CROP_PHOTO:
                Bundle extras = data.getExtras();
                Bitmap photo = null;
                if (extras != null) {
                    photo = extras.getParcelable("data");
                }
                File f = new File(getRealPathFromURI(mImageCaptureUri));
                if (f.exists() && photo != null) {
                      /*图片路径压缩*/
                    final File file = new File(BimpUtils.getInstace().savebitmap(photo));
                    submitToServer(file);
                } else {
                    Toast.makeText(getActivity(), "the file doesnt exist", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    private void doCrop() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        List<ResolveInfo> list = getActivity().getPackageManager().queryIntentActivities(intent, 0);
        int size = list.size();
        if (size == 0) {
            Toast.makeText(getActivity(), "Can not find image crop app",
                    Toast.LENGTH_SHORT).show();
            return;
        } else {
            intent.setData(mImageCaptureUri);
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);
            if (size == 1) {
                Intent i = new Intent(intent);
                ResolveInfo res = list.get(0);
                i.setComponent(new ComponentName(res.activityInfo.packageName,
                        res.activityInfo.name));
                startActivityForResult(i, CROP_PHOTO);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_share:
                showShare();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    public class CropOptionAdapter extends ArrayAdapter<SettingActivity.CropOption> {
        private ArrayList<SettingActivity.CropOption> mOptions;
        private LayoutInflater mInflater;

        public CropOptionAdapter(Context context, ArrayList<SettingActivity.CropOption> options) {
            super(context, R.layout.crop_selector, options);
            mOptions = options;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup group) {
            if (convertView == null)
                convertView = mInflater.inflate(R.layout.crop_selector, null);
            SettingActivity.CropOption item = mOptions.get(position);
            if (item != null) {
                ((ImageView) convertView.findViewById(R.id.iv_icon))
                        .setImageDrawable(item.icon);
                ((TextView) convertView.findViewById(R.id.tv_name))
                        .setText(item.title);

                return convertView;
            }

            return null;
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public class CropOption {
        public CharSequence title;
        public Drawable icon;
        public Intent appIntent;
    }


    private void submitToServer(final File file) {
        String url = AppURL.URL_UPLOAD_PiC + "&tokenKey=" + BaseApplication.getToken();
        HttpUtils http = new HttpUtils();
        HttpRequest.HttpMethod method = HttpRequest.HttpMethod.POST;
        RequestParams params = new RequestParams();
        params.addBodyParameter("attachment", file, "multipart/form-data");
        L.e("上传URL" + url);
        http.send(method, url, params, new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                Log.i("wcl", "current process -->" + current + "/" + total);
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                L.e("submitToServer" + result);
                Gson gson = new Gson();
                String error = gson.fromJson(result, JsonObject.class).get("error").getAsString();
                if (error.equals("0")) {
                    String j = gson.fromJson(result, JsonObject.class).get("data").getAsJsonObject().get("headPic").getAsString();
                    L.e("submitToServer" + "成功");
                    // refreshImg(j);
                    BaseApplication.setUserPic(j);
                    ImageLoader.getInstance().displayImage(BaseApplication.getUserPic(), idIgUserpic, ImageLoadOptions.getOptions());
                } else {
                    ToastManager.showToastReal("上传失败 重新上传");
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
            }
        });
    }


    private void refreshImg(final String path) {
        final String photo = BitmapUtils.getInstace().getCompressImagePaht(path);
        final Bitmap bmp = BitmapUtils.getInstace().compressBitmapFromSrc(photo, 100);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                idIgUserpic.setImageBitmap(bmp);
            }
        });

    }


    public void getItmeView() {
        viewHoder = new ViewHoder();
        if (inflater == null)
            inflater = getActivity().getLayoutInflater();
        View inf_rel = inflater.inflate(R.layout.item_menu, null);
        viewHoder.tv_title = (TextView) inf_rel.findViewById(R.id.tv_menu_title);
        viewHoder.tv_openning_hint = (TextView) inf_rel.findViewById(R.id.tv_expand_hint);
        viewHoder.iv_menu_ic = (ImageView) inf_rel.findViewById(R.id.iv_below_menu_ic);
        viewHoder.inf_rel = inf_rel;
    }

    private ViewHoder viewHoder;

    public class ViewHoder {
        View inf_rel;
        TextView tv_title;
        TextView tv_openning_hint;
        ImageView iv_menu_ic;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tag = v.getTag().toString();
            Intent intent = new Intent();
            switch (tag) {
                case "修改密码":
                    intent.setClass(getActivity(), UpdatePassWordActivity.class);
                    break;
                case "修改手机号码":
                    intent.setClass(getActivity(), UpdatePhoneNumber.class);
                    break;
                case "管理用户地址":
                    intent.setClass(getActivity(), AddressListActivity.class);
                    break;
            }
            startActivity(intent);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected void initView() {
        titleText.setText("修改资料");
    }


    public void onBack(View view) {

    }

    private static String[] PERMISSIONS_CAMERA_AND_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

    public void setCameraPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int storagePermission = getActivity().checkSelfPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
            if (storagePermission != PackageManager.PERMISSION_GRANTED || cameraPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), PERMISSIONS_CAMERA_AND_STORAGE,
                        0x007);
            }
        }
    }
}
