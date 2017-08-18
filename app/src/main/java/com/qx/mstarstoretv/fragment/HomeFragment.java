package com.qx.mstarstoretv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.AddAddressActivity;
import com.qx.mstarstoretv.activity.MainActivity;
import com.qx.mstarstoretv.activity.OrderActivity;
import com.qx.mstarstoretv.activity.SettingActivity;
import com.qx.mstarstoretv.activity.StoneSearchInfoActivity;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.BaseFragment;
import com.qx.mstarstoretv.json.HomeResult;
import com.qx.mstarstoretv.net.ImageLoadOptions;
import com.qx.mstarstoretv.net.OKHttpRequestUtils;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.viewutils.CircleImageView;
import com.qx.mstarstoretv.viewutils.CustomGridView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * 创建人：Yangshao
 * 创建时间：2016/9/21 17:01
 * @version    主页
 *
 */
public class HomeFragment extends BaseFragment {

    CustomGridView mCustomGridView;
    //得到宽度
    int mCusGridViewWidth;
    @Bind(R.id.id_ig_icon)
    CircleImageView idIgIcon;
    @Bind(R.id.id_tv_name)
    TextView idTvName;
    @Bind(R.id.id_ig_setting)
    ImageView idIgSetting;
    @Bind(R.id.id_ig_stone)
    ImageView idIgStone;

    public HomeFragment() {

    }


    @Override
    public void onResume() {
        super.onResume();

        L.e("onResume");
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        baseShowWatLoading();
        loadNetData();

        // BaseApplication.loadNetData();
        // BaseApplication.loadNetData1();
        //BaseApplication.loadNetData2();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            baseShowWatLoading();
            loadNetData();
        }
    }

    private void initView(View view) {

        idIgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        idIgStone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StoneSearchInfoActivity.class);
                startActivity(intent);
            }
        });
        BaseApplication.setUserPic(homeResult.getData().getUserInfo().getHeadPic());
        ImageLoader.getInstance().displayImage(BaseApplication.getUserPic(), idIgIcon, ImageLoadOptions.getOptions());


        MainActivity.setOnInformationCountClick(new MainActivity.OnInformationCountClick() {
            @Override
            public int getCount() {
                int count = Integer.valueOf(homeResult.getData().getUserInfo().getMesCount());
                return count;
            }
        });

        idTvName.setText(homeResult.getData().getUserInfo().getUserName());
        mCustomGridView = (CustomGridView) view.findViewById(R.id.id_gv_menu);
        /*得到控件测量宽度*/
        mCustomGridView.post(new Runnable() {
            @Override
            public void run() {
                mCusGridViewWidth = mCustomGridView.getMeasuredWidth();
            }
        });
        mCustomGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(getActivity(), OrderActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), StoneSearchInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), AddAddressActivity.class);
                        startActivity(intent);
                        break;
                }

            }
        });


        BaseAdapter mGvAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return functionsList.size();
            }

            @Override
            public HomeResult.DataEntity.FunctionsListEntity getItem(int position) {
                return functionsList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_gv_layout, parent, false);
                    holder.ig = (ImageView) convertView.findViewById(R.id.itemImageView);
                    holder.tv = (TextView) convertView.findViewById(R.id.itemTextView);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                //holder.ig.setBackgroundResource(images.get(position));
                ImageLoader.getInstance().displayImage(functionsList.get(position).getPic(), holder.ig, ImageLoadOptions.getOptions());
                holder.tv.setText(functionsList.get(position).getTitle());
                return convertView;
            }
        };

        mCustomGridView.setAdapter(mGvAdapter);

    }

    HomeResult homeResult;
    List<HomeResult.DataEntity.FunctionsListEntity> functionsList;

    public void loadNetData() {
        final String homeurl = AppURL.URL_HOME + "tokenKey=" + BaseApplication.getToken();
        L.e("netLogin" + homeurl);
        VolleyRequestUtils.getInstance().getCookieRequest(getActivity(), homeurl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                baseHideWatLoading();
                L.e(result);
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    homeResult = new Gson().fromJson(result, HomeResult.class);
                    if (homeResult.getData() == null) {
                        return;
                    }
                    functionsList = homeResult.getData().getFunctionsList();
                    initView(view);
                } else if (error == 2) {
                    loginToServer(MainActivity.class);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    public class ViewHolder {
        ImageView ig;
        TextView tv;
    }

}

