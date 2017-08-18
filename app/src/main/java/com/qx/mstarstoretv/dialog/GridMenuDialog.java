package com.qx.mstarstoretv.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.OrderActivity;
import com.qx.mstarstoretv.bean.Type;
import com.qx.mstarstoretv.inter.OnSeachListener;
import com.qx.mstarstoretv.json.SearchValue;
import com.qx.mstarstoretv.json.TypeFiler;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.viewutils.GridViewWithHeaderAndFooter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class GridMenuDialog {
    private Context context;
    private PopupWindow popupWindow;

    private List<Type> typese;
    private GridViewWithHeaderAndFooter lv;
    private TextView idTvConfirfilterr;
    private TextView idTvResetfilter;
    private ListViewAdapter listViewAdapter = new ListViewAdapter();
    public GridMenuDialog(Context context) {
        this.context = context;
        initData();
        initView();
    }


    OnSeachListener onSeachListener;

    public void setOnSeachListener(OnSeachListener onSeachListener) {
        this.onSeachListener = onSeachListener;
    }

    private void initData() {
        typese  = new ArrayList<>();
        if (OrderActivity.multiselectKey.size() != 0&&OrderActivity.multiselectKey != null) {
            for (int i = 0; i < OrderActivity.multiselectKey.size(); i++) {
                TypeFiler categoryFiler = OrderActivity.multiselectKey.get(i);
                Type type=new Type();
                type.setId(categoryFiler.getId());
                type.setType(1);
                type.setTypeName("");
                type.setContent(categoryFiler.getTitle());
                type.setValue(categoryFiler.getValue());
                type.setGroupKey(categoryFiler.getGroupKey());
                typese.add(type);
            }
        }

        if (OrderActivity.singleKey.size()!=0&&OrderActivity.singleKey !=null){
            for (int i = 0; i < OrderActivity.singleKey.size(); i++) {
                SearchValue searchKeyword = OrderActivity.singleKey.get(i);
                if (StringUtils.isEmpty(searchKeyword.getValue())){
                    break;
                }
                Type type=new Type();
                type.setId(searchKeyword.getValue());
                type.setTypeName(searchKeyword.getName());
                type.setContent(searchKeyword.getTxt());
                type.setValue(searchKeyword.getValue());
                type.setType(2);
                typese.add(type);
            }
        }

    }


    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_filter_menu, null);
        popupWindow = new PopupWindow(view, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）//设置监听
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    //如果焦点不在popupWindow上，且点击了外面，不再往下dispatch事件：
                    //不做任何响应,不 dismiss popupWindow
                    return false;
                }
                //否则default，往下dispatch事件:关掉popupWindow，
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        lv = (GridViewWithHeaderAndFooter) view.findViewById(R.id.id_lv_memu);
        idTvConfirfilterr = (TextView) view.findViewById(R.id.id_tv_confirfilterr);
        idTvResetfilter = (TextView) view.findViewById(R.id.id_tv_resetfilter);
        lv.setAdapter(listViewAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typese.remove(i);
                listViewAdapter.notifyDataSetChanged();
            }
        });

        idTvConfirfilterr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Type> cateTypes=new ArrayList<>();
                StringBuilder sbKy = new StringBuilder();
                for (int i=0;i<typese.size();i++){
                    Type type=typese.get(i);
                    if (type.getType()==2){
                        sbKy.append("&" + type.getTypeName() + "=" + type.getId());
                    }else {
                        cateTypes.add(type);
                    }
                }

                String url=sbKy.toString()+getCheckBoxUrl(cateTypes);
                if (onSeachListener!=null){
                    onSeachListener.onSeach(url);
                    dismiss();
                }

            }
        });
        idTvResetfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                listViewAdapter.notifyDataSetChanged();
            }
        });
    }

    public String getCheckBoxUrl(List<Type> cateTypes){
        List<String> list=new ArrayList<>();
        int count=cateTypes.size();
        for (int i=0;i<count;i++){
            Type Type = cateTypes.get(i);
            list.add(Type.getGroupKey());
        }
        HashSet<String> hs = new HashSet<>(list); //此时已经去掉重复的数据保存在hashset中
        Iterator<String> iterator=hs.iterator();
        StringBuffer sbbuf=new StringBuffer();
        while(iterator.hasNext()){
            StringBuffer sb=new StringBuffer();
            String tag=iterator.next();
            sb.append("&"+ tag+"=");
            for (int i=0;i<count;i++){
                Type typeFiler = cateTypes.get(i);
                if (typeFiler.getGroupKey().equals(tag)){
                    sb.append(typeFiler.getValue()+"|");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sbbuf.append(sb.toString());
        }

        L.e("typeFiler  check"+sbbuf.toString());
        return sbbuf.toString();
    }


    // 下拉式 弹出 pop菜单 parent 右下角
    public void showAsDropDown(View parent) {
        popupWindow.showAsDropDown(parent,
                10,
                // 保证尺寸是根据屏幕像素密度来的
                20);
        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        // 刷新状态
        ColorDrawable dw = new ColorDrawable(0xffffffff);//0xb0000000
        popupWindow.setBackgroundDrawable(dw);//半透明颜色
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                L.e("List_noteTypeActivity:", "我是关闭事件");
                onListMenuCloseClick.onClose();
            }
        });
        popupWindow.update();
    }

    // 隐藏菜单
    public void dismiss() {
        if (onListMenuCloseClick != null) {
            popupWindow.dismiss();
            onListMenuCloseClick.onClose();
        }

    }

    public void setOnListMenuSelectCloseClick(OnListMenuSelectCloseClick onListMenuCloseClick) {
        this.onListMenuCloseClick = onListMenuCloseClick;
    }

    OnListMenuSelectCloseClick onListMenuCloseClick;

    public interface OnListMenuSelectCloseClick {
        void onClose();

        void onSelect(String select);
    }

    public class ListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return typese.size();
        }

        @Override
        public Object getItem(int position) {
            return typese.get(position);
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
                convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview_txt1, parent, false);
                viewHolder.textView = (Button) convertView.findViewById(R.id.tv_item_goods_type);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText("X "+typese.get(position).getContent());
            return convertView;
        }


        public class ViewHolder {
            Button textView;
        }
    }
}
