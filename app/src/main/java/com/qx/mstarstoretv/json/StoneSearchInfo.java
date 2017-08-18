package com.qx.mstarstoretv.json;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class StoneSearchInfo implements Serializable{
    private String cerAuth;
    private String color;
    private String shape;
    private String purity;
    private String cut;
    private String polishing;
    private String symmetric;
    private String fluorescence;
    private String price;
    private String weight;
    private String percent;

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCerAuth() {
        return cerAuth;
    }

    public void setCerAuth(String cerAuth) {
        this.cerAuth = cerAuth;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getPurity() {
        return purity;
    }

    public void setPurity(String purity) {
        this.purity = purity;
    }

    public String getCut() {
        return cut;
    }

    public void setCut(String cut) {
        this.cut = cut;
    }

    public String getPolishing() {
        return polishing;
    }

    public void setPolishing(String polishing) {
        this.polishing = polishing;
    }

    public String getSymmetric() {
        return symmetric;
    }

    public void setSymmetric(String symmetric) {
        this.symmetric = symmetric;
    }

    public String getFluorescence() {
        return fluorescence;
    }

    public void setFluorescence(String fluorescence) {
        this.fluorescence = fluorescence;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
