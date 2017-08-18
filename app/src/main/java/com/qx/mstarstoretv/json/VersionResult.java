package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class VersionResult {
    /**
     * response :
     * error : 0
     * message :
     * data : {"version":"1.1","path":"https://www.pgyer.com/IGab","message":"当前版本过低，请立即更新！"}
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
         * version : 1.1
         * path : https://www.pgyer.com/IGab
         * message : 当前版本过低，请立即更新！
         */

        private String version;
        private String url;
        private String message;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
