package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class ConfirmOrderResult {
    /**
     * response :
     * error : 0
     * message : 生成订单成功
     * data : {"orderNum":"AP170512020","isNeetPay":1,"id":"2020","waitOrderCount":"3","isErpOrder":0}
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
         * orderNum : AP170512020
         * isNeetPay : 1
         * id : 2020
         *
         * waitOrderCount : 3
         * isErpOrder : 0
         */

        private String orderNum;
        private int isNeetPay;
        private String id;
        private String orderId;
        private String waitOrderCount;
        private int isErpOrder;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public int getIsNeetPay() {
            return isNeetPay;
        }

        public void setIsNeetPay(int isNeetPay) {
            this.isNeetPay = isNeetPay;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWaitOrderCount() {
            return waitOrderCount;
        }

        public void setWaitOrderCount(String waitOrderCount) {
            this.waitOrderCount = waitOrderCount;
        }

        public int getIsErpOrder() {
            return isErpOrder;
        }

        public void setIsErpOrder(int isErpOrder) {
            this.isErpOrder = isErpOrder;
        }
    }
}
