package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class GetPartListResult {

    /**
     * response :
     * error : 0
     * message :
     * data : {"list":[{"pid":"104926","partSort":"2","price":"0","title":"圆形","pics":"http://appapi1.fanerweb.com/","picm":"http://appapi1.fanerweb.com/","picb":"http://appapi1.fanerweb.com/","modelPartCount":["","",""],"selectProItem":{"id":"104930","title":"三爪圆形中圈","price":"0","handSize":"","word":"","modelPic":[{"pics":"http://appapi1.fanerweb.com/","picm":"http://appapi1.fanerweb.com/","picb":"http://appapi1.fanerweb.com/"}]}},{"pid":"104927","partSort":"2","price":"0","title":"方形","pics":"http://appapi1.fanerweb.com/","picm":"http://appapi1.fanerweb.com/","picb":"http://appapi1.fanerweb.com/","modelPartCount":["","",""],"selectProItem":{"id":"104933","title":"三爪方形中圈","price":"0","handSize":"","word":"","modelPic":[{"pics":"http://appapi1.fanerweb.com/","picm":"http://appapi1.fanerweb.com/","picb":"http://appapi1.fanerweb.com/"}]}}]}
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
        private List<ModelPartsBean> list;

        public List<ModelPartsBean> getList() {
            return list;
        }

        public void setList(List<ModelPartsBean> list) {
            this.list = list;
        }


    }
}
