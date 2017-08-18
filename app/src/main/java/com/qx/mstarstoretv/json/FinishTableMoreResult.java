package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10 0010.
 */

public class FinishTableMoreResult {

    /**
     * data : {"recItem":{"accountID":"201703","customerName":"合肥鑫宝","number":1,"orderDate":"2017-03-01 16:00:48","orderNum":"AP170301290","purityName":"G750白","recDate":"2017-03-10 14:17:35","recNum":1703100042,"recOperator":"吴平凤","totalPrice":685.17},"recMaterials":{"list":[{"RecGoldPrice":"金价","RecMMoney":"金额","recMRatio":"损耗","recMWeight":"净金重","typeName":"品名"},{"RecGoldPrice":210.2,"RecMMoney":619.17,"recMRatio":1.12,"recMWeight":2.63,"typeName":"女戒"},{"RecGoldPrice":"","RecMMoney":"","recMRatio":1.12,"recMWeight":"","typeName":"小计"}],"moneySum":619.17,"title":"材料"},"recOtherProcessExpenseses":{"list":[{"enChase":"品名","recOMoney":"金额","recOQuantity":"数量","recOUPrice":"工费"}],"moneySum":0,"title":"其他加工费"},"recProcessExpenseses":{"list":[{"recPFeeAddTotal":"超额工费","recPMoney":"金额","recPQuantity":"件数","recPUPrice":"单件工费","sampleFee":"起版费","typeName":"品名"},{"recPFeeAddTotal":30,"recPMoney":66,"recPQuantity":1,"recPUPrice":36,"sampleFee":0,"typeName":"女戒"},{"recPFeeAddTotal":"","recPMoney":"","recPQuantity":"","recPUPrice":"","sampleFee":"","typeName":"小计"}],"moneySum":66,"title":"加工费"},"recStones":{"list":[{"comeFrom":"来源","recSMoney":"金额","recSQuantity":"数量","recSStoneSN":"编号","recSUPrice":"单价","recSWeight":"重量","stoneTypeName":"石名"}],"moneySum":0,"title":"宝石"}}
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
         * recItem : {"accountID":"201703","customerName":"合肥鑫宝","number":1,"orderDate":"2017-03-01 16:00:48","orderNum":"AP170301290","purityName":"G750白","recDate":"2017-03-10 14:17:35","recNum":1703100042,"recOperator":"吴平凤","totalPrice":685.17}
         * recMaterials : {"list":[{"RecGoldPrice":"金价","RecMMoney":"金额","recMRatio":"损耗","recMWeight":"净金重","typeName":"品名"},{"RecGoldPrice":210.2,"RecMMoney":619.17,"recMRatio":1.12,"recMWeight":2.63,"typeName":"女戒"},{"RecGoldPrice":"","RecMMoney":"","recMRatio":1.12,"recMWeight":"","typeName":"小计"}],"moneySum":619.17,"title":"材料"}
         * recOtherProcessExpenseses : {"list":[{"enChase":"品名","recOMoney":"金额","recOQuantity":"数量","recOUPrice":"工费"}],"moneySum":0,"title":"其他加工费"}
         * recProcessExpenseses : {"list":[{"recPFeeAddTotal":"超额工费","recPMoney":"金额","recPQuantity":"件数","recPUPrice":"单件工费","sampleFee":"起版费","typeName":"品名"},{"recPFeeAddTotal":30,"recPMoney":66,"recPQuantity":1,"recPUPrice":36,"sampleFee":0,"typeName":"女戒"},{"recPFeeAddTotal":"","recPMoney":"","recPQuantity":"","recPUPrice":"","sampleFee":"","typeName":"小计"}],"moneySum":66,"title":"加工费"}
         * recStones : {"list":[{"comeFrom":"来源","recSMoney":"金额","recSQuantity":"数量","recSStoneSN":"编号","recSUPrice":"单价","recSWeight":"重量","stoneTypeName":"石名"}],"moneySum":0,"title":"宝石"}
         */

        private RecItemBean recItem;
        private RecMaterialsBean recMaterials;
        private RecOtherProcessExpensesesBean recOtherProcessExpenseses;
        private RecProcessExpensesesBean recProcessExpenseses;
        private RecStonesBean recStones;

        public RecItemBean getRecItem() {
            return recItem;
        }

        public void setRecItem(RecItemBean recItem) {
            this.recItem = recItem;
        }

        public RecMaterialsBean getRecMaterials() {
            return recMaterials;
        }

        public void setRecMaterials(RecMaterialsBean recMaterials) {
            this.recMaterials = recMaterials;
        }

        public RecOtherProcessExpensesesBean getRecOtherProcessExpenseses() {
            return recOtherProcessExpenseses;
        }

        public void setRecOtherProcessExpenseses(RecOtherProcessExpensesesBean recOtherProcessExpenseses) {
            this.recOtherProcessExpenseses = recOtherProcessExpenseses;
        }

        public RecProcessExpensesesBean getRecProcessExpenseses() {
            return recProcessExpenseses;
        }

        public void setRecProcessExpenseses(RecProcessExpensesesBean recProcessExpenseses) {
            this.recProcessExpenseses = recProcessExpenseses;
        }

        public RecStonesBean getRecStones() {
            return recStones;
        }

        public void setRecStones(RecStonesBean recStones) {
            this.recStones = recStones;
        }

        public static class RecItemBean {
            /**
             * accountID : 201703
             * customerName : 合肥鑫宝
             * number : 1
             * orderDate : 2017-03-01 16:00:48
             * orderNum : AP170301290
             * purityName : G750白
             * recDate : 2017-03-10 14:17:35
             * recNum : 1703100042
             * recOperator : 吴平凤
             * totalPrice : 685.17
             */

            private String accountID;
            private String customerName;
            private String number;
            private String orderDate;
            private String orderNum;
            private String purityName;
            private String recDate;
            private String recNum;
            private String recOperator;
            private String totalPrice;

            public String getAccountID() {
                return accountID;
            }

            public void setAccountID(String accountID) {
                this.accountID = accountID;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getOrderDate() {
                return orderDate;
            }

            public void setOrderDate(String orderDate) {
                this.orderDate = orderDate;
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

            public String getRecDate() {
                return recDate;
            }

            public void setRecDate(String recDate) {
                this.recDate = recDate;
            }

            public String getRecNum() {
                return recNum;
            }

            public void setRecNum(String recNum) {
                this.recNum = recNum;
            }

            public String getRecOperator() {
                return recOperator;
            }

            public void setRecOperator(String recOperator) {
                this.recOperator = recOperator;
            }

            public String getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(String totalPrice) {
                this.totalPrice = totalPrice;
            }
        }

        public static class RecMaterialsBean {
            /**
             * list : [{"RecGoldPrice":"金价","RecMMoney":"金额","recMRatio":"损耗","recMWeight":"净金重","typeName":"品名"},{"RecGoldPrice":210.2,"RecMMoney":619.17,"recMRatio":1.12,"recMWeight":2.63,"typeName":"女戒"},{"RecGoldPrice":"","RecMMoney":"","recMRatio":1.12,"recMWeight":"","typeName":"小计"}]
             * moneySum : 619.17
             * title : 材料
             */

            private double moneySum;
            private String title;
            private List<ListBean> list;

            public double getMoneySum() {
                return moneySum;
            }

            public void setMoneySum(double moneySum) {
                this.moneySum = moneySum;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * RecGoldPrice : 金价
                 * RecMMoney : 金额
                 * recMRatio : 损耗
                 * recMWeight : 净金重
                 * typeName : 品名
                 */

                private String RecGoldPrice;
                private String RecMMoney;
                private String recMRatio;
                private String recMWeight;
                private String typeName;

                public String getRecGoldPrice() {
                    return RecGoldPrice;
                }

                public void setRecGoldPrice(String RecGoldPrice) {
                    this.RecGoldPrice = RecGoldPrice;
                }

                public String getRecMMoney() {
                    return RecMMoney;
                }

                public void setRecMMoney(String RecMMoney) {
                    this.RecMMoney = RecMMoney;
                }

                public String getRecMRatio() {
                    return recMRatio;
                }

                public void setRecMRatio(String recMRatio) {
                    this.recMRatio = recMRatio;
                }

                public String getRecMWeight() {
                    return recMWeight;
                }

                public void setRecMWeight(String recMWeight) {
                    this.recMWeight = recMWeight;
                }

                public String getTypeName() {
                    return typeName;
                }

                public void setTypeName(String typeName) {
                    this.typeName = typeName;
                }
            }
        }

        public static class RecOtherProcessExpensesesBean {
            /**
             * list : [{"enChase":"品名","recOMoney":"金额","recOQuantity":"数量","recOUPrice":"工费"}]
             * moneySum : 0
             * title : 其他加工费
             */

            private String moneySum;
            private String title;
            private List<ListBeanX> list;

            public String getMoneySum() {
                return moneySum;
            }

            public void setMoneySum(String moneySum) {
                this.moneySum = moneySum;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ListBeanX> getList() {
                return list;
            }

            public void setList(List<ListBeanX> list) {
                this.list = list;
            }

            public static class ListBeanX {
                /**
                 * enChase : 品名
                 * recOMoney : 金额
                 * recOQuantity : 数量
                 * recOUPrice : 工费
                 */

                private String enChase;
                private String recOMoney;
                private String recOQuantity;
                private String recOUPrice;

                public String getEnChase() {
                    return enChase;
                }

                public void setEnChase(String enChase) {
                    this.enChase = enChase;
                }

                public String getRecOMoney() {
                    return recOMoney;
                }

                public void setRecOMoney(String recOMoney) {
                    this.recOMoney = recOMoney;
                }

                public String getRecOQuantity() {
                    return recOQuantity;
                }

                public void setRecOQuantity(String recOQuantity) {
                    this.recOQuantity = recOQuantity;
                }

                public String getRecOUPrice() {
                    return recOUPrice;
                }

                public void setRecOUPrice(String recOUPrice) {
                    this.recOUPrice = recOUPrice;
                }
            }
        }

        public static class RecProcessExpensesesBean {
            /**
             * list : [{"recPFeeAddTotal":"超额工费","recPMoney":"金额","recPQuantity":"件数","recPUPrice":"单件工费","sampleFee":"起版费","typeName":"品名"},{"recPFeeAddTotal":30,"recPMoney":66,"recPQuantity":1,"recPUPrice":36,"sampleFee":0,"typeName":"女戒"},{"recPFeeAddTotal":"","recPMoney":"","recPQuantity":"","recPUPrice":"","sampleFee":"","typeName":"小计"}]
             * moneySum : 66
             * title : 加工费
             */

            private String moneySum;
            private String title;
            private List<ListBeanXX> list;

            public String getMoneySum() {
                return moneySum;
            }

            public void setMoneySum(String moneySum) {
                this.moneySum = moneySum;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ListBeanXX> getList() {
                return list;
            }

            public void setList(List<ListBeanXX> list) {
                this.list = list;
            }

            public static class ListBeanXX {
                /**
                 * recPFeeAddTotal : 超额工费
                 * recPMoney : 金额
                 * recPQuantity : 件数
                 * recPUPrice : 单件工费
                 * sampleFee : 起版费
                 * typeName : 品名
                 */

                private String recPFeeAddTotal;
                private String recPMoney;
                private String recPQuantity;
                private String recPUPrice;
                private String sampleFee;
                private String typeName;

                public String getRecPFeeAddTotal() {
                    return recPFeeAddTotal;
                }

                public void setRecPFeeAddTotal(String recPFeeAddTotal) {
                    this.recPFeeAddTotal = recPFeeAddTotal;
                }

                public String getRecPMoney() {
                    return recPMoney;
                }

                public void setRecPMoney(String recPMoney) {
                    this.recPMoney = recPMoney;
                }

                public String getRecPQuantity() {
                    return recPQuantity;
                }

                public void setRecPQuantity(String recPQuantity) {
                    this.recPQuantity = recPQuantity;
                }

                public String getRecPUPrice() {
                    return recPUPrice;
                }

                public void setRecPUPrice(String recPUPrice) {
                    this.recPUPrice = recPUPrice;
                }

                public String getSampleFee() {
                    return sampleFee;
                }

                public void setSampleFee(String sampleFee) {
                    this.sampleFee = sampleFee;
                }

                public String getTypeName() {
                    return typeName;
                }

                public void setTypeName(String typeName) {
                    this.typeName = typeName;
                }
            }
        }

        public static class RecStonesBean {
            /**
             * list : [{"comeFrom":"来源","recSMoney":"金额","recSQuantity":"数量","recSStoneSN":"编号","recSUPrice":"单价","recSWeight":"重量","stoneTypeName":"石名"}]
             * moneySum : 0
             * title : 宝石
             */

            private String moneySum;
            private String title;
            private List<ListBeanXXX> list;

            public String getMoneySum() {
                return moneySum;
            }

            public void setMoneySum(String moneySum) {
                this.moneySum = moneySum;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ListBeanXXX> getList() {
                return list;
            }

            public void setList(List<ListBeanXXX> list) {
                this.list = list;
            }

            public static class ListBeanXXX {
                /**
                 * comeFrom : 来源
                 * recSMoney : 金额
                 * recSQuantity : 数量
                 * recSStoneSN : 编号
                 * recSUPrice : 单价
                 * recSWeight : 重量
                 * stoneTypeName : 石名
                 */

                private String comeFrom;
                private String recSMoney;
                private String recSQuantity;
                private String recSStoneSN;
                private String recSUPrice;
                private String recSWeight;
                private String stoneTypeName;

                public String getComeFrom() {
                    return comeFrom;
                }

                public void setComeFrom(String comeFrom) {
                    this.comeFrom = comeFrom;
                }

                public String getRecSMoney() {
                    return recSMoney;
                }

                public void setRecSMoney(String recSMoney) {
                    this.recSMoney = recSMoney;
                }

                public String getRecSQuantity() {
                    return recSQuantity;
                }

                public void setRecSQuantity(String recSQuantity) {
                    this.recSQuantity = recSQuantity;
                }

                public String getRecSStoneSN() {
                    return recSStoneSN;
                }

                public void setRecSStoneSN(String recSStoneSN) {
                    this.recSStoneSN = recSStoneSN;
                }

                public String getRecSUPrice() {
                    return recSUPrice;
                }

                public void setRecSUPrice(String recSUPrice) {
                    this.recSUPrice = recSUPrice;
                }

                public String getRecSWeight() {
                    return recSWeight;
                }

                public void setRecSWeight(String recSWeight) {
                    this.recSWeight = recSWeight;
                }

                public String getStoneTypeName() {
                    return stoneTypeName;
                }

                public void setStoneTypeName(String stoneTypeName) {
                    this.stoneTypeName = stoneTypeName;
                }
            }
        }
    }
}
