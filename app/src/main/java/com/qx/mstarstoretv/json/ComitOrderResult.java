package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class ComitOrderResult {

    /**
     * data : {"Ailpay":{"orderNnm":"AP170512233","out_trade_no":"310548325710836010","proName":"千禧之星订单支付:AP170512233","probody":"LM0061","total_fee":0.01},"needPayPrice":0.01,"title":"AP170512233定金支付"}
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
         * Ailpay : {"orderNnm":"AP170512233","out_trade_no":"310548325710836010","proName":"千禧之星订单支付:AP170512233","probody":"LM0061","total_fee":0.01}
         * needPayPrice : 0.01
         * title : AP170512233定金支付
         */

        private AilpayBean Ailpay;
        private String needPayPrice;
        private String title;

        public AilpayBean getAilpay() {
            return Ailpay;
        }

        public void setAilpay(AilpayBean Ailpay) {
            this.Ailpay = Ailpay;
        }

        public String getNeedPayPrice() {
            return needPayPrice;
        }

        public void setNeedPayPrice(String needPayPrice) {
            this.needPayPrice = needPayPrice;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class AilpayBean {
            /**
             * orderNnm : AP170512233
             * out_trade_no : 310548325710836010
             * proName : 千禧之星订单支付:AP170512233
             * probody : LM0061
             * total_fee : 0.01
             */

            private String orderNnm;
            private String out_trade_no;
            private String proName;
            private String probody;
            private double total_fee;

            public String getOrderNnm() {
                return orderNnm;
            }

            public void setOrderNnm(String orderNnm) {
                this.orderNnm = orderNnm;
            }

            public String getOut_trade_no() {
                return out_trade_no;
            }

            public void setOut_trade_no(String out_trade_no) {
                this.out_trade_no = out_trade_no;
            }

            public String getProName() {
                return proName;
            }

            public void setProName(String proName) {
                this.proName = proName;
            }

            public String getProbody() {
                return probody;
            }

            public void setProbody(String probody) {
                this.probody = probody;
            }

            public double getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(double total_fee) {
                this.total_fee = total_fee;
            }
        }
    }
}
