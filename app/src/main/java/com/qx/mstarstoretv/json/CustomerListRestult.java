package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */
public class CustomerListRestult {


    /**
     * data : {"list_count":13,"list":[{"keycode":"45305","customerFullName":null,"customerID":4359,"customerName":"旺道BS021"},{"keycode":"45213","customerFullName":null,"customerID":4266,"customerName":"旺道BS020"},{"keycode":"44098","customerFullName":null,"customerID":4141,"customerName":"旺道-0112"},{"keycode":"44097","customerFullName":null,"customerID":4140,"customerName":"旺道-神华"},{"keycode":"44096","customerFullName":null,"customerID":4139,"customerName":"旺道-包头"},{"keycode":"43869","customerFullName":null,"customerID":3895,"customerName":"旺道B-S020"},{"keycode":"43766","customerFullName":null,"customerID":3798,"customerName":"旺道B-S005"},{"keycode":"43765","customerFullName":null,"customerID":3797,"customerName":"旺道B-S006"},{"keycode":"25008","customerFullName":null,"customerID":2394,"customerName":"旺道订做"},{"keycode":"-6","customerFullName":null,"customerID":2081,"customerName":"刁轶飞旺道B单"},{"keycode":"24152","customerFullName":null,"customerID":1323,"customerName":"旺道B单"},{"keycode":"23151","customerFullName":null,"customerID":1165,"customerName":"陕西西安旺道"},{"keycode":"23793","customerFullName":" ","customerID":771,"customerName":"旺道 "}]}
     * response :
     * error : 0
     * message :
     */
    private DataEntity data;
    private String response;
    private String error;
    private String message;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataEntity getData() {
        return data;
    }

    public String getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public class DataEntity {
        /**
         * list_count : 13
         * list : [{"keycode":"45305","customerFullName":null,"customerID":4359,"customerName":"旺道BS021"},{"keycode":"45213","customerFullName":null,"customerID":4266,"customerName":"旺道BS020"},{"keycode":"44098","customerFullName":null,"customerID":4141,"customerName":"旺道-0112"},{"keycode":"44097","customerFullName":null,"customerID":4140,"customerName":"旺道-神华"},{"keycode":"44096","customerFullName":null,"customerID":4139,"customerName":"旺道-包头"},{"keycode":"43869","customerFullName":null,"customerID":3895,"customerName":"旺道B-S020"},{"keycode":"43766","customerFullName":null,"customerID":3798,"customerName":"旺道B-S005"},{"keycode":"43765","customerFullName":null,"customerID":3797,"customerName":"旺道B-S006"},{"keycode":"25008","customerFullName":null,"customerID":2394,"customerName":"旺道订做"},{"keycode":"-6","customerFullName":null,"customerID":2081,"customerName":"刁轶飞旺道B单"},{"keycode":"24152","customerFullName":null,"customerID":1323,"customerName":"旺道B单"},{"keycode":"23151","customerFullName":null,"customerID":1165,"customerName":"陕西西安旺道"},{"keycode":"23793","customerFullName":" ","customerID":771,"customerName":"旺道 "}]
         */
        private int list_count;
        private List<ListEntity> list;

        public void setList_count(int list_count) {
            this.list_count = list_count;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public int getList_count() {
            return list_count;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public class ListEntity {
            /**
             * keycode : 45305
             * customerFullName : null
             * customerID : 4359
             * customerName : 旺道BS021
             */
            private String keycode;
            private String customerFullName;
            private int customerID;
            private String customerName;

            public void setKeycode(String keycode) {
                this.keycode = keycode;
            }

            public void setCustomerFullName(String customerFullName) {
                this.customerFullName = customerFullName;
            }

            public void setCustomerID(int customerID) {
                this.customerID = customerID;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getKeycode() {
                return keycode;
            }

            public String getCustomerFullName() {
                return customerFullName;
            }

            public int getCustomerID() {
                return customerID;
            }

            public String getCustomerName() {
                return customerName;
            }
        }
    }
}
