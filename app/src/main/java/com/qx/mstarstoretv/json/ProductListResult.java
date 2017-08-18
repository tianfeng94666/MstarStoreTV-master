package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */
public class ProductListResult  {


    /**
     * data : {"modelList":[{"baseInfo":"类型:皮带头;手寸:1","needPayPrice":"0","number":2,"stonePrice":"0","modelId":"21","price":"0","id":"124","pic":"http://appapi.fanerweb.com/Uploads/Pics/2016-09-21/577dd3b0N5baaf195.jpg","title":"18K金气质锁骨链群镶钻石链牌 弯弯的月亮女款K金项链吊坠 18K玫瑰金","info":"主石(类型:方钻,数量:1粒,规格:0~30,形状:马鞍,颜色:H+,纯度:SI)"}],"orderInfo":{"confirmDate":"2016-12-05 11:46:46","otherInfo":"成色:; 质量等级:; 字印:2222wa; 金价:0/g; 件数:1","needPayPrice":"0","address":"辽宁省 丹东市 振兴区 哄哄","totalPrice":"0","orderStatusTitle":"待生产","orderNum":"AP2016112310378","orderDate":"2016-11-23 10:32:48","customerName":"旺道-0112"}}
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
         * modelList : [{"baseInfo":"类型:皮带头;手寸:1","needPayPrice":"0","number":2,"stonePrice":"0","modelId":"21","price":"0","id":"124","pic":"http://appapi.fanerweb.com/Uploads/Pics/2016-09-21/577dd3b0N5baaf195.jpg","title":"18K金气质锁骨链群镶钻石链牌 弯弯的月亮女款K金项链吊坠 18K玫瑰金","info":"主石(类型:方钻,数量:1粒,规格:0~30,形状:马鞍,颜色:H+,纯度:SI)"}]
         * orderInfo : {"confirmDate":"2016-12-05 11:46:46","otherInfo":"成色:; 质量等级:; 字印:2222wa; 金价:0/g; 件数:1","needPayPrice":"0","address":"辽宁省 丹东市 振兴区 哄哄","totalPrice":"0","orderStatusTitle":"待生产","orderNum":"AP2016112310378","orderDate":"2016-11-23 10:32:48","customerName":"旺道-0112"}
         */
        private List<ModelListEntity> modelList;
        private OrderInfoEntity orderInfo;

        public void setModelList(List<ModelListEntity> modelList) {
            this.modelList = modelList;
        }

        public void setOrderInfo(OrderInfoEntity orderInfo) {
            this.orderInfo = orderInfo;
        }

        public List<ModelListEntity> getModelList() {
            return modelList;
        }

        public OrderInfoEntity getOrderInfo() {
            return orderInfo;
        }



    }
}
