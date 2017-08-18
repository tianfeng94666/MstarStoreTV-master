package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class QuotedPriceResult {

    /**
     * response :
     * error : 0
     * message :
     * data : {"list":[{"title":"包含证书","content":"证书:GIA 重量:0.18 颜色:D 净度:VVS2 价格:9000.00"},{"title":"不包含证书","content":" 重量:0.18 颜色:D 净度:VVS2 价格:9000.00"},{"title":"包含证书","content":"证书:GIA 重量:0.32 颜色:D 净度:VVS2 价格:16300.00"},{"title":"不包含证书","content":" 重量:0.32 颜色:D 净度:VVS2 价格:16300.00"},{"title":"包含证书","content":"证书:GIA 重量:0.67 颜色:D 净度:VS1 价格:24600.00"},{"title":"不包含证书","content":" 重量:0.67 颜色:D 净度:VS1 价格:24600.00"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * title : 包含证书
             * content : 证书:GIA 重量:0.18 颜色:D 净度:VVS2 价格:9000.00
             */

            private String title;
            private String content;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
