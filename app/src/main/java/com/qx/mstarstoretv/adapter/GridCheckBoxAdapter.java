package com.qx.mstarstoretv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.ClassifyActivity;
import com.qx.mstarstoretv.activity.OrderActivity;
import com.qx.mstarstoretv.base.MyAction;
import com.qx.mstarstoretv.json.TypeFiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 城市适配器
 *
 */
public class GridCheckBoxAdapter extends BaseAdapter {

    private List<TypeFiler> mdata;
    private Context mContext;
    String myAction;
    String getGroupKey;   //标签  category
    public  List<TypeFiler> checkData;
    public GridCheckBoxAdapter(Context context, List<TypeFiler> mdata,String getGroupKey,String action) {
        this.mContext = context;
        this.mdata = mdata;
        this.myAction=action;
        this.getGroupKey=getGroupKey;
        listData = getData();
    }



    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHodler viewHodler = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_gridview_txt, null);
			viewHodler = new ViewHodler();
			viewHodler.checkBox = (CheckBox) convertView
					.findViewById(R.id.tv_item_goods_type);
			convertView.setTag(viewHodler);
		} else {
			viewHodler = (ViewHodler) convertView.getTag();
		}
        final ViewHodler finalViewHodler = viewHodler;
        viewHodler.checkBox.setText(mdata.get(position).getName());
        viewHodler.checkBox.setTag(listData.get(position).get("radioid").toString());
        viewHodler.categoryFiler=mdata.get(position);
        viewHodler.categoryFiler.setGroupKey(getGroupKey);
        viewHodler.categoryFiler.setId(mdata.get(position).getValue());
        viewHodler.categoryFiler.setName(mdata.get(position).getName());

        /*分类页面*/
        if (myAction.equals(MyAction.classifyActivityAction)){
            checkData= ClassifyActivity.hisCategoryFilterList;
        }else {
            /*订单页*/
            checkData=OrderActivity.multiselectKey;
            if (OrderActivity.multiselectKey.size()!=0){
                for (int i=0;i< checkData.size();i++){
                    if ( viewHodler.categoryFiler.getValue().equals(checkData.get(i).getValue())){
                        viewHodler.checkBox.setChecked(true);
                        viewHodler.categoryFiler.setCheck(true);
                    }
                }
            }
        }

        viewHodler.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                finalViewHodler.categoryFiler.setCheck(isChecked);
                if(isChecked)
                {
                   // L.e("选择"+finalViewHodler.categoryFiler.toString());
                    checkData.add(finalViewHodler.categoryFiler);

                }
                else
                {
                    for (int i=0;i<checkData.size();i++){
                        if(checkData.get(i).getValue().equals(finalViewHodler.categoryFiler.getValue())){
                            checkData.remove(i);
                        }
                    }

                }

                updateUrl();
            }
        });

//
//        if (OrderActivity.multiselectKey!=null&&OrderActivity.multiselectKey.size()!=0){
//            for (int i=0;i< OrderActivity.multiselectKey.size();i++){
//                if ( viewHodler.categoryFiler.getValue().equals(OrderActivity.multiselectKey.get(i).getValue())){
//                    viewHodler.checkBox.setChecked(true);
//                    viewHodler.categoryFiler.setCheck(true);
//                }
//            }
//        }
//
//        viewHodler.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                finalViewHodler.categoryFiler.setCheck(isChecked);
//                if(isChecked)
//                {
//                    OrderActivity.multiselectKey.add(finalViewHodler.categoryFiler);
//
//                }
//                else
//                {
//                    for (int i=0;i<OrderActivity.multiselectKey.size();i++){
//                        if(OrderActivity.multiselectKey.get(i).getValue().equals(finalViewHodler.categoryFiler.getValue())){
//                            OrderActivity.multiselectKey.remove(i);
//                        }
//                    }
//
//                }
//
//                updateUrl();
//            }
//        });

        return convertView;
    }

    private void updateUrl() {
      //  L.e("updateUrl  此次条数"+OrderActivity.multiselectKey.size());
        ExpandableGridAdapter.allSelctMap.put(getGroupKey, checkData);
    }


    private class ViewHodler {
        private CheckBox checkBox;
        private  TypeFiler categoryFiler;

    }

    /**
     * 数据封装
     *
     * @return
     */
    private List<HashMap<String, Object>> getData() {
        List<HashMap<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < mdata.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("radioid", i);
            list.add(map);
        }
        return list;
    }

    private List<HashMap<String, Object>> listData;
}
