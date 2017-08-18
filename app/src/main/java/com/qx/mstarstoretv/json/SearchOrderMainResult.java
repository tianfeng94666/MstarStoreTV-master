package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class SearchOrderMainResult {

    /**
     * response :
     * error : 0
     * message :
     * data : {"orderProduce":{"orderInfo":{"orderNum":"AP170310360","customerName":"世爵百年KD4117-NJ","orderDate":"2017-03-10 13:35:16","confirmDate":"2017-03-10 15:13:58","orderNote":"封副石！！！","otherInfo":"成色:; 质量等级:; 金价:204.5/g; 件数:2","totalPrice":null,"needPayPrice":null,"orderStatusTitle":"分厂加工","address":null,"invoiceTitle":null,"invoiceType":null},"modelList":[{"id":null,"modelId":null,"title":"A40261","pic":"http://124.172.169.117:9888/","baseInfo":"类型:女戒;手寸:12","stonePrice":null,"price":null,"needPayPrice":null,"number":1,"info":"主石(类型:钻石,数量:1粒,规格:6',形状:圆形,自带石头);备注(改镶6分)"},{"id":null,"modelId":null,"title":"A44183","pic":"http://124.172.169.117:9888/","baseInfo":"类型:女戒;手寸:13","stonePrice":null,"price":null,"needPayPrice":null,"number":1,"info":"主石(类型:钻石,数量:1粒,规格:7',形状:圆形,自带石头)"}]},"orderSended":{"recList":[{"recNum":1703160050,"customerName":"世爵百年KD4117-NJ","recDate":"2017-03-16 16:29:38","purityName":"Au750白","orderDate":"2017-03-10 13:35:16","number":2,"totalPrice":726.51,"moList":[{"recNum":1703160050,"moNum":1703160039,"moDate":"2017-03-16 16:08:51","number":2,"totalPrice":726.51}]}]}}
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
         * orderProduce : {"orderInfo":{"orderNum":"AP170310360","customerName":"世爵百年KD4117-NJ","orderDate":"2017-03-10 13:35:16","confirmDate":"2017-03-10 15:13:58","orderNote":"封副石！！！","otherInfo":"成色:; 质量等级:; 金价:204.5/g; 件数:2","totalPrice":null,"needPayPrice":null,"orderStatusTitle":"分厂加工","address":null,"invoiceTitle":null,"invoiceType":null},"modelList":[{"id":null,"modelId":null,"title":"A40261","pic":"http://124.172.169.117:9888/","baseInfo":"类型:女戒;手寸:12","stonePrice":null,"price":null,"needPayPrice":null,"number":1,"info":"主石(类型:钻石,数量:1粒,规格:6',形状:圆形,自带石头);备注(改镶6分)"},{"id":null,"modelId":null,"title":"A44183","pic":"http://124.172.169.117:9888/","baseInfo":"类型:女戒;手寸:13","stonePrice":null,"price":null,"needPayPrice":null,"number":1,"info":"主石(类型:钻石,数量:1粒,规格:7',形状:圆形,自带石头)"}]}
         * orderSended : {"recList":[{"recNum":1703160050,"customerName":"世爵百年KD4117-NJ","recDate":"2017-03-16 16:29:38","purityName":"Au750白","orderDate":"2017-03-10 13:35:16","number":2,"totalPrice":726.51,"moList":[{"recNum":1703160050,"moNum":1703160039,"moDate":"2017-03-16 16:08:51","number":2,"totalPrice":726.51}]}]}
         */

        private OrderProduceBean orderProduce;
        private OrderSendedBean orderSended;

        public OrderProduceBean getOrderProduce() {
            return orderProduce;
        }

        public void setOrderProduce(OrderProduceBean orderProduce) {
            this.orderProduce = orderProduce;
        }

        public OrderSendedBean getOrderSended() {
            return orderSended;
        }

        public void setOrderSended(OrderSendedBean orderSended) {
            this.orderSended = orderSended;
        }

        public static class OrderProduceBean {
            /**
             * orderInfo : {"orderNum":"AP170310360","customerName":"世爵百年KD4117-NJ","orderDate":"2017-03-10 13:35:16","confirmDate":"2017-03-10 15:13:58","orderNote":"封副石！！！","otherInfo":"成色:; 质量等级:; 金价:204.5/g; 件数:2","totalPrice":null,"needPayPrice":null,"orderStatusTitle":"分厂加工","address":null,"invoiceTitle":null,"invoiceType":null}
             * modelList : [{"id":null,"modelId":null,"title":"A40261","pic":"http://124.172.169.117:9888/","baseInfo":"类型:女戒;手寸:12","stonePrice":null,"price":null,"needPayPrice":null,"number":1,"info":"主石(类型:钻石,数量:1粒,规格:6',形状:圆形,自带石头);备注(改镶6分)"},{"id":null,"modelId":null,"title":"A44183","pic":"http://124.172.169.117:9888/","baseInfo":"类型:女戒;手寸:13","stonePrice":null,"price":null,"needPayPrice":null,"number":1,"info":"主石(类型:钻石,数量:1粒,规格:7',形状:圆形,自带石头)"}]
             */

            private OrderInfoEntity orderInfo;
            private List<ModelListEntity> modelList;

            public OrderInfoEntity getOrderInfo() {
                return orderInfo;
            }

            public void setOrderInfo(OrderInfoEntity orderInfo) {
                this.orderInfo = orderInfo;
            }

            public List<ModelListEntity> getModelList() {
                return modelList;
            }

            public void setModelList(List<ModelListEntity> modelList) {
                this.modelList = modelList;
            }



        }

        public static class OrderSendedBean {
            private List<RecListBean> recList;

            public List<RecListBean> getRecList() {
                return recList;
            }

            public void setRecList(List<RecListBean> recList) {
                this.recList = recList;
            }


        }
    }
}
