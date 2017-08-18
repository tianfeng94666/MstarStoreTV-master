package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class FinishTableLessResult {

    /**
     * data : {"recList":[{"customerName":"富士达","moList":[{"moDate":"2017-03-07 09:09:15","moNum":1703070003,"number":1,"recNum":1703070003,"totalPrice":840.53}],"number":1,"purityName":"G18K白","recDate":"2017-03-07 09:12:11","recNum":1703070003,"totalPrice":73.26}]}
     * error : 0
     * message :
     * response :
     */

    private DataBean data;
    private int error;
    private String message;
    private String response;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static class DataBean {
        private List<RecListBean> recList;

        public List<RecListBean> getRecList() {
            return recList;
        }

        public void setRecList(List<RecListBean> recList) {
            this.recList = recList;
        }


    }
}
