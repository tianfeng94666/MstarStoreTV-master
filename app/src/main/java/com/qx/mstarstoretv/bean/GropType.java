package com.qx.mstarstoretv.bean;

/**
 * Created by Administrator on 2016/10/24.
 */
public class GropType {

    String name;
    String id;
    String mulSelect;

    public String getMulSelect() {
        return mulSelect;
    }

    public void setMulSelect(String mulSelect) {
        this.mulSelect = mulSelect;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GropType() {
    }

    public GropType(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
