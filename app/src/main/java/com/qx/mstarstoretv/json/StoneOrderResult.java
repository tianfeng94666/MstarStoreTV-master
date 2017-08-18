package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class StoneOrderResult {

    /**
     * response :
     * error : 0
     * message :
     * data : {"address":{"id":"119","name":"Gigi ","phone":"13456543456","addr":"江苏省 淮安市 盱眙县 34567788887"},"list":[],"customer":{"customerID":23651,"keycode":"97108","customerName":"冼小俊测试","customerFullName":null}}
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
         * address : {"id":"119","name":"Gigi ","phone":"13456543456","addr":"江苏省 淮安市 盱眙县 34567788887"}
         * list : []
         * customer : {"customerID":23651,"keycode":"97108","customerName":"冼小俊测试","customerFullName":null}
         */

        private AddressEntity address;
        private CustomerEntity customer;
        private List<StoneBean> list;

        public AddressEntity getAddress() {
            return address;
        }

        public void setAddress(AddressEntity address) {
            this.address = address;
        }

        public CustomerEntity getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerEntity customer) {
            this.customer = customer;
        }

        public List<StoneBean> getList() {
            return list;
        }

        public void setList(List<StoneBean> list) {
            this.list = list;
        }




    }
}
