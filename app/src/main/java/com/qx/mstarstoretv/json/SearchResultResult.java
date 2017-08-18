package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class SearchResultResult {
    /**
     * data : {"list_count":637,"orderList":[{"customerName":"钟爱珠宝","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 15:32:41","orderNum":"AP170319627","purityName":"G18K玫瑰金"},{"customerName":"爱恋珠宝（深圳）17032001","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 15:28:57","orderNum":"AP170320679","purityName":"G750白"},{"customerName":"广州海天","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 15:22:15","orderNum":"AP170320678","purityName":"G18K白"},{"customerName":"天泽龙泰昆王","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 15:18:58","orderNum":"AP170320677","purityName":"G18K玫瑰金"},{"customerName":"溧阳八佰伴自营","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 14:53:29","orderNum":"AP170320676","purityName":"G18K玫瑰金"},{"customerName":"爱恋珠宝（深圳）17030102W","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 14:34:39","orderNum":"AP170320675","purityName":"G18K玫瑰金"},{"customerName":"爱恋珠宝（深圳）17030102W","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 14:33:50","orderNum":"AP170320674","purityName":"G750白"},{"customerName":"爱恋珠宝（深圳）17031002W","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 14:24:09","orderNum":"AP170312377","purityName":"G750白"},{"customerName":"爱恋珠宝（深圳）17031001W","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 14:23:40","orderNum":"AP170312382","purityName":"G750白"},{"customerName":"爱恋珠宝（深圳）17031001W","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 14:23:31","orderNum":"AP170312381","purityName":"G18K玫瑰金"}]}
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
         * list_count : 637
         * orderList : [{"customerName":"钟爱珠宝","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 15:32:41","orderNum":"AP170319627","purityName":"G18K玫瑰金"},{"customerName":"爱恋珠宝（深圳）17032001","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 15:28:57","orderNum":"AP170320679","purityName":"G750白"},{"customerName":"广州海天","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 15:22:15","orderNum":"AP170320678","purityName":"G18K白"},{"customerName":"天泽龙泰昆王","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 15:18:58","orderNum":"AP170320677","purityName":"G18K玫瑰金"},{"customerName":"溧阳八佰伴自营","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 14:53:29","orderNum":"AP170320676","purityName":"G18K玫瑰金"},{"customerName":"爱恋珠宝（深圳）17030102W","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 14:34:39","orderNum":"AP170320675","purityName":"G18K玫瑰金"},{"customerName":"爱恋珠宝（深圳）17030102W","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 14:33:50","orderNum":"AP170320674","purityName":"G750白"},{"customerName":"爱恋珠宝（深圳）17031002W","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 14:24:09","orderNum":"AP170312377","purityName":"G750白"},{"customerName":"爱恋珠宝（深圳）17031001W","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 14:23:40","orderNum":"AP170312382","purityName":"G750白"},{"customerName":"爱恋珠宝（深圳）17031001W","goldPrice":209.2,"number":0,"orderDate":"2017-03-20 14:23:31","orderNum":"AP170312381","purityName":"G18K玫瑰金"}]
         */

        private int list_count;
        private List<OrderListBean> orderList;

        public int getList_count() {
            return list_count;
        }

        public void setList_count(int list_count) {
            this.list_count = list_count;
        }

        public List<OrderListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public static class OrderListBean {
            /**
             * customerName : 钟爱珠宝
             * goldPrice : 209.2
             * number : 0
             * orderDate : 2017-03-20 15:32:41
             * orderNum : AP170319627
             * purityName : G18K玫瑰金
             */

            private String customerName;
            private double goldPrice;
            private String number;
            private String orderDate;
            private String orderNum;
            private String purityName;

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public double getGoldPrice() {
                return goldPrice;
            }

            public void setGoldPrice(double goldPrice) {
                this.goldPrice = goldPrice;
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
        }
    }
}
