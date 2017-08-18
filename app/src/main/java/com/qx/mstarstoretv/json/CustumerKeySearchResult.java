package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class CustumerKeySearchResult {
    /**
     * data : {"list":[{"customerID":22123,"keycode":"69671","customerName":"爱恋珠宝（深圳）","customerFullName":null},{"customerID":21300,"keycode":"67920","customerName":"爱恋珠宝（深圳）16010401","customerFullName":null},{"customerID":21306,"keycode":"67921","customerName":"爱恋珠宝（深圳）16010501","customerFullName":null},{"customerID":21472,"keycode":"68027","customerName":"爱恋珠宝（深圳）16030201","customerFullName":null},{"customerID":21581,"keycode":"68926","customerName":"爱恋珠宝（深圳）16031803","customerFullName":null},{"customerID":21808,"keycode":"69111","customerName":"爱恋珠宝（深圳）16042207","customerFullName":null},{"customerID":21823,"keycode":"69121","customerName":"爱恋珠宝（深圳）16042901W","customerFullName":null},{"customerID":21831,"keycode":"69128","customerName":"爱恋珠宝（深圳）16050501","customerFullName":null},{"customerID":21876,"keycode":"69169","customerName":"爱恋珠宝（深圳）16051301W","customerFullName":null},{"customerID":21896,"keycode":"69188","customerName":"爱恋珠宝（深圳）16051806A","customerFullName":null},{"customerID":21904,"keycode":"69196","customerName":"爱恋珠宝（深圳）16052001W","customerFullName":null},{"customerID":21952,"keycode":"69242","customerName":"爱恋珠宝（深圳）16053002W","customerFullName":null},{"customerID":21984,"keycode":"69267","customerName":"爱恋珠宝（深圳）16061302W","customerFullName":null},{"customerID":22033,"keycode":"69310","customerName":"爱恋珠宝（深圳）16062102W","customerFullName":null},{"customerID":22043,"keycode":"69618","customerName":"爱恋珠宝（深圳）16062301","customerFullName":null},{"customerID":22082,"keycode":"69644","customerName":"爱恋珠宝(深圳)16063001W承德店","customerFullName":null},{"customerID":22087,"keycode":"69649","customerName":"爱恋珠宝(深圳)16070102","customerFullName":null},{"customerID":22112,"keycode":"69661","customerName":"爱恋珠宝（深圳）16070502","customerFullName":null},{"customerID":22166,"keycode":"69709","customerName":"爱恋珠宝（深圳）16070508","customerFullName":null},{"customerID":22144,"keycode":"69691","customerName":"爱恋珠宝（深圳）16071201W","customerFullName":null}],"list_count":190}
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
         * list : [{"customerID":22123,"keycode":"69671","customerName":"爱恋珠宝（深圳）","customerFullName":null},{"customerID":21300,"keycode":"67920","customerName":"爱恋珠宝（深圳）16010401","customerFullName":null},{"customerID":21306,"keycode":"67921","customerName":"爱恋珠宝（深圳）16010501","customerFullName":null},{"customerID":21472,"keycode":"68027","customerName":"爱恋珠宝（深圳）16030201","customerFullName":null},{"customerID":21581,"keycode":"68926","customerName":"爱恋珠宝（深圳）16031803","customerFullName":null},{"customerID":21808,"keycode":"69111","customerName":"爱恋珠宝（深圳）16042207","customerFullName":null},{"customerID":21823,"keycode":"69121","customerName":"爱恋珠宝（深圳）16042901W","customerFullName":null},{"customerID":21831,"keycode":"69128","customerName":"爱恋珠宝（深圳）16050501","customerFullName":null},{"customerID":21876,"keycode":"69169","customerName":"爱恋珠宝（深圳）16051301W","customerFullName":null},{"customerID":21896,"keycode":"69188","customerName":"爱恋珠宝（深圳）16051806A","customerFullName":null},{"customerID":21904,"keycode":"69196","customerName":"爱恋珠宝（深圳）16052001W","customerFullName":null},{"customerID":21952,"keycode":"69242","customerName":"爱恋珠宝（深圳）16053002W","customerFullName":null},{"customerID":21984,"keycode":"69267","customerName":"爱恋珠宝（深圳）16061302W","customerFullName":null},{"customerID":22033,"keycode":"69310","customerName":"爱恋珠宝（深圳）16062102W","customerFullName":null},{"customerID":22043,"keycode":"69618","customerName":"爱恋珠宝（深圳）16062301","customerFullName":null},{"customerID":22082,"keycode":"69644","customerName":"爱恋珠宝(深圳)16063001W承德店","customerFullName":null},{"customerID":22087,"keycode":"69649","customerName":"爱恋珠宝(深圳)16070102","customerFullName":null},{"customerID":22112,"keycode":"69661","customerName":"爱恋珠宝（深圳）16070502","customerFullName":null},{"customerID":22166,"keycode":"69709","customerName":"爱恋珠宝（深圳）16070508","customerFullName":null},{"customerID":22144,"keycode":"69691","customerName":"爱恋珠宝（深圳）16071201W","customerFullName":null}]
         * list_count : 190
         */

        private int list_count;
        private List<CustomerEntity> list;

        public int getList_count() {
            return list_count;
        }

        public void setList_count(int list_count) {
            this.list_count = list_count;
        }

        public List<CustomerEntity> getList() {
            return list;
        }

        public void setList(List<CustomerEntity> list) {
            this.list = list;
        }


    }
}
