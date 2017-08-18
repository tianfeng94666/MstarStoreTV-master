package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2016/9/21.
 */
public class BaseResult {


    /**
     * data : {}
     * response :
     * error :
     * message :
     */
    private DataEntity data;
    private String response;
    private String error;
    private String message;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataEntity getData() {
        return data;
    }

    public String getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public class DataEntity {
    }
}
