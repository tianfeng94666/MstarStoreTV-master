package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */
public class OrderListResult {


    /**
     * data : {"modelColor":[{"id":"1","title":"PT900"},{"id":"2","title":"PT950"},{"id":"3","title":"Pd950"},{"id":"4","title":"G18K黄"},{"id":"5","title":"G18K白"},{"id":"6","title":"G18K玫瑰金"},{"id":"7","title":"G18K玫瑰金分色"},{"id":"8","title":"Au750白"},{"id":"9","title":"G750白"},{"id":"10","title":"G14K白"},{"id":"11","title":"G14K黄"},{"id":"12","title":"G9K白"},{"id":"13","title":"G9K黄"},{"id":"14","title":"925银"},{"id":"15","title":"G750分色"},{"id":"16","title":"G750电分色"},{"id":"17","title":"PT900足金"},{"id":"18","title":"G750玫瑰金"},{"id":"19","title":"AU750玫瑰金分色"},{"id":"20","title":"G750玫瑰金分色"},{"id":"21","title":"G750玫瑰金电分色"},{"id":"22","title":"G750黄"},{"id":"23","title":"G18k玫瑰金分色"},{"id":"24","title":"Pd990"},{"id":"25","title":"G18K黄分色"},{"id":"26","title":"G585"},{"id":"27","title":"K14WG"},{"id":"28","title":"K18WG"},{"id":"29","title":"G18K分色"},{"id":"30","title":"PK"},{"id":"31","title":"PT"},{"id":"32","title":"AU750分色"},{"id":"33","title":"G14K玫瑰金"},{"id":"34","title":"不锈钢"},{"id":"35","title":"14K"},{"id":"36","title":"G18K分色"},{"id":"37","title":"9k"},{"id":"38","title":"千足黄金"},{"id":"39","title":"钯金"},{"id":"40","title":"14K黄白分色"},{"id":"41","title":"AU750"},{"id":"42","title":"皮绳"},{"id":"43","title":"PT999"},{"id":"44","title":"熔金PT"},{"id":"45","title":"9K红"},{"id":"46","title":"22K"},{"id":"47","title":"G18K3色分色"}],"address":{"phone":"13888888888","name":"杨明智","id":"7","addr":"辽宁省 丹东市 振兴区 内蒙古"},"modelQuality":[{"id":"1","title":"精品"},{"id":"2","title":"普通"},{"id":"3","title":"精工"},{"id":"4","title":"手工"},{"id":"5","title":"机制"}],"currentOrderlList":{"list_count":"2","list":[{"baseInfo":"类型:;手寸:1","number":"1","modelId":"20","price":"7158","id":"14","pic":"http://appapi.fanerweb.com/Uploads/Pics/2016-09-21/57b55369N2495bed4.jpg","title":"18K金钻石戒指 一款多戴 女款K金结婚戒指(A10100)","info":"主石(类型:锑钻,数量:1粒,规格:40,形状:方形,颜色:H-K,纯度:VS-VSS);副石A(类型:马眼钻,数量:1粒,规格:40,形状:梯形,颜色:H-K,纯度:VS-VSS)"},{"baseInfo":"类型:;手寸:1","number":"1","modelId":"20","price":"1793","id":"13","pic":"http://appapi.fanerweb.com/Uploads/Pics/2016-09-21/57b55369N2495bed4.jpg","title":"18K金钻石戒指 一款多戴 女款K金结婚戒指(A10100)","info":"主石(类型:马眼钻,数量:1粒,规格:40,形状:梯形,颜色:H-K,纯度:SI)"}]},"customer":{"keycode":"01040","customerFullName":" ","customerID":148,"customerName":"杭州浙地 "}}
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
         * modelColor : [{"id":"1","title":"PT900"},{"id":"2","title":"PT950"},{"id":"3","title":"Pd950"},{"id":"4","title":"G18K黄"},{"id":"5","title":"G18K白"},{"id":"6","title":"G18K玫瑰金"},{"id":"7","title":"G18K玫瑰金分色"},{"id":"8","title":"Au750白"},{"id":"9","title":"G750白"},{"id":"10","title":"G14K白"},{"id":"11","title":"G14K黄"},{"id":"12","title":"G9K白"},{"id":"13","title":"G9K黄"},{"id":"14","title":"925银"},{"id":"15","title":"G750分色"},{"id":"16","title":"G750电分色"},{"id":"17","title":"PT900足金"},{"id":"18","title":"G750玫瑰金"},{"id":"19","title":"AU750玫瑰金分色"},{"id":"20","title":"G750玫瑰金分色"},{"id":"21","title":"G750玫瑰金电分色"},{"id":"22","title":"G750黄"},{"id":"23","title":"G18k玫瑰金分色"},{"id":"24","title":"Pd990"},{"id":"25","title":"G18K黄分色"},{"id":"26","title":"G585"},{"id":"27","title":"K14WG"},{"id":"28","title":"K18WG"},{"id":"29","title":"G18K分色"},{"id":"30","title":"PK"},{"id":"31","title":"PT"},{"id":"32","title":"AU750分色"},{"id":"33","title":"G14K玫瑰金"},{"id":"34","title":"不锈钢"},{"id":"35","title":"14K"},{"id":"36","title":"G18K分色"},{"id":"37","title":"9k"},{"id":"38","title":"千足黄金"},{"id":"39","title":"钯金"},{"id":"40","title":"14K黄白分色"},{"id":"41","title":"AU750"},{"id":"42","title":"皮绳"},{"id":"43","title":"PT999"},{"id":"44","title":"熔金PT"},{"id":"45","title":"9K红"},{"id":"46","title":"22K"},{"id":"47","title":"G18K3色分色"}]
         * address : {"phone":"13888888888","name":"杨明智","id":"7","addr":"辽宁省 丹东市 振兴区 内蒙古"}
         * modelQuality : [{"id":"1","title":"精品"},{"id":"2","title":"普通"},{"id":"3","title":"精工"},{"id":"4","title":"手工"},{"id":"5","title":"机制"}]
         * currentOrderlList : {"list_count":"2","list":[{"baseInfo":"类型:;手寸:1","number":"1","modelId":"20","price":"7158","id":"14","pic":"http://appapi.fanerweb.com/Uploads/Pics/2016-09-21/57b55369N2495bed4.jpg","title":"18K金钻石戒指 一款多戴 女款K金结婚戒指(A10100)","info":"主石(类型:锑钻,数量:1粒,规格:40,形状:方形,颜色:H-K,纯度:VS-VSS);副石A(类型:马眼钻,数量:1粒,规格:40,形状:梯形,颜色:H-K,纯度:VS-VSS)"},{"baseInfo":"类型:;手寸:1","number":"1","modelId":"20","price":"1793","id":"13","pic":"http://appapi.fanerweb.com/Uploads/Pics/2016-09-21/57b55369N2495bed4.jpg","title":"18K金钻石戒指 一款多戴 女款K金结婚戒指(A10100)","info":"主石(类型:马眼钻,数量:1粒,规格:40,形状:梯形,颜色:H-K,纯度:SI)"}]}
         * customer : {"keycode":"01040","customerFullName":" ","customerID":148,"customerName":"杭州浙地 "}
         */
        private List<ModelColorEntity> modelColor;
        private AddressEntity address;
        private List<ModelQualityEntity> modelQuality;
        private CurrentOrderlListEntity currentOrderlList;
        private CustomerEntity customer;
        private OrderInfoEntity orderInfo;
        private Double totalPrice;
        private Double totalNeedPayPrice;
        private DefaultValue defaultValue;

        public DefaultValue getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(DefaultValue defaultValue) {
            this.defaultValue = defaultValue;
        }
        public OrderInfoEntity getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(OrderInfoEntity orderInfo) {
            this.orderInfo = orderInfo;
        }

        public Double getTotalNeedPayPrice() {
            return totalNeedPayPrice;
        }

        public void setTotalNeedPayPrice(Double totalNeedPayPrice) {
            this.totalNeedPayPrice = totalNeedPayPrice;
        }

        public Double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public void setModelColor(List<ModelColorEntity> modelColor) {
            this.modelColor = modelColor;
        }

        public void setAddress(AddressEntity address) {
            this.address = address;
        }

        public void setModelQuality(List<ModelQualityEntity> modelQuality) {
            this.modelQuality = modelQuality;
        }

        public void setCurrentOrderlList(CurrentOrderlListEntity currentOrderlList) {
            this.currentOrderlList = currentOrderlList;
        }

        public void setCustomer(CustomerEntity customer) {
            this.customer = customer;
        }

        public List<ModelColorEntity> getModelColor() {
            return modelColor;
        }

        public AddressEntity getAddress() {
            return address;
        }

        public List<ModelQualityEntity> getModelQuality() {
            return modelQuality;
        }

        public CurrentOrderlListEntity getCurrentOrderlList() {
            return currentOrderlList;
        }

        public CustomerEntity getCustomer() {
            return customer;
        }

        public class ModelColorEntity {
            /**
             * id : 1
             * title : PT900
             */
            private String id;
            private String title;
            private String price;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }


        public class ModelQualityEntity {
            /**
             * id : 1
             * title : 精品
             */
            private String id;
            private String title;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }


        public class OrderInfoEntity {
            /**
             * id : 1
             * title : 精品
             */
            private String customerName;
            private String purityName;
            private String qualityName;
            private String word;


            /*订单详情页面lIST  头部信息*/
            private String orderNum;
            private String goldPrice;
            private String orderDate;
            private String orderStatus;

            private String invoiceTitle;
            private String invoiceType;

            private String orderNote;

            public String getOrderNote() {
                return orderNote;
            }

            public void setOrderNote(String orderNote) {
                this.orderNote = orderNote;
            }

            public String getInvoiceTitle() {
                return invoiceTitle;
            }

            public void setInvoiceTitle(String invoiceTitle) {
                this.invoiceTitle = invoiceTitle;
            }

            public String getInvoiceType() {
                return invoiceType;
            }

            public void setInvoiceType(String invoiceType) {
                this.invoiceType = invoiceType;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getGoldPrice() {
                return goldPrice;
            }

            public void setGoldPrice(String goldPrice) {
                this.goldPrice = goldPrice;
            }

            public String getOrderDate() {
                return orderDate;
            }

            public void setOrderDate(String orderDate) {
                this.orderDate = orderDate;
            }

            public String getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(String orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getPurityName() {
                return purityName;
            }

            public void setPurityName(String purityName) {
                this.purityName = purityName;
            }

            public String getQualityName() {
                return qualityName;
            }

            public void setQualityName(String qualityName) {
                this.qualityName = qualityName;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }

            @Override
            public String toString() {
                return "OrderInfoEntity{" +
                        "customerName='" + customerName + '\'' +
                        ", purityName='" + purityName + '\'' +
                        ", qualityName='" + qualityName + '\'' +
                        ", word='" + word + '\'' +
                        ", orderNum='" + orderNum + '\'' +
                        ", goldPrice='" + goldPrice + '\'' +
                        ", orderDate='" + orderDate + '\'' +
                        ", orderStatus='" + orderStatus + '\'' +
                        '}';
            }
        }

        public class CurrentOrderlListEntity {
            /**
             * list_count : 2
             * list : [{"baseInfo":"类型:;手寸:1","number":"1","modelId":"20","price":"7158","id":"14","pic":"http://appapi.fanerweb.com/Uploads/Pics/2016-09-21/57b55369N2495bed4.jpg","title":"18K金钻石戒指 一款多戴 女款K金结婚戒指(A10100)","info":"主石(类型:锑钻,数量:1粒,规格:40,形状:方形,颜色:H-K,纯度:VS-VSS);副石A(类型:马眼钻,数量:1粒,规格:40,形状:梯形,颜色:H-K,纯度:VS-VSS)"},{"baseInfo":"类型:;手寸:1","number":"1","modelId":"20","price":"1793","id":"13","pic":"http://appapi.fanerweb.com/Uploads/Pics/2016-09-21/57b55369N2495bed4.jpg","title":"18K金钻石戒指 一款多戴 女款K金结婚戒指(A10100)","info":"主石(类型:马眼钻,数量:1粒,规格:40,形状:梯形,颜色:H-K,纯度:SI)"}]
             */
            private String list_count;
            private List<ListEntity> list;

            public void setList_count(String list_count) {
                this.list_count = list_count;
            }

            public void setList(List<ListEntity> list) {
                this.list = list;
            }

            public String getList_count() {
                return list_count;
            }

            public List<ListEntity> getList() {
                return list;
            }

            public class ListEntity {
                /**
                 * baseInfo : 类型:;手寸:1
                 * number : 1
                 * modelId : 20
                 * price : 7158
                 * id : 14
                 * pic : http://appapi.fanerweb.com/Uploads/Pics/2016-09-21/57b55369N2495bed4.jpg
                 * title : 18K金钻石戒指 一款多戴 女款K金结婚戒指(A10100)
                 * info : 主石(类型:锑钻,数量:1粒,规格:40,形状:方形,颜色:H-K,纯度:VS-VSS);副石A(类型:马眼钻,数量:1粒,规格:40,形状:梯形,颜色:H-K,纯度:VS-VSS)
                 */
                private String baseInfo;
                private String number;
                private String modelId;
                private String price;
                private String id;
                private String pic;
                private String title;
                private String info;
                private String needPayPrice;
                private Boolean isCheck;
                private String showPageType;
                private String purityName;

                public String getPurityName() {
                    return purityName;
                }

                public void setPurityName(String purityName) {
                    this.purityName = purityName;
                }

                public String getShowPageType() {
                    return showPageType;
                }

                public void setShowPageType(String showPageType) {
                    this.showPageType = showPageType;
                }

                public Boolean getCheck() {
                    return isCheck;
                }

                public void setCheck(Boolean check) {
                    isCheck = check;
                }

                public String getNeedPayPrice() {
                    return needPayPrice;
                }

                public void setNeedPayPrice(String needPayPrice) {
                    this.needPayPrice = needPayPrice;
                }

                @Override
                public String toString() {
                    return "ListEntity{" +
                            "baseInfo='" + baseInfo + '\'' +
                            ", number='" + number + '\'' +
                            ", modelId='" + modelId + '\'' +
                            ", price='" + price + '\'' +
                            ", id='" + id + '\'' +
                            ", pic='" + pic + '\'' +
                            ", title='" + title + '\'' +
                            ", info='" + info + '\'' +
                            '}';
                }

                public void setBaseInfo(String baseInfo) {
                    this.baseInfo = baseInfo;
                }

                public void setNumber(String number) {
                    this.number = number;
                }

                public void setModelId(String modelId) {
                    this.modelId = modelId;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public String getBaseInfo() {
                    return baseInfo;
                }

                public String getNumber() {
                    return number;
                }

                public String getModelId() {
                    return modelId;
                }

                public String getPrice() {
                    return price;
                }

                public String getId() {
                    return id;
                }

                public String getPic() {
                    return pic;
                }

                public String getTitle() {
                    return title;
                }

                public String getInfo() {
                    return info;
                }
            }
        }

    }
}
