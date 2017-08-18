package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
public class HomeResult {


    /**
     * data : {"userInfo":{"mesCount":"2","userName":"xianxiaojun","headPic":"http://192.168.1.240:9112/Uploads/Pics/2016-09-20/s_57e0e36799c67.jpg"},"functionsList":[{"pic":"http://192.168.1.240:9112/functions/order.jpg","title":"版房下单"},{"pic":"http://192.168.1.240:9112/functions/product.jpg","title":"成品订单"}]}
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
         * userInfo : {"mesCount":"2","userName":"xianxiaojun","headPic":"http://192.168.1.240:9112/Uploads/Pics/2016-09-20/s_57e0e36799c67.jpg"}
         * functionsList : [{"pic":"http://192.168.1.240:9112/functions/order.jpg","title":"版房下单"},{"pic":"http://192.168.1.240:9112/functions/product.jpg","title":"成品订单"}]
         */
        private UserInfoEntity userInfo;
        private List<FunctionsListEntity> functionsList;

        public void setUserInfo(UserInfoEntity userInfo) {
            this.userInfo = userInfo;
        }

        public void setFunctionsList(List<FunctionsListEntity> functionsList) {
            this.functionsList = functionsList;
        }

        public UserInfoEntity getUserInfo() {
            return userInfo;
        }

        public List<FunctionsListEntity> getFunctionsList() {
            return functionsList;
        }

        public class UserInfoEntity {
            /**
             * mesCount : 2
             * userName : xianxiaojun
             * headPic : http://192.168.1.240:9112/Uploads/Pics/2016-09-20/s_57e0e36799c67.jpg
             */
            private String mesCount;
            private String userName;
            private String headPic;

            public void setMesCount(String mesCount) {
                this.mesCount = mesCount;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public String getMesCount() {
                return mesCount;
            }

            public String getUserName() {
                return userName;
            }

            public String getHeadPic() {
                return headPic;
            }
        }

        public class FunctionsListEntity {
            /**
             * pic : http://192.168.1.240:9112/functions/order.jpg
             * title : 版房下单
             */
            private String pic;
            private String title;
            private String keyword;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPic() {
                return pic;
            }

            public String getTitle() {
                return title;
            }
        }
    }
}
