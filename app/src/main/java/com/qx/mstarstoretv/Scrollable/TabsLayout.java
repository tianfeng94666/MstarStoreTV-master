package com.qx.mstarstoretv.Scrollable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.utils.UIUtils;

public class TabsLayout extends HorizontalScrollView {

    private ViewGroup mContainer;
    private LayoutInflater mInflater;


    public TabsLayout(Context context) {
        this(context,null);
    }

    public TabsLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TabsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mInflater = LayoutInflater.from(context);
        mContainer = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
         layoutParams .gravity = Gravity.CENTER;
        mContainer.setLayoutParams(layoutParams);
        addView(mContainer);
    }

    public void setViewPager() {
        populateViews();

    }
    ImageView imageView;
    private void populateViews() {
       //
        View view = createTabView();
        imageView = (ImageView) view.findViewById(R.id.id_ig_view);
       // view.setText("1111111111111");
        LayoutParams layoutParams = new LayoutParams(UIUtils.getWindowManager().getWidth(), LayoutParams.MATCH_PARENT);
        mContainer.addView(view,layoutParams);
    }

    public void setImageView(int id) {
        imageView.setImageResource(id);
    }


    private View createTabView() {
        return  mInflater.inflate(R.layout.view_tab_item, mContainer, false);
    }

}
