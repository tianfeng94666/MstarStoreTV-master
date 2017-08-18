package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class SendingResult {
    /**
     * data : {"list_count":25,"orderList":[{"customerName":"富士达","moNum":1,"number":1,"orderDate":"2017-02-28 14:41:06","orderNum":"AP170228268","purityName":"G18K白","recNum":1},{"customerName":"世爵百年KD3884-00","moNum":1,"number":1,"orderDate":"2017-02-23 17:16:47","orderNum":"AP170223231","purityName":"Au750白","recNum":1},{"customerName":"世爵百年KD3850-NJ","moNum":1,"number":1,"orderDate":"2017-02-21 13:55:19","orderNum":"AP170221199","purityName":"Au750白","recNum":1},{"customerName":"富士达","moNum":2,"number":2,"orderDate":"2017-02-27 17:57:33","orderNum":"AP170227264","purityName":"PT950","recNum":2},{"customerName":"海宁明牌-银泰店","moNum":1,"number":1,"orderDate":"2017-02-20 17:03:44","orderNum":"AP170220181","purityName":"G18K玫瑰金","recNum":1},{"customerName":"海宁明牌-银泰店","moNum":1,"number":3,"orderDate":"2017-02-20 17:02:35","orderNum":"AP170220180","purityName":"G18K玫瑰金","recNum":3},{"customerName":"海宁明牌-禾兴路店","moNum":1,"number":1,"orderDate":"2017-02-20 16:53:21","orderNum":"AP170220176","purityName":"Au750白","recNum":1},{"customerName":"万长春","moNum":2,"number":2,"orderDate":"2017-02-20 16:19:21","orderNum":"AP170219161","purityName":"G750白","recNum":2},{"customerName":"海宁明牌珠宝-工人路店","moNum":1,"number":1,"orderDate":"2017-02-20 16:19:39","orderNum":"AP170219159","purityName":"Au750白","recNum":1},{"customerName":"海宁明牌珠宝","moNum":1,"number":1,"orderDate":"2017-02-17 11:23:39","orderNum":"AP170217125","purityName":"Au750白","recNum":1}]}
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
        /**
         * list_count : 25
         * orderList : [{"customerName":"富士达","moNum":1,"number":1,"orderDate":"2017-02-28 14:41:06","orderNum":"AP170228268","purityName":"G18K白","recNum":1},{"customerName":"世爵百年KD3884-00","moNum":1,"number":1,"orderDate":"2017-02-23 17:16:47","orderNum":"AP170223231","purityName":"Au750白","recNum":1},{"customerName":"世爵百年KD3850-NJ","moNum":1,"number":1,"orderDate":"2017-02-21 13:55:19","orderNum":"AP170221199","purityName":"Au750白","recNum":1},{"customerName":"富士达","moNum":2,"number":2,"orderDate":"2017-02-27 17:57:33","orderNum":"AP170227264","purityName":"PT950","recNum":2},{"customerName":"海宁明牌-银泰店","moNum":1,"number":1,"orderDate":"2017-02-20 17:03:44","orderNum":"AP170220181","purityName":"G18K玫瑰金","recNum":1},{"customerName":"海宁明牌-银泰店","moNum":1,"number":3,"orderDate":"2017-02-20 17:02:35","orderNum":"AP170220180","purityName":"G18K玫瑰金","recNum":3},{"customerName":"海宁明牌-禾兴路店","moNum":1,"number":1,"orderDate":"2017-02-20 16:53:21","orderNum":"AP170220176","purityName":"Au750白","recNum":1},{"customerName":"万长春","moNum":2,"number":2,"orderDate":"2017-02-20 16:19:21","orderNum":"AP170219161","purityName":"G750白","recNum":2},{"customerName":"海宁明牌珠宝-工人路店","moNum":1,"number":1,"orderDate":"2017-02-20 16:19:39","orderNum":"AP170219159","purityName":"Au750白","recNum":1},{"customerName":"海宁明牌珠宝","moNum":1,"number":1,"orderDate":"2017-02-17 11:23:39","orderNum":"AP170217125","purityName":"Au750白","recNum":1}]
         */

        private int list_count;
        private List<OrderListBean> orderList;

        public int getList_count() {
            return list_count;
        }

        public void setList_count(int list_count) {
            this.list_count = list_count;
        }

        public List<OrderListBean>  getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public static class OrderListBean {
            /**
             * customerName : 富士达
             * moNum : 1
             * number : 1
             * orderDate : 2017-02-28 14:41:06
             * orderNum : AP170228268
             * purityName : G18K白
             * recNum : 1
             */

            private String customerName;
            private String moNum;
            private String number;
            private String orderDate;
            private String orderNum;
            private String purityName;
            private String recNum;
            private String recBillNum;

            public String getRecBillNum() {
                return recBillNum;
            }

            public void setRecBillNum(String recBillNum) {
                this.recBillNum = recBillNum;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getMoNum() {
                return moNum;
            }

            public void setMoNum(String moNum) {
                this.moNum = moNum;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getOrderDate() {
                return orderDate;
            }

            public void setOrderDate(String orderDate) {
                this.orderDate = orderDate;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getPurityName() {
                return purityName;
            }

            public void setPurityName(String purityName) {
                this.purityName = purityName;
            }

            public String getRecNum() {
                return recNum;
            }

            public void setRecNum(String recNum) {
                this.recNum = recNum;
            }
        }
    }
}
