package com.qx.mstarstoretv.viewutils;

import android.os.Build;
import android.util.Log;
import android.view.View;

import com.qx.mstarstoretv.utils.ToastManager;

/**
 * @author wuzhen
 * @since 2017/07/24
 */
public class BottomScalePageTransformer extends BasePageTransformer {

    private static final float MIN_SCALE = 0.80f;
    private static final float MIN_ALPHA = 0.8f;
    private int currentPosition;

    public XGallery.OnGalleryPageSelectListener getmOnGalleryPageSelectListener() {
        return mOnGalleryPageSelectListener;
    }

    public void setmOnGalleryPageSelectListener(XGallery.OnGalleryPageSelectListener mOnGalleryPageSelectListener) {
        this.mOnGalleryPageSelectListener = mOnGalleryPageSelectListener;
    }

    private XGallery.OnGalleryPageSelectListener mOnGalleryPageSelectListener;

    @Override
    public void transformPage(final View page, final float position) {
//        if (position < -1 || position > 1) {
//            page.setAlpha(MIN_ALPHA);
//            page.setScaleX(MIN_SCALE);
//            page.setScaleY(MIN_SCALE);
//        } else if (position <= 1) { // [-1,1]
//            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
//            if (position < 0) {
//                float scaleX = 1 + 0.3f * position;
//                Log.d("google_lenve_fb", "transformPage: scaleX:" + scaleX);
//                page.setScaleX(scaleX);
//                page.setScaleY(scaleX);
//            } else {
//                float scaleX = 1 - 0.3f * position;
//                page.setScaleX(scaleX);
//                page.setScaleY(scaleX);
//            }
//            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
//        }
        page.setScaleX(MIN_SCALE);
        page.setScaleY(MIN_SCALE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            page.getParent().requestLayout();
        }

    }


}
