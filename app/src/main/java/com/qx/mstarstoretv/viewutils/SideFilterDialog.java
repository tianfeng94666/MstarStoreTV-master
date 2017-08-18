package com.qx.mstarstoretv.viewutils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.OrderActivity;
import com.qx.mstarstoretv.base.BaseFilterData;
import com.qx.mstarstoretv.base.MyAction;
import com.qx.mstarstoretv.bean.Type;
import com.qx.mstarstoretv.json.ClassTypeFilerEntity;
import com.qx.mstarstoretv.json.SearchValue;
import com.qx.mstarstoretv.json.TypeFiler;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;


/*
 * 创建人：Yangshao
 * 创建时间：2016/10/24 18:02
 * @version    侧滑筛选菜单
 *
 */

public  class SideFilterDialog extends BaseFilterData {

    private View mPopView;
    private Context mContext;

    private PopupWindow popupWindow;
    private ExpandableListView expandableGridView;
    private List<ClassTypeFilerEntity> mTypeListData;
    /*确定和重置按钮*/
    private TextView idTvConfirfilterr;
    private TextView idTvResetfilter;
    //得到 状态栏的高度
    int mStatusBarHeight;

    private GridViewWithHeaderAndFooter lv;
    private List<Type> typese;
    private ListViewAdapter listViewAdapter = new ListViewAdapter();

    public SideFilterDialog(Context context, List<ClassTypeFilerEntity> typeListData, String action,int statusBarHeight) {
        this.mStatusBarHeight=statusBarHeight;
        this.mContext = context;
        this.mTypeListData = typeListData;
        initView(context);
        initBaseFilterData(context, action);
        initListener();
    }



    private void initListener() {
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                L.e("SideFilterDialog  消失");
                dismiss();
            }
        });
        idTvResetfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*单选始终有默认值  只需要情况里面的Value*/
                for (int i = 0; i < OrderActivity.singleKey.size(); i++) {
                    OrderActivity.singleKey.get(i).setValue("");
                }
                OrderActivity.multiselectKey.clear();
                typese.clear();
                listViewAdapter.notifyDataSetChanged();
                adapter.isResetGridTextAdapter();
            }
        });


        /*提交  开始搜索*/
        idTvConfirfilterr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSeachListener != null) {
                    onSeachListener.onSeach(MyAction.filterDialogAction);
                }
                //getCheckBoxUrl();
                // getRadioGroupUrl();
                dismiss();
            }
        });

    }

    public boolean isScreenChange() {

        Configuration mConfiguration = mContext.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向

        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            //横屏
            return true;
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            //竖屏
            return false;
        }
        return false;
    }


    public void initView(Context context) {
        initData();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPopView = inflater.inflate(R.layout.dialog_filter_dialog, null);
        expandableGridView = (ExpandableListView) mPopView.findViewById(R.id.list);
        lv = (GridViewWithHeaderAndFooter) mPopView.findViewById(R.id.id_lv_memu);
        idTvConfirfilterr = (TextView) mPopView.findViewById(R.id.id_tv_confirfilterr);
        idTvResetfilter = (TextView) mPopView.findViewById(R.id.id_tv_resetfilter);

        if(UIUtils.isPad(mContext)){
            popupWindow = new PopupWindow(mPopView, UIUtils.dip2px(760), getWindowHeight());
            lv.setNumColumns(5);
        }else {
            popupWindow = new PopupWindow(mPopView, UIUtils.dip2px(440), getWindowHeight());
        }
        lv.setAdapter(listViewAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typese.remove(i);
                listViewAdapter.notifyDataSetChanged();
            }
        });
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());     //点击外部消失这句很重要
        // 点击外面的控件也可以使得PopUpWindow dimiss
        popupWindow.setOutsideTouchable(true);

    }
    private void initData() {
        typese  = new ArrayList<>();
        if (OrderActivity.multiselectKey.size() != 0&&OrderActivity.multiselectKey != null) {
            for (int i = 0; i < OrderActivity.multiselectKey.size(); i++) {
                TypeFiler categoryFiler = OrderActivity.multiselectKey.get(i);
                Type type=new Type();
                type.setId(categoryFiler.getId());
                type.setType(1);
                type.setTypeName(categoryFiler.getTitle());
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
                    continue;
                }
                Type type=new Type();
                type.setId(searchKeyword.getValue());
                type.setTypeName(searchKeyword.getTypename());
                type.setContent(searchKeyword.getTxt());
                type.setValue(searchKeyword.getValue());
                type.setType(2);
                typese.add(type);
            }
        }

    }
    public void getWindowHight(){
        DisplayMetrics metric = new DisplayMetrics();
        UIUtils.getWindowManager().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 2
    }


    @Override
    public ExpandableListView getExpandableGridView() {
        return expandableGridView;
    }

    @Override
    public List<ClassTypeFilerEntity> getTypeListData() {
        return mTypeListData;
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

    @Override
    public void loadNetData() {

    }

    public interface OnListMenuSelectCloseClick {
        void onClose();
    }

    // 下拉式 弹出 pop菜单 parent 右下角
    public void showAsDropDown(RelativeLayout parent, int y) {
        popupWindow.showAtLocation(parent, Gravity.RIGHT, 0, y);
    }

    public int getWindowWidth() {
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    public int getWindowHeight() {
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
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
            ListViewAdapter.ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ListViewAdapter.ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gridview_txt1, parent, false);
                viewHolder.textView = (Button) convertView.findViewById(R.id.tv_item_goods_type);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ListViewAdapter.ViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText("X "+typese.get(position).getContent());
            return convertView;
        }


        public class ViewHolder {
            Button textView;
        }
    }

}
