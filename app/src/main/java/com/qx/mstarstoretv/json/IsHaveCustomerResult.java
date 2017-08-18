package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class IsHaveCustomerResult {
    /**
     * data : {"customer":{"customerID":23882,"keycode":"71844","customerName":"世爵百年KD4761-NJ","customerFullName":"深圳市金福生珠宝有限公司"},"state":1}
     * response :
     * error : 0
     * message :
     */

    private DataBean data;
    private String response;
    private String error;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * customer : {"customerID":23882,"keycode":"71844","customerName":"世爵百年KD4761-NJ","customerFullName":"深圳市金福生珠宝有限公司"}
         * state : 1
         */

        private CustomerEntity customer;
        private int state;

        public CustomerEntity getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerEntity customer) {
            this.customer = customer;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }


    }
}
