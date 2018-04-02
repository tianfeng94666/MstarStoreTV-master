package com.qx.mstarstoretv.json;

import com.qx.mstarstoretv.bean.Type;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class GetAddressResult {

    /**
     * response :
     * error : 0
     * message :
     * data : {"address":{"id":0,"name":"自提","addr":"广东省 深圳市罗湖区 水贝二路 19栋","phone":"011236569","isDefault":0},"DefaultCustomer":{"customerID":23720,"keycode":"71659","customerName":"朱绍斌1","customerFullName":null}}
     */

    private String response;
    private int error;
    private String message;
    private DataBean data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : {"id":0,"name":"自提","addr":"广东省 深圳市罗湖区 水贝二路 19栋","phone":"011236569","isDefault":0}
         * DefaultCustomer : {"customerID":23720,"keycode":"71659","customerName":"朱绍斌1","customerFullName":null}
         */

        private AddressEntity address;
        private CustomerEntity DefaultCustomer;
        private String IsMasterAccount;
        private int IsHaveSelectArea;
        private List<Type> memberArealist;

        public List<Type> getMemberArealist() {
            return memberArealist;
        }

        public void setMemberArealist(List<Type> memberArealist) {
            this.memberArealist = memberArealist;
        }

        public int getIsHaveSelectArea() {
            return IsHaveSelectArea;
        }

        public void setIsHaveSelectArea(int isHaveSelectArea) {
            IsHaveSelectArea = isHaveSelectArea;
        }

        public String getIsMasterAccount() {
            return IsMasterAccount;
        }

        public void setIsMasterAccount(String isMasterAccount) {
            IsMasterAccount = isMasterAccount;
        }

        public AddressEntity getAddress() {
            return address;
        }

        public void setAddress(AddressEntity address) {
            this.address = address;
        }

        public CustomerEntity getDefaultCustomer() {
            return DefaultCustomer;
        }

        public void setDefaultCustomer(CustomerEntity DefaultCustomer) {
            this.DefaultCustomer = DefaultCustomer;
        }


    }
}
