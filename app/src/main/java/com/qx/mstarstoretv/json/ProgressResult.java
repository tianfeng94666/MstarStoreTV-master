package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2016/12/23.
 */
public class ProgressResult {

    /**
     * data : {"orderlList":[{"number":1,"modelInfo":"类型:女戒;手寸:1","progress":[{"currentFlow":3,"flowInfo":"类型:女戒;数量:1;进度:执模发出"},{"currentFlow":4,"flowInfo":"类型:吊坠;数量:1;进度:执模加工"},{"currentFlow":4,"flowInfo":"类型:耳吊;数量:1;进度:执模加工"}],"pic":"http://124.172.169.117:9888/220X220/Module/Image1/1/Y07363.jpg","title":"Y07363"},{"number":1,"modelInfo":"类型:手镯;手寸:1","progress":[{"currentFlow":3,"flowInfo":"类型:女戒;数量:1;进度:执模发出"},{"currentFlow":4,"flowInfo":"类型:吊坠;数量:1;进度:执模加工"},{"currentFlow":4,"flowInfo":"类型:耳吊;数量:1;进度:执模加工"},{"currentFlow":3,"flowInfo":"类型:女戒;数量:1;进度:执模发出"},{"currentFlow":4,"flowInfo":"类型:吊坠;数量:1;进度:执模加工"},{"currentFlow":4,"flowInfo":"类型:耳吊;数量:1;进度:执模加工"}],"pic":"","title":"Z-S8271"}],"orderInfo":{"otherInfo":"成色:G18K白;质量:精品;","ConfirmDate":"2016-12-22 15:55:26","orderNum":"AP2016122115131"},"flowTotalCount":24}
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
         * orderlList : [{"number":1,"modelInfo":"类型:女戒;手寸:1","progress":[{"currentFlow":3,"flowInfo":"类型:女戒;数量:1;进度:执模发出"},{"currentFlow":4,"flowInfo":"类型:吊坠;数量:1;进度:执模加工"},{"currentFlow":4,"flowInfo":"类型:耳吊;数量:1;进度:执模加工"}],"pic":"http://124.172.169.117:9888/220X220/Module/Image1/1/Y07363.jpg","title":"Y07363"},{"number":1,"modelInfo":"类型:手镯;手寸:1","progress":[{"currentFlow":3,"flowInfo":"类型:女戒;数量:1;进度:执模发出"},{"currentFlow":4,"flowInfo":"类型:吊坠;数量:1;进度:执模加工"},{"currentFlow":4,"flowInfo":"类型:耳吊;数量:1;进度:执模加工"},{"currentFlow":3,"flowInfo":"类型:女戒;数量:1;进度:执模发出"},{"currentFlow":4,"flowInfo":"类型:吊坠;数量:1;进度:执模加工"},{"currentFlow":4,"flowInfo":"类型:耳吊;数量:1;进度:执模加工"}],"pic":"","title":"Z-S8271"}]
         * orderInfo : {"otherInfo":"成色:G18K白;质量:精品;","ConfirmDate":"2016-12-22 15:55:26","orderNum":"AP2016122115131"}
         * flowTotalCount : 24
         */
        private List<OrderlListEntity> orderlList;
        private OrderInfoEntity orderInfo;
        private int flowTotalCount;

        public void setOrderlList(List<OrderlListEntity> orderlList) {
            this.orderlList = orderlList;
        }

        public void setOrderInfo(OrderInfoEntity orderInfo) {
            this.orderInfo = orderInfo;
        }

        public void setFlowTotalCount(int flowTotalCount) {
            this.flowTotalCount = flowTotalCount;
        }

        public List<OrderlListEntity> getOrderlList() {
            return orderlList;
        }

        public OrderInfoEntity getOrderInfo() {
            return orderInfo;
        }

        public int getFlowTotalCount() {
            return flowTotalCount;
        }

        public class OrderlListEntity {
            /**
             * number : 1
             * modelInfo : 类型:女戒;手寸:1
             * progress : [{"currentFlow":3,"flowInfo":"类型:女戒;数量:1;进度:执模发出"},{"currentFlow":4,"flowInfo":"类型:吊坠;数量:1;进度:执模加工"},{"currentFlow":4,"flowInfo":"类型:耳吊;数量:1;进度:执模加工"}]
             * pic : http://124.172.169.117:9888/220X220/Module/Image1/1/Y07363.jpg
             * title : Y07363
             */
            private String number;
            private String modelInfo;
            private List<ProgressEntity> progress;
            private String pic;
            private String title;

            public void setNumber(String number) {
                this.number = number;
            }

            public void setModelInfo(String modelInfo) {
                this.modelInfo = modelInfo;
            }

            public void setProgress(List<ProgressEntity> progress) {
                this.progress = progress;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getNumber() {
                return number;
            }

            public String getModelInfo() {
                return modelInfo;
            }

            public List<ProgressEntity> getProgress() {
                return progress;
            }

            public String getPic() {
                return pic;
            }

            public String getTitle() {
                return title;
            }

            public class ProgressEntity {
                /**
                 * currentFlow : 3
                 * flowInfo : 类型:女戒;数量:1;进度:执模发出
                 */
                private String currentFlow;
                private String flowInfo;

                public void setCurrentFlow(String currentFlow) {
                    this.currentFlow = currentFlow;
                }

                public void setFlowInfo(String flowInfo) {
                    this.flowInfo = flowInfo;
                }

                public String getCurrentFlow() {
                    return currentFlow;
                }

                public String getFlowInfo() {
                    return flowInfo;
                }
            }
        }

        public class OrderInfoEntity {
            /**
             * otherInfo : 成色:G18K白;质量:精品;
             * ConfirmDate : 2016-12-22 15:55:26
             * orderNum : AP2016122115131
             */
            private String otherInfo;
            private String ConfirmDate;
            private String orderNum;

            public void setOtherInfo(String otherInfo) {
                this.otherInfo = otherInfo;
            }

            public void setConfirmDate(String ConfirmDate) {
                this.ConfirmDate = ConfirmDate;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getOtherInfo() {
                return otherInfo;
            }

            public String getConfirmDate() {
                return ConfirmDate;
            }

            public String getOrderNum() {
                return orderNum;
            }
        }
    }
}
