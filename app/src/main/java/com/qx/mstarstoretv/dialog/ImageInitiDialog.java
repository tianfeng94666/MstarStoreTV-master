package com.qx.mstarstoretv.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

import com.qx.mstarstoretv.R;

/**
 * Created by Administrator on 2016/10/14.
 */
public class ImageInitiDialog extends Dialog {

    Context context;
    PopupWindow pop;
    public ImageInitiDialog(Context context) {
        super(context);
        this.context=context;
        nativeupload();
    }

    OnImageSelectListener OnImageSelectListener;
    public interface OnImageSelectListener{
        void onCamera();
        void onPhotos();
    }

    public void setOnImageSelectListener(ImageInitiDialog.OnImageSelectListener onImageSelectListener) {
        OnImageSelectListener = onImageSelectListener;
    }

    private void nativeupload() {
        View view = LayoutInflater.from(context).inflate(R.layout.native_upload_layout, null, false);
        pop = new PopupWindow(view, -1, -1, true);
        pop.setBackgroundDrawable(new ColorDrawable(0x993b3b3b));
        View camera = view.findViewById(R.id.camera);
        View photos = view.findViewById(R.id.photos);
        View cancel = view.findViewById(R.id.cancel);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnImageSelectListener.onCamera();
                pop.dismiss();
            }
        });

        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OnImageSelectListener.onPhotos();
                pop.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (pop != null && pop.isShowing()) {
                    pop.dismiss();
                }
                return false;
            }
        });

    }

    public void showDialog(View view){
        pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }
}
