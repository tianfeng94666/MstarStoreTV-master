package com.qx.mstarstoretv.manager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class FitGridLayoutManager extends GridLayoutManager {


    private RecyclerView recyclerView;

    public FitGridLayoutManager(Context context, RecyclerView recyclerView, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.recyclerView = recyclerView;
    }

    public FitGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public FitGridLayoutManager(Context context, RecyclerView recyclerView, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
//        try {
//            for(int i = 0 ;i <getItemCount();i++){
//                View view = recycler.getViewForPosition(i);
//               ViewGroup.LayoutParams p =view.getLayoutParams();
//                p.width =100;
//                p.height = UIUtils.getWindowWidth()/2;
//                view.setLayoutParams(p);
//                recycler.recycleView(view);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
