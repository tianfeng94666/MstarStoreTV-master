package com.qx.mstarstoretv.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.ClassifyActivity;
import com.qx.mstarstoretv.activity.OrderActivity;
import com.qx.mstarstoretv.bean.GropType;
import com.qx.mstarstoretv.json.SearchValue;
import com.qx.mstarstoretv.json.TypeFiler;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandableGridAdapter extends BaseExpandableListAdapter implements
        AdapterView.OnItemClickListener {
    private String[][] child_text_array;
    private String[][] child_text_value;
    private Context context;
    private List<Map<String, Object>> list;
    List<TypeFiler> mData;
    List<GropType> mgetGroupKey;
    public String myAction;
    int numcolumn =0;

    public int getNumcolumn() {
        return numcolumn;
    }

    public void setNumcolumn(int numcolumn) {
        this.numcolumn = numcolumn;
    }

    public ExpandableGridAdapter(Context context,
                                 List<Map<String, Object>> list, String[][] child_text_array, String[][] child_text_value, List<GropType> getGroupKey, String action) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.list = list;
        this.child_text_array = child_text_array;
        this.child_text_value = child_text_value;
        this.mgetGroupKey = getGroupKey;
        this.myAction = action;
    }


    /**
     * 获取一级标签总数
     */
    @Override
    public int getGroupCount() {
        return list.size();
    }

    /**
     * 获取一级标签下二级标签的总数
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        // 这里返回1是为了让ExpandableListView只显示一个ChildView，否则在展开
        // ExpandableListView时会显示和ChildCount数量相同的GridView
        return 1;
    }

    /**
     * 获取一级标签内容
     */
    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition).get("txt");
    }

    /**
     * 获取一级标签下二级标签的内容
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child_text_array[groupPosition][childPosition];
    }

    /**
     * 获取一级标签的ID
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 获取二级标签的ID
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 指定位置相应的组视图
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 对一级标签进行设置
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        convertView = LinearLayout.inflate(context,
                R.layout.item_gridview_group_layout, null);

        TextView group_title = (TextView) convertView
                .findViewById(R.id.group_title);
        ImageView group_image = (ImageView) convertView
                .findViewById(R.id.group_image);
        if (isExpanded) {
//            group_title.setCompoundDrawablesWithIntrinsicBounds(0, 0,
//                    R.drawable.icon_filter_right, 0);
            group_image.setImageResource( R.drawable.icon_filter_right);
        } else {
//            group_title.setCompoundDrawablesWithIntrinsicBounds(0, 0,
//                    R.drawable.icon_fliter_down, 0);
            group_image.setImageResource( R.drawable.icon_fliter_down);
        }
        group_title.setText(list.get(groupPosition).get("txt").toString());
        return convertView;
    }

    @Override
    public int getChildTypeCount() {
        return 4;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        if (mgetGroupKey.get(groupPosition).getId().equals("1")) {
            return TYPE_CHECKBOX;
        } else {
            return TYPE_RADIO;
        }

    }

    private LayoutInflater mInflater;
    private final int TYPE_RADIO = 0;
    private final int TYPE_CHECKBOX = 1;



    List<GridCheckBoxAdapter> checkBoxAdapters=new ArrayList<>();
    List<GridRadioAdapter> gridRadioAdapters=new ArrayList<>();
    public  static Map<String,List<TypeFiler>> allSelctMap=new HashMap<>();


    /*搜索过的多选历史记录*/
//

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, final ViewGroup parent) {
        ViewHodler viewHodler = null;
        if (null == convertView) {
            viewHodler = new ViewHodler();
            convertView = mInflater.inflate(R.layout.item_grid_child_layout, null);
            viewHodler.linearLayout = (LinearLayout) convertView
                    .findViewById(R.id.id_lay_custon);
            viewHodler.gridView = (MyGridView) convertView
                    .findViewById(R.id.gridview);
            viewHodler.editTextHig = (EditText) convertView.findViewById(R.id.id_ed_hig);
            viewHodler.editTextLow = (EditText) convertView.findViewById(R.id.id_ed_low);
            if(UIUtils.isPad(context)){
                viewHodler.gridView.setNumColumns(5);
            }else {
                viewHodler.gridView.setNumColumns(3);
            }
            if(numcolumn!=0){
                viewHodler.gridView.setNumColumns(numcolumn);
            }
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }

        int itemViewType = getChildType(groupPosition, childPosition);
        int size = child_text_array[groupPosition].length;
        mData = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            mData.add(new TypeFiler(child_text_array[groupPosition][i], child_text_value[groupPosition][i], false));
            // 选中当前点击的二级目录
        }
        switch (itemViewType) {
            case TYPE_CHECKBOX:
                GridCheckBoxAdapter gridTextAdapter= new GridCheckBoxAdapter(context, mData, mgetGroupKey.get(groupPosition).getName(), myAction);
                viewHodler.gridView.setAdapter(gridTextAdapter);
                break;

            case TYPE_RADIO:
                viewHodler.linearLayout.setVisibility(View.VISIBLE);
                final GridRadioAdapter priceAdapter = new GridRadioAdapter(context, mData, mgetGroupKey.get(groupPosition).getName(), myAction);
                gridRadioAdapters.add(priceAdapter);
                final ViewHodler finalViewHodler = viewHodler;
                priceAdapter.setOnCleanEditText(new GridRadioAdapter.onCleanEditText() {
                    @Override
                    public void onClean(String tag) {
                        finalViewHodler.editTextLow.setText("");
                        finalViewHodler.editTextHig.setText("");
                        for(int i = 0; i< OrderActivity.singleKey.size(); i++){
                            SearchValue searchValue = OrderActivity.singleKey.get(i);
                            if(searchValue.getName().equals(tag)){
                                searchValue.setHig("");
                                searchValue.setLow("");
                            }
                        }

                        for(int i = 0; i< ClassifyActivity.singleKey.size(); i++){
                            SearchValue searchValue = ClassifyActivity.singleKey.get(i);
                            if(searchValue.getName().equals(tag)){
                                searchValue.setHig("");
                                searchValue.setLow("");
                            }
                        }
                    }
                });
                viewHodler.editTextHig.addTextChangedListener(new EditTextChange(viewHodler.editTextHig,mgetGroupKey.get(groupPosition).getName(),priceAdapter));
                viewHodler.editTextLow.addTextChangedListener(new EditTextChange(viewHodler.editTextLow,mgetGroupKey.get(groupPosition).getName(),priceAdapter));
                viewHodler.gridView.setAdapter(priceAdapter);
                break;

        }

        return convertView;
    }


    class EditTextChange implements TextWatcher {
        GridRadioAdapter gridRadioAdapter;
        EditText view;
        String tag;

        public EditTextChange(EditText view, String tag, GridRadioAdapter gridRadioAdapter) {
            this.gridRadioAdapter=gridRadioAdapter;
            this.tag = tag;
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void afterTextChanged(Editable editable) {
            gridRadioAdapter.dataSetChanged(tag);
            if (!StringUtils.isEmpty(editable.toString())) {
                for(int i = 0; i< OrderActivity.singleKey.size(); i++){
                    SearchValue searchValue = OrderActivity.singleKey.get(i);
                    if(searchValue.getName().equals(tag)){
                        switch (view.getId()) {
                            case R.id.id_ed_hig:
                                searchValue.setHig(view.getText().toString());
                                break;
                            case R.id.id_ed_low:
                                searchValue.setLow(view.getText().toString());
                                break;
                        }
                    }
                }

                for(int i = 0; i< ClassifyActivity.singleKey.size(); i++){
                    SearchValue searchValue = ClassifyActivity.singleKey.get(i);
                    if(searchValue.getName().equals(tag)){
                        switch (view.getId()) {
                            case R.id.id_ed_hig:
                                searchValue.setHig(view.getText().toString());
                                break;
                            case R.id.id_ed_low:
                                searchValue.setLow(view.getText().toString());
                                break;
                        }
                    }
                }
            }
        }
    }


    public class ViewHodler {
        MyGridView gridView;
        LinearLayout linearLayout;
        EditText editTextLow, editTextHig;
    }



    public void isResetGridTextAdapter() {
        for (int i=0;i<gridRadioAdapters.size();i++){
            gridRadioAdapters.get(i).notifyDataSetChanged();
        }
        for(int i=0;i<checkBoxAdapters.size();i++){
            checkBoxAdapters.get(i).notifyDataSetChanged();
        }
        notifyDataSetChanged();
    }

    /**
     * 当选择子节点的时候，调用该方法
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

    }


}