package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public  class StoneBean {
    /**
     * id : 11
     * price : 2376.00
     * info : 证书:GIA证书号:2131040153 重量:0.75 颜色:G 净度:SI2
     *
     */

    private String id;
    private String price;
    private String info;
    private int number=1;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
