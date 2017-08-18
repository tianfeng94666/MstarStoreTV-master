package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2017/3/21 0021.
 */


public class OrderInfoEntity {
    /**
     * confirmDate : 2016-12-05 11:46:46
     * otherInfo : 成色:; 质量等级:; 字印:2222wa; 金价:0/g; 件数:1
     * needPayPrice : 0
     * address : 辽宁省 丹东市 振兴区 哄哄
     * totalPrice : 0
     * orderStatusTitle : 待生产
     * orderNum : AP2016112310378
     * orderDate : 2016-11-23 10:32:48
     * customerName : 旺道-0112
     */
    private String confirmDate;
    private String otherInfo;
    private String needPayPrice;
    private String address;
    private String totalPrice;
    private String orderStatusTitle;
    private String orderNum;
    private String orderDate;
    private String customerName;
    private String invoiceType;
    private String invoiceTitle;
    private String orderNote;

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public void setNeedPayPrice(String needPayPrice) {
        this.needPayPrice = needPayPrice;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderStatusTitle(String orderStatusTitle) {
        this.orderStatusTitle = orderStatusTitle;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public String getNeedPayPrice() {
        return needPayPrice;
    }

    public String getAddress() {
        return address;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getOrderStatusTitle() {
        return orderStatusTitle;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }
}