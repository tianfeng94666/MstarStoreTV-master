package com.qx.mstarstoretv.viewutils;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/26 0026.
 */

public class SquareTextView extends TextView {


        private static final int SPEC = 3;
        private static int SCREENWIDTH;
        private Context mContext;
        private int divideAmount;



        public SquareTextView(Context context) {
            super(context);
            init(context);
        }

        public SquareTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }

        public SquareTextView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context);
        }

        private void init(Context mContext) {
            this.mContext = mContext;
            SCREENWIDTH = getScreenWidth();
        }
        public boolean isScreenChange() {

            Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
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
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            if( isScreenChange()){
                divideAmount=4;
            }else {
                divideAmount=2;
            }
            int width = (SCREENWIDTH - dp2pix(SPEC)) / divideAmount;
            setMeasuredDimension(width, width);
        }

        private int getScreenWidth() {
            return mContext.getResources().getDisplayMetrics().widthPixels;
        }

        private int dp2pix(float dp) {
            float density = mContext.getResources().getDisplayMetrics().density;
            return (int) (dp * density + 0.5f);
        }


}
