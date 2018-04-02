package com.qx.mstarstoretv.json;

import com.qx.mstarstoretv.bean.Type;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class SettingResult implements Serializable{

    /**
     * data : {"headPic":"http://appapi0.fanerweb.com/images/imageForApi/defaultHead.jpg","isMasterAccount":0,"isShowOriginalPrice":0,"isShowPrice":1,"modelAddtion":0,"phone":"13058023844","stoneAddtion":0,"userName":"tianfeng3"}
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

    public static class DataBean implements Serializable{
        /**
         * headPic : http://appapi0.fanerweb.com/images/imageForApi/defaultHead.jpg
         * isMasterAccount : 0
         * isShowOriginalPrice : 0
         * isShowPrice : 1
         * modelAddtion : 0
         * phone : 13058023844
         * stoneAddtion : 0
         * userName : tianfeng3
         */

        private String headPic;
        private int isMasterAccount;
        private int isShowOriginalPrice;
        private int isShowPrice;
        private int memberAreaId;
        private String modelAddtion;
        private String phone;
        private String stoneAddtion;
        private String stoneAddtion1;
        private String stoneAddtion2;
        private String userName;
        private String address;
        private String userArea;
        private List<Type> memberArealist;

        public int getMemberAreaId() {
            return memberAreaId;
        }

        public void setMemberAreaId(int memberAreaId) {
            this.memberAreaId = memberAreaId;
        }

        public String getUserArea() {
            return userArea;
        }

        public void setUserArea(String userArea) {
            this.userArea = userArea;
        }

        public List<Type> getMemberArealist() {
            return memberArealist;
        }

        public void setMemberArealist(List<Type> memberArealist) {
            this.memberArealist = memberArealist;
        }

        public String getStoneAddtion1() {
            return stoneAddtion1;
        }

        public void setStoneAddtion1(String stoneAddtion1) {
            this.stoneAddtion1 = stoneAddtion1;
        }

        public String getStoneAddtion2() {
            return stoneAddtion2;
        }

        public void setStoneAddtion2(String stoneAddtion2) {
            this.stoneAddtion2 = stoneAddtion2;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getIsMasterAccount() {
            return isMasterAccount;
        }

        public void setIsMasterAccount(int isMasterAccount) {
            this.isMasterAccount = isMasterAccount;
        }

        public int getIsShowOriginalPrice() {
            return isShowOriginalPrice;
        }

        public void setIsShowOriginalPrice(int isShowOriginalPrice) {
            this.isShowOriginalPrice = isShowOriginalPrice;
        }

        public int getIsShowPrice() {
            return isShowPrice;
        }

        public void setIsShowPrice(int isShowPrice) {
            this.isShowPrice = isShowPrice;
        }

        public String getModelAddtion() {
            return modelAddtion;
        }

        public void setModelAddtion(String modelAddtion) {
            this.modelAddtion = modelAddtion;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStoneAddtion() {
            return stoneAddtion;
        }

        public void setStoneAddtion(String stoneAddtion) {
            this.stoneAddtion = stoneAddtion;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
