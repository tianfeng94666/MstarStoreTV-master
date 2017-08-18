package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public class AddressResult {


    /**
     * data : {"provinceList":[{"name":"北京","id":"1"},{"name":"天津","id":"2"},{"name":"河北省","id":"3"},{"name":"山西省","id":"4"},{"name":"内蒙古自治区","id":"5"}]}
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
         * provinceList : [{"name":"北京","id":"1"},{"name":"天津","id":"2"},{"name":"河北省","id":"3"},{"name":"山西省","id":"4"},{"name":"内蒙古自治区","id":"5"}]
         */
        private List<ProvinceListEntity> provinceList;

        public void setProvinceList(List<ProvinceListEntity> provinceList) {
            this.provinceList = provinceList;
        }

        public List<ProvinceListEntity> getProvinceList() {
            return provinceList;
        }


    }
}
