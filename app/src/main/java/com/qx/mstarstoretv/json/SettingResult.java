package com.qx.mstarstoretv.json;

import java.io.Serializable;

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
        private String modelAddtion;
        private String phone;
        private String stoneAddtion;
        private String userName;
        private String address;

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
