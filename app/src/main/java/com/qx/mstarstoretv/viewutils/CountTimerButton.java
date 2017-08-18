package com.qx.mstarstoretv.viewutils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

import com.qx.mstarstoretv.R;

public class CountTimerButton extends CountDownTimer{
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */

    private Button button;

    public CountTimerButton(Button btn,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.button=btn;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        button.setClickable(false);  //不可点击
        button.setText(millisUntilFinished / 1000 + " 秒后可重新发送");
        button.setBackgroundResource(R.drawable.frame_bg_gray);

        SpannableString spannableString=new SpannableString(button.getText().toString());
        ForegroundColorSpan span=new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(span,0,2, Spanned.SPAN_INCLUSIVE_INCLUSIVE); //时间设为红色
        button.setText(spannableString);
    }

    @Override
    public void onFinish() {
        button.setText("重新获取验证码");
        button.setClickable(true);//重新获得点击
        button.setBackgroundResource(R.drawable.frame_bg_while);  //还原背景色
    }
}
