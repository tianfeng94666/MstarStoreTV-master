package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class ModelListEntity {
    /**
     * baseInfo : 类型:皮带头;手寸:1
     * needPayPrice : 0
     * number : 2
     * stonePrice : 0
     * modelId : 21
     * price : 0
     * id : 124
     * pic : http://appapi.fanerweb.com/Uploads/Pics/2016-09-21/577dd3b0N5baaf195.jpg
     * title : 18K金气质锁骨链群镶钻石链牌 弯弯的月亮女款K金项链吊坠 18K玫瑰金
     * info : 主石(类型:方钻,数量:1粒,规格:0~30,形状:马鞍,颜色:H+,纯度:SI)
     */
    private String baseInfo;
    private String needPayPrice;
    private String number;
    private String stonePrice;
    private String modelId;
    private String price;
    private String id;
    private String pic;
    private String title;
    private String info;
    private String invoiceTitle;
    private String invoiceType;

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

    public void setBaseInfo(String baseInfo) {
        this.baseInfo = baseInfo;
    }

    public void setNeedPayPrice(String needPayPrice) {
        this.needPayPrice = needPayPrice;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setStonePrice(String stonePrice) {
        this.stonePrice = stonePrice;
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

    public String getNeedPayPrice() {
        return needPayPrice;
    }

    public String getNumber() {
        return number;
    }

    public String getStonePrice() {
        return stonePrice;
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