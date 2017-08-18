package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class StoneOrderDetailResult {

    /**
     * data : {"customerName":"冼小俊测试","list":[{"info":"证书号:2131040153证书:GIA 重量:0.75 颜色:G 净度:SI2","number":"1","price":"2376.00"},{"info":"证书号:1136126085证书:GIA 重量:0.32 颜色:D 净度:VS1","number":"2","price":"3600.00"},{"info":"证书号:1136461182证书:GIA 重量:0.52 颜色:H 净度:VS2","number":"3","price":"7164.00"}],"orderDate":"2017-05-19 16:42:11","orderNum":"880548527449471010","orderNumber":3,"orderStatusTitle":"待付款","postAddress":"江苏省 淮安市 盱眙县 34567788887","postName":"金啦啦","postTel":"13456543456","remark":"","totelPrice":"13140.00"}
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
         * customerName : 冼小俊测试
         * list : [{"info":"证书号:2131040153证书:GIA 重量:0.75 颜色:G 净度:SI2","number":"1","price":"2376.00"},{"info":"证书号:1136126085证书:GIA 重量:0.32 颜色:D 净度:VS1","number":"2","price":"3600.00"},{"info":"证书号:1136461182证书:GIA 重量:0.52 颜色:H 净度:VS2","number":"3","price":"7164.00"}]
         * orderDate : 2017-05-19 16:42:11
         * orderNum : 880548527449471010
         * orderNumber : 3
         * orderStatusTitle : 待付款
         * postAddress : 江苏省 淮安市 盱眙县 34567788887
         * postName : 金啦啦
         * postTel : 13456543456
         * remark :
         * totelPrice : 13140.00
         */

        private String customerName;
        private String orderDate;
        private String orderNum;
        private int orderNumber;
        private String orderStatusTitle;
        private String postAddress;
        private String postName;
        private String postTel;
        private String remark;
        private String totelPrice;
        private List<StoneBean> list;
        private int isNeetPay;

        public int getIsNeetPay() {
            return isNeetPay;
        }

        public void setIsNeetPay(int isNeetPay) {
            this.isNeetPay = isNeetPay;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
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

        public int getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(int orderNumber) {
            this.orderNumber = orderNumber;
        }

        public String getOrderStatusTitle() {
            return orderStatusTitle;
        }

        public void setOrderStatusTitle(String orderStatusTitle) {
            this.orderStatusTitle = orderStatusTitle;
        }

        public String getPostAddress() {
            return postAddress;
        }

        public void setPostAddress(String postAddress) {
            this.postAddress = postAddress;
        }

        public String getPostName() {
            return postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public String getPostTel() {
            return postTel;
        }

        public void setPostTel(String postTel) {
            this.postTel = postTel;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTotelPrice() {
            return totelPrice;
        }

        public void setTotelPrice(String totelPrice) {
            this.totelPrice = totelPrice;
        }

        public List<StoneBean> getList() {
            return list;
        }

        public void setList(List<StoneBean> list) {
            this.list = list;
        }


    }
}
