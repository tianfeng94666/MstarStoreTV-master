package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class CancleStoneOrderResult {

    /**
     * response :
     * error : 0
     * message : 取消成功
     * data : null
     */

    private String response;
    private int error;
    private String message;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
