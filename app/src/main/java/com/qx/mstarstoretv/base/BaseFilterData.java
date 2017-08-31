package com.qx.mstarstoretv.base;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

import com.qx.mstarstoretv.adapter.ExpandableGridAdapter;
import com.qx.mstarstoretv.bean.GropType;
import com.qx.mstarstoretv.inter.ClassifyOnSeachListener;
import com.qx.mstarstoretv.json.ClassTypeFilerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  abstract class BaseFilterData extends BaseActivity{
    public Context mContext;
    public ExpandableGridAdapter adapter;
    /*分类 */
    public List<GropType> getGroupKeyList=new ArrayList<>();
    /*一级菜单名称*/
    public List<Map<String, Object>> list;
    /*多选所选择的类容*/
    public  Map<Integer, Boolean> groupSelct = new HashMap<>();

    int numcolumn =0;

    public int getNumcolumn() {
        return numcolumn;
    }

    public void setNumcolumn(int numcolumn) {
        this.numcolumn = numcolumn;
    }
    /*判断哪个页面的操作*/
    public String myAction;
    public void initBaseFilterData(Context context,String action) {
        this.mContext = context;
        this.myAction=action;
        initData();
        initView();
        setListener();
    }

    private void initView(){
        adapter = new ExpandableGridAdapter(mContext, list, getArrayTitle(),getArrayId(),getGroupKeyList,myAction);
        adapter.setNumcolumn(numcolumn);
        getExpandableGridView().setAdapter(adapter);
        //默认全部展开
        for (int i = 0; i < list.size(); i++) {
            getExpandableGridView().expandGroup(i);
            groupSelct.put(i, true);
        }
    }

    private void initData(){
        list = new ArrayList<>();
        List<ClassTypeFilerEntity> filerList = getTypeListData();
        for (int i = 0; i <  filerList.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("txt",  filerList.get(i).getTitle());
            list.add(map);
        }
    }

    public abstract ExpandableListView getExpandableGridView();
    public abstract List<ClassTypeFilerEntity> getTypeListData();

    public String [] getString(List<String> list){
        String strings[]=new String[list.size()];
        for(int i=0,j=list.size();i<j;i++){
            strings[i]=list.get(i);
        }
        return strings;
    }

    public  String [][] getArrayTitle(){
        String strTwo[][] = new String[ getTypeListData().size()][];
        for (int i=0;i< getTypeListData().size();i++){
            List<ClassTypeFilerEntity.AttributeListEntity> attributeList = getTypeListData().get(i).getAttributeList();
            GropType gropType=new GropType();
            gropType.setName(getTypeListData().get(i).getGroupKey());
            gropType.setId(getTypeListData().get(i).getMulSelect()+"");
            gropType.setMulSelect(getTypeListData().get(i).getMulSelect()+"");
            getGroupKeyList.add(gropType);
            List list=new ArrayList();
            for (int j=0;j<attributeList.size();j++){
                list.add(attributeList.get(j).getTitle());
            }
            strTwo[i]=getString(list);
        }
        return strTwo;
    }

    public  String [][] getArrayId(){
        String strTwo[][] = new String[ getTypeListData().size()][];
        for (int i=0;i< getTypeListData().size();i++){
            List<ClassTypeFilerEntity.AttributeListEntity> attributeList=getTypeListData().get(i).getAttributeList();
            List list=new ArrayList();
            for (int j=0;j<attributeList.size();j++){
                list.add(attributeList.get(j).getValue());
            }
            strTwo[i]=getString(list);
        }
        return strTwo;
    }


    public ClassifyOnSeachListener onSeachListener;
    public  void setOnSeachListener(ClassifyOnSeachListener onSeachListener) {
        this.onSeachListener=onSeachListener;
    }

    private void setListener() {
        getExpandableGridView().setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                if (groupSelct.get(groupPosition)) {
                    getExpandableGridView().collapseGroup(groupPosition);
                    groupSelct.put(groupPosition, false);
                } else {
                    getExpandableGridView().expandGroup(groupPosition);
                    groupSelct.put(groupPosition, true);
                }
                return true;
            }
        });

    }




}
