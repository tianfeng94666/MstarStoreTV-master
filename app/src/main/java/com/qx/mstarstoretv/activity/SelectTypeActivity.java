package com.qx.mstarstoretv.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.viewutils.CustomAdapter;
import com.qx.mstarstoretv.viewutils.CustomListView;
import com.qx.mstarstoretv.viewutils.CustomRadioGroup;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectTypeActivity extends BaseActivity {


    private CustomListView customListView;
    CustomTypeListViewAdapter customListViewAdapter;
    LinearLayout normsLayout;
    List<String> typese=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typese.add("推荐款");
        typese.add("畅销款");
        typese.add("最新款");
        typese.add("全部");
        typese.add("推荐款");
        typese.add("畅销款");
        typese.add("最新款");
        typese.add("全部");



        types.add("我  们");
        types.add("我  们");
        types.add("我  们");
        types.add("我  们");
        types.add("我  们");
        types.add("推 荐 款");
        types.add("畅 销 款");
        types.add("最 新 款");
        types.add("全 部");

        listData=getData();

        setContentView(R.layout.activity_select_type);
        normsLayout = (LinearLayout) findViewById(R.id.norms_layout);

        customListView= (CustomListView) findViewById(R.id.sexangleView);
        customListViewAdapter = new CustomTypeListViewAdapter();
        customListView.setDividerHeight(10);
        customListView.setDividerWidth(10);
        customListView.setAdapter(customListViewAdapter);
       // customListView.setAdapter(customListViewAdapter);
        LoadingGoodsLayout();

    }

    @Override
    public void loadNetData() {

    }


    int size=15;
    private RadioGroup[] radioGroups;// 存储
    private int[] checkedIds;
    boolean isflag = false;
    LayoutInflater inflater;
    private int InitId = 0x1000;// 初始id
    @SuppressLint("NewApi")
    private void LoadingGoodsLayout() {
        size=typese.size();
        radioGroups = new RadioGroup[size];
        if (checkedIds == null) {
            isflag = true;
            checkedIds = new int[size];
        }
        for (int i = 0; i < size; i++) {
            if (inflater == null)
                inflater = LayoutInflater.from(this);
            View inf_rel = inflater.inflate(R.layout.select_types, null);
            LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ViewGroup.LayoutParams radiolp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            CustomRadioGroup normsGroup = (CustomRadioGroup) inf_rel.findViewById(R.id.goods_norms_group);
            normsGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener(i));
            for (int j = 0; j < size; j++) {
                RadioButton view = new RadioButton(this);
                if (j == 0) {
                    view.setTag(i);
                }
                view.setId(++InitId);
                view.setText(typese.get(j)+"");
                view.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
                view.setGravity(Gravity.CENTER);
                view.setPadding(20, 20, 20, 20);
                view.setButtonDrawable(R.drawable.radio_style);  //设置样式
                //view.setBackgroundResource(R.drawable.radio_style1.xml);  //设置背景
                view.setLayoutParams(radiolp);
                normsGroup.addView(view);
                if (view.getId() == checkedIds[i]) {
                    L.e("radioButton0:" + view.getId());
                    view.setChecked(true);
                }
            }
            radioGroups[i] = normsGroup;
            normsLayout.addView(inf_rel, llparams);
            if (!isflag){
                initRadioButton(i, checkedIds[i]);
            }

        }
        if (radioGroups != null && radioGroups.length > 0 && isflag) {
            for (int i = 0; i < radioGroups.length; i++) {
                RadioGroup rg = radioGroups[i];
                RadioButton rb = (RadioButton) rg.findViewWithTag(i);
                if (rb != null) {
                    rb.setChecked(true);
                    initRadioButton(i, rb.getId());
                }
            }
        }
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initRadioButton(int position, int viewId) {
        Class clazz = RadioGroup.class;
        MyOnCheckedChangeListener listener = null;
        try {
            Field f = clazz.getDeclaredField("mOnCheckedChangeListener");
            f.setAccessible(true);
            listener = (MyOnCheckedChangeListener) f.get(radioGroups[position]);
            listener.onCheckedChanged(radioGroups[position], viewId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
        }
    }

    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        private int i;// 分辨哪个RadioGroup
        public MyOnCheckedChangeListener(int i) {
            this.i = i;
        }
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
            String selected = radioButton.getText().toString();
            L.e("radioButton:" +selected);
        }
    }
    /**
     * 数据封装
     * @return
     */
    private List<HashMap<String, Object>> getData()
    {
        List<HashMap<String, Object>> list = new ArrayList<>();
        for(int i=0;i<types.size();i++)
        {
            HashMap<String, Object> map = new HashMap<>();
            map.put("radioid", i);
            list.add(map);
        }
        return list;
    }

    private List<String> types=new ArrayList<>();
    private List<HashMap<String, Object>> listData;
    Map<Integer, Boolean> isCheckMap =  new HashMap<Integer, Boolean>();
    public class CustomTypeListViewAdapter extends CustomAdapter {

        public CustomTypeListViewAdapter(){

        }
        @Override
        public int getCount() {
            return types.size();
        }

        @Override
        public Object getItem(int position) {
            return types.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(SelectTypeActivity.this).inflate(R.layout.adapter_cb_layout, parent, false);
                viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.id_cb_type);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.checkBox.setText(types.get(position) + "");
            viewHolder.checkBox.setTag(listData.get(position).get("radioid").toString());
            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int radiaoId = Integer.parseInt(buttonView.getTag().toString());
                    if(isChecked)
                    {
                        //将选中的放入hashmap中
                        isCheckMap.put(radiaoId, isChecked);
                        L.e(types.get(radiaoId)+"被选中");
                    }
                    else
                    {
                        //取消选中的则剔除
                        isCheckMap.remove(radiaoId);
                    }
                }
            });
            return  convertView;
        }


        public class ViewHolder{
            CheckBox checkBox;
        }
    }


}
