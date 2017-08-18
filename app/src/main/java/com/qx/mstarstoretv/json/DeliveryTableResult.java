package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10 0010.
 */

public class DeliveryTableResult {

    /**
     * data : {"moItem":{"customerName":"世爵百年KD3999-BJ","goldPrice":225.4,"moDate":"2017-03-12 15:51:06","moNum":1703120051,"number":4,"orderNum":"AP170302298","purityName":"PT950","totalPrice":2924.03},"modelList":[{"dInfo":"基本费用:42.00 附加费用:0.00 其他费用:0.00 起版费:0.00","modelNum":"Y07326-10","pic":"http://124.172.169.117:9888/220X220/Module/Image1/90/Y07326-10.jpg","remark":"备注:","sInfo":"手寸:13  毛重:2.87 净金重:2.85 损耗:1.11","stInfo":["[主石]石号:SKL0302;数量:1;重量:0.1;金额:0.00"],"typeName":"女戒","unitPrice":755.05},{"dInfo":"基本费用:42.00 附加费用:0.00 其他费用:0.00 起版费:0.00","modelNum":"Y07326-10","pic":"http://124.172.169.117:9888/220X220/Module/Image1/90/Y07326-10.jpg","remark":"备注:","sInfo":"手寸:9  毛重:2.70 净金重:2.68 损耗:1.11","stInfo":["[主石]石号:SKL0302;数量:1;重量:0.1;金额:0.00"],"typeName":"女戒","unitPrice":712.52},{"dInfo":"基本费用:42.00 附加费用:0.00 其他费用:0.00 起版费:0.00","modelNum":"A43367","pic":"http://124.172.169.117:9888/220X220/Module/Image1/6/A43367.JPG","remark":"备注:","sInfo":"手寸:14  毛重:2.52 净金重:2.48 损耗:1.11","stInfo":["[主石]石号:SKL0302;数量:1;重量:0.177;金额:0.00"],"typeName":"女戒","unitPrice":663.63},{"dInfo":"基本费用:42.00 附加费用:0.00 其他费用:0.00 起版费:0.00","modelNum":"A32155","pic":"http://124.172.169.117:9888/220X220/Module/Image1/9/A32155.jpg","remark":"备注:","sInfo":"手寸:10  毛重:3.03 净金重:3.00 损耗:1.11","stInfo":["[主石]石号:SKL0302;数量:1;重量:0.145;金额:0.00"],"typeName":"女戒","unitPrice":792.83}]}
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

    public static class DataBean {
        /**
         * moItem : {"customerName":"世爵百年KD3999-BJ","goldPrice":225.4,"moDate":"2017-03-12 15:51:06","moNum":1703120051,"number":4,"orderNum":"AP170302298","purityName":"PT950","totalPrice":2924.03}
         * modelList : [{"dInfo":"基本费用:42.00 附加费用:0.00 其他费用:0.00 起版费:0.00","modelNum":"Y07326-10","pic":"http://124.172.169.117:9888/220X220/Module/Image1/90/Y07326-10.jpg","remark":"备注:","sInfo":"手寸:13  毛重:2.87 净金重:2.85 损耗:1.11","stInfo":["[主石]石号:SKL0302;数量:1;重量:0.1;金额:0.00"],"typeName":"女戒","unitPrice":755.05},{"dInfo":"基本费用:42.00 附加费用:0.00 其他费用:0.00 起版费:0.00","modelNum":"Y07326-10","pic":"http://124.172.169.117:9888/220X220/Module/Image1/90/Y07326-10.jpg","remark":"备注:","sInfo":"手寸:9  毛重:2.70 净金重:2.68 损耗:1.11","stInfo":["[主石]石号:SKL0302;数量:1;重量:0.1;金额:0.00"],"typeName":"女戒","unitPrice":712.52},{"dInfo":"基本费用:42.00 附加费用:0.00 其他费用:0.00 起版费:0.00","modelNum":"A43367","pic":"http://124.172.169.117:9888/220X220/Module/Image1/6/A43367.JPG","remark":"备注:","sInfo":"手寸:14  毛重:2.52 净金重:2.48 损耗:1.11","stInfo":["[主石]石号:SKL0302;数量:1;重量:0.177;金额:0.00"],"typeName":"女戒","unitPrice":663.63},{"dInfo":"基本费用:42.00 附加费用:0.00 其他费用:0.00 起版费:0.00","modelNum":"A32155","pic":"http://124.172.169.117:9888/220X220/Module/Image1/9/A32155.jpg","remark":"备注:","sInfo":"手寸:10  毛重:3.03 净金重:3.00 损耗:1.11","stInfo":["[主石]石号:SKL0302;数量:1;重量:0.145;金额:0.00"],"typeName":"女戒","unitPrice":792.83}]
         */

        private MoItemBean moItem;
        private List<ModelListBean> modelList;

        public MoItemBean getMoItem() {
            return moItem;
        }

        public void setMoItem(MoItemBean moItem) {
            this.moItem = moItem;
        }

        public List<ModelListBean> getModelList() {
            return modelList;
        }

        public void setModelList(List<ModelListBean> modelList) {
            this.modelList = modelList;
        }

        public static class MoItemBean {
            /**
             * customerName : 世爵百年KD3999-BJ
             * goldPrice : 225.4
             * moDate : 2017-03-12 15:51:06
             * moNum : 1703120051
             * number : 4
             * orderNum : AP170302298
             * purityName : PT950
             * totalPrice : 2924.03
             */
            private String customerName;
            private String goldPrice;
            private String moDate;
            private String moNum;
            private String number;
            private String orderNum;
            private String purityName;
            private String totalPrice;

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getGoldPrice() {
                return goldPrice;
            }

            public void setGoldPrice(String goldPrice) {
                this.goldPrice = goldPrice;
            }

            public String getMoDate() {
                return moDate;
            }

            public void setMoDate(String moDate) {
                this.moDate = moDate;
            }

            public String getMoNum() {
                return moNum;
            }

            public void setMoNum(String moNum) {
                this.moNum = moNum;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getPurityName() {
                return purityName;
            }

            public void setPurityName(String purityName) {
                this.purityName = purityName;
            }

            public String getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(String totalPrice) {
                this.totalPrice = totalPrice;
            }
        }

        public static class ModelListBean {
            /**
             * dInfo : 基本费用:42.00 附加费用:0.00 其他费用:0.00 起版费:0.00
             * modelNum : Y07326-10
             * pic : http://124.172.169.117:9888/220X220/Module/Image1/90/Y07326-10.jpg
             * remark : 备注:
             * sInfo : 手寸:13  毛重:2.87 净金重:2.85 损耗:1.11
             * stInfo : ["[主石]石号:SKL0302;数量:1;重量:0.1;金额:0.00"]
             * typeName : 女戒
             * unitPrice : 755.05
             */

            private String dInfo;
            private String modelNum;
            private String pic;
            private String remark;
            private String sInfo;
            private String typeName;
            private String unitPrice;
            private List<String> stInfo;
            private boolean isChoose;
            private String modNum;

            public String getModNum() {
                return modNum;
            }

            public void setModNum(String modNum) {
                this.modNum = modNum;
            }

            public boolean isChoose() {
                return isChoose;
            }

            public void setChoose(boolean choose) {
                isChoose = choose;
            }
            public String getDInfo() {
                return dInfo;
            }

            public void setDInfo(String dInfo) {
                this.dInfo = dInfo;
            }

            public String getModelNum() {
                return modelNum;
            }

            public void setModelNum(String modelNum) {
                this.modelNum = modelNum;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getSInfo() {
                return sInfo;
            }

            public void setSInfo(String sInfo) {
                this.sInfo = sInfo;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(String unitPrice) {
                this.unitPrice = unitPrice;
            }

            public List<String> getStInfo() {
                return stInfo;
            }

            public void setStInfo(List<String> stInfo) {
                this.stInfo = stInfo;
            }
        }
    }
}
