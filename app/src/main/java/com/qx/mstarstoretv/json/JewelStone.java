package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class JewelStone {


    private String jewelStoneCode;
    private String jewelStoneColor;
    private String jewelStoneId;
    private String jewelStonePrice;
    private String jewelStonePurity;
    private String jewelStoneWeight;
    private String jewelStoneShape;

    public String getJewelStoneShape() {
        return jewelStoneShape;
    }

    public void setJewelStoneShape(String jewelStoneShape) {
        this.jewelStoneShape = jewelStoneShape;
    }

    public String getJewelStoneCode() {
        return jewelStoneCode;
    }

    public void setJewelStoneCode(String jewelStoneCode) {
        this.jewelStoneCode = jewelStoneCode;
    }

    public String getJewelStoneColor() {
        return jewelStoneColor;
    }

    public void setJewelStoneColor(String jewelStoneColor) {
        this.jewelStoneColor = jewelStoneColor;
    }

    public String getJewelStoneId() {
        return jewelStoneId;
    }

    public void setJewelStoneId(String jewelStoneId) {
        this.jewelStoneId = jewelStoneId;
    }

    public String getJewelStonePrice() {
        return jewelStonePrice;
    }

    public void setJewelStonePrice(String jewelStonePrice) {
        this.jewelStonePrice = jewelStonePrice;
    }

    public String getJewelStonePurity() {
        return jewelStonePurity;
    }

    public void setJewelStonePurity(String jewelStonePurity) {
        this.jewelStonePurity = jewelStonePurity;
    }

    public String getJewelStoneWeight() {
        return jewelStoneWeight;
    }

    public void setJewelStoneWeight(String jewelStoneWeight) {
        this.jewelStoneWeight = jewelStoneWeight;
    }

    public String getTotalString() {
        return "形状：" + getJewelStoneShape() + ";净度：" + getJewelStonePurity() + ";价格：" + getJewelStonePrice() + ";重量：" + getJewelStoneWeight() + ";证书编号：" + getJewelStoneCode();
    }
}
