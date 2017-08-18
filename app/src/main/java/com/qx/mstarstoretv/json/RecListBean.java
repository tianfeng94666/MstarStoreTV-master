package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public  class RecListBean {
    /**
     * customerName : 富士达
     * moList : [{"moDate":"2017-03-07 09:09:15","moNum":1703070003,"number":1,"recNum":1703070003,"totalPrice":840.53}]
     * number : 1
     * purityName : G18K白
     * recDate : 2017-03-07 09:12:11
     * recNum : 1703070003
     * totalPrice : 73.26
     */

    private String customerName;
    private String number;
    private String purityName;
    private String recDate;
    private String recNum;
    private double totalPrice;
    private List<MoListBean> moList;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPurityName() {
        return purityName;
    }

    public void setPurityName(String purityName) {
        this.purityName = purityName;
    }

    public String getRecDate() {
        return recDate;
    }

    public void setRecDate(String recDate) {
        this.recDate = recDate;
    }

    public String getRecNum() {
        return recNum;
    }

    public void setRecNum(String recNum) {
        this.recNum = recNum;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<MoListBean> getMoList() {
        return moList;
    }

    public void setMoList(List<MoListBean> moList) {
        this.moList = moList;
    }

    public static class MoListBean {
        /**
         * moDate : 2017-03-07 09:09:15
         * moNum : 1703070003
         * number : 1
         * recNum : 1703070003
         * totalPrice : 840.53
         */

        private String moDate;
        private String moNum;
        private String number;
        private String recNum;
        private double totalPrice;

        public String getMoDate() {
            return moDate;
        }

        public void setMoDate(String moDate) {
            this.moDate = moDate;
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

        public String getRecNum() {
            return recNum;
        }

        public void setRecNum(String recNum) {
            this.recNum = recNum;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }
    }
}