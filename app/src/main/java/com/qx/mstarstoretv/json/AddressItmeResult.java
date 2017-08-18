package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public class AddressItmeResult {


    /**
     * data : {"provinceList":[{"name":"北京","id":"1"},{"name":"天津","id":"2"},{"name":"河北省","id":"3"},{"name":"山西省","id":"4"},{"name":"内蒙古自治区","id":"5"}],"address":{"province_id":"20","phone":"15994767200","name":"广播站","id":"4","area_id":"3230","addr":"神木林百度洞44","city_id":"319"}}
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
         * address : {"province_id":"20","phone":"15994767200","name":"广播站","id":"4","area_id":"3230","addr":"神木林百度洞44","city_id":"319"}
         */
        private List<ProvinceListEntity> provinceList;
        private AddressEntity address;

        public void setProvinceList(List<ProvinceListEntity> provinceList) {
            this.provinceList = provinceList;
        }

        public void setAddress(AddressEntity address) {
            this.address = address;
        }

        public List<ProvinceListEntity> getProvinceList() {
            return provinceList;
        }

        public AddressEntity getAddress() {
            return address;
        }

        public class ProvinceListEntity {
            /**
             * name : 北京
             * id : 1
             */
            private String name;
            private String id;

            public void setName(String name) {
                this.name = name;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public String getId() {
                return id;
            }
        }

        public class AddressEntity {
            /**
             * province_id : 20
             * phone : 15994767200
             * name : 广播站
             * id : 4
             * area_id : 3230
             * addr : 神木林百度洞44
             * city_id : 319
             */
            private String province_id;
            private String phone;
            private String name;
            private String id;
            private String area_id;
            private String addr;
            private String city_id;
            private String place;

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }

            public void setProvince_id(String province_id) {
                this.province_id = province_id;
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

            public void setArea_id(String area_id) {
                this.area_id = area_id;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getProvince_id() {
                return province_id;
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

            public String getArea_id() {
                return area_id;
            }

            public String getAddr() {
                return addr;
            }

            public String getCity_id() {
                return city_id;
            }
        }
    }
}
