//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qx.mstarstoretv.viewutils;

import android.view.View;
import android.view.ViewGroup;

public class CustomAdapter {
    private String TAG = CustomAdapter.class.getSimpleName();
    private View myView;
    private ViewGroup myViewGroup;
    private CustomListView myCustomListView;

    public CustomAdapter() {
    }

    public int getCount() {
        return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0L;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    private final void getAllViewAddSexangle() {
        this.myCustomListView.removeAllViews();

        for(int i = 0; i < this.getCount(); ++i) {
            View viewItem = this.getView(i, this.myView, this.myViewGroup);
            this.myCustomListView.addView(viewItem, i);
        }

    }

    public void notifyDataSetChanged() {
        CustomListView.setAddChildType(true);
        this.notifyCustomListView(this.myCustomListView);
    }

    public void notifyCustomListView(CustomListView formateList) {
        this.myCustomListView = formateList;
        this.myCustomListView.removeAllViews();
        this.getAllViewAddSexangle();
    }


}
