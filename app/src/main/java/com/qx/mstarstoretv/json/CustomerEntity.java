package com.qx.mstarstoretv.json;

public class CustomerEntity {
            /**
             * keycode : 01040
             * customerFullName :
             * customerID : 148
             * customerName : 杭州浙地
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