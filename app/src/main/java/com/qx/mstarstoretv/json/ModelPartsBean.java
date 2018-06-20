package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class ModelPartsBean {
    /**
     * pid : 104926
     * partSort : 2
     * price : 0
     * title : 圆形
     * pics : http://appapi1.fanerweb.com/
     * picm : http://appapi1.fanerweb.com/
     * picb : http://appapi1.fanerweb.com/
     * modelPartCount : ["","",""]
     * selectProItem : {"id":"104930","title":"三爪圆形中圈","price":"0","handSize":"","word":"","modelPic":[{"pics":"http://appapi1.fanerweb.com/","picm":"http://appapi1.fanerweb.com/","picb":"http://appapi1.fanerweb.com/"}]}
     */

    private String pid;
    private String partSort;
    private String price;
    private String title;
    private String pics;
    private String picm;
    private String picb;
    private String partCount;
    private SelectProItemBean selectProItem;
    private List<String> modelPartCount;

    public String getPartCount() {
        return partCount;
    }

    public void setPartCount(String partCount) {
        this.partCount = partCount;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPartSort() {
        return partSort;
    }

    public void setPartSort(String partSort) {
        this.partSort = partSort;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getPicm() {
        return picm;
    }

    public void setPicm(String picm) {
        this.picm = picm;
    }

    public String getPicb() {
        return picb;
    }

    public void setPicb(String picb) {
        this.picb = picb;
    }

    public SelectProItemBean getSelectProItem() {
        return selectProItem;
    }

    public void setSelectProItem(SelectProItemBean selectProItem) {
        this.selectProItem = selectProItem;
    }

    public List<String> getModelPartCount() {
        return modelPartCount;
    }

    public void setModelPartCount(List<String> modelPartCount) {
        this.modelPartCount = modelPartCount;
    }


}
