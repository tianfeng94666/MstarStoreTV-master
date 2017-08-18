package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class StoneOrderStateResult {
    /**
     * data : {"list":[{"customerName":"名匠一号0523","number":"2","orderDate":"2017-05-23 14:21:13","orderNum":"950548864473504036","remark":"","totalPrice":"386.00"},{"customerName":"名匠一号0523","number":"2","orderDate":"2017-05-23 14:26:37","orderNum":"800548864797165036","remark":"","totalPrice":"366.00"},{"customerName":"名匠一号0523","number":"3","orderDate":"2017-05-23 14:28:34","orderNum":"960548864914087036","remark":"","totalPrice":"856.00"},{"customerName":"名匠一号0523","number":"3","orderDate":"2017-05-23 14:29:17","orderNum":"290548864957228036","remark":"","totalPrice":"856.00"},{"customerName":"世爵百年KD4739-00","number":"3","orderDate":"2017-05-23 14:31:51","orderNum":"630548865111273036","remark":"","totalPrice":"856.00"},{"customerName":"广州绿翠-302号店","number":"2","orderDate":"2017-05-23 15:58:29","orderNum":"970548870309696036","remark":"","totalPrice":"19493.00"},{"customerName":"世爵百年KD4742-NJ","number":"3","orderDate":"2017-05-23 15:59:41","orderNum":"540548870381805036","remark":"","totalPrice":"29795.00"},{"customerName":"世爵百年KD4742-NJ","number":"4","orderDate":"2017-05-23 16:02:38","orderNum":"520548870558463036","remark":"","totalPrice":"37875.00"},{"customerName":"名匠一号0523","number":"2","orderDate":"2017-05-23 16:07:12","orderNum":"950548870832774036","remark":"","totalPrice":"19392.00"},{"customerName":"名匠一号0523","number":"2","orderDate":"2017-05-23 16:08:21","orderNum":"800548870901667036","remark":"","totalPrice":"19392.00"}],"list_count":"24"}
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
         * list : [{"customerName":"名匠一号0523","number":"2","orderDate":"2017-05-23 14:21:13","orderNum":"950548864473504036","remark":"","totalPrice":"386.00"},{"customerName":"名匠一号0523","number":"2","orderDate":"2017-05-23 14:26:37","orderNum":"800548864797165036","remark":"","totalPrice":"366.00"},{"customerName":"名匠一号0523","number":"3","orderDate":"2017-05-23 14:28:34","orderNum":"960548864914087036","remark":"","totalPrice":"856.00"},{"customerName":"名匠一号0523","number":"3","orderDate":"2017-05-23 14:29:17","orderNum":"290548864957228036","remark":"","totalPrice":"856.00"},{"customerName":"世爵百年KD4739-00","number":"3","orderDate":"2017-05-23 14:31:51","orderNum":"630548865111273036","remark":"","totalPrice":"856.00"},{"customerName":"广州绿翠-302号店","number":"2","orderDate":"2017-05-23 15:58:29","orderNum":"970548870309696036","remark":"","totalPrice":"19493.00"},{"customerName":"世爵百年KD4742-NJ","number":"3","orderDate":"2017-05-23 15:59:41","orderNum":"540548870381805036","remark":"","totalPrice":"29795.00"},{"customerName":"世爵百年KD4742-NJ","number":"4","orderDate":"2017-05-23 16:02:38","orderNum":"520548870558463036","remark":"","totalPrice":"37875.00"},{"customerName":"名匠一号0523","number":"2","orderDate":"2017-05-23 16:07:12","orderNum":"950548870832774036","remark":"","totalPrice":"19392.00"},{"customerName":"名匠一号0523","number":"2","orderDate":"2017-05-23 16:08:21","orderNum":"800548870901667036","remark":"","totalPrice":"19392.00"}]
         * list_count : 24
         */

        private String list_count;
        private List<ListBean> list;

        public String getList_count() {
            return list_count;
        }

        public void setList_count(String list_count) {
            this.list_count = list_count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * customerName : 名匠一号0523
             * number : 2
             * orderDate : 2017-05-23 14:21:13
             * orderNum : 950548864473504036
             * remark :
             * totalPrice : 386.00
             */

            private String customerName;
            private String number;
            private String orderDate;
            private String id;
            private String orderNum;
            private String remark;
            private String totalPrice;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

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

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(String totalPrice) {
                this.totalPrice = totalPrice;
            }
        }
    }
}
