package com.qx.mstarstoretv.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.BaseFilterData;
import com.qx.mstarstoretv.base.MyAction;
import com.qx.mstarstoretv.inter.ClassifyOnSeachListener;
import com.qx.mstarstoretv.json.ClassTypeFilerEntity;
import com.qx.mstarstoretv.json.ClasssifyResult;
import com.qx.mstarstoretv.json.SearchValue;
import com.qx.mstarstoretv.json.TypeFiler;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.ToastManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ClassifyActivity extends BaseFilterData{

    ExpandableListView expandableGridView;
    List<ClassTypeFilerEntity> mTypeListData = new ArrayList<>();
    List<ClasssifyResult.DataEntity.TypeListEntity> typeList = new ArrayList<>();
    TextView idTvConfirfilterr;
    TextView idTvResetfilter;
    @Bind(R.id.title_text)
    TextView titleText;
    //左侧产品目录
    private ListView menuListView;
    CatalogueAdapter catalogueAdapter;

    /*搜索过的多选历史记录*/
    public static List<TypeFiler> hisCategoryFilterList;
    /*搜索过的单选历史记录*/
    public static List<SearchValue> singleKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_classify);
        ButterKnife.bind(this);
        initParameter();
        loadNetData();
        initListener();

    }


    private void initParameter() {
        expandableGridView = (ExpandableListView) findViewById(R.id.list);
        idTvConfirfilterr = (TextView) findViewById(R.id.id_tv_confirfilterr);
        idTvResetfilter = (TextView) findViewById(R.id.id_tv_resetfilter);
        menuListView = (ListView) findViewById(R.id.classify_list);
        catalogueAdapter = new CatalogueAdapter(this);
        menuListView.setAdapter(catalogueAdapter);
        hisCategoryFilterList = new ArrayList<>();
        for (int i = 0; i < singleKey.size(); i++) {
            singleKey.get(i).setValue("");
        }
        titleText.setText(R.string.classify_select);
    }


    private void initListener() {
        this.setNumcolumn(7);
                /*item选择重置*/
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ClasssifyResult.DataEntity.TypeListEntity typeListEntity = typeList.get(position);
                catalogueAdapter.setSelectedPosition(position);
                catalogueAdapter.notifyDataSetChanged();
                hisCategoryFilterList.clear();
                for (int i = 0; i < singleKey.size(); i++) {
                    singleKey.get(i).setValue("");
                }
                TypeFiler categoryFiler = new TypeFiler();
                categoryFiler.setId(typeListEntity.getValue());
                categoryFiler.setGroupKey(typeListEntity.getGroupKey());
                categoryFiler.setValue(typeListEntity.getValue());
                hisCategoryFilterList.add(categoryFiler);
                adapter.isResetGridTextAdapter();
            }
        });


        /*重置按钮*/
        idTvResetfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hisCategoryFilterList.clear();
                for (int i = 0; i < singleKey.size(); i++) {
                    singleKey.get(i).setValue("");
                }
                adapter.isResetGridTextAdapter();
            }
        });
        /*确认按钮*/
        idTvConfirfilterr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClassifySeachListener != null) {
                    onClassifySeachListener.onSeach(MyAction.classifyActivityAction);
                    finish();
                }

            }
        });
    }

    public static ClassifyOnSeachListener onClassifySeachListener;

    public static void setOnClassifySeachListener(ClassifyOnSeachListener onClassifySeachListener) {
        ClassifyActivity.onClassifySeachListener = onClassifySeachListener;
    }

    private String getLoadNetURL() {
        String url = null;
        url = AppURL.URL_MODEL_FILTER + "tokenKey=" + BaseApplication.getToken();
        return url;
    }

    @Override
    public  void loadNetData() {
        L.e("getLoadNetURL" + getLoadNetURL());
        VolleyRequestUtils.getInstance().getCookieRequest(this, getLoadNetURL(), new VolleyRequestUtils.HttpStringRequsetCallBack(){
            @Override
            public void onSuccess(String result) {
                try {
                    L.e("解析" + result);
                    ClasssifyResult classsifyResult = new Gson().fromJson(result, ClasssifyResult.class);
                    if (classsifyResult.getError() == 0&&classsifyResult.getData()!=null) {
                        ClasssifyResult.DataEntity dataEntity = classsifyResult.getData();
                        mTypeListData = dataEntity.getTypeFiler();//分类数据
                        typeList = dataEntity.getTypeList();//分类列表
                        L.e("解析成功");
                        initBaseFilterData(ClassifyActivity.this, MyAction.classifyActivityAction);
                          /*默认设置第一个*/
                        TypeFiler categoryFiler = new TypeFiler();
                        categoryFiler.setId(typeList.get(0).getValue());
                        categoryFiler.setGroupKey(typeList.get(0).getGroupKey());
                        categoryFiler.setValue(typeList.get(0).getValue());
                        hisCategoryFilterList.add(categoryFiler);
                        catalogueAdapter.notifyDataSetChanged();
                    } else if (classsifyResult.getError() == 2) {
                        loginToServer(ClassifyActivity.class);
                    } else{
                        String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                        L.e(message);
                        ToastManager.showToastReal(message);
                    }
                } catch (Exception e) {
                    baseHideWatLoading();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String fail) {
            }
        });

    }


    @Override
    public ExpandableListView getExpandableGridView() {
        return expandableGridView;
    }
    @Override
    public List<ClassTypeFilerEntity> getTypeListData() {
        return mTypeListData;
    }


    class CatalogueAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private int selectedPosition;
        public CatalogueAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            //默认选中位置为0
            selectedPosition = 0;
        }

        @Override
        public int getCount() {
            return typeList.size();
        }

        @Override
        public Object getItem(int position) {
            return typeList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_classify_catalogue, null);
                viewHolder.tv_menu = (TextView) convertView.findViewById(R.id.tv_catalogue);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv_menu.setText(typeList.get(position).getTitle());
            if (selectedPosition == position) {
                convertView.setBackgroundResource(R.color.theme_bg);
            } else {
                convertView.setBackgroundResource(R.color.white);
            }
            return convertView;
        }

        public class ViewHolder {
            TextView tv_menu;
        }

        public void setSelectedPosition(int position) {
            selectedPosition = position;
        }
    }


    public void onBack(View view) {
        finish();
    }
}
