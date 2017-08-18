package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */
public class InvocieResult {


    /**
     * data : {"invoiceType":[{"id":1,"title":"明细"},{"id":2,"title":"珠宝"}]}
     * response :
     * error : 0
     * message :
     */
    private DataEntity data;
    private String response;
    private int error;
    private String message;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setError(int error) {
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

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public class DataEntity {
        /**
         * invoiceType : [{"id":1,"title":"明细"},{"id":2,"title":"珠宝"}]
         */
        private List<InvoiceTypeEntity> invoiceType;

        public void setInvoiceType(List<InvoiceTypeEntity> invoiceType) {
            this.invoiceType = invoiceType;
        }

        public List<InvoiceTypeEntity> getInvoiceType() {
            return invoiceType;
        }

        public class InvoiceTypeEntity {
            /**
             * id : 1
             * title : 明细
             */
            private int id;
            private String title;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }
    }
}
