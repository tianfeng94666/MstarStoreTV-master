package com.qx.mstarstoretv.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class OrderSearchBean implements Serializable {
    private int customerID;//客户id
    private int skeyid;//搜索类型id
    private String keyword;//关键字
    private int sscopeid;//搜索范围id
    private String sdate;//开始时间
    private String edate;//结束时间

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getSkeyid() {
        return skeyid;
    }

    public void setSkeyid(int skeyid) {
        this.skeyid = skeyid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getSscopeid() {
        return sscopeid;
    }

    public void setSscopeid(int sscopeid) {
        this.sscopeid = sscopeid;
    }

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
}
