package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */
public class PriceResult {

    /**
     * data : {"priceList":[{"price":207743.358,"id":"19"},{"price":139737.208,"id":"18"}]}
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
         * priceList : [{"price":207743.358,"id":"19"},{"price":139737.208,"id":"18"}]
         */
        private List<PriceListEntity> priceList;

        public void setPriceList(List<PriceListEntity> priceList) {
            this.priceList = priceList;
        }

        public List<PriceListEntity> getPriceList() {
            return priceList;
        }

        public class PriceListEntity {
            /**
             * price : 207743.358
             * id : 19
             */
            private double price;
            private double needPayPrice;
            private String id;

            public double getNeedPayPrice() {
                return needPayPrice;
            }

            public void setNeedPayPrice(double needPayPrice) {
                this.needPayPrice = needPayPrice;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public void setId(String id) {
                this.id = id;
            }

            public double getPrice() {
                return price;
            }

            public String getId() {
                return id;
            }
        }
    }
}
