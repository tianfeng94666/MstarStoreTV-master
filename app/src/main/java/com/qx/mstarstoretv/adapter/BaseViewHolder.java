package com.qx.mstarstoretv.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qx.mstarstoretv.net.ImageLoadOptions;

public class BaseViewHolder {
    private final SparseArray<View> mViews;
    private View mConvertView;
    private int mPosition;

    private BaseViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,false);
        //setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static BaseViewHolder get(Context context, View convertView,
                                     ViewGroup parent, int layoutId, int position) {

        if (convertView == null) {
            return new BaseViewHolder(context, parent, layoutId, position);
        }
        return (BaseViewHolder) convertView.getTag();
    }


    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public BaseViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }
    /**
     * 为TextView设置背景
     *
     * @param viewId
     * @param text
     * @return
     */
    public BaseViewHolder setText(int viewId, String text,int id) {
        TextView view = getView(viewId);
        view.setText(text);
        view.setBackgroundResource(id);
        return this;
    }
    /**
     * 为TextView字体颜色
     *
     * @param viewId
     * @param text
     * @return
     */
    public BaseViewHolder setText(int viewId, String text,int backgroundid ,int colorid) {
        TextView view = getView(viewId);
        view.setText(text);
        view.setBackgroundResource(backgroundid);
        view.setTextColor(colorid);
        return this;
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public BaseViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public BaseViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public BaseViewHolder setImageBitmap(int viewId, String st) {
        ImageView view = getView(viewId);
        ImageLoader.getInstance().displayImage(st, view, ImageLoadOptions.getOptions());
        return this;
    }
    public BaseViewHolder setImageBitmapHeight(int viewId, double t) {
        ImageView view = getView(viewId);
        view.setMinimumHeight((int)(view.getMeasuredWidth()*t));
        return this;
    }
    /**
     * 为ImageView设置点击事件
     *
     * @param viewId
     * @return
     */
    public BaseViewHolder setViewOnclick(int viewId, View.OnClickListener clickListener) {
        View view = getView(viewId);
       view.setOnClickListener(clickListener);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public BaseViewHolder setImageByUrl(int viewId, String url) {
        ImageLoader.getInstance().displayImage(url, (ImageView) getView(viewId), ImageLoadOptions.getOptions());
        return this;
    }

    public int getPosition() {
        return mPosition;
    }

}  