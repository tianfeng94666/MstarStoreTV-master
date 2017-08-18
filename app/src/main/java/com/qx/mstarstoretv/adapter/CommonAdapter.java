package com.qx.mstarstoretv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qx.mstarstoretv.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;
    private int defItem;
    private int selectColor;

    public CommonAdapter(List<T> mDatas, int itemLayoutId) {
        this.mContext = UIUtils.getContext();
        this.mInflater = LayoutInflater.from(mContext);
        this.mItemLayoutId = itemLayoutId;
        setListData(mDatas);

    }

    public void setDefSelect(int position,int color) {
        this.defItem = position;
        this.selectColor =color;
        notifyDataSetChanged();
    }
    /**
     * 添加集合数据到原集合首位，下拉刷新时使用
     *
     * @param list
     */
    public void addToFirst(List<T> list) {
        if (list == null)
            return;
        this.mDatas.addAll(0, list);
        notifyDataSetChanged();
    }

    public void addToFirst(T t) {
        if (t == null)
            return;
        this.mDatas.add(0, t);
        notifyDataSetChanged();
    }

    /**
     * 添加数据到末尾，用于上拉加载等情况。<BR>
     * 不清楚原集合，添加到末尾。
     *
     * @param list
     */
    public void addToLast(List<T> list) {
        if (list == null)
            return;
        this.mDatas.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加元素到集合末尾
     *
     * @param t
     */
    public void addToLast(T t) {
        if (t == null)
            return;
        this.mDatas.add(t);
        notifyDataSetChanged();
    }


    /**
     * 设置数据。<BR>
     * 会清空原集合所有数据,后添加。
     *
     * @param list
     */
    public void setListData(List<T> list) {
        if (list == null) {
            list = new ArrayList<T>(0);
        }
        this.mDatas = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final BaseViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(position,viewHolder, getItem(position));
        return viewHolder.getConvertView();

    }

    public abstract void convert(int position, BaseViewHolder helper, T item);

    private BaseViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return BaseViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

}  