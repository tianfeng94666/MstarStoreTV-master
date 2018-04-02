package com.qx.mstarstoretv.json;



import com.qx.mstarstoretv.bean.Type;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23 0023.
 */

public class PlaceResult {

    /**
     * response :
     * error : 0
     * message :
     * data : {"memberArealist":[{"id":"4","title":"苏皖区"},{"id":"7","title":"其他"}]}
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
        private List<Type> memberArealist;

        public List<Type> getMemberArealist() {
            return memberArealist;
        }

        public void setMemberArealist(List<Type> memberArealist) {
            this.memberArealist = memberArealist;
        }

    }
}
