package com.qx.mstarstoretv.viewutils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import java.util.List;

import com.qx.mstarstoretv.adapter.BaseViewHolder;
import com.qx.mstarstoretv.adapter.CommonAdapter;
import com.qx.mstarstoretv.base.BaseActivity;
import com.qx.mstarstoretv.bean.Type;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.wheelview.widget.WheelItem;
import com.qx.mstarstoretv.viewutils.wheelview.widget.WheelView;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class CustomselectStringButton extends RelativeLayout {

    private View rootview;
    private String text;
    private String type;
    private TextView tv;
    private List<String> types;
    private List<String> stringTypes;
    private Context mContext;
    String textName;
    float textSize;
    private PopupWindow popupWindow;
    private WheelView wheelView;
    private TextView tvTitle;
    private TextView tvConfirm;
    private TextView tvCancle;
    private boolean isAbleOnclick = true;
    private int backgroundId;
    private ListView listview;
    private CommonAdapter<String> adapter;
    private int showType;

    public int getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(int backgroundId) {
        this.backgroundId = backgroundId;
        tv.setBackgroundResource(backgroundId);
    }

    public TextView getTv() {
        return tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    public void setTextName(String textName) {
        if (!StringUtils.isEmpty(textName)) {
            this.tv.setText(textName);
            tv.setTextColor(getResources().getColor(R.color.black));
            if(showType==0){
                tv.setBackgroundResource(R.drawable.btn_bg_while );
            }else {
                tv.setBackgroundResource(R.color.white );
            }

        }
    }

    public void setDefaultText(String textName) {
        if (!StringUtils.isEmpty(textName)) {
            this.tv.setText(textName);
            this.tv.setTextColor(getResources().getColor(R.color.text_color2));
        }
    }

    public String getTextName() {
        return this.tv.getText().toString();
    }

    public CustomselectStringButton(Context context, View rootview) {
        super(context);
        this.rootview = rootview;
    }

    public CustomselectStringButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomselectStringButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomselectStringButton);
        try {
            showType = typedArray.getInt(R.styleable.CustomselectStringButton_show_type,0);
            textName = typedArray.getString(R.styleable.CustomselectStringButton_tv_string_name);
            backgroundId = typedArray.getResourceId(R.styleable.CustomselectStringButton_tv_string_background,R.drawable.btn_bg_while );
            textSize = typedArray.getDimension(R.styleable.CustomSelectButton_tv_size,UIUtils.sp2px(25));
        } finally {
            typedArray.recycle();
        }

        View rootView = View.inflate(context, R.layout.custom_select_button2, this);
        tv = (TextView) rootView.findViewById(R.id.id_cus_tv);
        if (!StringUtils.isEmpty(textName)) {
            tv.setText(textName);
            if(showType==0){
                tv.setBackgroundResource(R.drawable.btn_bg_while );
            }else {
                tv.setBackgroundResource(R.color.white );
            }

        }
        tv.setTextSize(textSize);
        tv.setOnClickListener(new CustomselectStringButton.RadioClickListener());
    }

    public class RadioClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            if (isAbleOnclick) {
                if (onSelectData != null) {
                    types = onSelectData.getData();
//                showDialog();
                    showPopupWindow2();
                }
            } else {
                ToastManager.showToastReal("裸石库中的裸石无法修改！");
            }
        }
    }

    private int getSelect(String text) {
        int a = -1;
        for (int i = 0; i < types.size(); i++) {
            if (types.get(i).equals(text)) {
                a = i;
                break;
            }
        }
        return a;
    }

    public void showPopupWindow() {
        View view = View.inflate(mContext, R.layout.popupwindow_bottom, null);
        wheelView = (WheelView) view.findViewById(R.id.wv_popupwindwo);
        tvTitle = (TextView) view.findViewById(R.id.tv_title_popupwindow);
        tvTitle.setText(onSelectData.getTitle());
        tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        tvCancle = (TextView) view.findViewById(R.id.tv_cancle);
        SimpleWheelAdapter arrayWheelAdapter = new SimpleWheelAdapter(mContext);
        wheelView.setWheelAdapter(arrayWheelAdapter);
        wheelView.setWheelSize(5);
        wheelView.setWheelData(types);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 20;
        style.textSize = 16;
        wheelView.setStyle(style);
        int select = getSelect(text);
        if (select == -1) {
            wheelView.setSelection(onSelectData.defaultPosition());
        } else {
            wheelView.setSelection(select);
        }
        wheelView.setSkin(WheelView.Skin.Holo);
//        wheelView.setLoop(true);
        wheelView.setWheelClickable(true);
        View view1 = onSelectData.getRootView();
        popupWindow = new PopupWindow(view, view1.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        UIUtils.setBackgroundAlpha(mContext, 0.2f);//设置屏幕透明度
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.Animation);
      popupWindow.showAsDropDown(view1);

//        popupWindow.showAtLocation(view1, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        tvConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                type = types.get(wheelView.getCurrentPosition());
                if (type != null) {
                    if (!StringUtils.isEmpty(type)) {
                        setTextName(type);
                        setText(type);
                    }
                    if (onSelectData != null) {
                        onSelectData.getSelectId(type);
                    }
                }
                closePupupWindow();
            }
        });
        tvCancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closePupupWindow();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                UIUtils.setBackgroundAlpha(mContext, 1.0f);//设置屏幕透明度
            }
        });
    }
    public void showPopupWindow2() {
        String text = getTextName().toString();
        View view = View.inflate(mContext, R.layout.popupwindow_bottom2, null);
        listview = (ListView) view.findViewById(R.id.lv_item);
        tvTitle = (TextView) view.findViewById(R.id.tv_title_popupwindow);
        tvCancle = (TextView) view.findViewById(R.id.tv_cancle);
        tvTitle.setText(onSelectData.getTitle() + "");
        adapter = new CommonAdapter<String>(types, R.layout.tv_item_type2) {
            @Override
            public void convert(int position, BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_item_type,item);
            }

        };
        listview.setAdapter(adapter);
        int select = getSelect(text);
        if (select == -1) {
            listview.setSelection(onSelectData.defaultPosition());
        } else {
            listview.setSelection(select);
        }
        UIUtils.setBackgroundAlpha(mContext, 0.3f);//设置屏幕透明度
        View view1 = onSelectData.getRootView();
        popupWindow = new PopupWindow(view, view1.getWidth() < 300 ? 300 : view1.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setAnimationStyle(R.style.Animation);
        if(showType==0){
            popupWindow.showAsDropDown(view1);
        }else {
            popupWindow.showAsDropDown(view1,0,-500);
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                type = types.get(position);
                if (type != null) {
                    if (!StringUtils.isEmpty(type)) {
                        setTextName(type);
                        setText(type);
                    }
                    if (onSelectData != null) {
                        onSelectData.getSelectId(type);
                    }
                }
                closePupupWindow();
            }
        });

        tvCancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closePupupWindow();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                UIUtils.setBackgroundAlpha(mContext, 1.0f);//设置屏幕透明度
            }
        });
    }
    public void closePupupWindow() {
        UIUtils.setBackgroundAlpha(mContext, 1.0f);//设置屏幕透明度
        popupWindow.dismiss();
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((BaseActivity) mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        ((BaseActivity) mContext).getWindow().setAttributes(lp);
    }

    AlertDialog dialog;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    OnselectData onSelectData;

    public void setOnSelectData(OnselectData onSelectData) {
        this.onSelectData = onSelectData;
    }

    public void setOnSelectData(OnselectData onSelectData, boolean isAbleOnclick) {
        this.onSelectData = onSelectData;
        this.isAbleOnclick = isAbleOnclick;
    }

    public interface OnselectData {
        List<String> getData();

        void getSelectId(String type);

        int defaultPosition();

        String getTitle();

        View getRootView();
    }

    class SimpleWheelAdapter extends  com.qx.mstarstoretv.viewutils.wheelview.adapter.SimpleWheelAdapter {

        private Context mContext;

        public SimpleWheelAdapter(Context context) {
            super(context);
            mContext = context;
        }

        @Override
        public View bindView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new WheelItem(mContext);
            }
            WheelItem item = (WheelItem) convertView;

            item.setText(types.get(position));
            return convertView;
        }

    }
}
