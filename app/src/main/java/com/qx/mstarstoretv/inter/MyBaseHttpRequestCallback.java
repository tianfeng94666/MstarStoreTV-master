package com.qx.mstarstoretv.inter;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;

/**
 * Desction:
 * Author:pengjianbo
 * Date:15/9/29 下午4:06
 */
public class MyBaseHttpRequestCallback<String> extends BaseHttpRequestCallback<String>{

    @Override
    protected void onSuccess(String result) {
        onLogicSuccess(result);
    }

    public void onLogicSuccess(String t) {

    }

    public void onLogicFailure(String t) {

    }
}
