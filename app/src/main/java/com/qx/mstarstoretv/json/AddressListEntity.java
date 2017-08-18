package com.qx.mstarstoretv.json;

public class AddressListEntity {

            /**
             * isDefault : 1
             * phone : 15155166161
             * name : 你们
             * id : 17
             * addr : 内蒙古自治区 鄂尔多斯市 达拉特旗 内蒙古自治区/鄂尔多斯市/达拉特旗
             */
            private String isDefault;
            private String phone;
            private String name;
            private String id;
            private String addr;

            public void setIsDefault(String isDefault) {
                this.isDefault = isDefault;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getIsDefault() {
                return isDefault;
            }

            public String getPhone() {
                return phone;
            }

            public String getName() {
                return name;
            }

            public String getId() {
                return id;
            }

            public String getAddr() {
                return addr;
            }
        }