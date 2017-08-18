package com.qx.mstarstoretv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.ClassifyActivity;
import com.qx.mstarstoretv.activity.OrderActivity;
import com.qx.mstarstoretv.base.MyAction;
import com.qx.mstarstoretv.json.SearchValue;
import com.qx.mstarstoretv.json.TypeFiler;

import java.util.List;

/*
 * 创建人：Yangshao
 * 创建时间：2016/10/11 8:57
 * @version  这段代码只有两个人能看得懂，一个是上帝一个是我
 *    
 */
public class GridRadioAdapter extends BaseAdapter {
    private List<TypeFiler> child_text_array;
    private Context mContext;
    String mgetGroupKey;
    String myAction;
    public List<SearchValue> isSlect;
    public GridRadioAdapter(Context context, List<TypeFiler> child_text_array,String getGroupKey,String action) {
        this.mContext = context;
        this.child_text_array = child_text_array;
        this.mgetGroupKey=getGroupKey;
        this.myAction=action;
    }

    @Override
    public int getCount() {
        return child_text_array.size();
    }

    @Override
    public TypeFiler getItem(int position) {
        return child_text_array.get(position);
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
                    R.layout.item_gridview_radio, null);
            viewHodler = new ViewHodler();
            viewHodler.radioButton = (RadioButton) convertView
                    .findViewById(R.id.rd_item_goods_type);
            viewHodler.linearLayout = (LinearLayout) convertView
                    .findViewById(R.id.id_lay1);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        viewHodler.radioButton.setText(child_text_array.get(position).getName());
        if (myAction.equals(MyAction.classifyActivityAction)){
            isSlect= ClassifyActivity.singleKey;
        }else {
            isSlect= OrderActivity.singleKey;
        }
        if (isSlect.size()!=0){
            for (int i = 0; i <isSlect.size(); i++) {
                if (isSlect.get(i).getName().equals(mgetGroupKey)){
                    viewHodler.searchKeyword=isSlect.get(i);
//                    if (myAction.equals(MyAction.filterDialogAction)){
//                    }
                    if (viewHodler.searchKeyword.getValue().equals(child_text_array.get(position).getValue())){
                        viewHodler.radioButton.setChecked(true);
                    }else {
                        viewHodler.radioButton.setChecked(false);
                    }
                }
            }
        }

        final ViewHodler finalViewHodler = viewHodler;
        viewHodler.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCleanEditText!=null){
                    onCleanEditText.onClean(mgetGroupKey);
                }
                if(finalViewHodler.searchKeyword!=null){
                    finalViewHodler.searchKeyword.setValue(child_text_array.get(position).getValue());
                    finalViewHodler.searchKeyword.setTxt(child_text_array.get(position).getName());
                }

                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    /*重置已经选择的*/
    public void dataSetChanged(String getGroupKey) {
        for (int i = 0; i <isSlect.size(); i++) {
            if (isSlect.get(i).getName().equals(getGroupKey)){
                SearchValue searchKeyword=isSlect.get(i);
                searchKeyword.setValue("");
            }
        }
        notifyDataSetChanged();
    }

    public void setOnCleanEditText(GridRadioAdapter.onCleanEditText onCleanEditText) {
        this.onCleanEditText = onCleanEditText;
    }

    onCleanEditText onCleanEditText;
    interface  onCleanEditText{
        void onClean(String tag);
    }
    public class ViewHodler {
        public RadioButton radioButton;
        public LinearLayout linearLayout;
        public SearchValue searchKeyword;
    }
}
