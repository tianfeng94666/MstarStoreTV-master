package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class UpdataVersionResult {

    /**
     * response :
     * error : 0
     * message :
     * data : {"value":2,"message":"当前版本过低，请更新"}
     */

    private String response;
    private int error;
    private String message;
    private DataBean data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * value : 2
         * message : 当前版本过低，请更新
         */

        private int value;
        private String message;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
