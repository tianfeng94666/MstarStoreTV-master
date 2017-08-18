package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class GetAddressResult {

    /**
     * data : {"address":{"addr":"广东省 深圳市罗湖区 水贝二路 19栋","id":0,"isDefault":0,"name":"自提","phone":"011236569"}}
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
         * address : {"addr":"广东省 深圳市罗湖区 水贝二路 19栋","id":0,"isDefault":0,"name":"自提","phone":"011236569"}
         */

        private AddressEntity address;
        private CustomerEntity customerEntity;

        public CustomerEntity getCustomerEntity() {
            return customerEntity;
        }

        public void setCustomerEntity(CustomerEntity customerEntity) {
            this.customerEntity = customerEntity;
        }

        public AddressEntity getAddress() {
            return address;
        }

        public void setAddress(AddressEntity address) {
            this.address = address;
        }


    }
}
