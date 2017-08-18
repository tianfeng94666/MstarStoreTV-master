package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class SearchOrderResult {

    /**
     * response :
     * error : 0
     * message :
     * data : {"searchKeyword":[{"id":1,"title":"订单号"},{"id":2,"title":"款号"}],"searchScope":[{"id":1,"title":"我的订单"},{"id":2,"title":"所有订单"}],"searchDateScope":[{"titie":"今天","sdate":"2017-03-24","edate":"2017-03-24","isDefault":1},{"title":"昨天","sdate":"2017-03-23","edate":"2017-03-23","isDefault":0},{"title":"最近三天","sdate":"2017-03-21","edate":"2017-03-24","isDefault":0},{"title":"最近一个月","sdate":"2017-02-24","edate":"2017-03-24","isDefault":0}],"startDate":"2017-03-24","endDate":"2017-03-24"}
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
         * searchKeyword : [{"id":1,"title":"订单号"},{"id":2,"title":"款号"}]
         * searchScope : [{"id":1,"title":"我的订单"},{"id":2,"title":"所有订单"}]
         * searchDateScope : [{"titie":"今天","sdate":"2017-03-24","edate":"2017-03-24","isDefault":1},{"title":"昨天","sdate":"2017-03-23","edate":"2017-03-23","isDefault":0},{"title":"最近三天","sdate":"2017-03-21","edate":"2017-03-24","isDefault":0},{"title":"最近一个月","sdate":"2017-02-24","edate":"2017-03-24","isDefault":0}]
         * startDate : 2017-03-24
         * endDate : 2017-03-24
         */

        private String startDate;
        private String endDate;
        private List<SearchKeywordBean> searchKeyword;
        private List<SearchScopeBean> searchScope;
        private List<SearchDateScopeBean> searchDateScope;

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public List<SearchKeywordBean> getSearchKeyword() {
            return searchKeyword;
        }

        public void setSearchKeyword(List<SearchKeywordBean> searchKeyword) {
            this.searchKeyword = searchKeyword;
        }

        public List<SearchScopeBean> getSearchScope() {
            return searchScope;
        }

        public void setSearchScope(List<SearchScopeBean> searchScope) {
            this.searchScope = searchScope;
        }

        public List<SearchDateScopeBean> getSearchDateScope() {
            return searchDateScope;
        }

        public void setSearchDateScope(List<SearchDateScopeBean> searchDateScope) {
            this.searchDateScope = searchDateScope;
        }

        public static class SearchKeywordBean {
            /**
             * id : 1
             * title : 订单号
             */

            private int id;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class SearchScopeBean {
            /**
             * id : 1
             * title : 我的订单
             */

            private int id;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class SearchDateScopeBean {
            /**
             * titie : 今天
             * sdate : 2017-03-24
             * edate : 2017-03-24
             * isDefault : 1
             */

            private String sdate;
            private String edate;
            private int isDefault;
            private String title;


            public String getSdate() {
                return sdate;
            }

            public void setSdate(String sdate) {
                this.sdate = sdate;
            }

            public String getEdate() {
                return edate;
            }

            public void setEdate(String edate) {
                this.edate = edate;
            }

            public int getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
