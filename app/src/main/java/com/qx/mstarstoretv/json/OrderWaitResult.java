package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
public class OrderWaitResult {


    /**
     * data : {"orderList":{"list":[{"customerName":"杭州浙地订做","id":"157","modifyDate":"2017-02-18 16:57:27","needPayPrice":33.005052,"orderDate":"2017-02-18 16:57:27","orderNum":"AP170218157","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金分色; 质量等级:精工; 字印:; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/Y06218-10.JPG"],"totalPrice":330.05052},{"customerName":"杭州浙地许鹏贤","id":"156","modifyDate":"2017-02-18 16:42:43","needPayPrice":0,"orderDate":"2017-02-18 16:42:43","orderNum":"AP170218156","orderStatusTitle":"待付定金","otherInfo":"成色:G750白; 质量等级:精工; 字印:; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/94/L1561.jpg"],"totalPrice":0},{"customerName":"杭州浙地(周坚强)","id":"57","modifyDate":"2017-02-11 14:08:29","needPayPrice":152.85102288,"orderDate":"2017-02-11 14:08:29","orderNum":"AP170211057","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金分色; 质量等级:精工; 字印:; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/Y06155-60.jpg"],"totalPrice":1528.5102288},{"customerName":"杭州浙地董淑梅订做","id":"56","modifyDate":"2017-02-11 12:29:30","needPayPrice":552.0054816,"orderDate":"2017-02-11 12:28:05","orderNum":"AP170211256","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金分色; 质量等级:普通; 字印:我们; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/QSY0074-35.jpg"],"totalPrice":5520.054816},{"customerName":"卡仑帝深圳展厅0208","id":"45","modifyDate":"2017-02-10 12:18:34","needPayPrice":552.0054816,"orderDate":"2017-02-10 12:18:34","orderNum":"AP170210145","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金; 质量等级:普通; 字印:xianxianjun; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/QSY0074-35.jpg"],"totalPrice":5520.054816},{"customerName":"世爵百年KD3680-NJ","id":"44","modifyDate":"2017-02-10 12:12:41","needPayPrice":89.9123664,"orderDate":"2017-02-10 12:12:41","orderNum":"AP170210144","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金; 质量等级:普通; 字印:; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/Y06155-60.jpg"],"totalPrice":899.123664},{"customerName":"卡仑帝深圳展厅0208","id":"32","modifyDate":"2017-02-09 16:21:51","needPayPrice":105.4382348,"orderDate":"2017-02-09 15:52:47","orderNum":"AP170209532","orderStatusTitle":"待付定金","otherInfo":"成色:PT900; 质量等级:普通; 字印:yyyyy; 金价:214/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/Y06155-60.jpg"],"totalPrice":1054.382348},{"customerName":"名匠一号0206","id":"18","modifyDate":"2017-02-07 16:37:52","needPayPrice":120.93129344,"orderDate":"2017-02-07 16:37:52","orderNum":"AP170207318","orderStatusTitle":"待付定金","otherInfo":"成色:Au750白; 质量等级:普通; 字印:vvvv; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/93/SY02222-60.jpg"],"totalPrice":1209.3129344}],"list_count":"9"},"statusCount":{"finished":0,"produceding":16,"waitForSend":0,"waitForValidate":9}}
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
         * orderList : {"list":[{"customerName":"杭州浙地订做","id":"157","modifyDate":"2017-02-18 16:57:27","needPayPrice":33.005052,"orderDate":"2017-02-18 16:57:27","orderNum":"AP170218157","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金分色; 质量等级:精工; 字印:; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/Y06218-10.JPG"],"totalPrice":330.05052},{"customerName":"杭州浙地许鹏贤","id":"156","modifyDate":"2017-02-18 16:42:43","needPayPrice":0,"orderDate":"2017-02-18 16:42:43","orderNum":"AP170218156","orderStatusTitle":"待付定金","otherInfo":"成色:G750白; 质量等级:精工; 字印:; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/94/L1561.jpg"],"totalPrice":0},{"customerName":"杭州浙地(周坚强)","id":"57","modifyDate":"2017-02-11 14:08:29","needPayPrice":152.85102288,"orderDate":"2017-02-11 14:08:29","orderNum":"AP170211057","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金分色; 质量等级:精工; 字印:; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/Y06155-60.jpg"],"totalPrice":1528.5102288},{"customerName":"杭州浙地董淑梅订做","id":"56","modifyDate":"2017-02-11 12:29:30","needPayPrice":552.0054816,"orderDate":"2017-02-11 12:28:05","orderNum":"AP170211256","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金分色; 质量等级:普通; 字印:我们; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/QSY0074-35.jpg"],"totalPrice":5520.054816},{"customerName":"卡仑帝深圳展厅0208","id":"45","modifyDate":"2017-02-10 12:18:34","needPayPrice":552.0054816,"orderDate":"2017-02-10 12:18:34","orderNum":"AP170210145","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金; 质量等级:普通; 字印:xianxianjun; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/QSY0074-35.jpg"],"totalPrice":5520.054816},{"customerName":"世爵百年KD3680-NJ","id":"44","modifyDate":"2017-02-10 12:12:41","needPayPrice":89.9123664,"orderDate":"2017-02-10 12:12:41","orderNum":"AP170210144","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金; 质量等级:普通; 字印:; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/Y06155-60.jpg"],"totalPrice":899.123664},{"customerName":"卡仑帝深圳展厅0208","id":"32","modifyDate":"2017-02-09 16:21:51","needPayPrice":105.4382348,"orderDate":"2017-02-09 15:52:47","orderNum":"AP170209532","orderStatusTitle":"待付定金","otherInfo":"成色:PT900; 质量等级:普通; 字印:yyyyy; 金价:214/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/Y06155-60.jpg"],"totalPrice":1054.382348},{"customerName":"名匠一号0206","id":"18","modifyDate":"2017-02-07 16:37:52","needPayPrice":120.93129344,"orderDate":"2017-02-07 16:37:52","orderNum":"AP170207318","orderStatusTitle":"待付定金","otherInfo":"成色:Au750白; 质量等级:普通; 字印:vvvv; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/93/SY02222-60.jpg"],"totalPrice":1209.3129344}],"list_count":"9"}
         * statusCount : {"finished":0,"produceding":16,"waitForSend":0,"waitForValidate":9}
         */

        private OrderListBean orderList;
        private StatusCountBean statusCount;

        public OrderListBean getOrderList() {
            return orderList;
        }

        public void setOrderList(OrderListBean orderList) {
            this.orderList = orderList;
        }

        public StatusCountBean getStatusCount() {
            return statusCount;
        }

        public void setStatusCount(StatusCountBean statusCount) {
            this.statusCount = statusCount;
        }

        public static class OrderListBean {
            /**
             * list : [{"customerName":"杭州浙地订做","id":"157","modifyDate":"2017-02-18 16:57:27","needPayPrice":33.005052,"orderDate":"2017-02-18 16:57:27","orderNum":"AP170218157","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金分色; 质量等级:精工; 字印:; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/Y06218-10.JPG"],"totalPrice":330.05052},{"customerName":"杭州浙地许鹏贤","id":"156","modifyDate":"2017-02-18 16:42:43","needPayPrice":0,"orderDate":"2017-02-18 16:42:43","orderNum":"AP170218156","orderStatusTitle":"待付定金","otherInfo":"成色:G750白; 质量等级:精工; 字印:; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/94/L1561.jpg"],"totalPrice":0},{"customerName":"杭州浙地(周坚强)","id":"57","modifyDate":"2017-02-11 14:08:29","needPayPrice":152.85102288,"orderDate":"2017-02-11 14:08:29","orderNum":"AP170211057","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金分色; 质量等级:精工; 字印:; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/Y06155-60.jpg"],"totalPrice":1528.5102288},{"customerName":"杭州浙地董淑梅订做","id":"56","modifyDate":"2017-02-11 12:29:30","needPayPrice":552.0054816,"orderDate":"2017-02-11 12:28:05","orderNum":"AP170211256","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金分色; 质量等级:普通; 字印:我们; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/QSY0074-35.jpg"],"totalPrice":5520.054816},{"customerName":"卡仑帝深圳展厅0208","id":"45","modifyDate":"2017-02-10 12:18:34","needPayPrice":552.0054816,"orderDate":"2017-02-10 12:18:34","orderNum":"AP170210145","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金; 质量等级:普通; 字印:xianxianjun; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/QSY0074-35.jpg"],"totalPrice":5520.054816},{"customerName":"世爵百年KD3680-NJ","id":"44","modifyDate":"2017-02-10 12:12:41","needPayPrice":89.9123664,"orderDate":"2017-02-10 12:12:41","orderNum":"AP170210144","orderStatusTitle":"待付定金","otherInfo":"成色:G18K玫瑰金; 质量等级:普通; 字印:; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/Y06155-60.jpg"],"totalPrice":899.123664},{"customerName":"卡仑帝深圳展厅0208","id":"32","modifyDate":"2017-02-09 16:21:51","needPayPrice":105.4382348,"orderDate":"2017-02-09 15:52:47","orderNum":"AP170209532","orderStatusTitle":"待付定金","otherInfo":"成色:PT900; 质量等级:普通; 字印:yyyyy; 金价:214/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/97/Y06155-60.jpg"],"totalPrice":1054.382348},{"customerName":"名匠一号0206","id":"18","modifyDate":"2017-02-07 16:37:52","needPayPrice":120.93129344,"orderDate":"2017-02-07 16:37:52","orderNum":"AP170207318","orderStatusTitle":"待付定金","otherInfo":"成色:Au750白; 质量等级:普通; 字印:vvvv; 金价:210.2/g; 件数:1","pics":["http://124.172.169.117:9888/220X220/Module/Image1/93/SY02222-60.jpg"],"totalPrice":1209.3129344}]
             * list_count : 9
             */

            private String list_count;
            private List<ListBean> list;

            public String getList_count() {
                return list_count;
            }

            public void setList_count(String list_count) {
                this.list_count = list_count;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * customerName : 杭州浙地订做
                 * id : 157
                 * modifyDate : 2017-02-18 16:57:27
                 * needPayPrice : 33.005052
                 * orderDate : 2017-02-18 16:57:27
                 * orderNum : AP170218157
                 * orderStatusTitle : 待付定金
                 * otherInfo : 成色:G18K玫瑰金分色; 质量等级:精工; 字印:; 金价:210.2/g; 件数:1
                 * pics : ["http://124.172.169.117:9888/220X220/Module/Image1/97/Y06218-10.JPG"]
                 * totalPrice : 330.05052
                 */

                private String customerName;
                private String id;
                private String modifyDate;
                private double needPayPrice;
                private String orderDate;
                private String orderNum;
                private String orderStatusTitle;
                private String otherInfo;
                private double totalPrice;
                private List<String> pics;

                public String getCustomerName() {
                    return customerName;
                }

                public void setCustomerName(String customerName) {
                    this.customerName = customerName;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getModifyDate() {
                    return modifyDate;
                }

                public void setModifyDate(String modifyDate) {
                    this.modifyDate = modifyDate;
                }

                public double getNeedPayPrice() {
                    return needPayPrice;
                }

                public void setNeedPayPrice(double needPayPrice) {
                    this.needPayPrice = needPayPrice;
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

                public String getOrderStatusTitle() {
                    return orderStatusTitle;
                }

                public void setOrderStatusTitle(String orderStatusTitle) {
                    this.orderStatusTitle = orderStatusTitle;
                }

                public String getOtherInfo() {
                    return otherInfo;
                }

                public void setOtherInfo(String otherInfo) {
                    this.otherInfo = otherInfo;
                }

                public double getTotalPrice() {
                    return totalPrice;
                }

                public void setTotalPrice(double totalPrice) {
                    this.totalPrice = totalPrice;
                }

                public List<String> getPics() {
                    return pics;
                }

                public void setPics(List<String> pics) {
                    this.pics = pics;
                }
            }
        }

        public static class StatusCountBean {
            /**
             * finished : 0
             * produceding : 16
             * waitForSend : 0
             * waitForValidate : 9
             */

            private int finished;
            private int produceding;
            private int waitForSend;
            private int waitForValidate;

            public int getFinished() {
                return finished;
            }

            public void setFinished(int finished) {
                this.finished = finished;
            }

            public int getProduceding() {
                return produceding;
            }

            public void setProduceding(int produceding) {
                this.produceding = produceding;
            }

            public int getWaitForSend() {
                return waitForSend;
            }

            public void setWaitForSend(int waitForSend) {
                this.waitForSend = waitForSend;
            }

            public int getWaitForValidate() {
                return waitForValidate;
            }

            public void setWaitForValidate(int waitForValidate) {
                this.waitForValidate = waitForValidate;
            }
        }
    }
}
