package com.qx.mstarstoretv.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class WeixinPayResult {
    /**
     * response :
     * error : 0
     * message :
     * data : {"prepayid":"wx2017060815285680d60881ca0141299779","timestamp":"1496907314","package":"WXPay","sign":"95C71ACAF995EDEBF2F7AB30B7F7B084","partnerid":"1480954052","noncestr":"R0NHDkrcg5VJg9Ii","appid":"wx303dc6296f3aed55"}
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
         * prepayid : wx2017060815285680d60881ca0141299779
         * timestamp : 1496907314
         * package : WXPay
         * sign : 95C71ACAF995EDEBF2F7AB30B7F7B084
         * partnerid : 1480954052
         * noncestr : R0NHDkrcg5VJg9Ii
         * appid : wx303dc6296f3aed55
         */

        private String prepayid;
        private String timestamp;
        @SerializedName("package")
        private String packageX;
        private String sign;
        private String partnerid;
        private String noncestr;
        private String appid;

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }
    }
}
