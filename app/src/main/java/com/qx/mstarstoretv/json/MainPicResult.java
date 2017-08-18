package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class MainPicResult {

    /**
     * data : {"horizontal":["http://appapi1.fanerweb.com/images/ad/20170726/1.png","http://appapi1.fanerweb.com/images/ad/20170726/2.png"],"vertical":["http://appapi1.fanerweb.com/images/ad/20170726/b1.png","http://appapi1.fanerweb.com/images/ad/20170726/b2.png"]}
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
        private List<String> horizontal;
        private List<String> vertical;

        public List<String> getHorizontal() {
            return horizontal;
        }

        public void setHorizontal(List<String> horizontal) {
            this.horizontal = horizontal;
        }

        public List<String> getVertical() {
            return vertical;
        }

        public void setVertical(List<String> vertical) {
            this.vertical = vertical;
        }
    }
}
